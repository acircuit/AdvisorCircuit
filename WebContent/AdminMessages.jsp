<!DOCTYPE html>
<%@page import="org.AC.dto.SessionDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>Users</title>  		 
	
    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	
		<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">
	<!--<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">-->
	<!-- Custom styles for this template -->
    
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	
    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		
	<%
			List<SessionDTO> sessionList = (List<SessionDTO>)request.getAttribute("sessionList");
			pageContext.setAttribute("sessionList", sessionList);
	%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-9">
            	<h4></h4>
            	<h1 class="page-header">Messages And Files</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
                    <div class="table-responsive">
                        <table style="text-align:center" class="table table-bordered">
                        <tr><th style="text-align:center">SESSION_ID</th><th style="text-align:center">REQUEST_ID</th><th style="text-align:center">STATUS</th><th style="text-align:center">VIEW Message/Files</th></tr>
                        <c:forEach items="${sessionList}" var="session">
                                <tr>
                                <c:url value="SessionViewDetails" var="myURL">
                                        <c:param name="rId" value="${session.getRequestId()}"/>
                                </c:url>
                                <td><a href="${myURL}" target="_blank">${session.getSessionId()}</a></td><td><p>${session.getRequestId()}</p></td><td><p>${session.getStatus()}</p></td>
                                <td>
                                <c:url value="AdminUserAdvisorMessages" var="myURL">
                                <c:param name="sessionid" value="${session.getSessionId()}"/>
                                </c:url>
                                <p><a href="${myURL}" target="_blank">VIEW MESSAGES/FILES</a></p>														
                                </td>
                                </tr>
                        </c:forEach>
                        </table> 
                    </div>
        		</div>
        
                <div style="margin-bottom: 80px">
                    <ul class="pagination" style="float: right;">
                        <li id="PageList"></li>
                    </ul>
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
	<script src="assets/js/bootstrap-slider.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
    <script src="assets/js/Pagination.js"></script>
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