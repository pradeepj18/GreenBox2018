package com.playsoftech.greenbox.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.PhysicalEventMarks;
import com.playsoftech.greenbox.pojo.PlayerStatus;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import com.playsoftech.greenbox.pojo.TrialPlayerStatus;

public class UnselectTrialPlayerDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public void unselectTrialPlayer(String selectedPlayer[],long trial_id) 
	{
		try
		{
			GetDAOList getDAOList = new GetDAOList();
			List<?> getTMR = getDAOList.getTrialMemberAllSelected(trial_id);
			List<Long> allSelectedId = new ArrayList<>();
			
			/*TO GET ALL APPEARING AND INJURED TRIAL PLAYER*/
			Iterator<?> allSelectedIt = getTMR.iterator();
			while(allSelectedIt.hasNext())
			{
				TrialMemberRegistration tmr = (TrialMemberRegistration)allSelectedIt.next();
				allSelectedId.add(tmr.getTmr_id());
			}
			
			/*TO GET ALL PLAYER FROM APPEARING AND INJURED WHICH ARE REMAIN SELECTED FROM THE LIST*/
			List<Long> selectedId = new ArrayList<>();
			for(int i = 0;i < selectedPlayer.length;i++) 
			{	
				selectedId.add(Long.parseLong(selectedPlayer[i]));
			}
			
			
			/*ALL PLAYER WHICH ARE UNSELECTED FROM THE LIST*/
			allSelectedId.removeAll(selectedId);
			Iterator<?> it = allSelectedId.iterator();
			Session session = sessionFactory.openSession();
			while(it.hasNext())
			{
				Long id = (Long)it.next();
				TrialMemberRegistration tmr = (TrialMemberRegistration)session.get(TrialMemberRegistration.class, id);
				session.beginTransaction();
				tmr.setPlayerstatus(TrialPlayerStatus.NOTPLAYED);
				session.getTransaction().commit();
			}
			session.close();
		}
		catch(HibernateException e)
		{
			System.out.println("UnselectTrialPlayerDAO unselectTrialPlayer "+e);
		}
	}
	
	public void unselectTrialPlayerPhy(String selectedPlayer[],long trial_id) 
	{
		try
		{
			
			Session session = sessionFactory.openSession();
			for(int i = 0;i < selectedPlayer.length;i++) 
			{	
				
				Long id = Long.parseLong(selectedPlayer[i]);
			
				session.beginTransaction();
				
				Query query = session.createQuery("delete from PhysicalEventMarks where playerstatus=:playerstatus and trial_id=:trial_id and tmr_id=:tmr_id")
												.setString("playerstatus", PlayerStatus.INJURED.toString())
												.setLong("trial_id", trial_id)
												.setLong("tmr_id", id);
				
				/*Query query = session.createQuery("update PhysicalEventMarks set playerstatus=:playerstatus where trial_id=:trial_id and tmr_id=:tmr_id")
												.setString("playerstatus", PlayerStatus.NOTPLAYED.toString())
												.setLong("trial_id", trial_id)
												.setLong("tmr_id", id);*/
			
				int row = query.executeUpdate();
				session.getTransaction().commit();
			}
			session.close();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			
			System.out.println("UnselectTrialPlayerDAO unselectTrialPlayerPhy "+e);
		}
	}
	
}
