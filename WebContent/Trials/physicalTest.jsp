<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@ page import="playsoftech.gb.getmyPath"%>
<%@ page import="com.playsoftech.greenbox.pojo.Events" %>
<%@ page import="com.playsoftech.greenbox.pojo.Login" %>
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

<!-- <script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script type="text/javascript"
	src="../materialize/js/materialize.min.js"></script>
 -->
<style></style>
</head>
<body class="bg-image" onload="physicalPlayerList(1);isvalidForm(this);">
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
			} 
			else
			{
	
				GetDAOList getDAOList = new GetDAOList();
				GetDAOBool getDAOBool = new GetDAOBool();
				long trial_id = 3;
				long login_id = Long.parseLong(session.getAttribute("login_id").toString());
				Login login = getDAOList.getLogin(login_id, trial_id);
						
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
					<div class="col s12 m8 offset-m2 l6 offset-l3"
						style="background: rgba(142, 140, 140, 0.6)">
						<div class="center tittle ">
							<h5 style="color: white;">UnSelect Player</h5>
						</div>
						<%
							List<?> eventList = getDAOList.getEvents();
						%>
						 <div class="input-field col s12 m6 offset-m3 white-text" style="margin-top:0;">
						    <select class="icons white-text" id="event_id" onchange="physicalPlayerList(1)">
						      <option value="" disabled>Select the physical event</option>
						      <%
						      	Iterator<?> eventIterator = eventList.iterator();
						      	if(eventIterator.hasNext()){
						      		Events event = (Events)eventIterator.next();
						      %>
						      	<option value="<%=event.getEvent_id() %>" data-icon="images/sample-1.jpg" class="circle" selected><%=event.getEvent_name() %></option>
						      <%		
						      	}
						      	while(eventIterator.hasNext()){
						      		Events event = (Events)eventIterator.next();
						      %>
						      <option value="<%=event.getEvent_id() %>" data-icon="images/sample-1.jpg" class="circle"><%=event.getEvent_name() %></option>
						      <%} %>
						     
						    </select>
						 </div>
						 <div id="physicalForm"></div>
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
		
		function physicalPlayerList(event_id){
			 var x = document.getElementById("event_id").value;
			$.ajax({
				
				method:"post",
				url:"physicalTestPlayerList.jsp",
				data:({trial_id:<%=trial_id %>,event_id:x}),
				async:true,
				cache:true,
				beforeSend:function(){},
				complete:function(){},
				success:function(msg){
					$('#physicalForm').html(msg);
				}
				
			});
		}
		
		 function getCheckedBoxes(chkboxName) {
			  var checkboxes = document.getElementsByName(chkboxName);
			  var checkboxesChecked = [];
			  // loop over them all
			  for (var i=0; i<checkboxes.length; i++) {
			     // And stick the checked ones onto an array...
			     if (checkboxes[i].checked) {
			        checkboxesChecked.push(checkboxes[i]);
			     }
			  }
			  // Return the array if it is non-empty, or null
			  return checkboxesChecked.length > 0 ? checkboxesChecked : null;
		} 
		
		function showPhysicalForm(){
			
			var tmr_id = [];
			if(getCheckedBoxes("player") != null){
			var checkBoxes = getCheckedBoxes("player");
			for(var i = 0;i < checkBoxes.length;i++){
				tmr_id[i] = checkBoxes[i].value;
			}
			}
			tmr_id[i++] = <%=trial_id %>;
			var event_id = document.getElementById("event_id").value;
			tmr_id[i++] = event_id;
			
			$.ajax({
				method:"post",
				url:"../AddPhysicalPlayer",
				data:{tmr_id:tmr_id},
				async:true,
				cache:true,
				beforeSend:function(){},
				complete:function(){},
				success:function(msg){
					window.location.replace("TrialPushUpMarks.jsp?trial_id="+<%=trial_id %>+"&event_id="+event_id);
				}
			});
			
		}
		
		$(document).keypress(
			    function(event){
			     if (event.which == '13') {
			        event.preventDefault();
			      }


			});
	</script>
	<%	}
		} catch (Exception e) {
			System.out.println("teamList " + e.getMessage());

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