export +v

# *************************************************************************
# This script is used to export environment variables related to the container configuration.
# 
# 
# KM_ENVIRONMENT_NAME    - This is the environment name used for the configuration i.e. /config/environment.local
# KM_MACHINE_NAME        - This is the machine name. The scripts grabs the hostname and looks for a corresponding configuration direcotry based on that name.
#                          if it's missing it exports the hostname to localhost
#
#  This script also the set-environment.sh from the appropiate directory based on the environment name                 
#
# *************************************************************************

computername=$(hostname -f)


if [ ! -f ../config/$KM_ENVIRONMENT_NAME/machine.$computername]; then (
    export KM_MACHINE_NAME=machine.localhost
) else (
    export KM_MACHINE_NAME=machine.$computername
)
if [ $ERRORLEVEL = 1]; then (
echo "ERROR: Unable to set-environment.sh"
echo "bin/*.sh commands will fail without correcting this"
@FINISHED_MESSAGE=Error exporting the config and environment variables
export @FINISHED_MESSAGE
echo $@FINISHED_MESSAGE 
) else (
echo exporting environment to $KM_ENVIRONMENT_NAME
echo exporting machine to $KM_MACHINE_NAME
echo Your config environment has been exported
echo
echo command is ../config/$KM_ENVIRONMENT_NAME/set-environment.sh
echo
../config/$KM_ENVIRONMENT_NAME/set-environment.sh
@FINISHED_MESSAGE=Successfully export the config and environment variables
export @FINISHED_MESSAGE
echo
echo $@FINISHED_MESSAGE
)
fi 