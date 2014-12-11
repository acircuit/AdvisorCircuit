<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
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

    <title>Upcoming Sessions</title>

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

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
    		String advisorName = (String)request.getAttribute("advisorname");
    		String userName = (String)request.getAttribute("userName");
    		String image = (String)request.getAttribute("image");
    		List<SessionDTO> sessions = (List<SessionDTO>)request.getAttribute("sessions");
    		List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requests");
    		List<UserDetailsDTO> userDetails = (List<UserDetailsDTO>)request.getAttribute("userdetails");    
    		pageContext.setAttribute("sessions", sessions);
    		pageContext.setAttribute("userRequestDetails", userRequestDetails);
    		pageContext.setAttribute("userDetails", userDetails);
    %>

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="dashboard.html"><b style="font-size:30px">Advisor </b><b style="font-size:30px;color:#000;font-family: Arial">Circuit</b></a>
            </div>
            <!-- /.navbar-header -->

            <div id="topnav">
			<%@ include file="topnav.jsp" %>            
			</div>
			<div id="j_sidebar">
				<%@ include file="j-sidebar_user.jsp" %>
			</div>
				
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Previous Sessions</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			
		<c:choose>
		  <c:when test="${userDetails.size() > 0 && userRequestDetails.size() > 0}">
		      <c:forEach items="${userDetails}" var="user">
				<c:forEach items="${userRequestDetails}" var="request">
					<!-- Blog Post Row -->
			        <div class="row" style="background-color:#f8f8f8">
			  
			            <div class="col-md-8">
			                <h3><c:out value="${user.getFullName()}"/></h3>
			                <p>Type : <b><c:out value="${request.getService()}"/></b>
			                </p>
			                <p><c:out value="${request.getQuery()}"/></p>
			                <a class="btn btn-primary" href="/Tomact_7/UserMyAccountPreviousSessionViewDetailController">View Details <i class="fa fa-angle-right"></i></a>
			            </div>
			        </div>
			        <hr>
	        		<!-- /.row -->
				</c:forEach>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:out value="YOU HAVE NO UPCOMING SESSIONS"></c:out>
		</c:otherwise>
		</c:choose>
		
            
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery Version 1.11.0 -->
    <script src="assets/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

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
