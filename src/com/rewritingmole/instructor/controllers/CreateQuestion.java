package com.rewritingmole.instructor.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewritingmole.model.MultipleAnswerQuestion;
import com.rewritingmole.model.MultipleChoiceQuestion;
import com.rewritingmole.model.Question;
import com.rewritingmole.model.QuestionPool;
import com.rewritingmole.model.QuestionType;
import com.rewritingmolequiz.dao.InstructorDao;
import com.rewritingmolequiz.utils.RandomGenerator;


/**
 * Servlet implementation class CreateQuestion
 * This servlet creates a question of any question type supported by this system. 
 * It checks if the instructor as an assessment bank; creates a new one if none exist and 
 * stores the question.If assessment bank exist then it just stores the question inside.
 */
@WebServlet("/CreateQuestion")
public class CreateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
	String course = request.getParameter("course");
	String questionType = request.getParameter("questiontype");
	String optionSize = request.getParameter("optionsize");
    InstructorDao dao = new InstructorDao();
    ObjectMapper  mapper = new ObjectMapper();
   
     
	// check if the user has a question bank
	//boolean doesQuestionBankExist = ;
	 QuestionPool questionPool = new QuestionPool();
	//if user exist then create question object and store it 
	if(questionPool == null) {
		//String pool = mapper.writeValueAsString(new QuestionPool());
		//dao.createQuestionPool("hello", "d" );
	}
	
	else {
	 
	  System.out.println(course + "\n" + questionType + "\n" + optionSize + "\n" + course + "\n");
		 //questionPool = dao.getQuestionPool("hello");
	
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
	      questionPool.getListOfQuestion().add(choiceQuestion);
	      System.out.println(request.getParameter("questionAnswer") + "   answer");
	      System.out.println(request.getParameter("point") + "   point");
	      System.out.println(request.getParameter("feedback") + "   feedback");
	      
	 /*  
	      try {
			dao.updateQuestionPool(mapper.writeValueAsString(questionPool), "hello");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
	     
	      answerQuestion.setPoint(request.getParameter("point"));
	      answerQuestion.setFeedback(request.getParameter("feedback"));
	      answerQuestion.setNegativePoint(request.getParameter("negpoint"));
	      questionPool.getListOfQuestion().add(answerQuestion);


	    
	      System.out.println(request.getParameter("point") + "   point"); 
	      System.out.println(request.getParameter("negpoint") + "   point");
	      System.out.println(request.getParameter("feedback") + "   feedback");
	    }
	
      }
  }

}
