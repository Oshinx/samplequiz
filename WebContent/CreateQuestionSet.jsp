<%@page import="com.rewritingmole.models.MultipleAnswerQuestion"%>
<%@page import="com.rewritingmole.models.MultipleChoiceQuestion"%>
<%@page import="com.rewritingmole.models.Question"%>
<%@page import="com.rewritingmole.models.QuestionPaper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   


    <title>Instructor Area | Dashboard</title>


   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="css/dashboard | style.css" rel="stylesheet">
     <%
              System.out.println( session.getAttribute("questionPaper") != null);
            if( session.getAttribute("questionPaper") != null){
            QuestionPaper paper = (QuestionPaper) session.getAttribute("questionPaper");
            if(paper.getDirection().equalsIgnoreCase("rtl")){
            	out.println("<link id=\"rtllink\" rel=\"stylesheet\" href=\"css/bootstrap-rtl.css\">");
            }
          }
    %>
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
            <li class="active"><a href="#">Create Assessment </a></li>
            <li><a href="./InstructorDataLoader?path=manageassessment">Manage Assessment</a></li>
           <!--  <li><a href="post.html">Change Password</a></li> -->
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
     </div></header>

     <section id="breadcrumb">
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
                      <a href="Instructor_Dashboard.jsp" class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Create Assessment</a>
                      <a href="./InstructorDataLoader?path=manageassessment" class="list-group-item"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Manage Assessment </a>
                    <!--   <a href="user.html" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Change Password </a> -->
                      <a href="./InstructorDataLoader?path=result" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Quiz Results </a>
                    </div>

                   

          </div>
           <form   action="CreateQuestionSet" method="post"  id="the" class="form-horizontal">
            <div class="col-md-9">
            <%
              System.out.println("welcome");
            if( session.getAttribute("questionPaper") != null){
            	int qcounter = 1;
            QuestionPaper paper = (QuestionPaper) session.getAttribute("questionPaper");
              for(int i = 0 ; i < Integer.parseInt(paper.getTotalNumberOfQuestion()); i++){
            	Question question =  paper.getListOfQuestion().get(i);
            	
            	
            	if (question instanceof MultipleChoiceQuestion){
           		 int optionSize = Integer.parseInt(((MultipleChoiceQuestion)question).getOptionSize());

     			String panelTop= "  <div id=\"question\" class=\"panel panel-default\">\n" + 
     					"    <div>\n" + 
     					"      <div class=\"panel-heading main-color-bg \">\n" + 
     					"         <h3 class=\"panel-title main-color-bg\">Question "+ qcounter +"</h3>\n" + 
     					"      </div>\n" + 
     					"         <div class=\"panel-body\"> \n" + 
     					"            <div class=\"col-md-12\">";
     					out.println(panelTop);
     				   String qText="  <div class=\"form-group\">\n" + 
   							"                  <label class=\"col-sm-2 control-label\">Question </label>\n" + 
  /* question done */       "              <div class=\"col-sm-10\">\n" + 
   							"                <input type=\"text\" name=\"questiontext-"+qcounter+"\" class=\"form-control col-sm-10\" required=\"required\">\n" + 
   							"             </div>\n" + 
   							"            </div>";
   							out.println(qText);
     		       int opCounter =0;
            		 for(int k = 0; k < optionSize; k++){
            			 opCounter +=1;
         				String mulchoiceOption = " <div class=\"form-group \">\n" + 
         						"              <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">A </label>\n" + 
        /*option done */ 		"                <div class=\"col-sm-10 radio\"><input type=\"radio\" name=\"qans"+qcounter+"\" class=\"options\" required=\"required\">\n" + 
         						"                  <textarea class=\"form-control col-sm-10\" name=\"question"+qcounter+"Option"+opCounter+"\" required=\"required\"></textarea>\n" + 
         						"                </div>\n" + 
         						"            </div>";
         								
         			out.println(mulchoiceOption);					
         		 }
            		 
            			String panelmidEnd="<div class=\"form-group\">\n" + 
            					"             <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">point</label>\n" + 
            					"                <div class=\"col-sm-10\">\n" + 
  /*done point*/  					"                  <input type=\"number\" min=\"0\" max=\"10\"  name=\"question"+qcounter+"-point\" class=\"form-control col-sm-10\" id=\"exampleInputName2 required =\" required\"\"=\"\">\n" + 
            					"                </div>\n" + 
            					"            </div>\n" + 
            					"\n" ; 
            			 out.println(panelmidEnd);
      					if(paper.getFeedback() != null){
      						String feedback =		
      	         					"          <div class=\"form-group\">\n" + 
      	         					"              <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Feedback </label>\n" + 
      	/*done feedback */               		"             <div class=\"col-sm-10\">\n" + 
      	         					"                <input type=\"text\" name=\"feedback"+qcounter+"\" class=\"form-control col-sm-10\" id=\"exampleInputName2 required =\" required\"\"=\"\">\n" + 
      	         					"             </div>\n" + 
      	         					"         </div>\n" ;
      	         							out.println(feedback);
      					}
      				
      					String end =
     					"                          <!-- start --> </div>\n" + 
     					"                           \n" + 
     					"                </div>\n" + 
     					"              </div>\n" + 
     					"\n" + 
     					"            </div>";;
            							
     					out.println(end);
         		 
            	}
            	
            	if(question instanceof MultipleAnswerQuestion){
            		
            		 int optionSize = Integer.parseInt(((MultipleAnswerQuestion)question).getOptionSize());

            			String panelTop= "  <div id=\"question\" class=\"panel panel-default\">\n" + 
            					"    <div>\n" + 
            					"      <div class=\"panel-heading main-color-bg \">\n" + 
            					"         <h3 class=\"panel-title main-color-bg\">Question "+ qcounter +"</h3>\n" + 
            					"      </div>\n" + 
            					"         <div class=\"panel-body\"> \n" + 
            					"            <div class=\"col-md-12\">";
            				out.println(panelTop);
            		 
            	        String qText="  <div class=\"form-group\">\n" + 
         /* question done */ 			"                  <label class=\"col-sm-2 control-label\">Question </label>\n" + 
            							"              <div class=\"col-sm-10\">\n" + 
            							"                <input type=\"text\" name=\"questiontext-"+qcounter+"\" class=\"form-control col-sm-10\" required=\"required\">\n" + 
            							"             </div>\n" + 
            							"            </div>";
            				out.println(qText);
            				int opCounter = 0;
            		 for(int k = 0; k < optionSize; k++){
            			      opCounter +=1;
            				String multipleAnswerOption =  "<div class=\"form-group \">\n" + 
            			     		"                       <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">A </label>\n" + 
    /*options done */         		"                         <div class=\"col-sm-10 checkbox\"><input type=\"checkbox\" name=\"qans"+qcounter+"\" class=\"options\" value=\"option1\">\n" + 
            			     		"                           <textarea  name=\"question"+qcounter+"Option"+opCounter+"\" class=\"form-control col-sm-10\"></textarea>\n" + 
            			     		"                         </div>\n" + 
            			     		"                     </div>";
            			  
            			out.println(multipleAnswerOption);					
            		 }

         			String panelmidEnd="<div class=\"form-group\">\n" + 
         					"             <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">point</label>\n" + 
         					"                <div class=\"col-sm-10\">\n" + 
/*done point*/  					"                  <input type=\"number\" min=\"0\" max=\"10\"  name=\"question"+qcounter+"-point\" class=\"form-control col-sm-10\" id=\"exampleInputName2 required =\" required\"\"=\"\">\n" + 
         					"                </div>\n" + 
         					"            </div>\n" + 
         					
         					"<div class=\"form-group\">\n" + 
         			    			"						  <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Negative Point </label>\n" + 
         			    			"						    <div class=\"col-sm-10\">\n" + 
         			    			"						    <input type=\"number\" class=\"form-control col-sm-10\" max =\"10\" min=\"0\" name =\"question"+qcounter+"negpoint\" required =\"required\">\n" + 
         			    			"						    </div>\n" + 
         			    			"						  </div>"+
         					"\n"; 
         			    			 out.println(panelmidEnd);
         					if(paper.getFeedback() != null){
         						String feedback =		
         	         					"          <div class=\"form-group\">\n" + 
         	         					"              <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Feedback </label>\n" + 
         	/*done feedback */               		"             <div class=\"col-sm-10\">\n" + 
         	         					"                <input type=\"text\" name=\"feedback"+qcounter+"\" class=\"form-control col-sm-10\" id=\"exampleInputName2 required =\" required\"\"=\"\">\n" + 
         	         					"             </div>\n" + 
         	         					"         </div>\n" ;
         	         							out.println(feedback);
         					}
         			    	String end =
         					"                          <!-- start --> </div>\n" + 
         					"                           \n" + 
         					"                </div>\n" + 
         					"              </div>\n" + 
         					"\n" + 
         					"            </div>";
            	        		
            	out.println(end);
            		 
            	}
            	qcounter +=1;
              }
              
              String button="<button type=\"submit\" id=\"createquestion\"  class=\"btn btn-default col-md-offset-2\">Create Question</button>";
              out.println(button);
            }
            %>
            
              
 
               </form>
            
    

               
             </div>
        </div>
      </div></section>
 <div style=" height: 200px;"></div>
   <footer id="footer">
             <p>Copyright Quizoli © 2018</p>
           </footer>




        
       


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
  

</body></html>