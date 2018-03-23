package com.playsoftech.greenbox.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.Login;
import com.playsoftech.greenbox.pojo.TrialRefreeLogin;

public class CheckLoginDAO {
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public long checkLogin(String username,String password,long trial_id){
		long reglogin_id = 0;
		try{
			Session session = sessionFactory.openSession();
			
			Criteria cr = session.createCriteria(TrialRefreeLogin.class);
			cr.add(Restrictions.eq("user_name", username));
			cr.add(Restrictions.eq("password", password));
			cr.add(Restrictions.eq("trial_id", trial_id));
			
			if(cr.list().size() > 0){
				List<?> reflist = cr.list();
				Iterator<?> refIt = reflist.iterator();
				TrialRefreeLogin refLogin = (TrialRefreeLogin)refIt.next();
				reglogin_id = refLogin.getReglogin_id();
			}
			
			session.close();
		}
		catch(HibernateException e){
			System.out.println("CheckLoginDAO getRefreeLogin() "+e.getMessage()); 
		}
		return reglogin_id;
	}
	
	public long checkOperatorLogin(String username,String password,long trial_id){
		long oper_login = 0;
		try{
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Login.class);
			cr.add(Restrictions.eq("username", username));
			cr.add(Restrictions.eq("password", password));
			cr.add(Restrictions.eq("trial_id", trial_id));
		
			if(cr.list().size() > 0){
				
				List<?> reflist = cr.list();
				Iterator<?> refIt = reflist.iterator();
				Login login = (Login)refIt.next();
				oper_login = login.getLoginid();
				
			}
			
			session.close();
			
		}
		catch(HibernateException e){
			System.out.println("CheckLoginDAO checkOperatorLogin: "+e.getMessage());
		}
		return oper_login;
	}
}
