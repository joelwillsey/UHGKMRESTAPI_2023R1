@echo off
@REM *************************************************************************
@REM This script is used to set environment variables related to the container configuration.
@REM 
@REM 
@REM KM_MW_HOME      - The container name used in the diretory structure  container.[container.Name] also the part of the Weblogic domain name [server_[container.Name] ans the Weblogic doamin application
@REM 
@REM                  
@REM KM_RELEASE_DIRECTORY   - Path the the KMRestAPI war files to be deployed   
@REM
@REM KM_MANAGEMENT_USERNAME    - The admin server user name
@REM KM_MANAGEMENT_PASSWORD    - The admin server user name
@REM KM_CONNECT_URL         - The AdminServer url for the container.  Should be in the form of t3s://localhost:[unique port]  You can connectot the AdminServer at https://localhost:[unique port]/console
@REM KM_EM-APPSERVER_PORT   - The port the appserver will run on
@REM
@REM KM_STARTUP_OPTIONS     - The startup options used by the KMRestAPI war files for the when the start-app command is given  This is optional if set at the Environment level
@REM KM_MEMORY_OPTS         - The memory options used for the when the start-app command is given.  This is optional if set at the Environment level
@REM
@REM *************************************************************************

REM This is the container name
set KM_DOMAIN=km

REM EM-AppServer connection info
set KM_MANAGEMENT_USERNAME=kmappservermanager
set KM_MANAGEMENT_PASSWORD=kmappserver123
set KM_CONNECT_URL=t3s://localhost:10290
set KM_EM-APPSERVER_PORT=7380

REM container start up options (This is optional if set at the Environment level)
set KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=C:\em\projects\uhg\KMRestAPI\kmservices\config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=C:\app_2\verint\projects\uhgiq\restapi\logs
set KM_MEMORY_OPTS= -Xms1024m -Xmx1024m

echo Container (domain) is set to [%KM_DOMAIN%]
echo Your container environment has been set.