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

echo Creating container $CONTAINER_NAME
echo command is $KM_WLS_HOME/common/bin/wlst.sh ../scripts/create-container.py
echo ---

(exec $KM_WLS_HOME/common/bin/wlst.sh ../scripts/create-container.py)

if [ ! "$?" = "0" ]; then 
	error "Unable to execute Python command!"
fi

DIRECTORY="${KM_MW_HOME}/domains/server_${KM_DOMAIN}/servers/${KM_DOMAIN}"
if [ ! -d DIRECTORY ]; then
	mkdir $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN
	if [ ! "$?" = "0" ]; then
		error "Unable to create new domain directory"
	fi
	mkdir $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security
	if [ ! "$?" = "0" ]; then
		error "Unable to create new domain security directory"
	fi
    echo copying $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/AdminServer/security $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security
    echo
    cp --recursive --force $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/AdminServer/security $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security	
	if [ ! "$?" = "0" ]; then
		error "Unable to copy security folder to new server!"
	fi
fi

success