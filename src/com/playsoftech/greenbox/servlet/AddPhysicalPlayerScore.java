package com.playsoftech.greenbox.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.playsoftech.greenbox.dao.AddPysicalEventScoreDAO;

@WebServlet("/AddPhysicalPlayerScore")
public class AddPhysicalPlayerScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			long trial_id = Long.parseLong(request.getParameter("trial_id"));
			HttpSession httpSession = request.getSession();
			long reflogin_id = Long.parseLong(httpSession.getAttribute("login_id").toString());
			
			AddPysicalEventScoreDAO addTrialPlayerScoreDAO = new AddPysicalEventScoreDAO();	
			long event_id = Long.parseLong(request.getParameter("event_id"));
			
			if(event_id != 4) {
				String[] score = request.getParameterValues("marks");
				String[] ids = request.getParameterValues("ids");
				String[] injured = request.getParameterValues("injuredPlayer");
				
				addTrialPlayerScoreDAO.addTrialPlayerScore(reflogin_id, trial_id,event_id, ids, score, injured);
			}
			else {
				String[] score = request.getParameterValues("marks");
				String[] ids = request.getParameterValues("ids");
				String[] injured = request.getParameterValues("injuredPlayer");
				String[] time = request.getParameterValues("dribletime");
 			
				addTrialPlayerScoreDAO.addTrialPlayerScoreDrible(reflogin_id, trial_id,event_id, ids, score, injured,time);
				
			}
			
		    response.sendRedirect("Trials/physicalTest.jsp");  
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("AddTrialPhysicalPlayerScore Exception :"+e);
		}
	}

}
