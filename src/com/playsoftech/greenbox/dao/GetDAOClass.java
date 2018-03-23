package com.playsoftech.greenbox.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Events;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;

public class GetDAOClass {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	
	public TrialMemberRegistration getTrialMemberRegistration(long tmr_id) {
		TrialMemberRegistration tmr = null;
		try {
			Session session = sessionFactory.openSession();
			
			tmr = (TrialMemberRegistration)session.get(TrialMemberRegistration.class, tmr_id);
			session.close();
		}
		catch(HibernateException e) {
			System.out.println("GetDAOClass getTrialMemberRegistration()"+e.getMessage());
		}
		return tmr;
	}
	
	public Events getEvents(long event_id) {
		Events event = null;
		try {
			Session session = sessionFactory.openSession();
			
			event = (Events)session.get(Events.class, event_id);
			session.close();
			
		}
		catch(HibernateException e) {
			System.out.println("GetDAOClass getEvents()"+e.getMessage());
		}
		return event;
		
	}
	
}
