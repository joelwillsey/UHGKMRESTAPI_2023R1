import os
managementUsername = os.environ.get('KM_MANAGEMENT_USERNAME')
managementPassword = os.environ.get('KM_MANAGEMENT_PASSWORD')
domain = os.environ.get('KM_DOMAIN')
connectUrl = os.environ.get('KM_CONNECT_URL')


try:
    connect(managementUsername,managementPassword,connectUrl)
    shutdown(domain, 'Server', ignoreSessions='true', force='true')
except:
    print 'Failed to shutdown server'

exit()
            