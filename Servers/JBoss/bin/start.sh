#!/bin/bash
cd $(dirname ${0}); SCRIPTHOME=${PWD}

if [[ $# == 0 ]]
then ## no commands passed
	echo "
Usage
	start.sh environment [environment-name] container [container-name]

Example
	start.sh environment dev container dev_rest
"
	exit 1
fi
while [[ ${#} > 0 ]]
do
	ARGUMENT=$(echo "${1}" | tr '[:upper:]' '[:lower:]' )
	case ${ARGUMENT} in
		environment)
			((A++)); shift; export ENVIRONMENT=${1};;
		container)
			((A++)); shift; export CONTAINER=${1};;
	esac

	shift
done

if [[ -z ${ENVIRONMENT} ]]
then
	# environment name not set
	echo "No environment name supplied"
	exit 3
elif [[ -z ${CONTAINER} ]]
then
	# container name not set
	echo "No container name supplied"
	exit 4
fi

HOSTNAME=$(hostname)

# source properties for this specific container
SETENV_FILE="${SCRIPTHOME}/../kmservices/config/environment.${ENVIRONMENT}/machine.${HOSTNAME}/container.${CONTAINER}/setenv.sh"
if [[ -f ${SETENV_FILE} ]]
then
	source ${SETENV_FILE}
else
	echo "Unable to locate ${SETENV_FILE}, cannot continue"
	exit 2
fi

# Setting up variables for JBoss
# at this point we should have valid values for ENVIRONMENT, HOSTNAME, and CONTAINER
# ... should
export JAVA_HOME=/app_2/verint/products/java/jdk1.8.0_101
export JBOSS_HOME=/app_2/verint/containers/jboss-eap-6.4
export CONFIG_HOME=/app_2/verint/projects/uhgiq/restapi/${CONTAINER}
export LOGS_HOME=/app_2/verint/projects/uhgiq/logs/${HOSTNAME}-${CONTAINER}
export JAVA_OPTS="-XX:+UseConcMarkSweepGC \
  -XX:+DisableExplicitGC \
  -verbose:gc \
  -XX:+UseCompressedOops \
  -Xloggc:${LOGS_HOME}/gc.log \
  -XX:+PrintGCDetails \
  -XX:+PrintGCTimeStamps \
  -XX:+UseGCLogFileRotation \
  -XX:NumberOfGCLogFiles=5 \
  -XX:GCLogFileSize=1M \
  -Xms4g \
  -Xmx4g \
  -XX:NewSize=1g \
  -XX:MaxNewSize=1g \
  -Djavax.net.ssl.trustStore=/app_2/verint/projects/uhgiq/AgentDesktop/config/environment.${ENVIRONMENT}/components/ssl/trust.jks \
  -Djavax.net.ssl.trustStorePassword=changeit \
  -Dorg.apache.coyote.http11.Http11Protocol.MAX_HEADER_SIZE=65536 \
  -Dorg.apache.coyote.ajp.MAX_PACKET_SIZE=65536"

# create the logging directory
if [[ ! -d ${LOGS_HOME}/jboss ]]
then
  mkdir -p ${LOGS_HOME}/jboss
fi

if [[ $(ps -ef | grep java | grep "${CONTAINER}" | awk '{print $2}' | wc -l) > 0 ]]
then
  echo "Server still running."
  exit 1
fi

${JBOSS_HOME}/bin/standalone.sh \
  --server-config=standalone-full.xml \
  -Djboss.server.name=${CONTAINER} \
  -Djboss.server.base.dir=${CONFIG_HOME}/ \
  -Djboss.server.config.dir=${CONFIG_HOME}/configuration \
  -Djboss.server.log.dir=${LOGS_HOME}/jboss \
  -Djboss.bind.address.management=${HOSTNAME} \
  -Djboss.bind.address=0.0.0.0 \
  -Djboss.socket.binding.port-offset=${PORT_OFFSET} \
  -Denvironment.name=${ENVIRONMENT} \
  -Dmachine.name=${HOSTNAME} \
  -Dcontainer.name=${CONTAINER} \
  -DconfigLocation=${CONFIG_HOME}/kmservices/config \
  -DlogFile=${LOGS_HOME} \
> ${LOGS_HOME}/jboss/stdout.log  2>&1 &
