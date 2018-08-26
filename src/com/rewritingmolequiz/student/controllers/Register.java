package com.rewritingmolequiz.student.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmolequiz.utils.Mailer;
import com.rewritingmolequiz.utils.RandomGenerator;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRegister(request, response);
	}
	
	protected void processRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email =  request.getParameter("email");
		String password =  request.getParameter("new_password");
		String repassword = request.getParameter("re-enterpass");
		String course = request.getParameter("select_course");
		HttpSession session = request.getSession();
		
		//confirm email is sheffield 
		boolean isEmailValid = email.contains("@sheffield.ac.uk");
		System.out.println(email +"\n"+ password  +"\n" +repassword +"\n"+ isEmailValid);
		if(isEmailValid == true) {
			
			
			if(!password.equals(repassword)) {
				//error case for password validation
				// invalid credentials
				 session = request.getSession();
				 session.setAttribute("regerror",  "<div class=\"alert alert-danger alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
			   				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
			   				"           	 Password Does not Match \n" + 
			   				"           </div>");
			}
			
			
				
				// send mail to confirm  random number
				String randomCode = RandomGenerator.generateRandomCode();
				
				
			
				session.setAttribute("email", email);
				session.setAttribute("pass", password);
				session.setAttribute("randomcode", randomCode);
				session.setAttribute("select_course", course);
				Mailer.sendConfirmationMail(email, randomCode);
				
			    
				RequestDispatcher rd = request.getRequestDispatcher("ConfirmRegistration.jsp");
				rd.forward(request, response);
			
		}
		
		else {
			// invalid credentials
			 session = request.getSession();
			 session.setAttribute("emailerror",  "<div class=\"alert alert-danger\" role=\"alert\">\n" + 
		    			"  <span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n" + 
		    			"  <span class=\"sr-only\">Error:</span>\n" + 
		    			"  Enter a valid email address\n" + 
		    			"</div>");
			
		
		     response.sendRedirect("Register.jsp");
		}
		
	}

}
