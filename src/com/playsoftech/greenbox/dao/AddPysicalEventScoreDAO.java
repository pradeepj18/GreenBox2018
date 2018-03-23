package com.playsoftech.greenbox.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.playsoftech.greenbox.Config.ConfigManager;

import playsoftech.gb.PhysicalMarksCal;

public class AddPysicalEventScoreDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public void addTrialPlayerScore(long reflogin_id,long trial_id,long event_id,String[] ids,String[] score,String[] injured)
	{
		try{
			
			Session session = sessionFactory.openSession();
			if(event_id != 4 ) 
			{
				for(int i=0;i < score.length;i++)
				{
					//System.out.println("ID :"+ids[i]+" SCORE :"+score[i]);
					
					session.beginTransaction();
					long tmr_id = Long.parseLong(ids[i]);
					int count1 = 0;
					Date date = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
					String time1 = null;
					double marks = 0;
					if(event_id != 1 && event_id!=5)
					{
						SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
						formatter.setTimeZone(TimeZone.getTimeZone("IST"));
						time1 = formatter.format(new Date());
						count1 = Integer.parseInt(score[i]);
						marks = new PhysicalMarksCal().getPhysicalMarksCal(event_id, count1+"");
						System.out.println("MArks :"+marks);
					}
					else if(event_id==5)
					{
						count1 = Integer.parseInt(score[i]);	
						time1 = count1/60+":"+count1%60;
						marks = new PhysicalMarksCal().getPhysicalMarksCal(event_id, count1+"");
					}
					else //event 
					{
						try {
							String time=null;
							count1 = 0;
							String[] t = null;
							t = score[i].toString().split("\\.");
							if(t.length == 1)
							{
								time = t[0]+".00";
								time1 = t[0]+":00";
							}
							else if(t.length > 1) {
								time1 = t[0]+":"+t[1];
								time = t[0]+"."+t[1];
							}
							else 
							{
								String[] t1 = score[i].toString().split(":");
								time1 = t1[0]+":"+t1[1];
								time = t1[0]+"."+t1[1];
							}
							marks = new PhysicalMarksCal().getPhysicalMarksCal(event_id, time);
						}
						catch(Exception e) {
							System.out.println("Time error :"+e);
						}
					}
					Query query = session.createQuery("update PhysicalEventMarks set mark=:marks, score=:score, playerstatus=:playerstatus,date=:date,time=:time where trial_id=:trial_id and event_id=:event_id and tmr_id=:tmr_id")
													.setDouble("marks", marks)
													.setInteger("score", count1)
													.setDate("date", date)
													.setString("time", "00:"+time1)
													.setString("playerstatus", "PLAYED")
													.setLong("trial_id", trial_id)
													.setLong("event_id", event_id)
													.setLong("tmr_id", tmr_id);
													
					
					int row = query.executeUpdate();
					if(row > 0) {
						System.out.println("Updated "+row);
					}
					session.getTransaction().commit();
					
				}
				/*FOR INJURED*/
				if(injured !=null)
				{
					for(int i = 0;i < injured.length;i++)
					{
						session.beginTransaction();
						
						long tmr_id = Long.parseLong(injured[i]);
						Date date = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
						
						/*SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
						formatter.setTimeZone(TimeZone.getTimeZone("IST"));
						String time1 = formatter.format(new Date());
	*/
						
						Query query = session.createQuery("update PhysicalEventMarks set playerstatus=:playerstatus,date=:date where trial_id=:trial_id and event_id=:event_id and tmr_id=:tmr_id")
															.setDate("date", date)
															.setString("playerstatus", "INJURED")
															.setLong("trial_id", trial_id)
															.setLong("event_id", event_id)
															.setLong("tmr_id", tmr_id);
						int row = query.executeUpdate();
						if(row > 0) {
							System.out.println("Updated "+row);
						}			
						session.getTransaction().commit();
					}
				}
			}
			
			session.close();
			
		}
		
		catch(HibernateException e){
			e.printStackTrace();
			System.out.println("AddTrialPlayerScore DAO :"+e);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("AddTrialPlayerScore DAO :"+e);
		}
	}
	
	public void addTrialPlayerScoreDrible(long reflogin_id,long trial_id,long event_id,String[] ids,String[] score,String[] injured,String[] time)
	{
		try{
			Session session = sessionFactory.openSession();
				for(int i=0;i < score.length;i++)
				{
					System.out.println("ID :"+ids[i]+" SCORE :"+score[i]);
					String time2 = null;
					session.beginTransaction();
					long tmr_id = Long.parseLong(ids[i]);
					int count1 = Integer.parseInt(score[i]);
					double marks = 0;
					Date date = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
					String time1 = null;
					
					
						try {
							
							String[] t = null;
							t = time[i].toString().split("\\.");
							if(t.length == 1)
							{
								time2 = t[0]+".00";
								time1 = t[0]+":00";
							}
							else if(t.length > 1) {
								time1 = t[0]+":"+t[1];
								time2 = t[0]+"."+t[1];
							}
							else 
							{
								String[] t1 = time[i].toString().split(":");
								time1 = t1[0]+":"+t1[1];
								time2 = t1[0]+"."+t1[1];
							}
							marks = new PhysicalMarksCal().getPhysicalMarksCal(event_id, time2);
						}
						catch(Exception e) {
							System.out.println("Time error :"+e);
						}
					
					Query query = session.createQuery("update PhysicalEventMarks set mark=:marks,score=:score, playerstatus=:playerstatus,date=:date,time=:time where trial_id=:trial_id and event_id=:event_id and tmr_id=:tmr_id")
													.setDouble("marks", marks)
													.setInteger("score", count1)
													.setDate("date", date)
													.setString("time", "00:"+time1)
													.setString("playerstatus", "PLAYED")
													.setLong("trial_id", trial_id)
													.setLong("event_id", event_id)
													.setLong("tmr_id", tmr_id);
													
					
					int row = query.executeUpdate();
					if(row > 0) {
						System.out.println("Updated "+row);
					}
					session.getTransaction().commit();
					
				}
				/*FOR INJURED*/
				if(injured !=null)
				{
					for(int i = 0;i < injured.length;i++)
					{
						session.beginTransaction();
						
						long tmr_id = Long.parseLong(injured[i]);
						Date date = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
						
						/*SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
						formatter.setTimeZone(TimeZone.getTimeZone("IST"));
						String time1 = formatter.format(new Date());
	*/
						
						Query query = session.createQuery("update PhysicalEventMarks set playerstatus=:playerstatus,date=:date where trial_id=:trial_id and event_id=:event_id and tmr_id=:tmr_id")
															.setDate("date", date)
															.setString("playerstatus", "INJURED")
															.setLong("trial_id", trial_id)
															.setLong("event_id", event_id)
															.setLong("tmr_id", tmr_id);
						int row = query.executeUpdate();
						if(row > 0) {
							System.out.println("Updated "+row);
						}			
						session.getTransaction().commit();
					}
				}
			session.close();
			
		}
		
		catch(HibernateException e){
			e.printStackTrace();
			System.out.println("AddTrialPlayerScore DAO :"+e.getMessage());
		}
		catch(Exception e) {
			System.out.println("AddTrialPlayerScore DAO :"+e.getMessage());
		}
	}
	
}
