<%@page import="javax.sound.midi.Soundbank"%>
<%@page import="com.rewritingmole.models.MultipleAnswerQuestion"%>
<%@page import="com.rewritingmole.models.MultipleChoiceQuestion"%>
<%@page import="com.rewritingmole.models.Question"%>
<%@page import="com.rewritingmole.models.QuestionPaper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 
    <title>Instructor Area | Dashboard</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="css/dashboard | style.css" rel="stylesheet">
       <%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setHeader("Expires", "0");
  Cookie [] cookies = request.getCookies();
  boolean valid = false;
  if(cookies != null){
	  for(Cookie cookie: cookies){
	      valid = cookie.getName().equalsIgnoreCase("suid");
	   
	  }}
  if(valid == false){
  	   response.sendRedirect("Login.jsp");
     }
  %>
   
    <%
    if(session.getAttribute("readyquestion") != null){
    	QuestionPaper qp = (QuestionPaper) session.getAttribute("readyquestion");
    
    	if (qp.getDirection() != null){
    	 if(qp.getDirection().equalsIgnoreCase("rtl")){
    		 String pageDirection = "<link id=\"rtllink\" rel=\"stylesheet\" href=\"css/bootstrap-rtl.css\">";
    		out.println(pageDirection);
    	}
      }
    }
    %>
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
            <li ><a href="./StudentDataLoader?path=login">Dashboard</a></li>
            <li class="active"><a href="./StudentDataLoader?path=quiz">Quizzes </a></li>
            <li><a href="./StudentDataLoader?path=result">Grades </a></li>
           <!--  <li><a href="post.html">FeedBack</a></li>  -->
          </ul>

           <ul class="nav navbar-nav navbar-right">
                      <li class="#"><a href="#">Welcome, ${susername}</a></li>
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
            <h1><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Quiz </h1>
          </div>
           
       </div>
     </header>

   
       <div class="container">
      <div class="well well">
      <h4 class="text-right col-md-5"> ${name}</h4>
      <h4 class="text-right" id="quiz-time-left">TIME :  0:00</h4> 
      <script type="text/javascript">
	var total_seconds = 60*${time}; 
	var c_mintutes = parseInt(total_seconds/60);
	var c_seconds = parseInt(total_seconds%60);
	function checkTime(){
		document.getElementById("quiz-time-left").innerHTML=
		'Time left:' +c_mintutes+ 'minutes '+c_seconds +'seconds';
		if(total_seconds <=0){
			setTimeout('document.sub.submit()',1);
		}
		else{
			total_seconds = total_seconds -1;
			c_mintutes = parseInt(total_seconds/60);
			c_seconds = parseInt(total_seconds%60);
			setTimeout('checkTime()',1000);
		}
	}
	setTimeout("checkTime()",1000);
