#!/bin/bash

set +v

echo
echo ==========================
echo Create the KMRestAPI Container
echo ==========================
echo

function error () {
	echo "${1}" 1>&2
	echo "BUILD FAILED"
	exit 1
}

function success () {
	echo "COMMAND SUCCESSFUL"
	exit 0 
}

echo Configure Appserver $CONTAINER_NAME
echo command is $KM_WLS_HOME/common/bin/wlst.sh ../scripts/configure-general-settings.py
echo ---
(exec $KM_WLS_HOME/common/bin/wlst.sh ../scripts/configure-general-settings.py)
#(exec java $JAVA_WLST_OPTIONS weblogic.WLST ../scripts/configure-general-settings.py)

if [ ! "$?" = "0" ]; then 
	error "Unable to execute Python command!"
fi

success
