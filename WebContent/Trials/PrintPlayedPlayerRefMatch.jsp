<%@page import="com.playsoftech.greenbox.pojo.RefreeMarksMatch"%>
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
<title>Played Player List</title>
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
			<div class="col s12 m12 s12">
				<%
					try {
						int trial_id = 3;
						int cnt = 1;
						GetDAOList getdaolist = new GetDAOList();
						Date dob=new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-16");
						List<?> memlist = getdaolist.getPlayedPlayerRefreeMarks(trial_id,dob);
						Iterator<?> itr = memlist.iterator();
						System.out.println("Size :"+memlist.size());
						if (itr.hasNext()) {
				%>

				<h5 class="center-align">GREENBOX MFC 2018-19</h5>
				
				<h6 class="center-align">Played Players - <%=getmyPath.getSuffixDate("2017-12-16") %></h6>
				<table class="table bordered">
					<thead>
						<tr>
							<!-- <th>Sr No</th> -->
							<th>REG NO</th>
							<th>Name</th>
							<th>Position</th>
							<th>ContactNO</th>
							<th>Extra</th>
						</tr>
					</thead>



					<%
						
					
						while (itr.hasNext()) {
							RefreeMarksMatch rmm = (RefreeMarksMatch) itr.next();
							
							TrialMemberRegistration tmr = getdaolist.getTrialMemberRegistration(rmm.getTrialMemberRegistration().getTmr_id(), trial_id);
							
									
					%>
					<tbody>
						<tr>
							<%-- <td><%=cnt++%></td> --%>
							<td><%=tmr.getTmr_id() %></td>
							<td><%=tmr.getFname().toUpperCase()%>&nbsp;<%=tmr.getLname().toUpperCase()%>&nbsp;</td>
							<td><%=tmr.getPosition().toUpperCase()%></td>
							<td><%=tmr.getMobileno() %></td>
							<td></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
				<%
					}
					} catch (Exception e) {
						e.printStackTrace();
					System.out.println("Error in playedplayer.jsp : "+e);
					}
				%>
			</div>

		</div>  
	</div>
</body>
</html>