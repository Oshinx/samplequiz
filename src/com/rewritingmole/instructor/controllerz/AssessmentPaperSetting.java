package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.models.Course;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmolequiz.utilz.RandomGenerator;

import javafx.scene.shape.QuadCurve;

/**
 * Servlet implementation class QuestionPaperSetting
 */
@WebServlet("/AssessmentPaperSetting")
public class AssessmentPaperSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("questionPaperName");
		String instruction = request.getParameter("questionPaperinstruction");
		String course  = request.getParameter("course");
		String numberOfQuestion = request.getParameter("numberofquestion");
		String releaseDate = request.getParameter("releasedate");
		String expiredDate = request.getParameter("expireddate");
		String startTime = request.getParameter("Starttime");
		String duration = request.getParameter("duration");
		String questionsize = request.getParameter("questionsize");
		String randomization [] = request.getParameterValues("Randomization");
		String feedback [] = request.getParameterValues("feedback");
		String defaultRand = "false";
		String defaultfeedback ="false";
	 System.out.println();
		if(feedback == null) {
			feedback = new String [1];
			feedback[0] = defaultfeedback;
			System.out.println(feedback[0]);
		}
		if(randomization == null) {
			randomization = new String[1];
			randomization[0] = defaultRand;
			System.out.println(randomization[0]);
		}
		HttpSession httpSession = request.getSession();
        List<Course> cd = null;
        String courseDirection = null;
      //  System.out.println(httpSession.getAttribute("listOfCourses") + " session");
        if(httpSession.getAttribute("listOfCourses") != null) {
        	cd =  (List) httpSession.getAttribute("listOfCourses");
             System.out.println(cd.size());
        	 for(Course d : cd) {
        		
        		 if(d.getCourseName().equalsIgnoreCase(course)) {
        			
        			 courseDirection = d.getDirection();
        			 System.out.println(courseDirection);
        		 }
        	 }
        }
       
		
		System.out.println("\n cousre: "+course + "\n instruction: "+ instruction+"\n numberOfQuestion: "+ numberOfQuestion + "\n releaseDate: "+ releaseDate
				+ "\n expiredDate: "+ expiredDate + "\n startTime: "+ startTime + "\n duration: "+ duration  + "\n randomization: "+randomization[0]
				+ "\n feedback: "+ feedback[0]);
		QuestionPaper paper = new QuestionPaper();
		paper.setAssessmentId(RandomGenerator.generateAssessmentId());
		paper.setName(name);
		paper.setInstruction(instruction);
		paper.setCourse(course);
		paper.setTotalNumberOfQuestion(numberOfQuestion);
		paper.setReleaseDate(releaseDate);
		paper.setExpiredDate(expiredDate);
		paper.setStartTime(startTime);
		paper.setDuration(duration);
		paper.setRandomization(randomization[0]);
		paper.setFeedback(feedback[0]);
		paper.setDirection(courseDirection);
		//need to add direction of the question paper 
		paper.setQuestionSize(questionsize);
	    //add the type of questionpaper
	    if(questionsize != null) {
	    	
	    	httpSession.setAttribute("questionPaper", paper);
	    	response.sendRedirect("SetQuestionPool");
	    }
	/*    if(numberOfQuestion != null) {
	    	 httpSession.setAttribute("questionPaper", paper);
	    	 response.sendRedirect("QuestionsDetail.jsp");
	    }*/
	    
	    
	    
		 
	  /* 	RequestDispatcher rd =  request.getRequestDispatcher("QuestionsDetail.jsp");
		rd.forward(request, response);*/
	}


}
