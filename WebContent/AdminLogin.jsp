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
    <style type="text/css"> 
		.form-control{width:100% !important;}
		body{background-color:#fff !important}
		@font-face {
			font-family:"custom_light";
			src: url("assets/fonts/NGCDMC__.TTF") /* EOT file for IE */
		}
		
		@font-face {
			font-family:"custom_bold";
			src: url("assets/fonts/NGCDBC__.TTF") /* TTF file for CSS3 browsers */
		}

		h1,h2,h3,h5{
			font-family:'custom_bold' !important;
			}
			
		h4{ font-family:'custom_light' !important;
			font-size:19px !important;
			}	
		 p{font-family:'custom_light' !important;}
		 div{font-family:'custom_light' !important;}
		.fa-lg{font-size:1.4em !important;}
		.social{padding-bottom:9px !important;}
		.foot-size{font-size:14px !important;}
		.foot-color{color:#717171;}
		
		@media only screen and (max-width:700px){
		
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		}
		 </style>  		 
	<style type="text/css"> 
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		@media only screen and (max-width:700px){
		.font_size{font-size:22px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		 </style>  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

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
	<div style="margin-left:5.9%;margin-right:5.9%">
		<%@include file="/Header.jsp" %>
	</div>
	<div class="container"> 
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
     	<div style="margin-left:5.9%;margin-right:5.9%">
			<%@include file="/Footer.jsp" %>
		</div>
     <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
</body>
</html>