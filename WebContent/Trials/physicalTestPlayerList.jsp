<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOBool"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.playsoftech.greenbox.pojo.TrialMemberRegistration"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOClass" %>
<%@ page import="playsoftech.gb.getmyPath"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

ul.collection.box
{
	height:350px;
	overflow-y:scroll;
}</style>
</head>
<body>
<%
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");

			if (session.getAttribute("login_id") == null) {
				response.sendRedirect("index.jsp");
			} 
			else
			{
				long event_id = Long.parseLong(request.getParameter("event_id"));
				GetDAOList getDAOList = new GetDAOList();
				GetDAOBool getDAOBool = new GetDAOBool();
				GetDAOClass getDAOClass = new GetDAOClass();
				
				long trial_id = Long.parseLong(request.getParameter("trial_id"));
				List<Long> getTrialMember = getDAOList.getPhysicalTestPlayer(trial_id,event_id);
	%>

 			<input type="text" id="myInput" onkeyup="myTrialMember()" placeholder="Search for names.." autofocus style="margin: 0;">
			<ul class="collection box" id="playercheck">
				<%
					if (getTrialMember.size() > 0) 
					{
								Iterator<?> trialMemIt = getTrialMember.iterator();
								while (trialMemIt.hasNext()) 
								{
									Long tmr_id = (Long)trialMemIt.next();
									TrialMemberRegistration trialMember = getDAOClass.getTrialMemberRegistration(tmr_id);
				%>
				<li class="collection-item avatar white">
					<div class="col s2">
						<img src="../<%=getmyPath.getPhoto(trialMember.getPhoto())%>"
							alt="" class="circle responsive-img valign profile-image">
					</div>
					<div class="col s6 ">
						<h6 class="truncate">
							<span><b><%=trialMember.getFname().toUpperCase()%> <%=trialMember.getMname().toUpperCase()%>
								<%=trialMember.getLname().toUpperCase()%></b>
							</span>
						</h6>
					</div>
					<div class="col s4 ">
						<h6>
							<span><b><%=getmyPath.getReg_id(trialMember.getTmr_id())%></b></span>
						</h6>
					</div>
					<div class="col s2">
						<input type="checkbox"
							id="myCheckbox<%=trialMember.getTmr_id()%>" name="player"
							class="filled-in" value="<%=trialMember.getTmr_id()%>" />
						<label for="myCheckbox<%=trialMember.getTmr_id()%>"></label>

					</div>
				</li>
				<%
					}
				} else {
				%>
				<li class="collection-item avatar">
					<h5>No Player Available</h5>
				</li>
				<%
					}
				%>
			</ul>
			<div class="right">
				<button class="btn-floating btn-large" type="submit"
					name="submit" onclick="showPhysicalForm()">
					<i class="material-icons right">send</i>
				</button>
			</div>
		<%}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("physicalTestPlayerList.jsp "+e.getMessage());
		}%>
						 

</body>
</html>