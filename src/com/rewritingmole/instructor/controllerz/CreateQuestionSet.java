package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import com.rewritingmole.models.MultipleAnswerQuestion;
import com.rewritingmole.models.MultipleChoiceQuestion;
import com.rewritingmole.models.Question;
import com.rewritingmole.models.QuestionPaper;


/**
 * Servlet implementation class PrepareQuestionPaper
 */
@WebServlet("/CreateQuestionSet")
public class CreateQuestionSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		HttpSession httpSession = req.getSession();
		if(httpSession.getAttribute("questionPaper") != null) {
		QuestionPaper paper = (QuestionPaper) httpSession.getAttribute("questionPaper");
		List<Question> listOfQuestion = paper.getListOfQuestion();
		
		
		int qcounter = 0;
		
		for(int i = 0; i<listOfQuestion.size(); i++) {
          Question question = listOfQuestion.get(i);
			qcounter +=1;
			      int option = 0;
			   if(question instanceof MultipleChoiceQuestion) {
				   option += 1;
				   String questionText = req.getParameter("questiontext-"+qcounter);
				   System.out.println("\n question text  "+questionText );   
				   for(int j = 0; j < Integer.parseInt(question.getOptionSize()); j++) {
					   String questionOption = req.getParameter("question"+qcounter+"Option"+option);
					   System.out.println("\n option " + questionOption);
				   }
				   String point = req.getParameter("question"+qcounter+"-point");
				   String feedback = req.getParameter("feedback"+qcounter);
				   System.out.println("\n quiz point " + point);
				   System.out.println("\n quiz feedback " + feedback);
				
				   
			   }
			   
			   System.out.println();
			  System.out.println("============================================");
			   if(question instanceof MultipleAnswerQuestion) {
				   
				   option += 1;
				   String questionText = req.getParameter("questiontext-"+qcounter);
				   System.out.println("\n question text  "+questionText );   
				   for(int j = 0; j < Integer.parseInt(question.getOptionSize()); j++) {
					   String questionOption = req.getParameter("question"+qcounter+"Option"+option);
					   System.out.println("\n option " + questionOption);
				   }
				   String point = req.getParameter("question"+qcounter+"-point");
				   String negativePoint = req.getParameter("question"+qcounter+"negpoint");
				   String feedback = req.getParameter("feedback"+qcounter);
				   System.out.println("\n negative point " + negativePoint);
				   System.out.println("\n quiz option " + point);
				   System.out.println("\n quiz feedback " + feedback);
				   
			   }
		}
		
		
		}
		
	}


	

}
