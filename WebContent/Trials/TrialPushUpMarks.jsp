<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@ page import="com.playsoftech.greenbox.pojo.PhysicalEventMarks"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOClass" %>
<%@ page import="playsoftech.gb.getmyPath"%>
<%@ page import="com.playsoftech.greenbox.pojo.Events" %>
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

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script type="text/javascript"
	src="../materialize/js/materialize.min.js"></script>

<style>
input[type="text"]{
	border-bottom: 1px solid #000;
	height: 2rem;
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
		
			if (session.getAttribute("login_id") == null) 
			{
				response.sendRedirect("index.jsp");
			} 
			else 
			{

				GetDAOList getDAOList = new GetDAOList();
				GetDAOBool getDAOBool = new GetDAOBool();
				GetDAOClass getDAOClass = new GetDAOClass();
				long trial_id = Long.parseLong(request.getParameter("trial_id"));
				long event_id = Long.parseLong(request.getParameter("event_id"));
				long login_id = Long.parseLong(session.getAttribute("login_id").toString());
				Login login = getDAOList.getLogin(login_id, trial_id);
				
				List<?> playerList = getDAOList.getPhysicalTestPlayerByEvent(trial_id, event_id);
				Iterator<?> it = playerList.iterator();
				
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
					<li class="bold"><a href="unselectedPlayer.jsp"
						class="waves-effect waves-cyan"><i class="material-icons">view_list
						</i>Update Player </a></li>
					<li class="bold active"><a href="physicalTest.jsp"
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
				<%
					Events event = getDAOClass.getEvents(event_id);
				%>
					<div class="col s12 m8 offset-m2 l6 offset-l3"
						style="background: rgba(142, 140, 140, 0.6)">
						<div class="center tittle ">
							<h5 style="color: white;">
							<%if(event!=null){ %>
							<%=event.getEvent_name().toUpperCase() %> PLAYER'S LIST
							
							<%} %>
							</h5>
						</div>
						 
						 <div>
						 <form action="../AddPhysicalPlayerScore" method="post">
						 <input type="hidden" name="trial_id" value="<%=trial_id %>">
						 <input type="hidden" name="event_id" value="<%=event_id %>">
						 <!-- PIC NmE Ns MARKS -->
						 	<div class="right-align" style="color:#000;font-size: 16px;">Click on checkBox if player is injured.</div>
						 	
						 	<ul class="collection" id="playercheck">
						 
						 		<%
						 		while(it.hasNext())
						 		{
									PhysicalEventMarks physicalEventMarks = (PhysicalEventMarks)it.next();
									TrialMemberRegistration tmr = getDAOClass.getTrialMemberRegistration(physicalEventMarks.getTrialMemberRegistration().getTmr_id());
								 %>
						 			<li class="collection-item avatar white">
						 			<%if(event_id!=4){ %>
									<div class="col s2">
										<img src="../<%=getmyPath.getPhoto(tmr.getPhoto()) %>" alt="" class="circle responsive-img valign profile-image">
									</div>
									<div class="col s4">
										<h6 class="truncate">
											<span><b class="playertmrid"><%=tmr.getFname().toUpperCase() %> <%=tmr.getLname().toUpperCase() %></b>
											</span>
										</h6>
									</div>
									<div class="col s2">
										<h6>
											<span><b class="playertmrid"><%=getmyPath.getReg_id(tmr.getTmr_id()) %></b></span>
										</h6>
									</div>
									<%if(event_id==1)
									{ %><!-- 100 MTR -->
									<div class="col s3" style="margin-left: 3em;">
										<input type="text" id="100mtrtime" name="marks" placeholder="Time" required>
										<input type="hidden"  name="ids"	value="<%=tmr.getTmr_id()%>" />
									</div>
									<%
									}
									else if(event_id == 6 || event_id == 7) // SHUTTLE RUN and REFLEX
									{
									%>
									<div class="col s3" style="margin-left: 3em;">
										<input type="number" id="GoalkeeperTime" name="marks" min="0" max="3" placeholder="Count out of 3" required>
										<input type="hidden"  name="ids"	value="<%=tmr.getTmr_id()%>" />
									</div>
									<%	
									}
									else if(event_id==5)/*  and plank */
									{
									%>
									<div class="col s3" style="margin-left: 3em;">
										<input type="number" id="plankTime" name="marks" min="0" max="999" 	placeholder="second" required>
										<input type="hidden"  name="ids"	value="<%=tmr.getTmr_id()%>" />
									</div>
									<%
									}
									else if(event_id==2 || event_id==3){ %> <!-- PUSH UP and MINUTES R/W-->
									<div class="col s3" style="margin-left: 3em;">
										<input type="number" id="PushUptime" name="marks" min="0" placeholder="Count" required>
										<input type="hidden"  name="ids"	value="<%=tmr.getTmr_id()%>" />
									</div>
									<%} %>
									
									<div class="col s1" style="float:none;">
										<input type="checkbox" id="injuredId<%=tmr.getTmr_id() %>" name="injuredPlayer" value="<%=tmr.getTmr_id() %>"/>
      									<label for="injuredId<%=tmr.getTmr_id() %>"></label>
									</div>
									<%}else{ %><!-- DRIBLLING -->
									<div class="col s2">
										<img src="../<%=getmyPath.getPhoto(tmr.getPhoto()) %>" alt="" class="circle responsive-img valign profile-image">
									</div>
									<div class="col s3">
										<h6 class="truncate">
											<span><b class="playertmrid"><%=tmr.getFname().toUpperCase() %> <%=tmr.getLname().toUpperCase() %></b>
											</span>
										</h6>
									</div>
									<div class="col s2">
										<h6>
											<span><b class="playertmrid"><%=getmyPath.getReg_id(tmr.getTmr_id()) %></b></span>
										</h6>
									</div>
									<div class="col s2" style="margin-left: 1em;">
										<input type="text" id="driblingtime" name="dribletime" placeholder="Time" required>
										<input type="hidden"  name="ids"	value="<%=tmr.getTmr_id()%>" />
									</div>
									<div class="col s2" style="margin-left: 3em;">
										<input type="number" id="PushUptime" name="marks" min="0" placeholder="Goal" required>
									</div>
									<div class="col s1" style="float:none;">
										<input type="checkbox" id="injuredId<%=tmr.getTmr_id() %>" name="injuredPlayer" value="<%=tmr.getTmr_id() %>"/>
      									<label for="injuredId<%=tmr.getTmr_id() %>"></label>
									</div>
									<%} %>
								</li>
								<%} %>
						 	</ul>
						 	<div class="right">
								<button class="btn-floating btn-large" type="submit" name="submit">
									<i class="material-icons right">send</i>
								</button>
							</div>
						 	</form>
						 </div>
						 
					</div>
				</div>
			</div>
		</section>
	</div>
	<script>
	 $(document).ready(function() {
		    $('select').material_select();
	});
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
				{
					alert("Select atleast one player");
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

				$(document).keypress(
			    function(event){
			     if (event.which == '13') {
			        event.preventDefault();
			      }


			});
	</script>
	<%	}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in TrialPushUpMarks " + e);

		}
	%>
	 <!-- jQuery Library -->
    <script type="text/javascript" src="js/plugins/jquery-1.11.2.min.js"></script>    
    <!--materialize js-->
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <!--scrollbar-->
    <script type="text/javascript" src="js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>

 <script type="text/javascript" src="js/plugins.min.js"></script>
</body>
</html>