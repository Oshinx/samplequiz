<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


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
  if(cookies != null){
	  for(Cookie cookie: cookies){
	      valid = cookie.getName().equalsIgnoreCase("suid");
	   
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
            <li><a href="StudentDataLoader?path=login">Dashboard</a></li>
            <li ><a href="StudentDataLoader?path=quiz">Quizzes </a></li>
            <li><a href="./StudentDataLoader?path=result">Grades </a></li>
          <!--   <li  class="active"><a href="post.html">FeedBack</a></li> -->
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
            <h1><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Feedback <small></small></h1>
          </div>
       </div>
     </header>

     <section id ="breadcrumb">
       <div class="container">
        <ol class="breadcrumb">
          <li class="active">Feedback </li>
        </ol>
       </div>
     </section>


      <section id="main">
        <div class="container">
          <div class="row">
             <div class="col-md-3">
               <div class="list-group">
                      <a href="StudentDataLoader?path=login" class="list-group-item  "><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 
                        Dashboard
                      </a>
                      <a href="StudentDataLoader?path=quiz" class="list-group-item "><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Quizzes</a>
                      <a href="post.html" class="list-group-item"><span class="glyphicon glyphicon-check" aria-hidden="true"></span> Grades </a>
                      <a href="user.html" class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Feedback </a>
                    </div>

                   

          </div>
            <div class="col-md-9">
               <div class="panel panel-default">
                    <div class="panel-heading main-color-bg">
                      <h3 class="panel-title main-color-bg">Feedback</h3>
                    </div>
                    <div class="panel-body">
                     
                        <div class="well dash-box ">
                          <div class="col-md-3">
                           <h4><p class="text-left">Course Name</p></h4>
                         </div>
                         
                          <a href="" class="btn btn-primary"> View Feedback</a>
                        </div>
                     
                      <!--End of Website Overview-->
                    </div>
                 </div>


               
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
