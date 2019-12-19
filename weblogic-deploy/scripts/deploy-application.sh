#!/bin/bash

set +v

echo
echo ==========================
echo Deploying war files
echo ==========================
echo

function error () {
	echo "${1}" 1>&2
	echo "BUILD FAILED"
	exit 1
}

function success () {
	echo "COMMAND SUCCESSFUL"
	exit 0 
}

echo Deploy Application $CONTAINER_NAME
echo command is $KM_WLS_HOME/common/bin/wlst.sh ../scripts/deploy-war-files.py
echo "KM Startup Options = " $KM_STARTUP_OPTIONS
echo "Java Options = " $JAVA_OPTIONS
echo "Original Java Options = " $ORIGINAL_JAVA_OPTIONS
echo "WLST Properties = " $WLST_PROPERTIES
echo ---
(exec $KM_WLS_HOME/common/bin/wlst.sh /app_2/verint/em/projects/restapi/${ENVIRONMENT_NAME}_rest/weblogic-deploy/scripts/deploy-war-files.py)

if [ ! "$?" = "0" ]; then 
	error "Unable to execute Python command!"
fi

success
