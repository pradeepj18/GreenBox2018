package com.playsoftech.greenbox.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.playsoftech.greenbox.dao.CheckLoginDAO;


@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			long trial_id = 3; //Make it dynamic
			
			CheckLoginDAO checkLoginDAO = new CheckLoginDAO();
			long reglogin_id = checkLoginDAO.checkLogin(username, password, trial_id);
			HttpSession session = request.getSession();
			boolean flag = true;
			if(reglogin_id > 0){
				flag = false;
				
				session.setAttribute("reflogin_id", reglogin_id);
				response.sendRedirect("Trials/team.jsp");
				
			}
			if(flag)
			{
				long oper_login = checkLoginDAO.checkOperatorLogin(username,password,trial_id);
				if(oper_login > 0){
					flag = false;
					session.setAttribute("login_id", oper_login);
					response.sendRedirect("Trials/teamlist.jsp");
				}
			}
			if(flag){
			
				response.sendRedirect("Trials/index.jsp");
			}
		}
		catch(Exception e){
			System.out.println("LoginValidation servlet"+e.getMessage());
			response.sendRedirect("Trials/index.jsp");
		}
	}

}
