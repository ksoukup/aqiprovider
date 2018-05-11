<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="n" tagdir="/WEB-INF/tags"%>
<%@ page
	import="com.fdm.model.Reading,com.fdm.model.Region,javax.servlet.http.HttpSession,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Air Quality</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<n:nav></n:nav>
	<header>
	<h1 class="text-center">Singapore Air Quality</h1>
	<h3 class="text-center">Current Readings</h3>
	
	</header>

	<div class="container">
		<br>
		<div id="regionCarousel" class="carousel slide" data-ride="carousel">

			<div class="carousel-inner" role="listbox">

				<%
					boolean first = true;
					for(Region region : (List<Region>) request.getAttribute("regions")){
						String regionName = region.getRegionName();
						String regionId = region.getRegionId();
						List<Reading> readings = region.getReadings();

								if(first){
					%>
				<div class="carousel-item active">
					<%
								}else{
					%>
				<div class="carousel-item">
					<%
								}
					%>
					<h3 class="text-center"><a href="getRegionInfo.jsp?regionName=<%=regionId%>"><%=regionName%></a></h3>
					<div class="row border-bottom ">
						<div class="col-4">TimeStamp</div>
						<div class="col-4 border-left">Type</div>
						<div class="col-2 border-left">Value</div>
					</div>
					<%	for (Reading reading : readings)
						{
					%>
					<div class="row border-bottom">
						<div class="col-4"><%=reading.getTimeStamp()%></div>
						<div class="col-4 border-left"><%=reading.getType()%></div>
						<div class="col-2 border-left"><%=reading.getValue()%></div>
					</div>

					<%
							
						}
					first = false;
					
					%>
				</div>
					<%
					}
					%>
					
			</div>
			<a class="carousel-control-prev" href="#regionCarousel" data-slide="prev">
			  <span class="carousel-control-prev-icon"></span>
			</a>
			<a class="carousel-control-next" href="#regionCarousel" data-slide="next">
			  <span class="carousel-control-next-icon"></span>
			</a>
		</div>
	</div>




</body>
</html>
