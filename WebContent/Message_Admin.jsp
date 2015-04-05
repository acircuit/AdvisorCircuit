<!DOCTYPE html>
<%@page import="org.AC.dto.MessageDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

   <title>Message</title>  		 
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

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
			String advisorMessage = "";
			String  time = "";
			advisorMessage = (String)request.getAttribute("advisorMessage");
			time = (String)request.getAttribute("time");
			List<MessageDTO> messages = (List<MessageDTO>)request.getAttribute("message");
			pageContext.setAttribute("messages", messages);
		%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        
        	<div class="col-md-3">
				<%@ include file="j-sidebar.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper" class="grey-panel">
        
        	<h1 class="page-header">Send your queries to the Admin here</h1>
            
            <div class="row">
                <div class="chat-panel panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-comments fa-fw"></i>
                        Message
                    </div><!-- /.panel-heading -->
                        
                    <div class="panel-body">
                      	  
                            <ul class="chat">
                            <c:if test="${messages.size() > 0}">
                            <c:forEach items="${messages}" var="message">
                            <c:if test="${message.getAdvisorMessage() != null }">
                                <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                        	<small class="text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i><c:out value="${message.getAdvisorMessageTimeString()}"></c:out>
                                            </small>
                                            <strong class="primary-font">Me</strong> 
                                        </div>
                                        <p>
											<c:out value="${message.getAdvisorMessage()}"></c:out>  
                                       </p>
                                    </div>
                                </li>
                                </c:if>
                                <c:if test="${message.getAdminMessage() != null }">
	                                <li class="right clearfix">
	                                    <span class="chat-img pull-right">
	                                        <i class="glyphicon glyphicon-user red"></i>
	                                    </span>
	                                    <div class="chat-body clearfix">
	                                        <div class="header">
	                                            <small>
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
                               </c:if>
                            </ul>
                            
                        </div>
                    
                    <form action="AdvisorMessageAdminForm" method="post">
	                        <!-- /.panel-body -->
	                        <div class="panel-footer">
	                            <div class="input-group">
	                                <input id="btn-input" type="text" class="form-control input-sm" name="advisormessage" placeholder="Type your message here..." maxlength="350"/>
	                                <span class="input-group-btn">
	                                <input type="submit" class="btn btn-warning" id="btn-chat" value="Send">	                                   
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
            </div><!-- /right area -->
		
        </div><!-- /row -->
		
    </div>
    <!-- /#wrapper -->
    <%@include file="/Footer.jsp" %>
	</div>

     <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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
