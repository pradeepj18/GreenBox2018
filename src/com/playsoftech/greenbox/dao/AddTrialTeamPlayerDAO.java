package com.playsoftech.greenbox.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;

public class AddTrialTeamPlayerDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public boolean addTrialPlayer(long trial_id,long tmr_id){
		boolean flag = false;
		try{
			Session session = sessionFactory.openSession();
			
			TrialsmemregDAO trialmemDAO =  new TrialsmemregDAO();
			Criteria criteria = session.createCriteria(TrialMemberRegistration.class);
			criteria.add(Restrictions.eq("trials.trial_id", trial_id));
			criteria.add(Restrictions.eq("tmr_id", tmr_id));
			
			List<?> trialPlayer = criteria.list();
			Iterator<?> it = trialPlayer.iterator();
			while(it.hasNext()){
				TrialMemberRegistration trialMem = (TrialMemberRegistration)it.next();
				session.beginTransaction();
				
				TrialMemberRegistration tm = (TrialMemberRegistration)session.load(TrialMemberRegistration.class, trialMem.getTmr_id());
				
				tm.setPlayerstatus(TrialPlayerStatus.APPEARING);
				
				if(tm.getDummy_id()==0)
				{
					//System.out.println("Servlert dummy : "+trialmemDAO.getdummyid());
					tm.setDummy_id(trialmemDAO.getdummyid());
				}
				session.save(tm);
				session.getTransaction().commit();
				flag = true;
			}
			session.close();
			
		}
		catch(HibernateException e){
			System.out.println("AddTrialTeamPlayerDAO addTrialPlayer :"+e.getMessage());
		}
		return flag;
	}
}
