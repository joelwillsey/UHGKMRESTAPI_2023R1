from java.io import File
from weblogic.deploy.api.spi import DeploymentOptions
import time as pythontime
import os
managementUsername = os.environ.get('KM_MANAGEMENT_USERNAME')
managementPassword = os.environ.get('KM_MANAGEMENT_PASSWORD')
domain = os.environ.get('KM_DOMAIN')
connectUrl = os.environ.get('KM_CONNECT_URL')
warPath = os.environ.get('KM_RELEASE_DIRECTORY')
#configLocation = os.environ.get ('configLocation')
#environment.name = os.environ.get ('ENVIRONMENT_NAME')

def ensureValidJavaVersion():
    print 'Checking Java version'
    requiredJavaVersion='1.8.0_201'
    requiredMajorVersion = int(requiredJavaVersion.split('.')[1])
    requiredUpdateVersion = int(requiredJavaVersion.split('_')[1])

    weblogicJavaVersion = System.getProperty('java.version')
    if weblogicJavaVersion.endswith('-ea'):
        message = 'Java version %s currently used by the application server is an unsupported early access version. Please uninstall the application server and reinstall using Java %d and update version %d.' % (weblogicJavaVersion, requiredMajorVersion, requiredUpdateVersion)
        print message
        raise message

    actualUpdateVersion = int(weblogicJavaVersion.split('_')[1])
    actualMajorVersion = int(weblogicJavaVersion.split('.')[1])

    #if actualUpdateVersion != requiredUpdateVersion or actualMajorVersion != requiredMajorVersion:
    if actualMajorVersion != requiredMajorVersion:        
        message = 'Java version %s currently used by the application server does not match the version referenced by ccadmin. Please uninstall the application server and reinstall using Java %d and update version %d.' % (weblogicJavaVersion, requiredMajorVersion, requiredUpdateVersion)
        print message
        raise message

#ensureValidJavaVersion()

def deployApplication(filename):
    try:
        appname = filename[:filename.find('.')]
        targetFile = warPath + '/' + filename

        app = domain.lookupAppDeployment(appname)
        if app is not None:
            print "Undeploying existing application"
            stopApplication(appname)
            undeploy(appname)

        deploymentManager = getWLDM()
        defaultTargets = deploymentManager.getTargets()
        options = DeploymentOptions()
        options.setName(appname)

        print "Deploying: %s" % appname
        progress = deploymentManager.distribute(defaultTargets, File(targetFile), None, options)
        # progress.getDeploymentStatus copies the status, we must refresh this each loop
        while not progress.getDeploymentStatus().isCompleted():
            pythontime.sleep(0.2)
        print "Deploy complete"

        print "Starting: %s" % appname
        startApplication(appname)

        print "Start complete"
    except:
		print 'Error during the deployment or starting of application' +appname+ '\n'
		


connect(managementUsername, managementPassword, connectUrl)

cd('/')
domain = cmo

deployApplication('verintkm.war')

deployApplication('searchservices.war')

deployApplication('filterservices.war')

deployApplication('contentservices.war')



disconnect()