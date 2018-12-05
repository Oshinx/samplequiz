package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.models.Result;
import com.rewritingmolequiz.dao.InstructorDao;

/**
 * Servlet implementation class DisplayResult
 */
@WebServlet("/DisplayResult")
public class DisplayResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String path = request.getParameter("path");
	    
	     System.out.println("DisplayResult");
		 StringTokenizer stringTokenizer = new StringTokenizer(path,":");
	  	 String direction = stringTokenizer.nextToken();
	  	 String id = stringTokenizer.nextToken();
	  	 HttpSession session = request.getSession();
	  	 
	  	 if(session.getAttribute("courseresult") != null) {
	  		 session.removeAttribute("courseresult");
	  	 }
	  	 
	  	 if(direction.equalsIgnoreCase("gresult")) {
	  		 InstructorDao dao = new InstructorDao();
	  		 List<Result> listOfResult =  dao.getInstructorResults(id);
	  		 session.setAttribute("courseresult", listOfResult);
	  		 response.sendRedirect("CourseGrade.jsp");
	  	 }
	}

	

}
