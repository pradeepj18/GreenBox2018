<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="DBManager.DBManager,java.sql.*,playsoftech.gb.getmyPath"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score Card</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">
<link rel="stylesheet" href="../materialize/css/scorecard.css">
</head>
<body
	onload="showgoal(1);showgoal(2);showgoal(11);showgoal(12);showgoal(13);showgoal(21);showgoal(22);showgoal(23);recentscore(4,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>);recentscore(3,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>);recentscore(2,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>);recentscore(1,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>);">
	<div class="container-fluid">
		<%
			try {
				DBManager.loadDriver();
				ResultSet rss = null, rs = null;
				long login_id=Long.parseLong(request.getParameter("login_id"));
		%>

		<div class="row">
			<%
				String photo = getmyPath.getDefaultLogo();
					rss = DBManager.fetchQuery(
							"select gender,pool,tid1,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="
									+ request.getParameter("tourId")
									+ ") as photo1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="
									+ request.getParameter("tourId")
									+ ") as tname1,tid2,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="
									+ request.getParameter("tourId")
									+ ") as photo2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="
									+ request.getParameter("tourId") + ") as tname2 from wmatch w where mid="
									+ request.getParameter("mid") + " and tourId=" + request.getParameter("tourId") + ";");

					//select gender,pool,tid1,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId=1) as photo1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId=1) as tname1,tid2,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId=1) as photo2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId=1) as tname2 from wmatch w where mid=1 and tourId=1;

					if (rss.next()) {
						if (getmyPath.isPhoto(rss.getString("photo1")))
							photo = rss.getString("photo1");
			%>
			<div class="col l4 s12 m12">

				<div class="card purple darken-1">
					<span><img alt="<%=rss.getString("tname1")%>"
						src="../<%=photo%>" class="img-circle"></span> <span
						class="card-title"><%=rss.getString("tname1")%></span> <span
						class="card-title1"><span id="refscore1"></span></span>
				</div>
				<div class="card pink darken-3">
					<div class="row" style="margin-bottom: 0;">
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc">FOUL</span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc">CARD [Y]</span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc">CARD [R]</span>
						</div>
					</div>
					<div class="row" style="margin-bottom: 0;">
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc"><span id="refscore11"></span></span>
						</div>

						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc"><span id="refscore12"></span></span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc"><span id="refscore13"></span></span>
						</div>
					</div>
				</div>
				<ul class="collapsible" data-collapsible="accordion">
					<%
						String p1 = "";
								rs = DBManager.fetchQuery(
										"select m.barcodeId,m.fName,m.mName,m.lName,tp.jerseyNo,ttId,SUBSTRING_INDEX(photo,'/',-3) as photo1 from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.ttId="
												+ rss.getInt("tid1") + " and tp.tourId=" + request.getParameter("tourId") + ";");

								//select m.barcodeId,m.fName,m.mName,m.lName,tp.jerseyNo,ttId,SUBSTRING_INDEX(photo,'/',-3) as photo1 from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.ttId=4 and tp.tourId=1;

								if (rs.next()) {
									rs.beforeFirst();
									while (rs.next()) {

										if (!getmyPath.isPhoto(rs.getString("photo1")))
											p1 = getmyPath.getDefaultMemberimg();
										else
											p1 = rs.getString("photo1");
										//	System.out.println(rs.getString("photo1"));
					%>
					<li id="p<%=rs.getInt("barcodeId")%>">
						<div class="collapsible-header">

							<div class="row" style="margin-bottom: 0px;">
								<div class="col l2 s2 m2">
									<img alt="<%=rs.getString("fName")%>" src="../<%=p1%>"
										class="img-circle">
								</div>
								<div class="col l5 s5 m5">
									<span class="pname"><%=rs.getString("fName")%></span>
								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet goal = DBManager
																.fetchQuery("select count(gno) as pgoal from tourGoal where tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (goal.next()) {
									%>
									<span class="badge purple"><span
										id="pgfc11<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc11<%=rs.getInt("barcodeId")%>"><%=goal.getInt("pgoal")%></span></span></span>
									<%
										}
									%>
								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet foul = DBManager
																.fetchQuery("select count(fno) as pfoul from tourFoul where tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (foul.next()) {
									%>
									<span class="badge blue lighten-1"><span
										id="pgfc12<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc12<%=rs.getInt("barcodeId")%>"><%=foul.getInt("pfoul")%></span></span></span>
									<%
										}
									%>

								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet ycard = DBManager
																.fetchQuery("select count(cno) as py from tourCard where cname='YELLOW' and tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (ycard.next()) {
									%>
									<span class="badge amber accent-4"><span
										id="pgfc13<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc13<%=rs.getInt("barcodeId")%>"><%=ycard.getInt("py")%></span></span></span>
									<%
										}
									%>
								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet rcard = DBManager
																.fetchQuery("select count(cno) as pr from tourCard where cname='RED' and tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (rcard.next()) {
									%>
									<span class="badge red"><span
										id="pgfc14<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc14<%=rs.getInt("barcodeId")%>"><%=rcard.getInt("pr")%></span></span></span>
									<%
										}
									%>
								</div>
								<div class="col l1 s1 m1">
									<span class="pjno"><%=rs.getInt("jerseyNo")%></span>
								</div>
							</div>
						</div>
						<div class="collapsible-body">
							<div class="row">
								<div class="col l4 m4 s4">
									<a class="waves-effect waves-light btn purple"
										onclick="addGFC(1,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid1")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,1,<%=login_id%>)">GOAL</a>
								</div>
								<div class="col l4 m4 s4">
									<a class="waves-effect waves-light btn blue lighten-1"
										onclick="addGFC(2,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid1")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,1,<%=login_id%>)">FOUL</a>
								</div>
								<!-- OWN GOAL START-->
								<div class="col l4 m4 s4">
									<a class="waves-effect waves-light btn black lighten-1"
										onclick="addGFC(5,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid1")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,2,<%=login_id%>)"><b>OG<sup
											class="red-text">*</sup></b></a>
								</div>
								<!-- OWN GOAL END -->
								<div class="col l6 m6 s6">
									<br> <a
										class="waves-effect waves-light btn amber accent-4"
										onclick="addGFC(3,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid1")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,1,<%=login_id%>)">CARD
										[Y] </a>
								</div>
								<div class="col l6 m6 s6">
									<br> <a class="waves-effect waves-light btn red"
										onclick="addGFC(4,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid1")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,1,<%=login_id%>)">CARD
										[R]</a>
								</div>
							</div>
						</div>
					</li>
					<%
						}
								} else {
					%>
					<li><div class="row">
							<div class="col l12 m12 s12">
								<span class="gfc black-text">Players not found</span>
							</div>
						</div></li>
					<%
						}
					%>
				</ul>
			</div>

			 <div class="col l4 s12 m12 center-align">
				<div class="card pink darken-3">
					<span class="card-minfo">MATCH - <%=request.getParameter("mid")%></span><br>
					<span class="card-minfo"> POOL - <%=(char) (rss.getInt("pool") + 64)%></span>

				</div>
				<div class="row">
					<div class="input-field col l12 s12 m12">
						<select id="gtype" name="gtype" class="browser-default">
							<option value="-1" disabled selected>Choose your
								Category</option>
							<option value="1">FIRST HALF</option>
							<option value="2">SECOND HALF</option>
							<option value="G">GOLDEN GOAL</option>
							<option value="P">PENALTY</option>

						</select>
					</div>
				</div>
				<div class="row">
					<div class="col l5 m5 s5" style="padding: 0;">
						<div class="card pink darken-3">
							<input class="form-control input-lg card-timer right-align"
								type="text" id="mintime" name="mintime" value="10">
						</div>
					</div>
					<div class="col l2 m2 s2" style="padding: 0;">
						<div class="card pink darken-3 noselect">
							<input
								class="form-control input-lg card-timer center-align disabled"
								type="text" value=":">
						</div>
					</div>
					<div class="col l5 m5 s5" style="padding: 0;">
						<div class="card pink darken-3">
							<input class="form-control input-lg card-timer left-align"
								type="text" id="sectime" name="sectime" value="00">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col l4 m4 s4" style="text-align: center;">
						<a data-target="mymodal" class="waves-effect waves-light btn green darken-4" id="start"
							onclick="start();updateTimer();" >START</a>
					</div>
					<div class="col l4 m4 s4" style="text-align: center;">
						<a data-target="mymodal" class="waves-effect waves-light btn orange darken-1 disabled"
							id="stop" onclick="stop();updateTimer();" >STOP</a>
					</div>
					<div class="col l4 m4 s4" style="text-align: center;">
						<a data-target="mymodal" class="waves-effect waves-light btn red darken-4 disabled"
							id="reset" onclick="reset();" >RESET</a>
					</div>
				</div>
				<div class="row">
					<div class="col l12 s12 m12">
						<h5>RECENT SCORES</h5>
					</div>
				</div>

				<ul class="collapsible" data-collapsible="accordion">
					<%
						String a[] = {"GOAL", "FOUL", "CARD [Y]", "CARD [R]"};
								for (int i = 0; i < 4; i++) {
					%>
					<li>
						<div class="collapsible-header">
							<span class="scorer"><%=a[i]%> SCORER</span>

						</div>
						<div class="collapsible-body"
							style="max-height: 250px; overflow-y: scroll;">
							<div class="row" style="margin-bottom: 10px;">
								<div class="col l1 s2 m2" style="padding: 0;">
									<span style="font-weight: 600;">JNO</span>
								</div>
								<div class="col l5 s5 m5" style="padding: 0;">
									<span style="font-weight: 600;">PLAYER NAME</span>
								</div>
								<div class="col l5 s5 m5" style="padding: 0;">
									<span style="font-weight: 600;">TEAM NAME</span>
								</div>
							</div>
							<hr class="white-text">
							<div id="recent<%=i + 1%>"></div>

						</div>
					</li>
					<%
						}
					%>

				</ul>
				<div class="row">
					<div class="col l2 s2 m2"></div>
					<div class="col l8 s8 m8">
						<a class="waves-effect waves-light btn gen pink darken-3 disabled"
							onclick="generate(<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,2,<%=login_id%>)"><i
							class="material-icons left">print</i>SCORESHEET</a>
					</div>
					<div class="col l2 s2 m2"></div>
				</div>

			</div> 
			<!-- ----------------------------------------------TEAM B----------------------------------------------------- -->
		 <div class="col l4 s12 m12">
				<div class="card purple darken-1">
					<%
						photo = getmyPath.getDefaultLogo();
								if (getmyPath.isPhoto(rss.getString("photo2")))
									photo = rss.getString("photo2");
					%>
					<span><img alt="<%=rss.getString("tname2")%>"
						src="../<%=photo%>" class="img-circle"></span> <span
						class="card-title"><%=rss.getString("tname2")%></span> <span
						class="card-title1"><span id="refscore2"></span></span>
				</div>
				<div class="card pink darken-3">
					<div class="row" style="margin-bottom: 0;">
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc">FOUL</span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc">CARD [Y]</span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc">CARD [R]</span>
						</div>
					</div>
					<div class="row" style="margin-bottom: 0;">
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc"><span id="refscore21"></span></span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc"><span id="refscore22"></span></span>
						</div>
						<div class="col l4 m4 s4" style="text-align: center;">
							<span class="gfc"><span id="refscore23"></span></span>
						</div>
					</div>
				</div>
				<ul class="collapsible" data-collapsible="accordion">
					<%
						p1 = "";
								rs = null;
								rs = DBManager.fetchQuery(
										"select m.barcodeId,m.fName,m.mName,m.lName,tp.jerseyNo,ttId,SUBSTRING_INDEX(photo,'/',-3) as photo1 from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.ttId="
												+ rss.getInt("tid2") + " and tp.tourId=" + request.getParameter("tourId") + ";");
								if (rs.next()) {
									rs.beforeFirst();
									while (rs.next()) {

										if (!getmyPath.isPhoto(rs.getString("photo1")))
											p1 = getmyPath.getDefaultMemberimg();
										else
											p1 = rs.getString("photo1");
					%>
					<li id="p<%=rs.getInt("barcodeId")%>">
						<div class="collapsible-header">

							<div class="row" style="margin-bottom: 0px;">
								<div class="col l2 s2 m2">
									<img alt="<%=rs.getString("fName")%>" src="../<%=p1%>"
										class="img-circle">
								</div>
								<div class="col l5 s5 m5">
									<span class="pname"><%=rs.getString("fName")%></span>
								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet goal = DBManager
																.fetchQuery("select count(gno) as pgoal from tourGoal where tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (goal.next()) {
									%>
									<span class="badge purple"><span
										id="pgfc21<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc21<%=rs.getInt("barcodeId")%>"><%=goal.getInt("pgoal")%></span></span></span>
									<%
										}
									%>
								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet foul = DBManager
																.fetchQuery("select count(fno) as pfoul from tourFoul where tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (foul.next()) {
									%>
									<span class="badge blue lighten-1"><span
										id="pgfc22<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc22<%=rs.getInt("barcodeId")%>"><%=foul.getInt("pfoul")%></span></span></span>
									<%
										}
									%>

								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet ycard = DBManager
																.fetchQuery("select count(cno) as py from tourCard where cname='Y' and tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (ycard.next()) {
									%>
									<span class="badge amber accent-4"><span
										id="pgfc23<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc23<%=rs.getInt("barcodeId")%>"><%=ycard.getInt("py")%></span></span></span>
									<%
										}
									%>
								</div>
								<div class="col l1 s1 m1">
									<%
										ResultSet rcard = DBManager
																.fetchQuery("select count(cno) as pr from tourCard where cname='R' and tourId="
																		+ request.getParameter("tourId") + " and mid=" + request.getParameter("mid")
																		+ " and barcodeId=" + rs.getInt("barcodeId") + ";");
														if (rcard.next()) {
									%>
									<span class="badge red"><span
										id="pgfc24<%=rs.getInt("barcodeId")%>"><span
											id="hpgfc24<%=rs.getInt("barcodeId")%>"><%=rcard.getInt("pr")%></span></span></span>
									<%
										}
									%>
								</div>
								<div class="col l1 s1 m1">
									<span class="pjno"><%=rs.getInt("jerseyNo")%></span>
								</div>
							</div>
						</div>
						<div class="collapsible-body">
							<div class="row">
								<div class="col l4 m4 s4">
									<a class="waves-effect waves-light btn purple"
										onclick="addGFC(1,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid2")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,2,<%=login_id%>)">GOAL</a>
								</div>
								<div class="col l4 m4 s4">
									<a class="waves-effect waves-light btn blue lighten-1"
										onclick="addGFC(2,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid2")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,2,<%=login_id%>)">FOUL</a>
								</div>
								<!-- OWN GOAL START-->
								<div class="col l4 m4 s4">
									<a class="waves-effect waves-light btn black lighten-1"
										onclick="addGFC(5,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid2")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,1,<%=login_id%>)"><b>OG<sup
											class="red-text">*</sup></b></a>
								</div>
								<!-- OWN GOAL END -->
								<div class="col l6 m6 s6">
									<br> <a
										class="waves-effect waves-light btn amber accent-4"
										onclick="addGFC(3,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid2")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,2,<%=login_id%>)"><span
										style="">CARD [Y]</span></a>
								</div>
								<div class="col l6 m6 s6">
									<br> <a class="waves-effect waves-light btn red"
										onclick="addGFC(4,<%=rs.getInt("jerseyNo")%>,<%=rss.getInt("tid2")%>,<%=request.getParameter("tourId")%>,<%=request.getParameter("mid")%>,<%=rs.getInt("barcodeId")%>,2,<%=login_id%>)">CARD
										[R]</a>
								</div>
							</div>
						</div>
					</li>
					<%
						}
								} else {
					%>
					<li><div class="row">
							<div class="col l12 m12 s12">
								<span class="gfc black-text">Players not found</span>
							</div>
						</div></li>
					<%
						}
					%>
				</ul>
			</div> 
			<%
				}
			%>
		</div>
		<div id="gfc"></div>
		<!-- ---------------------MODEL----------------------- -->
		<div id="mymodal" class="modal msgmodal">
			<div class="modal-content">
				<div class="row">
					<h5 id="msg"></h5>
				</div>
			</div>
			<div class="modal-footer">
				<button onclick="closeModal();"
					class="modal-action waves-effect waves-green btn-flat">OK</button>

			</div>
		</div>
		<!-- ---------------------------------------------------- -->
		<%
			} catch (Exception e) {
				System.out.println("scorecard : " + e);
				e.printStackTrace();
			}
		
		%>
	</div>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="../materialize/js/materialize.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.collapsible').collapsible();
			 $('select').material_select();
			 $('.noselect').click(function(){
				 $(this).mousedown(function(){return false;}); 
			 });
		});
		
		var duration = 0;
		var timestatus=false;
		var minutes,seconds,min,refreshInterval,timer;
		function startTimer()
		{
			timer = duration-1;
			
			refreshInterval = setInterval(refreshTimer,1000);
		}
		function refreshTimer()
		{
			minutes = parseInt(timer/60,10);
			seconds = parseInt(timer%60,10);

			minutes = minutes < 10?"0"+minutes:minutes;
			seconds = seconds < 10?"0"+seconds:seconds;
			if(--timer < 0)
			{
				timer = 0;
				duration = 0;
				document.getElementById("mintime").value = "00";
				document.getElementById("sectime").value = "00";
				clearTimeout(refreshInterval);
			}
			document.getElementById("mintime").value = minutes;
			document.getElementById("sectime").value = seconds;
			
			if($("#gtype").val()!=1 && minutes==0 && seconds==1)
				{
					$('.gen').removeClass('disabled');
				}
			if(minutes==0 && seconds==0)
			{
				$("#start").removeClass("disabled");
				$("#stop").addClass("disabled");
				$("#reset").addClass("disabled");
				document.getElementById("mintime").disabled = false;
				document.getElementById("sectime").disabled = false;
			}
		}
		function start()
		{
		
			if((($("#mintime").val()==="00" || $("#mintime").val()==="0") && ($("#sectime").val()==="00" || $("#sectime").val()==="0")))
			{
				alert("first set valid time");
				return false;
			}
			if(document.getElementById("gtype").value==="-1")
			{
				alert("Choose match category");
				return false;
			}
			else if((document.getElementById("mintime").value) < 0 || (document.getElementById("sectime").value) < 0)
			{
				alert("Negative time is not allowed");
				return false;
			}
			else 
			{
				var	mintime = document.getElementById("mintime").value;
				var	sectime = document.getElementById("sectime").value;
				
				if((mintime.indexOf(' ') >= 0) || (sectime.indexOf(' ') >= 0))
				{
					alert("Enter valid Time.");
					return;
				}
				else if((!isNaN(sectime))==true && (!isNaN(mintime))==true)
				{
					$("#start").addClass("disabled");
					$("#stop").removeClass("disabled");
					$("#reset").addClass("disabled");

					document.getElementById("mintime").disabled = true;
					document.getElementById("sectime").disabled = true;
					 
					min = (parseInt(mintime*60,10))+parseInt(sectime,10);
			
					duration = min;
					startTimer();
					timestatus=true;
				}
				else
					{
					alert('Enter valid time.');
					}
			}
		}
		function stop()
		{
			$("#start").removeClass("disabled");
			$("#stop").addClass("disabled");
			$("#reset").removeClass("disabled");
			
			clearTimeout(refreshInterval);
			timestatus=false;
		}
		function reset()
		{
			clearTimeout(refreshInterval);
			$("#start").removeClass("disabled");
			$("#stop").addClass("disabled");
			$("#reset").addClass("disabled");
			
			document.getElementById("mintime").disabled = false;
			document.getElementById("sectime").disabled = false;
			
			document.getElementById("mintime").value = "00";
			document.getElementById("sectime").value = "00";
			
			document.getElementById("mintime").focus();
			timestatus=false;
		}
		function setCookie(value)
		{
			d = window.location.hostname;
			document.cookie = "timer=" + value + ";domain="+ d +";path=/";
			return true;
		}
		function updateTimer()
		{
			var t = document.getElementById("mintime").value;
			var t1 = document.getElementById("sectime").value;
			
			setCookie(t+t1);
			setTimeout(updateTimer,1000);
			
		}
		
		function getParameter(variable)
		{
		       var query = window.location.search.substring(1);
		       var vars = query.split("&");
		       for (var i=0;i<vars.length;i++) {
		               var pair = vars[i].split("=");
		               if(pair[0] == variable){return pair[1];}
		       }
		       return(false);
		}
		function showgoal(flag)
		{
		
			var tourId=getParameter("tourId");
			var mid=getParameter("mid");
		//	$('#refscore'+flag).load('RefreshScoreAjx.jsp?tourId='+tourId+'&mid='+mid+'&flag='+flag);
			$.ajax({
				method : "post",
				url : "RefreshScoreAjx.jsp",
				data : ({
					tourId : tourId,
					mid : mid,
					flag : flag
				}),
				async : false,
				cache : false,
				beforeSend : function() {},
				complete : function() {},
				success : function(msg) {
					$('#refscore'+flag).html(msg);
				}
			});
		}
		function playergfc(uid,tourId,mid,bid,tuid)
		{
			//$('#pgfc'+tuid+uid+bid).load("playergfcAjx.jsp?tourId="+tourId+"&mid="+mid+"&bid="+bid+"&uid="+uid+"&tuid="+tuid);
			if(uid==5)
				{
				uid=1;
				if(tuid==1)
					tuid=2;
				else
					tuid=1;
				}
			
				
			$.ajax({
				method : "post",
				url : "playergfcAjx.jsp",
				data : ({
					tourId : tourId,
					mid : mid,
					bid : bid,
					uid : uid,
					tuid: tuid
				}),
				async : false,
				cache : false,
				beforeSend : function() {},
				complete : function() {},
				success : function(msg) {
					$('#pgfc'+tuid+uid+bid).html(msg);
				}
			});
		}
		
		function recentscore(flag,tourId,mid)
		{
			//	$('#recent'+flag).load("recentrefAjx.jsp?flag="+flag+"&tourId="+tourId+"&mid="+mid);

			if(flag==5)
							flag=1;
			$.ajax({
					method : "post",
					url : "recentrefAjx.jsp",
					data : ({
						tourId : tourId,
						mid : mid,
						flag : flag
					}),
					async : false,
					cache : false,
					beforeSend : function() {},
					complete : function() {},
					success : function(msg) {
						
						$('#recent'+flag).html(msg);
					}
				});
		}
		function addGFC(uid,jno,tid,tourId,mid,bid,tuid,login_id)
		{
			var timer=document.getElementById("mintime").value+":"+document.getElementById("sectime").value;
			var gtype=document.getElementById("gtype").value;
			if((($("#mintime").val()==="00" || $("#mintime").val()==="0") && ($("#sectime").val()==="00" || $("#sectime").val()==="0")))
			{
				alert("first set valid time");
				return false;
			}
			if(document.getElementById("gtype").value==="-1")
			{
				alert("Choose match category");
				return false;
			}
			else if(timestatus==false)
			{
				alert("Time is stoped.first start the time");
				return false;
			}
			if(uid==5){
				gtype="OG";}
				
		//	$('#gfc').load("addGFCAjx.jsp?jno="+jno+"&tid="+tid+"&tourId="+tourId+"&mid="+mid+"&time="+timer+"&gtype="+gtype+"&bid="+bid+"&uid="+uid);
			$.ajax({
					method : "post",
					url : "addGFCAjx.jsp",
					data : ({
						tourId : tourId,
						mid : mid,
						jno : jno,
						tid : tid,
						time : timer,
						gtype : gtype,
						bid : bid,
						uid : uid,
						login_id:login_id
					}),
					async : false,
					cache : false,
					beforeSend : function() {},
					complete : function() {},
					success : function(msg) {
						$('#gfc').html(msg);
					}
				});
			showgoal(tuid);
			showgoal(tuid+""+(uid-1));
			playergfc(uid,tourId,mid,bid,tuid);
			recentscore(uid,tourId,mid);
		
		}
		function delgfc(cno,tourId,mid,uid,bid)
		{
			if (confirm("Are You Sure To Delete..?") == true) 
				{
				//	$('#gfc').load("delGFCAjx.jsp?tourId="+tourId+"&mid="+mid+"&cno="+cno+"&uid="+uid+"&bid="+bid);
					$.ajax({
						method : "post",
						url : "delGFCAjx.jsp",
						data : ({
							tourId : tourId,
							mid : mid,
							cno : cno,
							bid : bid,
							uid : uid
						}),
						async : false,
						cache : false,
						beforeSend : function() {},
						complete : function() {},
						success : function(msg) {
							$('#gfc').html(msg);
						}
					});
					
					showgoal(1);showgoal(2);showgoal(1+""+(uid-1));showgoal(2+""+(uid-1));
					playergfc(uid,tourId,mid,bid,1);playergfc(uid,tourId,mid,bid,2);
					recentscore(uid,tourId,mid);	
				}
			
		}
		
		function generate(tourId,mid,uid,login_id)
		{
			$.ajax({
				method : "post",
				url : "updatetossAjx.jsp",
				data : ({
					tourId : tourId,
					mid : mid,
					uid : uid,
					login_id:login_id
				}),
				async : false,
				cache : false,
				beforeSend : function() {},
				complete : function() {},
				success : function(msg) {
					window.open("newscoresheet.jsp?mid="+mid+"&tourId="+tourId+"&uid="+uid+"&login_id="+login_id,"Score Sheet");
					
				}
			});
			
		}
	
		
		function callModal(msg) {
			$('.modal').modal({
				width : '60%',
				dismissible : false, 
				opacity : .8,
				inDuration : 200, 
				outDuration : 200, 
				startingTop : '50%',
				endingTop : '30%',
				ready : function(modal, trigger) {
					document.getElementById("msg").innerHTML = msg;
			//	alert(modal+"  "+trigger);
				},
				 complete: function() {
					
				 //alert("++");       
				 }
			});
	}
		function closeModal()
		{
			  $('#mymodal').modal('close');
		}
		
	</script>
</body>
</html>