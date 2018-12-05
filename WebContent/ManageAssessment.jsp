<%@page import="com.rewritingmole.models.QuestionPaper"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


    <title>Instructor Area | Dashboard</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="css/dashboard | style.css" rel="stylesheet">

    <script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>
  </head>

  <body>
    <%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setHeader("Expires", "0");
  Cookie [] cookies = request.getCookies();
  boolean valid = false;
  if(cookies !=null){
	  for(Cookie cookie: cookies){
	      valid = cookie.getName().equalsIgnoreCase("iuid");
	    
	  }}
  if(valid == false){
  	   response.sendRedirect("Login.jsp");
     }
  %>
    <nav class="navbar navbar-default ">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Quizoli</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li ><a href="./InstructorDataLoader?path=dashboard">Dashboard</a></li>
            <li><a href="CreateAssessment.jsp">Create Assessment </a></li>
            <li class="active"><a href="./InstructorDataLoader?path=manageassessment">Manage  Assessment </a></li>
       <!--    <li><a href="post.html">Change Password</a></li> -->
            <li><a href="./InstructorDataLoader?path=result">Quiz Results</a></li>

          </ul>

           <ul class="nav navbar-nav navbar-right">
            <li class="#"><a href="#">Welcome, ${iusername}</a></li>
             <li class="#"><a href="./Logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout</a></li>
         
            
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
     
     <!--Header-->
     <header id="header">
       <div class="container">
         <div class="row">
          <div class="col-md-10">
            <h1><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Manage  Assessment <small></small></h1>
          </div>
        
       </div>
     </header>

     <section id ="breadcrumb">
       <div class="container">
        <ol class="breadcrumb">
          <li class="active">Manage  Assessment</li>
        </ol>
       </div>
     </section>


      <section id="main">
        <div class="container">
          <div class="row">
             <div class="col-md-3">
               <div class="list-group">
                      <a href="./InstructorDataLoader?path=dashboard" class="list-group-item "><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 
                        Dashboard
                      </a>
                      <a href="CreateAssessment.jsp" class="list-group-item  "><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Create Assessment</a>
                      <a href="./InstructorDataLoader?path=manageassessment" class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Manage  Assessment</a>
                   <!--    <a href="user.html" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Change Password </a>  -->
                      <a href="./InstructorDataLoader?path=result" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Quiz Results </a>
                    </div>

                  

          </div>
            <div class="col-md-9">
               <div class="panel panel-default">
                    <div class="panel-heading main-color-bg ">
                      <h3 class="panel-title main-color-bg">Manage  Assessment</h3>
                    </div>
                    <div class="panel-body">
                     <table class="table table-striped table-hover "> 
                    <thead>
                     <tr>
                      <th>#</th> 
                      <th>Quiz Name</th>
                         </tr> </thead>
                          <tbody> 
                       
                           <%
                 
                      if(session.getAttribute("listOfQuestionPaper") != null){
                    	 List<QuestionPaper> listOfQuestionPapers = (List) session.getAttribute("listOfQuestionPaper");
                      int  counter = 0;
           
                           System.out.println(listOfQuestionPapers.size());
                    	  for(QuestionPaper paper : listOfQuestionPapers){
                    		  counter += 1;
                              out.println("<tr>");
                    		  if(paper.getStatus().equalsIgnoreCase("activated")){
                          		  out.println("<th>"+counter+"</th> ");
                        		  out.println("<td>"+paper.getName()+"</td>");
                        		  out.println("<td><a class=\"btn btn-success disabled \" href=\"./ManageAssessment?path=activated:"+paper.getAssessmentId()+":\">Activate</a></td>");
                        		  out.println("<td><a class=\"btn btn-danger \" href=\"./ManageAssessment?path=deactivated:"+paper.getAssessmentId()+":\">Deactivate</a></td>");
                        		 // out.println("<td><a class=\"btn btn-primary\" href=\"./ManageAssessment?path=edit:"+paper.getAssessmentId()+":\">Activate</a></td>");
                        	  
                    		  }
                    		  if(paper.getStatus().equalsIgnoreCase("deactivated")){
                    			  
                        		  out.println("<th class=\"table-text\">"+counter+"</th> ");
                        		  out.println("<td class=\"table-text\">"+paper.getName()+"</td>");
                        		  out.println("<td><a class=\"btn btn-success \" href=\"./ManageAssessment?path=activated:"+paper.getAssessmentId()+":\">Activate</a></td>");
                        		  out.println("<td><a class=\"btn btn-danger disabled \" href=\"./ManageAssessment?path=deactivated:"+paper.getAssessmentId()+":\">Deactivate</a></td>");
                        		//  out.println("<td><a class=\"btn btn-primary\" href=\"./ManageAssessment?path=edit:"+paper.getAssessmentId()+":\">Edit</a></td>");
                    			  
                    		  }
                    		  out.println("</tr>");
                    	  }
                    	
                      }
                   %>
                       

                                  </tbody>
                                   </table>
                                 
                      <!--End of Website Overview-->
                    </div>
                 </div>


               
             </div>
        </div>
      </section>
 <div style=" height: 200px;"></div>
   <footer id="footer">
             <p>Copyright Adminstrap &copy; 2017</p>
           </footer>




        
  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>
    