#!/bin/bash

set +v

echo
echo ==========================
echo Starting Appserver
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

echo Start Appserver $CONTAINER_NAME
echo command is $KM_MW_HOME/domains/server_$CONTAINER_NAME/bin/startWebLogic.sh
echo ---
(exec $KM_MW_HOME/domains/server_$CONTAINER_NAME/bin/startWebLogic.sh -Dweblogic.Name=$CONTAINER_NAME -Dweblogic.management.username=kmappservermanager -Dweblogic.management.password=kmappserver123 -Dweblogic.security.SSL.ignoreHostnameVerification=true $KM_MEMORY_OPTS & )

if [ ! "$?" = "0" ]; then 
	error "Unable to execute Python command!"
fi

success
