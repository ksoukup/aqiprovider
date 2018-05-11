<%@ tag language="java" pageEncoding="ISO-8859-1"%>
	<nav class="navbar navbar-expand-lg">
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">HOME</a></li>
				<li class="nav-item dropdown">
        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          			Regions
        			</a>
        			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="getRegionInfo.jsp?regionName=NRS">National</a>
						<a class="dropdown-item" href="getRegionInfo.jsp?regionName=RCE">Central</a>
						<a class="dropdown-item" href="getRegionInfo.jsp?regionName=REA">East</a>
						<a class="dropdown-item" href="getRegionInfo.jsp?regionName=RNO">North</a>
						<a class="dropdown-item" href="getRegionInfo.jsp?regionName=RSO">South</a>
						<a class="dropdown-item" href="getRegionInfo.jsp?regionName=RWE">West</a>
					</div>
				</li>
			</ul>
		</div>
	</nav>