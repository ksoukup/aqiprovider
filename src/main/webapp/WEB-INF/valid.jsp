<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fdm.model.User,javax.servlet.http.HttpSession" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resources/styles/fdmDemoStyleSheet.css" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${user.username} User Added</title>
</head>
<body>
	<h1>Username Via expression: ${user.username}</h1>
	<%	User user = (User) request.getAttribute("user"); 
		
	%>
	
	<%= session.getAttribute("timesVisit") %>

	<h1>Username via Scriptlet <%= user.getUsername()  %></h1>
	<h1>Session ID: 	<%= session.getId() %></h1>
	 <a href="newRequest.jsp">New Request</a>
</body>
</html>