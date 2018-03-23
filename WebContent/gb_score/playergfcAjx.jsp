<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PLAYER Goal Foul Card</title>

</head>
<body>
	<%
	
		int uid=Integer.parseInt(request.getParameter("uid"));
		int tuid=Integer.parseInt(request.getParameter("tuid"));
		int tourId = Integer.parseInt(request.getParameter("tourId"));
		int mid= Integer.parseInt(request.getParameter("mid"));
		int bid=Integer.parseInt(request.getParameter("bid"));
		try {
			DBManager.loadDriver();
			if(tuid==1 && uid==1)
			{
				ResultSet rs=DBManager.fetchQuery("select count(gno) as g from tourGoal where tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("g"));
				}
			}
			else if(tuid==1 && uid==2)
			{
				ResultSet rs=DBManager.fetchQuery("select count(fno) as f from tourFoul where tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("f"));
				}
			}
			else if(tuid==1 && uid==3)
			{
				ResultSet rs=DBManager.fetchQuery("select count(cno) as y from tourCard where cname='YELLOW' and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("y"));
				}
			}
			else if(tuid==1 && uid==4)
			{
				ResultSet rs=DBManager.fetchQuery("select count(cno) as r from tourCard where cname='RED' and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("r"));
				}
			}
			else if(tuid==2 && uid==1)
			{
				ResultSet rs=DBManager.fetchQuery("select count(gno) as g from tourGoal where tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("g"));
				}
			}
			else if(tuid==2 && uid==2)
			{
				ResultSet rs=DBManager.fetchQuery("select count(fno) as f from tourFoul where tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("f"));
				}
			}
			else if(tuid==2 && uid==3)
			{
				ResultSet rs=DBManager.fetchQuery("select count(cno) as y from tourCard where cname='YELLOW' and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("y"));
				}
			}
			else if(tuid==2 && uid==4)
			{
				ResultSet rs=DBManager.fetchQuery("select count(cno) as r from tourCard where cname='RED' and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
				if(rs.next())
				{
					out.println(rs.getInt("r"));
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Error in playergfcAjx : " + e);
		}
		/* finally
		{
			DBManager.close();
		} */
	%>
	
</body>
<script>

</script>
</html>