@echo off
@REM *************************************************************************
@REM This script is used to set environment variables related to the environment configuration.
@REM 
@REM 
@REM KM_MW_HOME      - The em-appserver home directory of your WebLogic installation. 
@REM KM_WLS_HOME     - The wlserver path for WLST calls
@REM                  
@REM KM_RELEASE_DIRECTORY   - Path the the KMRestAPI war files to be deployed   
@REM
@REM EM_KM_BASE_LOG_PATH    - Base path for the log directory
@REM EM_SERVER_LOGS         - The sub directory for server logs
@REM EM_ACCESS_LOGS         - The sub directory for access logs
@REM EM_DIAGNOSTIC_LOGS     - The sub directory for diagnostic logs
@REM
@REM EM_IDENTITY_KEYSTORE   - Path to the identity keystore
@REM EM_IDENTITY_STORE_PASSPHRASE - The pass phrase for the identity keystore
@REM EM_TRUST_KEYSTORE      - Path to the trust keystore
@REM EM_TRUST_STORE_PASSPHRASE  - The pass phrase for the trust keystore
@REM
@REM JAVA_WLST_OPTIONS      - Options used to run the jave command weblogic.WLST   This will specify all the keystores and such
@REM
@REM KM_STARTUP_OPTIONS     - The startup options used by the KMRestAPI war files for the when the start-app command is given  This can be overridden at the container level
@REM KM_MEMORY_OPTS         - The memory options used for the when the start-app command is given.  This can be overridden at the container level
@REM
@REM KM_CONFIG_ENVIRONMENT_SET  - A flag to indicate that the set-config and set-environment properties have been set so no need to run them again for this session
@REM *************************************************************************

REM Path the em-appserver, set only KM_MW_HOME
set KM_MW_HOME=C:\em\containers\em-appserver
set KM_WLS_HOME=%KM_MW_HOME%\wlserver

REM Path to war files set KM_RELEASE_DIRECTORY
set KM_RELEASE_DIRECTORY=C:\app_2\verint\projects\uhgiq\restapi\release

REM em-appserver logging directories
set EM_KM_BASE_LOG_PATH=C:\app_2\verint\projects\uhgiq\restapi\logs
set EM_SERVER_LOGS=%EM_KM_BASE_LOG_PATH%\%KM_DOMAIN%\weblogic\server.log
set EM_ACCESS_LOGS=%EM_KM_BASE_LOG_PATH%\%KM_DOMAIN%\weblogic\http-access.log
set EM_DIAGNOSTIC_LOGS=%EM_KM_BASE_LOG_PATH%\%KM_DOMAIN%\weblogic\diagnostic-images

set EM_IDENTITY_KEYSTORE=C:\em\products\agent-desktop_15.3-FP6_5.6.0\config\config-sets\environment\environment.abstract-local-dev\resources\local-dev-keystore.jks
set EM_IDENTITY_STORE_PASSPHRASE=changeit
set EM_TRUST_KEYSTORE=C:\em\\products\agent-desktop_15.3-FP6_5.6.0\config\config-sets\environment\environment.abstract-local-dev\resources\local-dev-keystore.jks
set EM_TRUST_STORE_PASSPHRASE=changeit

REM This is the java options the weblogic.WLST runs with
set JAVA_WLST_OPTIONS=-Dweblogic.security.IdentityKeyStore=CustomIdentity -Dweblogic.security.CustomIdentityKeyStoreFileName=%EM_IDENTITY_KEYSTORE% -Dweblogic.security.CustomIdentityKeyStorePassPhrase=%EM_IDENTITY_STORE_PASSPHRASE% -Dweblogic.security.Identity.KeyStoreType=JKS -Dweblogic.security.TrustKeyStore=CustomTrust -Dweblogic.security.CustomTrustKeyStoreFileName=%EM_TRUST_KEYSTORE% -Dweblogic.security.CustomTrustKeyStoreType=JKS -Dweblogic.security.CustomTrustKeyStorePassPhrase=%EM_TRUST_STORE_PASSPHRASE% -Dweblogic.security.IgnoreHostNameVerification=true -Dweblogic.security.SSL.ignoreHostnameVerification=true

REM container start up options
set KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=C:\em\projects\uhg\KMRestAPI\kmservices\config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=C:\app_2\verint\projects\uhgiq\restapi\logs
set KM_MEMORY_OPTS= -Xms1024m -Xmx1024m

set ORIGINAL_JAVA_OPTIONS = %JAVA_OPTIONS%
REM set this variable as a check for the other scripts if the all config and environment variables have been set
if defined KM_ENVIRONMENT_NAME set KM_CONFIG_ENVIRONMENT_SET=true

echo Set em-appserver path to %KM_MW_HOME%
echo Set WSLT path to %KM_WLS_HOME%
echo Set WSLT java options to %JAVA_WLST_OPTIONS%
echo Your environment [%KM_ENVIRONMENT_NAME%] variables has been set.
