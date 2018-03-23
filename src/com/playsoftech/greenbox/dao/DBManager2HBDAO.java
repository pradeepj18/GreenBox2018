package com.playsoftech.greenbox.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Admin;
import com.playsoftech.greenbox.pojo.CardName;
import com.playsoftech.greenbox.pojo.CardType;
import com.playsoftech.greenbox.pojo.Gender;
import com.playsoftech.greenbox.pojo.GoalFoulType;
import com.playsoftech.greenbox.pojo.Login;
import com.playsoftech.greenbox.pojo.MatchStatus;
import com.playsoftech.greenbox.pojo.MemCategory;
import com.playsoftech.greenbox.pojo.MemStatus;
import com.playsoftech.greenbox.pojo.Member;
import com.playsoftech.greenbox.pojo.Mtype;
import com.playsoftech.greenbox.pojo.PlayingStatus;
import com.playsoftech.greenbox.pojo.PrintMatch;
import com.playsoftech.greenbox.pojo.PrintMatchId;
import com.playsoftech.greenbox.pojo.Team;
import com.playsoftech.greenbox.pojo.TeamStatus;
import com.playsoftech.greenbox.pojo.TourAge;
import com.playsoftech.greenbox.pojo.TourPlayer;
import com.playsoftech.greenbox.pojo.TourPlayerId;
import com.playsoftech.greenbox.pojo.PlayerCategory;
import com.playsoftech.greenbox.pojo.TourPool;
import com.playsoftech.greenbox.pojo.TourTeam;
import com.playsoftech.greenbox.pojo.Tournament;
import com.playsoftech.greenbox.pojo.Wmatch;
import com.playsoftech.greenbox.pojo.WmatchId;

import DBManager.DB2;
import DBManager.DBManager;

