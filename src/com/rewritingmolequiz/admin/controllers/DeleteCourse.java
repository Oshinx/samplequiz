package com.rewritingmolequiz.admin.controllers;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmolequiz.dao.AdminDao;

/**
 * Servlet implementation class DeleteCourse
 */
@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String courseId = request.getParameter("id");
	     //Delete the Language using id
	     AdminDao adminDao = new AdminDao();
	     if(adminDao.checkIfCourseId(courseId)) {
	     adminDao.deleteCourse(courseId);
		 HttpSession session = request.getSession();
		 session.setAttribute("path", "delete");
		 response.sendRedirect("AdminDataLoader?path=ManageCourses");
			
	     }
	     
	     else {
	    	 //Course  does not exit
	    	 System.out.println("testing the world");
	     }
	     
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
