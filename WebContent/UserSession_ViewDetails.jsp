<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@page import="org.AC.dto.SessionFeedBackDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Session View Details</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<link href="assets/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="assets/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/main.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="assets/css/sb-admin-2.css" rel="stylesheet">
<!--<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">-->
<!-- Custom styles for this template -->

<!-- Fonts from Google Fonts -->
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900'
	rel='stylesheet' type='text/css'>

<!-- Custom Fonts -->
<link href="assets/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<%
	Boolean isFeedBackRejected = true;
		Boolean isEmailRejected = true;
		String advisorname = (String)request.getAttribute("advisorname");
		String userName = (String)request.getAttribute("userName");
		String image = (String)request.getAttribute("image");
		String uId = (String)request.getAttribute("uId");
		String time = (String)request.getAttribute("time");
		String date = (String)request.getAttribute("date");
		String path = (String)request.getAttribute("path");
		String details = (String)request.getAttribute("details");
		SessionFeedBackDTO feedbackUser = (SessionFeedBackDTO)request.getAttribute("feedUser");
		SessionFeedBackDTO feedAdvisor = (SessionFeedBackDTO)request.getAttribute("feedAdvisor");
		SessionFeedBackDTO emailUser = (SessionFeedBackDTO)request.getAttribute("emailUser");
		SessionFeedBackDTO emailAdvisor = (SessionFeedBackDTO)request.getAttribute("emailAdvisor");
		Boolean fromCancelledSession = false;
		Boolean fromPreviousSession = false;
		Boolean visible = false;
		String sessionDate = (String)request.getAttribute("sessionDate");
		List<UserRequestDTO> requests = (List<UserRequestDTO>)request.getAttribute("requests");
		List<SessionDTO> sessions = (List<SessionDTO>)request.getAttribute("sessions");
		List<UserDetailsDTO> userdetails = (List<UserDetailsDTO>)request.getAttribute("userdetails");
		fromCancelledSession = (Boolean)request.getAttribute("isFromCancelledSession");
		fromPreviousSession = (Boolean)request.getAttribute("isFromPreviousSession");
		visible = (Boolean)request.getAttribute("visible");
		pageContext.setAttribute("requests", requests);
		pageContext.setAttribute("sessions", sessions);
		pageContext.setAttribute("userdetails", userdetails);
		pageContext.setAttribute("advisorname", advisorname);
		pageContext.setAttribute("userName", userName);
		pageContext.setAttribute("image", image);
		pageContext.setAttribute("time", time);
		pageContext.setAttribute("feedbackUser", feedbackUser);
		pageContext.setAttribute("feedAdvisor", feedAdvisor);
		pageContext.setAttribute("emailUser", emailUser);
		pageContext.setAttribute("emailAdvisor", emailAdvisor);
		pageContext.setAttribute("date", date);
		pageContext.setAttribute("details", details);
		pageContext.setAttribute("fromCancelledSession", fromCancelledSession);
		pageContext.setAttribute("fromPreviousSession", fromPreviousSession);
		pageContext.setAttribute("visible", visible);
		if(feedbackUser != null){
	isFeedBackRejected = feedbackUser.getUserApproval();
		}
		if(emailUser != null){
	isEmailRejected = emailUser.getUserApproval();
		}
%>
</head>

