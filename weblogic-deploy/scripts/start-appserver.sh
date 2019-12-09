#!/bin/bash
set +v

echo
echo ==========================
echo Starting the appserver
echo ---------------------


export @FINISHED_MESSAGE=COMMAND FAILED

export startTime = date
if "%~1"="" goto BLANK

export CONTAINER_NAME=%1

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

# export to using ORIGINAL_JAVA_OPTIONS so that the JAVA_OPTIONS does not keep getting longer and longer after each re-run of the command
export JAVA_OPTIONS=$ORIGINAL_JAVA_OPTIONS $KM_STARTUP_OPTIONS
export USER_MEM_ARGS=$KM_MEMORY_OPTS
export SERVER_NAME=$CONTAINER_NAME

echo
echo command is $KM_MW_HOME/domains/server_$CONTAINER_NAME/bin/startWebLogic.sh
echo .

start $KM_MW_HOME/domains/server_$CONTAINER_NAME/bin/startWebLogic.sh

export @FINISHED_MESSAGE=COMMAND SUCCESSFUL

GOTO DONE

:BLANK

echo Missing WebLogic container parameter [start-appserver.sh containerName]
export @FINISHED_MESSAGE=COMMAND FAILED


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

