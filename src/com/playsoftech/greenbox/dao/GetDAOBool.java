package com.playsoftech.greenbox.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Member;
import com.playsoftech.greenbox.pojo.PhysicalEventMarks;
import com.playsoftech.greenbox.pojo.PlayerStatus;
import com.playsoftech.greenbox.pojo.RefreeMarksMatch;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;

public class GetDAOBool {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public boolean isTrialPlayerAppeared(long trial_id,long tmr_id){
		boolean flag = false;
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
			criteria.add(Restrictions.eq("tmr_id", tmr_id));
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.eq("playerstatus",TrialPlayerStatus.APPEARING));
			disjunction.add(Restrictions.eq("playerstatus",TrialPlayerStatus.INJURED));
			
			criteria.add(disjunction);
			
			List<?> trialMemberReg = criteria.list();
			if(trialMemberReg.size() > 0){
				flag = true;
			}
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOBool isTrialPlayerAppeared(long trial_id,long tmr_id) "+e.getMessage());
		}
		return flag;
	}
	
	public boolean isTrialPlayerAppearedPhy(long trial_id,long tmr_id){
		boolean flag = false;
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PhysicalEventMarks.class);
			criteria.add(Restrictions.eq("trialMemberRegistration.tmr_id", tmr_id));
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.eq("playerstatus",TrialPlayerStatus.APPEARING));
			disjunction.add(Restrictions.eq("playerstatus",TrialPlayerStatus.INJURED));
			
			criteria.add(disjunction);
			
			List<?> trialMemberReg = criteria.list();
			if(trialMemberReg.size() > 0){
				flag = true;
			}
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOBool isTrialPlayerAppeared(long trial_id,long tmr_id) "+e.getMessage());
		}
		return flag;
	}
	
	public boolean isPlayerExists(String name) {
		boolean flag = true;
		try
		{
			String split[] = name.split(" ");
			String fname="",mname="",lname="";
			if(split.length==3)
			{
				fname = split[0];
				mname = split[1];
				lname = split[2];
			}
			else if(split.length==2)
			{
				fname = split[0];
				lname = split[1];
			}
			else if(split.length==1)
			{
				fname = split[0];
			}
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(TrialMemberRegistration.class);
			
			cr.add(Restrictions.eq("fname", fname));
			cr.add(Restrictions.eq("mname", mname));
			cr.add(Restrictions.eq("lname", lname));
			
			if(cr.list().size()>0)
			{
				flag = false;
			}
			session.close();		
			
			//System.out.println("~~~~~~~~~~~~~~~~~~~~FOR "+name+"~~~~~~~~~~~~~~~~~~~~"+flag);
			
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOBool playerExists "+e);
		}
		return flag;
	}
	
	public boolean isTrialPlayerPlayedPhysical(long event_id,long trial_id,long tmr_id){
		boolean flag = false;
		try{
			
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PhysicalEventMarks.class);
			criteria.add(Restrictions.eq("trialMemberRegistration.tmr_id", tmr_id));
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			criteria.add(Restrictions.eq("events.event_id", event_id));
			criteria.add(Restrictions.eq("playerstatus", PlayerStatus.PLAYED));	
			
			List<?> trialMemberReg = criteria.list();
			if(trialMemberReg.size() > 0){
				flag = true;
			}
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOBool isTrialPlayerPlayedPhysical(long event_id,long trial_id,long tmr_id) "+e.getMessage());
		}
		return flag;
	}
	
	public boolean isTrialPlayerPlayedMatch(long trial_id,long tmr_id){
		boolean flag = false;
		try{
			
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(RefreeMarksMatch.class);
			criteria.add(Restrictions.eq("trialMemberRegistration.tmr_id", tmr_id));
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			
			List<?> trialMemberReg = criteria.list();
			if(trialMemberReg.size() > 0){
				flag = true;
			}
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOBool isTrialPlayerPlayedMatch(long trial_id,long tmr_id) "+e.getMessage());
		}
		return flag;
	}
	
	public boolean isDribblingGoal(long event_id,long trial_id,long tmr_id){
		boolean flag = false;
		try{
			
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PhysicalEventMarks.class);
			criteria.add(Restrictions.eq("trialMemberRegistration.tmr_id", tmr_id));
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			criteria.add(Restrictions.eq("events.event_id", event_id));
			List<?> trialMemberReg = criteria.list();
			if(trialMemberReg.size() > 0){
				Iterator<?> itr = trialMemberReg.iterator();
				if(itr.hasNext())
				{
					PhysicalEventMarks phy = (PhysicalEventMarks)itr.next();
					if(phy.getScore()!=0)
						flag = true;
					else
						flag = false;
				}
				
			}
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("GetDAOBool isTrialPlayerPlayedMatch(long trial_id,long tmr_id) "+e.getMessage());
		}
		return flag;
	}
	
}
