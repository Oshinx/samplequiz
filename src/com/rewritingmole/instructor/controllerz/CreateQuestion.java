package com.rewritingmole.instructor.controllerz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewritingmole.models.Course;
import com.rewritingmole.models.MultipleAnswerQuestion;
import com.rewritingmole.models.MultipleChoiceQuestion;
import com.rewritingmole.models.QuestionPool;
import com.rewritingmole.models.QuestionType;
import com.rewritingmolequiz.dao.InstructorDao;
import com.rewritingmolequiz.utilz.RandomGenerator;



/**
 * Servlet implementation class CreateQuestion
 * This servlet creates a question of any question type supported by this system. 
 * It checks if the instructor as an assessment bank; creates a new one if none exist and 
 * stores the question.If assessment bank exist then it just stores the question inside.
 */
@WebServlet("/CreateQuestion")
public class CreateQuestion extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
	String course = request.getParameter("course");
	String questionType = request.getParameter("questiontype");
	String optionSize = request.getParameter("optionsize");
    InstructorDao dao = new InstructorDao();
    ObjectMapper  mapper = new ObjectMapper();
    String courseDirection = null;
     HttpSession session =request.getSession();
	// check if the user has a question bank
	//boolean doesQuestionBankExist = ;

	 mapper.enableDefaultTyping();
		//get the user id from session 
		String userId = null;
		if(session.getAttribute("iuserid") != null) {
			userId = session.getAttribute("iuserid").toString();
		}
		 QuestionPool questionPool = dao.getQuestionPool(userId);
	 
	 

	//if user exist then create question object and store it 
	if(questionPool == null) {
		String pool = mapper.writeValueAsString(new QuestionPool());
		dao.createQuestionPool(userId, pool );
	}
	if(courseDirection == null) {
		System.out.println(session.getAttribute("listOfCourses") != null);
		  if(session.getAttribute("listOfCourses") != null){
              List<Course> courses = (List) session.getAttribute("listOfCourses");
              for(Course c : courses) {
            	  if(c.getCourseName().equalsIgnoreCase(course)) {
            		  courseDirection = c.getDirection();
            		  System.out.println(courseDirection+"=======================question direction");
            	  }
              }
		  
		  }
	}

	  System.out.println(course + "\n" + questionType + "\n" + optionSize + "\n" + course + "\n");

	
     if(questionType.equals(QuestionType.getMultiChoice())) {
	     MultipleChoiceQuestion choiceQuestion   = new MultipleChoiceQuestion();
	     
	      choiceQuestion.setQuestionId(RandomGenerator.generateQuestionId());
	      choiceQuestion.setCoursename(course);
	      choiceQuestion.setQuestionType(QuestionType.getMultiChoice());
	      choiceQuestion.setOptionSize(optionSize);
	      choiceQuestion.setQuestionText(request.getParameter("question-text"));
	      System.out.println(choiceQuestion.getQuestionText());
	      //getting all the answer
	      
	      for(int i = 1; i <=  Integer.parseInt(optionSize); i++) {
	    	  choiceQuestion.getOptionList().add(request.getParameter("option-"+i));
	    	  System.out.println(request.getParameter("option-"+i) + "   options");
	      }
	      
	      choiceQuestion.getAnswerList().add(request.getParameter("questionAnswer"));
	      choiceQuestion.setPoint(request.getParameter("point"));
	      choiceQuestion.setFeedback(request.getParameter("feedback"));
	      choiceQuestion.setQuestiondirection(courseDirection);
	      questionPool.getListOfQuestion().add((MultipleChoiceQuestion)choiceQuestion);
	      System.out.println(request.getParameter("questionAnswer") + "   answer");
	      System.out.println(request.getParameter("point") + "   point");
	      System.out.println(request.getParameter("feedback") + "   feedback");
               System.out.println(mapper.writeValueAsString(questionPool) + "=========question pool first");
     	}
	if(questionType.equals(QuestionType.getMultiAnswer())) {
		MultipleAnswerQuestion  answerQuestion = new MultipleAnswerQuestion();
		answerQuestion.setQuestionId(RandomGenerator.generateQuestionId());
		answerQuestion.setCoursename(course);
		answerQuestion.setQuestionType(QuestionType.getMultiAnswer());
		answerQuestion.setOptionSize(optionSize);
		answerQuestion.setQuestionText(request.getParameter("question-text"));
	    System.out.println(answerQuestion.getQuestionText());
	    
	    for(int i = 1; i <=  Integer.parseInt(optionSize); i++) {
	    	  answerQuestion.getOptionList().add(request.getParameter("option-"+i));
	    	  System.out.println(request.getParameter("option-"+i) + "   options");
	      }
	       
	      String [] answer = request.getParameterValues("selectquestionAnswer");
	    for(int i = 0 ;  i < answer.length ; i++) {
	    	 answerQuestion.getAnswerList().add(answer[i]);
	    	 System.out.println(answer[i]);
	      }
	      answerQuestion.setQuestiondirection(courseDirection);
	      answerQuestion.setPoint(request.getParameter("point"));
	      answerQuestion.setFeedback(request.getParameter("feedback"));
	      answerQuestion.setNegativePoint(request.getParameter("negpoint"));
	      
	      questionPool.getListOfQuestion().add((MultipleAnswerQuestion)answerQuestion);
	      String a = mapper.writeValueAsString(questionPool);
     
	    	 
	    
	      System.out.println(request.getParameter("point") + "   point"); 
	      System.out.println(request.getParameter("negpoint") + "   point");
	      System.out.println(request.getParameter("feedback") + "   feedback");
	         
	    }
	  try {
		  System.out.println(mapper.writeValueAsString(questionPool) + " question ===================second");
			dao.updateQuestionPool(userId,mapper.writeValueAsString(questionPool));
			response.sendRedirect("CreateQuestion.jsp");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
  }

}
