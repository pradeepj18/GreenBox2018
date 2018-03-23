<%@page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@page import="playsoftech.gb.getmyPath"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.playsoftech.greenbox.dao.GetDAOList"%>
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
<title>Petanque Score Sheet</title>
<style type="text/css">
table, td, tr, th {
	border-collapse: collapse;
	border: 1px solid black;
}

td, th {
	padding: 5px;
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
			<%-- <div class="col s12 m12 s12">
				<%
					try {
						long trial_id = 3;
						int cnt = 1;
						GetDAOList getdaolist = new GetDAOList();
						List<?> memlist = getdaolist.getTrialMemberList(trial_id);
						Iterator<?> itr = memlist.iterator();
						if (itr.hasNext()) {
				%>

				<h5 class="center-align">GREENBOX MFC 2018-19</h5>
				<h6 class="center-align"><%=getmyPath.getStringDate("2018-12-09")%>&nbsp;to&nbsp;<%=getmyPath.getStringDate("2018-12-10")%></h6>
				<h6 class="center-align">PLAYERS LIST</h6>
				<table class="table bordered">
					<thead>
						<tr>
							<th>Sr No</th>
							<th>REG</th>
							<th>Name</th>
							<th>Position</th>
							<th>Age</th>
							<th>Email</th>
							<th>ContactNO</th>
							<th>Batch</th>
						</tr>
					</thead>



					<%
						while (itr.hasNext()) {
									TrialMemberRegistration tmr = (TrialMemberRegistration) itr.next();
					%>
					<tbody>
						<tr>
							 <td><%=cnt++%></td>
							<td><%=tmr.getTmr_id() %></td>
							<td><%=tmr.getFname().toUpperCase()%>&nbsp;<%=tmr.getLname().toUpperCase()%>&nbsp;</td>
							<td><%=tmr.getPosition().toUpperCase()%></td>
							<td><%=getmyPath.getAge(tmr.getDob())%></td>
							<td><%=tmr.getEmail().toLowerCase() %></td>
							<td><%=tmr.getMobileno() %></td>
							<td><%=tmr.getBatch().toUpperCase().toString().split("-")[0] %></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
				<%
					}
					} catch (Exception e) {
						e.getMessage();
					}
				%>
			</div>
 --%>
		</div> 
		
		 <div class="row">
			<div class="col s12 m12 s12">
				<%
					try {
						long trial_id = 3;
						int cnt = 1;
						GetDAOList getdaolist = new GetDAOList();
						GetDAOBool getDAOBool = new GetDAOBool();
						//List<?> memlist = getdaolist.getTrialMemberBatch(trial_id, "Batch A- Dec 09-10, 2017");
						List<?> memlist = getdaolist.getTrialMemberList(trial_id);
						
						Iterator<?> itr = memlist.iterator();
						if (itr.hasNext()) {
				%>

				<h5 class="center-align">GREENBOX MFC 2018-19</h5>
				<%-- <h6 class="center-align"><%=getmyPath.getStringDate("2018-12-16")%>&nbsp;to&nbsp;<%=getmyPath.getStringDate("2018-12-17")%></h6>--%>
				<h6 class="center-align">NOT PLAYED PLAYERS</h6> 
				<table class="table bordered">
					<thead>
						<tr>
							<!--  <th>Sr No</th>  -->
							<th>REG NO</th>
							<th>Name</th>
							<th>Position</th>
							<th>Age</th>
							<th>Batch</th>
							<th>ContactNO</th>
							<th>Extra</th>
						</tr>
					</thead>



					<%
						while (itr.hasNext()) {
									TrialMemberRegistration tmr = (TrialMemberRegistration) itr.next();
									if(!getDAOBool.isTrialPlayerPlayedMatch(trial_id, tmr.getTmr_id()))
									{
					%>
					<tbody>
						<tr>
						<%-- 	<td><%=cnt++%></td>  --%>
							<td><%=tmr.getTmr_id() %></td>
							<td><%=tmr.getFname().toUpperCase()%>&nbsp;<%=tmr.getLname().toUpperCase()%>&nbsp;</td>
							<td><%=tmr.getPosition().toUpperCase()%></td>
							<td><%=getmyPath.getAge(tmr.getDob())%></td>
							<td><%=tmr.getBatch().split("-")[0].split(" ")[1] %></td>
							<td><%=tmr.getMobileno() %></td>
							<td></td>
						</tr>
					</tbody>
					<%
									}
						}
					%>
				</table>
				<%
					}
					} catch (Exception e) {
						e.getMessage();
					}
				%>
			</div>

		</div>  
	</div>
</body>
</html>