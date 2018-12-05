<%@page import="com.rewritingmole.models.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 


    <title>Instructor Area | Question Details</title>

   
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="css/dashboard | style.css" rel="stylesheet">
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
            <li class="active"><a href="CreateAssessment.jsp">Create Assessment </a></li>
            <li><a href="./InstructorDataLoader?path=manageassessment">Manage Assessment </a></li>
           <!-- <li><a href="post.html">Change Password</a></li> -->	
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
            <h1><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Create Assessment <small></small></h1>
          </div>
           
       </div>
     </header>

     <section id ="breadcrumb">
       <div class="container">
        <ol class="breadcrumb">
        <li class="active"> <a href="CreateAssessment.jsp">Create Assessment</a></li>
         <li class="active"> <a href="./InstructorDataLoader?path=assesseset">Assessment Setting</a></li>
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
                      <a href="CreateAssessment.jsp" class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Create Assessment</a>
                      <a href="./InstructorDataLoader?path=manageassessment" class="list-group-item"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Manage Assessment </a>
                      <!-- <a href="user.html" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Change Password </a> -->
                      <a href="./InstructorDataLoader?path=result" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Quiz Results </a>
                    </div>

                   

          </div>
           <form action="AssessmentPaperSetting"  method="post" id= "the" class="form-horizontal">
            <div class="col-md-9">

               <div class="panel panel-default">
                    <div class="panel-heading main-color-bg ">
                      <h3 class="panel-title main-color-bg">Question Setting</h3>
                    </div>
                    <div class="panel-body">
                     <div class="col-md-12">
                     <div class="form-group">
						  <label for="questionPaperName" class="col-sm-2 control-label">Assessment Name</label>
						    <div class="col-sm-7">
						     <input type="text" class="form-control col-sm-6" name="questionPaperName" id="questionPaperName" required="required">
						    </div>
						  </div>
						  
						  <div class="form-group">
						  <label for="questionPaperinstruction" class="col-sm-2 control-label">Instruction</label>
						    <div class="col-sm-7">
						     <textarea  class="form-control col-sm-6" name="questionPaperinstruction" id="questionPaperinstruction" required="required"></textarea>
						    </div>
						  </div>
						  
			          <div class="form-group">
						  <label for="course" class="col-sm-2 control-label">Course</label>
						    <div class="col-sm-7">
						 <select name = "course" class="form-control col-sm-6" id="course">
						 <% 
                               if(session.getAttribute("listOfCourses") != null){
                               List<Course> course = (List) session.getAttribute("listOfCourses");
                               for(Course c : course){
                               out.println("  <option value = \""+c.getCourseName()+"\">"+c.getCourseName() +"</option>");        
                               System.out.print(c.getDirection());
                               }
                              
                            }
                              %>
						</select>
						    </div>
						  </div>
                          
                          
						  <%
						      if(session.getAttribute("pageloc") != null){
						    	String pageloc = session.getAttribute("pageloc").toString();
						    	if(pageloc.equalsIgnoreCase("quiz")){
						    		String numqueToServe = "                            <div class=\"form-group\">\n" + 
						    		   		"						  <label for=\"numberofquestion\" class=\"col-sm-2 control-label\">Question Size</label>\n" + 
						    		   		"						    <div class=\"col-sm-7\">\n" + 
						    		   		"						     <input type=\"number\" min=\"1\" max=\"70\" class=\"form-control col-sm-6\" name=\"questionsize\" id=\"numberofquestion\" required=\"required\">\n" + 
						    		   		"                            <p class=\"help-block\">Number of Questions for the quiz</p>\n"+
						    		   		"						    </div>\n" + 
						    		   		"						  </div>";
						    		out.println(numqueToServe);
						    	}
						        if(pageloc.equalsIgnoreCase("qset")){
							    	  System.out.println("hello");
							    		String numque=" <div class=\"form-group\">\n" + 
							    		   		"						  <label for=\"numberofquestion\" class=\"col-sm-2 control-label\">Number Of Question</label>\n" + 
							    		   		"						    <div class=\"col-sm-7\">\n" + 
							    		   		"						     <input type=\"number\" min=\"1\" max=\"70\" class=\"form-control col-sm-6\" name=\"numberofquestion\" id=\"numberofquestion\" required=\"required\">\n" + 
							    		   		"						    </div>\n" + 
							    		   		"						  </div>";
							    		   	out.println(numque);			
							    		
							    	}
						        session.removeAttribute("pageloc");
						      }
						  
						  %>

						     <div class="form-group">
						  <label for="releasedate" class="col-sm-2 control-label">Release Date</label>
						    <div class="col-sm-7">
						     <input type="date" class="form-control col-sm-6" name="releasedate" id="releasedate" required="required">
						    </div>
						  </div>

						   <div class="form-group">
						    <label for="expireddate" class="col-sm-2 control-label">Expired Date</label>
						    <div class="col-sm-7">
						     <input type="date" class="form-control col-sm-6" name="expireddate" id="expireddate" >
						    </div>
						  </div>

						  <div class="form-group">
						    <label for="Starttime" class="col-sm-2 control-label">Start Time</label>
						    <div class="col-sm-7">
						     <input type="time"  class="form-control col-sm-6" name="Starttime" id="Starttime" required="required">
						    </div>
						  </div>

						    <div class="form-group">
						    <label for="duration" class="col-sm-2 control-label">duration</label>
						    <div class="col-sm-7">
						    <input type="number"  min="20" max="180" class="form-control col-sm-6" name="duration" id="duration" required="required">
						    </div>
						    </div>

						   <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <div class="checkbox">
						        <label>
						          <input type="checkbox" name="Randomization" value="true"> Randomization
						        </label>
						      </div>
							    </div>
							  </div>

							 <!--   <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <div class="checkbox">
						        <label>
						          <input type="checkbox" name="feedback" value="true"> Feedback
						        </label>
						      </div>
							    </div>
							  </div> --> 

							   <div class="form-group">
								    <div class="col-sm-offset-2 col-sm-10">
								      <button type="submit" id="ok-button" class="btn btn-default "> OK</button>
								    </div>
  								</div>

						

                      <!--End of Website Overview-->
                    </div>
                 </div>
             </div>
       
            
                      </form>
                      
                   	 <form  id="questionform" class="form-horizontal">
                     <div id= "question" >

                     </div>


             	 </form>


               
             </div>
        </div>
      </section>
 <div style=" height: 200px;"></div>
   <footer id="footer">
             <p>Copyright Quizoli &copy; 2018</p>
           </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
