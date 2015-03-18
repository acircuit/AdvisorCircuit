<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Account</title>  		 

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
 
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%
    UserDetailsDTO user = (UserDetailsDTO)request.getAttribute("user");
    	pageContext.setAttribute("user", user);
    %>
</head>

<body>

    <div class="container">
   	<%@include file="/Header.jsp"%>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-9">
            	<h4></h4>
            	<h1 class="page-header">My Account</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_user.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
					
            		<div class="grey-panel-1" style="display:none;">
                    	<p class="notification-msg">You have 2 message</p>
                        <ul class="notification-list">
                        	<li>- Aditya Raina has replied to your session request.</li>
                            <li>- You have a new message from Lavi Aggarwal.</li>
                        </ul>
                    </div>
                    
                    <div class="grey-panel-1">
                        <div class="row">
                        	<div class="col-md-9">
                            <div class="account-info">
                            	<dl>
                                	<dt>Name</dt>
                                    <dd>${user.getFullName()}</dd>
                                </dl>
                                <dl>
                                	<dt>Email Id</dt>
                                    <dd>${user.getEmail()}</dd>
                                </dl>
                                <dl>
                                	<dt>Phone Number</dt>
                                    <dd>${user.getPhone()}</dd>
                                </dl>
                                 <dl>
                                	<dt>Occupation</dt>
                                    <dd>${user.getOccupation()}</dd>
                                </dl>
                                <dl>
                                <dt>Date of Registration</dt>
                                    <dd>${user.getDor()}</dd>
                                </dl>
                            </div>
                            </div>
                            <div class="col-md-3">
                            	<img id="" src="${user.getImage()}" alt="${user.getFullName()}" border="0" width="110">
                            </div>
                        </div>
                    </div>
            
        		</div>
            </div><!-- /right area -->
		
     	</div><!-- /row -->
        
    </div>   
    <!-- /#wrapper --> 
    <%@include file="/Footer.jsp" %>
    
	</div>
    <!-- /#container -->

    <!-- jQuery Version 1.11.0 -->
    <script src="assets/js/jquery-1.11.0.js"></script>
   
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Notifications - Use for reference -->
    <script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
    </body>

</html>
