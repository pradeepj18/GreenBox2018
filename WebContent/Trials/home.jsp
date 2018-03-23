<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@ page import="playsoftech.gb.getmyPath"%>
<%@ page import="com.playsoftech.greenbox.pojo.Login"%>
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

</head>
<body class="bg-image">

	<header id="header" class="page-topbar">
		<!-- start header nav-->
		<div class="navbar-fixed">
			<nav class="navbar-color">
				<div class="nav-wrapper ">
					<ul class="left">
						<li><h1 class="logo-wrapper ">
								<a href="#" class="brand-logo darken-1 "><img
									src="images/brand.png" class="" alt="materialize logo"></a> <span
									class="logo-text">Materialize</span>
							</h1></li>
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
	%>
	<div id="main">
		<!-- START WRAPPER -->
		<%
			try {
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Cache-Control", "no-store");
					response.setDateHeader("Expires", 0);
					response.setHeader("Pragma", "no-cache");

					if (session.getAttribute("login_id") == null) {
						response.sendRedirect("index.jsp");
					} else {

						GetDAOList getDAOList = new GetDAOList();
						GetDAOBool getDAOBool = new GetDAOBool();
						long trial_id = 3;
						long login_id = Long.parseLong(session.getAttribute("login_id").toString());
						Login login = getDAOList.getLogin(login_id, trial_id);

						
		%>
		<div class="wrapper">

			<aside id="left-sidebar-nav">
				<ul id="slide-out" class="side-nav fixed leftside-navigation">
					<li class="user-details cyan darken-2">
						<div class="row">
							<div class="col col s4 m4 l4">
								<img src="../<%=getmyPath.getPhoto(login.getPhoto())%>" alt=""
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
									href="#" data-activates="profile-dropdown"><%=login.getUsername().toUpperCase()%><i
									class="mdi-navigation-arrow-drop-down right"></i></a>
								<p class="user-roal">
									<%
										if (login.getAdmin().toString().equals("YES")) {
									%>
									ADMINISTRATOR
									<%
										} else {
									%>
									OPERATOR
									<%
										}
									%>
								</p>
							</div>
						</div>
					</li>
					<li class="bold active "><a href="home.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">apps</i>
							Dashboard</a></li>
					<li class="bold"><a href="teamlist.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">group_add
						</i> Select Team </a></li>
					<li class="bold"><a href="unselectedPlayer.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">view_list
						</i>Update Player </a></li>
					<li class="bold"><a href="physicalTest.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">view_list
						</i> Physical Test </a></li>
					<li class="">

						<ul class="collapsible" data-collapsible="accordion">
							<li>
								<div class="collapsible-header" style="color: #636262;font-weight: 400;">
									<i class="material-icons">print</i>Show Details
								</div>
								<div class="collapsible-body">
									<a href="AddTrialsRefree.jsp" class="waves-effect waves-cyan"><i
										class="material-icons">view_list </i> Add Refree </a> <a
										href="PrintRefreeUserPass.jsp" class="waves-effect waves-cyan"><i
										class="material-icons">view_list </i> Show Refree </a> <a
										href="PrintTrialPlayers.jsp" class="waves-effect waves-cyan"><i
										class="material-icons">view_list </i>Show Trial Players </a> <a
										href="TrialMemberOutput.jsp" class="waves-effect waves-cyan"><i
										class="material-icons">view_list </i> Trial Marks Evaluation </a>
								</div>
							</li>

						</ul>

					</li>
					<!-- <li class="bold"><a href="score.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">insert_chart</i>
							Score</a></li> -->
				</ul>
				<a href="#" data-activates="slide-out"
					class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only cyan"><i
					class="mdi-navigation-menu"></i></a>
			</aside>

		</div>

		<section id="content">
			<div class="slider">
				<ul class="slides">
					<li><img src="images/3.jpg"> <!-- random image --></li>
					<li><img src="images/3.jpg"> <!-- random image --></li>
					<li><img src="images/3.jpg"> <!-- random image --></li>
					<li><img src="images/3.jpg"> <!-- random image --></li>
					<li><img src="images/3.jpg"> <!-- random image --></li>
				</ul>
			</div>

			<div id="card-widgets">
				<div class="row">
					<div class="col s12 m6 l4">
						<div id="flight-card" class="card">
							<div class="card-header amber darken-2">
								<div class="card-title">
									<h4 class="flight-card-title">Player Registered</h4>
									<p class="flight-card-date">Number of players are registerd
										are</p>

								</div>
							</div>
							<div class="card-content-bg white-text">
								<div class="card-content">
									<h2 class="center">5412</h2>
									<h2 class="center">
										<i class="large material-icons">directions_run</i>
									</h2>


								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m6 l4">
						<div id="flight-card" class="card">
							<div class="card-header amber darken-2">
								<div class="card-title">
									<h4 class="flight-card-title">Teams</h4>
									<p class="flight-card-date">number of Teams we having is</p>
								</div>
							</div>
							<div class="card-content-bg white-text">
								<div class="card-content">
									<h2 class="center">5412</h2>
									<h2 class="center">
										<i class="large material-icons">person_add</i></i>
									</h2>

								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m6 l4">
						<div id="flight-card" class="card">
							<div class="card-header amber darken-2">
								<div class="card-title">
									<h4 class="flight-card-title">Officials</h4>
									<p class="flight-card-date">No. of Officials on panel we
										have</p>
								</div>
							</div>
							<div class="card-content-bg white-text">
								<div class="card-content">
									<h2 class="center">5412</h2>
									<h2 class="center">
										<i class="large material-icons">assistant_photo</i>
									</h2>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</section>
	</div>
	<%
		}
			} catch (Exception e) {
				System.out.println("teamList " + e.getMessage());

			}
	%>
	<%
		} catch (Exception e) {
			System.out.println("home.jsp exception " + e.getMessage());
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


</body>


</html>