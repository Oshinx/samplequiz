package com.rewritingmolequiz.student.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.rewritingmolequiz.dao.StudentDao;
import com.rewritingmolequiz.utils.RandomGenerator;

/**
 * Servlet implementation class ComfirmRegistration
 */
@WebServlet("/ComfirmRegistration")
public class ComfirmRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     HttpSession session =  request.getSession();
     String randomInput = request.getParameter("confirmNo");
     String email = null;
     String password = null ;
     String randomcode = null;
     String courseName = null;
     //check if value exist in session 
     if(session.getAttribute("email") != null && session.getAttribute("pass") != null && session.getAttribute("randomcode") != null) {
      email= session.getAttribute("email").toString();
      password= session.getAttribute("pass").toString();
      randomcode = session.getAttribute("randomcode").toString();
      courseName = session.getAttribute("select_course").toString();
     }
      if(!randomcode.equals(randomInput)) {
  
    	  session = request.getSession();
			 session.setAttribute("randcodeerror",  "<div class=\"alert alert-danger\" role=\"alert\">\n" + 
		    			"  <span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n" + 
		    			"  <span class=\"sr-only\">Error:</span>\n" + 
		    			"  Incorrect confirmation code\n" + 
		    			"</div>");
    	  response.sendRedirect("ConfirmRegistration.jsp");
    	  
      }
      else {
    	  //check if the user exist
    	  // register as new user 
    	  //register  for course also
    	  // redirect back to login page
    	  StudentDao dao = new StudentDao();
    	  try {
    		  boolean doesStudent = dao.studentExits(email);
    		  System.out.println(doesStudent  +" doesStudent Exist ");
    		  if(doesStudent == true) {
    			  //if  student exist student should meet the administrator for new registration 
    			  session = request.getSession();
    				 session.setAttribute("alreadyreg",  "<div class=\"alert alert-danger\" role=\"alert\">\n" + 
    			    			"  <span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n" + 
    			    			"  <span class=\"sr-only\">Error:</span>\n" + 
    			    			"  Contact the adminstrator registration unsuccessful\n" + 
    			    			"</div>");
    	    	  response.sendRedirect("ConfirmRegistration.jsp");
    	    	  
    			  
    		  }
    		  else {
    			  //create student and store in database
    			  String userId = RandomGenerator.generateUniqueId();
    			  
    			  //hash password before storing the data inside the database
			  dao.createStudent(userId, email, BCrypt.hashpw(password, BCrypt.gensalt()));
		      
			  //register student in the course_registration table 
			  dao.studentRegisterUser(userId, dao.getCourseId(courseName));
			  response.sendRedirect("Login.jsp");
    		  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      
	}

}
