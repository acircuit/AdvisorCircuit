<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.AdvisorNewDatesDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <title>Session View Details</title>
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
		 p{font-family:'custom_light' !important;}
		 div{font-family:'custom_light' !important;}
		.fa-lg{font-size:1.4em !important;}
		.social{padding-bottom:9px !important;}
		.foot-size{font-size:14px !important;}
		.foot-color{color:#717171;}
		.gray{color:#575758;}
		@media only screen and (max-width:700px){
		
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		}
		 </style>  		 

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">
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
						String userName =	(String)request.getAttribute("userName");
						String path =	(String)request.getAttribute("path");
						String advisorname = (String)request.getAttribute("advisorName");
						String isSessionPast = (String)request.getAttribute("isSessionPast");
						List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requestDetails");
						List<SessionDTO> sessionDetail = (List<SessionDTO>)request.getAttribute("sessionDetail");	
						List<AdvisorNewDatesDTO> advisorNewDates = (List<AdvisorNewDatesDTO>)request.getAttribute("advisorNewDates");	
						pageContext.setAttribute("userRequestDetails", userRequestDetails);
						pageContext.setAttribute("sessionDetail", sessionDetail);
						pageContext.setAttribute("path", path);
						out.println(path);
	%>
</head>

<body>

    <div style="margin-left:5.9%;margin-right:5.9%">
	<%@include file="/Header.jsp" %>
    <div id="wrapper">


        <div id="page-wrapper" style="background-color:#E5E5E5; margin-left:0px">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			<div class="row">    
				<div class="col-lg-8">
					<div class="mainbox">
							<div class="panel panel-info">
								<div class="panel-heading" style="background-color:#FFFFFF">
									<div class="panel-title" style="font-size:26px"> Session Details</div>   
								</div> 
							</div> 

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
											<label for="icode" class="col-md-3 control-label">Advisor :</label>
											<a href="${advisorId}">
												<div class="col-md-9">
													<p class="form-control"><b><%=advisorname %></b></p>
												</div>
											</a>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Advisor Image:</label>
											<a href="${advisorId}">											
												<div class="col-md-9">
													<img style="width:60%;padding:3% 1% 1% 5%;float:right"  id="userimage"  src="<%=advisorImage %>" alt="">
												</div>
											</a>
										</div>
										<div class="form-group">
											<label for="icode" class="col-md-3 control-label">Userwqdqw :</label>
											<div class="col-md-9">
												<p class="form-control"><b><%=userName.toUpperCase() %></b></p>
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
												<c:if test="${userRequest.getService().equals('careertalk')}">
												<p class="form-control"><b>Career Talk</b></p>
												</c:if>
												<c:if test="${userRequest.getService().equals('mockinterview')}">
												<p class="form-control"><b>Mock Interview</b></p>
												</c:if>
												<c:if test="${userRequest.getService().equals('cvcritique')}">
												<p class="form-control"><b>Resume Critique</b></p>
												</c:if>
												<c:if test="${userRequest.getService().equals('personalworkshops')}">
												<p class="form-control"><b>Personal Workshop</b></p>
												</c:if>
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
													<p class="form-control"><b><c:out value="${fn:toUpperCase(userRequest.getMode())}"/></b></p>
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
											<h4><a href="DownloadFile?rid=${userRequest.getRequestId()}">CLICK TO DOWNLOAD <%=userName %>'s CV</a></h4>
										</c:if>
										<c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
											<h4><a href="WordFileOpener?path=<%=path%>" target="blank">${fn:toUpperCase(request.getService())} FeedBack Form </a></h4>
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
									<c:if test="${sessionDetail.size() > 0}">
										<c:forEach var="session" items="${sessionDetail}">
											<h3>Session Details</h3>
											<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Session ID</label>
													<div class="col-md-9">
														<p class="form-control"><b><c:out value="${session.getSessionId()}"/></b></p>
													</div>
												</div>
												<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Session Plan</label>
													<div class="col-md-9">
														<textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
													</div>
												</div>
												<div class="form-group">
														<label for="icode" class="col-md-3 control-label">Accepted Date</label>
													<div class="col-md-9">
														<p class="form-control"><b><c:out value="${session.getAcceptedDate()}"/></b></p>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</form>
								</div>
							</c:forEach>	
						 </div>
					</div> 
				 </div> 
			</div>

			
        </div> 
            <!-- /#wrapper -->
        <%@include file="/Footer.jsp" %>   
        </div>
	<!-- /#container -->

   
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
