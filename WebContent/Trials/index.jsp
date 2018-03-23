<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.AddTrialsDAO"%>
<%@ page import="com.playsoftech.greenbox.dao.AddRefreeLoginDAO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>GreenBox-MFC</title>

<!-- Favicons-->
<link rel="icon" href="images/favicon/favicon.png" sizes="32x32">
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.min.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.min.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>
<body background="images/turf.jpg">
	<%
		try {
			/* AddTrialsDAO t = new AddTrialsDAO();
			t.addTrials(); */

			/* AddRefreeLoginDAO g = new AddRefreeLoginDAO();
			g.addRefreeLogin(); */
	%>
	<div class="valign-wrapper"
		style="width: 100%; height: 100%; position: absolute;text-align:center;">
		<div class="valign" style="width: 100%;">

			<div class="valign-wrapper">
				<div class="container">
					<div class="z-depth-1 white lighten-4 row"
						style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

						<form class="col s12" method="post" action="../LoginValidation">
							<div class="row">
								<div class="col m12">
									<img alt="greenbox" class="responsive-img" src="images/gb.png">
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<i class="material-icons prefix">account_circle</i> <input
										id="username" type="text" name="username" class="validate">
									<label for="username">Username</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<i class="material-icons prefix">lock_open</i> <input
										id="password" type="password" name="password" class="validate"
										maxlength="30" size="6" data-length="30"> <label
										for="password">Password</label>
								</div>

							</div>

							<br />

							<div class="row">
								<button type="submit" name="btn_login"
									class="col s12 btn btn-large waves-effect green hoverable">Login</button>
							</div>

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%
		} catch (Exception e) {
			System.out.println("index.jsp exception " + e.getMessage());
		}
	%>
	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.min.js"></script>
	<script src="js/init.js"></script>

</body>
</html>
