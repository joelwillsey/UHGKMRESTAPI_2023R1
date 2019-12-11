#!/bin/bash

set +v

echo
echo ==========================
echo Create the KMRestAPI Container
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
(exec $KM_MW_HOME/domains/server_$CONTAINER_NAME/bin/startWebLogic.sh -Dweblogic.management.username=kmappservermanager -Dweblogic.management.password=kmappserver123 & )

if [ ! "$?" = "0" ]; then 
	error "Unable to execute Python command!"
fi

success