package com.rewritingmolequiz.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewritingmole.models.User;
import com.rewritingmolequiz.dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
	}
    
	
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
		String username = httpServletRequest.getParameter("username") ;
		String password = httpServletRequest.getParameter("password");
		UserDao dao =  new UserDao();
		HttpSession session ;
		
		 if(dao.getUser(username, password) == null) {
				// invalid credentials
			 session = httpServletRequest.getSession();
			 session.setAttribute("loginerror",  "<div class=\"alert alert-danger alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
		   				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
		   				"           	 Invalid Credentials  alert\n" + 
		   				"           </div>");
			 httpServletResponse.sendRedirect("Login.jsp");
			}
			else {
				//valid credentials 
				User user = dao.getUser(username, password);
				System.out.println(user.getRole());
				//Authorization and Redirection
				//student
				if(user.getRole().equalsIgnoreCase("student")) {
					//redirect to student view loader
					//get student value and pass into the session 
					session = httpServletRequest.getSession();
				
					session.setAttribute("susername", user.getEmail());
					session.setAttribute("userid", user.getUserid());
					Cookie studentCookie = new Cookie("suid", "true");
					session.setAttribute("path", "login");
					httpServletResponse.addCookie(studentCookie);
					httpServletResponse.sendRedirect("StudentDataLoader?path=login");
				}
				//instructor
				 if(user.getRole().equalsIgnoreCase("instructor")) {
					//redirect to instructor view loader 
					//	Cookie instructorCookie = new Cookie("iuid", "");
					    session = httpServletRequest.getSession();
					    session.setAttribute("iusername", user.getEmail());
						session.setAttribute("iuserid", user.getUserid());
						Cookie instructorCookie = new Cookie("iuid", "true");
						httpServletResponse.addCookie(instructorCookie);
					httpServletResponse.sendRedirect("InstructorDataLoader?path=dashboard");
				}
				
				if(user.getRole().equals("admin")) {
					System.out.println("admin authen");
					
					session = httpServletRequest.getSession();
					System.out.println(user.getEmail());
					session.setAttribute("ausername", user.getEmail());
					Cookie adminCookie = new Cookie("auid", "true");
					// need to activate this in production adminCookie.setSecure(true);
					session.setAttribute("path", "login");
					httpServletResponse.addCookie(adminCookie);
					httpServletResponse.sendRedirect("AdminDataLoader?path=dashboard");
					
				}
				
				
				
				
			}
	}
}
