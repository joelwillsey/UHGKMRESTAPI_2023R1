#!/bin/bash

export JAVA_HOME=/app_2/verint/products/java/jdk1.8.0_101
export JBOSS_HOME=/app_2/verint/containers/jboss-eap-6.4

cd $(dirname ${0}); SCRIPTHOME=${PWD}

if [[ $# == 0 ]]
then ## no commands passed
	echo "
Usage
	stop.sh environment [environment-name] container [container-name]

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

if [[ $(ps -ef | grep java | grep "${CONTAINER}" | awk '{print $2}' | wc -l) > 0 ]]
then
  kill $( ps -ef | grep java | grep "${CONTAINER}" | awk '{print $2}' )
  sleep 5
  if [[ $(ps -ef | grep java | grep "${CONTAINER}" | awk '{print $2}' | wc -l) > 0 ]]
  then
    kill -9 $( ps -ef | grep java | grep "${CONTAINER}" | awk '{print $2}' )
  fi
else
  echo "No processes found"
fi

