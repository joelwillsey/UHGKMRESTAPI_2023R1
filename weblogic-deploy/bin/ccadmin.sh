#!/bin/bash

set +v

echo
echo ==========================
echo KMRestAPI Wrapper 
echo ==========================
echo

# =====================
# FUNCTION DECLARATIONS
# =====================

# Called if parameters are missing
function blank () {
	echo ---
	echo "Missing parameters. Command should be in the structure:"
	echo "./ccadmin.sh [command-name] [environment.name] [container.name]"
	echo "Example: ./ccadmin.sh create-container dev km"
	echo "---"
	endScriptFail
}

# Success Exit
function endScriptSuccess () {
	echo "BUILD SUCCESSFUL"
	exit 0
}

# Success Fail
function endScriptFail () {
	echo "BUILD FAILED"
	exit 1
}

function setConfig () {
	
	# Assign Variables
	COMPUTER_NAME=$(hostname -f)
	DIRECTORY="../config/${ENVIRONMENT_NAME}/machine.${COMPUTER_NAME}"
	# Check Machine Directory Exists
	if [ ! -d $DIRECTORY ]; then 
		KM_MACHINE_NAME="machine.localhost"
	else
		KM_MACHINE_NAME="machine.${COMPUTER_NAME}"
	fi
	# Export Machine Name so we have it in create-container
	export KM_MACHINE_NAME
	if [ ERRORLEVEL = 1 ]; then 
		echo "ERROR: Unable to set-environment.sh"
		echo "bin/*.sh commands will fail without correcting this"
		echo "Error exporting the config and environment variables"
		endScriptFail
	else 
		echo "Setting environment to ${ENVIRONMENT_NAME}"
		echo "Setting machine to ${KM_MACHINE_NAME}"
		echo "Your config environment has been exported"
	fi 	
}

function setEnvironment () {
	# Path the em-appserver, export only KM_MW_HOME
	KM_MW_HOME="/app_2/verint/em/containers/em-appserver"
	KM_WLS_HOME="${KM_MW_HOME}/wlserver"
	export $KM_MW_HOME
	export $KM_WLS_HOME
	
	# Path to war files export KM_RELEASE_DIRECTORY
	KM_RELEASE_DIRECTORY="/app_2/verint/em/projects/uhgiq/restapi/release"
	export $KM_RELEASE_DIRECTORY
	
	# em-appserver logging directories
	EM_KM_BASE_LOG_PATH="/app_2/verint/em/logs"
	EM_SERVER_LOGS="${EM_KM_BASE_LOG_PATH}/${KM_DOMAIN}/weblogic/server.log"
	EM_ACCESS_LOGS="${EM_KM_BASE_LOG_PATH}/${KM_DOMAIN}/weblogic/http-access.log"
	EM_DIAGNOSTIC_LOGS="${EM_KM_BASE_LOG_PATH}/${KM_DOMAIN}/weblogic/diagnostic-images"
	export $EM_KM_BASE_LOG_PATH
	export $EM_SERVER_LOGS
	export $EM_ACCESS_LOGS
	export $EM_DIAGNOSTIC_LOGS
	
	# This is the java options the weblogic.WLST runs with
	JAVA_WLST_OPTIONS="-Dweblogic.security.IdentityKeyStore=CustomIdentity -Dweblogic.security.CustomIdentityKeyStoreFileName=${EM_IDENTITY_KEYSTORE} -Dweblogic.security.CustomIdentityKeyStorePassPhrase=${EM_IDENTITY_STORE_PASSPHRASE} -Dweblogic.security.Identity.KeyStoreType=JKS -Dweblogic.security.TrustKeyStore=CustomTrust -Dweblogic.security.CustomTrustKeyStoreFileName=${EM_TRUST_KEYSTORE} -Dweblogic.security.CustomTrustKeyStoreType=JKS -Dweblogic.security.CustomTrustKeyStorePassPhrase=${EM_TRUST_STORE_PASSPHRASE} -Dweblogic.security.IgnoreHostNameVerification=true -Dweblogic.security.SSL.ignoreHostnameVerification=true"
	export $JAVA_WLST_OPTIONS

	# container start up options
	KM_STARTUP_OPTIONS=" -Denvironment.name=${ENVIRONMENT_NAME} -DconfigLocation=/app_2/verint/em/projects/uhg/KMRestAPI/kmservices/config -Dmachine.name=${KM_MACHINE_NAME} -Dcontainer.name=${CONTAINER_NAME} -DlogFile=/app_2/verint/em/logs"
	ORIGINAL_JAVA_OPTIONS=$JAVA_OPTIONS

	export $KM_STARTUP_OPTIONS
	export $ORIGINAL_JAVA_OPTIONS
	if [ ERRORLEVEL = 1 ]; then 
		echo "Error in setEnvironment()"
		endScriptFail
	fi
	echo "Successfully exported the environment variables"	
}

