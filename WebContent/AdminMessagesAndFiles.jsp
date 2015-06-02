<!DOCTYPE html>
<%@page import="org.AC.dto.FilesDTO"%>
<%@page import="org.AC.dto.AdvisorMessageDTO"%>
<%@page import="org.AC.dto.UserMessageDTO"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>Messages & Files</title>
<style type="text/css">
.form-control {
	width: 100% !important;
}

body {
	background-color: #fff !important
}

@font-face {
	font-family: "custom_light";
	src: url("assets/fonts/NGCDMC__.TTF") /* EOT file for IE */
}

@font-face {
	font-family: "custom_bold";
	src: url("assets/fonts/NGCDBC__.TTF") /* TTF file for CSS3 browsers */
}

h1, h2, h3, h5 {
	font-family: 'custom_bold' !important;
}

h4 {
	font-family: 'custom_light' !important;
	font-size: 19px !important;
}

p {
	font-family: 'custom_light' !important;
}

div{
	font-family:'custom-light' !important;
}

tr{
	border:1px solid #000000;
}

.fa-lg {
	font-size: 1.4em !important;
}

.social {
	padding-bottom: 9px !important;
}

.foot-size {
	font-size: 14px !important;
}

.foot-color {
	color: #717171;
}

@media only screen and (max-width:700px) {
}

@media only screen and (min-width: 320px) and (max-width: 470px) {
}

@media only screen and (min-width: 470px) and (max-width: 700px) {
}
</style>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- MetisMenu CSS -->
<link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/main.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="assets/css/sb-admin-2.css" rel="stylesheet">
<!-- Custom styles for this template -->

<!-- Fonts from Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>


<!-- Custom Fonts -->
<link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.bdesign {
	font-size: 20px !important;
}

.dropreg {
	height: 30px;
	width: 40%;
}

input[type="text"] {
	height: 40px !important
}

.add-on {
	height: 40px !important;
	cursor: pointer !important
}

.control-label {
	width: 130px !important
}
table{font-family:'custom_light' !important;}
</style>
<%
	List<UserMessageDTO> usermessages= (List<UserMessageDTO>)request.getAttribute("usermessages");
		List<AdvisorMessageDTO> advisormessages= (List<AdvisorMessageDTO>)request.getAttribute("advisormessages");
		List<FilesDTO> files= (List<FilesDTO>)request.getAttribute("files");
	   			pageContext.setAttribute("usermessages", usermessages);
	   			pageContext.setAttribute("advisormessages", advisormessages);
		pageContext.setAttribute("files", files);
%>
</head>

