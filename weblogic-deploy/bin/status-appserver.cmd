
@echo off

echo.
echo ====================================================
echo Server status
echo ---------------------

if "%~1"=="" goto BLANK

set CONTAINER_NAME=%1

set @FINISHED_MESSAGE=BUILD FAILED

REM This are the status server codes
set SERVER_RUNNING=2
set SERVER_STOPPED=8
set SERVER_STARTING=4
set SERVER_UNKNOWN=16

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

REM This only needs to be run once per session
if not defined ENVIRONMENT_WSTL_SET (
    echo Setting WLST environment variables
    call %WLS_HOME%/server/bin/setWLSEnv.cmd
    if %ERRORLEVEL% == 1 GOTO WLST_ERROR
    set ENVIRONMENT_WSTL_SET=true
) else (
    echo WLST environment variables already set
)

echo.
echo Command is: java %JAVA_WLST_OPTIONS% weblogic.WLST ..\scripts\server-status.py
echo.

java %JAVA_WLST_OPTIONS% weblogic.WLST ..\scripts\server-status.py

set SERVER_STATUS=%ERRORLEVEL%

if %SERVER_STATUS% == 1 GOTO ERROR

set @FINISHED_MESSAGE=COMMAND SUCCESSFUL

echo.
if %SERVER_STATUS% EQU %SERVER_RUNNING% GOTO RUNNING
if %SERVER_STATUS% EQU %SERVER_STARTING% GOTO STARTING
if %SERVER_STATUS% EQU %SERVER_STOPPED% GOTO STOPPED
if %SERVER_STATUS% EQU %SERVER_UNKNOWN% GOTO UNKNOWN

GOTO DONE

:RUNNING
echo Server %KM_DOMAIN% is RUNNING

GOTO DONE

:STARTING
echo Server %KM_DOMAIN% is STARTING

GOTO DONE

:STOPPED
echo Server %KM_DOMAIN% is STOPPED

GOTO DONE

:UNKNOWN
echo Server %KM_DOMAIN% is UNKNOWN

GOTO DONE

:WLST_ERROR

echo Missing WLST environment setup script - %WLS_HOME%/server/bin/setWLSEnv.cmd

GOTO ERROR

:BLANK

echo Missing WebLogic container parameter [server-status.cmd containerName]

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