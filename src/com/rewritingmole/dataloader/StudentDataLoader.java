package com.rewritingmole.dataloader;

import java.awt.print.Paper;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;

import com.rewritingmole.models.Course;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmole.models.Result;
import com.rewritingmolequiz.dao.AdminDao;
import com.rewritingmolequiz.dao.InstructorDao;
import com.rewritingmolequiz.dao.StudentDao;

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
		String loginPath = null;
		HttpSession session = request.getSession();
		String userId = null;
		StudentDao dao = new StudentDao();
		
		
		if(session.getAttribute("userid") != null) {
			userId = session.getAttribute("userid").toString();
		}
		if(session.getAttribute("listofcourses") != null) {
             session.removeAttribute("listofcourses");
		}
	
         if(path.equalsIgnoreCase("stureg")) {
        	 AdminDao adminDao = new AdminDao();
			try {
				List<Course> allCourses = adminDao.getCourses();
				session.setAttribute("allCourses", allCourses);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("Register.jsp");
		}
         
		if(session.getAttribute("path") != null || path.equals("login")) {
			//redirect to dashboard
			//loginPath = session.getAttribute("path").toString();
		
				//get the number of courses the student is registered in 
				List<Course> listOfCourses = dao.getStudentRegisteredCourse(userId);
				 
				session.setAttribute("listofcourses", listOfCourses);
				response.sendRedirect("StudentDashBoard.jsp");
			
		}
		 session.removeAttribute("path");
		
		if(path.equalsIgnoreCase("quiz")) {
			if(session.getAttribute("questionpapers") != null) {
		    	  session.removeAttribute("questionpapers");
		      }
			getQuiz(userId, dao,session);
			response.sendRedirect("StudentQuizzes.jsp");
		}
		
		if(path.equalsIgnoreCase("result")) {
			getResult(request, response, session);
			response.sendRedirect("StudentGrades.jsp");
		}
		
	}
	
     public void  getQuiz(String userId,StudentDao dao,HttpSession session ) {
    	 
    		List<Course> listOfCourses = dao.getStudentRegisteredCourse(userId);
    		//check database for questions Paper that match id
    		//get the listOfquestionPaper
    		String [] courseIds = new String [listOfCourses.size()];
    		for(int i = 0 ;  i <listOfCourses.size(); i++) {
    			courseIds[i] = listOfCourses.get(i).getCourseId();
    		}
    		try {
			List<QuestionPaper> list = dao.getListOfQuestionPaper(courseIds);
			System.out.println(list.size() +" original size");
			//check the question paper that are available by
			//release date 
			//expired date
			// start time
			//status 
			for(int p = 0; p < list.size(); p++) 
			{
				System.out.println(list.get(p).getStatus());
			}
          // get system date
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<QuestionPaper> readyQuestionPaper = new ArrayList<>();
			for(int k = 0; k < list.size(); k++) {
			 QuestionPaper paper = list.get(k);
			 DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 Date  releaseDate = dateFormat.parse(paper.getReleaseDate());
			 Date expiredDate = dateFormat.parse(paper.getExpiredDate());
		       
			 // get present date and converts to Date Object for comparsion

			 LocalDate date = LocalDate.now();
			 Date presentDate  = dateFormat.parse(dateTimeFormatter.format(date));
			  System.out.println(presentDate + "present date");
			  System.out.println(releaseDate + " release date");
			 LocalTime startTime = LocalTime.parse(paper.getStartTime());
			 String getHour = String.valueOf(LocalTime.now().getHour());
			 String getMinutes = String.valueOf(LocalTime.now().getMinute());
			 String currentTime = getHour+":"+ getMinutes;
			 LocalTime presentTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
			
			 if(!(presentDate.compareTo(expiredDate)>0) && paper.getStatus().equalsIgnoreCase("activated")) {
			
				 if(presentTime.compareTo(startTime) == 0) {
					 readyQuestionPaper.add(paper);
				 }
                  System.out.println(releaseDate.compareTo(presentDate) < 0 || releaseDate.compareTo(presentDate) == 0);
				 if(releaseDate.compareTo(presentDate) < 0 || releaseDate.compareTo(presentDate) == 0) {
					 
					 readyQuestionPaper.add(paper);
				 }
				  
			 }
			 
			 
			}
		      System.out.println(readyQuestionPaper.size());
				session.setAttribute("questionpapers", readyQuestionPaper);
			
			} catch (DecoderException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
     }
     
 	
 	public void getResult(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
 		StudentDao dao = new StudentDao();
 		String userid ="ee";
 		List<Result> results = null ;
 		if(session.getAttribute("userid") !=null) {
 		 userid = session.getAttribute("userid").toString();
 		 results = dao.getStudentResults(userid);
 		}
 		System.out.println((session.getAttribute("userid") !=null) + "-------------------------result");
 		session.setAttribute("studentresult", results);
 	}
     
 	
 
	

	} 
	

	
	
	
	

