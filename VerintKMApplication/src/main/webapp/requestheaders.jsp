<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Http Request Headers</title>
</head>
<body>
	<h2>HTTP Request Headers Received</h2>
	<table>
		<%
		Enumeration enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
		String name = (String) enumeration.nextElement();
		String value = request.getHeader(name);
		%>
		<tr><td><%= name %></td><td><%= value %></td></tr>
		<%
		}
		%>
	</table>
</body>
</html>