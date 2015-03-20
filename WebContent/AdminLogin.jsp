<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/favicon.png">
    <title>Admin Login</title>
      		   		 
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	<link href="assets/css/sb-admin-2.css" rel="stylesheet">
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	    <% 
	    Boolean isInvalidUsername= (Boolean)request.getAttribute("isInvalidUsername");
		%>
  
  </head>
<body>	
	<div class="container">
	<%@include file="/Header.jsp" %>
    
    <div class="row">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                     <div class="panel-heading" style="background-color:#66CCCC">
                        <div class="panel-title" style="font-size:26px">Admin Sign In</div>
                    </div> 
     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" action="AdminLogin"  method="post">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="email" class="form-control" name="email" placeholder="Email">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="Password">
                                    </div>
                              <div>
                              	<c:if test="${isLoginFailed == true}">
                              		<c:out value="Login Invalid"></c:out>
                              	</c:if>
                              </div>      
								<div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                    <button id="btn-signup" type="submit" class="btn btn-info" style="background-color:#66CCCC">Login</button>
                                    </div>
                                </div>

                   
					
                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid #66CCCC; padding-top:15px; font-size:100%" >
                                        </div>
                                    </div>
                                </div>    
                            </form>
                         
					</div>  

                    </div>  
        	</div>
    </div>
    <!-- /#wrapper -->

	<%@include file="/Footer.jsp" %>
	</div>
    <!-- /#container -->
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>