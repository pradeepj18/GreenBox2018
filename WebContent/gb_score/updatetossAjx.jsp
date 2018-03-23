<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ResultSet rs = null, rs1 = null;
		String gender = "";
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));

			int tourId = Integer.parseInt(request.getParameter("tourId"));
			long login_id = Long.parseLong(request.getParameter("login_id"));
			String uid1 = (request.getParameter("uid"));

			DBManager.loadDriver();
			int goal1 = 0, goal2 = 0, twon = 0, g = 0;
			DBManager DB = new DBManager();
			if (uid1.equals("1")) {
				int toss = Integer.parseInt(request.getParameter("toss"));
				
				int srno = DBManager.getMaxId("print_match", "sr_no");
				int court = 1;
				int tid1 = 0, tid2 = 0;

				String tname1 = "", tname2 = "", mtype = "";
				rs = DBManager.fetchQuery(
						"select mtype,gender,tid1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="
								+ tourId
								+ ") as tname1,tid2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="
								+ tourId + ") as tname2 from wmatch w where mid=" + mid + " and tourId="+tourId+";");

				//select mtype,gender,tid1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId=1) as tname1,tid2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId=1) as tname2 from wmatch w where mid=1 and tourId=1;

				if (rs.next()) {
					tid1 = rs.getInt("tid1");
					tid2 = rs.getInt("tid2");
					tname1 = rs.getString("tname1");
					tname2 = rs.getString("tname2");
					mtype = rs.getString("mtype");
					gender = rs.getString("gender");

					rs1 = DBManager.fetchQuery(
							"select time(now()) as t,curdate() as d,tName from team where tid in(select tid from tourTeam where ttId="
									+ toss + ")");
				//select time(now()) as t,curdate() as d,tName from team where tid in(select tid from tourTeam where ttId=4)

					rs1.next();

					DB.insert(
							"insert into print_match (mid,mdate,mtime,twon,court,sr_no,tid1,tid2,mtype,gender,tourId,login_id) value("
									+ mid + ",'" + rs1.getString("d") + "','" + rs1.getString("t") + "','"
									+ rs1.getString("tName") + "'," + court + "," + srno + "," + tid1 + "," + tid2
									+ ",'" + mtype + "','" + gender + "'," + tourId + "," + login_id + ");");

				}
				rs = null;
						int x=DB.insert("update wmatch set flag='ONGOING' where mid=" + mid + " and tourId=" + tourId
								+ " and gender='" + gender + "';");
						
						
			} else if (uid1.equals("2")) {
				rs = null;
				rs = DBManager
						.fetchQuery("select gender from wmatch where mid=" + mid + " and tourId=" + tourId + ";");
				if (rs.next()) {
					gender = rs.getString("gender");
							DB.insert("update wmatch set flag='DONE' where mid=" + mid + " and tourId=" + tourId
									+ " and gender='" + gender + "';");
					
				}
			}
			
	%>
	<!-- 		<script type="text/javascript">
			window.close();
		</script> -->
	<%
		} catch (Exception e) {
			System.out.println("UPdate Toss " + e);
			e.printStackTrace();
		} /* finally {
			DBManager.close();
		} */
	%>
</body>
</html>