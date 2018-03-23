package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.playsoftech.greenbox.dao.AddPhysicalPlayerDAO;

@WebServlet("/AddPhysicalPlayer")
public class AddPhysicalPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameterValues("tmr_id[]")!=null)
			{
				String id[] = request.getParameterValues("tmr_id[]");
				AddPhysicalPlayerDAO addPhysicalPlayerDAO = new AddPhysicalPlayerDAO();
				addPhysicalPlayerDAO.addPhysicalPlayer(id);
			}
		}
		catch(Exception e) {
			System.out.println("AddPhysicalPlayer servlet :"+e.getMessage());
		}
	}

}
