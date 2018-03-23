<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add | Events</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet"	href="../materialize/css/materialize.min.css" media="print,screen,projection" />
<script type="text/javascript"	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"	src="../materialize/js/materialize.min.js"></script>

</head>
<body>
<div class="container-fluid">
	<form method="post" action = "../AddEvent">
		<div class="row">
			<div class="col l4 m4"></div>
			<div class="input-field col s6 l4 m4">
	          <i class="material-icons prefix">account_circle</i>
	          <input id="icon_prefix" type="text" name="event_name" class="validate">
	          <label for="icon_prefix">Event Name</label>
        	</div>
        	<div class="col l4 m4"></div>
		</div>
		<div class="row">
			<div class="col l4 m4"></div>
			<div class="col l4 m4">
				<button class="btn waves-effect waves-light" type="submit" name="submit">
						Submit <i class="material-icons right">send</i>
				</button>
			</div>
			<div class="col l4 m4"></div>
			
		</div>
	</form>
</div>

</body>
</html>