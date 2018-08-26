package com.rewritingmole.instructor.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.model.QuestionPaper;

import javafx.scene.shape.QuadCurve;

/**
 * Servlet implementation class QuestionPaperSetting
 */
@WebServlet("/AssessmentPaperSetting")
public class AssessmentPaperSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("questionPaperName");
		String course  = request.getParameter("course");
		String numberOfQuestion = request.getParameter("numberofquestion");
		String releaseDate = request.getParameter("releasedate");
		String expiredDate = request.getParameter("expireddate");
		String startTime = request.getParameter("Starttime");
		String duration = request.getParameter("duration");
		String randomization [] = request.getParameterValues("Randomization");
		String feedback [] = request.getParameterValues("feedback");
		HttpSession httpSession = request.getSession();
		
		System.out.println("\n cousre: "+course +"\n numberOfQuestion: "+ numberOfQuestion + "\n releaseDate: "+ releaseDate
				+ "\n expiredDate: "+ expiredDate + "\n startTime: "+ startTime + "\n duration: "+ duration  + "\n randomization: "+randomization[0]
				+ "\n feedback: "+ feedback[0]);
		QuestionPaper paper = new QuestionPaper();
		paper.setName(name);
		paper.setCourse(course);
		paper.setTotalNumberOfQuestion(numberOfQuestion);
		paper.setReleaseDate(releaseDate);
		paper.setExpiredDate(expiredDate);
		paper.setStartTime(startTime);
		paper.setDuration(duration);
		paper.setRandomization(randomization[0]);
		paper.setFeedback(feedback[0]);
		//need to add direction of the question paper
		httpSession.setAttribute("questionPaper", arg1);
	   	RequestDispatcher rd =  request.getRequestDispatcher("QuestionsDetail.jsp");
		rd.forward(request, response);
		
		
		

	}

}
