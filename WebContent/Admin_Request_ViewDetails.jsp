<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.AdvisorNewDatesDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>View Details</title>

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

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
						String username =	(String)request.getAttribute("userName");
						String advisorname = (String)request.getAttribute("advisorName");
						List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requestDetails");	
						pageContext.setAttribute("userRequestDetails", userRequestDetails);
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
			<%@ include file="j-sidebar_admin.jsp" %>
			</div>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			<div class="row">    
				<div class="col-lg-8">
					<div id="signupbox" class="mainbox">
							<div class="panel panel-info">
								<div class="panel-heading" style="background-color:#f8f8f8">
									<div class="panel-title" style="font-size:26px">Details</div>   
								</div> 
							</div> 

					<c:forEach items="${userRequestDetails}" var="userRequest">					
						
								<div class="panel-body" >
									<form id="signupform" class="form-horizontal" role="form" action="AdminMyAccountRequestViewDetailsFormController" method="get">
										
										<div id="signupalert" style="display:none" class="alert alert-danger">
											<p>Error:</p>
											<span></span>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Advisor :</label>
											<div class="col-md-9">
												<p class="form-control"><b><%=advisorname %></b></p>
											</div>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Advisor Image:</label>
											<div class="col-md-9">
												<img style="width:60%;padding:3% 1% 1% 5%;float:right"  id="userimage"  src="<%=advisorImage %>" alt="">
											</div>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">User :</label>
											<div class="col-md-9">
												<p class="form-control"><b><%=username %></b></p>
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
												<p class="form-control"><b><c:out value="${userRequest.getService()}"/></b></p>
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
													<p class="form-control"><b><c:out value="${userRequest.getMode()}"/></b></p>
												</div>
										</div>				
										 
										<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Duration</label>
												<div class="col-md-9">
													<p class="form-control"><b><c:out value="${userRequest.getDuration()}"/></b></p>	
												</div>
										</div>
										<input type="hidden" name="rId" value="${userRequest.getRequestId()}">
										<c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
											<h4><a href="DownloadFileServlet?rid=${userRequest.getRequestId()}">CLICK TO DOWNLOAD <%=username %>'s CV</a></h4>
										</c:if>	
										<div style="height:10px"></div>
										<h3>Date and Time Submitted</h3>
										<hr>
										
										<div class="form-group">
													
										  <div class="col-md-6" >
											<div class="radio">
                                                <label>
                                                    <c:out value="${userRequest.getTime1()}"></c:out>
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <c:out value="${userRequest.getTime2()}"></c:out>
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <c:out value="${userRequest.getTime3()}"></c:out>
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <c:out value="${userRequest.getTime4()}"></c:out>
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <c:out value="${userRequest.getTime5()}"></c:out>
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <c:out value="${userRequest.getTime6()}"></c:out>
                                                </label>
                                            </div>
										  </div>	
										</div>
										<div class="form-group">
											<!-- Button -->                     
											<div class="row">
												<c:if test="${userRequest.getStatus().equals('PENDING FOR ADMIN APPROVAL') }">
													<div class="col-md-6" style="text-align:right">
														<button id="btn" type="submit" class="btn btn-info">Approve</button>
													</div>
													<div class="col-md-6">
													<c:url value="/AdminMyAccountRequestViewDetailsFormController" var="myURL">
									   						<c:param name="rId" value="${userRequest.getRequestId()}"/>
									   						<c:param name="cancel" value="true"/>
													</c:url>
														<a href="${myURL}" class="btn btn-info">Cancel</a>
													</div>
												</c:if>
											</div>
										</div>
										</c:forEach>	
									</form>
								 </div>
							</div> 
				 </div> 
			</div>

			
        </div>    
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

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
    	alert("hello");	
    	var img1=document.getElementById('userimage');
    	var val = document.getElementById('imagesource').value;
    	alert(val);
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

</body>

</html>
