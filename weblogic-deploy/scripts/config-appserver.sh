#!/bin/bash
set +v

echo
echo ==========================
echo Configuring the app server
echo ---------------------

if "%~1"="" goto BLANK

export CONTAINER_NAME=%1

export @FINISHED_MESSAGE=BUILD FAILED

# Check if the environment varaibles have been export (only need to be run once per session)
if  not "$KM_CONFIG_ENVIRONMENT_expor" = "true" (
    echo
    echo exporting config environment variables
    echo command is ../config/export-config.sh
    ../config/export-config.sh
    if $ERRORLEVEL = 1 GOTO CONFIG_ERROR
)

# export the container environment variables every time
echo
echo exporting container environment variables
echo
if not exist ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/ GOTO ENVIRONMENT_ERROR
echo command is ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/export-container-environment.sh
../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/export-container-environment.sh
if $ERRORLEVEL = 1 GOTO ENVIRONMENT_ERROR

# This only needs to be run once per session
if not defined ENVIRONMENT_WSTL_export (
    echo exporting wlst environment variables
    $KM_WLS_HOME/server/bin/exportWLSEnv.sh
    if $ERRORLEVEL = 1 GOTO WLST_ERROR
    export ENVIRONMENT_WSTL_export=true
) else (
    echo wlst environment variables already export
)

echo
echo Command is: java $JAVA_WLST_OPTIONS weblogic.WLST ../scripts/configure-general-exportings.py
echo

java $JAVA_WLST_OPTIONS weblogic.WLST ../scripts/configure-general-exportings.py

if $ERRORLEVEL = 1 GOTO ERROR

export @FINISHED_MESSAGE=COMMAND SUCCESSFUL

GOTO DONE

:WLST_ERROR

echo Missing wlst environment exportup script - $KM_WLS_HOME/server/bin/exportWLSEnv.sh

GOTO ERROR

:BLANK

echo Missing WebLogic container parameter [stop-appserver.sh containerName]

GOTO ERROR

:CONFIG_ERROR
echo  Unable to export the config and environment variables

GOTO ERROR

:ENVIRONMENT_ERROR
echo $CONTAINER_NAME is not a valid container name or the export-container-environment.sh file is missing. Path: ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME/container.$CONTAINER_NAME/export-container-environment.sh

:ERROR

export @FINISHED_MESSAGE=BUILD FAILED

:DONE

echo
echo $@FINISHED_MESSAGE
echo