package com.rewritingmole.dataloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.model.Course;
import com.rewritingmolequiz.dao.AdminDao;

/**
 * Servlet implementation class AdminDataLoader
 */
@WebServlet("/AdminDataLoader")
public class AdminDataLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("post");
		
	}
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// redirect based on input path 
	     String path = request.getParameter("path");
	     //
	if(path != null) {
	     if(path.equalsIgnoreCase("dashboard")) {
	    	 try {
				processDashBoard(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     if(path.equalsIgnoreCase("ManageCourses")) {
	    	 processManageCourse(request, response);
	     }
	     
	     if(path.equalsIgnoreCase("RegStuAdmin")) {
	    	 processAdminRegisterStudent(request, response);
	     }
	     if(path.equalsIgnoreCase("CreInstr")) {
	    	 
	    	 processCreateInstructor(request, response);
	     }
	}
	}
	
	
	
	
	protected void processDashBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//total number of course
		//total number of students 
		// total number of instructor
		
		//check if session exist and then remove to update data
		
		//checks total courses session
		HttpSession session = request.getSession();
		if(session.getAttribute("totalcourses") != null) {
			session.removeAttribute("totalcourses");
		}
		//checks total students session
		if(session.getAttribute("totalstudents") != null) {
			session.removeAttribute("totalstudents");
				}
		//checks total instructor session
		if(session.getAttribute("totalinstructor") != null) {
			session.removeAttribute("totalinstructor");
		}
		
		//get the fresh data from  the database and store in session
		
		//get the total number of course 
		AdminDao adminDao = new AdminDao();
		try {
			session.setAttribute("totalcourses", String.valueOf(adminDao.getTotalCourses()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get the total number of students
		try {
			session.setAttribute("totalstudents", String.valueOf(adminDao.getTotalStudent()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get the total number of instructor
				try {
					session.setAttribute("totalinstructor", String.valueOf(adminDao.getTotalInstructors()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		    response.sendRedirect("Admin_DashBoard.jsp");
		
	}
	
	protected void processManageCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//get rigister course and put in session 
		
		//create session object
		HttpSession session = request.getSession();
		
		
		//check if the session exist
		if(session.getAttribute("listOfCourses") != null) {
			 session.removeAttribute("listOfCourses");
		 }
		
		
		AdminDao adminDao = new AdminDao();
	
		List<Course> listOfCourses;
		try {
			listOfCourses = adminDao.getCourses();
			session.setAttribute("listOfCourses", listOfCourses);
			response.sendRedirect("Admin_ManageCourse.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	protected void processAdminRegisterStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// get the registered courses 
		//get rigister course and put in session 
		        System.out.println("inside the admin rfre");
				//create session object
				HttpSession session = request.getSession();
				
				
				//check if the session exist
				if(session.getAttribute("listOfCourses") != null) {
					 session.removeAttribute("listOfCourses");
				 }
				
				
				AdminDao adminDao = new AdminDao();
			
				List<Course> listOfCourses;
				try {
					listOfCourses = adminDao.getCourses();
					for(Course c : listOfCourses) {
						System.out.println(c.getCourseName());
					}
					session.setAttribute("listOfCourses", listOfCourses);
					response.sendRedirect("Admin_RegisterAdminStudent.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		
	}
	
	
	protected void processCreateInstructor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// get the registered courses 
		//get rigister course and put in session 
		        System.out.println("inside the admin rfre");
				//create session object
				HttpSession session = request.getSession();
				
				
				//check if the session exist
				if(session.getAttribute("listOfCourses") != null) {
					 session.removeAttribute("listOfCourses");
				 }
				
				
				AdminDao adminDao = new AdminDao();
			
				List<Course> listOfCourses;
				try {
					listOfCourses = adminDao.getCourses();
					for(Course c : listOfCourses) {
						System.out.println(c.getCourseName());
					}
					session.setAttribute("listOfCourses", listOfCourses);
					response.sendRedirect("Admin_CreateInstructor.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		
	}
	
	
	
}
