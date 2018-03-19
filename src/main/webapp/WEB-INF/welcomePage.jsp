<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ page import="com.fdm.model.Reading,javax.servlet.http.HttpSession,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Air Quality</title>
<link href="resources/styles/fdmDemoStyleSheet.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<h1>${message}</h1>
	<table>
		<tr>
			<th>Region</th><th>TimeStamp</th><th>Type</th><th>Value</th>
		</tr>
		<% for(Reading reading : (List<Reading>)request.getAttribute("readings")){	%>
		<tr>
			<td><%=	reading.getRegionName() %></td>
			<td><%= reading.getTimeStamp() %></td>
			<td><%= reading.getType() %></td>
			<td><%=	reading.getValue() %></td>
		</tr>
		<% }%>
	</table>
	

	
</body>
</html>
