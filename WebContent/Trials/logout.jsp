<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
try{
	session.setAttribute("reflogin_id", null);
	session.invalidate();
	response.sendRedirect("index.jsp");
	
}
catch(Exception e){
	System.out.println("in logout.jsp "+e.getMessage());
}%>
</body>
</html>