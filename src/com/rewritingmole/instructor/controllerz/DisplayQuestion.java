package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.models.MultipleAnswerQuestion;
import com.rewritingmole.models.MultipleChoiceQuestion;
import com.rewritingmole.models.QuestionPaper;


/**
 * Servlet implementation class DisplayQuestion
 */
@WebServlet("/DisplayQuestion")
public class DisplayQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("questionPaper") != null) {
			
		
			System.out.println("inside");
			QuestionPaper  paper = (QuestionPaper) httpSession.getAttribute("questionPaper");
	         
	         int  counter = 1;
	     	if(paper.getListOfQuestion().isEmpty() == false) {
				System.out.println(paper.getListOfQuestion().size() + " list size after");
				paper.getListOfQuestion().clear();
			}
				for(int i = 0; i < Integer.parseInt(paper.getTotalNumberOfQuestion()); i++) {
					String questionType = request.getParameter("questiontype-"+counter);
					String optionSize = request.getParameter("numberofoptions-"+counter);
			         System.out.println(questionType  +"     "+ optionSize);
					if(questionType.equalsIgnoreCase("multiplechoice")) {
						System.out.println(paper.getListOfQuestion().size() + " list size b4");
				
						MultipleChoiceQuestion choiceQuestion = new  MultipleChoiceQuestion();
						choiceQuestion.setQuestionType(questionType);
						choiceQuestion.setOptionSize(optionSize);
						paper.getListOfQuestion().add(choiceQuestion);
						
					}
	
				if(questionType.equals("multipleanswer")) {
					System.out.println(paper.getListOfQuestion().size() + " list size");
					
						
						MultipleAnswerQuestion answerQuestion = new MultipleAnswerQuestion();
						answerQuestion.setQuestionType(questionType);
						answerQuestion.setOptionSize(optionSize);
						paper.getListOfQuestion().add(answerQuestion);	
					}
					//adds questions 
				//	paper.getListOfQuestion().add("");	
					counter +=1;
				}
		}
				response.sendRedirect("CreateQuestionSet.jsp");
				
	     }
	   
			
			
		
	

}
