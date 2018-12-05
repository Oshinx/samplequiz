package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rewritingmole.models.Question;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmole.models.QuestionPool;
import com.rewritingmolequiz.dao.InstructorDao;

/**
 * Servlet implementation class CreateQuiz
 */
@WebServlet("/CreateQuiz")
public class CreateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		String selectedQuestions [] = request.getParameterValues("selectedquestions"); 
		HttpSession session = request.getSession();
	    QuestionPaper paper = (QuestionPaper) session.getAttribute("questionPaper");
	  System.out.println(paper.getDirection());
	    // if selected question size is less size of questions display error
	  if(selectedQuestions == null) {
	     selectedQuestions = new String[0];
				  
	  }

		 
		//get the user id from session 
		String userId = null;
		if(session.getAttribute("iuserid") != null) {
			userId = session.getAttribute("iuserid").toString();
		}
		
	    if(Integer.parseInt(paper.getQuestionSize()) >= selectedQuestions.length) {
	      session.setAttribute("errorqueSize","<div class=\"alert alert-danger\" role=\"alert\">\n" + 
		    		"  <span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n" + 
		    		"  <span class=\"sr-only\">Error:</span>\n" + 
		    		"  Select more than "+paper.getQuestionSize()+" questions\n" + 
		    		"</div>" );
	    	response.sendRedirect("SelectQuestions.jsp");
	    }
	  
	       //get the selected array of questions using the id 
	    QuestionPool pool = (QuestionPool) session.getAttribute("qpool");
	    int qcount =0;
	     int quePoolSize = pool.getListOfQuestion().size();
	              for (int i = 0; i < quePoolSize; i++) {
					Question question = pool.getListOfQuestion().get(i);
				     if(qcount >= selectedQuestions.length) {
				    	 break;
				     }
					
					//compare questionId and store in new questionPaper
					if(question.getQuestionId().equalsIgnoreCase(selectedQuestions[qcount])) {
				    paper.getListOfQuestion().add(question);				
					}
					qcount++;
				}
	              
	           //save question set into the database 
	              InstructorDao dao = new InstructorDao();
	              ObjectMapper  mapper  = new ObjectMapper();
	              String questioPaper = mapper.writeValueAsString(paper);
	              String couresid = dao.getCourseId(paper.getCourse());
	              dao.createAssessment(paper.getAssessmentId(),paper.getName(),userId,couresid, questioPaper, "deactivated", paper.getFeedback());
		           response.sendRedirect("CreateAssessment.jsp");
	}

}
