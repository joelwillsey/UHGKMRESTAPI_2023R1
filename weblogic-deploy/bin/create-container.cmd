
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

echo Creating contianer %CONTAINER_NAME%
echo command is %KM_WLS_HOME%/common/bin/wlst.cmd ../scripts/create-container.py
echo.

call %KM_WLS_HOME%/common/bin/wlst.cmd ../scripts/create-container.py

if %ERRORLEVEL% == 1 GOTO ERROR

if not exist %KM_MW_HOME%/domains/server_%KM_DOMAIN%/servers/%KM_DOMAIN% (
    mkdir %KM_MW_HOME%\domains\server_%KM_DOMAIN%\servers\%KM_DOMAIN%
    if %ERRORLEVEL% == 1 GOTO ERROR
    mkdir %KM_MW_HOME%\domains\server_%KM_DOMAIN%\servers\%KM_DOMAIN%\security
    if %ERRORLEVEL% == 1 GOTO ERROR
    echo.
    echo copying %KM_MW_HOME%\domains\server_%KM_DOMAIN%\servers\AdminServer\security %KM_MW_HOME%\domains\server_%KM_DOMAIN%\servers\%KM_DOMAIN%\security
    echo.
    xcopy /E /I /Y %KM_MW_HOME%\domains\server_%KM_DOMAIN%\servers\AdminServer\security %KM_MW_HOME%\domains\server_%KM_DOMAIN%\servers\%KM_DOMAIN%\security
    if %ERRORLEVEL% == 1 GOTO COPY_ERROR
)
set @FINISHED_MESSAGE=COMMAND SUCCESSFUL

GOTO DONE

:BLANK

echo Missing container name parameter [create-container.cmd containerName]

GOTO ERROR

:MKDIR_ERROR
echo Unable to create new server directory

GOTO ERROR

:COPY_ERROR
echo unable to copy security folder to new server

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