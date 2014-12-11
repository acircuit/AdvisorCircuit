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
    <title>Advisor Registration</title>
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

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	<% int uId =  (Integer)request.getAttribute("uId");
	%>
</head>
<body>
		`<div id="PDF" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
              <div class="panel panel-info">
  						
                   <div class="panel-body" >
                        			                           				                                      
                               <div class="col-md-offset-3 col-md-9">
                               		<a href="/Tomact_7/DeactivateUserController?uId=<%=uId %>" id="btn" class="btn btn-info">Deactivate</a>
                               </div>
                           		<span style="margin-left: 170px"><b>OR</b></span>
                               <!-- Button -->                                        
                               <div class="col-md-offset-3 col-md-9">
                               		 <a href="/Tomact_7/DeleteAdvisorController?uId=<%=uId %>" id="btn" class="btn btn-info">Delete</a>
                               </div>
                    </div>
                 </div> 
         </div> 
</body>
</html>