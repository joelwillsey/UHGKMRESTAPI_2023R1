#!/bin/bash

set +v

echo
echo ==========================
echo Create the KMRestAPI Container
echo ==========================
echo


function mkdir_error () {
	echo Unable to create new server directory
	error
}

function copy_error () {
	echo Unable to copy security folder to new server
	error
}

function error () {
	FINISHED_MESSAGE="BUILD FAILED"
	end
}

function end () {
	echo $FINISHED_MESSAGE
	exit
}

# Variable Assignments
FINISHED_MESSAGE="BUILD FAILED"
	
echo Creating contianer $CONTAINER_NAME
echo command is $KM_WLS_HOME/common/bin/wlst.sh ../scripts/create-container.py
echo ---

(exec $KM_WLS_HOME/common/bin/wlst.sh ../scripts/create-container.py)

if [ ! "$?" = "0" ]; then 
	error
fi

DIRECTORY="${KM_MW_HOME}/domains/server_${KM_DOMAIN}/servers/${KM_DOMAIN}"
if [ ! -d DIRECTORY ]; then
	mkdir $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN
	if [ ! "$?" = "0" ]; then
		mkdir_error
	fi
	mkdir $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security
	if [ ! "$?" = "0" ]; then
		mkdir_error
	fi
    echo copying $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/AdminServer/security $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security
    echo
    cp --recursive --force $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/AdminServer/security $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security	
	if [ ! "$?" = "0" ]; then
		copy_error
	fi
fi

# HAPPY PATH
FINISHED_MESSAGE="COMMAND SUCCESSFUL"
end	