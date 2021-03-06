<!DOCTYPE html>
<%@page import="org.AC.dto.ContactUsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
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
    <title>Message Admin</title>  		 
	
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
    <style type="text/css">
    	table {
		  table-layout: fixed;
		  width: 120px; /* Important */
		  
		}
		td,th {
		  width: 30px;
		  word-wrap: break-word;
		}
    </style>	
<%
			List<ContactUsDTO> contactList= (List<ContactUsDTO>)request.getAttribute("contactList");
   			pageContext.setAttribute("contactList", contactList);
%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
    	<div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-6">
            	<h4></h4>
            	<h1 class="page-header">Contact Us</h1>
            </div>
            <div class="col-md-3">
				<input type="text" id="search" style="float: right;" placeholder="SEARCH" onkeyup="searchTable(this.value)"/>
			</div>
        </div>
    	<div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
			
                    <div class="table-responsive">
                        <table style="text-align: center" class="table table-bordered" id="tblData">
                            <tr>
                                <th style="text-align: center">Name</th>
                                <th style="text-align: center">Email</th>
                                <th style="text-align: center">Phone</th>
                                <th style="text-align: center">Message</th>
                            </tr>
                            <c:forEach var="contact" items="${contactList}">
                                <tr>
                                    <td>${contact.getName()}</td>
                                    <td>${contact.getEmail()}</td>
                                    <td>${contact.getPhone()}</td>
                                    <td>
                                        <p>
                                            <a data-toggle="modal" data-target="#contact${contact.getId()}">User
                                                Message</a>
                                        </p>
                                    </td>
                                </tr>
                                <div class="modal fade" id="contact${contact.getId()}" tabindex="-1"
                                    role="dialog" aria-labelledby="myModalLabel"
                                    aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">�</button>
                                                <h4 class="modal-title" id="myModalLabel">User Message</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>${contact.getMessage()}</p>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                                <!-- /.modal -->
                            </c:forEach>
                        </table>
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
    	<script src="assets/js/gridSearch.js"></script>
    
</body>

</html>
