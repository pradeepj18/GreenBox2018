<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@ page import="com.playsoftech.greenbox.pojo.PhysicalEventMarks"%>
<%@ page import="playsoftech.gb.getmyPath"%>
<%@ page import="com.playsoftech.greenbox.pojo.Login"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin | Dashboard</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />
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

<link href="css/style.min.css" type="text/css" rel="stylesheet"
	media="screen,projection">

<style>
ul.collection.box
{
	height:360px;
	overflow-y:scroll;
}
</style>
</head>
<body class="bg-image" onload="isvalidForm(this)">
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

			if (session.getAttribute("login_id") == null) {
				response.sendRedirect("index.jsp");
			} else {

				GetDAOList getDAOList = new GetDAOList();
				GetDAOBool getDAOBool = new GetDAOBool();
				long trial_id = 3;
				long login_id = Long.parseLong(session.getAttribute("login_id").toString());
				Login login = getDAOList.getLogin(login_id, trial_id);
				
				List<?> getTrialMember = getDAOList.getTrialMemberAllSelected(trial_id);
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
								<img src="../<%=getmyPath.getPhoto(login.getPhoto()) %>" alt=""
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
									href="#" data-activates="profile-dropdown"><%=login.getUsername().toUpperCase() %><i
									class="mdi-navigation-arrow-drop-down right"></i></a>
								<p class="user-roal">
								<%
									if(login.getAdmin().toString().equals("YES"))
									{
								%>
									ADMINISTRATOR
								<%}
									else
									{%>
									OPERATOR
									<%} %>
								</p>
							</div>
						</div>
					</li>
					<li class="bold  "><a href="home.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">apps</i>
							Dashboard</a></li>
					<li class="bold"><a href="teamlist.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">group_add
						</i> Select Team </a></li>
					<li class="bold active"><a href="unselectedPlayer.jsp"
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
					<!-- <li class="bold"><a href="team.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">view_list
						</i> Team List </a></li>
					<li class="bold"><a href="score.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">insert_chart</i>
							Score</a></li> -->
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
							<h4 style="color: white;">UnSelect Player</h4>
							
						</div>
						<form onsubmit="return isvalidForm(this);"
							action="../UnselectTrialPlayer" method="post">
							<input type="text" id="myInput" onkeyup="myTrialMember()" placeholder="Search for names.." autofocus>
								<input type="hidden" name="trial_id" value="<%=trial_id %>">
							<ul class="collection box" id="playercheck">
								<%
									if (getTrialMember.size() > 0) {
												Iterator<?> trialMemIt = getTrialMember.iterator();
												while (trialMemIt.hasNext()) {
													PhysicalEventMarks phyEventMarks = (PhysicalEventMarks) trialMemIt.next();
													TrialMemberRegistration trialMember = getDAOList.getTrialMemberRegistration(phyEventMarks.getTrialMemberRegistration().getTmr_id(), trial_id);
													
								%>
								<li class="collection-item avatar white">
									<div class="col s2">
										<img src="../<%=getmyPath.getPhoto(trialMember.getPhoto())%>"
											alt="" class="circle responsive-img valign profile-image">
									</div>
									<div class="col s6 ">
										<h6 class="truncate">
											<span><b><%=trialMember.getFname().toUpperCase()%> <%=trialMember.getMname().toUpperCase()%>
												<%=trialMember.getLname().toUpperCase()%></b>
											</span>
										</h6>
									</div>
									<div class="col s4 ">
										<h6>
											<span><b><%=getmyPath.getReg_id(trialMember.getTmr_id())%></b></span>
										</h6>
									</div>
									<div class="col s2">
									
										<input type="checkbox"
											id="myCheckbox<%=trialMember.getTmr_id()%>" name="player"
											class="filled-in" value="<%=trialMember.getTmr_id()%>" />
										<%
											
										%>
										<label for="myCheckbox<%=trialMember.getTmr_id()%>"></label>

									</div>
								</li>
								<%
									}
											} else {
								%>
								<li class="collection-item avatar">
									<h5>No Player Available</h5>
								</li>
								<%
									}
								%>
							</ul>
							<div class="right">
								<button class="btn-floating btn-large" type="submit"
									name="submit">
									<i class="material-icons right">send</i>
								</button>
							</div>
						</form>
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

	<script type="text/javascript">
		var count = 1;
		function isvalidForm(form) {
			count=1;
			ul = document.getElementById("playercheck");
			li = ul.getElementsByTagName('li');
			for (var i = 0; i < li.length; i++) {
				a = li[i].getElementsByTagName("input")[0];
				if ($(a).is(":checked"))
					count++;
			}
			if (count == 1)
				{/* 
					alert("Select atleast one player"); */
					return false;
				}
			else
				return true;
		}

		$(document).ready(function() {
			$("#playercheck input[type=checkbox]").click(function() {
				if ($(this).is(":checked")) {
					Materialize.toast('Count - ' + count++, 2000);
					
				} else {
					Materialize.toast('Count - ' + (--count - 1), 2000);
				}
			});
			
		});

		function myTrialMember(){
			  var input, filter, ul, li, a, i;
			    input = document.getElementById('myInput');
			    filter = input.value.toUpperCase();
			    ul = document.getElementById("playercheck");
			    li = ul.getElementsByTagName('li');

			    // Loop through all list items, and hide those who don't match the search query
			    for (i = 0; i < li.length; i++) {
			        a = li[i].getElementsByTagName("span")[0];
			        a1 = li[i].getElementsByTagName("span")[1];
			        if ((a.innerHTML.toUpperCase().indexOf(filter) > -1) || (a1.innerHTML.toUpperCase().indexOf(filter) > -1)) {
			            li[i].style.display = "";
			        } else {
			            li[i].style.display = "none";
			        }
			    }
		}
		$(document).keypress(
			    function(event){
			     if (event.which == '13') {
			        event.preventDefault();
			      }


			});
	</script>
 <!-- jQuery Library -->
    <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>    
    <!--materialize js-->
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <!--scrollbar-->
    <script type="text/javascript" src="js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>

 <script type="text/javascript" src="js/plugins.min.js"></script>
</body>
</html>