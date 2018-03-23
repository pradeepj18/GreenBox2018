<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="com.playsoftech.greenbox.pojo.Trials"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<!DOCTYPE html>
<html>
<head>

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
</head>
<body>
	<div class="container-fluid">
		<%
			try {
		%>
		<form action="../TrialsmemregNP" method="post"
			enctype="multipart/form-data">

			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l4 m6 s12">
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

				<div class="input-field col l2 m4 s12">
					<input id="contact" type="number" class="validate" min="0"
						maxlength="10" name="contact" required> <label
						for="reg_id">Contact No</label>
				</div>
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l2 m4 s12">
					<input id="first_name" type="text" class="validate"
						name="first_name" pattern="[A-Za-z]+" required
						title="Enter Only Letters"> <label for="first_name">First
						Name</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="middle_name" type="text" class="validate"
						name="middle_name" pattern="[A-Za-z]+" title="Enter Only Letters">
					<label for="middle_name">Middle Name</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="last_name" type="text" class="validate" name="last_name"
						pattern="[A-Za-z]+" required title="Enter Only Letters" required>
					<label for="last_name">Last Name</label>
				</div>
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l2 m4 s6">
					<select required id="gender" name="gender">
						<option value="-1" disabled selected>Choose your gender</option>
						<option value="MALE">MALE</option>
						<option value="FEMALE">FEMALE</option>

					</select> <label>Gender</label>
				</div>
				<div class="input-field col l2 m4 s6">
					<select required id="category" name="category">
						<option value="-1" disabled selected>Choose your category</option>
						<option value="PLAYER">PLAYER</option>
						<option value="OFFICIAL">OFFICIAL</option>

					</select> <label>Category</label>
				</div>
				<div class="input-field col l2 m4 s6">
					<input id="dob" type="text" class="datepicker" required> <label
						for="dob">Birth Date</label>
				</div>
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l3"></div>
				<div class="input-field col l2 m4 s12">
					<input id="address" type="text" class="validate" name="address"
						title="Enter Only Letters"> <label for="first_name">Address</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="email" type="email" class="validate" name="email"
						required> <label for="middle_name">Email Id</label>
				</div>
				<div class="input-field col l2 m4 s12">
					<input id="position" type="text" class="validate" name="position"
						required> <label for="position">Position</label>
				</div>
				<div class="col l3"></div>
			</div>

			<div class="row">
				<div class="col l3"></div>
				<div class="col l4 m8 s12">
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

				<div class="col l3"></div>
			</div>
			<div class="row">
				<div class="col l5 m4 s2"></div>
				<div class="col l4 m4 s8">
					<button class="btn waves-effect waves-light" type="submit"
						name="submit">
						Submit <i class="material-icons right">send</i>
					</button>
				</div>
				<div class="col l5 s2 m4"></div>
			</div>
		</form>
		<%
			} catch (Exception e) {
				System.out.println("Error in NP jsp : " + e);
			}
		%>
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