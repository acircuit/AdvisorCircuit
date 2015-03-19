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

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-9">
            	<h4></h4>
            	<h1 class="page-header"></h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper" class="grey-panel">
        
                    <h3 class="ses-detail">Session Details</h3>
                    
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
                                                    <label for="icode" class="col-md-2 control-label">Advisor :</label>
                                                    <div class="col-md-10">
                                                        <a href="${advisorId }">
                                                            <span class="form-control"><%=advisorname %></span>
                                                        </a>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">Advisor Image:</label>
                                                    <div class="col-md-10">
                                                        <a href="${advisorId }">											
                                                            <img style="width:50%; float:left;" id="userimage" src="<%=advisorImage %>" alt="">
                                                        </a>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">User :</label>
                                                    <div class="col-md-10">
                                                        <span class="form-control"><%=userName.toUpperCase() %></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">User Image :</label>
                                                    <div class="col-md-10">
                                                        <img style="width:50%; float:left;"  id="userimage"  src="<%=userImage %>" alt="">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">Service :</label>
                                                    <div class="col-md-10">
                                                        <c:if test="${userRequest.getService().equals('careertalk')}">
                                                        <span class="form-control">Career Talk</span>
                                                        </c:if>
                                                        <c:if test="${userRequest.getService().equals('mockinterview')}">
                                                        <span class="form-control">Mock Interview</span>
                                                        </c:if>
                                                        <c:if test="${userRequest.getService().equals('cvcritique')}">
                                                        <span class="form-control">Resume Critique</span>
                                                        </c:if>
                                                        <c:if test="${userRequest.getService().equals('personalworkshops')}">
                                                        <span class="form-control">Personal Workshop</span>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">Query :</label>
                                                    <div class="col-md-10">
                                                        <textarea class="form-control" rows="3" readonly><c:out value="${userRequest.getQuery()}"/></textarea>
                                                    </div>
                                                </div>
                                                  
                                                <div class="form-group">
                                               		<label for="icode" class="col-md-2 control-label">Mode:</label>
                                                    <div class="col-md-10">
                                                        <span class="form-control"><c:out value="${fn:toUpperCase(userRequest.getMode())}"/></span>
                                                    </div>
                                                </div>				
                                                 
                                                <div class="form-group">
                                                	<label for="icode" class="col-md-2 control-label">Duration (Hrs):</label>
                                                    <div class="col-md-10">
                                                        <span class="form-control"><c:out value="${userRequest.getDuration()}"/></span>
                                                    </div>
                                                </div>
                                                
                                                <c:choose>
                                                    <c:when test="${userRequest.getMode().equals('phone')}">
                                                        <div class="form-group">
                                                            <label for="icode" class="col-md-2 control-label">Pin:</label>
                                                            <div class="col-md-10">
                                                                <input class="form-control" id="details"></input>	
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="col-md-12">
                                                        	<h4><a class="btn btn-info" onclick="SubmitModeDetails()">Submit Pin</a></h4>												
                                                        </div>
                                                    </c:when>
                                                    
                                                    <c:when test="${userRequest.getMode().equals('webchat')}">
                                                        <div class="form-group">
                                                            <label for="icode" class="col-md-2 control-label">Link:</label>
                                                            <div class="col-md-10">
                                                                <input class="form-control" id="details"></input>	
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="col-md-12">
                                                        	<h4><a class="btn btn-info" onclick="SubmitModeDetails()">Submit Link</a></h4>
                                                        </div>
                                                        
                                                        <div id="message" style="float: right"></div>
                                                        
                                                    </c:when>									
                                                </c:choose>
                                                
                                                <input type="hidden" name="rId" value="${userRequest.getRequestId()}">
                                                
                                                <c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">										
                                                <div class="form-group">
                                                    <div class="col-md-10">
                                                        <h4><a href="DownloadFile?rid=${userRequest.getRequestId()}">CLICK TO DOWNLOAD <%=userName.toUpperCase() %>'s CV</a></h4>
                                                    </div>
                                                </div>
                                                </c:if>
                                                
                                                <c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
                                                    <div class="form-group">
                                                        <label for="icode" class="col-md-2 control-label"></label>
                                                        <div class="col-md-9">
                                                            <h4><a href="DownloadFile?path=<%=path%>" >${fn:toUpperCase(userRequest.getService())} FeedBack Form</a></h4>
                                                        </div>
                                                        
                                                        <div class="col-md-12">
                                                            <h4><a class="btn btn-info" onclick="approvefeedback()">Approve FeedBack </a></h4>
                                                        </div>
                                                        
                                                        <div id="message" style="float: right"></div>
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
                                                    	<label for="icode" class="col-md-2 control-label">Session ID</label>
                                                        <div class="col-md-10">
                                                            <span class="form-control"><c:out value="${session.getSessionId()}"/></span>
                                                        </div>    
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label for="icode" class="col-md-2 control-label">Session Plan</label>
                                                        <div class="col-md-10">
                                                            <textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label for="icode" class="col-md-2 control-label">Accepted Date</label>
                                                        <div class="col-md-10">
                                                            <span class="form-control"><c:out value="${session.getAcceptedDateString()}"/></span>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group" style="margin-bottom:0;">
                                                        <div class="col-md-12">
                                                        <c:if test="${feedback != null}">
                                                           <a data-toggle="modal" data-target="#feedback" class="btn btn-info">Follow Up Mail</a>
                                                        </c:if>
                                                        <c:if test="${userRequest.getMode().equals('email') && mail != null }">
                                                            <a data-toggle="modal" data-target="#email" class="btn btn-info">Mail</a>
                                                        </c:if>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group">    
                                                        <div class="col-md-12">
                                                            <a id="show" class="btn btn-info" onclick="ApproveFollowUp(this)" style="margin-top:15px;">SHOW FOLLOW UP MAIL</a>
                                                            <a id="hide" class="btn btn-info" onclick="ApproveFollowUp(this)" style="margin-top:15px;">HIDE FOLLOW UP MAIL</a>
                                                        </div>
                                                    </div>
                                                    
                                                        <div class="modal fade" id="feedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog modal-lg">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                                                                        <h4 class="modal-title" id="myModalLabel">FeedBack</h4>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                            <div id="part2">
                                                                                <h4>User FeedBack :</h4>
                                                                                <hr>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Subject</label>
                                                                                        <div id = "feedbacksubject" class="col-md-10">
                                                                                            <input type="text" id="subject" name="subject" readonly="readonly" style="width: 80%" value="${feedback.getUserSubject()}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Body</label>
                                                                                        <div id="feedbackbody" class="col-md-10">
                                                                                            <textarea rows="10" id="body" readonly="readonly" name="body" style="width:100%">${feedback.getUserBody()}</textarea>                                   
                                                                                        </div>
                                                                                    </div>
                                                                                    <c:if test="${!feedback.getUserFile().equals('') && feedback.getUserFile().equals('')!= null}">
                                                                                         <div class="form-group">
                                                                                                <div class="col-md-12">
                                                                                                    <h4><a href="DownloadFile?path=${feedback.getUserFile()}">CLICK TO DOWNLOAD FILE</a></h4>
                                                                                                </div>
                                                                                        </div>
                                                                                    </c:if>
                                                                                    <div id="responsefeedbackuser">
                                                                                    </div>											            
                                                                                     <div class="form-group">
                                                                                        <!-- Button -->
                                                                                        <label for="icode" class="col-md-2 control-label"></label>                                 
                                                                                        <div class="col-md-10">
                                                                                            <input id="btn-signup" type="button" onclick="ApproveUserFeedBack()" class="btn btn-info" value="Approve">
                                                                                            <input id="btn-signup" type="button" onclick="RejectUserFeedBack()" class="btn btn-info" value="Reject" >
                                                                                        </div>
                                                                                    </div>
                                                                             </div><!--part2-->	
                                                                            <hr>																	 
                                                                             <div id="part2">
                                                                                <h4>Advisor FeedBack :</h4>
                                                                                <hr>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Subject</label>
                                                                                        <div id = "feedbacksubject" class="col-md-10">
                                                                                            <input type="text" id="subject" readonly="readonly" name="subject"  style="width:80%" value="${feedback.getAdvisorSubject()}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Body</label>
                                                                                        <div id = "feedbackbody" class="col-md-10">
                                                                                            <textarea rows="10" id="body" readonly="readonly" name="body" style="width:100%">${feedback.getAdvisorBody()}</textarea>                                   
                                                                                        </div>
                                                                                    </div>
                                                                                    <c:if test="${!feedback.getAdvisorFile().equals('') && feedback.getAdvisorFile().equals('')!= null}">
                                                                                     <div class="form-group">
                                                                                            <label for="icode" class="col-md-2 control-label"></label>
                                                                                            <div class="col-md-10">
                                                                                                <h4><a href="DownloadFile?path=${feedback.getAdvisorFile()}">CLICK TO DOWNLOAD FILE</a></h4>
                                                                                            </div>
                                                                                    </div>
                                                                                    </c:if>
                                                                                    <div id="responsefeedbackadvisor">
                                                                                    </div>
                                                                                    <hr>
                                                                                     <div class="form-group">
                                                                                        <!-- Button -->    
                                                                                        <label for="icode" class="col-md-2 control-label"></label>                                    
                                                                                        <div class="col-md-10">
                                                                                            <input id="btn-signup" type="button" onclick="ApproveAdvisorFeedBack()" class="btn btn-info" value="Approve">
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
                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                                                                        <h4 class="modal-title" id="myModalLabel">Mail</h4>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                            <div id="part2">
                                                                                <h4>User Mail :</h4>
                                                                                <hr>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Subject</label>
                                                                                        <div id="feedbacksubject" class="col-md-10">
                                                                                            <input type="text" id="subjectemailuser" readonly="readonly" name="subject"  style="width:80%" value="${mail.getUserSubject()}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Body</label>
                                                                                        <div id = "feedbackbody" class="col-md-10">
                                                                                            <textarea rows="10" id="bodyemailuser" readonly="readonly" name="body" style="width:100%">${mail.getUserBody()}</textarea>                                   
                                                                                        </div>
                                                                                    </div>
                                                                                    <c:if test="${!mail.getUserFile().equals('') && mail.getUserFile().equals('')!= null}">
                                                                                         <div class="form-group">
                                                                                                <label for="icode" class="col-md-2 control-label"></label>
                                                                                                <div class="col-md-10">
                                                                                                    <h4><a href="DownloadFile?path=${mail.getUserFile()}">CLICK TO DOWNLOAD FILE</a></h4>
                                                                                                </div>
                                                                                        </div>
                                                                                    </c:if>
                                                                                    <div id="responseemailuser">
                                                                                    </div>											            
                                                                                     <div class="form-group">
                                                                                        <!-- Button -->                                        
                                                                                        <label for="icode" class="col-md-2 control-label"></label>
                                                                                        <div class="col-md-10">
                                                                                            <input id="btn-signup" type="button" onclick="ApproveUserMail()" class="btn btn-info" value="Approve">
                                                                                            <input id="btn-signup" type="button" onclick="RejectUserMail()" class="btn btn-info" value="Reject" >
                                                                                        </div>
                                                                                    </div>
                                                                             </div><!--part2-->	
                                                                            <hr>																	 
                                                                             <div id="part2">
                                                                                <h4>Advisor Mail :</h4>
                                                                                <hr>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Subject</label>
                                                                                        <div id="feedbacksubject" class="col-md-10">
                                                                                            <input type="text" id="subjectemailadvisor" name="subject" style="width:80%" value="${mail.getAdvisorSubject()}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label">Body</label>
                                                                                        <div id="feedbackbody" class="col-md-10">
                                                                                            <textarea rows="10" id="bodyemailadvisor" name="body" style="width:100%">${mail.getAdvisorBody()}</textarea>                                   
                                                                                        </div>
                                                                                    </div>
                                                                                    <c:if test="${!mail.getAdvisorFile().equals('') && mail.getAdvisorFile().equals('')!= null}">
                                                                                     <div class="col-md-12">
                                                                                     	<a href="DownloadFile?path=${mail.getAdvisorFile()}" class="btn btn-info">CLICK TO DOWNLOAD FILE</a>
                                                                                     </div>
                                                                                    </c:if>
                                                                                    <div id="responseemailadvisor">
                                                                                    </div>
                                                                                    <hr>
                                                                                     <div class="form-group">
                                                                                        <label for="icode" class="col-md-2 control-label"></label>                                       
                                                                                        <div class="col-md-10">
                                                                                            <input id="btn-signup" type="button" onclick="ApproveAdvisorMail()" class="btn btn-info" value="Approve">
                                                                                            <input id="btn-signup" type="button" onclick="RejectAdvisorMail()" class="btn btn-info" value="Reject">
                                                                                        </div>
                                                                                    </div>
                                                                            </div>
                                                                    </div>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <div class="form-group">
                                                           <c:choose>
                                                                    <c:when test="${isSessionPast.equals('true')}">
                                                                        <div class="col-md-12" style="margin-bottom:15px;">
                                                                             <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
                                                                                <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                                                <c:param name="sId" value="${session.getSessionId()}"></c:param>
                                                                                <c:param name="advisornoshow" value="true"></c:param>
                                                                            </c:url>
                                                                            <a href="${myURL}" class="btn btn-info">SET SESSION CANCELLED DUE TO ADVISOR NO SHOW </a>
                                                                        </div>
                                                                        <div class="col-md-12" style="margin-bottom:15px;">
                                                                             <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
                                                                                <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                                                <c:param name="sId" value="${session.getSessionId()}"></c:param>
                                                                                <c:param name="usernoshow" value="true"></c:param>
                                                                            </c:url>
                                                                            <a href="${myURL}" class="btn btn-info">SET SESSION CANCELLED DUE TO USER NO SHOW </a>
                                                                        </div>
                                                                        <div class="col-md-12" style="margin-bottom:15px;">
                                                                             <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
                                                                                <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                                                <c:param name="sId" value="${session.getSessionId()}"></c:param>
                                                                                <c:param name="complete" value="true"></c:param>
                                                                            </c:url>
                                                                            <a href="${myURL}" class="btn btn-info">SET SESSION COMPLETE </a>
                                                                        </div>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="col-md-12" style="margin-bottom:15px;">
                                                                             <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
                                                                                <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                                                <c:param name="sId" value="${session.getSessionId()}"></c:param>
                                                                                <c:param name="advisorunavailabale" value="true"></c:param>
                                                                            </c:url>
                                                                            <a href="${myURL}" class="btn btn-info">SET SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY </a>
                                                                        </div>
                                                                        <div class="col-md-12" style="margin-bottom:15px;">
                                                                             <c:url value="AdminMyUpcomingSessionViewDetailForm" var="myURL">
                                                                                <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                                                <c:param name="sId" value="${session.getSessionId()}"></c:param>
                                                                                <c:param name="userunavailabale" value="true"></c:param>
                                                                            </c:url>
                                                                            <a href="${myURL}" class="btn btn-info">SET SESSION CANCELLED DUE TO USER UNAVAILABILITY </a>
                                                                        </div>
                                                                    
                                                                    </c:otherwise>
                                                                </c:choose>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                            </form>
                                        </div>
                    </c:forEach>
            
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
