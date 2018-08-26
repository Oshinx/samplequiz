package com.rewritingmole.dataloader;

import java.io.IOException;
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
 * Servlet implementation class StudentDataLoader
 */
@WebServlet("/StudentDataLoader")
public class StudentDataLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}
    
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String path = request.getParameter("path");
		
		// get courses for registration page
		if(path.equalsIgnoreCase("stureg")) {
			displayCourseReg(request, response);
		}
		
		
	}
	
	
	
	protected void displayCourseReg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get all the course and put in session
		HttpSession session = request.getSession();
		
		//check if the session exist and invalidates the session
		if(session.getAttribute("listOfCourses") != null) {
			 session.removeAttribute("listOfCourses");
		 }
		AdminDao adminDao = new AdminDao();
		
		List<Course> listOfCourses;
		try {
			//get the course and adds the to the session object
			listOfCourses = adminDao.getCourses();
			for(Course c : listOfCourses) {
				System.out.println(c.getCourseName());
			}
			session.setAttribute("listOfCourses", listOfCourses);
			response.sendRedirect("Register.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
}
