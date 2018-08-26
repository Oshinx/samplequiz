package com.rewritingmolequiz.admin.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmolequiz.dao.AdminDao;
import com.rewritingmolequiz.utils.RandomGenerator;

/**
 * Servlet implementation class CreateCourse
 */
@WebServlet("/CreateCourse")
public class CreateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletRequest response) {
		  System.out.println("Do get method ");
	}
	
	
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		HttpSession session =  request.getSession();
    		 
   		    if(session.getAttribute("success") != null){
   			 session.removeAttribute("success");
   		   System.out.println(session.getAttribute("success").toString()+"llll");
               
                }
            else if(session.getAttribute("error") != null){
           	 session.removeAttribute("error");
           	 System.out.print(session.getAttribute("error").toString());
              }
	}

    	
    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseId  = RandomGenerator.generateUniqueId();
		String courseName = request.getParameter("coursename");
		String direction = request.getParameter("direction");
		//check if course exit
		HttpSession session =  request.getSession();
		AdminDao adminDao = new AdminDao();
		if(adminDao.checkIfCourseExit(courseName)) {
			//print value exit 
           PrintWriter out = response.getWriter();
           session.setAttribute("error",  "<div class=\"alert alert-danger alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
   				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
   				"           	 Course Exist\n" + 
   				"           </div>");
           response.sendRedirect("ManageCourse.jsp");
		}
		else {
			
			System.out.println(adminDao.createCourse(courseId, courseName, direction));
			//print successful
			System.out.println("working");
			  PrintWriter out = response.getWriter();
			  session.setAttribute("success",  "<div class=\"alert alert-success alert-dismissible\" id=\"coursenexit\" role=\"alert\">\n" + 
		   				"           	 <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span>×</span></button>\n" + 
		   				"           	Course Created Successfully  alert\n" + 
		   				"           </div>");
	           response.sendRedirect("AdminDataLoader?path=ManageCourses");
		}
		
		
	}

}
