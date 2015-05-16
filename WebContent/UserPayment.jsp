\<!DOCTYPE html>
<%@page import="org.AC.dto.PaymentDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>My Account</title>  		 

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
 
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
		List<PaymentDTO> sessions= (List<PaymentDTO>)request.getAttribute("session");
		List<PaymentDTO> requests= (List<PaymentDTO>)request.getAttribute("request");
		List<PaymentDTO> payments= (List<PaymentDTO>)request.getAttribute("payment");
		pageContext.setAttribute("sessions", sessions);
		pageContext.setAttribute("requests", requests);
		pageContext.setAttribute("payments", payments);
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
            	<h1 class="page-header">Payment</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_user.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
					
                    <div class="row">
                       <%-- <div class="col-md-12">
                            <div class="btn-group" role="group" style="margin-bottom:15px;">
                              <button type="button" class="btn btn-default">Month</button>
                              <button type="button" class="btn btn-default">Year</button>
                            </div>
                        </div>  --%>
                    	
                        <div class="col-md-12">
            				<div class="table-responsive">
                    	<table style="text-align:center" class="table table-bordered">
                        	<tr>
                            	<th>Session Id</th>
                                <th>Session Date & Time</th>
                                <th>Service Type</th>
                                <th>Service Mode</th>
                                <th>Price(Rs)</th>	
                                <th>Discount(%)</th>
                                <th>Amount Paid(Rs)</th>
                                <th>Date of Payment</th>
                                <th>Payment Mode</th>
                                <th>Tracking Id</th>
                                <th>Details</th>
                            </tr>
                            	<c:forEach var="session" items="${sessions}">
                            		<c:forEach var="request" items="${requests}">  
                            			<c:if test="${request.getRequestId() == session.getRequestId()}">                          		
                            	  <tr>
                            	  	<td>
                            	  	<c:choose>
                            	  		<c:when test="${session.getStatus().equals('WAITING FOR SESSION') }">
                            	  			<a href="UserUpcomingSessionViewDetails?rId=${request.getRequestId()}" target="blank">${session.getSessionId()}</a>
                            	  		</c:when>
                            	  		<c:when test="${session.getStatus().equals('SESSION COMPLETE') }">
                            	  			<a href="UserPreviousSessionViewDetails?rId=${request.getRequestId()}" target="blank">${session.getSessionId()}</a>
                            	  		</c:when>
                            	  		<c:otherwise>
                            	  			<a href="UserCancelledSessionViewDetails?rId=${request.getRequestId()}" target="blank">${session.getSessionId()}</a>                            	  		
                            	  		</c:otherwise>
                            	  	</c:choose>
                            		</td>
                            		<td>${session.getAcceptedDateString()}</td>
                            			
                            				<c:choose>
                            					<c:when test="${request.getService().equals('careertalk')}">
                            						<td>Career Talk</td>
                            					</c:when>
                            					<c:when test="${request.getService().equals('mockinterview')}">
                            						<td>Mock Interview</td>
                            					</c:when>
                            					<c:when test="${request.getService().equals('cvcritique')}">
                            						<td>Resume Critique</td>
                            					</c:when>
                            				</c:choose>
		                            		<td>${request.getMode()}</td>
		                           			<td>${request.getPrice()}</td>
		                           			<td>${request.getDiscount()}</td>
		                           			<td>${request.getAmount()}</td>
	                           			
                           			<c:forEach var="payment" items="${payments}">
                           				<c:if test="${payment.getSessionId() == session.getSessionId()}">
                           					<td>${payment.getPurchaseDateString()}</td>
                           					<td>${payment.getPaymentMode()}</td>
                           					<td>${payment.getTrackingId()}</td>
                           					<td><a data-toggle="modal" data-target="#ucomment${session.getSessionId() }"> View Details </a></td>
                           					<div class="modal fade" id="ucomment${session.getSessionId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content" style="overflow-y :hidden">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
															<h4 class="modal-title" id="myModalLabel" style="text-align: center;">Comment</h4>
														</div>
														<div class="modal-body">
		                            		
														<form id="forgot_password_form" class="form-horizontal" role="form"  method="post">
							                                <div class="form-group">
							                                    <label for="icode" class="col-md-3 control-label">Comment</label>
							                                     <div class="col-md-9">
							                                     	  <c:choose>
							                                     	  		<c:when test="${session.getUserComment() != null && !session.getUserComment().equals('') }">
							                                     	  			<textarea rows="5" cols="50" id="usercomment${session.getSessionId()}" maxlength="750" readonly="readonly">${session.getUserComment()}</textarea>							                                     	  		
							                                     	  		</c:when>
							                                     	  		<c:otherwise>
							                                     	  			<c:out value="No Comments "></c:out>
							                                     	  		</c:otherwise>
							                                     	  </c:choose>
																 </div>
							                                </div>
							                               
							                            </form>
													</div>
												</div>
					                     	</div>
		                   				</div>	                           					
                           				</c:if>
                           			</c:forEach>
                           			</tr>
                           		</c:if>
                         		</c:forEach>
                            	</c:forEach>
                        </table>
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
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>   
    
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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