<body>

	<div class="container">
		<%@include file="/Header.jsp"%>

		<div id="wrapper" class="content">

			<div class="row">

				<div class="col-md-3">
					<%@ include file="j-sidebar_user.jsp"%>
				</div>
				<!-- /sidebar -->

				<div class="col-md-9">
					<c:forEach items="${requests}" var="request">
						<div id="page-wrapper" class="grey-panel">

							<c:if test="${request.getService().equals('careertalk')}">
								<h1 class="page-header text-career">Career Talk</h1>
							</c:if>
							<c:if test="${request.getService().equals('mockinterview')}">
								<h1 class="page-header text-Mock">Mock Interview</h1>
							</c:if>
							<c:if test="${request.getService().equals('cvcritique')}">
								<h1 class="page-header text-resume">Resume Critique</h1>
							</c:if>
							<c:if test="${request.getService().equals('personalworkshops')}">
								<h1 class="page-header text-personal">Personal Workshop</h1>
							</c:if>

							<h3 class="ses-detail">Session Details</h3>

							<div class="panel-body">
								<form id="signupform" class="form-horizontal" role="form"
									action="AdvisorMyAccountSessionViewDetailsFormController">

									<div id="signupalert" style="display: none"
										class="alert alert-danger">
										<p>Error:</p>
										<span></span>
									</div>

									<div class="form-group">
										<label for="icode" class="col-md-2 control-label">Advisor</label>
										<div class="col-md-10">
											<c:url value="AdvisorProfile" var="advisorId">
												<c:param name="aId" value="${request.getAdvisorId()}" />
											</c:url>
											<a href="${advisorId}"> <img
												style="width: 100px; float: left;" id="userimage"
												src="<%=image%>" alt="32321">
												<h2 class="name"><%=advisorname.toUpperCase()%></h2>
											</a>
										</div>
									</div>

									<h4>SCHEDULED FOR</h4>
									<hr>

									<div class="form-group">
										<div class="col-lg-12">
											<table class="table table-bordered table-hover">
												<tr>
													<th>Time
													</td>
													<td>:</td>
													<td><%=time%></td>
												</tr>
												<tr>
													<th>Date
													</td>
													<td>:</td>
													<td><%=date%></td>
												</tr>
												<c:if
													test="${!fromCancelledSession && !fromPreviousSession }">
													<tr>
														<th>Duration</th>
														<td>:</td>
														<td><c:out value="${request.getDuration()}"></c:out>
															Hour</td>
													</tr>
													<tr>
														<th>Countdown
														</td>
														<td>:</td>
														<td><c:out value="${request.getDays()}" /> Day <c:out
																value="${request.getHours()}" /> Hour <c:out
																value="${request.getMinutes()}" /> Minutes To Go</td>
													</tr>
												</c:if>
											</table>
										</div>
									</div>
									<c:set var="rId" value="${request.getRequestId()}"></c:set>
									<c:if
										test="${request.getService().equals('mockinterview') || request.getService().equals('cvcritique')}">
										<h4>
											<a href="DownloadFile?rid=${rId}">CLICK TO DOWNLOAD CV
												UPLOADED BY YOU</a>
										</h4>
									</c:if>
									<hr>


									<c:if test="<%=path != null && path.length()>5%>">
										<div class="form-group">
											<label for="icode" class="col-md-2 control-label"></label>
											<c:if test="${request.getService().equals('cvcritique')}">
												<div class="col-md-10">
													<h4>
														<a href="DownloadFile?path=<%=path%>">Resume Critique
															Feedback Form </a>
													</h4>
												</div>
											</c:if>
											<c:if test="${request.getService().equals('mockinterview')}">
												<div class="col-md-10">
													<h4>
														<a href="DownloadFile?path=<%=path%>">Mock Interview
															Feedback Form </a>
													</h4>
												</div>
											</c:if>
										</div>
									</c:if>

									<div class="form-group">
										<label for="icode" class="col-md-2 control-label">Mode
										</label> <label for="icode" class="col-md-3 control-label"
											style="text-align: left"><c:out
												value="${fn:toUpperCase(request.getMode())}" /></label>
										<c:if
											test="${!fromPreviousSession && !fromCancelledSession && request.getMode().equals('phone') && details != null && !details.equals('')}">
											<label for="icode" class="col-md-3 control-label"
												style="text-align: left"><a data-toggle="modal"
												data-target="#modephone">View Details</a></label>
										</c:if>
										<c:if
											test="${!fromPreviousSession && !fromCancelledSession && request.getMode().equals('webchat') && details != null && !details.equals('')}">
											<label for="icode" class="col-md-3 control-label"
												style="text-align: left"><a data-toggle="modal"
												data-target="#modewebchat">View Details</a></label>
										</c:if>
									</div>
									<fmt:bundle basename="Resources.Dependency" prefix="path.">
										<div class="modal fade" id="modephone" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">x</button>
														<h4 class="modal-title" id="myModalLabel">How to
															connect via phone</h4>
													</div>
													<div class="modal-body">
														<ol>
															<li>Please dial a number suitable to you from the
																list.<a href=<fmt:message key="enterprise_list"/>>DownLoad
																	List</a>
															</li>
															<li>Once you dial, enter this participant PIN - <%=details%>
																to join the call
															</li>
														</ol>
													</div>
												</div>
											</div>
										</div>
									</fmt:bundle>
									<div class="modal fade" id="modewebchat" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">x</button>
													<h4 class="modal-title" id="myModalLabel">How to
														connect via webchat</h4>
												</div>
												<div class="modal-body">
													<ol>
														<li>Please sign into your gmail account through which
															you can use hangouts</li>
														<li>After you are signed in, click/ copy paste this
															link : <%=details%> in your browser to join the video
															call
														</li>
													</ol>
												</div>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="icode" class="col-md-2 control-label">Session
											Query</label>
										<div class="col-md-10">
											<textarea class="form-control" rows="3" readonly="readonly"><c:out
													value="${request.getQuery()}" /></textarea>
										</div>
									</div>

									<c:forEach items="${sessions}" var="session">
										<div class="form-group">
											<label for="icode" class="col-md-2 control-label">Session
												Id </label> <label for="icode" class="col-md-10 control-label"
												style="text-align: left"><c:out
													value="${session.getSessionId()}" /></label>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-2 control-label">Session
												Plan</label>
											<div class="col-md-10">
												<textarea class="form-control" rows="3" readonly="readonly"><c:out
														value="${session.getSessionPlan()}" /></textarea>
											</div>
										</div>
										<input type="hidden" id="sId"
											value="${session.getSessionId()}" name="sId">
									</c:forEach>

									<div style="height: 10px"></div>

									<c:if test="${(!fromCancelledSession)}">
										<div class="text-center">
											<c:choose>
												<c:when test="${fromPreviousSession}">
													<a data-toggle="modal" data-target="#messageadvisor" onclick="getmessages()" class="btn btn-career margin-10">View  Chat</a>
												</c:when>
												<c:otherwise>
													<a data-toggle="modal" data-target="#messageadvisor" onclick="getmessages()" class="btn btn-career margin-10">Chat with the Advisor</a>
												</c:otherwise>
											</c:choose>

											<a data-toggle="modal" data-target="#uploadfile" onclick="getFiles()" class="btn btn-career margin-10">Upload Files</a>
										</div>
									</c:if>
									<c:if
										test="${request.getMode().equals('email') && !fromCancelledSession || fromPreviousSession && request.getMode().equals('email') }">
										<a class="btn btn-info margin-10" data-toggle="modal"
											data-target="#email" onclick="">Send Your Mail</a>
									</c:if>

									<c:if
										test="${(!fromCancelledSession && visible) || fromPreviousSession}">
										<a class="btn btn-info margin-10" data-toggle="modal"
											data-target="#feedback" onclick="">Follow Up Mail</a>
									</c:if>

								</form>











								<div class="modal fade" id="messageadvisor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content" style="background-color: #E5E5E5">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
												<h4 class="modal-title" id="myModalLabel">Chat with the Advisor</h4>
											</div>
											<div class="modal-body">
												<div id="page-wrapper1">
													<!-- /.row -->
													<div class="row">
														<div class="chat-panel panel panel-default">
															<div class="panel-heading">
																<i class="fa fa-comments fa-fw"></i> Message Hisory
															</div>
															<!-- /.panel-heading -->

															<div class="panel-body">
																<ul id="message" class='chat'></ul>
															</div>
															<c:if test="${(!fromPreviousSession)}">
																<div class="panel-footer col-xs-12">
																	<form class="form-inline">
																		  <div class="form-group col-xs-11">
																				<input id="usermessage" onkeypress="checkkey(event)" type="text" class="form-control" style="width: 100%;" name="usermessage" placeholder="Type your message here..." maxlength="350" />
																				<span class="glyphicon glyphicon-paperclip" aria-hidden="true" style="cursor: pointer; float: right;margin-left: 93%;position: absolute;margin-top: 1.5%;" title="Attach File" onclick="initFileSelectionDialogue()"></span>
																		  </div>
																		  <div class="form-group col-xs-1">
	  																			<input type="button" id="send" onclick="setmessage()" class="btn btn-warning btn-sm" id="btn-chat" value="Send">
																		  </div>
																		
																	<!-- /.panel-body -->
																		<!-- <div class="input-group"> 
																			<input id="usermessage" onkeypress="checkkey(event)" type="text" class="form-control input-sm" name="usermessage" placeholder="Type your message here..." maxlength="350" />
																			<span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span>
																		</div> 
																		<span class="input-group-btn">
																			<input type="button" id="send" onclick="setmessage()" class="btn btn-warning btn-sm" id="btn-chat" value="Send">
																		</span> -->
																	</form>
																	
																	<input type="file" id="uploadFileViaMsgModal" style="display: none;" name="file"/>
																	
																</div>
															</c:if>
															<!-- /.panel-footer -->
														</div>
														<!-- /.panel .chat-panel -->
													</div>
													<!-- /.row -->
												</div>


											</div>
										</div>
									</div>
								</div>
								
								
								<script type="text/javascript">
								
									function initFileSelectionDialogue(){
										$('#uploadFileViaMsgModal').click();
									}
									
									$('#uploadFileViaMsgModal').change(function () {
										
										if($("#uploadFileViaMsgModal").val()){
											if($("#uploadFileViaMsgModal").val().trim().length > 0){
												var confirmUpload = confirm("Are you sure you want to upload attached file ?");		
												
												if(confirmUpload){
													uploadFileViaAjax();
												}else{
													$("#uploadFileViaMsgModal").val("");
												}											
											}
										}
									});
									
									
									function uploadFileViaAjax() {
										var file = document.getElementById("uploadFileViaMsgModal");
										if (file.value == "") {
											alert("Please upload a valid file");
										} else if (document.getElementById('uploadFileViaMsgModal').files[0] && $('#uploadFileViaMsgModal')[0].files[0].size > 2621440) {
											alert("Please upload a file less than 2.5 MB");
										}else{
											var formData = new FormData();
											formData.append("myFile", document.getElementById("uploadFileViaMsgModal").files[0]);
											formData.append("sId", $("#sId").val());
											formData.append("fromUser", true);
											formData.append("purpose", "File uploaded via msg modal.");

											var xhr = new XMLHttpRequest();
											xhr.open("POST", "SessionFiles");
											xhr.send(formData);
											xhr.onreadystatechange = function() {
												if (xhr.readyState == 4 && xhr.status == 200) {
													console.log("file uploaded successfully.")
												//	document.getElementById("response").innerHTML = xhr.responseText;
												//	getFiles();
												}
											}
										}
									}
								</script>
								
								
								
								
								<div class="modal fade" id="uploadfile" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">x</button>
												<h4 class="modal-title" id="myModalLabel">
													Files <span style="color: #c84c4e">(After pressing submit, please don't close the window till you see the message - 'Your File has been uploaded')</span>
												</h4>
											</div>
											<div class="modal-body">
												<form id="uploadform" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

													<div id="part2">
														<h4>Uploaded Files :</h4>
														<div id="uploadedfiles"></div>
														<hr>
														<c:if test="${(!fromPreviousSession)}">
															<div class="form-group">
																<label for="icode" class="col-md-2 control-label">Upload Files</label>
																<div id="fileupload" class="col-md-10">
																	<input type="file" id="uploadFile" name="file">
																	<p class="help-block"></p>
																</div>
															</div>
															<div class="form-group">
																<label for="icode" class="col-md-2 control-label">Purpose</label>
																<div id="filepurpose" class="col-md-10">
																	<textarea rows="6" cols="50" id="purpose" name="purpose"></textarea>
																	<p class="help-block"></p>
																</div>
															</div>
															<div id="response"></div>
															<hr>
															<div class="form-group">
																<!-- Button -->
																<div class="col-md-offset-2 col-md-10">
																	<input id="btn-signup" type="button"
																		onclick="setfile()" class="btn btn-info"
																		value="Submit">
																</div>
															</div>
														</c:if>
													</div>
													<!--part2-->
												</form>
											</div>
										</div>
									</div>
								</div>
								
								
								
								
								<div class="modal fade" id="feedback" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">x</button>
												<h4 class="modal-title" id="myModalLabel">Follow Up
													Mail</h4>
											</div>
											<div class="modal-body">
												<form id="sendfeedback" class="form-horizontal" role="form"
													enctype="multipart/form-data" method="post">
													<c:choose>
														<c:when test="${feedbackUser.getId() != 0 }">
															<div id="part2">
																<h4>
																	Your FeedBack : <span style="color: #c84c4e">(After
																		pressing send, please don't close the window till you
																		see the message - 'Your Feedback has been sent')</span>
																</h4>
																<hr>
																<c:choose>
																	<c:when
																		test="<%=!isFeedBackRejected &&  (fromPreviousSession == null || !fromPreviousSession)%>">
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Subject</label>
																			<div id="feedbacksubject" class="col-md-9">
																				<input type="text" id="subject" name="subject"
																					style="width: 80%"
																					value="${feedbackUser.getUserSubject()}">
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Body</label>
																			<div id="feedbackbody" class="col-md-9"
																				style="width: 100px">
																				<textarea rows="15" cols="90" id="body" name="body">${feedbackUser.getUserBody()}</textarea>
																			</div>
																		</div>
																		<c:if
																			test="${!feedbackUser.getUserFile().equals('') && feedbackUser.getUserFile() != null }">
																			<div class="form-group">
																				<label for="icode" class="col-md-3 control-label"></label>
																				<div class="col-md-9">
																					<h4 style="float: right;">
																						<a
																							href="DownloadFile?path=${feedbackUser.getUserFile()}">CLICK
																							TO DOWNLOAD FILE</a>
																					</h4>
																				</div>
																			</div>
																		</c:if>
																		<div class="form-group text-left">
																			<label for="icode" class="col-md-2 control-label">Attach
																				File</label>
																			<div id="fileattach" class="col-md-10">
																				<input type="file" id="attachFile" name="file">
																				<p class="help-block">Max Size 2.5MB</p>
																			</div>
																		</div>
																		<div id="responsefeedback"></div>
																		<div class="form-group">
																			<!-- Button -->
																			<div class="col-md-offset-2 col-md-10">
																				<input id="btn-signup" type="button"
																					onclick="sendfeedbackagain()" class="btn btn-info"
																					value="Send">
																			</div>
																		</div>

																	</c:when>
																	<c:otherwise>
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Subject</label>
																			<div id="feedbacksubject" class="col-md-9">
																				<input type="text" id="subject" name="subject"
																					style="width: 80%"
																					value="${feedbackUser.getUserSubject()}"
																					readonly="readonly">
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Body</label>
																			<div id="feedbackbody" class="col-md-9"
																				style="width: 100px">
																				<textarea rows="15" cols="90" id="body" name="body"
																					readonly="readonly">${feedbackUser.getUserBody()}</textarea>
																			</div>
																		</div>
																		<c:if
																			test="${!feedbackUser.getUserFile().equals('') && feedbackUser.getUserFile() != null }">
																			<div class="form-group">
																				<label for="icode" class="col-md-3 control-label"></label>
																				<div class="col-md-9">
																					<h4 style="float: right;">
																						<a
																							href="DownloadFile?path=${feedbackUser.getUserFile()}">CLICK
																							TO DOWNLOAD FILE</a>
																					</h4>
																				</div>
																			</div>
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</div>
															<!--part2-->
														</c:when>
														<c:otherwise>
															<div id="part2">
																<h4>
																	Your FeedBack : <span style="color: #c84c4e">(After
																		pressing send, please don't close the window till you
																		see the message - 'Your Feedback has been sent')</span>
																</h4>
																<hr>
																<div class="form-group text-left">
																	<label for="icode" class="col-md-2 control-label">Subject</label>
																	<div id="feedbacksubject" class="col-md-10">
																		<input type="text" id="subject" name="subject"
																			style="width: 100%" maxlength="350">
																	</div>
																</div>
																<div class="form-group text-left">
																	<label for="icode" class="col-md-2 control-label">Body</label>
																	<div id="feedbackbody" class="col-md-10">
																		<textarea rows="10" id="body" name="body"
																			style="width: 100%" maxlength="3000"></textarea>
																	</div>
																</div>
																<div class="form-group text-left">
																	<label for="icode" class="col-md-2 control-label">Attach
																		File</label>
																	<div id="fileattach" class="col-md-10">
																		<input type="file" id="attachFile" name="file">
																		<p class="help-block">Max Size 2.5MB</p>
																	</div>
																</div>
																<div id="responsefeedback"></div>
																<hr>
																<c:if test="${!fromPreviousSession}">
																	<div class="form-group">
																		<!-- Button -->
																		<div class="col-md-offset-3 col-md-9">
																			<input id="btn-feed" type="button"
																				onclick="sendfeedback()" class="btn btn-info"
																				value="Send">
																		</div>
																	</div>
																</c:if>
															</div>
															<!--part2-->
														</c:otherwise>
													</c:choose>
													<c:if test="${feedAdvisor.getId() != 0 }">
														<hr>
														<div id="part2">
															<h4>Advisor FeedBack :</h4>
															<hr>
															<div class="form-group">
																<label for="icode" class="col-md-3 control-label">Subject</label>
																<div id="feedbacksubject" class="col-md-9">
																	<input type="text" id="subject" name="subject"
																		readonly="readonly" style="width: 80%"
																		value="${feedAdvisor.getAdvisorSubject()}">
																</div>
															</div>
															<div class="form-group">
																<label for="icode" class="col-md-3 control-label">Body</label>
																<div id="feedbackbody" class="col-md-9"
																	style="width: 100px">
																	<textarea rows="15" cols="90" id="body"
																		readonly="readonly" name="body">${feedAdvisor.getAdvisorBody()}</textarea>
																</div>
															</div>
															<c:if
																test="${!feedAdvisor.getAdvisorFile().equals('') && feedAdvisor.getAdvisorFile() != null}">
																<div class="form-group">
																	<label for="icode" class="col-md-3 control-label"></label>
																	<div class="col-md-9">
																		<h4 style="float: right;">
																			<a
																				href="DownloadFile?path=${feedAdvisor.getAdvisorFile()}">CLICK
																				TO DOWNLOAD FILE</a>
																		</h4>
																	</div>
																</div>
															</c:if>
															<div id="responsefeedbackadvisor"></div>
															<hr>
														</div>
													</c:if>
												</form>
											</div>
										</div>
									</div>
								</div>
								
								<div class="modal fade" id="email" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">x</button>
												<h4 class="modal-title" id="myModalLabel">Email</h4>
											</div>
											<div class="modal-body">
												<form id="sendemail" class="form-horizontal" role="form"
													enctype="multipart/form-data" method="post">
													<c:choose>
														<c:when
															test="${emailUser != null && emailUser.getId() != 0 }">
															<div id="part2">
																<h4>
																	Your Mail : <span style="color: #c84c4e">(After
																		pressing send, please don't close the window till you
																		see the message - 'Your mail has been sent')</span>
																</h4>
																<hr>
																<c:choose>
																	<c:when
																		test="<%=isEmailRejected != null && !isEmailRejected &&  (fromPreviousSession == null || !fromPreviousSession)%>">
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Subject</label>
																			<div id="emailsubject" class="col-md-9">
																				<input type="text" id="subjectemail" name="subject"
																					style="width: 80%"
																					value="${emailUser.getUserSubject()}"
																					maxlength="350">
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Body</label>
																			<div id="emailbody" class="col-md-9"
																				style="width: 100px">
																				<textarea rows="15" cols="90" id="bodyemail"
																					name="body" maxlength="3000">${emailUser.getUserBody()}</textarea>
																			</div>
																		</div>
																		<c:if
																			test="${!emailUser.getUserFile().equals('') && emailUser.getUserFile() != null }">
																			<div class="form-group">
																				<label for="icode" class="col-md-3 control-label"></label>
																				<div class="col-md-9">
																					<h4 style="float: right;">
																						<a
																							href="DownloadFile?path=${emailUser.getUserFile()}">CLICK
																							TO DOWNLOAD FILE</a>
																					</h4>
																				</div>
																			</div>
																		</c:if>
																		<div class="form-group text-left">
																			<label for="icode" class="col-md-2 control-label">Attach
																				File</label>
																			<div id="fileattach" class="col-md-10">
																				<input type="file" id="attachFileemail" name="file">
																				<p class="help-block">Max Size 2.5MB</p>
																			</div>
																		</div>
																		<div id="responseemail"></div>
																		<div class="form-group">
																			<!-- Button -->
																			<div class="col-md-offset-2 col-md-10">
																				<input id="btn-signup" type="button"
																					onclick="resendemailagain()" class="btn btn-info"
																					value="Send">
																			</div>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Subject</label>
																			<div id="emailsubject" class="col-md-9">
																				<input type="text" id="subjectemail"
																					readonly="readonly" name="subject"
																					style="width: 80%"
																					value="${emailUser.getUserSubject()}">
																			</div>
																		</div>
																		<div class="form-group">
																			<label for="icode" class="col-md-3 control-label">Body</label>
																			<div id="emailbody" class="col-md-9"
																				style="width: 100px">
																				<textarea rows="15" cols="90" id="bodyemail"
																					readonly="readonly" name="body">${emailUser.getUserBody()}</textarea>
																			</div>
																		</div>
																		<c:if
																			test="${!emailUser.getUserFile().equals('') && emailUser.getUserFile() != null }">
																			<div class="form-group">
																				<label for="icode" class="col-md-3 control-label"></label>
																				<div class="col-md-9">
																					<h4 style="float: right;">
																						<a
																							href="DownloadFile?path=${emailUser.getUserFile()}">CLICK
																							TO DOWNLOAD FILE</a>
																					</h4>
																				</div>
																			</div>
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</div>
															<!--part2-->
														</c:when>
														<c:otherwise>
															<div id="part2">
																<h4>
																	Your Mail : <span style="color: #c84c4e">(After
																		pressing send, please don't close the window till you
																		see the message - 'Your mail has been sent')</span>
																</h4>
																<hr>
																<div class="form-group text-left">
																	<label for="icode" class="col-md-2 control-label">Subject</label>
																	<div id="emailsubject" class="col-md-10">
																		<input type="text" id="subjectemail" name="subject"
																			style="width: 100%" maxlength="350">
																	</div>
																</div>
																<div class="form-group text-left">
																	<label for="icode" class="col-md-2 control-label">Body</label>
																	<div id="emailbody" class="col-md-10">
																		<textarea rows="10" id="bodyemail" name="body"
																			style="width: 100%" maxlength="3000"></textarea>
																	</div>
																</div>
																<div class="form-group text-left">
																	<label for="icode" class="col-md-2 control-label">Attach
																		File</label>
																	<div id="fileattach" class="col-md-10">
																		<input type="file" id="attachFileemail" name="file">
																		<p class="help-block">Max Size 2.5MB</p>
																	</div>
																</div>
																<div id="responseemail"></div>
																<hr>
																<c:if test="${!fromPreviousSession}">
																	<div class="form-group">
																		<!-- Button -->
																		<div class="col-md-offset-3 col-md-9">
																			<input id="btn-feed" type="button"
																				onclick="sendemail()" class="btn btn-info"
																				value="Send">
																		</div>
																	</div>
																</c:if>
															</div>
															<!--part2-->
														</c:otherwise>
													</c:choose>
													<c:if
														test="${emailAdvisor != null && emailAdvisor.getId() != 0 }">
														<hr>
														<div id="part2">
															<h4>Advisor Mail :</h4>
															<hr>
															<div class="form-group">
																<label for="icode" class="col-md-3 control-label">Subject</label>
																<div id="feedbacksubject" class="col-md-9">
																	<input type="text" id="subject" name="subject"
																		readonly="readonly" style="width: 80%"
																		value="${emailAdvisor.getAdvisorSubject()}">
																</div>
															</div>
															<div class="form-group">
																<label for="icode" class="col-md-3 control-label">Body</label>
																<div id="feedbackbody" class="col-md-9"
																	style="width: 100px">
																	<textarea rows="15" cols="90" id="body"
																		readonly="readonly" name="body">${emailAdvisor.getAdvisorBody()}</textarea>
																</div>
															</div>
															<c:if
																test="${!emailAdvisor.getAdvisorFile().equals('') && emailAdvisor.getAdvisorFile() != null}">
																<div class="form-group">
																	<label for="icode" class="col-md-3 control-label"></label>
																	<div class="col-md-9">
																		<h4 style="float: right;">
																			<a
																				href="DownloadFile?path=${emailAdvisor.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a>
																		</h4>
																	</div>
																</div>
															</c:if>
															<div id="responsefeedbackadvisor"></div>
															<hr>
														</div>
													</c:if>
												</form>
											</div>
										</div>
									</div>
								</div>

							</div>

						</div>
					</c:forEach>
				</div>
				<!-- /right area -->

			</div>
			<!-- /row -->

		</div>
		<!-- /#wrapper -->

		<%@include file="/Footer.jsp"%>
	</div>
	<!-- /#container -->

	<!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="assets/js/sb-admin-2.js"></script>
	<script type="text/javascript">
		function checkkey(e) {
			if (!e)
				e = e || window.event;
			if (e.keyCode == '13') {
				e.preventDefault();
				setmessage();
			}
		}

		
		function getmessages() {
			$.ajax({
				url : 'SessionMessages', // Your Servlet mapping or JSP(not suggested)
				data : {
					"sId" : $("#sId").val(),
					"user" : true
				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					$('#message').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		
		
		
		function setmessage() {
			var input = document.getElementById("usermessage");
			if ($("#usermessage").val() != "") {
				var val = input.value;
				input.value = "";
				$.ajax({
					url : 'SessionMessages', // Your Servlet mapping or JSP(not suggested)
					data : {
						"sId" : $("#sId").val(),
						"message" : val,
						"advisor" : false
					},
					type : 'POST',
					dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
					success : function(response) {
						flag = response;
					},
					error : function(request, textStatus, errorThrown) {
						flag = false;
						alert(errorThrown);
					}
				});
				getmessages();
			} else {
				alert("Please enter a message");
			}
		}
		function getFiles() {
			$.ajax({
				url : 'SessionFiles', // Your Servlet mapping or JSP(not suggested)
				data : {
					"sId" : $("#sId").val(),
					"getFile" : true,
					"advisor" : false
				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					$('#uploadedfiles').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		function setfile() {
			var setFile = true;
			var input1 = document.getElementById("uploadFile");
			if (input1.value == "") {
				setFile = false;
				alert("Please upload a file");
			} else if (document.getElementById('uploadFile').files[0] && $('#uploadFile')[0].files[0].size > 2621440) {
				alert("Please upload a file less than 2.5 MB");
				setFile = false;
			}
			if (setFile) {
				var formData = new FormData();
				formData.append("myFile", document.getElementById("uploadFile").files[0]);
				formData.append("sId", $("#sId").val());
				formData.append("purpose", $("#purpose").val());
				formData.append("fromUser", true);
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "SessionFiles");
				xhr.send(formData);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						document.getElementById("response").innerHTML = xhr.responseText;
						getFiles();
					}
				}
			}
			var input = document.getElementById("purpose");
			input.value = "";
			var input1 = document.getElementById("uploadFile");
			input1.value = "";
		}
		function sendfeedback() {
			var isError = false;
			if ($("#subject").val() == "") {
				alert("Please Enter Subject");
				event.preventDefault();
				isError = true
			} else if ($("#body").val() == "") {
				alert("Please Enter Body");
				event.preventDefault();
				isError = true;
			} else if (document.getElementById('attachFile').files[0]
					&& $('#attachFile')[0].files[0].size > 2621440) {
				alert("Please upload a file less than 2.5 MB");
				event.preventDefault();
				isError = true;
			}
			if (!isError) {
				var formData = new FormData();
				formData.append("myFile",
						document.getElementById("attachFile").files[0]);
				formData.append("sId", $("#sId").val());
				formData.append("body", $("#body").val());
				formData.append("subject", $("#subject").val());
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "SessionFeedBack");
				xhr.send(formData);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						document.getElementById("responsefeedback").innerHTML = xhr.responseText;
						getFiles();
					}
				}
				var input = document.getElementById("body");
				input.value = "";
				var input1 = document.getElementById("attachFile");
				input1.value = "";
				var input2 = document.getElementById("subject");
				input2.value = "";
			}
		}
		function sendfeedbackagain() {
			var isError = false;
			if ($("#subject").val() == "") {
				alert("Please Enter Subject");
				event.preventDefault();
				isError = true
			} else if ($("#body").val() == "") {
				alert("Please Enter Body");
				event.preventDefault();
				isError = true;
			} else if (document.getElementById('attachFile').files[0]
					&& $('#attachFile')[0].files[0].size > 2621440) {
				alert("Please upload a file less than 2.5 MB");
				event.preventDefault();
				isError = true;
			}
			if (!isError) {
				var formData = new FormData();
				formData.append("myFile",
						document.getElementById("attachFile").files[0]);
				formData.append("sId", $("#sId").val());
				formData.append("body", $("#body").val());
				formData.append("subject", $("#subject").val());
				formData.append("again", "true");
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "SessionFeedBack");
				xhr.send(formData);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						document.getElementById("responsefeedback").innerHTML = xhr.responseText;
						getFiles();
					}
				}
				var input = document.getElementById("body");
				input.value = "";
				var input1 = document.getElementById("attachFile");
				input1.value = "";
				var input2 = document.getElementById("subject");
				input2.value = "";
			}
		}
		function sendemail() {
			var isError = false;
			if ($("#subjectemail").val() == "") {
				alert("Please Enter Subject");
				event.preventDefault();
				isError = true
			} else if ($("#bodyemail").val() == "") {
				alert("Please Enter Body");
				event.preventDefault();
				isError = true;
			} else if (document.getElementById('attachFileemail').files[0]
					&& $('#attachFileemail')[0].files[0].size > 2621440) {
				alert("Please upload a file less than 2.5 MB");
				event.preventDefault();
				isError = true;
			}
			if (!isError) {
				var formData = new FormData();
				formData.append("myFile", document
						.getElementById("attachFileemail").files[0]);
				formData.append("sId", $("#sId").val());
				formData.append("body", $("#bodyemail").val());
				formData.append("subject", $("#subjectemail").val());
				formData.append("email", "user");
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "SessionFeedBack");
				xhr.send(formData);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						document.getElementById("responseemail").innerHTML = xhr.responseText;
						getFiles();
					}
				}
				var input = document.getElementById("bodyemail");
				input.value = "";
				var input1 = document.getElementById("attachFileemail");
				input1.value = "";
				var input2 = document.getElementById("subjectemail");
				input2.value = "";
			}
		}
		function resendemailagain() {
			debugger;
			var isError = false;
			if ($("#subjectemail").val() == "") {
				alert("Please Enter Subject");
				event.preventDefault();
				isError = true
			} else if ($("#bodyemail").val() == "") {
				alert("Please Enter Body");
				event.preventDefault();
				isError = true;
			} else if (document.getElementById('attachFileemail').files[0]
					&& $('#attachFileemail')[0].files[0].size > 2621440) {
				alert("Please upload a file less than 2.5 MB");
				event.preventDefault();
				isError = true;
			}
			if (!isError) {
				var formData = new FormData();
				formData.append("myFile", document
						.getElementById("attachFileemail").files[0]);
				formData.append("sId", $("#sId").val());
				formData.append("body", $("#bodyemail").val());
				formData.append("subject", $("#subjectemail").val());
				formData.append("email", "user");
				formData.append("again", "true");
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "SessionFeedBack");
				xhr.send(formData);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						document.getElementById("responseemail").innerHTML = xhr.responseText;
						getFiles();
					}
				}
				var input = document.getElementById("bodyemail");
				input.value = "";
				var input1 = document.getElementById("attachFileemail");
				input1.value = "";
				var input2 = document.getElementById("subjectemail");
				input2.value = "";
			}
		}
		
		
	</script>
	<!-- Page-Level Demo Scripts - Notifications - Use for reference -->
	<script type="text/javascript">
		// tooltip demo
		$('.tooltip-demo').tooltip({
			selector : "[data-toggle=tooltip]",
			container : "body"
		})

		// popover demo
		$("[data-toggle=popover]").popover()
		var _urq = _urq || [];
		_urq.push([ 'initSite', '8571f59c-9c67-4ac9-a169-0eb6aa49f203' ]);
		(function() {
			var ur = document.createElement('script');
			ur.type = 'text/javascript';
			ur.async = true;
			ur.src = ('https:' == document.location.protocol ? 'https://cdn.userreport.com/userreport.js'
					: 'http://cdn.userreport.com/userreport.js');
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ur, s);
		})();
	</script>
</body>

</html>
