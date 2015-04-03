<!DOCTYPE html>
<%@page import="org.AC.dto.PaymentDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

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
                        <div class="col-md-12">
                            <div class="btn-group" role="group" style="margin-bottom:15px;">
                              <button type="button" class="btn btn-default">Month</button>
                              <button type="button" class="btn btn-default">Year</button>
                            </div>
                        </div>
                    	
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
                            </tr>
                            	<c:forEach var="session" items="${sessions}">
                            	  <tr>
                            		<td>${session.getSessionId()}</td>
                            		<td>${session.getAcceptedDateString()}</td>
                            		<c:forEach var="request" items="${requests}">
                            			<c:if test="${request.getRequestId() == session.getRequestId()}">
		                            		<td>${request.getService()}</td>
		                             		<td>${request.getMode()}</td>
		                           			<td>${request.getPrice()}</td>
		                           			<td>${request.getDiscount()}</td>
		                           			<td>${request.getAmount()}</td>
	                           			</c:if>
	                          		</c:forEach>
                           			<c:forEach var="payment" items="${payments}">
                           				<c:if test="${payment.getSessionId() == session.getSessionId()}">
                           					<td>${payment.getPurchaseDateString()}</td>
                           					<td>${payment.getPaymentMode()}</td>
                           					<td>${payment.getTrackingId()}</td>	                           					
                           				</c:if>
                           			</c:forEach>
                            	</tr>
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