<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trials Member Registration</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="../materialize/css/materialize.css"
	media="print,screen,projection" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script type="text/javascript"
	src="../materialize/js/materialize.min.js"></script>
	
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col l4 m3"></div>
			<div class="input-field col l4 m6 s12">
				<select onchange="getpstatus(this);" id="pstatus">
					<option value="-1" disabled selected>Choose your Status</option>
					<option value="OLD">Existing Player</option>
					<option value="NEW">New Player</option>

				</select> <label></label>
			</div>
			<div class="col l4 m3"></div>
		</div>
	</div>
	<div id="oldnew"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();
		});
		function getpstatus(id)
		{
			if(id.value=="OLD")
			{
				$.ajax({
					method : "post",
					url : "trialsmemregOPAx.jsp",
					data : ({}),
					async : false,
					cache : false,
					beforeSend : function() {},
					complete : function() {},
					success : function(msg) {
						
						$('#oldnew').html(msg);
					}
				});
			}
			else if(id.value="NEW")
			{
				$.ajax({
					method : "post",
					url : "trialsmemregNPAx.jsp",
					data : ({}),
					async : false,
					cache : false,
					beforeSend : function() {},
					complete : function() {},
					success : function(msg) {
						
						$('#oldnew').html(msg);
					}
				});
			}
			else
			{
				alert("Select correct option");
			}
		}
	</script>
</body>
</html>