package com.rewritingmolequiz.admin.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import com.rewritingmole.model.User;
import com.rewritingmolequiz.dao.AdminDao;
import com.rewritingmolequiz.dao.UserDao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String oldPassword = request.getParameter("oldpassword");
	   String newPassword = request.getParameter("newpassword");
	   HttpSession session = request.getSession();
	   System.out.println(oldPassword);
	   //check if old password is valid 
	     UserDao dao = new UserDao();
	     User user = dao.getUser("adminstrator@sheffield.ac.uk", oldPassword);
	 
	     if(user == null) {
	    	 //old password is incorrect
	    	
	 		session.setAttribute("error", "<div class=\"alert alert-danger alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
	 				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
	 				"           	Incorrect credentials \n" + 
	 				"           </div>");
	 		//reposonse
	 		response.sendRedirect("ChangePassword.jsp");
	     }
	   //update new password
	     if(user != null ) {
	    	 
	    	 dao.updatePassword("adminstrator@sheffield.ac.uk", newPassword);
	    	 session.setAttribute("error", "<div class=\"alert alert-success alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
		 				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
		 				"           	Password Changed \n" + 
		 				"           </div>");
		 		//reposonse
		 		response.sendRedirect("ChangePassword.jsp");
	     }
	   
	   
	   
	}

}
