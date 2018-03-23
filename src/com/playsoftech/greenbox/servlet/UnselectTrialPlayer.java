package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.playsoftech.greenbox.dao.UnselectTrialPlayerDAO;

@WebServlet("/UnselectTrialPlayer")
public class UnselectTrialPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long trial_id = Long.parseLong(request.getParameter("trial_id"));
			String[] selectedPlayer = request.getParameterValues("player");
			
			UnselectTrialPlayerDAO unselectTrialPlayerDAO = new UnselectTrialPlayerDAO();
			unselectTrialPlayerDAO.unselectTrialPlayerPhy(selectedPlayer,trial_id);
			response.sendRedirect("Trials/unselectedPlayer.jsp");
		}
		catch(Exception e) {
			System.out.println("Servlet UnselectPlayer "+e.getMessage());
		}
	}

}
