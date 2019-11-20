import os
import sys


managementUsername = os.environ.get('KM_MANAGEMENT_USERNAME')
managementPassword = os.environ.get('KM_MANAGEMENT_PASSWORD')
domain = os.environ.get('KM_DOMAIN')
connectUrl = os.environ.get('KM_CONNECT_URL')

try:
    connect(managementUsername,managementPassword,connectUrl)

except:
    status = "STOPPED"
    errorlevel=8
    exit()

# set CMO to the server  
domainRuntime() 
cd("/ServerLifeCycleRuntimes/" + domain)
status = cmo.getState()
print "Server " + domain+ ": " + status

errorlevel=16
if status == "RUNNING": errorlevel=2
elif status == "STARTING": errorlevel=4
elif status == "UNKNOWN": errorlevel=16

disconnect()

sys.exit(errorlevel)
    
