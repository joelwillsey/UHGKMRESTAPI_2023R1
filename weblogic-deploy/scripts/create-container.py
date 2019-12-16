import os
managementUsername = os.environ.get('KM_MANAGEMENT_USERNAME')
managementPassword = os.environ.get('KM_MANAGEMENT_PASSWORD')
domain = os.environ.get('KM_DOMAIN')
connectUrl = os.environ.get('KM_CONNECT_URL')
adminPort  = int(connectUrl[(connectUrl.rfind(':') + 1):])
print "AdminPort=" + str(adminPort)
kmPort = int(os.environ.get('KM_EM_APPSERVER_PORT'))
print "kmPort=" + str(kmPort)
emAppServerPath = os.environ.get('KM_MW_HOME')
identityKeystorePath = os.environ.get('EM_IDENTITY_KEYSTORE')
identityKeystorePassphrase = os.environ.get('EM_IDENTITY_STORE_PASSPHRASE')
trustKeystorePath = os.environ.get('EM_TRUST_KEYSTORE')
trustKeystorePassphrase = os.environ.get('EM_TRUST_STORE_PASSPHRASE')
privateKeyPassphrase = 'changeit'
httpsEnabled = true

# WebLogic does not allow the http and https port to be the same.
# Only one of the ports is enabled so changing the port here is okay
if httpsEnabled:
    print "Enabling https configuration only"
    httpPort = kmPort + 1
    httpsPort = kmPort
else:
    print "Enabling http configuration only"
    httpPort = kmPort
    httpsPort = kmPort + 1

print "Creating server"
createDomain(emAppServerPath+'/wlserver/common/templates/wls/wls.jar', emAppServerPath+'/domains/server_'+domain, managementUsername, managementPassword)
readDomain (emAppServerPath+'/domains/server_'+domain)

print "Applying general settings, listening on port 7280"
set ('AdminServerName', domain)
set ('ProductionModeEnabled', true)
# enable separate administration console port
set ('AdministrationPort', adminPort)
set ('AdministrationPortEnabled', true)

cd ('Server/AdminServer')
set ('Name', domain)
set ('ListenAddress', '0.0.0.0')
set ('StagingMode', 'stage')
set ('KeyStores', 'CustomIdentityAndCustomTrust')

set ('ListenPort', httpPort)
set ('ListenPortEnabled', not httpsEnabled)

print "Configuring identity store"
set ('CustomIdentityKeyStoreFileName', identityKeystorePath)
set ('CustomIdentityKeyStoreType', 'JKS')
set ('CustomIdentityKeyStorePassPhraseEncrypted', identityKeystorePassphrase)

print "Configuring trust store"
set ('CustomTrustKeyStoreFileName', trustKeystorePath)
set ('CustomTrustKeyStoreType', 'JKS')
set ('CustomTrustKeyStorePassPhraseEncrypted', trustKeystorePassphrase)

print "Configuring SSL"
create(domain,'SSL')
cd ('SSL/'+domain)
set ('ListenPort', httpsPort)
set ('Enabled', httpsEnabled)
set ('ServerPrivateKeyAlias', 'appserver-identity-key')
set ('ServerPrivateKeyPassPhraseEncrypted', privateKeyPassphrase)

set ('TwoWaySSLEnabled', false)
set ('ClientCertificateEnforced', false)
set ('AcceptKssDemoCertsEnabled', false)
set ('HostnameVerifier', 'weblogic.security.utils.SSLWLSWildcardHostnameVerifier')

updateDomain ()
exit ()

