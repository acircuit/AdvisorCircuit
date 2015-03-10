<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Previous Sessions</title>  		 

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	
		<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
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
			List<UserDetailsDTO> userDetailsList = (List<UserDetailsDTO>)request.getAttribute("userDetails");
			List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requestDetails");
			List<AdvisorProfileDTO> advisorDetails = (List<AdvisorProfileDTO>)request.getAttribute("advisorDetails");
    		pageContext.setAttribute("userRequestDetails", userRequestDetails);
			pageContext.setAttribute("userDetailsList", userDetailsList);
			pageContext.setAttribute("advisorDetails", advisorDetails);

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
            	<h1 class="page-header">Previous Sessions</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
			
		<c:choose>
		  <c:when test="${userDetailsList.size() > 0 && userRequestDetails.size() > 0 && advisorDetails.size() > 0}">
		  	<c:forEach items="${userDetailsList}" var="user">
				<c:forEach items="${userRequestDetails}" var="request">
					<c:if test="${user.getUserId() == request.getUserId()}">
						 
                         <div class="grey-panel">
                         <div class="row">
				            <div class="col-md-9">
                            <div class="user-detail">
                            
								<c:forEach items="${advisorDetails}" var="advisor">
									<c:if test="${advisor.getAdvisorId() == request.getAdvisorId()}">
									<h1><c:out value="${advisor.getName()}"/><span style="margin-left: 20px"></span>WITH <span style="margin-left: 20px"></span><c:out value="${fn:toUpperCase(user.getFullName())}"/></h1>
									</c:if>
								</c:forEach>
				                <c:if test="${request.getService().equals('careertalk')}">
									<h3 class="text-career">Career Talk</h3>
								</c:if>
								<c:if test="${request.getService().equals('mockinterview')}">
                                	<h3 class="text-Mock">Service : Mock Interview</h3>
								</c:if>
								<c:if test="${request.getService().equals('cvcritique')}">
									<h3 class="text-resume">Resume Critique</h3>
								</c:if>
								<c:if test="${request.getService().equals('personalworkshops')}">
									<h3 class="text-personal">Service : Personal Workshop</h3>
								</c:if>
                                
				                <p><c:out value="${request.getQuery()}"/></p>
                                
				                <c:url value="AdminPreviousSessionsViewDetails" var="myURL">
							   			<c:param name="rId" value="${request.getRequestId()}"/>
								</c:url>
                                
				                <p>Current Status : <b><c:out value="${request.getStatus()}"/></b></p>
                                <a class="btn btn-career" href="${myURL}">View Details <i class="fa fa-angle-right"></i></a>
                                
				            </div>
                            </div>
                            
				            <div class="col-md-3 text-center">
				            	<c:if test="${request.getService().equals('careertalk')}">
									<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_1.png">
								</c:if>
								<c:if test="${request.getService().equals('mockinterview')}">
									<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_2.png">
								</c:if>
								<c:if test="${request.getService().equals('cvcritique')}">
									<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_3.png">
								</c:if>
								<c:if test="${request.getService().equals('personalworkshops')}">
									<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_4.png">
								</c:if>
		           		 	</div>
                            
				        </div><!-- /.row -->
                        </div>

		        	</c:if>
				</c:forEach>
			 </c:forEach>
		</c:when>
		<c:otherwise>
			<c:out value="YOU HAVE NO PAST SESSIONS"></c:out>
		</c:otherwise>
		</c:choose>
		
            
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
