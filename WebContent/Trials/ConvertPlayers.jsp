<%@page import="com.playsoftech.greenbox.dao.AddTrialsDAO"%>
<%@page import="com.playsoftech.greenbox.dao.GetDAOBool" %>
<%@page import="com.playsoftech.greenbox.pojo.MemStatus"%>
<%@page import="com.playsoftech.greenbox.pojo.MemCategory"%>
<%@page import="com.playsoftech.greenbox.pojo.Gender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="com.playsoftech.greenbox.pojo.Trials"%>
<%@ page import="com.playsoftech.greenbox.dao.GetDAOList"%>
<%@ page import="com.playsoftech.greenbox.pojo.Member,com.playsoftech.greenbox.dao.TrialsmemregDAO"%>
<%@ page import="DBManager.DBManager,java.sql.*,java.io.*,playsoftech.gb.getmyPath"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Convert Player</title>
</head>
<body>
	<%
		try {
			GetDAOBool getDAOBool = new GetDAOBool();
			
			if (!new File(getmyPath.getTrialMemberPath()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialMemberPath());
				//System.out.println("only trialmember");
			}
			
			if (!new File(getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID());
				//System.out.println("trial member with trialyear");
			}

			
			GetDAOList getDAOList = new GetDAOList();
			long a = getmyPath.getNewBarcodeId();
			System.out.println(a);
			DBManager.loadDriver();
			String name1="";
			String s="";
			ResultSet rs = DBManager.fetchQuery("select * from member1  order by submitted desc;");
			
			int cnt = 101;
			while (rs.next()) {
				
				if(getDAOBool.isPlayerExists(rs.getString("name")))
				{
				//	System.out.println("++++"+name1);
				long barcodeid = getmyPath.getNewBarcodeId();
				Member mem = null;
				String name[] = null;
				name = rs.getString("name").split(" ");
			//	System.out.println("Length :"+name.length);
				if (name.length == 1) {
						//System.out.println("First :"+name[0]);
					mem = getDAOList.getMemberExisting(name[0], "", "");
					if(mem != null)
					{
						//System.out.println(mem.getfName());
					
						new TrialsmemregDAO().addNewMember(cnt++,mem.getBarcodeId(), mem.getfName(), mem.getmName(), mem.getlName(), rs.getDate("dob"), Gender.MALE, "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+mem.getBarcodeId()+".png",3,MemCategory.PLAYER,MemStatus.OLD,rs.getString("address"),rs.getString("position"),rs.getString("email"),rs.getString("batch"),rs.getString("mobile"));
					}
					else
					{
															//(long tmr_id,long barcode_id,String fname,String mname,String lname,Date dob,Gender gender,String photo,long trial_id,MemCategory memcategory,MemStatus memstatus)
						new TrialsmemregDAO().addNewMember(cnt++,barcodeid, name[0], "", "", rs.getDate("dob"), Gender.MALE, "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+barcodeid+".png",3,MemCategory.PLAYER,MemStatus.NEW,rs.getString("address"),rs.getString("position"),rs.getString("email"),rs.getString("batch"),rs.getString("mobile"));
						
					}
				} 
				else if (name.length == 2) {
						//System.out.println("Second :"+name[0] + " " + name[1]);
					mem = getDAOList.getMemberExisting(name[0], "", name[1]);
					if(mem != null)
					{
						//System.out.println(mem.getfName());
						new TrialsmemregDAO().addNewMember(cnt++,mem.getBarcodeId(), mem.getfName(), mem.getmName(), mem.getlName(), rs.getDate("dob"), Gender.MALE, "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+mem.getBarcodeId()+".png",3,MemCategory.PLAYER,MemStatus.OLD,rs.getString("address"),rs.getString("position"),rs.getString("email"),rs.getString("batch"),rs.getString("mobile"));
					}
					else
					{
						new TrialsmemregDAO().addNewMember(cnt++,barcodeid, name[0], "", name[1], rs.getDate("dob"), Gender.MALE, "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+barcodeid+".png",3,MemCategory.PLAYER,MemStatus.NEW,rs.getString("address"),rs.getString("position"),rs.getString("email"),rs.getString("batch"),rs.getString("mobile"));
						
					}
				} 
				else if(name.length == 3) {
				//	System.out.println("Third :"+name[0] + " " + name[1] + " " + name[2]);
					mem = getDAOList.getMemberExisting(name[0], name[1], name[2]);
					if(mem != null)
					{
					//	System.out.println(mem.getfName()+" "+mem.getlName());
						new TrialsmemregDAO().addNewMember(cnt++,mem.getBarcodeId(), mem.getfName(), mem.getmName(), mem.getlName(), rs.getDate("dob"), Gender.MALE, "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+mem.getBarcodeId()+".png",3,MemCategory.PLAYER,MemStatus.OLD,rs.getString("address"),rs.getString("position"),rs.getString("email"),rs.getString("batch"),rs.getString("mobile"));
					}
					else
					{
						new TrialsmemregDAO().addNewMember(cnt++,barcodeid, name[0], name[1], name[2], rs.getDate("dob"), Gender.MALE, "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+barcodeid+".png",3,MemCategory.PLAYER,MemStatus.NEW,rs.getString("address"),rs.getString("position"),rs.getString("email"),rs.getString("batch"),rs.getString("mobile"));
						
					}
				}
				}
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			System.out.println("Conver player : "+e);
		}
	%>
</body>
</html>