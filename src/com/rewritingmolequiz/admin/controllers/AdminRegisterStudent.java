package com.rewritingmolequiz.admin.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;
import com.rewritingmolequiz.dao.AdminDao;

/**
 * Servlet implementation class AdminRegisterStudent
 */
@WebServlet("/AdminRegisterStudent")
public class AdminRegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String studentId = request.getParameter("studentid");
	String courseName = request.getParameter("course");
	AdminDao adminDao = new AdminDao();
	HttpSession session = request.getSession();
	//if student does not exist
	if(adminDao.studentExits(studentId) != true) {
		session.setAttribute("stunotexist", "<div class=\"alert alert-danger alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
					"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
					"           	Student does not exist \n" + 
					"           </div>");
		//reposonse
		response.sendRedirect("./AdminDataLoader?path=RegStuAdmin");
		}
	System.out.println(adminDao.getCourseId(courseName)+"course");
	System.out.println(adminDao.isregistered(studentId,adminDao.getCourseId(courseName))+"is registered");
	System.out.println(adminDao.studentExits(studentId) +" testing ");
	
	//if student exist but has registered for the course
	if(adminDao.studentExits(studentId) && adminDao.isregistered(studentId,adminDao.getCourseId(courseName))) {
		//return student has already registred for the course
		System.out.println("registered for this course");
		session.setAttribute("stureg", "<div class=\"alert alert-danger alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
				"           	Student has  registered for this Course\n" + 
				"           </div>");
		//reposonse
		response.sendRedirect("./AdminDataLoader?path=RegStuAdmin");
	}
	
	if(adminDao.studentExits(studentId) && adminDao.isregistered(studentId,adminDao.getCourseId(courseName)) != true) {
		//return student has already registred for the course
		
		adminDao.adminRegisterUser(studentId, adminDao.getCourseId(courseName));
		
		
		session.setAttribute("sturegsuccess", "<div class=\"alert alert-success alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
				"           	Registration Successful\n" + 
				"           </div>");
		//reposonse
		response.sendRedirect("./AdminDataLoader?path=RegStuAdmin");
	}
	
  }
      
	 
	

}
