package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rewritingmole.models.QuestionPaper;
import com.rewritingmolequiz.dao.InstructorDao;

/**
 * Servlet implementation class ManageAssessment
 */
@WebServlet("/ManageAssessment")
public class ManageAssessment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String link = request.getParameter("path");
    
  	 StringTokenizer stringTokenizer = new StringTokenizer(link,":");
  	 String path = stringTokenizer.nextToken();
  	 String id = stringTokenizer.nextToken();
  	 modifyStatus(request, response, path, id);
  	 
	}
	

	
    protected void modifyStatus(HttpServletRequest request, HttpServletResponse response, String path,String assessmentId) throws IOException {
    	   InstructorDao dao = new InstructorDao();
    	    HttpSession  httpSession = request.getSession();
    	    
    	    if(httpSession.getAttribute("listOfQuestionPaper") != null) {
    	    	httpSession.removeAttribute("listOfQuestionPaper");
    	    }
    		//get the user id from session 
    		String userId = null;
    		if(httpSession.getAttribute("iuserid") != null) {
    			userId = httpSession.getAttribute("iuserid").toString();
    		}
    	    
    	if(path.contains("activated")) {
    		// activate the database
    		//get  the id of  the question and change status to deactivate
    		try {
				dao.modifyQuestionPaperStatus(assessmentId, "activated");
				;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	if(path.contains("deactivated")) {
    		// deactivate the database
    		//get  the id of  the question and change status to deactivate
    		try {
				dao.modifyQuestionPaperStatus(assessmentId, "deactivated");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    		
    	}
    	
    	List<QuestionPaper>  listOfquestionPaper = dao.getquestionPapersInfo(userId);
    	//System.out.println(listOfquestionPaper.size());
    	httpSession.setAttribute("listOfQuestionPaper", listOfquestionPaper);
    	response.sendRedirect("ManageAssessment.jsp");
    	
    }
    
    protected void RedirectToEditPage(HttpServletRequest request, HttpServletResponse response, String path,String assessmentId) {
    	//get path and id 
    	// use id to get question set 
    	//display question in the redirect
    	
    	
    }

}
