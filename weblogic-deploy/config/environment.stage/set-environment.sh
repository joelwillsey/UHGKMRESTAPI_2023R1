set +v
# *************************************************************************
# This script is used to export environment variables related to the environment configuration.
# 
# 
# KM_MW_HOME      - The em-appserver home directory of your WebLogic installation. 
# KM_WLS_HOME     - The wlserver path for WLST calls
#                  
# KM_RELEASE_DIRECTORY   - Path the the KMRestAPI war files to be deployed   
#
# EM_KM_BASE_LOG_PATH    - Base path for the log directory
# EM_SERVER_LOGS         - The sub directory for server logs
# EM_ACCESS_LOGS         - The sub directory for access logs
# EM_DIAGNOSTIC_LOGS     - The sub directory for diagnostic logs
#
# EM_IDENTITY_KEYSTORE   - Path to the identity keystore
# EM_IDENTITY_STORE_PASSPHRASE - The pass phrase for the identity keystore
# EM_TRUST_KEYSTORE      - Path to the trust keystore
# EM_TRUST_STORE_PASSPHRASE  - The pass phrase for the trust keystore
#
# JAVA_WLST_OPTIONS      - Options used to run the jave command weblogic.WLST   This will specify all the keystores and such
#
# KM_STARTUP_OPTIONS     - The startup options used by the KMRestAPI war files for the when the start-app command is given  This can be overridden at the container level
# KM_MEMORY_OPTS         - The memory options used for the when the start-app command is given.  This can be overridden at the container level
#
# KM_CONFIG_ENVIRONMENT_export  - A flag to indicate that the export-config and export-environment properties have been export so no need to run them again for this session
# *************************************************************************

# Path the em-appserver, export only KM_MW_HOME
export KM_MW_HOME=/app_2/verint/em/containers/em-appserver
export KM_WLS_HOME=$KM_MW_HOME/wlserver

# Path to war files export KM_RELEASE_DIRECTORY
export KM_RELEASE_DIRECTORY=/app_2/verint/em/projects/uhgiq/restapi/release

# em-appserver logging directories
export EM_KM_BASE_LOG_PATH=/app_2/verint/em/logs
export EM_SERVER_LOGS=$EM_KM_BASE_LOG_PATH/$KM_DOMAIN/weblogic/server.log
export EM_ACCESS_LOGS=$EM_KM_BASE_LOG_PATH/$KM_DOMAIN/weblogic/http-access.log
export EM_DIAGNOSTIC_LOGS=$EM_KM_BASE_LOG_PATH/$KM_DOMAIN/weblogic/diagnostic-images

export EM_IDENTITY_KEYSTORE=/app_2/verint/em/products/agent-desktop_15.3-FP6_5.6.0/config/config-exports/environment/environment.abstract-local-dev/resources/local-dev-keystore.jks
export EM_IDENTITY_STORE_PASSPHRASE=changeit
export EM_TRUST_KEYSTORE=/app_2/verint/em/products/agent-desktop_15.3-FP6_5.6.0/config/config-exports/environment/environment.abstract-local-dev/resources/local-dev-keystore.jks
export EM_TRUST_STORE_PASSPHRASE=changeit

# This is the java options the weblogic.WLST runs with
export JAVA_WLST_OPTIONS=-Dweblogic.security.IdentityKeyStore=CustomIdentity -Dweblogic.security.CustomIdentityKeyStoreFileName=$EM_IDENTITY_KEYSTORE -Dweblogic.security.CustomIdentityKeyStorePassPhrase=$EM_IDENTITY_STORE_PASSPHRASE -Dweblogic.security.Identity.KeyStoreType=JKS -Dweblogic.security.TrustKeyStore=CustomTrust -Dweblogic.security.CustomTrustKeyStoreFileName=$EM_TRUST_KEYSTORE -Dweblogic.security.CustomTrustKeyStoreType=JKS -Dweblogic.security.CustomTrustKeyStorePassPhrase=$EM_TRUST_STORE_PASSPHRASE -Dweblogic.security.IgnoreHostNameVerification=true -Dweblogic.security.SSL.ignoreHostnameVerification=true

# container start up options
export KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=/app_2/verint/em/projects/uhg/KMRestAPI/kmservices/config -Dmachine.name=localhost -Dcontainer.name=stage -DlogFile=/app_2/verint/em/logs
export KM_MEMORY_OPTS= -Xms1024m -Xmx1024m

export ORIGINAL_JAVA_OPTIONS = $JAVA_OPTIONS
# export this variable as a check for the other scripts if the all config and environment variables have been export

if [ -z "$KM_ENVIRONMENT_NAME" ]
then
	export KM_CONFIG_ENVIRONMENT_export=false
else
	export KM_CONFIG_ENVIRONMENT_export=true
	echo export em-appserver path to $KM_MW_HOME
	echo export WSLT path to $KM_WLS_HOME
	echo export WSLT java options to $JAVA_WLST_OPTIONS
	echo Your environment $KM_ENVIRONMENT_NAME variables has been export.
fi 
