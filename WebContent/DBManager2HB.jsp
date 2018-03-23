<%@page import="com.playsoftech.greenbox.dao.AddTrialsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="DBManager.DBManager,playsoftech.gb.getmyPath,java.sql.*" %>
    <%@ page import="com.playsoftech.greenbox.dao.DBManager2HBDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	try
	{
		
		new DBManager2HBDAO().addLogin();
		new DBManager2HBDAO().addTournament();
		new DBManager2HBDAO().addMember();
		new DBManager2HBDAO().addTeam();
		new DBManager2HBDAO().addTourTeam();
		new DBManager2HBDAO().addTourPool();
		new DBManager2HBDAO().addTourPlayer(); 
		new DBManager2HBDAO().addWmatch();
		new DBManager2HBDAO().addPrintMatch();
		new DBManager2HBDAO().addManoftheMatch();
		new DBManager2HBDAO().addTourCard();
		new DBManager2HBDAO().addTourFoul();
		new DBManager2HBDAO().addTourGoal(); 
		/* -------------------------------------------- */
		
		
		
	}
	catch(Exception e)
	{
		e.getMessage();
	}
	%>
</body>
</html>