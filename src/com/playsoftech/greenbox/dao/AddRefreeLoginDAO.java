package com.playsoftech.greenbox.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.playsoftech.greenbox.Config.ConfigManager;
import com.playsoftech.greenbox.pojo.TrialRefreeLogin;

public class AddRefreeLoginDAO {
	
	SessionFactory sessionFactory = ConfigManager.getSessionFactory();
	public void addRefreeLogin(){
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			TrialRefreeLogin refreeLogin = new TrialRefreeLogin(); 
			refreeLogin.setReglogin_id(1);
			refreeLogin.setFname("Anand");
			refreeLogin.setLname("Singh");
			refreeLogin.setUser_name("sanand404@gmail.com");
			refreeLogin.setPassword("123Anand");
			refreeLogin.setTrial_id(1);
			
			session.save(refreeLogin);
			
			session.getTransaction().commit();
			session.close();
			
		}
		catch(HibernateException e){
			System.out.println("AddRefreeLoginDAO addRefreeLogin() :"+e.getMessage());
		}
	}
}
