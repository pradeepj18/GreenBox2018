package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.playsoftech.greenbox.dao.AddTrialTeamPlayerDAO;

@WebServlet("/AddTrialTeamPlayer")
public class AddTrialTeamPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			long trial_id = Long.parseLong(request.getParameter("trial_id"));
			String[] selectedPlayer = request.getParameterValues("player");
			
			AddTrialTeamPlayerDAO  addTrialTeamPlayerDAO = new AddTrialTeamPlayerDAO();
			for(int i=0;i < selectedPlayer.length;i++){
				long tmr_id = Long.parseLong(selectedPlayer[i]);
			
				if(addTrialTeamPlayerDAO.addTrialPlayer(trial_id, tmr_id)){
					/*System.out.println("TMR UPDATED:"+tmr_id);*/
				}
				
			}
			PrintWriter out=response.getWriter();
			out.println("<script type='text/javascript'>alert('Added Successfully!!!!');window.location.replace('Trials/teamlist.jsp');</script>");
		}
		catch(Exception e){
			System.out.println("AddTrialTeamPlayer servlet exception "+e.getMessage());
		}
	}

}
