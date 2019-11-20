

Command List

Every command requires you pass in the container container name.

-config-appserver [containerName]
-create-container [containerName]
-deploy-application [containerName]
-destroy-container [containerName]
-start-appserver [containerName]
-status-appserver [containerName]
-stop-appserver [containerName]


Setting up the environment

Edit the config/set-config.cmd file and set your environment name here
set KM_ENVIRONMENT_NAME=environment.local

Then edit the config/environment.[name]/set-environment.cmd 
set KM_MW_HOME=C:\em\containers\em-appserver
set EM_KM_BASE_LOG_PATH=C:\app_2\verint\projects\uhgiq\restapi\logs
set KM_RELEASE_DIRECTORY=C:\app_2\verint\projects\uhgiq\restapi\release


Note please copy over the war files to the directory specified by KM_RELEASE_DIRECTORY
verintkm.war, searchservices.war, filterservices.war, contentservices.war


Make sure these path out to your product directory
set EM_IDENTITY_KEYSTORE=C:\em\products\agent-desktop_15.3-FP6_5.6.0\config\config-sets\environment\environment.abstract-local-dev\resources\local-dev-keystore.jks
set EM_TRUST_KEYSTORE=C:\em\\products\agent-desktop_15.3-FP6_5.6.0\config\config-sets\environment\environment.abstract-local-dev\resources\local-dev-keystore.jks


Then edit the config/environment.[name]/container.[name]/set-conteiner-environment.cmd

Set this to the container name
set KM_DOMAIN=km

Make sure this matches up the your environment
set KM_STARTUP_OPTIONS= -Denvironment.name=local -DconfigLocation=C:\em\projects\uhg\KMRestAPI\kmservices\config -Dmachine.name=localhost -Dcontainer.name=dev -DlogFile=C:\app_2\verint\projects\uhgiq\restapi\logs



Setup of a new environment

1.  Edit set-config in the KMRestAPI_Weblogic/config
2.  Edit set-environment in KMRestAPI_Weblogic/config/environment.[name]
3.  Edit set-container-environment in KMRestAPI_Weblogic/config/environment.[name]/container.[name]
4.  create-container [containerName]
5.  start-appserver [containerName]
6.  config-appserver [containerName]
7.  stop-appserver [containerName]
8.  start-appserver [containerName]
9.  deploy-application [containerName]


Starting up an already configured environment
1.  start-appserver [containerName]

Stopping an already configured environment
1.  stop-appserver [containerName]

Status of weblogic server
1.  status-appserver [containerName]

Restrarting an app server
1.  stop-appserver [containerName]
2.  start-appserver [containerName]

Recreate environment
1.  destroy-container [containerName]    (Note in windows you will have to manually close the appserver commnad window)  If you forget just re-run command after closing it
2.  create-container [containerName]
3.  start-appserver [containerName]
4.  config-appserver [containerName]
5.  stop-appserver [containerName]
6.  start-appserver [containerName]
7.  deploy-application [containerName]


Overview of environment variable scripts

Every command firrt task is to check if the environment variable KM_CONFIG_ENVIRONMENT_SET=True  If it is not true it call the set-config script which then calls the set-environmnet script which in 
turn sets the KM_CONFIG_ENVIRONMENT_SET=True.  This is done so the environment variables are only run once not keep appending on to paths.

Every command always run the set-container-environment script every time it's run.  This is dertermined by the [containerName] passed in.

Commands Description


-   config-appserver [containerName]

    The config-appserver commands set the logging path for the appserver.  The app server needs running and then restarted after running the command for the log paths to take effect. This 
    command runs the WSLT KMRestAPI_Weblogic/scripts/configure-general-settings.py sript  This command needs to run just once after creating the container and starting the app server.

-  create-container [containerName]

    This command creates the directory structure for a new domain for the weblogic server.  It also copies over the boot.properties file to the directory structure so the that
    the application server will start without having to enter a user/password on startup.


-   deploy-application [containerName]

    This command deploys the war files need for the KMRestAPI. First it stops any applications, then un-deploys them and them re-deploys war files and starts them up. 
    It runs the KMRestAPI_Weblogic/scripts/deploy-war-files.py WSLT script.  The app server needs to be running for this command to work.  You should be able to access the application after running this command.  
    This command only needs to be run once,  upon app server restarting the application will still be deployed.

-   destroy-container [containerName]

    This commands deletes the driectory structure of the domain on the Weblogic server.  It first tries to run the KMRestAPI_Weblogic/scripts/shutdown.py WSLT script before deleting the directory.  In windows
    you will have to close the command window running the app server otherwise the processes are locked and the directory can not be deleted.

-   start-appserver [containerName]

    This command start the application server. If the config-appserver and deploy-appserver commands have been run once the application should be ready.  This command works by 
    running the startweblogic script in the em-appserver/domains/domainName/bin/startweblogic.cmd

-   status-appserver [containerName]

    This command  connects to the appserver and returns the app servers status.  If it's unable to connect it throws an ERRORLEVEL code.
    It runs the KMRestAPI_Weblogic/scripts/dserver-status.py WSLT script.

-   stop-appserver [containerName]

    This command stops the app server by running the KMRestAPI_Weblogic/scripts/shutdown.py WSLT script

