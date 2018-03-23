<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.pojo.Member"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="playsoftech.gb.*"%>
<%@ page import="java.util.List,java.util.Iterator,java.util.Set"%>
<%@ page import="com.playsoftech.greenbox.pojo.Trials"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.oldplayer
{
	width:100px;
	height:120px;
}
</style>
</head>
<body>
	<%
		try {
			long barcodeId = Long.parseLong(request.getParameter("barcodeId"));
	%>
	<form action="../TrialsmemregOP" method="post">
		<%
			List<?> memList = new GetDAOList().getOldMember(barcodeId);
				Iterator<?> iterator = memList.iterator();
		%>
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
		<%
			if (iterator.hasNext()) {
					Member member = (Member) iterator.next();
		%>
		<div class="row">
			<div class="col l3"></div>
			<div class="input-field col l2 m4 s12">
				<input id="first_name" type="text" class="validate"
					name="first_name" pattern="[A-Za-z]+" required
					title="Enter Only Letters" value="<%=member.getfName()%>"> <label for="first_name" class="active">First
					Name</label>
			</div>
			<div class="input-field col l2 m4 s12">
				<input id="middle_name" type="text" class="validate"
					name="middle_name" pattern="[A-Za-z]+" title="Enter Only Letters" value="<%=member.getmName()%>">
				<label for="middle_name">Middle Name</label>
			</div>
			<div class="input-field col l2 m4 s12">
				<input id="last_name" type="text" class="validate" name="last_name"
					pattern="[A-Za-z]+" required title="Enter Only Letters" required value="<%=member.getlName()%>">
				<label for="last_name" class="active">Last Name</label>
			</div>
			<div class="col l3"></div>
		</div>

		<div class="row">
			<div class="col l3"></div>
			<div class="input-field col l2 m4 s6">
				<select required id="gender" name="gender">
					<%if(member.getGender().toString().equalsIgnoreCase("MALE")){ %>
					<option value="MALE@<%=member.getBarcodeId() %>" selected>MALE</option>
					<%}else{ %>
					<option value="FEMALE" selected>FEMALE</option>
					<%} %>
				</select> <label>Gender</label>
			</div>
			<div class="input-field col l2 m4 s6">
				<select required id="category" name="category">
					<%if(member.getMemCategory().toString().equalsIgnoreCase("PLAYER")){ %>
					<option value="PLAYER" selected>PLAYER</option>
					<%}else{ %>
					<option value="OFFICIAL" selected>OFFICIAL</option>
					<%} %>
				</select> <label>Category</label>
			</div>
			<div class="input-field col l2 m4 s6">
				<input id="dob" type="text" class="datepicker" name="dob" required> <label
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
				<!-- <div class="file-field input-field">
					<div class="btn">
						<span>Browse</span> <input type="file" id="fileupload"
							name="pphoto" required />
					</div>
					<div class="file-path-wrapper">
						<input required class="file-path validate" name="newmemphoto"
							id="clear" placeholder="upload your photo" type="text">
					</div>
				</div> -->
				<img alt="" src="../<%=member.getPhoto()%>" class="oldplayer">
			</div>

			<div class="col l3"></div>
		</div>
		<%
			}
		%>
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
			System.out.println("Error in NP2 jsp : " + e);
		}
	%>
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
			format : 'yyyy/mm/dd'
		});
	</script>
</body>
</html>