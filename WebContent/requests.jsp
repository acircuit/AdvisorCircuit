<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
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

    <title>Requests</title>  		 

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
	<%	       List<UserRequestDTO> requests = (List<UserRequestDTO>)request.getAttribute("list1");
				List<UserDetailsDTO> userDetailsList = (List<UserDetailsDTO>)request.getAttribute("userDetailsList");
				String type = (String)request.getAttribute("type");
				String answered = request.getParameter("answered");
				String pending = request.getParameter("new");
				pageContext.setAttribute("requests", requests);
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
            	<div>
                	<h4></h4>
                </div>
            	<c:if test="<%=pending != null %>">
                    <h1 class="page-header">New Requests</h1>
                </c:if>
                <c:if test="<%=answered != null %>">
                    <h1 class="page-header">Answered Requests</h1>
                </c:if>
            </div>
        </div>
    
    	<div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
					
                    <!-- Blog Post Row -->
					<c:choose>
                    <c:when test="${userDetailsList.size() > 0 && requests.size() > 0 }">
                    <c:forEach items="${userDetailsList}" var="user">
					<c:forEach items="${requests}" var="request">
					<c:if test="${user.getUserId() == request.getUserId()}">
						 <div class="grey-panel">
                         <div class="row">
				            <div class="col-md-10">
                            	<div class="user-detail">
                                    <h1><c:out value="SESSION WITH ${fn:toUpperCase(user.getFullName())}"/></h1>
                                    <!--<p><c:out value="${request.getQuery()}"/></p>-->
                                    <dl>
                                        <dt>Booking Time :</dt>
                                        <dd><c:out value="${request.getBookingTimeString()}"/></dd>
                                    </dl>
                                    <c:url value="/AdvisorRequestViewDetail" var="myURL">
                                            <c:param name="rId" value="${request.getRequestId()}"/>
                                    </c:url>
                                    <dl>
                                        <dt>Current Status :</dt>
                                        <dd><c:out value="${request.getStatus()}"/></dd>
                                    </dl>
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
                            
                            <div class="col-md-2 text-center user-service">
				            	<c:choose>
				            	<c:when test="<%=pending != null %>">
				            		<c:if test="${request.getStatus().equals('PENDING FOR ADVISOR APPROVAL') || request.getStatus().equals('PENDING FOR ADMIN APPROVAL')  }">
						                   
				           			</c:if>
				           			<c:if test="${request.getService().equals('careertalk')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_1.png">
                                        <h4 class="text-career">Career Talk</h4>
									</c:if>
									<c:if test="${request.getService().equals('mockinterview')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_2.png">
                                        <h4 class="text-Mock">Mock Interview</h4>
									</c:if>
									<c:if test="${request.getService().equals('cvcritique')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_3.png">
                                        <h4 class="text-resume">Resume Critique</h4>
									</c:if>
									<c:if test="${request.getService().equals('personalworkshops')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_4.png">
                                        <h4 class="text-personal">Personal Workshop</h4>
									</c:if>				            	</c:when>
				            	<c:otherwise>
				            		<c:if test="${request.getService().equals('careertalk')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_1.png">
                                        <h4 class="text-career">Career Talk</h4>
									</c:if>
									<c:if test="${request.getService().equals('mockinterview')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_2.png">
                                        <h4 class="text-Mock">Mock Interview</h4>
									</c:if>
									<c:if test="${request.getService().equals('cvcritique')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_3.png">
                                        <h4 class="text-resume">Resume Critique</h4>
									</c:if>
									<c:if test="${request.getService().equals('personalworkshops')}">
										<img alt="" width="100" src="assets/img/WebMail/HomePage/Panel_2_Icon_4.png">
                                        <h4 class="text-personal">Personal Workshop</h4>
									</c:if>			  		            	
				            	</c:otherwise>			            
				            </c:choose>
				            </div>
                            
                        </div>    
				        </div>
		        	</c:if>
		        <!-- /.row -->
				</c:forEach>
			 </c:forEach>
			</c:when>
			<c:otherwise>
                 <c:if test="<%=answered != null %>">
						<c:out value="There are no answered requests for now"></c:out>			
                 </c:if>
                 <c:if test="<%=pending != null %>">
						<c:out value="There are no new requests for now"></c:out>			
                 </c:if>
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
