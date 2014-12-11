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

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="assets/plugins/timeline.css" rel="stylesheet">

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

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="dashboard.html"><b style="font-size:30px">Advisor </b><b style="font-size:30px;color:#000;font-family: Arial">Circuit</b></a>
            </div>
            <!-- /.navbar-header -->

            <div id="topnav">
			<%@ include file="topnav.jsp" %>            
			</div>

			<div id="j_sidebar">
			<%@ include file="j-sidebar.jsp" %>
			</div>
        </nav>
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Chat</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                    <div class="chat-panel panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>
                            Chat
						</div>
                        <!-- /.panel-heading -->
                        
                        <div class="panel-body">
                      	  
                            <ul class="chat">
                            <c:if test="${messages.size() > 0}">
                            <c:forEach items="${messages}" var="message">
                                <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font">Me</strong> 
                                            <small class="pull-right text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i><c:out value="${message.getAdvisorMessageTimeString()}"></c:out>
                                            </small>
                                        </div>
                                        <p>
											<c:out value="${message.getAdvisorMessage()}"></c:out>  
                                       </p>
                                    </div>
                                </li>
                                <c:if test="${message.getAdminMessage() != null }">
	                                <li class="right clearfix">
	                                    <span class="chat-img pull-right">
	                                        <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar" class="img-circle" />
	                                    </span>
	                                    <div class="chat-body clearfix">
	                                        <div class="header">
	                                            <small class=" text-muted">
	                                                <i class="fa fa-clock-o fa-fw"></i> <c:out value="${message.getAdminMessageTimeString()}"></c:out></small>
	                                            <strong class="pull-right primary-font">Admin</strong>
	                                        </div>
	                                        <p>
												<c:out value="${message.getAdminMessage()}"></c:out>	  
											</p>
	                                    </div>
	                                </li>
                                </c:if>
                               </c:forEach>
                               </c:if>
                            </ul>
                            
                        </div>
                        
                        <form action="AdvisorMyAccountMessageAdminFormController" method="post">
	                        <!-- /.panel-body -->
	                        <div class="panel-footer">
	                            <div class="input-group">
	                                <input id="btn-input" type="text" class="form-control input-sm" name="advisormessage" placeholder="Type your message here..." />
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
    <!-- /#wrapper -->

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
