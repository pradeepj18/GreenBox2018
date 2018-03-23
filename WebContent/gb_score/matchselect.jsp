<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">

<title>Match Perform</title>
</head>
<body>
<div class="container">
<div class="row">
<div class="col l3 m2"></div>
<div class="input-field col l6 s12 m8">
<%
try
{
	int tourId=Integer.parseInt(request.getParameter("tourId"));
	long login_id=17001;
	%>	
	<select id = "mid" onchange = "getMid(<%=tourId %>,<%=login_id %>);" class="icons">
<option value="" disabled selected>Choose your match</option>

<%

try
{
	ResultSet rs=null;
	DBManager.loadDriver();
	rs=DBManager.fetchQuery("select mid,gender,pool,tid1,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="+tourId+") as photo1,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid1 = tt.ttId and tourId="+tourId+") as tname1,tid2,(select SUBSTRING_INDEX(tFlag,'/',-3) from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="+tourId+") as photo2,(select tname from team t,tourTeam tt where tt.tId = t.tId and w.tid2 = tt.ttId and tourId="+tourId+") as tname2 from wmatch w where Flag!='DONE' and tourId="+tourId);
	if(rs.next())
	{
		rs.beforeFirst();
	while(rs.next())
	{
		/* if(rs.getString("gender").equalsIgnoreCase("M"))
	
		out.println("<option value="+rs.getInt("mid") + ">" + rs.getInt("mid")+ "  -  ("+ rs.getString("tname1")+","+ rs.getString("tname2")+")  -  MALE</option>");
		
		else
			out.println("<option value="+rs.getInt("mid") + ">" + rs.getInt("mid")+ "  -  ("+ rs.getString("tname1")+","+ rs.getString("tname2")+")  -  FEMALE</option>"); */
		%>
		<option value="<%=rs.getInt("mid") %>"><%=rs.getInt("mid") %>&nbsp;-&nbsp;<%=rs.getString("tname1") %> &nbsp;&nbsp;vs&nbsp;&nbsp;<%=rs.getString("tname2") %>&nbsp;&nbsp;-&nbsp;&nbsp;[ <%=(char)(rs.getInt("pool")+64) %> ]</option>
		<%
			}
					} else {
		%>
		
		<% 
	}
}
catch(Exception e){
	
	System.out.println("Error in matchselect : "+e);
}
%>
</select>
	<%
}
catch(Exception e)
{System.out.println("matchSelect : "+e);}
/* finally
{
	DBManager.close();
} */
%>
</div>
<div class="col l3 m2"></div>
</div>

<div class="row">
<div class="col l3 m2"></div>
<div class="col l6 m8">
<div id="toss"></div>
<div id="t"></div>
</div>
<div class="col l3 m2"></div>

</div>
</div>
<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="../materialize/js/materialize.min.js"></script>
<script>
$(document).ready(function() {
    $('select').material_select();
});
function getMid(tourId,login_id)
{
	var mid=document.getElementById("mid").value;
	//$('#toss').load("gettosAjx.jsp?tourId="+tourId+"&mid="+mid);	
	$.ajax({
		method : "post",
		url : "gettosAjx.jsp",
		data : ({
			tourId : tourId,
			mid : mid,
			login_id:login_id
		}),
		async : false,
		cache : false,
		beforeSend : function() {},
		complete : function() {},
		success : function(msg) {
			$("#toss").html(msg);
		}
	});
}
var toss,login_id;

function inittoss(id,login){
	  
	  toss=id;
	  login_id=login;
}

function startmatch(mid,tourId,uid,login_id)
{
	//alert("mid : "+mid+" tourId : "+tourId+" uid : "+uid+" login_id : "+login_id);
	if(uid==0)
	{
		window.open("newscorecard.jsp?mid="+mid+"&tourId="+tourId+"&login_id="+login_id, "file","status=1,width=1366,height=768");
		//window.location.replace("matchselect.jsp?tourId="+tourId+"&login_id="+login_id);
	}
	else
	{
	if(toss>0)
	{
		$.ajax({
		method : "post",
		url : "updatetossAjx.jsp",
		data : ({
			tourId : tourId,
			mid : mid,
			uid : uid,
			toss : toss,
			login_id:login_id
		}),
		async : false,
		cache : false,
		beforeSend : function() {},
		complete : function() {},
		success : function(msg) {
			window.open("newscorecard.jsp?mid="+mid+"&tourId="+tourId+"&login_id="+login_id, "file","status=1,width=1366,height=768");
			window.location.replace("matchselect.jsp?tourId="+tourId+"&login_id="+login_id);
		}
	});
	}
}
}
</script>
</body>
</html>