<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.AdvisorNewDatesDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Request View Detail</title>  		 

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
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
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
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
												
						List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requestDetails");
						List<SessionDTO> sessionDetail = (List<SessionDTO>)request.getAttribute("sessionDetail");	
						List<AdvisorNewDatesDTO> advisorNewDates = (List<AdvisorNewDatesDTO>)request.getAttribute("advisorNewDates");	
						
						pageContext.setAttribute("userRequestDetails", userRequestDetails);
						pageContext.setAttribute("sessionDetail", sessionDetail);
						pageContext.setAttribute("advisorNewDates", advisorNewDates);

	%>
</head>

<body>
	<div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper">
		
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
                                    <label for="icode" class="col-md-2 control-label">Advisor Name :</label>
                                    <div class="col-md-10">
                                    	<span class="form-control"><a href="${advisorId}"><%=advisorname %></a></span>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">Advisor Image:</label>
                                    <div class="col-md-10">
                                        <a href="${advisorId}">											
                                            <img style="width:100px; float:left;" id="userimage"  src="<%=advisorImage %>" alt="">
                                        </a>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">User Name:</label>
                                    <div class="col-md-10">
                                        <span class="form-control"><%=userName.toUpperCase() %></span>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">User Image :</label>
                                    <div class="col-md-10">
                                        <img style="width:100px; float:left;" id="userimage"  src="<%=userImage %>" alt="">
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
                                        <span class="form-control"><c:out value="${userRequest.getMode()}"/></span>
                                    </div>
                                </div>				
                                         
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label">Duration (Hrs):</label>
                                    <div class="col-md-10">
                                        <span class="form-control"><c:out value="${userRequest.getDuration()}"/></span>	
                                    </div>
                                </div>
                                
                                <input type="hidden" name="rId" value="${userRequest.getRequestId()}">
                                
                                <c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">										
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label"></label>
                                    <div class="col-md-10">
                                        <h4><a href="DownloadFile?rid=${userRequest.getRequestId()}">CLICK TO DOWNLOAD <%=userName.toUpperCase() %>'s CV</a></h4>
                                    </div>
                                </div>
                                </c:if>
                                
                                <div style="height:10px"></div>
                                
                                    <c:choose>
                                    <c:when test="${userRequest.getMode().equals('email')}">
                                        <h3>Date and Time Submitted By User:</h3>
                                        <hr>
                                        <div class="form-group">
                                        <div class="col-md-6" >
                                              <ol>
                                                    <li>
                                                        <div>
                                                            <label>
                                                               <c:out value="${userRequest.getTimeString1()}"></c:out>
                                                            </label>
                                                             <input value="${userRequest.getTimeString1()}" type="hidden" name="emaildate">
                                                        </div>
                                                    </li>
                                                </ol>
                                        </div>	
                                        </div>
                                        
                                        <hr>
                                    </c:when>
                                    <c:otherwise>
                                            
                                    <h3>Date and Time Submitted</h3>
                                    <hr>
                                        
                                    <div class="form-group">
                                      <div class="col-md-6" >
                                      <ol>
                                        <li>
                                            <div >
                                                <label>
                                                    <c:out value="${userRequest.getTimeString1()}"></c:out>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <div >
                                                <label>
                                                    <c:out value="${userRequest.getTimeString2()}"></c:out>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <div >
                                                <label>
                                                    <c:out value="${userRequest.getTimeString3()}"></c:out>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <div >
                                                <label>
                                                    <c:out value="${userRequest.getTimeString4()}"></c:out>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <div >
                                                <label>
                                                    <c:out value="${userRequest.getTimeString5()}"></c:out>
                                                </label>
                                            </div>
                                        </li>
                                        <li>
                                            <div >
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
                                                <div class="form-group">
                                                        <label for="icode" class="col-md-2 control-label">Session Plan</label>
                                                    <div class="col-md-10">
                                                        <textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
                                                    </div>
                                                </div>
                                                <c:if test="${session.getStatus().equals('PENDING FOR USER PAYMENT') }">
                                                    <div class="form-group">
                                                            <label for="icode" class="col-md-2 control-label">Accepted Date</label>
                                                        <div class="col-md-10">
                                                            <span class="form-control"><c:out value="${session.getAcceptedDateString()}"/></span>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                
                                                <c:if test="${session.getStatus().equals('PENDING FOR USER PAYMENT WITH NEW DATES') &&  advisorNewDates.size() > 0}">
                                                <h4>New Dates Provided By Advisor</h4>
                                                    <c:forEach var="dates" items="${advisorNewDates}">
                                                        <div class="col-md-6" >
                                                            <div >
                                                                <label>
                                                                    <c:out value="${dates.getNewDateString1()}"></c:out>
                                                                </label>
                                                            </div>
                                                            <div >
                                                                <label>
                                                                    <c:out value="${dates.getNewDateString2()}"></c:out>
                                                                </label>
                                                            </div>
                                                             <div >
                                                                <label>
                                                                    <c:out value="${dates.getNewDateString3()}"></c:out>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                                
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-md-9" style="text-align: center;margin-top: 20px;margin-left: 30px">
                                                            <c:url value="AdminSetNoUserPayment" var="myURL">
                                                                    <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                                    <c:param name="sId" value="${session.getSessionId()}"></c:param>
                                                            </c:url>
                                                                <a href="${myURL}" class="btn btn-info">SET SESSION CANCELLED DUE NO USER PAYMENT </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                            </c:forEach>
                                            </c:if>
                                            
                                        <div class="form-group">
                                            <!-- Button -->                     
                                            <div class="row">
                                                <c:if test="${userRequest.getStatus().equals('PENDING FOR ADMIN APPROVAL') }">
                                                    <div class="col-md-12 text-center">
                                                        <button id="btn" type="submit" class="btn btn-info">Approve</button>
                                                        <c:url value="AdminRequestViewDetailForm" var="myURL">
                                                            <c:param name="rId" value="${userRequest.getRequestId()}"/>
                                                            <c:param name="cancel" value="true"/>
                                                    	</c:url>
                                                        <a href="${myURL}" class="btn btn-info">Reject</a>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
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
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
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

</body>

</html>