</script>	
      </div>
       </div>
 


      <section id="main">
        <div class="container">
          <div class="row">
            
           <form name="sub" action="MarkQuiz" method="post" id= "the" class="form-horizontal">
            <div class="col-md-9 col-sm-offset-2">
            <%
            String optionLabel [] = {"A","B","C","D","E"};
            if(session.getAttribute("readyquestion") != null){
            	QuestionPaper qp = (QuestionPaper) session.getAttribute("readyquestion");
            	  int qcounter = 1 ;
            	  
            	  out.println(	" <input type=\"hidden\" name=\"questionsize\" value=\""+qp.getListOfQuestion().size()+"\">\n");
                  for(int i = 0; i < qp.getListOfQuestion().size(); i++){
                     Question question = qp.getListOfQuestion().get(i);
                        ;
                     //display question for multiplechoice question question
                     if(question.getQuestionType().equalsIgnoreCase("multiplechoice")){
                    	 //display question text
                    	                     	  String hidden =" <input type=\"hidden\" name=\"questionid"+qcounter+"\"  value=\""+question.getQuestionId()+"\">\n" + 
                    	  	 		"                 <input type=\"hidden\" name=\"questiontype"+qcounter+"\" value=\"multiplechoice\">";
                   	  	 				
                   	  	 	out.println(hidden);
             
                    	 String questionText = "     <div id=\"question\" class=\"panel panel-default\">\n" + 
                 	  	 		"                   <div>\n" + 
                	  	 		"	                  <div class=\"panel-heading main-color-bg \">\n" + 
                	  	 		"	                    <h3 class=\"panel-title main-color-bg\">Question "+qcounter+"</h3>\n" + 
                	  	 			
                	  	 		"	                    </div>\n" + 
                	  	 		"		                    <div class=\"panel-body\"> \n" + 
                	  	 		"		                    <div class=\"col-md-12\">\n" + 
                	  	 		"		                    <div class=\"form-group\">\n" + 
                	  	 		"		                    <label class=\"col-sm-2 control-label\">Question "+qcounter+"</label>\n" + 
                	  	 		"		                    <div class=\"col-sm-10\">\n" + 
                	  	 		"		                      <h5>"+question.getQuestionText()+"</h5>  \n" + 
                	  	 		"		                     </div>\n" + 
                	  	 		"              </div>";
                    	 out.println(questionText);
            
                    	 //display question option
                    	 
                    	 for(int y = 0;  y < question.getOptionList().size(); y++){
                    	   	  String options ="  <div class=\"form-group \">\n" + 
                    		  	 		"                    <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">"+optionLabel[y]+" </label>\n" + 
                    		  	 		"                    <div class=\"col-sm-10 radio\"><input type=\"radio\" name=\"question-"+qcounter+"\" class=\"options \" value=\""+question.getOptionList().get(y)+"\">\n" + 
                    		  	 		"                     <h5>"+question.getOptionList().get(y)+" </h5> \n" + 
                    		  	 		"                    </div>\n" + 
                    		  	 		"                    </div>";
                    	    	  
                    		 
                    		 out.println(options);
                    	 }
	
                    	    String end = "  </div>\n" + 
                    	  	 		"                     </div>\n" + 
                    	  	 		"\n" + 
                    	  	 		"              </div>\n" + 
                    	  	 		"\n" + 
                    	  	 		"              </div>";		
                	    	  		out.println(end);
                    	 
                     }
                     
                     
                     
                     //display question for multiple answer question
                     if(question.getQuestionType().equalsIgnoreCase("multipleanswers")){
                     	 //display question text
                     	   	   	  String hidden =" <input type=\"hidden\" name=\"questionid"+qcounter+"\"  value=\""+question.getQuestionId()+"\">\n" + 
                    	  	 		"                 <input type=\"hidden\" name=\"questiontype"+qcounter+"\" value=\"multipleanswer\">";
                   	  	 				
                   	  	 	out.println(hidden);	
                                String questionText = "     <div id=\"question\" class=\"panel panel-default\">\n" + 
                 	  	 		"                   <div>\n" + 
                	  	 		"	                  <div class=\"panel-heading main-color-bg \">\n" + 
                	  	 		"	                    <h3 class=\"panel-title main-color-bg\">Question "+qcounter+"</h3>\n" + 
                	  	 			
                	  	 		"	                    </div>\n" + 
                	  	 		"		                    <div class=\"panel-body\"> \n" + 
                	  	 		"		                    <div class=\"col-md-12\">\n" + 
                	  	 		"		                    <div class=\"form-group\">\n" + 
                	  	 		"		                    <label class=\"col-sm-2 control-label\">Question "+qcounter+"</label>\n" + 
                	  	 		"		                    <div class=\"col-sm-10\">\n" + 
                	  	 		"		                      <h5>"+question.getQuestionText()+"</h5>  \n" + 
                	  	 		"		                     </div>\n" + 
                	  	 		"              </div>";
                    	 out.println(questionText);
                     	 
                    	 //display question option
                    	  for(int y = 0;  y <question.getOptionList().size(); y++){
                    	 String options ="  <div class=\"form-group \">\n" + 
                 	  	 		"                    <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">"+optionLabel[y]+"</label>\n" + 
                	  	 		"                    <div class=\"col-sm-10 checkbox\"><input type=\"checkbox\" name=\"question-"+qcounter+"\" class=\"options \" value=\""+question.getOptionList().get(y)+"\">\n" + 
                	  	 		"                  <h5 > "+question.getOptionList().get(y)+"</h5> \n" + 
                	  	 		"                    </div>\n" + 
                	  	 		"                    </div>  ";
                    	 
                    	  }
                    	 
                  		
                    	   String end =	"  </div>\n" + 
                   	  	 		"                     </div>\n" + 
                	  	 		"\n" + 
                	  	 		"              </div>\n" + 
                	  	 		"\n" + 
                	  	 		"              </div>";
                    		
               	    	  		out.println(end);
                    	 
                     }
                     qcounter++;
                     
             }
                  
                  String submitButton="<button type=\"submit\" class=\"btn btn-default \">Submit</button>";
                  out.println(submitButton);
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
    <script src="js/Createquestion.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

    