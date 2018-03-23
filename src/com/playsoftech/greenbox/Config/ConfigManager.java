package com.playsoftech.greenbox.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class ConfigManager {
	private static SessionFactory sessionfactory = null;
	
	private static SessionFactory buildSessionFactory(){
		try{
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date date = fmt.parse("2017-12-31");
			if((date.compareTo(new Date()))==-1){
				
			}
			else
			{
				Configuration configuration = new Configuration().configure();
				System.out.println("Hibernate configuration file loaded.......");
				
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
				System.out.println("Hibernate service Created...");
				
				sessionfactory = configuration.buildSessionFactory(serviceRegistry);
				System.out.println("Hibernate service created");
				
			}
		}
		catch(HibernateException e){
			System.out.println("SesionFactory creation failed.."+e.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
			return sessionfactory;
	}
	public static SessionFactory getSessionFactory(){
		if(sessionfactory == null){
			sessionfactory = buildSessionFactory();
		}
		return sessionfactory;
	}
	
}
