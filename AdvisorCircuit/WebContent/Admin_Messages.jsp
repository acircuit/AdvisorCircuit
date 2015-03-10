<!DOCTYPE html>
<%@page import="org.AC.dto.MessageDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Message Admin</title>
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
		div{font-family:'custom_light' !important;} 
		 p{font-family:'custom_light' !important;}
		.fa-lg{font-size:1.4em !important;}
		.social{padding-bottom:9px !important;}
		.foot-size{font-size:14px !important;}
		.foot-color{color:#717171;}
		.gray{color:#575758;}
		.red{color:#B80000;}
		@media only screen and (max-width:700px){
		
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		}
		 </style>  		 
	
    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="assets/plugins/timeline.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="assets/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<%
			List<MessageDTO> messages = (List<MessageDTO>)request.getAttribute("message");
			String name =(String) request.getAttribute("name");
			String email =(String) request.getAttribute("email");
			String aId =(String) request.getAttribute("aId");
			pageContext.setAttribute("messages", messages);
			
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
            <!-- /.row -->
            <div class="row">
                    <div class="chat-panel panel panel-default"   style="margin-top:30px">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>
                            Message History
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                    	  	<ul class="chat">
                      	  <c:choose>
                      	  	<c:when test="${messages.size() > 0}">
							<c:forEach items="${messages}" var="message">
                                <c:set var="messageId" value="${message.getMessageId()}"></c:set>
                                <c:if test="${message.getAdvisorMessage() != null}">
                                <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font"><%=name %></strong> 
                                            <small class="pull-right text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i><c:out value="${message.getAdvisorMessageTimeString()}"></c:out>
                                            </small>
                                        </div>
                                        <p>
											<c:out value="${message.getAdvisorMessage()}"></c:out>  
                                       </p>
                                    </div>
                                </li>
                                </c:if>
                                <c:if test="${message.getAdminMessage() != null  }">
	                                <li class="right clearfix">
	                                    <span class="chat-img pull-right">
	                                        <i class="glyphicon glyphicon-user red"></i>
	                                    </span>
	                                    <div class="chat-body clearfix">
	                                        <div class="header">
	                                            <small class=" text-muted">
	                                                <i class="fa fa-clock-o fa-fw"></i> <c:out value="${message.getAdminMessageTimeString()}"></c:out></small>
	                                            <strong class="pull-right primary-font">Admin</strong>
	                                        </div>
	                                        <p style="text-align:right">
												<c:out value="${message.getAdminMessage()}"></c:out>	  
											</p>
	                                    </div>
	                                </li>
                                </c:if>
                               </c:forEach>
                      	  	</c:when>
                      	  <c:otherwise>
                      	  	<c:out value="There are no messages"></c:out>
                      	  </c:otherwise>
                      	  </c:choose>
                      	   </ul>
                            </div>
                            <form action="AdminMessage" method="post">
                            <input type="hidden" name="mId" value="${messageId}">
                            <input type="hidden" name="aId" value="${aId}">
                            <input type="hidden" name="email" value="<%=email%>">                            
	                        <!-- /.panel-body -->
	                        <div class="panel-footer">
	                            <div class="input-group">
	                                <input id="btn-input" type="text" class="form-control input-sm" name="adminmessage" placeholder="Type your message here..." />
	                                <span class="input-group-btn">
	                                <input type="submit" class="btn btn-warning btn-sm" id="btn-chat" value="Send">	                                   
	                                </span>
	                            </div>
	                        </div>
                        </form>
                       <!-- /.panel-footer -->
                    </div>
                    <!-- /.panel .chat-panel -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
		
    </div>
   	</div>
    
    <!-- /#wrapper -->
        <div style="margin-left:5.9%;margin-right:5.9%">	
    	<%@include file="/Footer.jsp" %>
    	</div>
	<!-- /#container -->

     <!-- jQuery Version 1.11.0 -->
    <script src="assets/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>
</body>

</html>
