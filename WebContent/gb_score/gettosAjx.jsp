
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GET TOSS</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">

</head>
<body>
	<div class="container">
		<div class="row">

			<%
				int mid = Integer.parseInt(request.getParameter("mid"));
				int tourId = Integer.parseInt(request.getParameter("tourId"));
				long login_id=Long.parseLong(request.getParameter("login_id"));
				try {
					DBManager.loadDriver();
					ResultSet rs = null;
					rs = DBManager.fetchQuery(
							"select mid,gender,pool,tid1,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="
									+ tourId
									+ ") as photo1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="
									+ tourId
									+ ") as tname1,tid2,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="
									+ tourId
									+ ") as photo2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="
									+ tourId + ") as tname2 from wmatch w where mid=" + mid + " and tourId=" + tourId
									+ " and flag='UPCOMING' and flag!='DONE';");
					//select mid,gender,pool,tid1,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId=1) as photo1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId=1) as tname1,tid2,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId=1) as photo2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId=1) as tname2 from wmatch w where mid=1 and tourId=1;

					if (rs.next()) {
			%>

			<h5>
				MATCH NUMBER -
				<%=mid%></h5>
			<h5>TOSS WON BY -</h5>

	
				<p>
					<input name="group1" type="radio" id="test1" value="<%=rs.getInt("tid1")%>" onclick="inittoss(<%=rs.getInt("tid1")%>,<%=login_id %>);" required  /> <label for="test1"><%=rs.getString("tname1") %></label>
				</p>
				<p>
					<input name="group1" type="radio" id="test2" value="<%=rs.getInt("tid2")%>" onclick="inittoss(<%=rs.getInt("tid2")%>,<%=login_id %>);" required/> <label for="test2"><%=rs.getString("tname2") %></label>
				</p>
				
  <button class="btn waves-effect waves-light" onclick="startmatch(<%=mid %>,<%=tourId %>,1,<%=login_id%>);">START MATCH
    <i class="material-icons right">send</i>
  </button>
    		<%
				}
					else
					{
						%>
						<button class="btn waves-effect waves-light" onclick="startmatch(<%=mid %>,<%=tourId %>,0,<%=login_id%>);">COMPLETE MATCH
							    <i class="material-icons right">send</i>
							  </button>
					<% 
					}
				} catch (Exception e) {
				System.out.print("Error in gettosAjx : "+e);
				}
				/* finally
				{
					DBManager.close();
				} */
			%>
		</div>
	</div>
</body>
</html>