package com.playsoftech.greenbox.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Events;

public class AddEventDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public boolean addEvent(String event_name){
		boolean flag = false;
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			
			Events event = new Events();
			event.setEvent_name(event_name);
			event.setEvent_id(getmaxEventId()+1);
			
			session.save(event);
			session.getTransaction().commit();
			session.close();
			flag = true;
		}
		catch(HibernateException e){
			System.out.println("AddEventDAO Exception :"+e.getMessage());
		}
		return flag;
	}
	public long getmaxEventId()
	{
		long max=0;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			//session.createCriteria(Tournament.class).addOrder(Order.desc("tour_id")).setMaxResults(1);
			Criteria cr=session.createCriteria(Events.class);
			Projection max1=Projections.max("event_id");
			cr.setProjection(max1);
			Object max2= cr.uniqueResult();
			if(max2==null)
				max=0;
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