public class DBManager2HBDAO {
SessionFactory sessionFactory = ConfigManager.getSessionFactory();
public DBManager2HBDAO()
{
	try {
		DBManager.loadDriver();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public void addLogin() {
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			Login l = new Login();
			l.setLoginid((long)17001);
			l.setAdmin(Admin.YES);
			l.setFname("Pradeep");
			l.setUsername("pj18cool");
			l.setPassword("pjcool");
			
			session.save(l);
			
			session.getTransaction().commit();
			session.close();
			
		}
		catch(HibernateException e) {
			System.out.println("AddLogin :"+e.getMessage());
		}
	}
	public void addTournament()
	{
		try
		{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Tournament tour=new Tournament();
			Login login = (Login)session.get(Login.class,(long)17001);
			
			ResultSet rs=DBManager.fetchQuery("select * from tournament");
			while(rs.next())
			{
				tour.setTourId(rs.getLong("tourId"));
				tour.setTourName(rs.getString("tourName"));
				tour.setTourTitle(rs.getString("tourTitle"));
				tour.setTourVenue(rs.getString("tourVenue"));
				tour.setTourOrgBy(rs.getString("tourOrgBy"));
				tour.setTourSDate(rs.getDate("tourSDate"));
				tour.setTourEDate(rs.getDate("tourEDate"));
				tour.setTourAge(TourAge.SENIOR);
				tour.setTourLogo(rs.getString("tourLogo"));
				tour.setLogin(login);
			}
			
			session.save(tour);
			session.getTransaction().commit();
			session.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error in addTournament : "+e);
		}
	}
	
	public void addMember()
	{
		try
		{
			Session session=sessionFactory.openSession();
			Date regdate=new SimpleDateFormat("yyyy/MM/dd").parse("2017/01/15");
			Login login = (Login)session.get(Login.class,(long)17001);
			Date today=new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			ResultSet rs=DBManager.fetchQuery("select * from member");
			while(rs.next())
			{
				Member member=new Member();
				member.setBarcodeId(rs.getLong("barcodeId"));
				member.setfName(rs.getString("fName"));
				member.setmName("");
				member.setlName(rs.getString("lName"));
				member.setDob(today);
				member.setGender(Gender.MALE);
				member.setPhoto("images/Member/"+rs.getString("photo"));
				member.setDetails("");
				member.setMemCategory(MemCategory.PLAYER);
				member.setMemStatus(MemStatus.OLD);
				
				member.setRegdate(regdate);
				member.setLogin(login);
				
				session.beginTransaction();
				session.save(member);
				session.getTransaction().commit();
				
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in member : "+e);
		}
	}
	
	public void addTeam()
	{
		try
		{
			Session session=sessionFactory.openSession();
			
			Login login = (Login)session.get(Login.class,(long)17001);
			ResultSet rs=DBManager.fetchQuery("select * from team");
			while(rs.next())
			{
				Team t=new Team();
				t.settId(rs.getLong("tId"));
				t.settName(rs.getString("tName"));
				t.settRegDate(rs.getDate("tRegDate"));
				t.setTeamStatus(TeamStatus.ACTIVE);
				t.settFlag("images/Flags/"+rs.getString("tName").toLowerCase()+".png");
				t.setDetails("");
				t.setLogin(login);
				
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in team : "+e);
		}
	}
	
	public void addTourTeam()
	{
		try
		{
			Session session=sessionFactory.openSession();
			
			Login login = (Login)session.get(Login.class,(long)17001);
			Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from tourTeam");
			while(rs.next())
			{
				TourTeam t=new TourTeam();
				t.setTtId(rs.getLong("ttId"));
				
				t.setTtGender(Gender.MALE);
				t.setTournament(tourId);
				Team tId = (Team)session.get(Team.class,rs.getLong("tId"));
				
				t.setTeam(tId);
				t.setLogin(login);
				
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in tourteam : "+e);
		}
	}
	
	public void addTourPool()
	{
		try
		{
			Session session=sessionFactory.openSession();
			
			Login login = (Login)session.get(Login.class,(long)17001);
			Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from tourPool");
			while(rs.next())
			{
				TourPool t=new TourPool();
				t.setTpId(rs.getLong("poolId"));
				t.setPname(rs.getString("pname"));
				t.setTpgender(Gender.MALE);
				t.setTournament(tourId);
				TourTeam ttId = (TourTeam)session.get(TourTeam.class,rs.getLong("ttId"));
				t.setTourteam(ttId);
				t.setLogin(login);
				addTourPool1(t);
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in tourpool : "+e);
		}
	}
	public void addTourPool1(TourPool m)
	{
		try
		{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(m);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e)
		{
			System.out.println("Error in tourpool1 : "+e);
		}
	}
	
	public void addTourPlayer()
	{
		try
		{
			Session session=sessionFactory.openSession();
		
			Login login = (Login)session.get(Login.class,(long)17001);
			//Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from tourPlayer");
			while(rs.next())
			{
				
				TourPlayerId tpId = new TourPlayerId();
				
				//Member barcodeId = (Member)session.get(Member.class,rs.getLong("barcodeId"));
				tpId.setBarcodeId(rs.getLong("barcodeId"));
				tpId.setTourId(1);
				tpId.setTtId(rs.getLong("ttId"));
				
				TourPlayer t=new TourPlayer();
				
				t.setTourPlayerId(tpId);
				t.setJerseyNo(rs.getLong("jerseyNo"));
				t.setPlayingstatus(PlayingStatus.ACTIVE);
				t.setPlayercategory(PlayerCategory.PLAYER);
				t.setLogin(login);
				
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in addTourPlayer : "+e);
		}
	}
	
	public void addWmatch()
	{
		try
		{
			Session session=sessionFactory.openSession();
		Mtype mtype=null;
			Login login = (Login)session.get(Login.class,(long)17001);
			Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from wmatch");
			while(rs.next())
			{
				
				WmatchId tpId = new WmatchId();
				
				tpId.setMid(rs.getLong("mid"));
				tpId.setTourId(tourId.getTourId());
				
				Wmatch t=new Wmatch();
				t.setWmatchId(tpId);
				t.setFlag(MatchStatus.DONE);
				t.setGender(Gender.MALE);
				
				if(rs.getString("mtype").equalsIgnoreCase("league"))
					mtype=Mtype.LEAGUE;
				else if(rs.getString("mtype").equalsIgnoreCase("pqtr"))
					mtype=Mtype.PQTR;
				else if(rs.getString("mtype").equalsIgnoreCase("qtr"))
					mtype=Mtype.QTR;
				else if(rs.getString("mtype").equalsIgnoreCase("semi"))
					mtype=Mtype.SEMI;
				else if(rs.getString("mtype").equalsIgnoreCase("final"))
					mtype=Mtype.FINAL;
				else if(rs.getString("mtype").equalsIgnoreCase("3rdplace"))
					mtype=Mtype.THIRDPLACE;
				else if(rs.getString("mtype").equalsIgnoreCase("clfn"))
					mtype=Mtype.CLFN;
				
				t.setMtype(mtype);
				t.setPool(rs.getInt("pool"));
				t.setTime(rs.getTime("mtime"));
				TourTeam tid1 = (TourTeam)session.get(TourTeam.class,(long)rs.getLong("tid1"));
				TourTeam tid2 = (TourTeam)session.get(TourTeam.class,(long)rs.getLong("tid2"));
				t.setTourTeam1(tid1);
				t.setTourTeam2(tid2);
				t.setLogin(login);
				
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in addWmatch : "+e);
		}
	}
	
	public void addPrintMatch()
	{
		try
		{
			Session session=sessionFactory.openSession();
			Mtype mtype=null;
			Login login = (Login)session.get(Login.class,(long)17001);
			//Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from print_match");
			while(rs.next())
			{
				
				PrintMatchId tpId=new PrintMatchId();
				
				tpId.setMdate(rs.getDate("mdate"));
				tpId.setMid(rs.getLong("mid"));
				tpId.setTourId(1l);
				
				
				PrintMatch t=new PrintMatch();
				t.setPrintMatchId(tpId);
				t.setCourt(rs.getInt("court"));
				
				
				if(rs.getString("mtype").equalsIgnoreCase("league"))
					mtype=Mtype.LEAGUE;
				else if(rs.getString("mtype").equalsIgnoreCase("pqtr"))
					mtype=Mtype.PQTR;
				else if(rs.getString("mtype").equalsIgnoreCase("qtr"))
					mtype=Mtype.QTR;
				else if(rs.getString("mtype").equalsIgnoreCase("semi"))
					mtype=Mtype.SEMI;
				else if(rs.getString("mtype").equalsIgnoreCase("final"))
					mtype=Mtype.FINAL;
				else if(rs.getString("mtype").equalsIgnoreCase("3rdplace"))
					mtype=Mtype.THIRDPLACE;
				else if(rs.getString("mtype").equalsIgnoreCase("clfn"))
					mtype=Mtype.CLFN;
				
				t.setMtype(mtype);
				t.setGender(Gender.MALE);
				t.setMtime(rs.getTime("mtime"));
				t.setSr_no(rs.getLong("srno"));
				t.setTid1(rs.getLong("tid1"));
				t.setTid2(rs.getLong("tid2"));
				t.setTwon(rs.getString("twon"));
				t.setLogin_id(login.getLoginid());
				
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in addPrintMatch : "+e);
		}
	}
	
	/*public void addManoftheMatch()
	{
		try
		{
			Session session=sessionFactory.openSession();
			Login login = (Login)session.get(Login.class,(long)17001);
			//Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from manofthematch");
			while(rs.next())
			{
				
				ManoftheMatch t=new ManoftheMatch();
				
				t.setMmid(rs.getLong("mmid"));
				Member barcodeId = (Member)session.get(Member.class,rs.getLong("barcodeId"));
				t.setMember(barcodeId);
				//t.setTournament(tourId);
				TourTeam ttId = (TourTeam)session.get(TourTeam.class,(long)rs.getLong("ttId"));
				t.setTourteam(ttId);
			
				
				
				WmatchId wmId = new WmatchId();
				wmId.setMid(rs.getLong("mid"));
				wmId.setTourId(1l);
				
				Wmatch wmatch = getRecordWmatch((long)rs.getLong("mid"),1l);
				wmatch.setWmatchId(wmId);
				
				System.out.println("   "+rs.getLong("mid"));
				t.setWmatch(wmatch);
				
				
				t.setLogin(login);
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in addManoftheMatch : "+e);
		}
	}
	
	public Wmatch getRecordWmatch(long mid,long tourId) {
		Wmatch wmatch = null;
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Wmatch where mid=:mid and tourId=:tourId")
										.setLong("mid", mid)
										.setLong("tourId", tourId);
			
			List<?> wmList = query.list();
			Iterator<?> wmIt = wmList.iterator();
			if(wmIt.hasNext()) {
				
				wmatch= (Wmatch)wmIt.next();
			}
			
		}
		catch(HibernateException e) {
			System.out.print("Error in getRecordWmatch");
		}
		
		return wmatch;
	}
	
	/*
	public void addTourCard()
	{
		try
		{
			Session session=sessionFactory.openSession();
			Login login = (Login)session.get(Login.class,(long)17001);
			//Tournament tourId = (Tournament)session.get(Tournament.class,(long)1);
			ResultSet rs=DBManager.fetchQuery("select * from tourCard");
			while(rs.next())
			{
				
				TourCardId tcid=new TourCardId();
					tcid.setCno(rs.getLong("cno"));
					tcid.setTourId(1l);
					
				TourCard t=new TourCard();
				t.setTourcardid(tcid);
				
				if(rs.getString("cname").equalsIgnoreCase("y"))
				t.setCname(CardName.YELLOW);
				else
					t.setCname(CardName.RED);
				
				if(rs.getString("ctype").equalsIgnoreCase("1"))
					t.setCtype(CardType.FH);
				else if(rs.getString("ctype").equalsIgnoreCase("2"))
					t.setCtype(CardType.SH);
				else if(rs.getString("ctype").equalsIgnoreCase("G"))
					t.setCtype(CardType.GOLDEN);
				else if(rs.getString("ctype").equalsIgnoreCase("P"))
					t.setCtype(CardType.PENALTY);
				
				t.setCtime(rs.getTime("ctime"));
			
				Member barcodeId = (Member)session.get(Member.class,rs.getLong("barcodeId"));
				t.setMember(barcodeId);
				
				TourTeam ttId = (TourTeam)session.get(TourTeam.class,(long)rs.getLong("ttId"));
				t.setTourteam(ttId);
				
				Wmatch wmatch = getRecordWmatch((long)rs.getLong("mid"),1l);
				t.setWmatch(wmatch);
				
				t.setLogin(login);
				session.beginTransaction();
				session.save(t);
				session.getTransaction().commit();
				
			}
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in addTourCard : "+e);
		}
	}*/
	
	public void addManoftheMatch()
	{
		try
		{
			Session session=sessionFactory.openSession();
			Login login = (Login)session.get(Login.class,(long)17001);	
			ResultSet rs=DBManager.fetchQuery("select * from manofthematch");
			while(rs.next())
			{
				new DB2().insert("insert into manofthematch(mmid,barcodeId,tourId,ttId,mid,login_id) values("+rs.getInt("mmid")+","+rs.getInt("barcodeId")+","+rs.getInt("tourId")+","+rs.getInt("ttId")+","+rs.getInt("mid")+","+login.getLoginid()+");");
				
			}
			session.close();
		}
		catch(Exception e)
		{
			System.out.println("Error manofthematch: "+e);
		}
	}
	public void addTourCard()
	{
		try
		{
			CardType ct=null;
			CardName cn=null;
			Session session=sessionFactory.openSession();
			Login login = (Login)session.get(Login.class,(long)17001);	
			ResultSet rs=DBManager.fetchQuery("select * from tourCard");
			while(rs.next())
			{
				if(rs.getString("cname").equalsIgnoreCase("y"))
					cn=CardName.YELLOW;
					else
						cn=CardName.RED;
					
					if(rs.getString("ctype").equalsIgnoreCase("1"))
						ct=(CardType.FH);
					else if(rs.getString("ctype").equalsIgnoreCase("2"))
						ct=(CardType.SH);
					else if(rs.getString("ctype").equalsIgnoreCase("G"))
						ct=(CardType.GOLDEN);
					else if(rs.getString("ctype").equalsIgnoreCase("P"))
						ct=(CardType.PENALTY);
					//System.out.println("insert into tourCard(cno,tourId,cname,ctime,ctype,login_id,barcodeId,ttId,mid) values("+rs.getInt("cno")+","+rs.getInt("tourId")+",'"+rs.getTime("cname")+"','"+cn+"','"+ct+"',"+login.getLoginid()+","+rs.getInt("barcodeId")+","+rs.getInt("ttId")+","+rs.getInt("mid")+");");
				
				new DB2().insert("insert into tourCard(cno,tourId,cname,ctime,ctype,login_id,barcodeId,ttId,mid) values("+rs.getInt("cno")+","+rs.getInt("tourId")+",'"+cn+"','"+rs.getTime("ctime")+"','"+ct+"',"+login.getLoginid()+","+rs.getInt("barcodeId")+","+rs.getInt("ttId")+","+rs.getInt("mid")+");");
				
			}
			session.close();
		}
		catch(Exception e)
		{
			System.out.println("Error tourCard: "+e);
		}
	}
		public void addTourFoul()
		{
			try
			{
				GoalFoulType ft=null;
				
				Session session=sessionFactory.openSession();
				Login login = (Login)session.get(Login.class,(long)17001);	
				ResultSet rs=DBManager.fetchQuery("select * from tourFoul");
				while(rs.next())
				{
					if(rs.getString("ftype").equalsIgnoreCase("1"))
						ft=(GoalFoulType.FH);
					else if(rs.getString("ftype").equalsIgnoreCase("2"))
						ft=(GoalFoulType.SH);
					else if(rs.getString("ftype").equalsIgnoreCase("G"))
						ft=(GoalFoulType.GOLDEN);
					else if(rs.getString("ftype").equalsIgnoreCase("P"))
						ft=(GoalFoulType.PENALTY);
					else if(rs.getString("ftype").equalsIgnoreCase("OG"))
						ft=(GoalFoulType.OG);
					
					new DB2().insert("insert into tourFoul(fno,tourId,ftime,ftype,login_id,barcodeId,ttId,mid) values("+rs.getInt("fno")+","+rs.getInt("tourId")+",'"+rs.getTime("ftime")+"','"+ft+"',"+login.getLoginid()+","+rs.getInt("barcodeId")+","+rs.getInt("ttId")+","+rs.getInt("mid")+");");
					
				}
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Error : "+e);
			}
	}
		
		public void addTourGoal()
		{
			try
			{
				GoalFoulType ft=null;
				
				Session session=sessionFactory.openSession();
				Login login = (Login)session.get(Login.class,(long)17001);	
				ResultSet rs=DBManager.fetchQuery("select * from tourGoal");
				while(rs.next())
				{
						if(rs.getString("gtype").equalsIgnoreCase("1"))
							ft=(GoalFoulType.FH);
						else if(rs.getString("gtype").equalsIgnoreCase("2"))
							ft=(GoalFoulType.SH);
						else if(rs.getString("gtype").equalsIgnoreCase("G"))
							ft=(GoalFoulType.GOLDEN);
						else if(rs.getString("gtype").equalsIgnoreCase("P"))
							ft=(GoalFoulType.PENALTY);
						else if(rs.getString("gtype").equalsIgnoreCase("OG"))
							ft=(GoalFoulType.OG);
					
					new DB2().insert("insert into tourGoal(gno,tourId,gtime,gtype,login_id,barcodeId,ttId,mid) values("+rs.getInt("gno")+","+rs.getInt("tourId")+",'"+rs.getTime("gtime")+"','"+ft+"',"+login.getLoginid()+","+rs.getInt("barcodeId")+","+rs.getInt("ttId")+","+rs.getInt("mid")+");");
					
				}
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Error : "+e);
			}
	}
}