<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


    <title>Quizoli | Login </title>

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
        </div><!--/.nav-collapse -->
      </div>
    </nav>
     
     <!--Header-->
     <header id="header">
       <div class="container">
         <div class="row">
          <div class="col-md-10">
            <h1 class="text-center"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Account </h1>
          </div>
          <div class="col-md-2">
           
          </div>
       </div>
     </header>

   

      <section id="main">
        <div class="container">
          <div class="row">
        
            <div class="col-md-5 col-md-offset-3">
            
            <form action="Login" method="post"   class="well">
              
                      <% 
                          
                      if(session.getAttribute("loginerror") != null){
                    	  System.out.println(session.getAttribute("loginerror") != null+"success workin");
                          String loginerror = session.getAttribute("loginerror").toString(); 
                          out.println(loginerror); 
                          }
                          
                          if(session.getAttribute("loginerror") != null){
              			  session.removeAttribute("loginerror");
                           }
                          %>
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email"name="username" class="form-control" id="exampleInputEmail1" placeholder="Email" required="required">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" name="password"class="form-control" id="exampleInputPassword1" placeholder="Password" required="required">
                </div>
              <h5 "><a href="./StudentDataLoader?path=stureg">Register</a></h5>
              <h5 "><a href="#">Forgot Password ? </a></h5>
                <button type="submit" class="btn btn-primary login-btn btn-block">Submit</button>
              </form>
             
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
    