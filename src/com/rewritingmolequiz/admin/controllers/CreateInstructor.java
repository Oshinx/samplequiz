package com.rewritingmolequiz.admin.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmolequiz.dao.AdminDao;
import com.rewritingmolequiz.dao.UserDao;
import com.rewritingmolequiz.utils.Mailer;
import com.rewritingmolequiz.utils.RandomGenerator;

/**
 * Servlet implementation class CreateInstructor
 */
@WebServlet("/CreateInstructor")
public class CreateInstructor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName = request.getParameter("course");
		String instructorEmail= request.getParameter("instructormail");
		String generatedEmail = RandomGenerator.generatePlaceHolder()+"@sheffield.ac.uk";
		String generatedPassword = RandomGenerator.generatePassword();
		String generatedUserId = RandomGenerator.generateUniqueId();
		UserDao  dao = new UserDao();
		HttpSession  session =  request.getSession();
		
		boolean mailSent = Mailer.sendInstructorMail(instructorEmail, generatedEmail,generatedPassword);
		if(mailSent == true) {
			//success
			dao.createInstrutor(generatedUserId, generatedEmail, generatedPassword);
			AdminDao  adminDao = new AdminDao();
			adminDao.adminRegisterUser(generatedUserId, adminDao.getCourseId(courseName));
			session.setAttribute("insuccess", "<div class=\"alert  alert-success\"  role=\"alert\">Registration Successful</div>");
		    response.sendRedirect("Admin_CreateInstructor.jsp");
			
		}
		else {
			//error
			session.setAttribute("inerror", "<div class=\"alert  alert-danger\"  role=\"alert\">Mail not sent</div>");
			  response.sendRedirect("Admin_CreateInstructor.jsp");
		}
		
		
	}

}
