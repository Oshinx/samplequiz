<%@page import="com.rewritingmole.models.QuestionPaper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    <%
    if(session.getAttribute("questionPaper") != null){
        QuestionPaper  paper = (QuestionPaper) session.getAttribute("questionPaper");
        if(paper.getDirection().equalsIgnoreCase("ltr")){
        	out.println();
        }
    }
    %>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


    <title>Instructor Area | Dashboard</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="css/dashboard | style.css" rel="stylesheet">
    <link href="css/questiondetails.css" rel="stylesheet">

  </head>

  <body>

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
            <li class="active"><a href="CreateAssessment.jsp">Create Assessment </a></li>
            <li><a href="pages.html">Activate Assessment </a></li>
            <li><a href="post.html">Change Password</a></li>
            <li><a href="user.html">Quiz Results</a></li>

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
            <h1><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Create Assessment <small></small></h1>
          </div>
        
       </div>
     </header>

     <section id ="breadcrumb">
       <div class="container">
        <ol class="breadcrumb">
          <li class="active">Create Assessment</li>
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
                      <a href="pages.html" class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Create Assessment</a>
                      <a href="post.html" class="list-group-item"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Activate Assessment </a>
                     <!--  <a href="user.html" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Change Password </a>
                      <a href="user.html" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Quiz Results </a>  -->
                    </div>

                   

          </div>
            <div class="col-md-9">
               <div class="panel panel-default">
                    <div class="panel-heading main-color-bg ">
                      <h3 class="panel-title main-color-bg">Question Details</h3>
                    </div>
                    <div class="panel-body">
                    <form action="DisplayQuestion" method="post">
                     <%
                     int  counter = 1;
                     if(session.getAttribute("questionPaper") != null){
                     QuestionPaper  paper = (QuestionPaper) session.getAttribute("questionPaper");
                     System.out.println(paper.getTotalNumberOfQuestion() +" total question size");
                     for(int i = 0; i < Integer.parseInt(paper.getTotalNumberOfQuestion()); i++){
                         String d = "     <div id=\"row-2\" class=\"row-2\">\n" + 
                          		"                <div class=\"question-details\">\n" + 
                          		"                <label for=\"questiontype\"> Question Type "+ counter +" </label>\n" + 
                          		"              <select required=\"\" name=\"questiontype-"+counter+"\" id=\"questiontype\">\n" + 
                          		"                <option value=\"multiplechoice\">Multiple Choice</option>\n" + 
                          		"                <option value=\"multipleanswer\">Multiple Answer</option>\n" + 
                          		"              </select>\n" + 
                          		"\n" + 
                          		"        &nbsp; &nbsp; <label for=\"numberofoptions\">Number of Option</label>\n" + 
                          		"            <input type=\"number\" min=\"2\" max=\"5\" id=\"numberofoptions\" name=\"numberofoptions-"+counter+"\" required=\"\">\n" + 
                          		"          </div>" ; 
                         out.println(d); 				
                          				counter += 1;
                     }
                   } 
                     %>
                  <button   type="submit" id="createquestion"  class="btn btn-default col-md-offset-9">Create Question Details</button>
                      <!--End of Website Overview-->
                  </form>
                 </div>


               
             </div>
        </div>
      </section>
        <div style=" height: 200px;"></div>
     <footer id="footer">
             <p>Copyright Adminstrap &copy; 2018</p>
           </footer>




        
       

    
    <script> 

  </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
   
  </body>
</html>
    