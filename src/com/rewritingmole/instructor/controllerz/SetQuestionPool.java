package com.rewritingmole.instructor.controllerz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;

import com.rewritingmole.models.Question;
import com.rewritingmole.models.QuestionPool;
import com.rewritingmolequiz.dao.InstructorDao;

/**
 * Servlet implementation class SetQuestionPool
 */
@WebServlet("/SetQuestionPool")
public class SetQuestionPool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the userId 
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//get the question pool 
		// set it in the session 
		//redirect to display page
		InstructorDao dao = new InstructorDao();
		QuestionPool pool;

		 
		//get the user id from session 
		String userId = null;
		if(session.getAttribute("iuserid") != null) {
			userId = session.getAttribute("iuserid").toString();
		}
		
		 if(session.getAttribute("errorqueSize") != null){
   			 session.removeAttribute("errorqueSize");
           }
              
	
				pool = dao.getQuestionPool(userId);
						System.out.println(pool.getListOfQuestion().size()+"====================pool si");
					     
				
						session.setAttribute("qpool", pool);
						response.sendRedirect("SelectQuestions.jsp");;

				// TODO Auto-generated catch block
		

	}

	

}
