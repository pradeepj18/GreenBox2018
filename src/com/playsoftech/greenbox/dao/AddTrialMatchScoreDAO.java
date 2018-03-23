package com.playsoftech.greenbox.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Events;
import com.playsoftech.greenbox.pojo.RefreeMarksMatch;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;
import com.playsoftech.greenbox.pojo.TrialRefreeLogin;
import com.playsoftech.greenbox.pojo.Trials;

public class AddTrialMatchScoreDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public void addTrialMatchScore(long reflogin_id,long trial_id,int score,long event_id,long tmr_id)
	{
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			long refmm_id = getmaxRefreeMarksMatchId()+1;
			TrialRefreeLogin refreelogin = (TrialRefreeLogin)session.get(TrialRefreeLogin.class, reflogin_id);
			Trials trials = (Trials)session.get(Trials.class, trial_id);
			/*Events events = (Events)session.get(Events.class, event_id);*/
			TrialMemberRegistration trialMemberRegistration = (TrialMemberRegistration)session.get(TrialMemberRegistration.class, tmr_id);
			
			Date today = null;
			try {
				today = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RefreeMarksMatch refreeMarksMatch = new RefreeMarksMatch();
			refreeMarksMatch.setTrialMemberRegistration(trialMemberRegistration);
			refreeMarksMatch.setTrials(trials);
			refreeMarksMatch.setRefreelogin(refreelogin);
			refreeMarksMatch.setRef_marks(score);
			refreeMarksMatch.setRefmm_id(refmm_id);
			refreeMarksMatch.setDate(today);
			refreeMarksMatch.setTime(java.util.Calendar.getInstance().getTime());
			session.save(refreeMarksMatch);
			session.getTransaction().commit();
			
			session.beginTransaction();
			
			trialMemberRegistration.setPlayerstatus(TrialPlayerStatus.PLAYED);
			
			session.save(trialMemberRegistration);
			session.getTransaction().commit();
			
			
			session.close();
		}
		catch(HibernateException e){
			System.out.println("AddTrialMatchScoreDAO Exception "+e.getMessage());
		}
		
	}
	public long getmaxRefreeMarksMatchId()
	{
		long max=0;
		try
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			//session.createCriteria(Tournament.class).addOrder(Order.desc("tour_id")).setMaxResults(1);
			Criteria cr=session.createCriteria(RefreeMarksMatch.class);
			Projection max1=Projections.max("refmm_id");
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
