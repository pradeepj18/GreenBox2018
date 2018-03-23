package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.playsoftech.greenbox.dao.AddTrialsDAO;

/**
 * Servlet implementation class AddTrialSeasonInfo
 */
@WebServlet("/AddTrialSeasonInfo")
public class AddTrialSeasonInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AddTrialSeasonInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try
		{
			long trial_id = new AddTrialsDAO().getmaxTrialsSeasonInfoID();
			String trial_name = request.getParameter("trial_name");
			String trial_venue = request.getParameter("trial_venue");
			Date trial_date =new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter("trial_date"));
			if(new AddTrialsDAO().addTrials(trial_id,trial_name,trial_venue,trial_date))
			{
				
				out.println("<script type='text/javascript'>alert('Trials Added Successfully !!!');window.location.replace('Trials/home.jsp');</script>");
			}
			else
			{
				out.println("<script type='text/javascript'>alert('Trials Cannot Add !!!');window.location.replace('Trials/AddTrialSeasonInfo.jsp');</script>");
			}
		}
		catch(Exception e)
		{
			out.println("<script type='text/javascript'>alert('Trials Cannot Add !!!');window.location.replace('Trials/AddTrialSeasonInfo.jsp');</script>");
			System.out.println("Error in AddTrialSeasonInfo : "+e);
		}
	}

}
