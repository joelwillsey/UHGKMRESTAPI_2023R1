<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%
	String USERNAME = "KM_USER";
	String FIRST_NAME = "KM_FIRSTNAME";
	String LAST_NAME = "KM_LASTNAME";
	String GROUPS = "KM_GROUPS";
	String ssoUserName = request.getHeader(USERNAME);
	String ssoFirstName = request.getHeader(FIRST_NAME); 
	String ssoLastName = request.getHeader(LAST_NAME); 
	String kmGroups = request.getHeader(GROUPS);
	String[] GLOBAL_GROUPS = {"KIQACME_Agent_Dental_Vision_Call","KIQACME_Agent_Dental_Vision_Claim","KIQACME Agent ECS_OLT",
			"KIQACME Agent Emp-Broker ECS","KIQACME Agent Emp-Broker Prime","KIQACME Agent Emp-Broker UHCBS","KIQACME Agent EnrollBill",
			"KIQACME_Agent_MedicaidEligCOB","KIQACME_Agent_Medicare_Processor","KIQACME Agent ProviderData","KIQACME_Agent_ProviderOps",
			"KIQACME_Agent_UHCWest","KIQACME Client Implementation","KIQACME Medicaid Call Agent","KIQACME Medicaid-Processor",
			"KIQACME Transaction-Processor","KIQAgent CCP","KIQAgent PPR","KIQACME HealthAdvisor","KIQCustomer Service","KIQEnrollment",
			"KIQPDP Telesales","KIQProducer HelpDesk","KIQTricare_Agent","KIQTRICAREMedMgmt_Agent","KIQAgent CCP","KIQkiqadmin"};
	
	String parsedGroups=parseKMGroups(kmGroups,GLOBAL_GROUPS );

	%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/VerintTools.css" type="text/css">
	<link rel="icon" type="image/ico" href="images/favicon.png">
	<title>Request Headers</title>

</head>
<body>
	<div id="xui-topbar">
		<h1>Verint Knowledge Management - Single Sign On Headers</h1>
		<a id="xui-logout" href="<%="http://" + request.getServerName() + ":" + request.getServerPort() + "/verintkm/verintkm.html" %>" class="logout">Log In</a>
	</div>
	<div id="xui-main">
		<div class="xui-lm-columns-12">
			<h2>Agent Information</h2>
			<% if(ssoUserName == null || ssoFirstName == null || ssoLastName == null  ) { %>
			<div class="xui-lm-row xui-message xui-message-negative">
			<em>Missing the user information required for single-sign-on</em>
			<p> </p>
			<% } else { 
				if (parsedGroups.trim().length() == 0) {%>
					<div class="xui-lm-row xui-message xui-message-warning">
					<em>This user has the Siteminder headers, but agent will not be created as there are no valid KM Global Groups.  If it's an existing agent with position defined in Engagement Management they will be allowed in. If they have no position defined in Engagement Management they will not be allowed to login.</em>
					<p> </p>
				<% } else { %>
					<div class="xui-lm-row xui-message xui-message-positive">
					<em>This information will be used for single-sign-on</em>
					<p> </p>
				<% } %>
			<% } %>
				<ul class="xui-list xui-attrlist">
					<li>
						<span class="xui-key">Username</span>
						<% if(ssoUserName == null) { %>
						<span class="xui-val"> </span>
						<% } else { %>
						<span class="xui-val"><%= ssoUserName %></span>
						<% } %>
					</li>
					<li>
					<span class="xui-key">First Name</span>
						<% if(ssoFirstName == null) { %>
						<span class="xui-val"> </span>
						<% } else { %>
						<span class="xui-val"><%= ssoFirstName %></span>
						<% } %>						
					</li>
					<li>
						<span class="xui-key">Last Name</span>
						<% if(ssoLastName == null) { %>
						<span class="xui-val"> </span>
						<% } else { %>
						<span class="xui-val"><%= ssoLastName %></span>
						<% } %>
					</li>
					<li>
						<span class="xui-key">Global KM Groups</span>
						<% if(kmGroups == null) { %>
						<span class="xui-val"> </span>
						<% } else { %>
						<span class="xui-val"><%= parsedGroups %></span>
						<% } %>
					</li>
				</ul>				
			</div>
		</div>
		<p> </p>
		<hr>
		<div>		
			<h2>HTTP Request Headers</h2>
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Value</th>
					</tr>
					<%
					Enumeration enumeration = request.getHeaderNames();
					while (enumeration.hasMoreElements()) {
						String name = (String) enumeration.nextElement();
						String value = request.getHeader(name);
		
						if(name.equals(USERNAME) || name.equals(FIRST_NAME) || name.equals(LAST_NAME) || name.equals(GROUPS)) {%>
							<tr class="xui-message xui-message-warning">
						<% } else { %>	
							<tr>
						<% } %>									
							<td><%= name %></td>					
							<td><%= value %></td>
						</tr>
					<% } %> 
				</tbody>
			</table>
		</div>
		<hr>
		<div class="xui-lm-col xui-lm-col6 xui-lm-col-last">
					
		<h2>Global KM Groups</h2>
		<h3>
			Lists of all the Global KM Groups the Verint Knowledge Management currently recognizes.			
		</h3>
			<div class="xui-message">
				<div class="xui-col-short">
					<ul class="xui-list">
						<% 
							int i=0;
							for(i=0;i<GLOBAL_GROUPS.length;i++)
							{ %>
								<li>
									<span><%= GLOBAL_GROUPS[i]%></span>
								</li>
						<% 	} %>	
					</ul>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>

<%! String parseKMGroups(String kmGroup, String[] SSO_GROUPS ) {
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
		return group;
	}%>