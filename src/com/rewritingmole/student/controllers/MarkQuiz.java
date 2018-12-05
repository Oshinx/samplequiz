package com.rewritingmole.student.controllers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewritingmole.models.MultipleAnswerQuestion;
import com.rewritingmole.models.Question;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmolequiz.dao.StudentDao;

/**
 * Servlet implementation class MarkQuiz
 */
@WebServlet("/MarkQuiz")
public class MarkQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //get questionsize
		String questionSize = request.getParameter("questionsize");
		System.out.println(questionSize +"======================qsize");
		//get all the values 
		QuestionPaper studentScript = new QuestionPaper();
		int qcounter = 1;
		for(int i = 0; i < Integer.parseInt(questionSize); i++) {
	         Question question = new Question(); 
	         // get questionId
	         String questionId = request.getParameter("questionid"+qcounter);
	         System.out.println(questionId +"====================qid");
	         question.setQuestionId(questionId);
			//get question type
			String questionType = request.getParameter("questiontype"+qcounter);
	
		    System.out.println(questionType +"====================qType");
			//if multiple choice
			 if(questionType.equalsIgnoreCase("multiplechoice")) {
				 //collect the selected Answer
				 question.setQuestionType("multiplechoice");
				 String answer = request.getParameter("question-"+qcounter);
				 // save answerlist
				 if(answer == null) {
						answer ="noanswer"; 
						 }
				    System.out.println(answer +"====================answer");
				 question.getAnswerList().add(answer);
				//set in the list of questions
				 studentScript.getListOfQuestion().add(question);
				 System.out.println(  studentScript.getListOfQuestion().size()+  "============================add student script size");
			
			 }
			
			// if multiple answer
			if(questionType.equalsIgnoreCase("multipleanswer")) {
				 question.setQuestionType("multiplechoice");
				
				 //collect the selected Answer
				 String [] answer = request.getParameterValues("question-"+qcounter);
				 // save answerlist
				 if(answer == null) {
				answer = new String [1];
				answer[0] ="noanswer"; 
				 }
				 if(answer != null) {
				 question.getAnswerList().addAll(Arrays.asList(answer));
				 //set question in the list of questions
				 studentScript.getListOfQuestion().add(question);
				 }
			}
			qcounter++;
		}
       

		markScript(request, response,studentScript);
		
	}
	
	public void markScript(HttpServletRequest request,HttpServletResponse response, QuestionPaper paper) throws IOException {
		//mark script and save in database
		 HttpSession  session = request.getSession();
		//get marking scheme
		 QuestionPaper markingScheme = null;
		 
		 String userId = null;
		 if(session.getAttribute("userid") != null) {
			 userId = session.getAttribute("userid").toString();
		 }
		 
		if(session.getAttribute("readyquestion") != null) {
			markingScheme = (QuestionPaper) session.getAttribute("readyquestion");
			paper.setAssessmentId(markingScheme.getAssessmentId());
			paper.setCourse(markingScheme.getCourse());
          
			System.out.println(" Marking scheme available");
		}
		
		//markscript  
		
		
		int score = 0;
		 for(int p = 0 ; p < markingScheme.getListOfQuestion().size(); p++) {
			 Question markingSchemeQ = markingScheme.getListOfQuestion().get(p);
			 System.out.println(markingSchemeQ.getQuestionId());
			 for(int o = 0; o < paper.getListOfQuestion().size(); o++) {
			 // get question
				 Question script = paper.getListOfQuestion().get(o);
				 
			 if(script.getQuestionId().equalsIgnoreCase(markingSchemeQ.getQuestionId())) {
			
			 //if question is equal to multiple question
			 if(script.getQuestionType().equalsIgnoreCase("multiplechoice")) {
				
				 if(script.getAnswerList().get(0).equalsIgnoreCase(markingSchemeQ.getAnswerList().get(0))){
					 score += Integer.parseInt(markingSchemeQ.getPoint());
				 }
				
			 }

			// if question is equal to multiple choice question
			 if(script.getQuestionType().equalsIgnoreCase("multipleanswer")) {
				 MultipleAnswerQuestion d = (MultipleAnswerQuestion)markingSchemeQ;
				 if(script.getAnswerList().get(o).equalsIgnoreCase(markingSchemeQ.getAnswerList().get(p))){
					 score += Integer.parseInt(markingSchemeQ.getPoint());
				 }
				 else {
					 score -= Integer.parseInt(d.getNegativePoint());
				 }
			 }
			 }
			 }
			 
		
			 
		 }
		  ObjectMapper  mapper = new ObjectMapper();
		
		 System.out.println(markingScheme.getCourse()+ "========================== final answer");
		 paper.setCourse(markingScheme.getCourse());
			saveResult(markingScheme.getCourse(), userId, paper.getAssessmentId(), score, mapper.writeValueAsString(paper));
		response.sendRedirect("StudentDataLoader?path=login");
	}
	 
	 //get score and save in database
	//String course_id 
	//String userid
	//assessmentid
	//score
	 public void saveResult(String name,String userid,String assessmentid, int score, String assessment) {
		 StudentDao dao = new StudentDao();
		 System.out.println(name + "======================== save Result");
		 String courseid = dao.getCourseId(name);
		 System.out.println();
		 dao.storeResult(courseid, userid, assessmentid, score, assessment);
	 }

}
