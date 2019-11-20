
@echo off

echo.
echo ====================================================
echo Create the KMRestAPI Container
echo ---------------------

if "%~1"=="" goto BLANK

set CONTAINER_NAME=%1

set @FINISHED_MESSAGE=BUILD FAILED

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
    call %KM_WLS_HOME%/server/bin/setWLSEnv.cmd
    if %ERRORLEVEL% == 1 GOTO WLST_ERROR
    set ENVIRONMENT_WSTL_SET=true
) else (
    echo WLST environment variables already set
)

echo.
echo Command is: java %JAVA_WLST_OPTIONS% weblogic.WLST ..\scripts\shutdown.py
echo.

java %JAVA_WLST_OPTIONS% weblogic.WLST ..\scripts\shutdown.py

if exist %KM_MW_HOME%/domains/server_%KM_DOMAIN%/ (
    echo.
    echo Deleting directory %KM_MW_HOME%\domains\server_%KM_DOMAIN%
    echo.

    RD /S /Q %KM_MW_HOME%\domains\server_%KM_DOMAIN%
    if %ERRORLEVEL% == 1 GOTO RD_ERROR
)
set @FINISHED_MESSAGE=COMMAND SUCCESSFUL

GOTO DONE

:BLANK

echo Missing container name parameter [create-container.cmd containerName]

GOTO ERROR

:MKDIR_ERROR
echo Unable to create new server directory

GOTO ERROR

:RD_ERROR
echo unable to remove directory %KM_MW_HOME%\domains\server_%KM_DOMAIN%

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