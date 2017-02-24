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
	response.addHeader("ssousername","");
	response.addHeader("ssofirstname", "");
	response.addHeader("ssolastname", "");
	response.addHeader("kmgroups", "");
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
	String[] SSO_GROUPS = {"KIQACME_Agent_Dental_Vision_Call","KIQACME_Agent_Dental_Vision_Claim","KIQACME Agent ECS_OLT",
			"KIQACME Agent Emp-Broker ECS","KIQACME Agent Emp-Broker Prime","KIQACME Agent Emp-Broker UHCBS","KIQACME Agent EnrollBill",
			"KIQACME_Agent_MedicaidEligCOB","KIQACME_Agent_Medicare_Processor","KIQACME Agent ProviderData","KIQACME_Agent_ProviderOps",
			"KIQACME_Agent_UHCWest","KIQACME Client Implementation","KIQACME Medicaid Call Agent","KIQACME Medicaid-Processor",
			"KIQACME Transaction-Processor","KIQAgent CCP","KIQAgent PPR","KIQACME HealthAdvisor","KIQCustomer Service","KIQEnrollment",
			"KIQPDP Telesales","KIQProducer HelpDesk","KIQTricare_Agent","KIQTRICAREMedMgmt_Agent","KIQAgent CCP","KIQkiqadmin"};
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
		//if(group=="") return "default";
		return group;
	}%>
