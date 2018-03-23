package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.playsoftech.greenbox.dao.AddTrialMatchScoreDAO;
import com.playsoftech.greenbox.dao.AddPysicalEventScoreDAO;


@WebServlet("/AddTrialMatchScore")
public class AddTrialMatchScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			long trial_id = 3;
			HttpSession httpSession = request.getSession();
			long reflogin_id = Long.parseLong(httpSession.getAttribute("reflogin_id").toString());
			
			String[] score = request.getParameterValues("marks");
			String[] ids = request.getParameterValues("ids");
			
			AddTrialMatchScoreDAO addTrialMatchScoreDAO = new AddTrialMatchScoreDAO(); 
			for(int i=0;i < score.length;i++)
			{
				addTrialMatchScoreDAO.addTrialMatchScore(reflogin_id, trial_id, Integer.parseInt(score[i]), 2, Long.parseLong(ids[i]));
			}
		    response.sendRedirect("Trials/team.jsp");  
		}
		catch(Exception e){
			System.out.println("AddTrialMatchScore Exception :"+e.getMessage());
			 response.sendRedirect("Trials/team.jsp");  
		}
	}

}
