package com.rewritingmole.student.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.models.Question;
import com.rewritingmole.models.QuestionPaper;

/**
 * Servlet implementation class PrepareQuiz
 */
@WebServlet("/ReadyQuiz")
public class ReadyQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String path = request.getParameter("path");
       System.out.println(path);
       getQuizReady(request, response, path);
		
	}

	public void  getQuizReady(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
	  	 StringTokenizer stringTokenizer = new StringTokenizer(path,":");
	  	 String direction = stringTokenizer.nextToken();
	  	 String id = stringTokenizer.nextToken();
	  	 
	  	 HttpSession httpSession = request.getSession();
	  	 //get QuestionPaper using Id 
	  	  // Copy question object with options only
	  	 //
	  	 //check if randomization is true 
	  	 //randomize the question and display 
 		//response.sendRedirect("ReadyQuiz");
	  	 //get the 
	  	 if(httpSession.getAttribute("questionpapers") != null) {
	  	 List<QuestionPaper> listQP = (List) httpSession.getAttribute("questionpapers");
	  	QuestionPaper paper = null ;
	  	 for(QuestionPaper p : listQP) {
	  		if(p.getAssessmentId().equalsIgnoreCase(id)) {
	  			paper = p;
	  		}
	  	}
	  	 if(paper.getRandomization().equalsIgnoreCase("true")) {
	  		 //randomize questions 
	  		 Collections.shuffle(paper.getListOfQuestion());
		 
	  		
	  	 }
	  	 //select question from the pool
  		/* int selectQuestion = Integer.valueOf(paper.getQuestionSize());
  		 QuestionPaper readyquestion =  new QuestionPaper();
  		 for(int n =0;  n < paper.getListOfQuestion().size(); n++) {
  			 Question que = paper.getListOfQuestion().get(n);
  			 readyquestion.getListOfQuestion().add(que);
  			 
  		 }*/
	  	 //
	  	 String s ;
	  	 System.out.println((paper == null) + "====================paper is not null");
	  	 httpSession.setAttribute("readyquestion", paper);
	  	 httpSession.setAttribute("time", paper.getDuration());
	  	 httpSession.setAttribute("name", paper.getDuration());
	  	 response.sendRedirect("TakeQuiz.jsp");
	  	 
	  	 }
 	}
 	
}
