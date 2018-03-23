<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.pojo.Member"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="playsoftech.gb.*"%>
<%@ page import="java.util.List,java.util.Iterator,java.util.Set"%>



<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trials Member Registration</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="../materialize/css/materialize.min.css"
	media="print,screen,projection" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script type="text/javascript"
	src="../materialize/js/materialize.min.js"></script>
 -->

<style type="text/css">
#search {
	border: 1px solid forestgreen;
	margin: 0;
	padding-left: 3rem;
	width: calc(100% - 3rem);
}
/* #search:focus .search-dropdown
{
	display:block;
} */
.search-dropdown {
	width: 100%;
	height: auto;
	max-height: 300px;
	background-color: currentColor;
	position: relative;
	overflow-y: auto;
	overflow-x: hidden;
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0
		rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
	margin: 0;
	display: none;
}

ul.search-dropdown li {
	font-size: 16px;
	display: block;
	padding: 5px 24px;
	cursor: pointer;
}

ul.search-dropdown li a {
	color: #fff;
}

ul.search-dropdown li:hover {
	background-color: green;
}

.margin-bot {
	margin-bottom: 0px !important;
}
</style>
</head>
<body>

	<div class="container-fluid" id="outbox">
		<div class="row margin-bot" id="memberblock">
			<div class="col l3"></div>
			<div class="input-field col s12 m12 l6">
				<input onkeyup="myFunction()" pattern="[A-Za-z]+"
					title="Enter Only letters"
					placeholder="Enter your name to search" id="search"
					type="search" data-activates='MemberList' class="dropdown-button"> 
					
					<label class="label-icon"
					for="search"><i class="material-icons" style="padding: 0px 10px;">search</i></label> <i
					class="material-icons" style="line-height: 48px;">close</i>
			
			</div>
			<div class="col l3"></div>
		</div>

		<div class="row">
			<div class="col l3"></div>
			<div class="col l6 s12 m12">
				<%
					List<?> memList = new GetDAOList().getAllOldMembers();
					Iterator<?> iterator = memList.iterator();

					String mname = "";
				%>
				<ul id="MemberList" class="dropdown-content"> <!-- for manual replace class="search-dropdown " -->
					<%
						if (iterator.hasNext()) {
							while (iterator.hasNext()) {
								Member mem = (Member) iterator.next();

								if (mem.getmName().equalsIgnoreCase(""))
									mname = " ";
								else
									mname = " " + mem.getmName() + " ";
								if (new GetDAOList().getNewTrialMember(mem.getBarcodeId())) {
					%>
					<li onclick="getMemberDetails(<%=mem.getBarcodeId()%>)"><a
						id="<%=mem.getBarcodeId()%>"> <span class="mem-name"><%=mem.getfName()%><%=mname%><%=mem.getlName()%></span>
					</a></li>
					<%
						}
							}
					%>
					<%
						} else {
					%>
					<li><span class="mem-name">Players not found</span></li>
					<%
						}
					%>
				</ul>

			</div>
			<div class="col l3"></div>
		</div>
		<div id="oldplayer"></div>
	</div>

	<script type="text/javascript">
		function Validation() {
			return true;
		}
		function myFunction() {
			var input, filter, ul, li, a, i;
			input = document.getElementById('search');
			filter = input.value.toUpperCase();
			ul = document.getElementById("MemberList");
			li = ul.getElementsByTagName('li');
			for (i = 0; i < li.length; i++) {
				a = li[i].getElementsByTagName("span")[0];
				if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
					li[i].style.display = "";
				} else {
					li[i].style.display = "none";
				}
			}
			
		}
		function getMemberDetails(barcodeId)
		{
			 $.ajax({
				method : "post",
				url : "trialsmemregOPAx2.jsp",
				data : ({
					barcodeId : barcodeId
				}),
				async : false,
				cache : false,
				beforeSend : function() {},
				complete : function() {},
				success : function(msg) {
					$("#memberblock").css("display","none");
					$(".search-dropdown").css("display","none");
					$('#oldplayer').html(msg);
				}
			}); 
			
		}
		/*  $(document).ready(function(){
			$("#search").on({
			    focus: function(event){
			    	event.preventDefault();
			        $(".search-dropdown").css("display","block");
			    },
			    click: function(event){
			    	event.preventDefault();
			        $(".search-dropdown").css("display","block");
			    },
			    dblclick:function(event){
			    	event.preventDefault();
			    	return false;
			    }
			    
			});	
			$("#MemberList").hover(function(){
				$(".search-dropdown").css("display","block");
			},
			function(){
				$(".search-dropdown").css("display","none");
			});
		});  */
		$('.dropdown-button').dropdown({
		      inDuration: 300,
		      outDuration: 225,
		      constrainWidth: true, // Does not change width of dropdown to that of the activator
		      hover: false, // Activate on hover
		      gutter: 0, // Spacing from edge
		      belowOrigin: true, // Displays dropdown below the button
		      alignment: 'left', // Displays dropdown with edge aligned to the left of button
		      stopPropagation: false // Stops event propagation
		    }
		  );
	</script>
</body>
</html>