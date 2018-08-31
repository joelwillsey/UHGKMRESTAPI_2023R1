<%@ page import="java.io.*,java.util.*" %>
<%
//String USERNAME = "USERNAME";
//String FIRST_NAME = "FIRST_NAME";
//String LAST_NAME = "LAST_NAME";
//String  GROUPS = "GROUPS";
String USERNAME = "USERNAME";
String FIRST_NAME = "FIRST_NAME";
String LAST_NAME = "LAST_NAME";
String  GROUPS = "GROUPS";
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
			"KIQPDP Telesales","KIQProducer HelpDesk","KIQTricare_Agent","KIQTRICAREMedMgmt_Agent","KIQAgent CCP","KIQkiqadmin",
			"KIQHPHC Lead JV","KIQOptumHealthuser","KIQMedica NAMS",
			"KM_All_KB_Access","KM_Dental_Vision_Call","KM_Dental_Vision_Claim","KM_EnI_ECS_OLT","KM_EnI_ECS","KM_EnI_PRIME","KM_EnI_UHCBS","KM_EnI_EnrollBill",
			"KM_CnS_EnrollBill","KM_MnR_EnrollBill","KM_MnR_Claim_n_Appeals","KM_EnI_ProducerOps","KM_Shrd_Svcs_ProviderData",
			"KM_Shrd_Svcs_ProviderOps","KM_EnI_UHCWest","KM_EnI_Customer_Implementation","KM_CnS_Consumer_Service","KM_CnS_Claims",
			"KM_EnI_Claims","KM_EnI_Claim_Appeals","KM_EnI_Specialty_Ops","KM_EnI_Consumer_Service","KM_EnI_Provider_Service","KM_EnI_Empire",
			"KM_EnI_Railroad","KM_UHCG_Clinical","KM_UHCG_Insurance","KM_UHCG_Markets","KM_UHCG_Medical","KM_MnR_Provider_Service","KM_EnI_River_Valley",
			"KM_EnI_NHP","KM_EnI_Natl_Accounts_Advocacy","KM_MnR_Consumer_Service","KM_Test","KM_MnR_Telesales","KM_MnR_Producer_HelpDesk",
			"KM_MnV_TRICARE","KM_MnV_TRICAREMedMgmt","KIQPolaris_Agent","KM_Polaris","KM_OClaims","KM_Exchange","KM_COB_Processor",
			"KM_Dental_Vision_Provider_Network","KM_UHG_Authors","KM_UHCG_Assistance","KM_EnI_Navigator_Optum","KM_Quality","KM_CnS_BCO", 
			"KM_CnS_ProviderService","KM_Implementations","KM_MnR_Claims","KM_PRISM","KM_ContractManagement","KM_USNetworksSalesAndClients", 
			"KM_FQHC_RHC_ProviderSupport"
			,"KM_Natl_Acct_CSI","KM_Medica","KM_MnR_Sup_Con_Services","KM_ECS_Advocacy"
			,"KM_MnR_Cons_Svc_Specialty_Teams"
			,"KM_BCS","KM_IHR","KM_ONCE","KM_RMO"
			,"KM_VEM_Author"
			};
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
