package com.playsoftech.greenbox.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.PhysicalEventMarkId;
import com.playsoftech.greenbox.pojo.PhysicalEventMarks;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;

public class AddPhysicalPlayerDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public void addPhysicalPlayer(String id[]) {
		try {
			
			long trial_id = Long.parseLong(id[id.length-2]);
			long event_id = Long.parseLong(id[id.length-1]);	
			System.out.println("Event :"+event_id);
			System.out.println("Trial :"+trial_id);
			
			Session session = sessionFactory.openSession();
			
			for(int i = 0;i < id.length-2;i++)
			{
				session.beginTransaction();
				
				TrialMemberRegistration tmr = (TrialMemberRegistration)session.get(TrialMemberRegistration.class, Long.parseLong(id[i]));
				PhysicalEventMarks physicalEventMarks = new PhysicalEventMarks();
				
				PhysicalEventMarkId physicalEventMarkId = new PhysicalEventMarkId();
				physicalEventMarkId.setEvent_id(event_id);
				physicalEventMarkId.setTmr_id(tmr.getTmr_id());
				physicalEventMarkId.setTrial_id(trial_id);
				
				physicalEventMarks.setTrialRegEventId(physicalEventMarkId);
				physicalEventMarks.setPlayerstatus(TrialPlayerStatus.APPEARING);
				
				session.save(physicalEventMarks);
				session.getTransaction().commit();
			}
			
			session.close();
		}
		catch(HibernateException e) {
			System.out.println("AddPhysicalPlayerDAO addPhysicalPlayer() "+e.getMessage()); 
		}
	}
}
