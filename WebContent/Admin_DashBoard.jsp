<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


    <title>Admin Area | Dashboard</title>

    <!-- Bootstrap core CSS -->
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
   for(Cookie cookie: cookies){
      valid = cookie.getName().equalsIgnoreCase("auid");
       System.out.println(valid + "  is workin");
       
   }
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
            <li class="active"><a href="./AdminDataLoader?path=dashboard">Dashboard</a></li>
            <li><a href="./AdminDataLoader?path=ManageCourses">Manage Course</a></li>
            <li><a href="./AdminDataLoader?path=RegStuAdmin">New Student Register</a></li>
            <li><a href="ChangePassword.jsp">Change Password</a></li>
            <li><a href="./AdminDataLoader?path=CreInstr">Create Instructor</a></li>
          </ul>
          
           <ul class="nav navbar-nav navbar-right">
            <li class="#"><a href="index.html">Welcome, ${username}</a></li>
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
            <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Dashboard <small>Manager Quizoli</small></h1>
          </div>
          <div class="col-md-2">
           
          </div>
       </div>
     </header>

     <section id ="breadcrumb">
       <div class="container">
        <ol class="breadcrumb">
          <li class="active"> Dashboard</li>
        </ol>
       </div>
     </section>
   

      <section id="main">
        <div class="container">
          <div class="row">
             <div class="col-md-3">
               <div class="list-group">
                      <a href="./AdminDataLoader?path=dashboard" class="list-group-item active main-color-bg"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 
                        Dashboard
                      </a>
                      <a href="./AdminDataLoader?path=ManageCourses" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Manage Courses </a>
                      <a href="./AdminDataLoader?path=RegStuAdmin" class="list-group-item"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>   Register New Student </a>
                      <a href="ChangePassword.jsp" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Change Password </a>
                      <a href="./AdminDataLoader?path=CreInstr" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Create Instructor </a>
                    </div>

                   

          </div>
            <div class="col-md-9">
               <div class="panel panel-default">
                    <div class="panel-heading main-color-bg ">
                      <h3 class="panel-title main-color-bg">Users  Overview</h3>
                    </div>
                    <div class="panel-body">
                      <div class="col-md-3">
                        <div class="well dash-box">
                          <h2><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${totalstudents}</h2>
                          <h4>Students</h4>
                        </div>
                        </div>
                         <div class="col-md-3">
                        <div class="well">
                          <h2><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${totalinstructor}</h2>
                          <h4>Instructors</h4>
                        </div>
                         </div>
                        <div class="col-md-3">
                        <div class="well">
                          <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> ${totalcourses}</h2>
                          <h4>Courses</h4>
                        </div>
                         </div>
                      <!--End of Website Overview-->
                    </div>
                 </div>

             </div>
        </div>
      </section>
 <div style=" height: 200px;"></div>
   <footer id="footer">
             <p>Copyright Quizoli &copy; 2017</p>
           </footer>




  
    <script> 
      CKEDITOR.replace('editor1');
  </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
   
  </body>
</html>
    