<%@page import="com.rewritingmole.models.Question"%>
<%@page import="com.rewritingmole.models.QuestionPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Instructor Area | Dashboard</title>

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
           <li><a href="./InstructorDataLoader?path=dashboard">Dashboard</a></li>
					<li class="active"><a href="CreateAssessment.jsp">Create
							Assessment </a></li>
					<li><a href="./InstructorDataLoader?path=manageassessment">Manage Assessment </a></li>
					<!--  <li><a href="post.html">Change Password</a></li>-->
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
          <li class="active">Create Assessment</li>
        </ol>
       </div>
     </section>


      <section id="main">
        <div class="container">
          <div class="row">
             <div class="col-md-3">
               <div class="list-group">
                      	<a href="./InstructorDataLoader?path=dashboard"
							class="list-group-item "><span
							class="glyphicon glyphicon-cog" aria-hidden="true"></span>
							Dashboard </a>
							
						 <a href="CreateAssessment.jsp"class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							Create Assessment</a> 
						<a href="./InstructorDataLoader?path=manageassessment" class="list-group-item"><span
							class="glyphicon glyphicon-education" aria-hidden="true"></span>
							Manage Assessment </a>
						<!-- 	 <a href="" class="list-group-item"><span
							class="glyphicon glyphicon-edit" aria-hidden="true"></span>
							Change Password </a>--> 
							<a href="./InstructorDataLoader?path=result" class="list-group-item"><span
							class="glyphicon glyphicon-edit" aria-hidden="true"></span> Quiz
							Results </a></div>

                   

          </div>
         

            <div class="col-md-9">
              <%
           
           if(session.getAttribute("errorqueSize") != null){
        
               String success = session.getAttribute("errorqueSize").toString(); 
               out.println(success); 
               }
           
           if(session.getAttribute("errorqueSize") != null){
   			 session.removeAttribute("errorqueSize");
           }
               
                
           
           %>
              <div class="form-group">
                <input type="text" class="form-control" id="exampleInputName2" placeholder="Search By Course Name">
              </div>
             
           <form action="CreateQuiz" method="post" id= "the" class="form-horizontal">                
                 <%
                 System.out.println("outside questionPrint");
                 if(session.getAttribute("qpool") != null){
         
                	 QuestionPool pool = (QuestionPool) session.getAttribute("qpool");
                	 int poolSize = pool.getListOfQuestion().size();
                	 int qcoter = 1;
               		 String optionLabel [] =  {"A","B","C","D","E"};
                	 for(int i = 0 ; i < poolSize ; i++){
                        
                       Question question = pool.getListOfQuestion().get(i);
                       System.out.println(question.getQuestiondirection() +"=====================qtext");
                        
                       //to resolve bug
                       if(question.getQuestiondirection() ==  null){
                    	   question.setQuestiondirection("rtl");
                       }
                       
                       if(question.getQuestiondirection().equalsIgnoreCase("ltr")){
                       	String qltr ="\n" + 
                    			"                     <div class=\"panel panel-default \">\n" + 
                    			"                       <div class=\"panel-heading main-color-bg \">\n" + 
                    			"                      <h3 class=\"panel-title main-color-bg\">Question "+qcoter+"</h3>\n" + 
                    			"                    </div>\n" + 
                    			"                          <table class=\"table\"> \n" + 
                    			"                                \n" + 
                    			"                                      <tbody> <tr>\n" + 
                    			"                                        <th ><input type=\"checkbox\" name=\"selectedquestions\" value=\""+question.getQuestionId()+"\"></th> \n" + 
                    			"                                          </tr>\n" + 
                    			"                                        \n" + 
                    			"                                        <tr>\n" + 
                    			"                                        \n" + 
                    			"                                         <td>Question "+qcoter+"</td> \n" + 
                    			"                                         <td>"+question.getQuestionText()+"</td>\n" + 
                    			"                                          </tr>\n" + 
                    			"\n" ; 
                    			out.println(qltr);
                    			int opcounter =0;
                    			int optionSize = Integer.parseInt(question.getOptionSize());
                    			for(int j = 0; j < optionSize ; j++){
                    				opcounter +=1;
                    				String oltr =
                                			"                                          <tr>\n" + 
                                			"                                         <td>"+optionLabel[j]+"</td> \n" + 
                                			"                                         <td>"+question.getOptionList().get(j)+"</td>\n" + 
                                			"                                          </tr>\n" 
                                	;
                                			out.println(oltr);
                    			}
                    			String end =		"\n" + 
                            			"                                              \n" + 
                            			"                                              </tbody>\n" + 
                            			"\n" + 
                            			"                                               </table>\n" + 
                            			"                                                  <input type=\"hidden\" name=\"\" value=\""+question.getCoursename()+"\" id=\"Course\">   \n" + 
                            			"                                        \n"+
                            					                             "</div>";
                            			out.println(end);
                    		
                    	   
                       }
                       if(question.getQuestiondirection().equalsIgnoreCase("rtl")){
                    		String qltr ="\n" + 
                        			"                     <div  dir=\"rtl\" class=\"panel panel-default \">\n" + 
                        			"                       <div class=\"panel-heading main-color-bg \">\n" + 
                        			"                      <h3 class=\"panel-title main-color-bg\">Question "+qcoter+"</h3>\n" + 
                        			"                    </div>\n" + 
                        			"                          <table class=\"table\"> \n" + 
                        			"                                \n" + 
                        			"                                      <tbody> <tr>\n" + 
                        			"                                        <th ><input type=\"checkbox\" name=\"selectedquestions\" value=\""+question.getQuestionId()+"\"></th> \n" + 
                        			"                                          </tr>\n" + 
                        			"                                        \n" + 
                        			"                                        <tr>\n" + 
                        			"                                        \n" + 
                        			"                                         <td>Question "+qcoter+"</td> \n" + 
                        			"                                         <td>"+question.getQuestionText()+"</td>\n" + 
                        			"                                          </tr>\n" + 
                        			"\n" ; 
                        			out.println(qltr);
                        			int opcounter =0;
                        			int optionSize = Integer.parseInt(question.getOptionSize());
                        			for(int j = 0; j < optionSize ; j++){
                        				opcounter +=1;
                        				String oltr =
                                    			"                                          <tr>\n" + 
                                    			"                                         <td>"+optionLabel[j]+"</td> \n" + 
                                    			"                                         <td>"+question.getOptionList().get(j)+"</td>\n" + 
                                    			"                                          </tr>\n" 
                                    	;
                                    			out.println(oltr);
                        			}
                        			String end =		"\n" + 
                                			"                                              \n" + 
                                			"                                              </tbody>\n" + 
                                			"\n" + 
                                			"                                               </table>\n" + 
                                			"                                                  <input type=\"hidden\" name=\"\" value=\""+question.getCoursename()+"\" id=\"Course\">   \n" + 
                                			"                                        \n"+
                                					                             "</div>";
                                			out.println(end);
                       }
                       qcoter++;
               		
                	 }
                	 out.println("<button   type=\"submit\" id=\"createquestion\"  class=\"btn btn-default col-md-offset-9\">Create Question Details</button>");
                 }
                 %>
           
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