function setEnvironmentProperties () {
	PROPERTY_FILE="../config/environment.${ENVIRONMENT_NAME}/environment.properties"
	echo "# Reading properties from $PROPERTY_FILE"
	
	EM_IDENTITY_KEYSTORE=$(getProperty "em.identity.keystore")
	EM_IDENTITY_STORE_PASSPHRASE=$(getProperty "em.identity.store.passphrase")
	EM_TRUST_KEYSTORE=$(getProperty "em.trust.keystore")
	EM_TRUST_STORE_PASSPHRASE=$(getProperty "em.trust.store.passphrase")
	KM_MEMORY_OPTS=$(getProperty "km.memory.opts")
	export $EM_IDENTITY_KEYSTORE
	export $EM_IDENTITY_STORE_PASSPHRASE
	export $EM_TRUST_KEYSTORE
	export $EM_TRUST_STORE_PASSPHRASE
	export $KM_MEMORY_OPTS
}

function setContainer () {
	KM_DOMAIN=$CONTAINER_NAME
	echo Container (domain) has been exported to [$KM_DOMAIN]
	echo Your container environment has been exported.	
	if [ ERRORLEVEL = 1 ]; then 
		echo "Error in setContainer()"
		endScriptFail
	fi	
}

function setContainerProperties () {
	PROPERTY_FILE="../config/environment.${ENVIRONMENT_NAME}/${KM_MACHINE_NAME}/container.${CONTAINER_NAME}/container.properties"
	echo "# Reading properties from $PROPERTY_FILE"
	
	KM_MANAGEMENT_USERNAME=$(getProperty "km.management.username")
	KM_MANAGEMENT_PASSWORD=$(getProperty "km.management.password")
	KM_CONNECT_URL=$(getProperty "km.connect.url")
	KM_EM_APPSERVER_PORT=$(getProperty "km.em.appserver.port")
	
	export $KM_MANAGEMENT_USERNAME
	export $KM_MANAGEMENT_PASSWORD
	export $KM_CONNECT_URL
	export $KM_EM_APPSERVER_PORT

}

function getProperty () {
	PROP_KEY=$1
	PROP_VALUE=`cat $PROPERTY_FILE | grep "$PROP_KEY" | cut -d'=' -f2`
	echo $PROP_VALUE
}

# =====================
# MAIN SCRIPT EXECUTION
# =====================

# Check input parameters
if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]; then
	blank
fi

# Assign Variables
export COMMAND_NAME=$1
export ENVIRONMENT_NAME=$2
export CONTAINER_NAME=$3

# Incase another script uses this variable directly
export KM_ENVIRONMENT_NAME=$ENVIRONMENT_NAME

# Set Configuration 
echo "---"
echo "Setting environment variables"
echo "Started running setConfig()"
setConfig
echo "Finished running setConfig()"
echo "---"
echo "Started running setEnvironment()"
setEnvironment
setEnvironmentProperties
echo "Finished running setEnvironment()"
echo "---"
echo "Started running setContainer()"
setContainer
setContainerProperties
echo "Finshed running setContainer()"
echo "---"
echo "Running command: ${COMMAND_NAME}"
(exec ../scripts/$COMMAND_NAME.sh)


