<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.AdvisorNewDatesDTO"%>
<%@page import="org.AC.dto.SessionFeedBackDTO"%>
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

    <title>Session View Details</title>
	<style type="text/css"> 
		.form-control{width:100% !important;}
		body{background-color:#fff !important}
		@font-face {
			font-family:"custom_light";
			src: url("assets/fonts/NGCDMC__.TTF") /* EOT file for IE */
		}
		
		@font-face {
			font-family:"custom_bold";
			src: url("assets/fonts/NGCDBC__.TTF") /* TTF file for CSS3 browsers */
		}

		h1,h2,h3,h5{
			font-family:'custom_bold' !important;
			}
			
		h4{ font-family:'custom_light' !important;
			font-size:19px !important;
			}	
		 p{font-family:'custom_light' !important;}
		div{font-family:'custom_light' !important;}
		.fa-lg{font-size:1.4em !important;}
		.social{padding-bottom:9px !important;}
		.foot-size{font-size:14px !important;}
		.foot-color{color:#717171;}
		.gray{color:#575758;}
		@media only screen and (max-width:700px){
		
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		}
		 </style>  		 

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
	<style type="text/css"> 
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		input[type="text"]{height:40px !important}
		.add-on{height:40px !important;cursor:pointer !important}
		.control-label{width:130px !important}
	</style>		
	<%	      			
						String advisorImage = (String)request.getAttribute("advisorImage");
						String userImage = (String)request.getAttribute("userImage");
						String userName =	(String)request.getAttribute("userName");
						String advisorname = (String)request.getAttribute("advisorName");
						String isSessionPast = (String)request.getAttribute("isSessionPast");
						String path	 = (String)request.getAttribute("path");
						SessionFeedBackDTO feedback	 = (SessionFeedBackDTO)request.getAttribute("feed");
						SessionFeedBackDTO mail	 = (SessionFeedBackDTO)request.getAttribute("mail");
						List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requestDetails");
						List<SessionDTO> sessionDetail = (List<SessionDTO>)request.getAttribute("sessionDetail");	
						List<AdvisorNewDatesDTO> advisorNewDates = (List<AdvisorNewDatesDTO>)request.getAttribute("advisorNewDates");	

						pageContext.setAttribute("userRequestDetails", userRequestDetails);
						pageContext.setAttribute("sessionDetail", sessionDetail);
						pageContext.setAttribute("feedback", feedback);
						pageContext.setAttribute("mail", mail);
	%>
</head>

<body>

    <div style="margin-left:5.9%;margin-right:5.9%">
	<%@include file="/Header.jsp" %>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class=" navbar-static-top" role="navigation" style="margin-bottom: 0">

			<div id="j_sidebar">
			<%@ include file="j-sidebar_admin.jsp" %>
			</div>
        </nav>

        <div id="page-wrapper" style="background-color:#E5E5E5">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			<div class="row">    
				<div class="col-lg-8">
					<div class="mainbox">
							<div class="panel panel-info">
								<div class="panel-heading" style="background-color:#FFFFFF">
									<div class="panel-title" style="font-size:26px"> Session Details</div>   
								</div> 
							</div> 

					<c:forEach items="${userRequestDetails}" var="userRequest">					
						
								<div class="panel-body" >
									<form id="signupform" class="form-horizontal" role="form" action="AdminRequestViewDetailForm" method="get">
										
										<div id="signupalert" style="display:none" class="alert alert-danger">
											<p>Error:</p>
											<span></span>
										</div>
										<c:url value="AdvisorProfile" var="advisorId">
   												<c:param name="aId" value="${userRequest.getAdvisorId()}"/>
										</c:url>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Advisor :</label>
											<a href="${advisorId }">
												<div class="col-md-9">
													<p class="form-control"><b><%=advisorname %></b></p>
												</div>
											</a>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Advisor Image:</label>
											<a href="${advisorId }">											
												<div class="col-md-9">
													<img style="width:60%;padding:3% 1% 1% 5%;float:right"  id="userimage"  src="<%=advisorImage %>" alt="">
												</div>
											</a>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">User :</label>
											<div class="col-md-9">
												<p class="form-control"><b><%=userName.toUpperCase() %></b></p>
											</div>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">User Image :</label>
											<div class="col-md-9">
												<img style="width:60%;padding:3% 1% 1% 5%;float:right"  id="userimage"  src="<%=userImage %>" alt="">
											</div>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Service :</label>
											<div class="col-md-9">
												<c:if test="${userRequest.getService().equals('careertalk')}">
												<p class="form-control"><b>Career Talk</b></p>
												</c:if>
												<c:if test="${userRequest.getService().equals('mockinterview')}">
												<p class="form-control"><b>Mock Interview</b></p>
												</c:if>
												<c:if test="${userRequest.getService().equals('cvcritique')}">
												<p class="form-control"><b>Resume Critique</b></p>
												</c:if>
												<c:if test="${userRequest.getService().equals('personalworkshops')}">
												<p class="form-control"><b>Personal Workshop</b></p>
												</c:if>
											</div>
										</div>
										
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Query :</label>
											<div class="col-md-9">
												<textarea class="form-control" rows="3" readonly><c:out value="${userRequest.getQuery()}"/></textarea>
											</div>
										</div>
										  
										<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Mode:</label>
												<div class="col-md-9">
													<p class="form-control"><b><c:out value="${fn:toUpperCase(userRequest.getMode())}"/></b></p>
												</div>
										</div>				
										 
										<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Duration (Hrs):</label>
												<div class="col-md-9">
													<p class="form-control"><b><c:out value="${userRequest.getDuration()}"/></b></p>	
												</div>
										</div>
										<c:choose>
											<c:when test="${userRequest.getMode().equals('phone')}">
												<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Pin:</label>
													<div class="col-md-9">
														<input class="form-control" id="details"></input>	
													</div>
												</div>
												<h4 style="float: right;"><a class="btn btn-info" onclick="SubmitModeDetails()">Submit Pin</a></h4>												
											</c:when>
											<c:when test="${userRequest.getMode().equals('webchat')}">
												<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Link:</label>
													<div class="col-md-9">
														<input class="form-control" id="details"></input>	
													</div>
												</div>
												<div class="col-md-12">
												<h4 style="float: right;"><a class="btn btn-info" onclick="SubmitModeDetails()">Submit Link</a></h4>
												</div>
												<div id = "message" style="float: right"></div>													
											</c:when>									
										</c:choose>
										<input type="hidden" name="rId" value="${userRequest.getRequestId()}">
										<c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">										
										<div class="form-group">
													<label for="icode" class="col-md-3 control-label"></label>
													<div class="col-md-12">
														<h4  style="float: right;"><a href="DownloadFile?rid=${userRequest.getRequestId()}">CLICK TO DOWNLOAD <%=userName.toUpperCase() %>'s CV</a></h4>
													</div>
											</div>
										</c:if>
										<c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
											<div class="form-group">
													<label for="icode" class="col-md-3 control-label"></label>
													<div class="col-md-9">
														<h4 style="float: right;"><a href="DownloadFile?path=<%=path%>" >${fn:toUpperCase(userRequest.getService())} FeedBack Form</a></h4>
													</div>
													<div class="col-md-12">
														<h4 style="float: right;"><a class="btn btn-info" onclick="approvefeedback()">Approve FeedBack </a></h4>
													</div>
													<div id = "message" style="float: right"></div>
											</div>
										</c:if>	
										<c:choose>
											<c:when test="${userRequest.getMode().equals('email')}">
												<h3>Date and Time Submitted:</h3>
												<hr>
												<div class="form-group">
												<div class="col-md-6" >
													  <ol>
													  		<li>
																<div class="radio">
					                                                <label>
					                                                   <c:out value="${userRequest.getTimeString1()}"></c:out>
					                                                </label>
					                                                 <input value="${userRequest.getTimeString1()}" type="hidden" name="emaildate">
					                                            </div>
				                                            </li>
			                                            </ol>
												  </div>	
												</div>
												<div style="height:10px"></div>
												<div>
												<hr>
												</div>	
											</c:when>
											<c:otherwise>
										<h3>Date and Time Submitted</h3>
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
									<c:if test="${sessionDetail.size() > 0}">
										<c:forEach var="session" items="${sessionDetail}">
											<h3>Session Details</h3>
											<input type="hidden" id="sessionId" value="${session.getSessionId()}">
											<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Session ID</label>
													<div class="col-md-9">
														<p class="form-control"><b><c:out value="${session.getSessionId()}"/></b></p>
													</div>
												</div>
												<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Session Plan</label>
													<div class="col-md-9">
														<textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
													</div>
												</div>
												<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Accepted Date</label>
													<div class="col-md-9">
														<p class="form-control"><b><c:out value="${session.getAcceptedDateString()}"/></b></p>
													</div>
												</div>
												<c:if test="${feedback != null}">
													<h4><a data-toggle="modal" data-target="#feedback"  style="float: right">Follow Up Mail</a>
												</c:if>
												<c:if test="${userRequest.getMode().equals('email') && mail != null }">
													<h4><a data-toggle="modal" data-target="#email" class="col-md-12" style="float: right">Mail</a>													
												</c:if>
												<div class="col-md-11" style="text-align: center;margin-top: 20px;margin-left: 30px">
													<a style="float: right;width: 300px" id="show" class="btn btn-info" onclick="ApproveFollowUp(this)">SHOW FOLLOW UP MAIL</a>
													<a style="float: right;width: 300px" id="hide" class="btn btn-info" onclick="ApproveFollowUp(this)">HIDE FOLLOW UP MAIL</a>
												</div>
												<div class="modal fade" id="feedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-lg">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
																<h4 class="modal-title" id="myModalLabel">FeedBack</h4>
															</div>
															<div class="modal-body">
											    					<div id="part2">
																		<h4>User FeedBack :</h4>
										                                <hr>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Subject</label>
											                                    <div id = "feedbacksubject" class="col-md-9">
																					<input type="text" id="subject" name="subject" readonly="readonly" style="width: 80%" value="${feedback.getUserSubject()}">
											                                    </div>
											                                </div>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Body</label>
											                                    <div id = "feedbackbody" class="col-md-9" style="width: 100px">
											                                    	<textarea rows="15" cols="90" id="body" readonly="readonly" name="body" >${feedback.getUserBody()}</textarea>                                   
											                                    </div>
											                                </div>
											                                <c:if test="${!feedback.getUserFile().equals('') && feedback.getUserFile().equals('')!= null}">
												                                 <div class="form-group">
																						<label for="icode" class="col-md-3 control-label"></label>
																						<div class="col-md-9">
																							<h4  style="float: right;"><a href="DownloadFile?path=${feedback.getUserFile()}">CLICK TO DOWNLOAD FILE</a></h4>
																						</div>
																				</div>
																			</c:if>
																			<div id="responsefeedbackuser">
											                                </div>											            
																			 <div class="form-group">
											                                    <!-- Button -->                                        
											                                    <div class=" col-md-9">
											                                    	<input id="btn-signup" type="button" onclick="ApproveUserFeedBack()" class="btn btn-info" value="Approve" style="float: right">
											                                    	<input id="btn-signup" type="button" onclick="RejectUserFeedBack()" class="btn btn-info" value="Reject" >
											                                    </div>
											                                </div>
																	 </div><!--part2-->	
																	<hr>																	 
																	 <div id="part2">
																		<h4>Advisor FeedBack :</h4>
										                                <hr>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Subject</label>
											                                    <div id = "feedbacksubject" class="col-md-9">
																					<input type="text" id="subject" readonly="readonly" name="subject"  style="width: 80%" value="${feedback.getAdvisorSubject()}">
											                                    </div>
											                                </div>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Body</label>
											                                    <div id = "feedbackbody" class="col-md-9" style="width: 100px">
											                                    	<textarea rows="15" cols="90" id="body" readonly="readonly" name="body" >${feedback.getAdvisorBody()}</textarea>                                   
											                                    </div>
											                                </div>
											                                <c:if test="${!feedback.getAdvisorFile().equals('') && feedback.getAdvisorFile().equals('')!= null}">
											                                 <div class="form-group">
																					<label for="icode" class="col-md-3 control-label"></label>
																					<div class="col-md-9">
																						<h4  style="float: right;"><a href="DownloadFile?path=${feedback.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a></h4>
																					</div>
																			</div>
																			</c:if>
											                                <div id="responsefeedbackadvisor">
											                                </div>
																			<hr>
																			 <div class="form-group">
											                                    <!-- Button -->                                        
											                                    <div class=" col-md-9">
											                                    	<input id="btn-signup" type="button" onclick="ApproveAdvisorFeedBack()" class="btn btn-info" value="Approve" style="float: right">
											                                    	<input id="btn-signup" type="button" onclick="RejectAdvisorFeedBack()" class="btn btn-info" value="Reject">
											                                    </div>
											                                </div>
																	</div>
															</div>
													</div>
												</div>
												</div>
												
												<div class="modal fade" id="email" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-lg">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
																<h4 class="modal-title" id="myModalLabel">Mail</h4>
															</div>
															<div class="modal-body">
											    					<div id="part2">
																		<h4>User Mail :</h4>
										                                <hr>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Subject</label>
											                                    <div id = "feedbacksubject" class="col-md-9">
																					<input type="text" id="subjectemailuser" readonly="readonly" name="subject"  style="width: 80%" value="${mail.getUserSubject()}">
											                                    </div>
											                                </div>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Body</label>
											                                    <div id = "feedbackbody" class="col-md-9" style="width: 100px">
											                                    	<textarea rows="15" cols="90" id="bodyemailuser" readonly="readonly"  name="body" >${mail.getUserBody()}</textarea>                                   
											                                    </div>
											                                </div>
											                                <c:if test="${!mail.getUserFile().equals('') && mail.getUserFile().equals('')!= null}">
												                                 <div class="form-group">
																						<label for="icode" class="col-md-3 control-label"></label>
																						<div class="col-md-9">
																							<h4  style="float: right;"><a href="DownloadFile?path=${mail.getUserFile()}">CLICK TO DOWNLOAD FILE</a></h4>
																						</div>
																				</div>
																			</c:if>
																			<div id="responseemailuser">
											                                </div>											            
																			 <div class="form-group">
											                                    <!-- Button -->                                        
											                                    <div class=" col-md-9">
											                                    	<input id="btn-signup" type="button" onclick="ApproveUserMail()" class="btn btn-info" value="Approve" style="float: right">
											                                    	<input id="btn-signup" type="button" onclick="RejectUserMail()" class="btn btn-info" value="Reject" >
											                                    </div>
											                                </div>
																	 </div><!--part2-->	
																	<hr>																	 
																	 <div id="part2">
																		<h4>Advisor Mail :</h4>
										                                <hr>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Subject</label>
											                                    <div id = "feedbacksubject" class="col-md-9">
																					<input type="text" id="subjectemailadvisor" name="subject"  style="width: 80%" value="${mail.getAdvisorSubject()}">
											                                    </div>
											                                </div>
											                                <div class="form-group">
											                                    <label for="icode" class="col-md-3 control-label">Body</label>
											                                    <div id = "feedbackbody" class="col-md-9" style="width: 100px">
											                                    	<textarea rows="15" cols="90" id="bodyemailadvisor"  name="body" >${mail.getAdvisorBody()}</textarea>                                   
											                                    </div>
											                                </div>
											                                <c:if test="${!mail.getAdvisorFile().equals('') && mail.getAdvisorFile().equals('')!= null}">
											                                 <div class="form-group">
																					<label for="icode" class="col-md-3 control-label"></label>
																					<div class="col-md-9">
																						<h4  style="float: right;"><a href="DownloadFile?path=${mail.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a></h4>
																					</div>
																			</div>
																			</c:if>
											                                <div id="responseemailadvisor">
											                                </div>
																			<hr>
																			 <div class="form-group">
											                                    <!-- Button -->                                        
											                                    <div class=" col-md-9">
											                                    	<input id="btn-signup" type="button" onclick="ApproveAdvisorMail()" class="btn btn-info" value="Approve" style="float: right">
											                                    	<input id="btn-signup" type="button" onclick="RejectAdvisorMail()" class="btn btn-info" value="Reject">
											                                    </div>
											                                </div>
																	</div>
															</div>
													</div>
												</div>
												</div>
												<div class="form-group">
													<div class="row">
														<c:choose>
															<c:when test="${isSessionPast.equals('true')}">
																<div class="col-md-11" style="text-align: center;margin-top: 20px;margin-left: 30px">
																	 <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
										   								<c:param name="rId" value="${userRequest.getRequestId()}"/>
																		<c:param name="sId" value="${session.getSessionId()}"></c:param>
																		<c:param name="advisornoshow" value="true"></c:param>
																	</c:url>
																	<a href="${myURL}" style="float: right;width: 300px" class="btn btn-info">SET SESSION CANCELLED DUE TO ADVISOR NO SHOW </a>
																</div>
																<div class="col-md-11" style="text-align: center;margin-top: 20px;margin-left: 30px">
																	 <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
										   								<c:param name="rId" value="${userRequest.getRequestId()}"/>
																		<c:param name="sId" value="${session.getSessionId()}"></c:param>
																		<c:param name="usernoshow" value="true"></c:param>
																	</c:url>
																	<a href="${myURL}" style="float: right;width: 300px" class="btn btn-info">SET SESSION CANCELLED DUE TO USER NO SHOW </a>
																</div>
																<div class="col-md-11" style="text-align: center;margin-top: 20px;margin-left: 30px">
																	 <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
										   								<c:param name="rId" value="${userRequest.getRequestId()}"/>
																		<c:param name="sId" value="${session.getSessionId()}"></c:param>
																		<c:param name="complete" value="true"></c:param>
																	</c:url>
																	<a href="${myURL}" style="float: right;width: 300px" class="btn btn-info">SET SESSION COMPLETE </a>
																</div>
															</c:when>
															<c:otherwise>
																<div class="col-md-11" style="text-align: center;margin-top: 20px;margin-left: 30px">
																	 <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
										   								<c:param name="rId" value="${userRequest.getRequestId()}"/>
																		<c:param name="sId" value="${session.getSessionId()}"></c:param>
																		<c:param name="advisorunavailabale" value="true"></c:param>
																	</c:url>
																	<a href="${myURL}" style="float: right;width: 350px" class="btn btn-info">SET SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY </a>
																</div>
																<div class="col-md-11" style="text-align: center;margin-top: 20px;margin-left: 30px">
																	 <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
										   								<c:param name="rId" value="${userRequest.getRequestId()}"/>
																		<c:param name="sId" value="${session.getSessionId()}"></c:param>
																		<c:param name="userunavailabale" value="true"></c:param>
																	</c:url>
																	<a href="${myURL}" style="float: right;width: 350px" class="btn btn-info">SET SESSION CANCELLED DUE TO USER UNAVAILABILITY </a>
																</div>
															
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</form>
								</div>
							</c:forEach>	
						 </div>
					</div> 
				 </div> 
			</div>

			
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
	<script type="text/javascript">
function approvefeedback(){
	var id = $('#sessionId').val();
	$.ajax({
	    url : 'ApproveFeedback', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :id},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#message').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function SubmitModeDetails(){
	var details = $('#details').val();
	var id = $('#sessionId').val();
	$.ajax({
	    url : 'ApproveFeedback', // Your Servlet mapping or JSP(not suggested)
	    data : {"details" :details,"sId" :id},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#message').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function ApproveUserFeedBack(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"type" :"user"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responsefeedbackuser').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function ApproveAdvisorFeedBack(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"type" :"advisor"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responsefeedbackadvisor').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function ApproveUserMail(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"target" :"user","isApprove":"true"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responseemailuser').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function ApproveAdvisorMail(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"target" :"advisor","isApprove":"true"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responseemailadvisor').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function RejectAdvisorMail(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"target" :"advisor","isApprove":"false"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responseemailadvisor').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function RejectUserMail(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"target" :"user","isApprove":"false"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responseemailuser').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function ApproveFollowUp(elem){
	var id=elem.id;
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"action" :id},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	      		 alert(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function RejectUserFeedBack(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"type" :"user","reject" :"true"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responsefeedbackuser').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
function RejectAdvisorFeedBack(){
	$.ajax({
	    url : 'SessionFeedBack', // Your Servlet mapping or JSP(not suggested)
	    data : {"sId" :$('#sessionId').val(),"type" :"advisor" ,"reject" :"true"},
	    type : 'POST',
	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	    success : function(response) {
	        $('#responsefeedbackadvisor').html(response); // create an empty div in your page with some id
	    },
	    error : function(request, textStatus, errorThrown) {
	        alert(errorThrown);
	    }
	}); 
}
</script>
</body>

</html>
