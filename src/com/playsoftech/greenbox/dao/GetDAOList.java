package com.playsoftech.greenbox.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Events;
import com.playsoftech.greenbox.pojo.Login;
import com.playsoftech.greenbox.pojo.MemStatus;
import com.playsoftech.greenbox.pojo.Member;
import com.playsoftech.greenbox.pojo.PhysicalEventMarks;
import com.playsoftech.greenbox.pojo.PlayerStatus;
import com.playsoftech.greenbox.pojo.RefreeMarksMatch;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;
import com.playsoftech.greenbox.pojo.TrialRefreeLogin;

public class GetDAOList {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public List<?> getTrialMember(long trial_id)
	{
		List<?> getTrialMember = null;
		try
		{
			Session session  = sessionFactory.openSession();
			
			Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.eq("playerstatus", TrialPlayerStatus.APPEARING));
			disjunction.add(Restrictions.eq("playerstatus", TrialPlayerStatus.NOTPLAYED));
			disjunction.add(Restrictions.eq("playerstatus", TrialPlayerStatus.INJURED));
			
			criteria.add(disjunction);
			
			getTrialMember = criteria.list();
			
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOList getTrialMember"+e.getMessage());
		}
		return getTrialMember;
	}
	
	/*Display List of selected TrialMember*/
	
	public List<?> getTrialMemberSelected(long trial_id){
		List<?> getTrialMemberSelected = null;
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			criteria.add(Restrictions.eq("playerstatus", TrialPlayerStatus.APPEARING));
			
			getTrialMemberSelected = criteria.list();
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOList getTrialMemberSelected :"+e.getMessage());
		}
		return getTrialMemberSelected; 
	}
	
	public List<?> getTrialMemberSelected1(long trial_id)
	{
		
		 List<?> memlist=null;
		 try{
				Session session = sessionFactory.openSession();
				
				Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
				criteria.add(Restrictions.eq("trials.trial_id", trial_id));
				criteria.add(Restrictions.eq("playerstatus", TrialPlayerStatus.APPEARING));
				criteria.addOrder(Order.asc("dummy_id"));
				
				/*Query criteria = session.createQuery("from TrialMemberRegistration where trial_id=:trial_id and playerstatus = 'APPEARING' order by dummy_id asc")
													.setLong("trial_id", trial_id);
				*/
				memlist = criteria.list();
				
				session.close();
			}
			catch(HibernateException e){
				System.out.println("GetDAOList getTrialMemberSelected1 :"+e.getMessage());
			}
		 return memlist;
		
	}
	
	public List<?> getTrialMemberAllSelected(long trial_id){
		List<?> getTrialMemberSelected = null;
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PhysicalEventMarks.class);
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.eq("playerstatus", TrialPlayerStatus.APPEARING));
			disjunction.add(Restrictions.eq("playerstatus", TrialPlayerStatus.INJURED));
			
			criteria.add(disjunction);
			getTrialMemberSelected = criteria.list();
			
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOList getTrialMemberAllSelected :"+e.getMessage());
		}
		return getTrialMemberSelected; 
	}
	
	public TrialMemberRegistration getTrialMemberRegistration(long tmr_id,long trial_id)
	{
		TrialMemberRegistration tmr = null;
		try
		{
			Session session = sessionFactory.openSession();
			
			Criteria cr =session.createCriteria(TrialMemberRegistration.class);
			cr.add(Restrictions.eq("trials.trial_id", trial_id));
			cr.add(Restrictions.eq("tmr_id", tmr_id));
			
			if(cr.list().size()>0)
			{
				tmr = (TrialMemberRegistration)cr.list().iterator().next();
			}
			
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOList getTrialMemberRegistration "+e);
		}
		return tmr;
	}
	
	public List<?> getTrialMemberList(long trial_id){
		List<?> getTrialMemberSelected = null;
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			//criteria.add(Restrictions.ne("position","goalkeeper"));
			
			getTrialMemberSelected = criteria.list();
			
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOList getTrialMemberAllSelected :"+e.getMessage());
		}
		return getTrialMemberSelected; 
	}
	
	
	public List<?> getTrials() {
		List<?> trial = null;
		try {
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query cr=session.createQuery("from Trials where YEAR(trial_date) = YEAR(:year)")
												.setDate("year", new Date());
			 trial = cr.list();

			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			System.out.print("Error in DAOlist get Trials : " + e);
		}
		return trial;
	}
	
	public List<?> getAllOldMembers(){
		List<?> member = null;
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria query=session.createCriteria(Member.class);
			query.add(Restrictions.eq("memStatus", MemStatus.OLD));
			member = query.list();
			
			session.getTransaction().commit();
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOlist getmember error:"+e.getMessage());
		}
		return member;
	}
	public List<?> getOldMember(long barcodeId){
		List<?> member = null;
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria query=session.createCriteria(Member.class);
			query.add(Restrictions.eq("memStatus", MemStatus.OLD));
			query.add(Restrictions.eq("barcodeId", barcodeId));
			member = query.list();
			
			session.getTransaction().commit();
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOlist getNEWmember error:"+e.getMessage());
		}
		return member;
	}
	public boolean getNewTrialMember(long barcodeId){
		boolean flag=false;
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria query=session.createCriteria(TrialMemberRegistration.class);
			query.add(Restrictions.eq("memstatus", MemStatus.OLD));
			query.add(Restrictions.eq("barcode_id", barcodeId));
			
			if(query.list().isEmpty() || query.list().size()==0)
			{
				flag=true;
			}
			
			session.getTransaction().commit();
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOlist getOLDmember error:"+e.getMessage());
		}
		return flag;
	}
	public List<?> getTrialEvent(long event_id){
		List<?> event=null;
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria query=session.createCriteria(Events.class);
			query.add(Restrictions.eq("event_id", event_id));
			event=query.list();
			session.getTransaction().commit();
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOlist getTrialEvent error:"+e.getMessage());
		}
		return event;
		
	}
	
	public List<?> getEvents(){
		List<?> events = null;
		try {
			Session session = sessionFactory.openSession();
			
			Criteria cr = session.createCriteria(Events.class);
			events = cr.list();
			session.close();
			
		}
		catch(HibernateException e) {
			System.out.println("GetDAOlist getEvents:"+e.getMessage());
		}
		return events;
	}
	
	public List<Long> getPhysicalTestPlayer(long trial_id,long event_id){
		List<Long> allMemberList = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			
			Criteria crTMR = session.createCriteria(TrialMemberRegistration.class);
			crTMR.add(Restrictions.eq("trials.trial_id", trial_id));
			List<?> allMemberList1 = crTMR.list();
			
			
			Iterator<?> allMemIt = allMemberList1.iterator();
			while(allMemIt.hasNext()) {
				TrialMemberRegistration tmr = (TrialMemberRegistration)allMemIt.next();
				allMemberList.add(tmr.getTmr_id());
				
			}
			
			
			//FOR INJURED
			Criteria crTMRIn = session.createCriteria(PhysicalEventMarks.class);
			crTMRIn.add(Restrictions.eq("trials.trial_id", trial_id));
			crTMRIn.add(Restrictions.eq("playerstatus", PlayerStatus.INJURED));
 			
			List<?> allMemberList2 = crTMRIn.list();
			
			List<Long> physicalInjuredPlayer = new ArrayList<>();
			
			Iterator<?> allMemItIn = allMemberList2.iterator();
			while(allMemItIn.hasNext()) {
				PhysicalEventMarks tmr = (PhysicalEventMarks)allMemItIn.next();
				physicalInjuredPlayer.add(tmr.getTrialMemberRegistration().getTmr_id());
				
			}
			
			Disjunction dis = Restrictions.disjunction();
			dis.add(Restrictions.eq("playerstatus", PlayerStatus.PLAYED));
			dis.add(Restrictions.eq("playerstatus", PlayerStatus.INJURED));
 			
			Criteria crPEM = session.createCriteria(PhysicalEventMarks.class);
			crPEM.add(Restrictions.eq("trials.trial_id", trial_id));
			crPEM.add(Restrictions.eq("events.event_id", event_id));
			crPEM.add(dis);
			
			List<Long> physicalMember = new ArrayList<>();
			List<?> phyEventMember = crPEM.list();
			allMemIt = null;
			allMemIt = phyEventMember.iterator();
			while(allMemIt.hasNext()) {
				
				PhysicalEventMarks tmr = (PhysicalEventMarks)allMemIt.next();
				physicalMember.add(tmr.getTrialMemberRegistration().getTmr_id());
			}
			
			allMemberList.removeAll(physicalMember);

			allMemberList.removeAll(physicalInjuredPlayer);
			session.close();
		}
		catch(HibernateException e) {
			System.out.println("GetDAOList getPhysicalTestPlayer "+e.getMessage());
		}
		return allMemberList;
		
	}
	/*FOR GOALKEEPER*/
	public List<Long> getPhysicalTestGoalkeeper(long trial_id,long event_id){
		List<Long> allMemberList = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			
			Criteria crTMR = session.createCriteria(TrialMemberRegistration.class);
			crTMR.add(Restrictions.eq("trials.trial_id", trial_id));
			crTMR.add(Restrictions.eq("position", "GOALKEEPER"));
			List<?> allMemberList1 = crTMR.list();
			
			
			Iterator<?> allMemIt = allMemberList1.iterator();
			while(allMemIt.hasNext()) {
				TrialMemberRegistration tmr = (TrialMemberRegistration)allMemIt.next();
				allMemberList.add(tmr.getTmr_id());
				
			}
			
			
			//FOR INJURED
			Criteria crTMRIn = session.createCriteria(PhysicalEventMarks.class);
			crTMRIn.add(Restrictions.eq("trials.trial_id", trial_id));
			crTMRIn.add(Restrictions.eq("playerstatus", PlayerStatus.INJURED));
 			
			List<?> allMemberList2 = crTMRIn.list();
			
			List<Long> physicalInjuredPlayer = new ArrayList<>();
			
			Iterator<?> allMemItIn = allMemberList2.iterator();
			while(allMemItIn.hasNext()) {
				PhysicalEventMarks tmr = (PhysicalEventMarks)allMemItIn.next();
				physicalInjuredPlayer.add(tmr.getTrialMemberRegistration().getTmr_id());
				
			}
			
			Disjunction dis = Restrictions.disjunction();
			dis.add(Restrictions.eq("playerstatus", PlayerStatus.PLAYED));
			dis.add(Restrictions.eq("playerstatus", PlayerStatus.INJURED));
 			
			Criteria crPEM = session.createCriteria(PhysicalEventMarks.class);
			crPEM.add(Restrictions.eq("trials.trial_id", trial_id));
			crPEM.add(Restrictions.eq("events.event_id", event_id));
			crPEM.add(dis);
			
			List<Long> physicalMember = new ArrayList<>();
			List<?> phyEventMember = crPEM.list();
			allMemIt = null;
			allMemIt = phyEventMember.iterator();
			while(allMemIt.hasNext()) {
				
				PhysicalEventMarks tmr = (PhysicalEventMarks)allMemIt.next();
				physicalMember.add(tmr.getTrialMemberRegistration().getTmr_id());
			}
			
			allMemberList.removeAll(physicalMember);

			allMemberList.removeAll(physicalInjuredPlayer);
			session.close();
		}
		catch(HibernateException e) {
			System.out.println("GetDAOList getPhysicalTestGoalkeeper "+e.getMessage());
		}
		return allMemberList;
		
	}
	
	
	public List<?> getPhysicalTestPlayerByEvent(long trial_id,long event_id){
		List<?> playerList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(PhysicalEventMarks.class);
			cr.add(Restrictions.eq("trials.trial_id", trial_id));
			cr.add(Restrictions.eq("events.event_id", event_id));
			cr.add(Restrictions.eq("playerstatus", TrialPlayerStatus.APPEARING));
			
			playerList = cr.list();
			session.close();
		}
		catch(HibernateException e) {
			
			System.out.println("GetDAOList getPhysicalPlayerByEvent()"+e.getMessage());
		}
		return playerList;
	}
	
	public Login getLogin(long login_id,long trial_id) {
		Login login = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Login.class);
			cr.add(Restrictions.eq("loginid", login_id));
			cr.add(Restrictions.eq("trial_id", trial_id));
			
			List<?> loginList = cr.list();
			Iterator<?> loginIt = loginList.iterator();
			if(loginIt.hasNext()) {
				login = (Login)loginIt.next();
			}
		}
		catch(HibernateException e) {
			System.out.println("GetDAOList getLogin() "+e);
		}
		return login;
	}
	public TrialRefreeLogin getTrialRefree(long refree_id,long trial_id)
	{
		TrialRefreeLogin refree = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(TrialRefreeLogin.class);
			cr.add(Restrictions.eq("reglogin_id", refree_id));
			cr.add(Restrictions.eq("trial_id", trial_id));
			
			List<?> loginList = cr.list();
			Iterator<?> loginIt = loginList.iterator();
			if(loginIt.hasNext()) {
				refree = (TrialRefreeLogin)loginIt.next();
			}
			session.close();
		}
		catch(HibernateException e) {
			System.out.println("GetDAOList getTrialRefree() "+e);
		}
		return refree;
	}
	
	public Member getMemberExisting(String fname,String mname,String lname)
	{
		Member mem = null;
		try
		{
			
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Member.class);
			if(lname.length() == 0 && mname.length()==0)
			{
				cr.add(Restrictions.eq("fName", fname));
				
			}
			else if(mname.length() == 0 )
			{
				cr.add(Restrictions.eq("fName", fname));
				cr.add(Restrictions.eq("lName", lname));
			}
			else 
			{
				cr.add(Restrictions.eq("fName", fname));
				cr.add(Restrictions.eq("mName", mname));
				cr.add(Restrictions.eq("lName", lname));
			}
			
			List<?> memberlist = cr.list();
			Iterator<?> memberitr = memberlist.iterator();
			if(memberitr.hasNext()) {
				mem = (Member)memberitr.next();
			}
			//System.out.println(mem.getBarcodeId() + " Fname : "+mem.getfName());
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOList getMemberExisting "+e);
		}
		return mem;
	}
	public List<?> getTrialMemberBatch(long trial_id,String batch){
		List<?> getTrialMemberSelected = null;
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			criteria.add(Restrictions.eq("batch", batch));
			
			getTrialMemberSelected = criteria.list();
			session.close();
		}
		catch(HibernateException e){
			System.out.println("GetDAOList getTrialMemberBatch :"+e.getMessage());
		}
		return getTrialMemberSelected; 
	}
	
	public List<?> getTrialRefree(long trial_id)
	{
		List<?> loginList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(TrialRefreeLogin.class);
			
			cr.add(Restrictions.eq("trial_id", trial_id));
			
			 loginList = cr.list();
			
			session.close();
		}
		catch(HibernateException e) {
			System.out.println("GetDAOList getTrialRefree() "+e);
		}
		return loginList;
	}
	
	public double getTrialMatchMarks(long tmr_id,long trial_id)
	{
		
		double marks = 0.0;
		try
		{
			
			Session session = sessionFactory.openSession();
			
			ProjectionList pl = Projections.projectionList();
			
			pl.add(Projections.groupProperty("trialMemberRegistration.tmr_id"));
			pl.add(Projections.sum("ref_marks").as("score"));
			
			Criteria cr = session.createCriteria(RefreeMarksMatch.class);
			cr.add(Restrictions.eq("trials.trial_id", trial_id));
			cr.add(Restrictions.eq("trialMemberRegistration.tmr_id", tmr_id));
			cr.setProjection(pl);
			
			List<?> list = cr.list();
			for(Object data:list)
			{
				Object[] obj = (Object[]) data;
				/*for(int i=0;i<obj.length;i++)
				System.out.println(i+"+++"+obj[i]);*/
				marks = Double.parseDouble(obj[1].toString());
				
			}
			//marks = marks/4;
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOList getTrialMatchMarks() "+e);
		}
		return marks;
	}
	
	public double getEventMarks(long trial_id,long tmr_id,long event_id)
	{
		double marks =0.0;
		try
		{
			Session session = sessionFactory.openSession();
			
			Criteria cr = session.createCriteria(PhysicalEventMarks.class);
			cr.add(Restrictions.eq("trials.trial_id", trial_id));
			cr.add(Restrictions.eq("trialMemberRegistration.tmr_id", tmr_id));
			cr.add(Restrictions.eq("events.event_id", event_id));
			
			List<?> list = cr.list();
			if(list.iterator().hasNext())
			{
				PhysicalEventMarks pme = (PhysicalEventMarks)list.iterator().next();
				marks = pme.getMark();
			}
			
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOList getEventMarks "+e);
		}
		return marks;
	}
	
	public List<?> getPlayedPlayerRefreeMarks(long trial_id,Date tdate)
	{
		List<?> list = null;
		try
		{
			Session session = sessionFactory.openSession();
			
			/*ProjectionList pl = Projections.projectionList();
			
			pl.add(Projections.groupProperty("trialMemberRegistration.tmr_id"));
			
			Criteria cr = session.createCriteria(RefreeMarksMatch.class);
			cr.add(Restrictions.eq("trials.trial_id", trial_id));
			cr.add(Restrictions.eq("date", tdate));
			cr.setProjection(pl);*/
			
			Query cr = session.createQuery("from RefreeMarksMatch where trial_id=:trial_id and date=:date group by tmr_id")
										.setLong("trial_id", trial_id)
										.setDate("date", tdate);
			list = cr.list();
			
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOList getEventMarks "+e);
		}
		return list;
	}

public List<?> getPlayedPlayerPhysicalMarks(long trial_id,Date tdate)
{
	List<?> list = null;
	try
	{
		Session session = sessionFactory.openSession();
		
		/*ProjectionList pl = Projections.projectionList();
		
		pl.add(Projections.groupProperty("trialMemberRegistration.tmr_id"));
		
		Criteria cr = session.createCriteria(RefreeMarksMatch.class);
		cr.add(Restrictions.eq("trials.trial_id", trial_id));
		cr.add(Restrictions.eq("date", tdate));
		cr.setProjection(pl);*/
		
		Query cr = session.createQuery("from PhysicalEventMarks where trial_id=:trial_id and date=:date group by tmr_id")
									.setLong("trial_id", trial_id)
									.setDate("date", tdate);
		list = cr.list();
		
		session.close();
	}
	catch(HibernateException e)
	{
		System.out.println("GetDAOList getEventMarks "+e);
	}
	return list;
}
}
