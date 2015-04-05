<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Upcoming Message</title>  		 
	 <fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<!--<link rel="stylesheet" href="assets/css/flipclock.css">-->
	
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
			List<UserDetailsDTO> userDetailsList = (List<UserDetailsDTO>)request.getAttribute("userDetailsList");
			List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requests");
			String SessionDate = (String)request.getAttribute("SessionDate");
    		pageContext.setAttribute("userRequestDetails", userRequestDetails);
			pageContext.setAttribute("userDetailsList", userDetailsList);

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
            	<h1 class="page-header">Upcoming Sessions</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
			
                    <c:choose>
                      <c:when test="${userDetailsList.size() > 0 && userRequestDetails.size() > 0}">
                         <c:forEach items="${userDetailsList}" var="user">		  	
                            <c:forEach items="${userRequestDetails}" var="request">
                              <c:if test="${user.getUserId() == request.getUserId()}">
                                
                                <!-- Blog Post Row -->
                                <div class="grey-panel">
                                <div class="row">
                          
                                    <div class="col-md-9">
                                    <div class="user-detail">
                                    
                                        <h1><c:out value="SESSION WITH ${fn:toUpperCase(user.getFullName())}"/></h1>
                                        <c:if test="${request.getService().equals('careertalk')}">
                                            <h3 class="text-career">Career Talk</h3>
                                        </c:if>
                                        <c:if test="${request.getService().equals('mockinterview')}">
                                            <h3 class="text-Mock">Mock Interview</h3>
                                        </c:if>
                                        <c:if test="${request.getService().equals('cvcritique')}">
                                            <h3 class="text-resume">Resume Critique</h3>
                                        </c:if>
                                        <c:if test="${request.getService().equals('personalworkshops')}">
                                            <h3 class="text-personal">Service : Personal Workshop</h3>
                                        </c:if>
                                         <dl>
                                        	  <dt>Accepted Date :</dt>
                                            <dd><c:out value="${request.getAcceptedDate()}"/></dd>
                                        </dl>
                                        <!--<p><c:out value="${request.getQuery()}"/></p>-->
                                        
                                        <c:url value="/AdvisorUpcomingSessionViewDetails" var="myURL">
                                        	<c:param name="rId" value="${request.getRequestId()}"/>
                                        </c:url>
                                        <c:if test="${request.getService().equals('careertalk')}">
                                         <a class="btn btn-career" href="${myURL}">View Details</a>
	                                    </c:if>
	                                    <c:if test="${request.getService().equals('mockinterview')}">
	                                          <a class="btn btn-mock" href="${myURL}">View Details</a>
	                                    </c:if>
	                                    <c:if test="${request.getService().equals('cvcritique')}">
	                                         <a class="btn btn-resume" href="${myURL}">View Details</a>
	                                    </c:if>
	                                    <c:if test="${request.getService().equals('personalworkshops')}">
	                                         <a class="btn btn-personal" href="${myURL}">View Details</a>
	                                    </c:if>
                                        
                                    </div>
                                    </div>
                                    
                                    <div class="col-md-3 text-center time-sesion">
                                        <p>Time Left For Session</p>
                                        <div class="time">
                                        	<div class="tm days"><span><c:out value="${request.getDays()}"/></span> <p>Days</p></div>
                                            <div class="tm hours"><span><c:out value="${request.getHours()}"/></span> <p>Hours</p></div>
                                            <div class="tm min"><span><c:out value="${request.getMinutes()}"/></span> <p>Minutes</p></div>
                                        </div>
                                     </div>
                                     
                                </div>
                                </div>
                                
                                </c:if>
                                <!-- /.row -->
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value="YOU HAVE NO UPCOMING SESSIONS"></c:out>
                    </c:otherwise>
                </c:choose>
		
	    		</div>
            </div><!-- /right area -->
            
		</div><!-- /row -->

     </div>
    <!-- /#wrapper -->
    <%@include file="/Footer.jsp" %>
	</div>

    <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	
	<script src="assets/js/flipclock.js"></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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
	<script type="text/javascript">
var _urq = _urq || [];
_urq.push(['initSite', '8571f59c-9c67-4ac9-a169-0eb6aa49f203']);
(function() {
var ur = document.createElement('script'); ur.type = 'text/javascript'; ur.async = true;
ur.src = ('https:' == document.location.protocol ? 'https://cdn.userreport.com/userreport.js' : 'http://cdn.userreport.com/userreport.js');
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ur, s);
})();
</script> 
</body>

</html>
