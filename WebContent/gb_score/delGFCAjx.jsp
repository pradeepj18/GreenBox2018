<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Goal Foul Card</title>
<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
</head>
<body>
	<%
	
		int uid=Integer.parseInt(request.getParameter("uid"));
		int tourId = Integer.parseInt(request.getParameter("tourId"));
		int mid= Integer.parseInt(request.getParameter("mid"));;
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		try {
			DBManager.loadDriver();
			DBManager DB = new DBManager();
			if(uid==4 || uid==3)
			{
				DB.insert("delete from tourCard where cno="+cno+" and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");	
			}
			else if(uid==2)
			{
				DB.insert("delete from tourFoul where fno="+cno+" and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
			}
			else if(uid==1)
			{
				DB.insert("delete from tourGoal where gno="+cno+" and tourId="+tourId+" and mid="+mid+" and barcodeId="+bid+";");
			}
			
		} catch (Exception e) {
			System.out.println("++Error in GBAddGFC" + e);
		}
	/* 	finally
		{
			DBManager.close();
		} */
	%>
	
</body>
<script>

</script>
</html>