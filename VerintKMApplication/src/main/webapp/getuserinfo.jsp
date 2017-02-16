<%@ page import="java.io.*,java.util.*" %>
<%
//String USERNAME = "USERNAME";
//String FIRST_NAME = "FIRST_NAME";
//String LAST_NAME = "LAST_NAME";
//String  GROUPS = "GROUPS";
String USERNAME = "KM_USER";
String FIRST_NAME = "KM_FIRSTNAME";
String LAST_NAME = "KM_LASTNAME";
String  GROUPS = "KM_GROUPS";
String SSO_FIRST_NAME = "SSO_FIRST_NAME";
String SSO_LAST_NAME = "SSO_LAST_NAME";
String KB_NAMES = "KB_NAMES";
String ssoUserName = request.getHeader(USERNAME);
String ssoFirstName = request.getHeader(FIRST_NAME); 
String ssoLastName = request.getHeader(LAST_NAME); 
String kmGroups = request.getHeader(GROUPS);
boolean debug = false;
if (debug) 
{
	response.addHeader("ssousername","kmmanager");
	response.addHeader("ssofirstname", "Steve");
	response.addHeader("ssolastname", "Rousos");
	response.addHeader("kmgroups", "KIQkiqadmin");
}
else
{
	response.addHeader("ssousername", request.getHeader(USERNAME));
	response.addHeader("ssofirstname", request.getHeader(FIRST_NAME));
	response.addHeader("ssolastname", request.getHeader(LAST_NAME));
	response.addHeader("kmgroups", parseKMGroups(request.getHeader(GROUPS)));
}


%>
<%! String parseKMGroups(String kmGroup) {
	String[] SSO_GROUPS = { "KIQkiqadmin","KIQACME Agent NCST", "KIQOHCSES Agent", "KIQOAdminCall_Agent","KIQOClinical_Agent", "KIQOProductSupport_Agent", "KIQOPaymentIntegrity_Agent", "KIQORX_Agent", "KIQMedica NAMS" };
		String group = "";
		if (kmGroup != null) 
		{
			for (int i = 0; i < SSO_GROUPS.length; i++) 
			{
				if (kmGroup.contains(SSO_GROUPS[i])) 
				{
					group += SSO_GROUPS[i];
					group += ",";
				}
			}
		}
		if(group=="") return "default";
		return group;
	}%>
