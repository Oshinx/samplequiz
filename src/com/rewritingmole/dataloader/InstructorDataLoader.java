package com.rewritingmole.dataloader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.models.Course;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmole.models.Result;
import com.rewritingmolequiz.dao.AdminDao;
import com.rewritingmolequiz.dao.InstructorDao;
import com.rewritingmolequiz.dao.StudentDao;

import sun.util.locale.StringTokenIterator;

/**
 * Servlet implementation class InstructorDataLoader
 */
@WebServlet("/InstructorDataLoader")
public class InstructorDataLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  // redirect the to different views based on the request
		String path = request.getParameter("path");
		System.out.println(path);
		HttpSession s = request.getSession();
		//get the user id from session 
		String userId = null;
		if(s.getAttribute("iuserid") != null) {
			userId = s.getAttribute("iuserid").toString();
		}
		
		//remove redirection session
		if(s.getAttribute("pageloc") !=null) {
			s.removeAttribute("pageloc");
		}
		//dashboard
		if(path.equalsIgnoreCase("dashboard")) {
			processDashBoard(request, response,userId);
		}
		
		//get all courses and redirect to create question 
		if(path.equalsIgnoreCase("createquestion")) {
			getCourses(request, response,userId);
			response.sendRedirect("CreateQuestion.jsp");
		}
		if(path.equalsIgnoreCase("assesseset")) {
			getCourses(request, response,userId);
			s.setAttribute("pageloc", "qset");
			response.sendRedirect("AssessmentSetting.jsp");
		}
		
        if(path.equalsIgnoreCase("assessquiz")) {
			getCourses(request, response,userId);
			s.setAttribute("pageloc", "quiz");
			response.sendRedirect("AssessmentSetting.jsp");
		}  
		
		if(path.equalsIgnoreCase("manageassessment")) {
			displayQuizSet(request, response,userId);
		}
		
		if(path.equalsIgnoreCase("result")) {
			getCourses(request, response,userId);
			response.sendRedirect("InstructorGradeDashboard.jsp");
		}
		
		
				
	}
	
	
	
	protected void processDashBoard(HttpServletRequest request, HttpServletResponse response, String userId) throws IOException {
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
		
		//get the fresh data from  the database and store in session
		
		//get the total number of course 
		AdminDao adminDao = new AdminDao();
		InstructorDao dao = new InstructorDao();
		//get total course
		List<Course> list = new ArrayList<>();
		
		try {
			List<Course> totalCourse = adminDao.getCourses();
			List<Course> instructorsCourse = dao.getInstructorsCourses(userId);
			 for(Course c :totalCourse) {
				 for(Course course: instructorsCourse) {
					 if(course.getCourseId().equalsIgnoreCase(c.getCourseId())) {
						 list.add(c);
					 }
				 }
			 }
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//get instructor's registered course
		
		//get compare both 
		
		// put instrustors registered course in the session
		
		
		
		

			session.setAttribute("totalcourses", String.valueOf(list.size()));
	
		
	/*	//get the total number of students
		try {
	//		session.setAttribute("totalstudents", String.valueOf(adminDao.getTotalStudent()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		    response.sendRedirect("Instructor_Dashboard.jsp");
		
	}
	
	
	protected void getCourses(HttpServletRequest request, HttpServletResponse response,String userId) throws IOException {
		//get rigister course and put in session 
		
		//create session object
		HttpSession session = request.getSession();
		
		//check if the session exist
		if(session.getAttribute("listOfCourses") != null) {
			 session.removeAttribute("listOfCourses");
		}
		
		AdminDao adminDao = new AdminDao();
		InstructorDao dao = new InstructorDao();
		//get total course
		List<Course> list = new ArrayList<>();
		
		try {
			List<Course> totalCourse = adminDao.getCourses();
			List<Course> instructorsCourse = dao.getInstructorsCourses(userId);
			 for(Course c :totalCourse) {
				 for(Course course: instructorsCourse) {
					 if(course.getCourseId().equalsIgnoreCase(c.getCourseId())) {
						 list.add(c);
					 }
				 }
			 }
			
			
			 session.setAttribute("listOfCourses", list);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			
		
		
		
	}
	
	protected void displayQuizSet(HttpServletRequest request, HttpServletResponse response,String userId) throws IOException {
		  HttpSession session = request.getSession();
		//check if session exist and remove it 
		if(session.getAttribute("listOfQuestionPaper") != null) {
			session.removeAttribute("listOfQuestionPaper");
		}
		//./InstructorDataLoader?path=manageassessment
		
		//get the list of question set and display in on the page 
		InstructorDao dao = new InstructorDao();
	   List<QuestionPaper> listOfQuestionPaper = dao.getquestionPapersInfo(userId);
	 
	   session.setAttribute("listOfQuestionPaper", listOfQuestionPaper);
	   response.sendRedirect("ManageAssessment.jsp");
	}
	
	
	
    
	
	
	
}
