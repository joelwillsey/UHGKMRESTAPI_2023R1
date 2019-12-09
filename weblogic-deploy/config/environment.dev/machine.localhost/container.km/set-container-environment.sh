set +v
# *************************************************************************
# This script is used to export environment variables related to the container configuration.
# 
# 
# KM_MW_HOME      - The container name used in the diretory structure  container.[container.Name] also the part of the Weblogic domain name [server_[container.Name] ans the Weblogic doamin application
# 
#                  
# KM_RELEASE_DIRECTORY   - Path the the KMRestAPI war files to be deployed   
#
# KM_MANAGEMENT_USERNAME    - The admin server user name
# KM_MANAGEMENT_PASSWORD    - The admin server user name
# KM_CONNECT_URL         - The AdminServer url for the container.  Should be in the form of t3s://localhost:[unique port]  You can connectot the AdminServer at https://localhost:[unique port]/console
# KM_EM-APPSERVER_PORT   - The port the appserver will run on
#
# KM_STARTUP_OPTIONS     - The startup options used by the KMRestAPI war files for the when the start-app command is given  This is optional if export at the Environment level
# KM_MEMORY_OPTS         - The memory options used for the when the start-app command is given.  This is optional if export at the Environment level
#
# *************************************************************************

# This is the container name
export KM_DOMAIN=km

# EM-AppServer connection info
export KM_MANAGEMENT_USERNAME=kmappservermanager
export KM_MANAGEMENT_PASSWORD=kmappserver123
export KM_CONNECT_URL=t3s://localhost:10290
export KM_EM-APPSERVER_PORT=7380

# container start up options (This is optional if export at the Environment level)
# export KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=C:\em\projects\uhg\KMRestAPI\kmservices\config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=C:\app_2\verint\projects\uhgiq\restapi\logs
# export KM_MEMORY_OPTS= -Xms1024m -Xmx1024m

echo Container (domain) is export to [$KM_DOMAIN]
echo Your container environment has been export.