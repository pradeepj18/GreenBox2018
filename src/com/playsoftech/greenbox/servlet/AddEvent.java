package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.playsoftech.greenbox.dao.AddEventDAO;

@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String event_name= request.getParameter("event_name");
			AddEventDAO addEventDAO = new AddEventDAO();
			PrintWriter out=response.getWriter();
			if(addEventDAO.addEvent(event_name)){
				out.println("<script type='text/javascript'>alert('Event Added Successfully !!!');window.location.replace('Trials/AddTrialEvent.jsp');</script>");
			}
			else{
				out.println("<script type='text/javascript'>alert('Event Error !!!');window.location.replace('Trials/AddTrialEvent.jsp');</script>");
			}
		}
		catch(Exception e){
			System.out.println("AddEvent Servlet Exception"+e.getMessage());
		}
	}

}
