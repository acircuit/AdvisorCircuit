<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.AdvisorNewDatesDTO"%>
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

    <title>Request View Details</title>  		 

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
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
    	
	<%	      			String advisorname = (String)request.getAttribute("advisorname");
						String userName = (String)request.getAttribute("userName");
						String image = (String)request.getAttribute("image");
						String uId = (String)request.getAttribute("uId");
						String rId =request.getParameter("rId");
						List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("list");
						List<SessionDTO> sessionDetails = (List<SessionDTO>)request.getAttribute("sessionDetails");
						List<AdvisorNewDatesDTO> newDate = (List<AdvisorNewDatesDTO>)request.getAttribute("newDate");
						
						pageContext.setAttribute("sessionDetails", sessionDetails);
						pageContext.setAttribute("newDate", newDate);
						pageContext.setAttribute("userRequestDetails", userRequestDetails);
						pageContext.setAttribute("image", image);
						String login = (String)session.getAttribute("isLogin");
						Boolean isAdvisor = false;
						if(login != null && login.equals("advisor")){
							isAdvisor = true;
						}
	%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
        
    	<div class="row">
        
        	<div class="col-md-3">
				<%@ include file="j-sidebar_user.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
                
            	<c:forEach items="${userRequestDetails}" var="userRequest">
        			<div id="page-wrapper" class="grey-panel">
                    
                    	<c:if test="${userRequest.getService().equals('careertalk')}">
                            <h1 class="page-header text-career">Career Talk</h1>
                        </c:if>
                        <c:if test="${userRequest.getService().equals('mockinterview')}">
                            <h1 class="page-header text-Mock">Mock Interview</h1>
                        </c:if>
                        <c:if test="${userRequest.getService().equals('cvcritique')}">
                            <h1 class="page-header text-resume">Resume Critique</h1>
                        </c:if>
                        <c:if test="${userRequest.getService().equals('personalworkshops')}">
                            <h1 class="page-header text-personal">Personal Workshop</h1>
                        </c:if>
                        
                        <h3 class="ses-detail"> Session Details</h3>
                        
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" action="UserPayment" method="post">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                
                                <input type="hidden" value="<%=uId%>" name="uId">	
                                <input type="hidden" value="<%=rId%>" name="rId">
                                
								<div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">Advisor :</label>
                                    <c:url value="AdvisorProfile" var="advisorId">
                                        <c:param name="aId" value="${userRequest.getAdvisorId()}"/>
                                    </c:url>
                                    <div class="col-md-10">
                                        <a href="${advisorId}">
                                        	<img style="width:100px; float:left;" id="userimage" src="<%=image %>" alt="32321">
                                        	<h2 class="name"><%=advisorname.toUpperCase()%></h2>
                                        </a>
                                    </div>
								</div>
								
                                <c:if test="${sessionDetails.size() > 0}">
											<c:forEach items="${sessionDetails}" var="session">
											<c:set var="sessionId" value="${session.getSessionId()}"></c:set>
											<input type="hidden" value="${session.getSessionId()}" name="sessionId">
												
												<div class="form-group">
													<label for="icode" class="col-md-2 control-label">Session Plan :</label>
													<div class="col-md-10">
														<textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
													</div>
												</div>
												<input type="hidden" value="${session.getAcceptedDate() }" name="acceptedDate">
												<c:if test="${session.getAcceptedDate() != null && !session.getAcceptedDate().equals('')}">
													<div class="form-group">
													<label for="icode" class="col-md-2 control-label">Accepted Date:</label>
														<div class="col-md-10">	
															<p class="form-control"><c:out value="${session.getAcceptedDateString()}"></c:out></p>
														</div>
													</div>		
												</c:if>
											</c:forEach>
										</c:if>	
                                        
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">Query :</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" rows="3" readonly><c:out value="${userRequest.getQuery()}"/></textarea>
                                    </div>
                                </div>
										  
                                <div class="form-group">
                                	<label for="icode" class="col-md-2 control-label">Mode:</label>
                                    <div class="col-md-10">	
                                      <c:if test="${userRequest.getMode().equals('phone')}">
                                		<img width="50" src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" alt="phone">	
                               	</c:if>
                                    <c:if test="${userRequest.getMode().equals('email')}">
                                    	<img width="50" src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" alt="email">	
                                    </c:if>
                                    <c:if test="${userRequest.getMode().equals('webchat')}">
                                    	<img width="50" src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" alt="webchat">	
                                    </c:if>                                    </div>
                                </div>				
										 
                                <div class="form-group">
                                	<label for="icode" class="col-md-2 control-label">Duration (Hrs):</label>
                                    <div class="col-md-10">
                                        <p><c:out value="${userRequest.getDuration()}"></c:out></p>	
                                    </div>
                                </div>
                                
                                <c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
                                    <div class="form-group">
                                        <label for="icode" class="col-md-2 control-label"></label>
                                        <div class="col-md-10">
                                            <h4 class="text-center"><a href="DownloadFile?rid=<%=rId %>">YOUR RESUME</a></h4>
                                        </div>
                                    </div>
                                </c:if>	
                                        
								<div style="height:10px"></div>
                                
								<c:choose>
											<c:when test="${userRequest.getMode().equals('email')}">
												<h3>Date and Time slots  submitted By You:</h3>
												<hr>
												<div class="form-group">
												<div class="col-md-6" >
													  <ol>
													  		<li>
																<div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString1()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
			                                            </ol>
												  </div>	
												</div>
											</c:when>
											<c:otherwise>
												<h3>Date and Time slots  submitted :</h3>
												<hr>
												
												<div class="form-group">
															
												  <div class="col-md-6" >
													  <ol>
													  		<li>
																<div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString1()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
				                                            <li>
					                                            <div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString2()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
				                                            <li>
					                                            <div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString3()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
				                                            <li>
					                                            <div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString4()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
				                                            <li>
					                                            <div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString5()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
				                                            <li>
					                                            <div class="radio">
					                                                <label>
					                                                    <c:out value="${userRequest.getTimeString6()}"></c:out>
					                                                </label>
					                                            </div>
				                                            </li>
			                                            </ol>
												  </div>	
												</div>
											</c:otherwise>
										
										</c:choose>
                                        
								<c:if test="${newDate.size() > 0 && acceptedDate == null}">
											<c:choose>
												<c:when test="${ userRequest.getMode().equals('email')}">
													<c:forEach items="${newDate}" var="date">
													<h4>Date and Time slots submitted By Advisor.</h4>
													<div class="form-group">
														<div class="col-md-6" >
															<div class="radio">
			                                                <label>
			                                                    <input type="radio" name="optionsRadios" id="optionsRadiosemail" value="${date.getNewDate1()}" checked="checked" ><c:out value="${date.getNewDateString1()}"></c:out>
			                                                </label>
				                                            </div>
														</div>	
													</div>
												</c:forEach>		
												</c:when>
												<c:otherwise>
													<c:forEach items="${newDate}" var="date">
													<h4>Choose from the Below Date And Time Submitted by the Advisor</h4>
													<div class="form-group">
																
														<div class="col-md-6" >
																<div class="radio">
			                                                <label>
			                                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="${date.getNewDate1()}" ><c:out value="${date.getNewDateString1()}"></c:out>
			                                                </label>
				                                            </div>
				                                            <div class="radio">
				                                                <label>
				                                                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="${date.getNewDate2()}"><c:out value="${date.getNewDateString2()}"></c:out>
				                                                </label>
				                                            </div>
				                                            <div class="radio">
				                                                <label>
				                                                    <input type="radio" name="optionsRadios" id="optionsRadios3" value="${date.getNewDate3()}"><c:out value="${date.getNewDateString3()}"></c:out>
				                                                </label>
				                                            </div>
														</div>	
													</div>
												</c:forEach>			
												</c:otherwise>
											</c:choose>
										</c:if>	
                                        
                                <input type="hidden" value="${userRequest.getRequestId()}" name="rId" >	
                                <input type="hidden" value="${sessionId}" name="sessionId" >						
								
                                <hr>
                                
                                <c:if test="${userRequest.getStatus().equals('REQUEST ACCEPTED BY ADVISOR')|| userRequest.getStatus().equals('REQUEST ACCEPTED BY ADVISOR WITH NEW DATES')}">							
                                    <div class="form-group">
                                        <!-- Button -->                    
                                        <div class="row">
                                            <div class="col-md-12 text-center">
                                               <button id="btn" type="submit" class="btn btn-info">Confirm Session</button>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </c:if>
                                
                                <div class="form-group">
                                    <c:url value="UserRequestViewDetailForm" var="cancel">
                                          <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                          <c:param name="cancel" value="true"/>
                                          <c:if test="${sessionId != null}">
                                            <c:param name="sessionId" value="${sessionId}"/>
                                          </c:if>
                                      </c:url>
                                      <div class="row">
                                        <div class="col-md-12 text-center">
                                            <a href="${cancel}" class="btn btn-info">Cancel Request</a>
                                        </div>
                                    </div>
                                </div>
                                
						</form>
						</div> 
                 
					</div>	
				</c:forEach>
            </div><!-- /right area -->
        	
        </div><!-- /row -->
        
    </div>
    <!-- /#wrapper -->
    
    <%@include file="/Footer.jsp" %>
	</div>

    <!-- jQuery Version 1.11.0 -->
    <script src="assets/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">
    	function setImageSrc(){
    	var img1=document.getElementById('userimage');
    	var val = document.getElementById('imagesource').value;
    	img1.src=val;
    	}
      $('#datetimepicker1').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
	  $('#datetimepicker2').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
	  $('#datetimepicker3').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
	   $('#datetimepicker4').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
	  $('#datetimepicker5').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
	  $('#datetimepicker6').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
  $(document).ready(function() {
		  $("#btn").click(function(event){
			  	  if($("#optionsRadios1").is(':visible') && $("#optionsRadios2").is(':visible') && $("#optionsRadios3").is(':visible') && $("#optionsRadios1").is(':checked') == false && $("#optionsRadios2").is(':checked') == false && $("#optionsRadios3").is(':checked') == false ){
				  alert("Please select a date and time");
				  event.preventDefault();
			  }
		  });
	  });
    </script>
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
