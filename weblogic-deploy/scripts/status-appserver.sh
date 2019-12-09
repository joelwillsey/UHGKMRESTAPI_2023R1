#!/bin/bash
set +v

echo
echo =============
echo Server status
echo ---------------------

if "%~1"="" goto BLANK

export CONTAINER_NAME=%1

export @FINISHED_MESSAGE=BUILD FAILED

# This are the status server codes
export SERVER_RUNNING=2
export SERVER_STOPPED=8
export SERVER_STARTING=4
export SERVER_UNKNOWN=16

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
if not exist ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME%/container.$CONTAINER_NAME/ GOTO ENVIRONMENT_ERROR
echo command is ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME%/container.$CONTAINER_NAME/export-container-environment.sh
../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME%/container.$CONTAINER_NAME/export-container-environment.sh
if $ERRORLEVEL = 1 GOTO ENVIRONMENT_ERROR

# This only needs to be run once per session
if not defined ENVIRONMENT_WSTL_export (
    echo exporting WLST environment variables
    $WLS_HOME/server/bin/exportWLSEnv.sh
    if $ERRORLEVEL = 1 GOTO WLST_ERROR
    export ENVIRONMENT_WSTL_export=true
) else (
    echo WLST environment variables already export
)

echo
echo Command is: java $JAVA_WLST_OPTIONS weblogic.WLST ../scripts/server-status.py
echo

java $JAVA_WLST_OPTIONS weblogic.WLST ../scripts/server-status.py

export SERVER_STATUS=$ERRORLEVEL

if $SERVER_STATUS = 1 GOTO ERROR

export @FINISHED_MESSAGE=COMMAND SUCCESSFUL

echo
if $SERVER_STATUS EQU $SERVER_RUNNING GOTO RUNNING
if $SERVER_STATUS EQU $SERVER_STARTING GOTO STARTING
if $SERVER_STATUS EQU $SERVER_STOPPED GOTO STOPPED
if $SERVER_STATUS EQU $SERVER_UNKNOWN GOTO UNKNOWN

GOTO DONE

:RUNNING
echo Server $KM_DOMAIN is RUNNING

GOTO DONE

:STARTING
echo Server $KM_DOMAIN is STARTING

GOTO DONE

:STOPPED
echo Server $KM_DOMAIN is STOPPED

GOTO DONE

:UNKNOWN
echo Server $KM_DOMAIN is UNKNOWN

GOTO DONE

:WLST_ERROR

echo Missing WLST environment exportup script - $WLS_HOME/server/bin/exportWLSEnv.sh

GOTO ERROR

:BLANK

echo Missing WebLogic container parameter [server-status.sh containerName]

GOTO ERROR

:CONFIG_ERROR
echo  Unable to export the config and environment variables

GOTO ERROR

:ENVIRONMENT_ERROR
echo $CONTAINER_NAME is not a valid container name or the export-container-environment.sh file is missing. Path: ../config/$KM_ENVIRONMENT_NAME/$KM_MACHINE_NAME%/container.$CONTAINER_NAME/export-container-environment.sh


:ERROR


export @FINISHED_MESSAGE=BUILD FAILED


:DONE

echo
echo $@FINISHED_MESSAGE
echo