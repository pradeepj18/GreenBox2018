<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
	<%@ page import="com.playsoftech.greenbox.pojo.GoalFoulType" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Goal Foul Card</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">

</head>
<body>

	<%
		int gno = 0;
		String tname = "", pname = "";
		int uid = Integer.parseInt(request.getParameter("uid"));
		int tourId = Integer.parseInt(request.getParameter("tourId"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		int jno = Integer.parseInt(request.getParameter("jno"));
		String timer = request.getParameter("time");
		String gtype = request.getParameter("gtype");
		String bid = request.getParameter("bid");
		long login_id=Long.parseLong(request.getParameter("login_id"));
		GoalFoulType gft=null;
		if(gtype.equalsIgnoreCase("1"))
			gft=GoalFoulType.FH;
		else if(gtype.equalsIgnoreCase("2"))
			gft=GoalFoulType.SH;
		else if(gtype.equalsIgnoreCase("G"))
			gft=GoalFoulType.GOLDEN;
		else if(gtype.equalsIgnoreCase("p"))
			gft=GoalFoulType.PENALTY;
		else if(gtype.equalsIgnoreCase("og"))
			gft=GoalFoulType.OG;
		try {
			DBManager.loadDriver();
			DBManager DB = new DBManager();
			if (uid == 1) {
				gno = DBManager.getMaxId("tourGoal", "gno");

				int r = DB.insert("insert into tourGoal(gno,gtime,barcodeId,tourId,mid,ttId,gtype,login_id) values(" + gno + ",'" + timer + "'," + bid + "," + tourId
						+ "," + mid + "," + tid + ",'" + gft.toString() + "',"+login_id+")");

			}
			/* ---------------------------------------------------------------------------------------------- */
			else if (uid == 2) {
				int fno = DBManager.getMaxId("tourFoul", "fno");

				int r = DB.insert("insert into tourFoul(fno,ftime,barcodeId,tourId,mid,ttId,ftype,login_id) values(" + fno + ",'" + timer + "'," + bid + "," + tourId
						+ "," + mid + "," + tid + ",'" + gft.toString() + "',"+login_id+")");

			}
			/*  -----------------------------------------------------------------------------*/

			else if (uid == 3) {
				int cno = DBManager.getMaxId("tourCard", "cno");
				int r = DB.insert("insert into tourCard(cno,ctime,barcodeId,tourId,mid,ttId,ctype,cname,login_id) values(" + cno + ",'" + timer + "'," + bid + "," + tourId
						+ "," + mid + "," + tid + ",'" + gft.toString() + "','YELLOW',"+login_id+")");

			}
			/*  --------------------------------------------------------------------------------*/
			else if (uid == 4) {
				int cno = DBManager.getMaxId("tourCard", "cno");
				int r = DB.insert("insert into tourCard(cno,ctime,barcodeId,tourId,mid,ttId,ctype,cname,login_id) values(" + cno + ",'" + timer + "'," + bid + "," + tourId
						+ "," + mid + "," + tid + ",'" + gft.toString() + "','RED',"+login_id+")");
			} else if (uid == 5) {
				int newtid = 0;
				ResultSet og = DBManager.fetchQuery("select tid1,tid2 from wmatch w where mid="
						+ request.getParameter("mid") + " and tourId=" + request.getParameter("tourId") + ";");
				if (og.next()) {
					if (og.getInt("tid1") == tid)
						newtid = og.getInt("tid2");
					else
						newtid = og.getInt("tid1");
					gno = DBManager.getMaxId("tourGoal", "gno");

					int r = DB.insert("insert into tourGoal(gno,gtime,barcodeId,tourId,mid,ttId,gtype,login_id) values(" + gno + ",'" + timer + "'," + bid + ","
							+ tourId + "," + mid + "," + newtid + ",'" + gft.toString() + "',"+login_id+")");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in addGFC" + e);
			//e.printStackTrace();
		}
	 /* 	finally
		{
			DBManager.close();
		}  */
	%>

</body>
<script>
	
</script>
</html>