<body>

	<div style="margin-left: 5.9%; margin-right: 5.9%">
		<%@include file="/Header.jsp"%>
		<div id="wrapper">

			<!-- Navigation -->
			<nav class=" navbar-static-top" role="navigation"
				style="margin-bottom: 0"></nav>

			<div id="page-wrapper" style="background-color:#E5E5E5; margin-left:0px">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header" style="color:#575758">Session Messages & Upcoming Files</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel">
							<div class="panel-body">

								<div class="col-md-12">
									<table style="text-align: center" class="table">
										<tr>
											<th style="text-align: center">Type</th>
											<th style="text-align: center">By</th>
											<th style="text-align: center">Time</th>
											<th style="text-align: center">Message/File</th>
											<th style="text-align: center">Comment</th>
											<th style="text-align: center">Status</th>
										</tr>
										<c:forEach var="message" items="${usermessages}">
											<tr>
												<td>MESSAGE</td>
												<td>USER</td>
												<td style="text-align: left"><c:out
														value="${message.getUserMessageTimeString()}"></c:out></td>
												<td>
													<p>
														<a data-toggle="modal" data-target="#usermessage${message.getMessageId()}">User
															Message</a>
													</p>
												</td>
												<td></td>
												<c:choose>
													<c:when test="${message.getStatus().equals('APPROVED')}">
														<td>APPROVED</td>
													</c:when>
													<c:when test="${message.getStatus().equals('REJECTED')}">
														<td>REJECTED</td>
													</c:when>
													<c:otherwise>
														<td>
															<button id="usermessagebtn" type="Submit"
																class="btn btn-info" onclick="SetUserMessageApproval(this)"
																value="${message.getMessageId()}">APPROVE</button> OR
															<button id="usermessagebtn" type="Submit"
																class="btn btn-info"
																onclick="SetUserMessageCancellation()"
																value="${message.getMessageId()}">REJECT</button>
															<div id="usermessage"></div>
														</td>
													</c:otherwise>
												</c:choose>
											</tr>
											<div class="modal fade" id="usermessage${message.getMessageId()}" tabindex="-1"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																aria-hidden="true"><i class="glyphicon glyphicon-remove"></i></a></button>
															<h4 class="modal-title" id="myModalLabel">User Message</h4>
														</div>
														<div class="modal-body">
															<p>${message.getUserMessage()}</p>
														</div>
													</div>
													<!-- /.modal-content -->
												</div>
												<!-- /.modal-dialog -->
											</div>
											<c:set var="usermsgsessionId" value="${message.getSessionId()}"></c:set>
											<!-- /.modal -->
										</c:forEach>
										<c:forEach var="message" items="${advisormessages}">
											<tr>
												<td>MESSAGE</td>
												<td>ADVISOR</td>
												<td><c:out
														value="${message.getAdvisorMessageTimeString()}"></c:out></td>
												<td>
													<p>
														<a data-toggle="modal" data-target="#advisormessage${message.getMessageId()}">Advisor
															Message</a>
													</p>
												</td>
												<td></td>
												<c:choose>
													<c:when test="${message.getStatus().equals('APPROVED')}">
														<td>APPROVED</td>
													</c:when>
													<c:when test="${message.getStatus().equals('REJECTED')}">
														<td>REJECTED</td>
													</c:when>
													<c:otherwise>
														<td>
															<button id="advisormessagebtn" type="Submit"
																class="btn btn-info"
																onclick="SetAdvisorMessageApproval()"
																value="${message.getMessageId()}">APPROVE</button> OR
															<button id="advisormessagebtn" type="Submit"
																class="btn btn-info"
																onclick="SetAdvisorMessageCancellation()"
																value="${message.getMessageId()}">REJECT</button>
															<div id="advisormessage"></div>
														</td>
													</c:otherwise>
												</c:choose>
											</tr>
											<div class="modal fade" id="advisormessage${message.getMessageId()}" tabindex="-1"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																aria-hidden="true">×</button>
															<h4 class="modal-title" id="myModalLabel">Advisor Message</h4>
														</div>
														<div class="modal-body">
															<p>${message.getAdvisorMessage()}</p>
														</div>
													</div>
													<!-- /.modal-content -->
												</div>
												<!-- /.modal-dialog -->
											</div>
											<c:set var="advisormsgsessionId" value="${message.getSessionId()}"></c:set>
											<!-- /.modal -->
										</c:forEach>
										<c:forEach var="file" items="${files}">
											<tr>
												<td>FILE</td>
												<td><c:out value="${file.getUploadedBy()}"></c:out></td>
												<td><c:out value="${file.getTimeString()}"></c:out></td>
												<td><a href="${file.getHref()}">${file.getFileName()}</a></td>
												<td><c:out value="${file.getComment()}"></c:out></td>
												<c:choose>
													<c:when test="${file.getStatus().equals('APPROVED')}">
														<td>APPROVED</td>
													</c:when>
													<c:when test="${file.getStatus().equals('REJECTED')}">
														<td>REJECTED</td>
													</c:when>
													<c:otherwise>
														<td><button id="filebtn" type="Submit"
																class="btn btn-info" onclick="SetFileApproval(this)"
																value="${file.getId()}">APPROVE</button> OR
															<button id="filebtn" type="Submit" class="btn btn-info"
																onclick="SetFileCancellation()" value="${file.getId()}">REJECT</button>
															<div id="filemessage"></div></td>
													</c:otherwise>
												</c:choose>

											</tr>
											<c:set var="filesessionId" value="${file.getSessionId()}"></c:set>
										</c:forEach>

									</table>

									<div style="height: 40px"></div>

									<div class="col-md-6">
										<table class="table">

										</table>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->
		<%@include file="/Footer.jsp"%>
	</div>
	<!-- /#container -->

	<!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


	<!-- Custom Theme JavaScript -->
	<script src="assets/js/sb-admin-2.js"></script>
	<!-- Page-Level Demo Scripts - Notifications - Use for reference -->
	<script>
		// tooltip demo
		$('.tooltip-demo').tooltip({
			selector : "[data-toggle=tooltip]",
			container : "body"
		})

		// popover demo
		$("[data-toggle=popover]").popover()
	</script>
	<script type="text/javascript">
		function SetFileApproval(e) {
			var val = $('#filebtn').val();
			$.ajax({
				url : 'AdminApproveSessionMessagesAndFiles', // Your Servlet mapping or JSP(not suggested)
				data : {
					"fileId" : val,
					"status" : "APPROVED",
					"sId"    : "${filesessionId}"
				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					location.reload();
					$('#filemessage').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		function SetFileCancellation() {
			var val = $('#filebtn').val();
			$.ajax({
				url : 'AdminApproveSessionMessagesAndFiles', // Your Servlet mapping or JSP(not suggested)
				data : {
					"fileId" : val,
					"status" : "REJECTED",
					"sId"    : ${filesessionId}
				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					location.reload();
					$('#filemessage').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		function SetUserMessageApproval(event) {
			var val = $('#usermessagebtn').val();
			$.ajax({
			    url : 'AdminApproveSessionMessagesAndFiles', // Your Servlet mapping or JSP(not suggested)
			    data : {"userMessageId" : val,"status" : "APPROVED","sId" : ${usermsgsessionId}},
			    type : 'POST',
			    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
			    success : function(response) {
			    	location.reload();
			    	$('#usermessage').html(response); // create an empty div in your page with some id
			    },
			    error : function(request, textStatus, errorThrown) {
			        alert(errorThrown);
			    }
			}); 
		}
		function SetUserMessageCancellation() {
			var val = $('#usermessagebtn').val();
			$.ajax({
				url : 'AdminApproveSessionMessagesAndFiles', // Your Servlet mapping or JSP(not suggested)
				data : {
					"userMessageId" : val,
					"status" : "REJECTED"
				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					location.reload();
					$('#usermessage').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		function SetAdvisorMessageApproval() {
			var flag =false;
			var val = $('#advisormessagebtn').val();
			$.ajax({
				url : 'AdminApproveSessionMessagesAndFiles', // Your Servlet mapping or JSP(not suggested)
				data : {
					"advisorMessageId" : val,
					"status" : "APPROVED",
					"sId"    : "${advisormsgsessionId}"

				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					location.reload();	
					$('#advisormessage').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
		function SetAdvisorMessageCancellation() {
			var val = $('#advisormessagebtn').val();
			$.ajax({
				url : 'AdminApproveSessionMessagesAndFiles', // Your Servlet mapping or JSP(not suggested)
				data : {
					"advisorMessageId" : val,
					"status" : "REJECTED",
					"sId"    : "${advisormsgsessionId}"
				},
				type : 'POST',
				dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				success : function(response) {
					location.reload();
					$('#advisormessage').html(response);
				},
				error : function(request, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
	</script>
</body>

</html>
