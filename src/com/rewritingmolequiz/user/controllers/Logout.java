package com.rewritingmolequiz.user.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// search the cookie for the different user types 
		// get each cookie and invalidate it finally invalidate the session.
		Cookie [] cookies = request.getCookies();
		Cookie deleteCookie;
		for(Cookie cookie : cookies) {
			//delete the admin cookie
			if(cookie.getName().equals("auid")) {
			    deleteCookie = new Cookie("auid","");
			    deleteCookie.setMaxAge(0);
			    response.addCookie(deleteCookie);
			    
			}
			//delete the instructor cookie
	        if(cookie.getName().equals("iuid")) {
	        	  deleteCookie = new Cookie("iuid","");
				  deleteCookie.setMaxAge(0);
				  response.addCookie(deleteCookie);
			}
	      //delete the student cookie
	        if(cookie.getName().equals("suid")) {
	              deleteCookie = new Cookie("suid","");
				  deleteCookie.setMaxAge(0);
				  response.addCookie(deleteCookie);	
			}
			deleteCookie = null;
			
		}
		//delete session 
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("Login.jsp");
		
	}


}
