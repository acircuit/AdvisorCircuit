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
	<%	      			String advisorname = (String)request.getAttribute("advisorname");
						String username = (String)request.getAttribute("userName");
						String image = (String)request.getAttribute("image");
						String uId = (String)request.getAttribute("uId");
						String rId =request.getParameter("rId");
						List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("list");
						List<SessionDTO> sessionDetails = (List<SessionDTO>)request.getAttribute("sessionDetails");
						List<AdvisorNewDatesDTO> newDate = (List<AdvisorNewDatesDTO>)request.getAttribute("newDate");
						pageContext.setAttribute("userRequestDetails", userRequestDetails);
						pageContext.setAttribute("sessionDetails", sessionDetails);
						pageContext.setAttribute("newDate", newDate);
						pageContext.setAttribute("image", image);
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
                    <h1 class="page-header"><%=username %></h1>
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
								
					<div class="col-md-5">
						<img style="width:60%;padding:3% 1% 1% 5%;float:right"  id="userimage"  src="<%=image %>" alt="32321">
					</div>
					<c:forEach items="${userRequestDetails}" var="userRequest">
						<div class="col-md-7" style="text-align:left">
							<h3>With <%=advisorname%></h3>
							<p>SERVICE : <b><c:out value="${userRequest.getService()}"/></b></p>
						</div>
						<div style="height:170px"></div>
						
								<div class="panel-body" >
									<form id="signupform" class="form-horizontal" role="form" action="AdvisorMyAccountRequestViewDetailsFormController" method="post">
										
										<div id="signupalert" style="display:none" class="alert alert-danger">
											<p>Error:</p>
											<span></span>
										</div>
										<input type="hidden" value="<%=uId%>" name="uId">	
										<input type="hidden" value="<%=rId%>" name="rId">	
										<c:if test="${sessionDetails.size() > 0}">
											<c:forEach items="${sessionDetails}" var="session">
												<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Session Plan :</label>
													<div class="col-md-9">
														<textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
													</div>
												</div>
												<input type="hidden" value="${session.getAcceptedDate() }" name="acceptedDate">
												<c:if test="${session.getAcceptedDate() != null}">
													<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Accepted Date:</label>
														<div class="col-md-9">	
															<p class="form-control"><b><c:out value="${session.getAcceptedDate()}"></c:out></b></p>
														</div>
													</div>		
												</c:if>
											</c:forEach>
										</c:if>	
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Query :</label>
											<div class="col-md-9">
												<textarea class="form-control" rows="3" readonly><c:out value="${userRequest.getQuery()}"/></textarea>
											</div>
										</div>
										  
										<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Mode:</label>
												<div class="col-md-9">	
													<input type="text" style="width: 200px" name="optionsRadiosInlinem" id="optionsRadiosInlinem1" value="${userRequest.getMode()}" readonly>
												</div>
										</div>				
										 
										<div class="form-group">
													<label for="icode" class="col-md-3 control-label">Duration</label>
												<div class="col-md-9">	
													<input type="text" style="width: 200px" name="optionsRadiosInlinem" id="optionsRadiosInlinem1" value="${userRequest.getDuration()}" readonly>
												</div>
										</div>
										<c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
											<h4><a href="DownloadFileServlet?rid=<%=rId %>">CLICK TO DOWNLOAD <%=username %>'s CV</a></h4>
										</c:if>	
										<c:if test="${userRequest.getStatus().equals('PENDING FOR ADVISOR APPROVAL') }">
										<div style="height:10px"></div>
										<h3>Choose from Date and Time Submitted</h3>
										<hr>
										
											<div class="form-group">
														
														<div class="col-md-6" >
														<div class="radio">
	                                                <label>
	                                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="${userRequest.getTime1()}" ><c:out value="${userRequest.getTime1()}"></c:out>
	                                                </label>
	                                            </div>
	                                            <div class="radio">
	                                                <label>
	                                                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="${userRequest.getTime2()}"><c:out value="${userRequest.getTime2()}"></c:out>
	                                                </label>
	                                            </div>
	                                            <div class="radio">
	                                                <label>
	                                                    <input type="radio" name="optionsRadios" id="optionsRadios3" value="${userRequest.getTime3()}"><c:out value="${userRequest.getTime3()}"></c:out>
	                                                </label>
	                                            </div>
													</div>	
											</div>								
											<div style="height:10px"></div>
											<div>
											<p>Not happy with any date of time ?</p>
											<h4><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
											Click to Choose your Date and Time</a>
											</h4>
											<hr>
											</div>
											<div id="collapseOne" class="panel-collapse collapse">
												 <div class="form-group">
														<label for="icode" class="col-md-3 control-label">Slot 1</label>
														<div id="datetimepicker1" class="input-append date">
														  <input type="datetime" name="datetimepicker1"></input>
														  <span class="add-on">
															<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
														  </span>
														</div>
												</div>
												
												<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Slot 2</label>
														<div id="datetimepicker2" class="input-append date">
														  <input type="datetime" name = "datetimepicker2"></input>
														  <span class="add-on">
															<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
														  </span>
														</div>
												</div>
												<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Slot 3</label>
														<div id="datetimepicker3" class="input-append date">
														  <input type="datetime" name = "datetimepicker3"></input>
														  <span class="add-on">
															<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
														  </span>
														</div>
												</div>
											</div>
											
											<h4>PlEASE WRITE DOWN YOUR OWN SESSION PLAN</h4>
											<hr>
											<div class="form-group">
												
												<div class="col-md-12">
													<textarea class="form-control" rows="4" name="sessionplan"></textarea>
												</div>
											</div>
										</c:if>
										<c:if test="${newDate.size() > 0 && acceptedDate == null}">
											<c:forEach items="${newDate}" var="date">
											<h4>Date And Time Submitted by the Advisor</h4>
												<div class="form-group">
															
													<div class="col-md-6" >
															<div class="radio">
		                                                <label>
		                                                    <p><c:out value="${date.getNewDate1()}"></c:out></p>
		                                                </label>
		                                            </div>
		                                            <div class="radio">
		                                                <label>
		                                                    <p><c:out value="${date.getNewDate2()}"></c:out></p>
		                                                </label>
		                                            </div>
		                                            <div class="radio">
		                                                <label>
		                                                    <p><c:out value="${date.getNewDate3()}"></c:out></p>
		                                                </label>
		                                            </div>
													</div>	
												</div>
											</c:forEach>			
										</c:if>	
										<div class="form-group">
											<!-- Button -->                     
											<div class="row">
												<c:if test="${userRequest.getStatus().equals('PENDING FOR ADVISOR APPROVAL') }">
													<div class="col-md-6" style="text-align:right">
														<button id="btn" type="submit" class="btn btn-info">Reply</button>
													</div>
													<div class="col-md-6">
													<c:url value="/AdvisorMyAccountRequestViewDetailsFormController" var="myURL">
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
