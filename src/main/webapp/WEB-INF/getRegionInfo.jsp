<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags"%>
<%@ page
	import="com.fdm.model.Reading,javax.servlet.http.HttpSession,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>24 Hour Readings for <%=request.getParameter("regionName")%></title>
</head>
<body>
	<n:nav></n:nav>
	<header>
		<h1 class="text-center">Singapore Air Quality</h1>
		<h2 class="text-center">Region: <%=request.getParameter("regionName")%></h2>
	</header>
	<div class="container ">
		<div class="row border-bottom">
			<div class="col-5">TimeStamp</div>
			<div class="col-5 border-left">Type</div>
			<div class="col-2 border-left">Value</div>
		</div>
		<%
			for (Reading reading : (List<Reading>) request.getAttribute("readings")) {
				String regionName = reading.getRegionName();
		%>
		<div class="row border-bottom">
			<div class="col-5"><%=reading.getTimeStamp()%></div>
			<div class="col-5 border-left"><%=reading.getType()%></div>
			<div class="col-2 border-left"><%=reading.getValue()%></div>
		</div>
		<%
			}
		%>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>