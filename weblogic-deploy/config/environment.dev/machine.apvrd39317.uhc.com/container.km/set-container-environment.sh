#!/bin/bash

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
KM_DOMAIN=km 
export $KM_DOMAIN

# EM-AppServer connection info
KM_MANAGEMENT_USERNAME=kmappservermanager
KM_MANAGEMENT_PASSWORD=kmappserver123
KM_CONNECT_URL=t3s://localhost:10290
KM_EM_APPSERVER_PORT=8680
export $KM_MANAGEMENT_USERNAME
export $KM_MANAGEMENT_PASSWORD
export $KM_CONNECT_URL
export $KM_EM_APPSERVER_PORT

# container start up options (This is optional if export at the Environment level)
# export KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=C:\em\projects\uhg\KMRestAPI\kmservices\config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=C:\app_2\verint\projects\uhgiq\restapi\logs
# export KM_MEMORY_OPTS= -Xms1024m -Xmx1024m

echo Container (domain) has been exported to [$KM_DOMAIN]
echo Your container environment has been exported.