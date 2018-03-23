package com.playsoftech.greenbox.dao;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.TrialRefreeLogin;

public class AddTrialsRefreeDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public boolean addTrialsRefree(long reglogin_id,String fname,String mname,String lname,Date dob,String imagePath,Date reg_date,long trial_id){	
		boolean flag = false;
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			TrialRefreeLogin trialsRef = new TrialRefreeLogin();
			
			trialsRef.setReglogin_id(reglogin_id);
			trialsRef.setFname(fname);
			trialsRef.setMname(mname);
			trialsRef.setLname(lname);
			trialsRef.setDob(dob);
			trialsRef.setPhoto(imagePath);
			trialsRef.setReg_date(reg_date);
			trialsRef.setTrial_id(trial_id);
			int rand_int = ThreadLocalRandom.current().nextInt(10000);
			trialsRef.setUser_name(fname+""+reglogin_id);
			trialsRef.setPassword(fname+""+rand_int);
			
			session.save(trialsRef);
			
			session.getTransaction().commit();
			session.close();
			flag = true;
		}
		catch(HibernateException e){
			
		}
		return flag;
	}
	public long getmaxTrialsRefreeid()
	{
		long max=1;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			//session.createCriteria(Tournament.class).addOrder(Order.desc("tour_id")).setMaxResults(1);
			Criteria cr=session.createCriteria(TrialRefreeLogin.class);
			Projection max1=Projections.max("reglogin_id");
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
