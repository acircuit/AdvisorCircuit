<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@page import="org.AC.dto.SessionFeedBackDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Session View Details</title>  		 

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    
	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="assets/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">
	<!--<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">-->
	<!-- Custom styles for this template -->
    
    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
    
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
	
		<%
		Boolean isRejectedFeedback = true;
		Boolean isRejectedMail = true;
		String advisorname = (String)request.getAttribute("advisorname");
		String userName = (String)request.getAttribute("userName");
		String image = (String)request.getAttribute("image");
		String uId = (String)request.getAttribute("uId");
		String time = (String)request.getAttribute("time");
		String date = (String)request.getAttribute("date");
		String path = (String)request.getAttribute("path");
		SessionFeedBackDTO feedUser = (SessionFeedBackDTO)request.getAttribute("feedUser");
		SessionFeedBackDTO feedAdvisor = (SessionFeedBackDTO)request.getAttribute("feedAdvisor");
		SessionFeedBackDTO emailUser = (SessionFeedBackDTO)request.getAttribute("emailUser");
		SessionFeedBackDTO emailAdvisor = (SessionFeedBackDTO)request.getAttribute("emailAdvisor");
		String details = (String)request.getAttribute("details");
		Boolean fromCancelledSession = false;
		Boolean fromPreviousSession = false;
		String sessionDate = (String)request.getAttribute("sessionDate");
		List<UserRequestDTO> requests = (List<UserRequestDTO>)request.getAttribute("requests");
		List<SessionDTO> sessions = (List<SessionDTO>)request.getAttribute("sessions");
		List<UserDetailsDTO> userdetails = (List<UserDetailsDTO>)request.getAttribute("userdetails");	
		fromCancelledSession = (Boolean)request.getAttribute("isFromCancelledSession");
		fromPreviousSession = (Boolean)request.getAttribute("isFromPreviousSession");
		pageContext.setAttribute("requests", requests);
		pageContext.setAttribute("sessions", sessions);
		pageContext.setAttribute("userdetails", userdetails);
		pageContext.setAttribute("advisorname", advisorname);
		pageContext.setAttribute("userName", userName);
		pageContext.setAttribute("image", image);
		pageContext.setAttribute("time", time);
		pageContext.setAttribute("date", date);
		pageContext.setAttribute("feedUser", feedUser);
		pageContext.setAttribute("feedAdvisor", feedAdvisor);
		pageContext.setAttribute("emailUser", emailUser);
		pageContext.setAttribute("emailAdvisor", emailAdvisor);
		pageContext.setAttribute("fromCancelledSession", fromCancelledSession);
		pageContext.setAttribute("fromPreviousSession", fromPreviousSession);
		pageContext.setAttribute("path", path);
		pageContext.setAttribute("details", details);
		if(feedAdvisor != null){
			isRejectedFeedback = feedAdvisor.getAdvisorApproval();
		}
		if(emailAdvisor != null){
			isRejectedMail = emailAdvisor.getAdvisorApproval();
		}
		%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    <fmt:bundle basename="Resources.Dependency" prefix="path.">     
    <div id="wrapper" class="content">
        
        <div class="row">
        
        	<div class="col-md-3">
            	<div id="j_sidebar">
					<%@ include file="j-sidebar.jsp" %>
				</div>
            </div><!-- /sidebar -->
            
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
                        <form id="signupform" class="form-horizontal" role="form" action="AdvisorMyAccountSessionViewDetailsFormController" >
                            
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            
                            <div class="form-group">
                                <label for="icode" class="col-md-2 control-label">User</label>
                                <div class="col-md-10">
                                    <img style="width:100px; float:left;" id="userimage"  src="<%=image %>" alt="32321">
                                    <h2 class="name"><%=userName.toUpperCase()%></h2>
                                </div>
                            </div>
                            
                            <h4>SCHEDULED FOR</h4>
                            <hr>	
                            
                            <div class="form-group">
                                
                                <div class="col-lg-12">
                                    <table class="table table-bordered table-hover">
                                    <tr><th>Time</td><td>:</td><td><%=time %></td></tr>
                                    <tr><th>Date</td><td>:</td><td><%=date %></td></tr>
                                    <c:if test="${!fromCancelledSession &&   !fromPreviousSession }">																																						
                                    <tr><th>Duration (Hour)</th><td>:</td><td><c:out value="${request.getDuration()}"></c:out> </td></tr>													
                                    <tr><th>Countdown</td><td>:</td><td><c:out value="${request.getDays()}"/> Day <c:out value="${request.getHours()}"/> Hour  <c:out value="${request.getMinutes()}"/> Minutes To Go</td></tr>
                                    </c:if>
                                </table>
                                </div>
                                
                            </div>
                            <hr>
                            
                            <c:set var="rId" value="${request.getRequestId()}"></c:set>
                            <c:if test="${request.getService().equals('mockinterview') || request.getService().equals('cvcritique')}">
                            	<a href="DownloadFile?rid=${rId}" class="btn btn-info">CLICK TO DOWNLOAD <%=userName.toUpperCase() %>'s CV</a>
                            </c:if>
                            
                            <c:if test="${request.getService().equals('mockinterview') || request.getService().equals('cvcritique')}">
                                <div class="form-group">
                                    <c:if test="${request.getService().equals('cvcritique')}">
                                        <div class="col-md-5">
                                            <h4><a data-toggle="modal" data-target="#servicefeedback">Resume Critique  Feedback Form</a></br>                                        
                                        </div>
                                    </c:if>
                                    <c:if test="${request.getService().equals('mockinterview')}">
                                        <div class="col-md-5">
                                            <h4><a data-toggle="modal" data-target="#servicefeedback">Mock Interview Feedback Form</a></br>
                                        </div>
                                    </c:if>
                                    <div class="col-md-1">	
                                        <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Please download the form, enter your feedback and upload the same when it is completed." data-trigger="focus">
                                        <small><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></small> </button>   
                                    </div>
                                    <div id="messagewordfile" style="float: right" class="col-md-9"></div>													
                                </div>
                            </c:if>
                            <div class="modal fade" id="servicefeedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                            <c:if test="${request.getService().equals('mockinterview')}">
                                            	<h4 class="modal-title" id="myModalLabel">Mock Interview Feedback Form <span style="color: #c84c4e">(After pressing send, please don't close the window till you see the message - 'Your Feedback has been sent')</span></h4>
                                            </c:if>
                                            <c:if test="${request.getService().equals('cvcritique')}">
                                            	<h4 class="modal-title" id="myModalLabel">Resume Critique Feedback Form <span style="color: #c84c4e">(After pressing send, please don't close the window till you see the message - 'Your Feedback has been sent')</span></h4>
                                           	</c:if>
                                        </div>
                                        <div class="modal-body">
                                        <ol>
                                        <c:if test="${request.getService().equals('mockinterview')}">
                                         	<li>
                                            <a href=<fmt:message key="mockinterview_feedback"/>>DownLoad Mock Interview Form </a>
                                         </li>
                                        </c:if>
                                        <c:if test="${request.getService().equals('cvcritique')}">
                                            	<li>
                                            <a href=<fmt:message key="cv"/>>DownLoad Resume Critique Form </a>
                                         </li>
                                        </c:if>
                                       	
                                       	<li>
                                         	<h4> Upload the File after you have finished the form</h4>
                                         	<input id="servicefiles" type="file" name="file">
                                         	
                                         	<c:if test="${request.getService().equals('mockinterview')}">
                                         		<a class="btn btn-info" id="mi" onclick="SubmitFeedBackForm(this,event)" style="margin-top:10px">Upload Finished Form</a>
                                            </c:if>
                                            <c:if test="${request.getService().equals('cvcritique')}">
                                         		<a class="btn btn-info" id="cv" onclick="SubmitFeedBackForm(this,event)" style="margin-top:10px">Upload Finished Form</a>
                                           	</c:if>
                                           	<div id="responseservice">
                                       		</div>	
                                          	</li>
 										</ol>
                                        </div>
                                    </div>
                                </div>
                            </div>	
                            	<div class="form-group">
                                <label for="icode" class="col-md-2 control-label">Mode </label>
                                <label for="icode" class="col-md-3 control-label" style="text-align:left"><c:out value="${fn:toUpperCase(request.getMode())}"/></label>
                                <c:if test="${!fromPreviousSession && !fromCancelledSession && request.getMode().equals('phone') && details != null && !details.equals('')}">
                                <label for="icode" class="col-md-3 control-label" style="text-align:left"><a data-toggle="modal" data-target="#modephone">View Mode Details</a></label>	
                                </c:if>
                                <c:if test="${!fromPreviousSession && !fromCancelledSession && request.getMode().equals('webchat') && details != null && !details.equals('')}">
                                <label for="icode" class="col-md-3 control-label" style="text-align:left"><a data-toggle="modal" data-target="#modewebchat">View Mode Details</a></label>	
                                </c:if>					
                            </div>
                            
                            	<div class="modal fade" id="modephone" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                            <h4 class="modal-title" id="myModalLabel">How to Connect via Phone</h4>
                                        </div>
                                        <div class="modal-body">
                                        <ol>
                                            <li>Please dial a number suitable to you from <a href=<fmt:message key="enterprise_list"/>>this list. </a>
                                            </li> 
                                            <li>Once you dial, enter this participant PIN - <%=details %> to join the call
                                            </li>
                                        </ol>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                           		<div class="modal fade" id="modewebchat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                            <h4 class="modal-title" id="myModalLabel">How to connect via WebChat</h4>
                                        </div>
                                        <div class="modal-body">
                                        <ol>
                                            <li>Please sign into your gmail account or the official Advisor Circuit gmail account through which you can use google hangouts</li>
                                            <li>After you are signed in, click/ copy paste this link in your browser to join the video call : <%=details %></li>
                                        </ol>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            	<div class="form-group">
                                <label for="icode" class="col-md-2 control-label">Session Query</label>
                                <div class="col-md-10">
                                    <textarea class="form-control" rows="3"  readonly="readonly"><c:out value="${request.getQuery()}"/></textarea>
                                </div>
                            </div>
                            
                            <c:forEach items="${sessions}" var="session">
                            	<input type="hidden" value="${session.getSessionId()}" id="sId">
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">Session Id </label>
                                    <label for="icode" class="col-md-10 control-label" style="text-align:left"><c:out value="${session.getSessionId()}"/></label>
                                </div>
                                	
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">Session Plan</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" rows="3" readonly="readonly"><c:out value="${session.getSessionPlan()}"/></textarea>
                                    </div>
                                </div>	
                                <input type="hidden" id="sId" value="${session.getSessionId()}"name="sId" >			
                            </c:forEach>
                            
                            <div style="height:10px"></div>
                            
                            <c:if test="${(!fromCancelledSession)}">
                            	<div class="text-right">
                                	<c:choose>
	                          		<c:when test="${fromPreviousSession}">
                                			<a data-toggle="modal" data-target="#messageuser" onclick="getmessages()" class="btn btn-career">View Chat</a>
	                          		</c:when>
	                          		<c:otherwise>
                                			<a data-toggle="modal" data-target="#messageuser" onclick="getmessages()" class="btn btn-career">Chat with the User</a>
	                          		</c:otherwise>
                               	</c:choose>
                                	<!-- <a data-toggle="modal" data-target="#uploadfile" onclick="getFiles()" class="btn btn-career">Upload Files</a> -->
                                </div>
                            </c:if>
                            <c:if test="${ fromPreviousSession && request.getMode().equals('email') || !fromCancelledSession && emailUser.getId() != 0}">
                                <a data-toggle="modal" data-target="#email" class="btn btn-info" onclick="">User's Mail</a>
                            </c:if>
                            
                            <c:if test="${ fromPreviousSession || !fromCancelledSession && feedUser.getId() != 0}">
                                <a data-toggle="modal" data-target="#feedback" class="btn btn-info" onclick="">Follow Up Mail</a>
                            </c:if>
                            
                            <hr>
                            
                            <div class="modal fade" id="feedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                    <h4 class="modal-title" id="myModalLabel">Session Feedback</h4>
                                </div>
                                <div class="modal-body">
                                    <div id="part2">
                                        <h4>User FeedBack :</h4>
                                        <hr>
                                            <div class="form-group">
                                                <label for="icode" class="col-md-2 control-label">Subject</label>
                                                <div id = "feedbacksubject" class="col-md-10">
                                                    <input type="text" id="subject" name="subject" readonly="readonly" style="width: 100%" value="${feedUser.getUserSubject()}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="icode" class="col-md-2 control-label">Body</label>
                                                <div id="feedbackbody" class="col-md-10">
                                                    <textarea rows="10" id="body"  name="body" readonly="readonly" style="width:100%;">${feedUser.getUserBody()}</textarea>                                   
                                                </div>
                                            </div>
                                             <c:if test="${!feedUser.getUserFile().equals('') && feedUser.getUserFile() != null}">
                                                 <div class="form-group">
                                                        <label for="icode" class="col-md-2 control-label"></label>
                                                        <div class="col-md-10">
                                                            <h4><a href="DownloadFile?path=${feedUser.getUserFile()}">CLICK TO DOWNLOAD FILE</a></h4>
                                                        </div>
                                                </div>
                                            </c:if>										            
                                     </div><!--part2-->	
                                     <c:choose>
                                     		<c:when test="${!feedAdvisor.getAdvisorSubject().equals('') && feedAdvisor.getAdvisorSubject() != null && !feedAdvisor.getAdvisorBody().equals('') &&  feedAdvisor.getAdvisorBody()!=null  }">
                                     				 <div id="part2">
			                                        <h4>Your FeedBack : <span style="color: #c84c4e">(After pressing send, please don't close the window till you see the message - 'Your Feedback has been sent')</span></h4>
			                                        <hr>
								 <c:choose>
			                                        <c:when test="<%=!isRejectedFeedback && (fromPreviousSession == null || !fromPreviousSession)  %>">
			                                        		<div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Subject</label>
			                                                <div id = "feedbacksubject" class="col-md-10">
			                                                    <input type="text" id="advisorsubject" name="subject"  style="width:100%" value="${feedAdvisor.getAdvisorSubject()}" maxlength="350">
			                                                </div>
			                                            </div>
			                                            <div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Body</label>
			                                                <div id = "feedbackbody" class="col-md-10">
			                                                    <textarea rows="10" id="advisorbody"  name="body" style="width:100%;" maxlength="3000">${feedAdvisor.getAdvisorBody()}</textarea>                                   
			                                                </div>
			                                            </div>
			                                             <c:if test="${!feedAdvisor.getAdvisorFile().equals('') && feedAdvisor.getAdvisorFile() != null }">
			                                                 <div class="form-group">
			                                                        <label for="icode" class="col-md-2 control-label"></label>
			                                                        <div class="col-md-10">
			                                                            <h4><a href="DownloadFile?path=${feedAdvisor.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a></h4>
			                                                        </div>
			                                                </div>
			                                            </c:if>
			                                        	<div class="form-group">
				                                                <label for="icode" class="col-md-2 control-label">Attach File</label>
				                                                <div id = "fileattach" class="col-md-10">
				                                                    <input type="file" id="attachFile" name="file">
				                                                    <p class="help-block">Max Size 2.5MB</p>
				                                                </div>
		                                            		</div>
			                                            	<div id="responsefeedback">
				                                            </div>
				                                             <div class="form-group">
				                                                <!-- Button -->                                        
				                                                <div class="col-md-offset-2 col-md-10">
				                                                    <input id="btn-signup" type="button" onclick="sendfeedback()" class="btn btn-info" value="Send">
				                                                </div>
				                                            </div>
			                                        </c:when>
			                                        <c:otherwise>
			                                        		<div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Subject</label>
			                                                <div id = "feedbacksubject" class="col-md-10">
			                                                    <input type="text" id="advisorsubject" name="subject" readonly="readonly"  style="width:100%" value="${feedAdvisor.getAdvisorSubject()}">
			                                                </div>
			                                            </div>
			                                            <div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Body</label>
			                                                <div id = "feedbackbody" class="col-md-10">
			                                                    <textarea rows="10" id="advisorbody"  name="body" readonly="readonly" style="width:100%;">${feedAdvisor.getAdvisorBody()}</textarea>                                   
			                                                </div>
			                                            </div>
			                                             <c:if test="${!feedAdvisor.getAdvisorFile().equals('') && feedAdvisor.getAdvisorFile() != null }">
			                                                 <div class="form-group">
			                                                        <label for="icode" class="col-md-2 control-label"></label>
			                                                        <div class="col-md-10">
			                                                            <h4><a href="DownloadFile?path=${feedAdvisor.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a></h4>
			                                                        </div>
			                                                </div>
			                                            </c:if>
			                                        </c:otherwise>
 								</c:choose>
			                                     </div><!--part2-->	
                                     		
                                     		</c:when>
                                     		<c:otherwise>
		                                     	<div id="part2">
		                                        <h4>Your Reply :<span style="color: #c84c4e">(After pressing send, please don't close the window till you see the message - 'Your Feedback has been sent')</h4>
		                                        <hr>
		                                        <c:if test="${(!fromPreviousSession)}">
		                                            <div class="form-group">
		                                                <label for="icode" class="col-md-2 control-label">Subject</label>
		                                                <div id = "feedbacksubject" class="col-md-10">
		                                                    <input type="text" id="advisorsubject" name="subject"  style="width:100%" maxlength="350">
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
		                                                <label for="icode" class="col-md-2 control-label">Body</label>
		                                                <div id = "feedbackbody" class="col-md-10">
		                                                    <textarea rows="10" id="advisorbody"  name="body"  style="width:100%;" maxlength="3000"></textarea>                                   
		                                                </div>
		                                            </div>
		                                             <div class="form-group">
		                                                <label for="icode" class="col-md-2 control-label">Attach File</label>
		                                                <div id = "fileattach" class="col-md-10">
		                                                    <input type="file" id="attachFile" name="file">
		                                                    <p class="help-block">Max Size 2.5MB</p>
		                                                </div>
		                                            </div>
		                                            <div id="responsefeedback">
		                                            </div>
		                                            <hr>
		                                             <div class="form-group">
		                                                <!-- Button -->                                        
		                                                <div class="col-md-offset-2 col-md-10">
		                                                    <input id="btn-signup" type="button" onclick="sendfeedback()" class="btn btn-info" value="Send">
		                                                </div>
		                                            </div>
		                                        </c:if>
		                                     </div><!--part2-->	
                                     	</c:otherwise>
                                     </c:choose>
                                    
                                </div>						
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="email" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                    <h4 class="modal-title" id="myModalLabel">Mail;</h4>
                                </div>
                                <div class="modal-body">
                                    <div id="part2">
                                        <h4>User Mail :</h4>
                                        <hr>
                                            <div class="form-group">
                                                <label for="icode" class="col-md-2 control-label">Subject</label>
                                                <div id = "feedbacksubject" class="col-md-10">
                                                    <input type="text" id="subject" readonly="readonly" name="subject"  style="width: 100%" value="${emailUser.getUserSubject()}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="icode" class="col-md-2 control-label">Body</label>
                                                <div id="feedbackbody" class="col-md-10">
                                                    <textarea rows="10" id="body" readonly="readonly" name="body"  style="width:100%;">${emailUser.getUserBody()}</textarea>                                   
                                                </div>
                                            </div>
                                             <c:if test="${!emailUser.getUserFile().equals('') && emailUser.getUserFile() != null}">
                                                 <div class="form-group">
                                                        <label for="icode" class="col-md-2 control-label"></label>
                                                        <div class="col-md-10">
                                                            <h4><a href="DownloadFile?path=${emailUser.getUserFile()}">CLICK TO DOWNLOAD FILE</a></h4>
                                                        </div>
                                                </div>
                                            </c:if>										            
                                     </div><!--part2-->	
                                     <c:choose>
                                     		<c:when test="${!emailAdvisor.getAdvisorSubject().equals('') && emailAdvisor.getAdvisorSubject() != null && !emailAdvisor.getAdvisorBody().equals('') &&  emailAdvisor.getAdvisorBody()!=null  }">
                                     				 <div id="part2">
			                                        <h4>Your Reply : <span style="color: #c84c4e">(After pressing send, please don't close the window till you see the message - 'Your email has been sent')</span></h4>
			                                        <hr>
			                                        <c:choose>
			                                        <c:when test="<%=!isRejectedMail && (fromPreviousSession == null || !fromPreviousSession)  %>">
			                                        		 <div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Subject</label>
			                                                <div id = "feedbacksubject" class="col-md-10">
			                                                    <input type="text" id="advisorsubjectemail" name="subject"  style="width:100%" value="${emailAdvisor.getAdvisorSubject()}" maxlength="350">
			                                                </div>
			                                            </div>
			                                            <div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Body</label>
			                                                <div id = "feedbackbody" class="col-md-10">
			                                                    <textarea rows="10" id="advisorbodyemail"  name="body" style="width:100%;" maxlength="6000">${emailAdvisor.getAdvisorBody()}</textarea>                                   
			                                                </div>
			                                            </div>
			                                             <c:if test="${!emailAdvisor.getAdvisorFile().equals('') && emailAdvisor.getAdvisorFile() != null }">
			                                                 <div class="form-group">
			                                                        <label for="icode" class="col-md-2 control-label"></label>
			                                                        <div class="col-md-10">
			                                                            <h4><a href="DownloadFile?path=${emailAdvisor.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a></h4>
			                                                        </div>
			                                                </div>
			                                            </c:if>
			                                        	<div class="form-group">
				                                                <label for="icode" class="col-md-2 control-label">Attach File</label>
				                                                <div id = "fileattach" class="col-md-10">
				                                                    <input type="file" id="attachFileemail" name="file">
				                                                    <p class="help-block">Max Size 2.5MB</p>
				                                                </div>
		                                            		</div>
			                                            	<div id="responseemail" style="color: #c84c4e">
				                                            </div>
				                                             <div class="form-group">
				                                                <!-- Button -->                                        
				                                                <div class="col-md-offset-2 col-md-10">
				                                                    <input id="btn-signup" type="button" onclick="sendemail()" class="btn btn-info" value="Send">
				                                                </div>
				                                            </div>
			                                        </c:when>
			                                        <c:otherwise>
			                                        		<div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Subject</label>
			                                                <div id = "feedbacksubject" class="col-md-10">
			                                                    <input type="text" id="advisorsubjectemail" name="subject" readonly="readonly" style="width:100%" value="${emailAdvisor.getAdvisorSubject()}">
			                                                </div>
			                                            </div>
			                                            <div class="form-group">
			                                                <label for="icode" class="col-md-2 control-label">Body</label>
			                                                <div id = "feedbackbody" class="col-md-10">
			                                                    <textarea rows="10" id="advisorbodyemail"  name="body" readonly="readonly" style="width:100%;">${emailAdvisor.getAdvisorBody()}</textarea>                                   
			                                                </div>
			                                            </div>
			                                             <c:if test="${!emailAdvisor.getAdvisorFile().equals('') && emailAdvisor.getAdvisorFile() != null }">
			                                                 <div class="col-md-10">
                                                                <a href="DownloadFile?path=${emailAdvisor.getAdvisorFile()}" class="btn btn-info">CLICK TO DOWNLOAD FILE</a>
                                                            </div>
			                                            </c:if>
			                                        </c:otherwise>
			                                        </c:choose>									            
			                                     </div><!--part2-->	
                                     		
                                     		</c:when>
                                     		<c:otherwise>
		                                     	<div id="part2">
		                                        <h4>Your Reply : <span style="color: #c84c4e">(After pressing send, please don't close the window till you see the message - 'Your mail has been sent')</span></h4>
		                                        <hr>
		                                        <c:if test="${(!fromPreviousSession)}">
		                                            <div class="form-group">
		                                                <label for="icode" class="col-md-2 control-label">Subject</label>
		                                                <div id = "feedbacksubject" class="col-md-10">
		                                                    <input type="text" id="advisorsubjectemail" name="subject"  style="width:100%" maxlength="350">
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
		                                                <label for="icode" class="col-md-2 control-label">Body</label>
		                                                <div id = "feedbackbody" class="col-md-10">
		                                                    <textarea rows="10" id="advisorbodyemail"  name="body"  style="width:100%;" maxlength="3000"></textarea>                                   
		                                                </div>
		                                            </div>
		                                             <div class="form-group">
		                                                <label for="icode" class="col-md-2 control-label">Attach File</label>
		                                                <div id = "fileattach" class="col-md-10">
		                                                    <input type="file" id="attachFileemail" name="file">
		                                                    <p class="help-block">Max Size 2.5MB</p>
		                                                </div>
		                                            </div>
		                                            <div id="responseemail">
		                                            </div>
		                                            <hr>
		                                             <div class="form-group">
		                                                <!-- Button -->                                        
		                                                <div class="col-md-offset-2 col-md-10">
		                                                    <input id="btn-signup" type="button" onclick="sendemail()" class="btn btn-info" value="Send">
		                                                </div>
		                                            </div>
		                                        </c:if>
		                                     </div><!--part2-->	
                                     	</c:otherwise>
                                     </c:choose>
                                    
                                </div>						
                            </div>
                        </div>
                    </div>
                    
                        </form>
                    </div>
                    
		 		</c:forEach>
			
			
			
			
			
			
			
			
			
			<div class="modal fade" id="messageuser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content"  style="background-color:#E5E5E5">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
							<h4 class="modal-title" id="myModalLabel">Message User</h4>
						</div>
						<div class="modal-body">
							<div id="page-wrapper1">
	    
				            <!-- /.row -->
				            <div class="row">
				                    <div class="chat-panel panel panel-default">
				                        <div class="panel-heading">
				                            <i class="fa fa-comments fa-fw"></i>
				                            Message History
										</div>
				                        <!-- /.panel-heading -->
				                        
				                        <div  class="panel-body">
				                      	  <ul id="message" class='chat'>

				                           </ul> 
				                        </div>
				                        <c:if test="${(!fromPreviousSession)}">
					                        <form >
						                        <!-- /.panel-body -->
							                        <div class="panel-footer col-xs-12">
														<form class="form-inline">
															  <div class="form-group col-xs-11">
																	<input id="advisormessage" onkeypress="checkkey(event)" type="text" class="form-control" style="width: 100%;" name="advisormessage" placeholder="Type your message here..." maxlength="350" />
																	<span class="glyphicon glyphicon-paperclip" aria-hidden="true" style="cursor: pointer; float: right;margin-left: 93%;position: absolute;margin-top: 1.5%;" title="Attach File" onclick="initFileSelectionDialogue()"></span>
															  </div>
															  <div class="form-group col-xs-1">
																	<input type="button" id="send" onclick="setmessage()" class="btn btn-warning btn-sm" id="btn-chat" value="Send">
															  </div>
														</form>
														<input type="file" id="uploadFileViaMsgModal" style="display: none;" name="file"/>
														
													</div>
																
						                            <!-- <div class="input-group">
						                                <input  id ="advisormessage" onkeypress="checkkey(event)" type="text" class="form-control input-sm" name="advisormessage" placeholder="Type your message here..." maxlength="350"/>
						                                <span class="input-group-btn">
						                                <input type="button" id="send" onclick="setmessage()" class="btn btn-warning btn-sm" id="btn-chat" value="Send">	
						                                </span>
						                            </div> -->
					                        </form>
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
											formData.append("fromUser", false);
											formData.append("purpose", "File uploaded via msg modal.");

											var xhr = new XMLHttpRequest();
											xhr.open("POST", "SessionFiles");
											xhr.send(formData);
											xhr.onreadystatechange = function() {
												if (xhr.readyState == 4 && xhr.status == 200) {
													console.log("file uploaded successfully.");
													getmessages();

												//	document.getElementById("response").innerHTML = xhr.responseText;
												//	getFiles();
												}
											}
										}
									}
								</script>			
			
			
			
            
				<div class="modal fade" id="uploadfile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content" style="background-color:#E5E5E5">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
							<h4 class="modal-title" id="myModalLabel">Files <span style="color: #c84c4e">(After pressing submit, please don't close the window till you see the message - 'Your File has been uploaded')</span></h4>
						</div>
						<div class="modal-body">
							<form id="uploadform" class="form-horizontal" role="form" enctype="multipart/form-data"  method="post">
	    			
							<div id="part2">
								<h4>Uploaded Files :</h4>
								<div id="uploadedfiles">
									
								</div>
                                <hr>
                                <c:if test="${(!fromPreviousSession)}">
	                                <div class="form-group">
	                                    <label for="icode" class="col-md-3 control-label" style="text-align:left; text-size:19px">Upload File</label>
	                                    <div id = "fileupload" class="col-md-9">
											<input type="file" id="uploadFiles" name="file">
											<p class="help-block"></p>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label for="icode" class="col-md-3 control-label" style="text-align:left; text-size:19px">Message</label>
	                                    <div id = "filepurpose" class="col-md-9">
	                                    <textarea rows="6" cols="50" id="purpose"  name="purpose" ></textarea>
											<p class="help-block"></p>
	                                    </div>
	                                </div>
	                                <div id="response">
	                                </div>
									<hr>
									 <div class="form-group">
	                                    <!-- Button -->                                        
	                                    <div class="col-md-offset-3 col-md-9">
	                                    	<input id="uploadfilesubmit" type="button" onclick="setfile()" class="btn btn-info" value="Submit">
	                                    </div>
	                                </div>
                                </c:if>
							 </div><!--part2-->	
                     </form>
						</div>						
					</div>
				</div>
			</div>
            	
            </div><!-- /right area -->
            
		</div><!-- /row -->
    </div>
	<!-- /#wrapper -->
    </fmt:bundle>    
    <%@include file="/Footer.jsp" %>
    </div>
	<!-- /#container -->

    <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
    <script>
    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
	<script>
      $(function () { $('.popover-show').popover('show');});
      $(function () { $('.popover-hide').popover('hide');});
      $(function () { $('.popover-destroy').popover('destroy');});
      $(function () { $('.popover-toggle').popover('toggle');});
     $(function () { $(".popover-options a").popover({html : true });});
   </script>
	<script type="text/javascript">
	  function checkkey(event){
	      if (!event) event = event || window.event;
		  if(event.keyCode == '13'){
			  event.preventDefault();
			  setmessage();
		  }		  
	  }
	  function setmessage(){
		  var input = document.getElementById("advisormessage");
		  if($("#advisormessage").val() != ""){
		  var val = $("#advisormessage").val();
    	  input.value = "";
			  $.ajax({
	              url : 'SessionMessages', // Your Servlet mapping or JSP(not suggested)
	              data : {"sId" : $("#sId").val(),"message" : val,"advisor":true},
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
	  	}else{
	  		alert("Please enter a message");
	  	}
	  }
	  function getmessages(){
			  $.ajax({
	              url : 'SessionMessages', // Your Servlet mapping or JSP(not suggested)
	              data : {"sId" : $("#sId").val(),"advisor": "true"},
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
	  function setfile(){
		  var setFile = true;
		  var input1 = document.getElementById("uploadFiles");
		  if(input1.value == ""){
			  setFile = false;
			  alert("Please upload a file");
		  }else if(document.getElementById('uploadFiles').files[0] && $('#uploadFiles')[0].files[0].size > 2621440){
	    		 alert("Please upload a file less than 2.5 MB");
	    		 setFile = false;
	    	 }
		  if(setFile){
			  var formData = new FormData();
			  formData.append("myFile", document.getElementById("uploadFiles").files[0]);
			  formData.append("sId",$("#sId").val());
			  formData.append("purpose",$("#purpose").val());
			  formData.append("fromUser",false);	  
			  var xhr = new XMLHttpRequest();
			  xhr.open("POST", "SessionFiles");
			  xhr.send(formData);
			  xhr.onreadystatechange=function()
			  {
			  if (xhr.readyState==4 && xhr.status==200)
			    {
			    document.getElementById("response").innerHTML=xhr.responseText;
			    getFiles();
			    }
			  }
		  }
		  var input = document.getElementById("purpose");
		  input.value = "";
		  input1.value = "";
		  
	  }
	  function getFiles(){
		  $.ajax({
              url : 'SessionFiles', // Your Servlet mapping or JSP(not suggested)
              data : {"sId" : $("#sId").val(),"getFile" : true,"advisor":true},
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
	
	  function sendfeedback(){
		  var isError = false;
		  if($("#advisorsubject").val() == ""){
	    		 alert("Please Enter Subject");
	    		 event.preventDefault();
	    		 isError = true
	    	 }else if($("#advisorbody").val() == ""){
	    		 alert("Please Enter Body");
	    		 event.preventDefault();
	    		 isError = true;
	    	 }else if(document.getElementById('attachFile').files[0] && $('#attachFile')[0].files[0].size > 2621440){
	    		 alert("Please upload a file less than 2.5 MB");
	    		 event.preventDefault();
	    		 isError = true;
	    	 }
		  if(!isError){
			  var formData = new FormData();
			  formData.append("myFile", document.getElementById("attachFile").files[0]);
			  formData.append("sId",$("#sId").val());
			  formData.append("body",$("#advisorbody").val());
			  formData.append("subject",$("#advisorsubject").val());
			  formData.append("advisor","true");
			  var xhr = new XMLHttpRequest();
			  xhr.open("POST", "SessionFeedBack");
			  xhr.send(formData);
			  xhr.onreadystatechange=function()
			  {
			  if (xhr.readyState==4 && xhr.status==200)
			    {
			    document.getElementById("responsefeedback").innerHTML=xhr.responseText;
			    getFiles();
			    }
			  }
			  var input = document.getElementById("advisorbody");
			  input.value = "";
			  var input1 = document.getElementById("attachFile");
			  input1.value = "";
			  var input2 = document.getElementById("advisorsubject");
			  input2.value = "";
		  }
	  }
	  function sendemail(){
		  var isError = false;
		  if($("#advisorsubjectemail").val() == ""){
	    		 alert("Please Enter Subject");
	    		 event.preventDefault();
	    		 isError = true
	    	 }else if($("#advisorbodyemail").val() == ""){
	    		 alert("Please Enter Body");
	    		 event.preventDefault();
	    		 isError = true;
	    	 }else if(document.getElementById('attachFileemail').files[0] && $('#attachFileemail')[0].files[0].size > 2621440){
	    		 alert("Please upload a file less than 2.5 MB");
	    		 event.preventDefault();
	    		 isError = true;
	    	 }
		  if(!isError){
			  var formData = new FormData();
			  formData.append("myFile", document.getElementById("attachFileemail").files[0]);
			  formData.append("sId",$("#sId").val());
			  formData.append("body",$("#advisorbodyemail").val());
			  formData.append("subject",$("#advisorsubjectemail").val());
			  formData.append("advisor","true");
			  formData.append("email","advisor");
			  var xhr = new XMLHttpRequest();
			  xhr.open("POST", "SessionFeedBack");
			  xhr.send(formData);
			  xhr.onreadystatechange=function()
			  {
			  if (xhr.readyState==4 && xhr.status==200)
			    {
			    document.getElementById("responseemail").innerHTML=xhr.responseText;
			    getFiles();
			    }
			  }
			  var input = document.getElementById("advisorbodyemail");
			  input.value = "";
			  var input1 = document.getElementById("attachFileemail");
			  input1.value = "";
			  var input2 = document.getElementById("advisorsubjectemail");
			  input2.value = "";
		  }
	  }
	  function SubmitFeedBackForm(elem,event){
		  var service = elem.id;
		   var isError = false;
			var avatar = $("#servicefiles").val();
			if(avatar.length < 1) {
				event.preventDefault();
				alert("Please upload a file");
				isError = true;
			}else if(document.getElementById('servicefiles').files[0] && $('#servicefiles')[0].files[0].size > 2621440){
	    		 alert("Please upload a file less than 2.5 MB");
	    		 event.preventDefault();
	    		 isError = true;
	    	 }
		  if(!isError){
			  var formData = new FormData();
			  formData.append("myFile", document.getElementById("servicefiles").files[0]);
			  formData.append("sId",$("#sId").val());
			  formData.append("type", "advisor");
			  formData.append("service", service);
			  var xhr = new XMLHttpRequest();
			  xhr.open("POST", "ApproveFeedback");
			  xhr.send(formData);
			  xhr.onreadystatechange=function()
			  {
			  if (xhr.readyState==4 && xhr.status==200)
			    {
			    document.getElementById("responseservice").innerHTML=xhr.responseText;
			    getFiles();
			    }
			  }
			  var input1 = document.getElementById("servicefiles");
			  input1.value = "";
		  }
		  
	  }
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
                            	