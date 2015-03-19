<!DOCTYPE html>
<%@page import="org.AC.dto.HereToHelpDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Users</title>  		 
	
    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    
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
			List<HereToHelpDTO> help = (List<HereToHelpDTO>)request.getAttribute("heretohelp");
			pageContext.setAttribute("help", help);
			out.print(help.size());
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
            	<h1 class="page-header">Review And Recommendation</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
				
                    <div class="table-responsive">
                        <table style="text-align:center" class="table table-bordered">
                                                    <tr><th style="text-align:center">NAME</th><th style="text-align:center">EMAIL</th><th style="text-align:center">OCCUPATION</th><th style="text-align:center">INDUSTRY</th><th style="text-align:center">SERVICE</th><th style="text-align:center">FORM</th></tr>
                                                            <c:forEach items="${help}" var="we">
                                                            <tr><td><p>${we.getName()}</p></td><td><p>${we.getEmail()}</p></td><td><p>${we.getOccupation()}</p></td>
                                                            <td>
                                                            <p>${we.getIndustry()}</p>
                                                                                                                
                                                            </td>
                                                            <td>
                                                            <p>${we.getService()}</p>
                                                            </td>
                                                            <td>
                                                                <a data-toggle="modal" data-target="#form${we.getHelpId()}">Full Details</a>
                                                            </td>
                                                            </tr>
                                                        <div class="modal fade" id="form${we.getHelpId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog modal-lg">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                                                                        <h4 class="modal-title" id="myModalLabel">Here To Help</h4>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                    <h4 style="font-family:'custom_light' !important;color:#555">1. Personal Information</h4>
                                                                    <div class="form-group">
                                                                    <label for="email" class="col-md-3 control-label">Full Name:</label>
                                                                     <div class="col-md-9">
                                                                          <input id ="name" type="text" name="name" class="form-control" value="${we.getName()}" >
                                                                     </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                    <label for="email" class="col-md-3 control-label">Email:</label>
                                                                     <div class="col-md-9">
                                                                          <input id ="email" type="text" name="email" class="form-control" value="${we.getEmail()}">
                                                                     </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                    <label for="phone" class="col-md-3 control-label">Phone:</label>
                                                                     <div class="col-md-9">
                                                                          <input id ="phone" type="text" name="phone" class="form-control" value="${we.getPhone()}">
                                                                     </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                    <label for="occupation" class="col-md-3 control-label">Occupation:</label>
                                                                     <div class="col-md-9">
                                                                          <input id ="occupation" type="text" name="phone" class="form-control" value="${we.getOccupation()}">
                                                                     </div>
                                                                    </div>
                                                                    <h4 style="font-family:'custom_light' !important;color:#555">2.Industry or area in which you need help:</h4>
                                                                     <div class="form-group">
                                                                         <div class="col-md-12">
                                                                              <input id ="industry" type="text" name="industry" class="form-control" value="${we.getIndustry()}">
                                                                         </div>
                                                                    </div>
                                                                    <h4 style="font-family:'custom_light' !important;color:#555">3.Which Service do you want [Visit How It Works page for details]</h4>
                                                                    <div class="form-group">
                                                                     <div class="col-md-12">
                                                                          <input id ="name" type="text" name="name" class="form-control" value="${we.getService()}" >
                                                                     </div>
                                                                    </div>
                                                                    <h4 style="font-family:'custom_light' !important;;color:#555">4.Rank below modes of communication in order of preference </h4>
                                                                    <div class="form-group">
                                                                        <label for="phone" class="col-md-3 control-label">Phone:</label>
                                                                         <div class="col-md-9 ">
                                                                             <input type="text" name="phonemode" class="form-control" id="optionsRadiophone1" value="${we.getPhone_rank() }"/>
                                                                            
                                                                         </div>
                                                                     </div>
                                                                     <div class="form-group">
                                                                         <label for="icode" class="col-md-3 control-label">Email:</label>
                                                                         <div class="col-md-9 ">
                                                                             <input type="text" name="emailmode" class="form-control" id="optionsRadiophone1" value="${we.getEmail_rank() }"/>								                                     
                                                                         </div>
                                                                     </div>
                                                                     <div class="form-group">
                                                                         <label for="icode" class="col-md-3 control-label" >WebChat:</label>
                                                                         <div class="col-md-9 ">
                                                                             <input type="text" name="webchatmode" class="form-control" id="optionsRadiophone1" value="${we.getWebchat_rank() }"/>								                                     
                                                                         </div>
                                                                     </div>
                                                                      <h4 style="font-family:'custom_light' !important;color:#555">5.Query</h4>
                                                                    <div class="form-group">
                                                                        <div class="col-md-12">
                                                                            <textarea class="form-control" style="font-family:'custom_light' !important;" id="query" name="query" rows="3">${we.getQuery()}</textarea>
                                                                        </div>
                                                                    </div>
                                                                    <h4 style="font-family:'custom_light' !important;color:#555">6.Other Details:</h4>
                                                                    <div class="form-group">
                                                                        <div class="col-md-12">
                                                                            <textarea class="form-control" style="font-family:'custom_light' !important;" id="other" name="other" rows="3">${we.getOther()}</textarea>
                                                                        </div>
                                                                    </div>
                                                                    </div>
                                                                </div><!-- /.modal-content -->
                                                            </div><!-- /.modal-dialog -->
                                                        </div><!-- /.modal -->
                                                        </c:forEach>
                                                    </table>
                    </div>
                
				</div>
        
        		<div style="margin-bottom: 80px">
                    <ul class="pagination" style="float: right;">
                        <li id="PageList"></li>
                    </ul>
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
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="assets/js/bootstrap-slider.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
    <script src="assets/js/Pagination.js"></script>
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
