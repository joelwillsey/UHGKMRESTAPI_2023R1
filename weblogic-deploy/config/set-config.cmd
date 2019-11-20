@echo off

@REM *************************************************************************
@REM This script is used to set environment variables related to the container configuration.
@REM 
@REM 
@REM KM_ENVIRONMENT_NAME    - This is the environemnt name used for the configuration i.e. /config/environment.local
@REM KM_MACHINE_NAME        - This is the machine name. The scripts grabs the hostname and looks for a corresponding configuration direcotry based on that name.
@REM                          if it's missing it sets the hostname to localhost
@REM
@REM  This script also call the set-environment.cmd from the appropiate directory based on the environment name                 
@REM
@REM *************************************************************************

set KM_ENVIRONMENT_NAME=environment.local



if not exist ..\config\%KM_ENVIRONMENT_NAME%\machine.%computername%\ (
    set KM_MACHINE_NAME=machine.localhost
) else (
    set set KM_MACHINE_NAME=machine.%computername%
)

echo Setting environment to %KM_ENVIRONMENT_NAME%
echo Setting machine to %KM_MACHINE_NAME%
echo Your config environment has been set.

echo.
echo command is ../config/%KM_ENVIRONMENT_NAME%/set-environment.cmd
echo.
call ../config/%KM_ENVIRONMENT_NAME%/set-environment.cmd
if %ERRORLEVEL% == 1 GOTO ENVIRONMENT_ERROR

set @FINISHED_MESSAGE=Successfully set the config and environment variables

GOTO DONE

:ENVIRONMENT_ERROR

echo ERROR: Unable to call set-environment.cmd
echo bin/*.cmd commands will fail with out correcting this

set @FINISHED_MESSAGE=Error setting the config and environment variables!!!!!!

echo.
echo %@FINISHED_MESSAGE%
echo.

REM Set the ERRORLEVEL so the caling script knows tis failed to set all the required variables
exit /b 1

:DONE

echo.
echo %@FINISHED_MESSAGE%
echo.
