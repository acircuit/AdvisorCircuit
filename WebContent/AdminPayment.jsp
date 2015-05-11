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
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
					
                    <div class="row">
                    	
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
                                <th>Amount Paid by Customer(Rs)</th>
                                <th>Amount Due to Advisor(Rs)</th>
                                <th>Fee</th>
                                <th>Date of Payment</th>
                                <th>Payment Mode</th>
                                <th>Tracking Id</th>
                                <th>Paid/Unpaid</th>
                                <th>User Comment</th>
                                <th>Advisor Comment</th>
                            </tr>
                            	<c:forEach var="session" items="${sessions}">
                            	  <tr>
                            		<td>${session.getSessionId()}</td>
                            		<td>${session.getAcceptedDateString()}</td>
                            		<c:forEach var="request" items="${requests}">
                            			<c:if test="${request.getRequestId() == session.getRequestId()}">
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
	                           			</c:if>
	                           		</c:forEach>
	                           			<c:forEach var="payment" items="${payments}">
	                           				<c:if test="${payment.getSessionId() == session.getSessionId()}">
	                           					<td>${payment.getAmountPayable()}</td>
	                           					<td>${payment.getFee()}</td>	                           						                           				
	                           					<td>${payment.getPurchaseDateString()}</td>
	                           					<td>${payment.getPaymentMode()}</td>
	                           					<td>${payment.getTrackingId()}</td>	
	                           					<c:choose>
	                           						<c:when test="${payment.getPaidToAdvisor()}">
	                           							<td>PAID<br><a id="${session.getSessionId()}" onclick="UpdatePaidToAdvisor(this,false)">toggle</a>
	                           							</td>	                           							
	                           						</c:when>
	                           						<c:otherwise>
	                           							<td>UNPAID<br><a id="${session.getSessionId()}" onclick="UpdatePaidToAdvisor(this,true)">toggle</a></td>
	                           						</c:otherwise>
	                           					</c:choose>                                      					
	                           				</c:if>
                            		</c:forEach>
                            		<td><a data-toggle="modal" data-target="#ucomment${session.getSessionId() }"> User's Comment</a></td>
                            		<td><a data-toggle="modal" data-target="#acomment${session.getSessionId() }">Advisor's Comment</a></td>
                            		</tr>
                            			<div class="modal fade" id="ucomment${session.getSessionId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content" style="overflow-y :hidden">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
													<h4 class="modal-title" id="myModalLabel" style="text-align: center;">Add Comment for User</h4>
												</div>
												<div class="modal-body">
                            		
												<form id="forgot_password_form" class="form-horizontal" role="form"  method="post">
					                                <div class="form-group">
					                                    <label for="icode" class="col-md-3 control-label">Comment</label>
					                                     <div class="col-md-9">
					                                     	  <textarea rows="5" cols="50" id="usercomment${session.getSessionId()}" maxlength="750">${session.getUserComment() }</textarea>
														 </div>
					                                </div>
					                                <div class="form-group">
					                                    <!-- Button -->                                        
					                                    <div class="col-md-offset-3 col-md-9">
					                                        <button id="${session.getSessionId()}" onclick="SetUserComment(this)" type="button" class="btn btn-info">Submit</button>
															<!--<button id="btn" type="submit"  class="btn btn-info">Cancel</button>	-->
					                                    </div>
					                                </div>
					                            </form>
											</div>
										</div>
			                     	</div>
                   				</div>
                   				<div class="modal fade" id="acomment${session.getSessionId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content" style="overflow-y :hidden">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
													<h4 class="modal-title" id="myModalLabel" style="text-align: center;">Add Comment for Advisor</h4>
												</div>
												<div class="modal-body">
												<form id="forgot_password_form" class="form-horizontal" role="form"  method="post">
					                                <div class="form-group">
					                                    <label for="icode" class="col-md-3 control-label">Comment</label>
					                                     <div class="col-md-9">
					                                     	  <textarea rows="5" cols="50" id="advisorcomment${session.getSessionId()}" maxlength="750">${session.getAdvisorComment() }</textarea>
														 </div>
					                                </div>
					                                <div class="form-group">
					                                    <!-- Button -->                                        
					                                    <div class="col-md-offset-3 col-md-9">
					                                        <button id="${session.getSessionId()}" onclick="SetAdvisorComment(this)" type="button" class="btn btn-info">Submit</button>
															<!--<button id="btn" type="submit"  class="btn btn-info">Cancel</button>	-->
					                                    </div>
					                                </div>
					                            </form>
											</div>
										</div>
			                     	</div>
                   				</div>
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
    <script src="assets/js/bootstrap-slider.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


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
    	function UpdatePaidToAdvisor(elem,update){
    		var sessionId = elem.id;
    		$.ajax({
    		    url : 'AdminPayment', // Your Servlet mapping or JSP(not suggested)
    		    data : {"sid" : sessionId,"update" : update},
    		    type : 'POST',
    		    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
    		    success : function(response) {
    		    	      location.reload(); // create an empty div in your page with some id
    		    },
    		    error : function(request, textStatus, errorThrown) {
    		        alert(errorThrown);
    		    }
    		}); 
    	}
    	function SetUserComment(elem){
    		var sessionId = elem.id;
    		$.ajax({
    		    url : 'AdminPayment', // Your Servlet mapping or JSP(not suggested)
    		    data : {"sid" : sessionId,"usercomment" : $("#usercomment"+sessionId).val()},
    		    type : 'POST',
    		    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
    		    success : function(response) {
    		    	      location.reload(); // create an empty div in your page with some id
    		    },
    		    error : function(request, textStatus, errorThrown) {
    		        alert(errorThrown);
    		    }
    		}); 
    	}
    	
    	function SetAdvisorComment(elem){
    		var sessionId = elem.id;
    		$.ajax({
    		    url : 'AdminPayment', // Your Servlet mapping or JSP(not suggested)
    		    data : {"sid" : sessionId,"advisorcomment" : $("#advisorcomment"+sessionId).val()},
    		    type : 'POST',
    		    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
    		    success : function(response) {
    		    	      location.reload(); // create an empty div in your page with some id
    		    },
    		    error : function(request, textStatus, errorThrown) {
    		        alert(errorThrown);
    		    }
    		}); 
    	}
    </script>
    </body>

</html>