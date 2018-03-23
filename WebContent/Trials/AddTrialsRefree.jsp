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

<script type="text/javascript">
	window.onload = function() {
		var fileUpload = document.getElementById("fileupload");
		fileUpload.onchange = function() {
			if (typeof (FileReader) != "undefined") {
				var myimg = document.getElementById("myimg");
				myimg.innerHTML = "";
				var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.png)$/;
				for (var i = 0; i < fileUpload.files.length; i++) {
					var file = fileUpload.files[i];
					if (regex.test(file.name.toLowerCase())) {
						var reader = new FileReader();
						reader.onload = function(e) {
							var img = document.createElement("img");
							img.height = "100";
							img.width = "150";
							img.src = e.target.result;
							myimg.appendChild(img);
						}
						reader.readAsDataURL(file);
					} else {
						document.getElementById("fileupload").value = "";
						alert(file.name + " is not a valid image file.");
						myimg.innerHTML = "";
						return false;
					}
				}
			} else {
				alert("This browser does not support HTML5 FileReader.");
			}
		}
	};
</script>
<style type="text/css">
body
{
	overflow-x:hidden;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<form action="../AddTrialsRefree" method="post"
			enctype="multipart/form-data">
			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l2 m4 s12">
					<input id="first_name" type="text" class="validate" name="first_name" required> <label
						for="first_name">First Name</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="middle_name" type="text" class="validate" name="middle_name"> <label
						for="middle_name">Middle Name</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="last_name" type="text" class="validate" name="last_name" required> <label
						for="last_name">Last Name</label>
				</div>
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l2 m4 s12">
					<input id="dob" type="text" class="datepicker" required> <label
						for="dob">Birth Date</label>
				</div>
				<div class="col m6 s12 l3">
				<div class="input-field">
					<select required id="trials_id" name="trial_id">
						<option value="-1" disabled selected>Choose Trial Name</option>

						<%
							try {
									GetDAOList getdaolist = new GetDAOList();
									List<?> triallist = getdaolist.getTrials();
									Iterator<?> itr = triallist.iterator();
									while (itr.hasNext()) {
										Trials trials = (Trials) itr.next();
						%>
						<option value="<%=trials.getTrial_id()%>"><%=trials.getTrial_name()%></option>
						<%
							}
								} catch (Exception e) {
									System.out.println("Error in List : " + e);
								}
						%>


					</select> <label>Trial Name</label>
				</div>
				</div>
				
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l3"></div>
				<div class="col l3 m8 s12">
					<div class="file-field input-field">
						<div class="btn">
							<span>Browse</span> <input type="file" id="fileupload"
								name="pphoto" required />
						</div>
						<div class="file-path-wrapper">
							<input required class="file-path validate" name="newmemphoto"
								id="clear" placeholder="upload your photo" type="text">
						</div>
					</div>
				</div>
				<div class="col l2 m4 s12 center-align">
					<div id="myimg"></div>
				</div>
				<div class="col l3">
				</div>
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
			max : new Date((new Date()).valueOf()),
			selectMonths : true,
			selectYears : 50,
			today : 'Today',
			clear : 'Clear',
			close : 'Close',
			closeOnSelect : true,
			formatSubmit : 'yyyy/mm/dd',
			format : 'dd mmmm yyyy'
		});
	</script>
</body>
</html>