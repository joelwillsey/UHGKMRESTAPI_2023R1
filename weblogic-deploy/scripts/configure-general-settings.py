

import os

def configureBasicAuthCredentials():
    cd('/SecurityConfiguration/server_'+domain)
    print 'Setting appserver property EnforceValidBasicAuthCredentials to false'
    set('EnforceValidBasicAuthCredentials','false')

def configureServerLog():
    print "Configuring server log"
    cd ('/Servers/'+domain+'/Log/'+domain)
    set('DateFormatPattern', 'MMM d, yyyy HH:mm:ss,S z')
    set('FileName', serverLogPath)
    set('RotationType', 'byTime')
    set('FileCount', 5)
    listOfLogFileSeverityLevels = ['TRACE' , 'DEBUG', 'INFO', 'NOTICE', 'WARNING']
    severityLevel = 'DEBUG'
    if severityLevel.upper() not in listOfLogFileSeverityLevels:
        set('LogFileSeverity', 'WARNING')
    else:
        set('LogFileSeverity', severityLevel)
    set('StdoutSeverity', 'Off')
    set('DomainLogBroadcastSeverity', 'Off')
    set('RedirectStdoutToServerLogEnabled', true)
    set('RedirectStderrToServerLogEnabled', true)

def configureDiagnosticLog():
    print "Configuring diagnostic log"
    cd('/Servers/'+domain+'/ServerDiagnosticConfig/'+domain)
    set('ImageDir', diagnosticLogPath)

def configureAccessLog():
    print "Configuring HTTP access log"
    if false:
        print "HTTP access log is enabled"
    else:
        print "HTTP access log is disabled"

    cd('/Servers/'+domain+'/WebServer/'+domain+'/WebServerLog/'+domain)
    set('FileName', accessLogPath)

    set('LoggingEnabled', false)
    set('RotationType', 'byTime')
    set('FileCount', 5)
    accessLogFormatType = 'common'
    set('LogFileFormat', accessLogFormatType)
    if (accessLogFormatType == 'extended'):
        set('ELFFields', '')




managementUsername = os.environ.get('KM_MANAGEMENT_USERNAME')
managementPassword = os.environ.get('KM_MANAGEMENT_PASSWORD')
domain = os.environ.get('KM_DOMAIN')
connectUrl = os.environ.get('KM_CONNECT_URL')
serverLogPath = os.environ.get('EM_SERVER_LOGS')
accessLogPath = os.environ.get('EM_ACCESS_LOGS')
diagnosticLogPath = os.environ.get('EM_DIAGNOSTIC_LOGS')

connect(managementUsername,managementPassword,connectUrl)
edit()
startEdit()
undo(unactivatedChanges = "true", defaultAnswer = "y")

configureBasicAuthCredentials()
configureServerLog()
configureDiagnosticLog()
configureAccessLog()

save()
activate(block='true')
disconnect()

