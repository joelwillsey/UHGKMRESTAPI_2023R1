#!/bin/bash

set +v

echo
echo ==========================
echo Destroy the KMRestAPI Container
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

echo Shutting down and destroying container $CONTAINER_NAME
echo command is $KM_WLS_HOME/common/bin/wlst.sh ../scripts/shutdown.py
echo ---

(exec $KM_WLS_HOME/common/bin/wlst.sh ../scripts/shutdown.py)

if [ ! "$?" = "0" ]; then 
	error "Unable to execute Python command!"
fi

DIRECTORY="${KM_MW_HOME}/domains/server_${KM_DOMAIN}/servers/${KM_DOMAIN}"
if [ -d DIRECTORY ]; then
	rm -rf $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN
	if [ ! "$?" = "0" ]; then
		error "Unable to delete domain directory"
	fi
fi

success