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
<title>Trial Member Marks Evaluation</title>
<style type="text/css">
table, td, tr, th {
	border-collapse: collapse;
	border: 1px solid black;
}

th {
	text-align: center;
}

td, th {
	padding: 5px;
}

.th1, .th4 {
	width: 4%;
}

.th2 {
	width: 15%;
}

.th3 {
	width: 8%;
}

.th5 {
	width: 13%;
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

						GetDAOList getdaolist = new GetDAOList();
						GetDAOBool getDAOBool = new GetDAOBool();
						//List<?> memlist = getdaolist.getTrialMemberBatch(trial_id, "Batch B- Dec 16-17, 2017");
						TrialMarksEvaluation tmrEval = new TrialMarksEvaluation();

						List<?> memlist = tmrEval.marksEvalute(trial_id);
						Iterator<?> itr = memlist.iterator();
						if (itr.hasNext()) {
				%>

				<h5 class="center-align">GREENBOX MFC 2018-19</h5>
				<h6 class="center-align">TRIAL RESULT ANALYSIS</h6>
				<table class="table bordered">
					<thead>
						<tr>
							<th class="th1" rowspan="2">REG NO</th>
							<th class="th2" rowspan="2">Name</th>
							<th class="th3" rowspan="2">Position</th>
							<th class="th4" rowspan="2">Age</th>
							<th class="th4" rowspan="2">Batch</th>
							<th class="th5" rowspan="2">M1 = Trial Match Marks(Out of
								50)</th>
							<th class="th6" colspan="8">M2 = Physical Marks(Out of 10
								each)</th>

							<th class="th7" rowspan="2">Grand Total(M1+M2)</th>
						</tr>
						<tr>
							<th>100 MTR</th>
							<th>PUSH UPS</th>
							<th>3M R/W</th>
							<th>DRIBBLING</th>
							<th>PLANK</th>
							<th>SHUTTLE RUN</th>
							<th>REFLEX</th>
							<th>Total(M2)</th>
						</tr>

					</thead>


					<tbody>
						<%
							DecimalFormat df = new DecimalFormat("0.00");
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
							<th class="center-align"><%=df.format(tmr.getMatch_marks())%></th>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>


							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(1, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><%=tmr.getHundred_mtrs()%></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(2, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><%=tmr.getPushup()%></td>
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
							<td class="center-align"><%=tmr.getR_w()%></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(4, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><%=tmr.getDribbling()%></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(5, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><%=tmr.getPlank()%></td>
							<%
								} else {
							%>
							<td class="center-align"><i class="tiny material-icons"
								style="font-weight: 900;">close</i></td>
							<%
								}
							%>

							<%
								if (getDAOBool.isTrialPlayerPlayedPhysical(6, trial_id, tmr.getTmr_id())) {
							%>
							<td class="center-align"><%=tmr.getShutle_run()%></td>
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
							<td class="center-align"><%=tmr.getReflex()%></td>
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

							<th class="center-align"><%=tmr.getReflex() + tmr.getPushup() + tmr.getR_w() + tmr.getShutle_run()
									+ tmr.getPlank()%></th>
							<%
								} else {
							%>
							<th class="center-align"><%=tmr.getHundred_mtrs() + tmr.getPushup() + tmr.getR_w() + tmr.getDribbling()
									+ tmr.getPlank()%></th>
							<%
								}
							%>
							<th class="center-align"><%=df.format(tmr.getTotal())%></th>

							<%-- <td><%=df.format(tmr.getMatch_marks()) %></td>
							<td><%=tmr.getHundred_mtrs() %></td>
							<td><%=tmr.getPushup()  %></td>
							<td><%=tmr.getR_w()  %></td>
							<td><%=tmr.getDribbling() %></td>
							<td><%=tmr.getPlank()  %></td>
							<td><%=tmr.getShutle_run() %></td>
							<td><%=tmr.getReflex() %></td>
							 --%>

						</tr>

						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("TrialMemberOutput exception " + e);
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>