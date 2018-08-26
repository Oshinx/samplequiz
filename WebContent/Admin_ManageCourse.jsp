<%@page import="com.rewritingmole.model.Course"%>
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


    <title>Admin Area | Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="css/manage | courses.css" rel="stylesheet">

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
      System.out.print(valid + "  is workin");
     
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
            <li ><a href="./AdminDataLoader?path=dashboard">Dashboard</a></li>
            <li class="active"><a href="./AdminDataLoader?path=ManageCourses">Manage Courses</a></li>
            <li><a href="./AdminDataLoader?path=RegStuAdmin">New Student Register</a></li>
            <li><a href="ChangePassword.jsp">Change Password</a></li>
            <li><a href="./AdminDataLoader?path=CreInstr">Create Instructor</a></li>
          </ul>
          
           <ul class="nav navbar-nav navbar-right">
            <li class="#"><a href="#">Welcome,${username}</a></li>
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
            <h1><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Create A New Course <small> Manage New Course Creation</small></h1>
          </div>
          <div class="col-md-2">
           
          </div>
       </div>
     </header>

     <section id ="breadcrumb">
       <div class="container">
        <ol class="breadcrumb">
          <li class="active"> Manage Course</li>
        </ol>
       </div>
     </section>
   

      <section id="main">
        <div class="container">
          <div class="row">
             <div class="col-md-3">
               <div class="list-group">
                      <a href="./AdminDataLoader?path=dashboard" class="list-group-item "><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 
                        Dashboard
                      </a>
                      <a href="./AdminDataLoader?path=ManageCourses" class="list-group-item active  main-color-bg"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Manage Courses </a>
                      <a href="./AdminDataLoader?path=RegStuAdmin" class="list-group-item"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>   Register New Student </a>
                      <a href="ChangePassword.jsp" class="list-group-item"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Change Password </a>
                      <a href="./AdminDataLoader?path=CreInstr" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Create Instructor </a>
                    </div>

                   

          </div>
            <div class="col-md-9">
               <div class="panel panel-default">
                    <div class="panel-heading main-color-bg ">
                      <h3 class="panel-title main-color-bg">Create New Course</h3>
                    </div>
                    <div class="panel-body">
                      <div class="col-md-7 col-md-offset-2">
                        
                        
                        
                      <% 
                          
                      if(session.getAttribute("success") != null){
                    	  System.out.println(session.getAttribute("success") != null+"success");
                          String success = session.getAttribute("success").toString(); 
                          out.println(success); 
                          }
                      else if(session.getAttribute("error") != null){
                    	  System.out.println(session.getAttribute("error") != null +"error" );
                          String error = session.getAttribute("error").toString();
                           out.println(error);   
                        }
                       else{
                    	  
                        }
                 
             		 
             		    if(session.getAttribute("success") != null){
             			 session.removeAttribute("success");
             			
                         
                          }
                      else if(session.getAttribute("error") != null){
                     	 session.removeAttribute("error");
           
                        }
                          %>
                       <form action="CreateCourse" method="post">
                            <div class="form-group">
                              <label for="coursename">Enter Course Name</label>
                              <input type="text" name ="coursename" id="coursename"class="form-control"  placeholder=" Enter Course Name" required>
                            </div>
                            <div class="form-group">
                              <label> Direction </label>
                              <select  name ="direction" class ="form-control" placeholder="Add Message" >
                              <option value = "rtl"> Right to Left</option>
                              <option value = "ltr">Left  to Right </option>
                              </select>  
                            </div>
                            
                        
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                        
                        
                       
                      <!--End of Website Overview-->
                 
                 </div>

             </div>
           </div>
           
           
                     <div class="panel panel-default">
                      <div class="panel-heading main-color-bg">
                      <h3 class="panel-title main-color-bg">Latest Users</h3>
                      </div>
                      <div class="panel-body">
                      <table class="table table-striped table-hover "> 
        <thead>
         <tr>
          <th>#</th> 
          <th>Course Name</th>
             </tr> </thead>
              <tbody>
                
               <%
               if(session.getAttribute("listOfCourses") != null){
               List<Course> course = (List) session.getAttribute("listOfCourses");
               int count = 0;
               for(Course c : course){
            	   count += 1;
              out.println(" <tr>");   
        	  out.println("  <th >"+count+"</th>");
        	  out.println("<th>"+c.getCourseName()+"</th>");
        	  out.println(" <td><a class=\"btn btn-danger\" href=\"./DeleteCourse?id="+c.getCourseId()+"\">Delete</a></td> ");
        	  out.println(" </tr>");
               }}
        
          %> 

                      </tbody>
                       </table>
                      </div>
                 </div>
        </div>





      </section>


      

 <div style=" height: 200px;"></div>

   <footer id="footer">
             <p>Copyright Quizoli &copy; 2017</p>
           </footer>




     

        
       

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script> 
      CKEDITOR.replace('editor1');
  </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="./admin/createnewcourse.js"></script>
  </body>
</html>
