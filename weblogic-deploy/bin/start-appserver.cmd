
@echo off

echo.
echo ====================================================
echo Starting the appserver
echo ---------------------


set @FINISHED_MESSAGE=COMMAND FAILED

set startTime = %Time%
if "%~1"=="" goto BLANK

set CONTAINER_NAME=%1

REM Check if the environment varaibles have been set (only need to be run once per session)
if  not "%KM_CONFIG_ENVIRONMENT_SET%" == "true" (
    echo.
    echo Setting config environment variables
    echo command is ../config/set-config.cmd
    call ../config/set-config.cmd
    if %ERRORLEVEL% == 1 GOTO CONFIG_ERROR
)

REM Set the container environment variables every time
echo.
echo Setting container environment variables
echo.
if not exist ../config/%KM_ENVIRONMENT_NAME%/%KM_MACHINE_NAME%/container.%CONTAINER_NAME%/ GOTO ENVIRONMENT_ERROR
echo command is ../config/%KM_ENVIRONMENT_NAME%/%KM_MACHINE_NAME%/container.%CONTAINER_NAME%/set-container-environment.cmd
call ../config/%KM_ENVIRONMENT_NAME%/%KM_MACHINE_NAME%/container.%CONTAINER_NAME%/set-container-environment.cmd
if %ERRORLEVEL% == 1 GOTO ENVIRONMENT_ERROR

Rem Set to using ORIGINAL_JAVA_OPTIONS so that the JAVA_OPTIONS does not keep getting longer and longer after each re-run of the command
set JAVA_OPTIONS=%ORIGINAL_JAVA_OPTIONS% %KM_STARTUP_OPTIONS%
set USER_MEM_ARGS=%KM_MEMORY_OPTS%
set SERVER_NAME=%CONTAINER_NAME%

echo.
echo command is %KM_MW_HOME%/domains/server_%CONTAINER_NAME%/bin/startWebLogic.cmd
echo .

start %KM_MW_HOME%/domains/server_%CONTAINER_NAME%/bin/startWebLogic.cmd

set @FINISHED_MESSAGE=COMMAND SUCCESSFUL

GOTO DONE

:BLANK

echo Missing WebLogic container parameter [start-appserver.cmd containerName]
set @FINISHED_MESSAGE=COMMAND FAILED


GOTO ERROR

:CONFIG_ERROR
echo  Unable to set the config and environment variables

GOTO ERROR

:ENVIRONMENT_ERROR
echo %CONTAINER_NAME% is not a valid container name or the set-container-environment.cmd file is missing. Path: ../config/%KM_ENVIRONMENT_NAME%/%KM_MACHINE_NAME%/container.%CONTAINER_NAME%/set-container-environment.cmd


:ERROR
set @FINISHED_MESSAGE=BUILD FAILED

:DONE

echo.
echo %@FINISHED_MESSAGE%
echo.

