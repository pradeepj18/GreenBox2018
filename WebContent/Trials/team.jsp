<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@ page import="playsoftech.gb.getmyPath"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialRefreeLogin "%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description" content=" ">
<meta name="keywords" content="">
<title>GreenBox-MFC</title>

<!-- Favicons-->
<link rel="icon" href="images/favicon/favicon.png" sizes="32x32">

<!-- CORE CSS-->
<link href="css/materialize.min.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="css/style.min.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<style>
.playertmrid {
	vertical-align: middle;
	line-height: 30px;
}
</style>
</head>
<body class="bg-image">
	<header id="header" class="page-topbar">
		<!-- start header nav-->
		<div class="navbar-fixed">
			<nav class="navbar-color">
				<div class="nav-wrapper ">
					<ul class="left">
						<li>
							<h1 class="logo-wrapper ">
								<a href="#" class="brand-logo darken-1 "><img
									src="images/brand.png" class="" alt="materialize logo"></a> <span
									class="logo-text">Materialize</span>
							</h1>
						</li>
					</ul>
					<div class="header-search-wrapper hide-on-med-and-down ">
						<h5 class="center truncate">GreenBox-Mini FootBall
							Championship 2018</h5>
					</div>
					<ul class="right hide-on-med-and-down ">
						<li><a href="#"
							class="waves-effect waves-block waves-light notification-button "
							data-activates="notifications-dropdown"><i
								class="material-icons">add_alert</i> </a></li>
					</ul>
				</div>
			</nav>
		</div>
		<!-- end header nav-->
	</header>
	<%
		try {

			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");

			if (session.getAttribute("reflogin_id") == null) {
				response.sendRedirect("index.jsp");
			} else 
			{
				String refloginid = session.getAttribute("reflogin_id").toString();
				long reflogin_id = Long.parseLong(refloginid);

				long trial_id = 3;
				GetDAOList getDAOList = new GetDAOList();
				
				TrialRefreeLogin trialRefreeLogin = getDAOList.getTrialRefree(reflogin_id, trial_id);
				List<?> getTrialMember = getDAOList.getTrialMemberSelected1(trial_id);
	%>


	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">

			<!-- START LEFT SIDEBAR NAV-->
			<aside id="left-sidebar-nav">
				<ul id="slide-out" class="side-nav fixed leftside-navigation">
					<li class="user-details cyan darken-2">
						<div class="row">
							<div class="col col s4 m4 l4">
								<img src="../<%=getmyPath.getPhoto(trialRefreeLogin.getPhoto()) %>" alt=""
									class="circle responsive-img valign profile-image">
							</div>
							<div class="col col s8 m8 l8">
								<ul id="profile-dropdown" class="dropdown-content">
									<li><a href="#"><i class="mdi-action-face-unlock"></i>
											Profile</a></li>
									<li><a href="#"><i class="mdi-action-settings"></i>
											Settings</a></li>
									<li><a href="logout.jsp"><i
											class="mdi-hardware-keyboard-tab"></i> Logout</a></li>
								</ul>
								<a
									class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
									href="#" data-activates="profile-dropdown"><%=trialRefreeLogin.getUser_name().toUpperCase() %><i
									class="mdi-navigation-arrow-drop-down right"></i></a>
								<p class="user-roal">Refree</p>
							</div>
						</div>
					</li>
					<!-- <li class="bold  "><a href="home.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">apps</i>
							Dashboard</a></li>
					<li class="bold "><a href="teamlist.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">group_add
						</i> Select Team </a></li> -->
						
					<li class="bold active"><a href="team.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">view_list
						</i> Team List </a></li>
					<li class="bold"><a href="score.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">insert_chart</i>
							Score</a></li>
				</ul>
				<a href="#" data-activates="slide-out"
					class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only cyan"><i
					class="mdi-navigation-menu"></i></a>
			</aside>
		</div>
		<section id="content">
			<div class="container">
				<div class="row">
					<div class="col s12 m8 offset-m2 l6 offset-l3"
						style="background: rgba(142, 140, 140, 0.6)">
						<div class="center tittle ">
							<h5 style="color: white;">Team Players</h5>
						</div>

						<ul class="collection" id="playercheck">
							<%
								if (getTrialMember.size() > 0) {
								Iterator<?> trialMemIt = getTrialMember.iterator();
								int i = 0;
								while (trialMemIt.hasNext()) 
								{
									TrialMemberRegistration trialMember = (TrialMemberRegistration) trialMemIt.next();
									if(i <=5)
									{
							%>
							
								<li class="collection-item avatar white" >
							<%}else {%>
							<li class="collection-item avatar white" style="background-color: #729f57 !important;color: white;">
							<%} 
								i = i+1;
							%>
								<div class="col s2">
									<img src="../<%=getmyPath.getPhoto(trialMember.getPhoto())%>"
										alt="" class="circle responsive-img valign profile-image">
								</div>
								<div class="col s4 ">
									<h6>
										<b class="playertmrid"><%=getmyPath.getReg_id(trialMember.getTmr_id())%></b>
									</h6>
								</div>
								<div class="col s6 center ">
									<h6 class="truncate right">
										<b class="playertmrid"><%=trialMember.getFname().toUpperCase()%> <%=trialMember.getLname().toUpperCase()%>
										</b>
									</h6>
								</div>
							</li>
							<%
								}
										} else {
							%>
							<li class="collection-item avatar">
								<h5 class="center">No Player Available</h5>
							</li>
							<%
								}
							%>
						</ul>
						<div class="right">
							<a href="score.jsp" class="btn-floating btn-large"><i
								class="material-icons">send</i></a>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<%
		}
		} catch (Exception e) {
			System.out.println("Team.jsp Exception " + e.getMessage());
		}
	%>

	<!-- ================================================
    Scripts
    ================================================ -->

	<!-- jQuery Library -->
	<script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>
	<!--materialize js-->
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<!--scrollbar-->
	<script type="text/javascript"
		src="js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script type="text/javascript" src="js/plugins.min.js"></script>
	<script>
		$('.carousel.carousel-slider').carousel({
			fullWidth : true
		});
	
	</script>
	<script type="text/javascript">
			var flag=false;
			ul = document.getElementById("playercheck");
		    li = ul.getElementsByTagName('li');
		    
	
	</script>
</body>
</html>