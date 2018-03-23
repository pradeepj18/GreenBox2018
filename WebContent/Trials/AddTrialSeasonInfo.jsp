<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
	<%@ page import="com.playsoftech.greenbox.pojo.Trials"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
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
	href="../materialize/css/materialize.min.css"
	media="print,screen,projection" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script type="text/javascript"
	src="../materialize/js/materialize.min.js"></script>


<style type="text/css">
body
{
	overflow-x:hidden;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<form action="../AddTrialSeasonInfo" method="post">
			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l2 m4 s12">
					<input id="trial_name" type="text" class="validate" name="trial_name" required> <label
						for="trial_name">Trial Name</label>
				</div>
			<div class="input-field col l2 m4 s12">
					<input id="trial_venue" type="text" class="validate" name="trial_venue" required> <label
						for="trial_venue">Trial Venue</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="trial_date" name="trial_date" type="text" class="datepicker" required> <label
						for="trial_date">Trial Date</label>
				</div>
				
				
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l5 m4 s3"></div>
				<div class="col l4 m4 s6">
					<button class="btn waves-effect waves-light" type="submit"
						name="submit">
						Submit <i class="material-icons right">send</i>
					</button>
				</div>
				<div class="col l5 s3 m4"></div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();
		});
		$('.datepicker').pickadate({
			min : new Date((new Date()).valueOf()),
			selectMonths : true,
			selectYears : 10,
			today : 'Today',
			clear : 'Clear',
			close : 'Close',
			closeOnSelect : true,
			format : 'yyyy/mm/dd'
		});
	</script>
</body>
</html>