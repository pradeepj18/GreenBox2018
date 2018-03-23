<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Refresh Score</title>
</head>
<body>
	<%
	try
	{
		DBManager.loadDriver();
		ResultSet rs = null,rs1=null;;
		int tid1 = 0, tid2 = 0;
		int tAscore = 0, tBscore = 0;
		int flag = Integer.parseInt(request.getParameter("flag"));
		rs = DBManager.fetchQuery(
				"select tid1,tid2 from wmatch w where mid="+ request.getParameter("mid") + " and tourId="+ request.getParameter("tourId")+";");
		if (rs.next()) {
			tid1 = rs.getInt("tid1");
			tid2 = rs.getInt("tid2");
		}
		if (flag == 1) {
			rs = null;
			rs = DBManager.fetchQuery(
					"select count(gno) as tAscore from tourGoal where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid1 + ";");
			if (rs.next()) {
				tAscore = rs.getInt("tAscore");
			}
			out.println(tAscore);
		} else if (flag == 2) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(gno) as tBscore from tourGoal where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid2 + ";");
			if (rs1.next())
				tBscore = rs1.getInt("tBscore");
			out.println(tBscore);
		} else if (flag == 11) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(fno) as tAscore from tourFoul where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid1 + ";");
			if (rs1.next()) {
				tAscore = rs1.getInt("tAscore");
			}
			out.println(tAscore);
		}
		else if (flag == 21) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(fno) as tBscore from tourFoul where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid2 + ";");
			if (rs1.next()) {
				tAscore = rs1.getInt("tBscore");
			}
			out.println(tAscore);
		}
		
		else if (flag == 12) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(cno) as tAscore from tourCard where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid1 + " and cname='YELLOW';");
			if (rs1.next()) {
				tAscore = rs1.getInt("tAscore");
			}
			out.println(tAscore);
		}
		else if (flag == 22) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(cno) as tBscore from tourCard where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid2 + " and cname='YELLOW';");
			if (rs1.next()) {
				tAscore = rs1.getInt("tBscore");
			}
			out.println(tAscore);
		}
		else if (flag == 13) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(cno) as tAscore from tourCard where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid1 + " and cname='RED';");
			if (rs1.next()) {
				tAscore = rs1.getInt("tAscore");
			}
			out.println(tAscore);
		}
		else if (flag == 23) {
			rs1 = null;
			rs1 = DBManager.fetchQuery(
					"select count(cno) as tBscore from tourCard where tourId=" + request.getParameter("tourId")
							+ " and mid=" + request.getParameter("mid") + " and ttId=" + tid2 + " and cname='RED';");
			if (rs1.next()) {
				tAscore = rs1.getInt("tBscore");
			}
			out.println(tAscore);
		}
	}catch(Exception e){System.out.println("refresh score : "+e);}
	/* finally
	{
		DBManager.close();
	} */
	%>
</body>
</html>