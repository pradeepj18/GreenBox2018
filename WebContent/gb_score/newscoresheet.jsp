<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="DBManager.DBManager,java.sql.*,playsoftech.gb.getmyPath"%>
<%@ page import="java.text.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"
	media="print,screen,projection">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="../materialize/css/newscoresheet.css"
	media="print,screen,projection">

<title>GreenBox | ScoreSheet</title>
<style type="text/css">
.myalign {
	text-align: center !important;
}

.winner-info {
	font-size: 20px;
	font-weight: 600;
}
.print-tab
{
}
@media print {
	.myalign {
		text-align: center !important;
	}
	.winner-info {
		font-size: 20px;
		font-weight: 600;
	}
	.print-tab
	{
		display:block;
	}
}
</style>
</head>
<body>
	<%
		try {
			DBManager.loadDriver();
			int mid = Integer.parseInt(request.getParameter("mid"));
			int tourId = Integer.parseInt(request.getParameter("tourId"));
	%>
	<div class="container-fluid">

		<div class="row">
			<div class="col l12 m12 s12 center-align">
				<span class="title">GREEN BOX SCORE SHEET</span>
			</div>
		</div>
		<%
			String tnameA = "", tnameB = "";
				int tid1 = 0, tid2 = 0;
				String mtype = "";
				ResultSet tourinfo = DBManager.fetchQuery("select * from tournament where tourId=" + tourId);
				if (tourinfo.next()) {
					ResultSet pminfo = DBManager.fetchQuery(
							"select *,(select tname from team t,tourTeam tt where tt.tId=t.tId and tt.ttId=pm.tid1 and tt.tourId=pm.tourId) as tnameA,(select tname from team t,tourTeam tt where tt.tId=t.tId and tt.ttId=pm.tid2 and tt.tourId=pm.tourId) as tnameB from print_match pm where tourId="
									+ tourId + " and mid=" + mid);
					if (pminfo.next()) {
						if (pminfo.getString("mtype").equalsIgnoreCase("league"))
							mtype = "League";
						else if (pminfo.getString("mtype").equalsIgnoreCase("pqtr"))
							mtype = "PRE Quarter FINAL";
						else if (pminfo.getString("mtype").equalsIgnoreCase("qtr"))
							mtype = "Quarter FINAL";
						else if (pminfo.getString("mtype").equalsIgnoreCase("semi"))
							mtype = "SEMI FINAL";
						else if (pminfo.getString("mtype").equalsIgnoreCase("THIRDPLACE"))
							mtype = "3rdPlace";
						else if (pminfo.getString("mtype").equalsIgnoreCase("clfn"))
							mtype = "CLFN";
						else
							mtype = "FINAL";
		%>
		<%-- <div class="row">
			<div class="col l9 m9 s8 myalign">
				<span class="text-title">EVENT - </span><span class="text-info"><%=tourinfo.getString("tourName")%></span>
			</div>
			<div class="col l3 m3 s4">
				<span class="text-title">DATE - </span><span class="text-info"><%=getmyPath.getSuffixDate(pminfo.getString("mdate"))%></span>
			</div>
		</div>

		<div class="row">
			<div class="col l9 m8 s8 myalign">
				<span class="text-title">ORG.BY - </span><span class="text-info"><%=tourinfo.getString("tourOrgBy")%></span>
			</div>
			<div class="col l3 m4 s4 ">
				<span class="text-title">MATCH - </span><span class="text-info"><%=mid%></span>
			</div>
		</div>

		<div class="row">
			<div class="col l9 m8 s8 myalign">
				<span class="text-title">VENUE - </span><span class="text-info"><%=tourinfo.getString("tourVenue")%></span>
			</div>
			<div class="col l3 m4 s4 ">

				<span class="text-info"> <%=mtype%></span>
			</div>
		</div> --%>
		<div class="row">
			<div class="col l12 m12 s12 myalign">
				<span class="text-title">EVENT - </span><span class="text-info"><%=tourinfo.getString("tourName")%></span>
			</div>
			<div class="col l12 m12 s12 myalign">
				<span class="text-title">ORG.BY - </span><span class="text-info"><%=tourinfo.getString("tourOrgBy")%></span>
			</div>
			<div class="col l12 m12 s12 myalign">
				<span class="text-title">VENUE - </span><span class="text-info"><%=tourinfo.getString("tourVenue")%></span>
			</div>
		</div>

		<div class="row">
			<div class="col l6 m6 s6 right-align">
				<span class="text-title">DATE - </span><span class="text-info"><%=getmyPath.getSuffixDate(pminfo.getString("mdate"))%></span>
			</div>
			<div class="col l6 m6 s6 ">
				<span class="text-title">MATCH - </span><span class="text-info"><%=mid%></span>
				<span class="text-info"> [ <%=mtype.toUpperCase()%> ]
				</span>
			</div>
		</div>

		<div class="row">
			<div class="col l6 m6 s6 center-align">
				<span class="text-title">TEAMS - </span><span class="text-info"><%=pminfo.getString("tnameA")%></span><span
					style="color: rgba(0, 0, 0, 0.7); font-size: 12px;">&nbsp;VS&nbsp;</span><span
					class="text-info"><%=pminfo.getString("tnameB")%></span>
			</div>
			<div class="col l3 m3 s3 center-align">
				<span class="text-title">TOSS - </span><span class="text-info"><%=pminfo.getString("twon")%></span>
			</div>
			<div class="col l3 m3 s3 center-align">
				<span class="text-title">TIME - </span><span class="text-info"><%=pminfo.getString("mtime")%></span>
			</div>
		</div>
		<%
						tnameA = pminfo.getString("tnameA");
						tnameB = pminfo.getString("tnameB");
						tid1 = pminfo.getInt("tid1");
						tid2 = pminfo.getInt("tid2");
					}
				}
		%>
		<div class="row">
			<div class="col l12 m12 s12 ">
				<span class="text-title">TEAM A - </span><span class="text-info"><%=tnameA%></span>
			</div>
		</div>
		<div class="row">
			<div class="col l12 m12 s12">
				<table class="table bordered">
					<thead>
						<tr>
							<th class="thno">SRNO</th>
							<th class="thpname">PLAYER NAME</th>
							<th class="thno">JNO</th>
							<th class="thno">GNO</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
							<th class="thno">GNO</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
							<th class="thno">FNO</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
							<th class="thno">CNAME</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
						</tr>
					</thead>
					<tbody>
						<%
							ResultSet player = DBManager.fetchQuery(
										"select m.fName,m.mName,m.lName,tp.jerseyNo from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.ttId="
												+ tid1 + " and tp.tourId=" + tourId + ";");

								ResultSet goalscored = DBManager.fetchQuery(
										"select tg.gtime,tg.gtype,ttId,(select jerseyNo from tourPlayer tp where tp.barcodeId=tg.barcodeId and tourId="
												+ tourId + " and mid=" + mid + " and ttId=" + tid1
												+ ") as jno from tourGoal tg where tourId=" + tourId + " and mid=" + mid + " and ttId="
												+ tid1 + " and gtype!='OG' or (ttId=" + tid2 + " and gtype='OG');");
								ResultSet foul = DBManager.fetchQuery(
										"select tg.ftime,(select jerseyNo from tourPlayer tp where tp.barcodeId=tg.barcodeId and tourId="
												+ tourId + " and mid=" + mid + " and ttId=" + tid1
												+ ") as jno from tourFoul tg where tourId=" + tourId + " and mid=" + mid + " and ttId="
												+ tid1 + ";");
								ResultSet card = DBManager.fetchQuery(
										"select tg.cname,tg.ctime,(select jerseyNo from tourPlayer tp where tp.barcodeId=tg.barcodeId and tourId="
												+ tourId + " and mid=" + mid + " and ttId=" + tid1
												+ ") as jno from tourCard tg where tourId=" + tourId + " and mid=" + mid + " and ttId="
												+ tid1 + ";");
								ResultSet coach = DBManager.fetchQuery(
										"select fName,lName from member where barcodeId in(select barcodeId from tourCoach where tourId="
												+ tourId + " and ttId=" + tid1 + " and pStatus='active');");
								ResultSet manager = DBManager.fetchQuery(
										"select fName,lName from member where barcodeId in(select barcodeId from tourManager where tourId="
												+ tourId + " and ttId=" + tid1 + " and pStatus='active');");
								for (int i = 1; i <= 12; i++) {
						%>
						<tr>
							<td class="tdno center-align"><%=i%></td>
							<%
								if (player.next()) {
							%>
							<td class="tdpname"><%=player.getString("fName")%>&nbsp;<%=player.getString("lName")%></td>
							<td class="tdno center-align"><%=player.getInt("jerseyNo")%></td>
							<%
								} else {
							%>
							<td class="tdpname"></td>
							<td class="tdno"></td>
							<%
								}
							%>

							<td class="tdno center-align"><%=i%></td>
							<%
								if (goalscored.next()) {

											if ((goalscored.getInt("ttId") == tid1)) {
												if (goalscored.getString("gtype").equalsIgnoreCase("penalty")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(P)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else if (goalscored.getString("gtype").equalsIgnoreCase("golden")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(G)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
											}

											else	if (goalscored.getString("gtype").equalsIgnoreCase("og") && goalscored.getInt("ttId") == tid2) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(OG)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
										} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
							%>

							<td class="tdno center-align"><%=12 + i%></td>

							<%
								if (goalscored.absolute(12 + i)) {
											if ((goalscored.getInt("ttId") == tid1)) {
												if (goalscored.getString("gtype").equalsIgnoreCase("penalty")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(P)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else if (goalscored.getString("gtype").equalsIgnoreCase("golden")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(G)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
											}
											else	if (goalscored.getString("gtype").equalsIgnoreCase("og") && goalscored.getInt("ttId") == tid2) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(OG)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
										} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
										goalscored.absolute(i);
							%>
							<td class="tdno center-align"><%=i%></td>
							<%
								if (foul.next()) {
							%>

							<td class="tdno center-align"><%=foul.getInt("jno")%></td>
							<td class="tdno center-align"><%=foul.getString("ftime")%></td>
							<%
								} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
							%>
							<%
								if (card.next()) {
							%>
							<td class="tdno center-align"><%=card.getString("cname")%></td>
							<td class="tdno center-align"><%=card.getInt("jno")%></td>
							<td class="tdno center-align"><%=card.getString("ctime")%></td>
							<%
								} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
							%>
						</tr>
						<%
							}
						%>
						<tr>
							<td class="tdno center-align">13</td>
							<%
								if (coach.next()) {
							%>
							<td class="tdpname"><%=coach.getString("fName") + " " + coach.getString("lName")%></td>
							<%
								} else {
							%>
							<td class="tdpname">NA</td>
							<%
								}
									for (int i = 0; i < 13; i++) {
							%>
							<td class="tdno"></td>
							<%
								}
							%>
						</tr>
						<tr>
							<td class="tdno center-align">14</td>
							<%
								if (manager.next()) {
							%>
							<td class="tdpname"><%=manager.getString("fName") + " " + manager.getString("lName")%></td>
							<%
								} else {
							%>
							<td class="tdpname">NA</td>
							<%
								}
									for (int i = 0; i < 13; i++) {
							%>
							<td class="tdno"></td>
							<%
								}
							%>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col l12 m12 s12 ">
				<span class="text-title">TEAM B - </span><span class="text-info"><%=tnameB%></span>
			</div>
		</div>
		<div class="row">
			<div class="col l12 m12 s12">
				<table class="table bordered">
					<thead>
						<tr>
							<th class="thno">SRNO</th>
							<th class="thpname">PLAYER NAME</th>
							<th class="thno">JNO</th>
							<th class="thno">GNO</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
							<th class="thno">GNO</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
							<th class="thno">FNO</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
							<th class="thno">CNAME</th>
							<th class="thno">PNO</th>
							<th class="thno">TIME</th>
						</tr>
					</thead>
					<tbody>
						<%
							player = null;
								goalscored = null;
								foul = null;
								card = null;
								coach = null;
								manager = null;
								player = DBManager.fetchQuery(
										"select m.fName,m.mName,m.lName,tp.jerseyNo from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.ttId="
												+ tid2 + " and tp.tourId=" + tourId + ";");

								goalscored = DBManager.fetchQuery(
										"select tg.gtime,tg.gtype,ttId,(select jerseyNo from tourPlayer tp where tp.barcodeId=tg.barcodeId and tourId="
												+ tourId + " and mid=" + mid + " and ttId=" + tid2
												+ ") as jno from tourGoal tg where tourId=" + tourId + " and mid=" + mid + " and ttId="
												+ tid2 + " and gtype!='OG' or (ttId=" + tid1 + " and gtype='OG');");

								foul = DBManager.fetchQuery(
										"select tg.ftime,(select jerseyNo from tourPlayer tp where tp.barcodeId=tg.barcodeId and tourId="
												+ tourId + " and mid=" + mid + " and ttId=" + tid2
												+ ") as jno from tourFoul tg where tourId=" + tourId + " and mid=" + mid + " and ttId="
												+ tid2 + ";");
								card = DBManager.fetchQuery(
										"select tg.cname,tg.ctime,(select jerseyNo from tourPlayer tp where tp.barcodeId=tg.barcodeId and tourId="
												+ tourId + " and mid=" + mid + " and ttId=" + tid2
												+ ") as jno from tourCard tg where tourId=" + tourId + " and mid=" + mid + " and ttId="
												+ tid2 + ";");
								coach = DBManager.fetchQuery(
										"select fName,lName from member where barcodeId in(select barcodeId from tourCoach where tourId="
												+ tourId + " and ttId=" + tid2 + " and pStatus='active');");
								manager = DBManager.fetchQuery(
										"select fName,lName from member where barcodeId in(select barcodeId from tourManager where tourId="
												+ tourId + " and ttId=" + tid2 + " and pStatus='active');");
								for (int i = 1; i <= 12; i++) {
						%>
						<tr>
							<td class="tdno center-align"><%=i%></td>
							<%
								if (player.next()) {
							%>
							<td class="tdpname"><%=player.getString("fName")%>&nbsp;<%=player.getString("lName")%></td>
							<td class="tdno center-align"><%=player.getInt("jerseyNo")%></td>
							<%
								} else {
							%>
							<td class="tdpname"></td>
							<td class="tdno"></td>
							<%
								}
							%>

							<td class="tdno center-align"><%=i%></td>
							<%
								if (goalscored.next()) {

											if ((goalscored.getInt("ttId") == tid2)) {
												if (goalscored.getString("gtype").equalsIgnoreCase("penalty")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(P)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else if (goalscored.getString("gtype").equalsIgnoreCase("golden")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(G)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
											}

											else	if (goalscored.getString("gtype").equalsIgnoreCase("og") && goalscored.getInt("ttId") == tid1) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(OG)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
										} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
							%>

							<td class="tdno center-align"><%=12 + i%></td>

							<%
								if (goalscored.absolute(12 + i)) {
											if ((goalscored.getInt("ttId") == tid2)) {
												if (goalscored.getString("gtype").equalsIgnoreCase("penalty")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(P)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else if (goalscored.getString("gtype").equalsIgnoreCase("golden")) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(G)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								} else {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
											}
											else	if (goalscored.getString("gtype").equalsIgnoreCase("og") && goalscored.getInt("ttId") == tid1) {
							%>
							<td class="tdno center-align"><%=goalscored.getInt("jno")%>&nbsp;<b
								class="red-text">(OG)</b></td>
							<td class="tdno center-align"><%=goalscored.getString("gtime")%></td>
							<%
								}
										} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
										goalscored.absolute(i);
							%>
							<td class="tdno center-align"><%=i%></td>
							<%
								if (foul.next()) {
							%>

							<td class="tdno center-align"><%=foul.getInt("jno")%></td>
							<td class="tdno center-align"><%=foul.getString("ftime")%></td>
							<%
								} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
							%>
							<%
								if (card.next()) {
							%>
							<td class="tdno center-align"><%=card.getString("cname")%></td>
							<td class="tdno center-align"><%=card.getInt("jno")%></td>
							<td class="tdno center-align"><%=card.getString("ctime")%></td>
							<%
								} else {
							%>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<td class="tdno"></td>
							<%
								}
							%>
						</tr>
						<%
							}
						%>
						<tr>
							<td class="tdno center-align">13</td>
							<%
								if (coach.next()) {
							%>
							<td class="tdpname"><%=coach.getString("fName") + " " + coach.getString("lName")%></td>
							<%
								} else {
							%>
							<td class="tdpname">NA</td>
							<%
								}
									for (int i = 0; i < 13; i++) {
							%>
							<td class="tdno"></td>
							<%
								}
							%>
						</tr>
						<tr>
							<td class="tdno center-align">14</td>
							<%
								if (manager.next()) {
							%>
							<td class="tdpname"><%=manager.getString("fName") + " " + manager.getString("lName")%></td>
							<%
								} else {
							%>
							<td class="tdpname">NA</td>
							<%
								}
									for (int i = 0; i < 13; i++) {
							%>
							<td class="tdno"></td>
							<%
								}
							%>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="row">
			<div class="col l4 m4 s6 ">
				<span class="text-info">1st HALF SCORE - </span>
				<table class="table bordered">
					<thead>
						<tr>
							<%
								ResultSet fh = DBManager.fetchQuery("select (select count(gno) from tourGoal where tourId=" + tourId
											+ " and mid=" + mid + " and ttId=" + tid1
											+ " and gtype='FH') as fhscoreA,(select count(gno) from tourGoal where tourId=" + tourId
											+ " and mid=" + mid + " and ttId=" + tid2 + " and gtype='FH') as fhscoreB;");
									if (fh.next()) {
							%>
							<th class="thteam"><%=tnameA%></th>
							<th class="thscore center-align"><%=fh.getInt("fhscoreA")%></th>
							<th class="thteam"><%=tnameB%></th>
							<th class="thscore center-align"><%=fh.getInt("fhscoreB")%></th>
							<%
								}
							%>
						</tr>
					</thead>
				</table>
			</div>
			<div class="col l4 m4 s6 ">
				<span class="text-info">2nd HALF SCORE - </span>
				<table class="table bordered">
					<thead>
						<tr>
							<%
								ResultSet sh = DBManager.fetchQuery("select (select count(gno) from tourGoal where tourId=" + tourId
											+ " and mid=" + mid + " and ttId=" + tid1
											+ " and gtype='SH') as fhscoreA,(select count(gno) from tourGoal where tourId=" + tourId
											+ " and mid=" + mid + " and ttId=" + tid2 + " and gtype='SH') as fhscoreB;");
									if (sh.next()) {
							%>
							<th class="thteam"><%=tnameA%></th>
							<th class="thscore center-align"><%=sh.getInt("fhscoreA")%></th>
							<th class="thteam"><%=tnameB%></th>
							<th class="thscore center-align"><%=sh.getInt("fhscoreB")%></th>
							<%
								}
							%>
						</tr>
					</thead>
				</table>
			</div>
			<div class="col l4 m4 s6 ">
				<span class="text-info">EXTRA SCORE - </span>
				<table class="table bordered">
					<thead>
						<tr>
							<%
								ResultSet et = DBManager.fetchQuery("select (select count(gno) from tourGoal where tourId=" + tourId
											+ " and mid=" + mid + " and ttId=" + tid1
											+ " and (gtype='PENALTY' or gtype='GOLDEN' or gtype='OG')) as fhscoreA,(select count(gno) from tourGoal where tourId="
											+ tourId + " and mid=" + mid + " and ttId=" + tid2
											+ " and (gtype='PENALTY' or gtype='GOLDEN' or gtype='OG')) as fhscoreB;");
									if (et.next()) {
							%>
							<th class="thteam"><%=tnameA%></th>
							<th class="thscore center-align"><%=et.getInt("fhscoreA")%></th>
							<th class="thteam"><%=tnameB%></th>
							<th class="thscore center-align"><%=et.getInt("fhscoreB")%></th>
							<%
								}
							%>
						</tr>
					</thead>
				</table>
			</div>
			<div class="col l4 m6 s6 ">
				<span class="text-info">FULL SCORE - </span>
				<table class="table bordered">
					<thead>
						<tr>
							<%
								ResultSet full = DBManager
											.fetchQuery("select (select count(gno) from tourGoal where tourId=" + tourId + " and mid=" + mid
													+ " and ttId=" + tid1 + ") as fhscoreA,(select count(gno) from tourGoal where tourId="
													+ tourId + " and mid=" + mid + " and ttId=" + tid2 + ") as fhscoreB;");
									if (full.next()) {
							%>
							<th class="thteam"><%=tnameA%></th>
							<th class="thscore center-align"><%=full.getInt("fhscoreA")%></th>
							<th class="thteam"><%=tnameB%></th>
							<th class="thscore center-align"><%=full.getInt("fhscoreB")%></th>
							<%
								}
							%>
						</tr>
					</thead>
				</table>
			</div>
			<div class="col l4 m4 s12 center-align winner">
				<%
					ResultSet winner = DBManager
								.fetchQuery("select (select count(gno) from tourGoal where tourId=" + tourId + " and mid=" + mid
										+ " and ttId=" + tid1 + ") as scoreA,(select count(gno) from tourGoal where tourId="
										+ tourId + " and mid=" + mid + " and ttId=" + tid2 + ") as scoreB;");
						if (winner.next()) {
							if (winner.getInt("scoreA") > winner.getInt("scoreB")) {
				%>
				
				<span class="text-title print-tab">WINNER - </span> <span class="winner-info print-tab"><%=tnameA%></span>
				<%
					} else if (winner.getInt("scoreA") < winner.getInt("scoreB")) {
				%>
				<span class="text-title print-tab">WINNER - </span> <span class="winner-info print-tab"><%=tnameB%></span>
				<%
					} else {
				%>
				<span class="text-title print-tab">WINNER - </span> <span class="winner-info print-tab">DRAW</span>
				<%
					}
						}
				%>

			</div>
		</div>
		<div class="row">
			<div class="col l4 m4 s4">
				<span class="text-title">CAPTAIN SIGN - </span>
			</div>
			<div class="col l4 m4 s4">
				<span class="text-info"><%=tnameA%> : ___________________</span>
			</div>
			<div class="col l4 m4 s4">
				<span class="text-info"><%=tnameB%> : ___________________</span>
			</div>
		</div>

		<div class="row">
			<div class="col l4 m4 s4">
				<span class="text-title">REFEREE (SIGN) - </span>
			</div>
			<div class="col l4 m4 s4">
				<%
					ResultSet tmr1 = DBManager.fetchQuery(
								"select m.fName,m.lName from member m where barcodeId in(select barcodeId1 from tourmatchrefoff tmo where mid="+mid+" and tourId="
										+ tourId + ");");
						if (tmr1.next()) {
				%>
				<span class="text-info"><%=tmr1.getString("fName") + " " + tmr1.getString("lName")%>
					: ___________________</span>
				<%
					} else {
				%>
				<span class="text-info"> - : ___________________</span>
				<%
					}
				%>
			</div>
			<div class="col l4 m4 s4">
				<%
					ResultSet tmr2 =  DBManager.fetchQuery(
							"select m.fName,m.lName from member m where barcodeId in(select barcodeId2 from tourmatchrefoff tmo where mid="+mid+" and tourId="
									+ tourId + ");");
						if (tmr2.next()) {
				%>
				<span class="text-info"><%=tmr2.getString("fName") + " " + tmr2.getString("lName")%>
					: ___________________</span>
				<%
					} else {
				%>
				<span class="text-info"> - : ___________________</span>
				<%
					}
				%>
			</div>
		</div>
		<%-- <div class="row">
			<div class="col l4 m4 s4">
				<span class="text-title">TABLE OFFICIAL(SIGN) - </span>
			</div>
			<div class="col l4 m4 s4">
				<%
					ResultSet tmo1 = DBManager.fetchQuery(
								"select m.fName,m.lName from member m where barcodeId in(select barcodeId from tourmatchrefoff tmo where m.barcodeId=tmo.tmo1 and tourId="
										+ tourId + ");");
						if (tmo1.next()) {
				%>
				<span class="text-info"><%=tmo1.getString("fName") + " " + tmo1.getString("lName")%>
					: ___________________</span>
				<%
					} else {
				%>
				<span class="text-info"> - : ___________________</span>
				<%
					}
				%>
			</div>
			<div class="col l4 m4 s4">
				<%
					ResultSet tmo2 = DBManager.fetchQuery(
								"select m.fName,m.lName from member m where barcodeId in(select barcodeId from tourmatchrefoff tmo where m.barcodeId=tmo.tmo2 and tourId="
										+ tourId + ");");
						if (tmo2.next()) {
				%>
				<span class="text-info"><%=tmo2.getString("fName") + " " + tmo2.getString("lName")%>
					: ___________________</span>
				<%
					} else {
				%>
				<span class="text-info"> - : ___________________</span>
				<%
					}
				%>
			</div>
		</div> --%>
		<%-- <div class="row">
			<div class="col l4 m4 s4">
				<span class="text-title">MATCH REFEREE(SIGN) - </span>
			</div>
			<div class="col l4 m4 s4">
				<%
					ResultSet tourreff = DBManager.fetchQuery(
								"select m.fName,m.lName from member m where barcodeId in(select barcodeId from tourmatchrefoff tmo where m.barcodeId=tmo.tourRefree and tourId="
										+ tourId + ");");
						if (tourreff.next()) {
				%>
				<span class="text-info"><%=tourreff.getString("fName") + " " + tourreff.getString("lName")%>
					: ___________________</span>
				<%
					} else {
				%>
				<span class="text-info"> - : ___________________</span>
				<%
					}
				%>
			</div>

		</div> --%>
	</div>
	<%
		} catch (Exception e) {
			System.out.println("Error in new scoresheet : " + e);
		}
	%>
</body>
</html>