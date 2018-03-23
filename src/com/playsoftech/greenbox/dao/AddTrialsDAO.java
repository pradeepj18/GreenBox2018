package com.playsoftech.greenbox.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.MatchStatus;
import com.playsoftech.greenbox.pojo.Trials;

public class AddTrialsDAO {

	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	boolean flag=false;
	public boolean addTrials(long trial_id,String trial_name,String trial_venue,Date trial_date)
	{
		
		try
		{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			Trials trials = new Trials();
			trials.setTrial_id(trial_id);
			trials.setTrial_date(trial_date);
			trials.setTrial_name(trial_name);
			trials.setTrial_venue(trial_venue);
			trials.setTrial_status(MatchStatus.UPCOMING);
			session.save(trials);
			
			session.getTransaction().commit();
			session.close();
			flag = true;
			
		}
		catch(HibernateException e)
		{
			System.out.println("Error in AddTrialDAO : "+e);
		}
		return flag;
	}
	public long getmaxTrialsSeasonInfoID()
	{
		long max=1;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			//session.createCriteria(Tournament.class).addOrder(Order.desc("tour_id")).setMaxResults(1);
			Criteria cr=session.createCriteria(Trials.class);
			Projection max1=Projections.max("trial_id");
			cr.setProjection(max1);
			Object max2= cr.uniqueResult();
			if(max2==null)
				max=1;
			else
				max = new Long((long) max2);
			session.getTransaction().commit();
			session.close();		
		}
		catch (HibernateException e)
		{e.getMessage();}
		return max;
	}
}
