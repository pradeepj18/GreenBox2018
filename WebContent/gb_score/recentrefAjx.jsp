<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DBManager.DBManager,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Goal Foul Card</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<style type="text/css">
.btn-floating i:hover {
	font-weight: 800;
	background-color: #fff;
	color: #e53935;
}
.btn-floating.removebtn
{
	width:30px;
	height:30px;
}
.btn-floating.removebtn i
{
	line-height:30px;
}
.margin-bot10
{
margin-bottom:10px;
}
</style>
</head>
<body>
	<%
		try {
			int uid = Integer.parseInt(request.getParameter("flag"));
			int tourId = Integer.parseInt(request.getParameter("tourId"));
			int mid = Integer.parseInt(request.getParameter("mid"));
			DBManager.loadDriver();
			//System.out.println("UID---"+uid+" tourId---"+tourId+" mid----"+mid);
			if (uid == 4) {
				ResultSet gs = null;
				gs = DBManager
						.fetchQuery("select g.cno,g.ttId,g.barcodeId from tourCard g where cname='RED' and tourId="
								+ tourId + " and mid=" + mid + " order by cno desc;");
				if (gs.next()) {
					gs.beforeFirst();
					while (gs.next()) {
						ResultSet gs1 = DBManager.fetchQuery(
								"select m.barcodeId,m.fName,m.lName,tp.jerseyNo,(select tName from team where tId in(select tId from tourTeam where ttId="
										+ gs.getInt("ttId")
										+ ")) as tname from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.tourId="
										+ request.getParameter("tourId") + " and  m.barcodeId="
										+ gs.getInt("barcodeId") + ";");
						if (gs1.next()) {
	%>
	<div class="row margin-bot10" >
		<div class="col l1 s1 m1">
			<span><%=gs1.getInt("jerseyNo")%></span>
		</div>
		<div class="col l5 s5 m5">
			<span><%=gs1.getString("fName")%></span>
		</div>
		<div class="col l4 s4 m4">
			<span><%=gs1.getString("tname")%></span>
		</div>
		<div class="col l2 s2 m2">
			<span><a
				class="btn-floating removebtn waves-effect waves-light red darken-1"
				onclick="delgfc(<%=gs.getInt("cno")%>,<%=tourId%>,<%=mid%>,<%=uid%>,<%=gs.getInt("barcodeId")%>)"><i
					class="material-icons">remove</i></a></span>
		</div>
	</div>
	<%
		}
					}
				}
			} else if (uid == 3) {
				ResultSet gs = null;
				gs = DBManager.fetchQuery("select g.cno,g.ttId,g.barcodeId from tourCard g where cname='YELLOW' and tourId="
						+ tourId + " and mid=" + mid + " order by cno desc;");
				if (gs.next()) {
					gs.beforeFirst();
					while (gs.next()) {
						ResultSet gs1 = DBManager.fetchQuery(
								"select m.barcodeId,m.fName,m.lName,tp.jerseyNo,(select tName from team where tId in(select tId from tourTeam where ttId="
										+ gs.getInt("ttId")
										+ ")) as tname from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.tourId="
										+ request.getParameter("tourId") + " and  m.barcodeId="
										+ gs.getInt("barcodeId") + ";");
						if (gs1.next()) {
	%>
	<div class="row margin-bot10">
		<div class="col l1 s1 m1">
			<span><%=gs1.getInt("jerseyNo")%></span>
		</div>
		<div class="col l5 s5 m5">
			<span><%=gs1.getString("fName")%></span>
		</div>
		<div class="col l4 s4 m4">
			<span><%=gs1.getString("tname")%></span>
		</div>
		<div class="col l2 s2 m2">
			<span><a
				class="btn-floating removebtn waves-effect waves-light red darken-1"
				onclick="delgfc(<%=gs.getInt("cno")%>,<%=tourId%>,<%=mid%>,<%=uid%>,<%=gs.getInt("barcodeId")%>)"><i
					class="material-icons">remove</i></a></span>
		</div>
	</div>
	<%
		}
					}
				}
			}
			else if (uid == 2) {
				ResultSet gs = null;
				gs = DBManager.fetchQuery("select g.fno,g.ttId,g.barcodeId from tourFoul g where tourId="
						+ tourId + " and mid=" + mid + " order by fno desc;");
				if (gs.next()) {
					gs.beforeFirst();
					while (gs.next()) {
						ResultSet gs1 = DBManager.fetchQuery(
								"select m.barcodeId,m.fName,m.lName,tp.jerseyNo,(select tName from team where tId in(select tId from tourTeam where ttId="
										+ gs.getInt("ttId")
										+ ")) as tname from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.tourId="
										+ request.getParameter("tourId") + " and  m.barcodeId="
										+ gs.getInt("barcodeId") + ";");
						if (gs1.next()) {
	%>
	<div class="row margin-bot10">
		<div class="col l1 s1 m1">
			<span><%=gs1.getInt("jerseyNo")%></span>
		</div>
		<div class="col l5 s5 m5">
			<span><%=gs1.getString("fName")%></span>
		</div>
		<div class="col l4 s4 m4">
			<span><%=gs1.getString("tname")%></span>
		</div>
		<div class="col l2 s2 m2">
			<span><a
				class="btn-floating removebtn waves-effect waves-light red darken-1"
				onclick="delgfc(<%=gs.getInt("fno")%>,<%=tourId%>,<%=mid%>,<%=uid%>,<%=gs.getInt("barcodeId")%>)"><i
					class="material-icons">remove</i></a></span>
		</div>
	</div>
	<%
		}
					}
				}
			}
			
			else if (uid == 1) {
				ResultSet gs = null;
				String og="";
				int newttid=0;
				gs = DBManager.fetchQuery("select g.gno,g.ttId,g.barcodeId,g.gtype,(select tid1 from wmatch w where w.mid=g.mid and w.tourId=g.tourId) as tid1,(select tid2 from wmatch w where w.mid=g.mid and w.tourId=g.tourId) as tid2 from tourGoal g where tourId="
						+ tourId + " and mid=" + mid + " order by gno desc;");
				if (gs.next()) {
					gs.beforeFirst();
					while (gs.next()) {
						newttid=gs.getInt("ttId");
						og="";
						if(gs.getString("gtype").equalsIgnoreCase("og"))
						{
							og="(OG)";
							if(newttid== gs.getInt("tid1"))
								newttid=gs.getInt("tid2");
							else
								newttid=gs.getInt("tid1");
						}
						ResultSet gs1 = DBManager.fetchQuery(
								"select m.barcodeId,m.fName,m.lName,tp.jerseyNo,(select tName from team where tId in(select tId from tourTeam where ttId="
										+ newttid
										+ ")) as tname from tourPlayer tp,member m where tp.barcodeId=m.barcodeId and tp.tourId="
										+ request.getParameter("tourId") + " and  m.barcodeId="
										+ gs.getInt("barcodeId") + ";");
						if (gs1.next()) {
							og="";
							if(gs.getString("gtype").equalsIgnoreCase("og"))
								og="(OG)";
							else if(gs.getString("gtype").equalsIgnoreCase("penalty"))
								og="(P)";
							else if(gs.getString("gtype").equalsIgnoreCase("golden"))
								og="(G)";
	%>
	<div class="row margin-bot10">
		<div class="col l1 s1 m1">
			<span><%=gs1.getInt("jerseyNo")%></span>
		</div>
		<div class="col l5 s5 m5">
			<span><%=gs1.getString("fName")%>&nbsp;<b class="red-text"><%=og %></b></span>
		</div>
		<div class="col l4 s4 m4">
			<span><%=gs1.getString("tname")%></span>
		</div>
		<div class="col l2 s2 m2">
			<span><a
				class="btn-floating removebtn waves-effect waves-light red darken-1"
				onclick="delgfc(<%=gs.getInt("gno")%>,<%=tourId%>,<%=mid%>,<%=uid%>,<%=gs.getInt("barcodeId")%>)"><i
					class="material-icons">remove</i></a></span>
		</div>
	</div>
	<%
		}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in recentref" + e);
		//	e.printStackTrace();
		}
	/* finally
	{
		DBManager.close();
	} */
	%>

</body>

</html>