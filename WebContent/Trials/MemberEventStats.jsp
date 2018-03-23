<%@page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.playsoftech.greenbox.dao.AddTrialsDAO"%>
<%@page import="playsoftech.gb.getmyPath"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@page import="com.playsoftech.greenbox.dao.TrialMarksEvaluation"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" media="print,screen">
<link type="text/css" rel="stylesheet"
	href="../materialize/css/materialize.min.css"
	media="print,screen,projection" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="../materialize/css/playerlist.css"
	media="print,screen,projection" />
<title>Trial Member Event Status</title>
<style type="text/css">
table, td, tr, th {
	border-collapse: collapse;
	border: 1px solid black;
}
th
{
	text-align:center;
}
td, th {
	padding: 5px;
}
.th1,.th4
{
	width:4%;
}
.th2
{
	width:15%;
}
.th3
{
	width:8%;
}
.th5
{
	width:13%;
}
@media print {
	@page {
		size: landscape;
		margin: 0;
	}
	table, td, tr, th {
		border-collapse: collapse;
		border: 1px solid black;
	}
	td, th {
		padding: 5px;
	}
}
</style>
</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col s12 m12 s12">
			
				<%
					try {
						long trial_id = new AddTrialsDAO().getmaxTrialsSeasonInfoID();
						int cnt = 1;
						GetDAOList getdaolist = new GetDAOList();
						GetDAOBool getDAOBool = new GetDAOBool();
						//List<?> memlist = getdaolist.getTrialMemberBatch(trial_id, "Batch B- Dec 16-17, 2017");
						TrialMarksEvaluation tmrEval = new TrialMarksEvaluation();
						
						
						List<?> memlist = tmrEval.marksEvalute(trial_id);
						Iterator<?> itr = memlist.iterator();
						if (itr.hasNext()) {
				%>

				<h5 class="center-align">GREENBOX MFC 2018-19</h5>
				<h6 class="center-align">TRIAL PLAYER PLAYING STATUS</h6>
				<table class="table bordered">
					<thead>
						<tr>
							<th class="th1" rowspan="2">Sr No</th>
							<th class="th2" rowspan="2">Name</th>
							<th class="th3" rowspan="2">Position</th>
							<th class="th4" rowspan="2">Age</th>
							<th class="th4" rowspan="2">Batch</th>
							<th class="th5" rowspan="2">Trial Match</th>
							<th class="th6" colspan="7">Physical Events</th>
						</tr>
						<tr>
							<th>100 MTR</th>
							<th>PUSH UPS</th>
							<th>3M R/W</th>
							<th>DRIBBLING</th>
							<th>PLANK</th>
							<th>SHUTTLE RUN</th>
							<th>REFLEX</th>
						</tr>

					</thead>


	<tbody>
					 <%
					 	
						while (itr.hasNext()) {
							TrialMarksEvaluation tmr = (TrialMarksEvaluation) itr.next();
					%>
				
						<tr>
							<td><%=tmr.getTmr_id()%></td>
							<td><%=tmr.getFname().toUpperCase()%>&nbsp;<%=tmr.getLname().toUpperCase()%>&nbsp;</td>
							<td><%=tmr.getPosition().toUpperCase()%></td>
							<td><%=tmr.getAge() %></td>
							<td><%=tmr.getBatch().split("-")[0].toUpperCase().split(" ")[1] %>
							
							<%if(getDAOBool.isTrialPlayerPlayedMatch(trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"><i class="tiny material-icons">check</i> </td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(1, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"><i class="tiny material-icons">check</i> </td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(2, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"> <i class="tiny material-icons">check</i> </td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(3, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"> <i class="tiny material-icons">check</i></td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(4, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(5, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(6, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
							
							<%if(getDAOBool.isTrialPlayerPlayedPhysical(7, trial_id, tmr.getTmr_id())){ %>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%}else{ %>
							<td class="center-align"><i class="tiny material-icons" style="font-weight:900;">close</i></td>
							<%} %>
						</tr>
				
					<%
						}
					%> 
						</tbody>
						
						
				<%-- 		<tbody>
						<%
							while (itr.hasNext()) {
										TrialMarksEvaluation tmr = (TrialMarksEvaluation) itr.next();
						%>

						<tr>
							<td><%=tmr.getTmr_id()%></td>
							<td><%=tmr.getFname().toUpperCase()%>&nbsp;<%=tmr.getLname().toUpperCase()%>&nbsp;</td>
							<td><%=tmr.getPosition().toUpperCase()%></td>
							<td><%=tmr.getAge()%></td>
							<td><%=tmr.getBatch().split("-")[0].toUpperCase().split(" ")[1]%>

								<%
									if (getDAOBool.isTrialPlayerPlayedMatch(trial_id, tmr.getTmr_id())) {
								%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (tmr.getPosition().equalsIgnoreCase("goalkeeper")) {
							%>
							<td class="center-align">-</td>
							<%
								} else {
							%>
							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(1, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>
							<%
								}
							%>
							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(2, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(3, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>
							<%
								if (tmr.getPosition().equalsIgnoreCase("goalkeeper")) {
							%>
							<td class="center-align">-</td>
							<%
								} else {
							%>
							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(4, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(5, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>
							<%
								if (tmr.getPosition().equalsIgnoreCase("goalkeeper")) {
							%>
							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(6, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(7, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><i class="tiny material-icons">check</i></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>
							<%
								} else {
							%>
							<td class="center-align">-</td>
							<td class="center-align">-</td>
							<%
								}
							%>
						</tr>

						<%
							}
						%>
					</tbody> --%>
				</table>
				<%
					}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("TrialMemberOutput exception "+e);
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>