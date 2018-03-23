package com.playsoftech.greenbox.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Admin;
import com.playsoftech.greenbox.pojo.AuctionStatus;
import com.playsoftech.greenbox.pojo.Gender;
import com.playsoftech.greenbox.pojo.Login;
import com.playsoftech.greenbox.pojo.MemCategory;
import com.playsoftech.greenbox.pojo.MemStatus;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;
import com.playsoftech.greenbox.pojo.Trials;


public class TrialsmemregDAO {
	SessionFactory sessionFactory=ConfigManager.getSessionFactory();

	public boolean addNewMember(long tmr_id,long barcode_id,String fname,String mname,String lname,Date dob,Gender gender,String photo,long trial_id,MemCategory memcategory,MemStatus memstatus,String address,String position,String email,String batch,String mobile) {
		boolean flag=false;
		try {
			
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			Date regdate = null;
			try {
				regdate = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
			TrialMemberRegistration tmr=new TrialMemberRegistration();
			Trials trials=(Trials)session.get(Trials.class, trial_id);	
			
					tmr.setAuctionstatus(AuctionStatus.UNSELECTED);
					tmr.setBarcode_id(barcode_id);
					tmr.setDob(dob);
					tmr.setFname(fname.toUpperCase());
					tmr.setGender(gender);
					tmr.setLname(lname.toUpperCase());
					tmr.setMname(mname.toUpperCase());
					tmr.setMemcategory(memcategory);
					tmr.setRegdate(regdate);
					tmr.setTrials(trials);
					tmr.setTmr_id(tmr_id);
					tmr.setPhoto(photo);
					tmr.setPlayerstatus(TrialPlayerStatus.NOTPLAYED);
					tmr.setMemstatus(memstatus);
					tmr.setAddress(address);
					tmr.setEmail(email);
					tmr.setMobileno(mobile);
					tmr.setBatch(batch);
					tmr.setPosition(position);
					
					session.save(tmr);
					
			session.getTransaction().commit();
			session.close();
			flag=true;
		}
		catch(HibernateException e) {
			System.out.println("TrialsmemregDAO :"+e.getMessage());
		}
		return flag;
	}
	public long getmaxID()
	{
		long max=1;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr=session.createCriteria(TrialMemberRegistration.class);
			Projection max1=Projections.max("barcode_id");
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
	
	public long getmaxtmrID()
	{
		long max=100;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr=session.createCriteria(TrialMemberRegistration.class);
			Projection max1=Projections.max("tmr_id");
			cr.setProjection(max1);
			Object max2= cr.uniqueResult();
			if(max2==null)
				max=100;
			else
				max = new Long((long) max2);
			session.getTransaction().commit();
			session.close();		
		}
		catch (HibernateException e)
		{e.getMessage();}
		return max+1;
	}
	
	
	public long getdummyid()
	{
		long max=1;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr=session.createCriteria(TrialMemberRegistration.class);
			Projection max1=Projections.max("dummy_id");
			cr.setProjection(max1);
			Object max2= cr.uniqueResult();
			if(max2==null)
				max=1;
			else
				max = new Long((long) max2);
			//System.out.println("getdummyid : "+max);
			session.getTransaction().commit();
			session.close();		
		}
		catch (HibernateException e)
		{e.getMessage();}
		return max+1;
	}
	
}
