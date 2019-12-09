#!/bin/bash

set +v

echo
echo ==========================
echo Create the KMRestAPI Container
echo ==========================
echo

function blank () {
	echo Missing container name parameter [create-container.sh containerName]
	error
}

function mkdir_error () {
	echo Unable to create new server directory
	error
}

function copy_error () {
	echo Unable to copy security folder to new server
	error
}

function config_error () {
	echo Unable to export the config and environment variables
	error
}

function environment_error () {
	echo $CONTAINER_NAME is not a valid container name or the export-container-environment.sh file is missing. Path: ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/export-container-environment.sh
	error
}

function error () {
	FINISHED_MESSAGE="BUILD FAILED"
	done
}

function done () {
	echo $FINISHED_MESSAGE
	exit
}

if [ $1 = "" ]; then
	blank
fi

# Variable Assignments
CONTAINER_NAME=$1
FINISHED_MESSAGE="BUILD FAILED"

# Check if the environment varaibles have been exported (only need to be run once per session)
if [ $KM_CONFIG_ENVIRONMENT_expor != "true" ]; then
    echo exporting config environment variables
	echo Running script ../config/export-config.sh
	(exec ../config/export-config.sh)
	if [ ERRORLEVEL = 1 ]; then
		config_error
	fi
fi	
# export the container environment variables every time
echo
echo exporting container environment variables
echo

DIRECTORY = ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/
if [ ! -d DIRECTORY ]; then
	environment_error
	else
	echo Running script ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/export-container-environment.sh
	(exec ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/export-container-environment.sh)
	if [ ERRORLEVEL = 1 ]; then
		environment_error
	fi
fi	
echo Creating contianer $CONTAINER_NAME
echo command is $KM_WLS_HOME/common/bin/wlst.sh ../scripts/create-container.py
echo

(exec $KM_WLS_HOME/common/bin/wlst.sh ../scripts/create-container.py)

if [ ERRORLEVEL = 1 ]; then 
	error
fi

DIRECTORY = $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN
if [ ! -d DIRECTORY ]; then
	mkdir $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN
	if [ ERRORLEVEL = 1 ]; then
		error
	fi
	mkdir $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security
	if [ ERRORLEVEL = 1 ]; then
		error
	fi
    echo copying $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/AdminServer/security $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security
    echo
    cp --recursive --force $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/AdminServer/security $KM_MW_HOME/domains/server_$KM_DOMAIN/servers/$KM_DOMAIN/security	
	if [ ERRORLEVEL = 1 ]; then
		error
	fi
fi

# HAPPY PATH
FINISHED_MESSAGE="COMMAND SUCCESSFUL"
done	