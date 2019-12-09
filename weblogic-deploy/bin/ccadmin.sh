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
		echo "---"
		echo "Running Set Environment"
		setEnvironment
		echo "Successfully exported the config and environment variables"
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
	
	EM_IDENTITY_KEYSTORE="/app_2/verint/em/products/agent-desktop_15.3-FP6_5.6.0/config/config-exports/environment/environment.abstract-local-dev/resources/local-dev-keystore.jks"
	EM_IDENTITY_STORE_PASSPHRASE="changeit"
	EM_TRUST_KEYSTORE="/app_2/verint/em/products/agent-desktop_15.3-FP6_5.6.0/config/config-exports/environment/environment.abstract-local-dev/resources/local-dev-keystore.jks"
	EM_TRUST_STORE_PASSPHRASE="changeit"
	export $EM_IDENTITY_KEYSTORE
	export $EM_IDENTITY_STORE_PASSPHRASE
	export $EM_TRUST_KEYSTORE
	export $EM_TRUST_STORE_PASSPHRASE

	# This is the java options the weblogic.WLST runs with
	JAVA_WLST_OPTIONS="-Dweblogic.security.IdentityKeyStore=CustomIdentity -Dweblogic.security.CustomIdentityKeyStoreFileName=${EM_IDENTITY_KEYSTORE} -Dweblogic.security.CustomIdentityKeyStorePassPhrase=${EM_IDENTITY_STORE_PASSPHRASE} -Dweblogic.security.Identity.KeyStoreType=JKS -Dweblogic.security.TrustKeyStore=CustomTrust -Dweblogic.security.CustomTrustKeyStoreFileName=${EM_TRUST_KEYSTORE} -Dweblogic.security.CustomTrustKeyStoreType=JKS -Dweblogic.security.CustomTrustKeyStorePassPhrase=${EM_TRUST_STORE_PASSPHRASE} -Dweblogic.security.IgnoreHostNameVerification=true -Dweblogic.security.SSL.ignoreHostnameVerification=true"
	export $JAVA_WLST_OPTIONS

	# container start up options
	KM_STARTUP_OPTIONS=" -Denvironment.name=local -DconfigLocation=/app_2/verint/em/projects/uhg/KMRestAPI/kmservices/config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=/app_2/verint/em/logs"
	KM_MEMORY_OPTS=" -Xms1024m -Xmx1024m"
	ORIGINAL_JAVA_OPTIONS=$JAVA_OPTIONS

	export $KM_STARTUP_OPTIONS
	export $KM_MEMORY_OPTS
	export $ORIGINAL_JAVA_OPTIONS
	if [ ERRORLEVEL = 1 ]; then 
		echo "Error in setEnvironment()"
		endScriptFail
	fi	
}

function setContainerEnvironment () {
	# EM-AppServer connection info
	KM_MANAGEMENT_USERNAME="kmappservermanager"
	KM_MANAGEMENT_PASSWORD="kmappserver123"
	KM_CONNECT_URL="t3s://localhost:10290"
	KM_EM_APPSERVER_PORT=8680
	export $KM_MANAGEMENT_USERNAME
	export $KM_MANAGEMENT_PASSWORD
	export $KM_CONNECT_URL
	export $KM_EM_APPSERVER_PORT

	# container start up options (This is optional if export at the Environment level)
	# export KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=C:\em\projects\uhg\KMRestAPI\kmservices\config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=C:\app_2\verint\projects\uhgiq\restapi\logs
	# export KM_MEMORY_OPTS= -Xms1024m -Xmx1024m
	KM_DOMAIN=$CONTAINER_NAME
	echo Container (domain) has been exported to [$KM_DOMAIN]
	echo Your container environment has been exported.	
	if [ ERRORLEVEL = 1 ]; then 
		echo "Error in setContainerEnvironment()"
		endScriptFail
	fi	
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
echo "Started running setContainerEnvironment()"
setContainerEnvironment
echo "Finshed running setContainerEnvironment()"
echo "---"
echo "Running command: ${COMMAND_NAME}"
(exec ../scripts/$COMMAND_NAME.sh)



