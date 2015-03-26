<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.SessionDTO"%>
<%@page import="org.AC.dto.AdvisorNewDatesDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Requests-View Details</title>  		 

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
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
	
	<%	      			String advisorname = (String)request.getAttribute("advisorname");
						String userName = (String)request.getAttribute("userName");
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

   	<div class="container">
   	<%@include file="/Header.jsp"%>
    
    <div id="wrapper" class="content">
        
        <div class="row">
        
        	<div class="col-md-3">
				<%@ include file="j-sidebar.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<c:forEach items="${userRequestDetails}" var="userRequest">
        			<div id="page-wrapper" class="grey-panel">
            			
                    <c:if test="${userRequest.getService().equals('careertalk')}">
                        <h1 class="page-header text-career">Career Talk</h1>
                    </c:if>
                    <c:if test="${userRequest.getService().equals('mockinterview')}">
                        <h1 class="page-header text-Mock">Mock Interview</h1>
                    </c:if>
                    <c:if test="${userRequest.getService().equals('cvcritique')}">
                        <h1 class="page-header text-resume">Resume Critique</h1>
                    </c:if>
                    <c:if test="${userRequest.getService().equals('personalworkshops')}">
                        <h1 class="page-header text-personal">Personal Workshop</h1>
                    </c:if>
                    <h3 class="ses-detail">Session Details</h3>

					<input type="hidden" id="service" value="${userRequest.getService()}">						
                    
					<div class="panel-body" >
						<form id="signupform" class="form-horizontal" role="form" action="AdvisorRequestViewDetailForm" method="post">
										
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
										
                            <div class="form-group">
                                <label for="icode" class="col-md-2 control-label">User :</label>
                                <div class="col-md-10">
                                    <img style="width:100px; float:left;" id="userimage"  src="<%=image %>" alt="32321">
                                    <h2 class="name"><%=userName.toUpperCase()%></h2>
                                </div>
                            </div>
                            
                            <input type="hidden" value="<%=uId%>" name="uId">	
                            <input type="hidden" value="<%=rId%>" name="rId">	
										
                            <c:if test="${sessionDetails.size() > 0}">
								<c:forEach items="${sessionDetails}" var="session">
                                    
                                    <div class="form-group">
                                        <label for="icode" class="col-md-2 control-label">Session Plan :</label>
                                        <div class="col-md-10">
                                            <textarea class="form-control" rows="3" readonly><c:out value="${session.getSessionPlan()}"/></textarea>
                                        </div>
                                    </div>
									
                                    <input type="hidden" value="${session.getAcceptedDate() }" name="acceptedDate">
                                    <c:if test="${session.getAcceptedDate() != null}">
                                        <div class="form-group">
                                        <label for="icode" class="col-md-2 control-label">Accepted Date:</label>
                                            <div class="col-md-10">	
                                                <p class="form-control"><c:out value="${session.getAcceptedDateString()}"></c:out></p>
                                            </div>
                                        </div>		
                                    </c:if>
								</c:forEach>
							</c:if>	
                            
                            <div class="form-group">
                                <label for="icode" class="col-md-2 control-label">Query :</label>
                                <div class="col-md-10">
                                    <textarea class="form-control" rows="3" readonly><c:out value="${userRequest.getQuery()}"/></textarea>
                                </div>
                            </div>
										  
                            <div class="form-group">
                            	<label for="icode" class="col-md-2 control-label">Mode:</label>
                                <div class="col-md-10">
                                    <c:if test="${userRequest.getMode().equals('phone')}">
                                		<p>PHONE</p>	
                               		</c:if>
                                    <c:if test="${userRequest.getMode().equals('email')}">
                                    	<p>EMAIL</p>	
                                    </c:if>
                                    <c:if test="${userRequest.getMode().equals('webchat')}">
                                    	<p>WEBCHAT</p>	
                                    </c:if>
                               </div>
                            </div>				
								<input type="hidden" value="${userRequest.getMode()}" id="requestModes">		 
                            <div class="form-group">
                            	<label for="icode" class="col-md-2 control-label">Duration (Hrs):</label>
                                <div class="col-md-10">	
                                    <p name="optionsRadiosInlinem" id="optionsRadiosInlinem1">${userRequest.getDuration()}</p>	
                                </div>
                            </div>
                            <div class="form-group">
                            	<label for="icode" class="col-md-2 control-label">Amount (Rs):</label>
                                <div class="col-md-10">	
                                    <p name="optionsRadiosInlinem" id="optionsRadiosInlinem1">${userRequest.getAmount()}</p>	
                                </div>
                            </div>
                            
                            <c:if test="${userRequest.getService().equals('mockinterview') || userRequest.getService().equals('cvcritique')}">
                                <div class="col-md-12" style="padding-left: 0px">
                                	<a  href="DownloadFile?rid=<%=rId %>" class="btn btn-info">CLICK TO DOWNLOAD <%=userName.toUpperCase() %>'s CV</a>
                                	<div style="height:10px"></div>
                                </div>
                            </c:if>	
										<c:if test="${userRequest.getStatus().equals('PENDING FOR ADVISOR APPROVAL') }">
										<div style="height:10px"></div>
										<c:choose>
											<c:when test="${userRequest.getMode().equals('email')}">
												<h3>Date and Time Submitted:</h3>
												<hr>
												<div class="form-group">
												<div class="col-md-6" >
													  <ol>
													  		<li>
																<div class="radio">
					                                                <label>
					                                                   <c:out value="${userRequest.getTimeString1()}"></c:out>
					                                                </label>
					                                                 <input value="${userRequest.getTimeString1()}" type="hidden" name="emaildate">
					                                            </div>
				                                            </li>
			                                            </ol>
												  </div>	
												</div>
												<div style="height:10px"></div>
												<div>
												<h4><a id = "acc" class="accordion-toggle"   data-toggle="collapse" data-parent="#accordion" href="#collapseEmail">
												Not happy with the above date? Click to choose your own</a>
												</h4>
												<hr>
												</div>	
											</c:when>
											<c:otherwise>
												<h3>Choose from Date and Time Submitted</h3>
												<hr>
												<div class="form-group">
															
															<div class="col-md-6" >
															<div class="radio">
		                                                <label>
		                                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="${userRequest.getTime1()}" ><c:out value="${userRequest.getTimeString1()}"></c:out>
		                                                </label>
		                                            </div>
		                                            <div class="radio">
		                                                <label>
		                                                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="${userRequest.getTime2()}"><c:out value="${userRequest.getTimeString2()}"></c:out>
		                                                </label>
		                                            </div>
		                                            <div class="radio">
		                                                <label>
		                                                    <input type="radio" name="optionsRadios" id="optionsRadios3" value="${userRequest.getTime3()}"><c:out value="${userRequest.getTimeString3()}"></c:out>
		                                                </label>
		                                            </div>
		                                             <div class="radio">
		                                                <label>
		                                                    <input type="radio" name="optionsRadios" id="optionsRadios4" value="${userRequest.getTime4()}"><c:out value="${userRequest.getTimeString4()}"></c:out>
		                                                </label>
		                                            </div>
		                                             <div class="radio">
		                                                <label>
		                                                    <input type="radio" name="optionsRadios" id="optionsRadios5" value="${userRequest.getTime5()}"><c:out value="${userRequest.getTimeString5()}"></c:out>
		                                                </label>
		                                            </div>
		                                             <div class="radio">
		                                                <label>
		                                                    <input type="radio" name="optionsRadios" id="optionsRadios6" value="${userRequest.getTime6()}"><c:out value="${userRequest.getTimeString6()}"></c:out>
		                                                </label>
		                                            </div>
														</div>	
												</div>	
												<div style="height:10px"></div>
												<div>
												<h4><a id = "acc" class="accordion-toggle" onclick="uncheckdates()"  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
												Not happy with any of the above date and time slots? Click to choose your own</a>
												</h4>
												<hr>
												</div>							
											
											</c:otherwise>
										
										</c:choose>
										
											
											<div id="collapseOne" class="panel-collapse collapse">
												 <div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">Slot 1</label>
                                                    <div id="datetimepicker1" class="input-append date col-md-10">
                                                      <input id="datepicker1" type="datetime" name="datetimepicker1" style="float:left;" readonly="readonly"></input>
                                                      <span class="add-on" style="float:left;">
                                                        <i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
                                                      </span>
                                                    </div>
												</div>
												
												<div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">Slot 2</label>
                                                    <div id="datetimepicker2" class="input-append date col-md-10">
                                                      <input id="datepicker2" type="datetime" name="datetimepicker2" style="float:left;" readonly="readonly"></input>
                                                      <span class="add-on" style="float:left;">
                                                        <i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
                                                      </span>
                                                    </div>
												</div>
                                                
												<div class="form-group">
                                                    <label for="icode" class="col-md-2 control-label">Slot 3</label>
                                                    <div id="datetimepicker3" class="input-append date col-md-10">
                                                      <input id="datepicker3" type="datetime" name="datetimepicker3" style="float:left;" readonly="readonly"></input>
                                                      <span class="add-on" style="float:left;">
                                                        <i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
                                                      </span>
                                                    </div>
												</div>
                                                
												<hr>
                                                
											</div>
                                            
											<div id="collapseEmail" class="panel-collapse collapse">
												<div class="form-group">
												<label for="icode" style="font-family:'custom_light' !important;" class="col-md-2 control-label">Slot 1</label>
												<div id="datetimepickeremail" class="input-append date col-md-10">
												  <input type="datetime" style="font-family:'custom_light' !important;" id="datepickeremail" name="datetimepickeremail" data-provide="datepicker"  class="datepicker" readonly="readonly"></input>
												  <span class="add-on">
													<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
												  </span>
												</div>
												</div>
											</div>
											<h4>PlEASE WRITE YOUR SESSION PLAN FOR THE USER :</h4>
											<div class="form-group">
												<div class="col-md-12">
													<textarea class="form-control" id="sessionplan" rows="4" name="sessionplan" placeholder="Write Session Plan" style="width:90%;" maxlength="800"></textarea>
												</div>
											</div>
											<hr>
										</c:if>
										<c:if test="${newDate.size() > 0 && acceptedDate == null}">
											<c:forEach items="${newDate}" var="date">
											<h4>Date And Time Submitted by the Advisor</h4>
												<div class="form-group">
															
													<div class="col-md-6" >
															<div >
		                                                <label>
		                                                    <c:out value="${date.getNewDateString1()}"></c:out>
		                                                </label>
		                                            </div>
		                                            <div >
		                                                <label>
		                                                    <c:out value="${date.getNewDateString2()}"></c:out>
		                                                </label>
		                                            </div>
		                                            <div >
		                                                <label>
		                                                    <c:out value="${date.getNewDateString3()}"></c:out>
		                                                </label>
		                                            </div>
													</div>	
												</div>
											</c:forEach>			
										</c:if>	
										<div class="form-group">
											<!-- Button -->                     
											<div class="row text-center">
												<c:if test="${userRequest.getStatus().equals('PENDING FOR ADVISOR APPROVAL') }">
													<button id="btn" type="submit" class="btn btn-info margin-10">Accept Request</button>
													<c:url value="AdvisorRequestViewDetailForm" var="myURL">
									   						<c:param name="rId" value="${userRequest.getRequestId()}"/>
									   						<c:param name="cancel" value="true"/>
													</c:url>
													<a href="${myURL}" class="btn btn-info margin-10">Decline Request</a>
												</c:if>
											</div>
										</div>
										</form>
					</div>
					
                    </div>
				</c:forEach>
            </div><!-- /right area -->
            
            
       </div><!-- /row -->

	</div>    
    <!-- /#page-wrapper -->
    
	<%@include file="/Footer.jsp" %>
	</div>
    <!-- /#wrapper -->

    <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="assets/js/moment.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap3-datetimepicker.js"></script>
    <script type="text/javascript">
	    function uncheckdates(){
			var active = $('#collapseOne').is(':visible');
			if(!active){
				$( '#optionsRadios1').attr('checked', false);
				$( '#optionsRadios2' ).attr('checked', false);
				$( '#optionsRadios3' ).attr('checked', false);
				$( '#optionsRadios4' ).attr('checked', false);
				$( '#optionsRadios5' ).attr('checked', false);
				$( '#optionsRadios6' ).attr('checked', false);
		
			}	
	    }
    	function setImageSrc(){
    	var img1=document.getElementById('userimage');
    	var val = document.getElementById('imagesource').value;
    	img1.src=val;
    	}
      $('#datetimepicker1').datetimepicker({
    	 pick12HourFormat: true,
      	useCurrent:false
      	});
	  $('#datetimepicker2').datetimepicker({
		  pick12HourFormat: true,
	    	useCurrent:false
	   });
	  $('#datetimepicker3').datetimepicker({
		  pick12HourFormat: true,
	    	useCurrent:false
	   });
	  $('#datetimepickeremail').datetimepicker({
	    	pick12HourFormat: false,
	    	useCurrent:false,
	    	 pickTime: false
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
	 <script type="text/javascript">
	$(document).ready(function() {	
		var value = document.getElementById('service').value;
		if(value == "cvcritique"){			
			var dt = new Date();
			dt.setHours(0,0,0);
			dt.setDate(dt.getDate() + 2);
			$('#datetimepicker1').data("DateTimePicker").setMinDate(dt);
			$('#datetimepicker2').data("DateTimePicker").setMinDate(dt);
			$('#datetimepicker3').data("DateTimePicker").setMinDate(dt);
		}else{
			var dt = new Date();
			dt.setDate(dt.getDate() + 1);
			dt.setHours(0,0,0);
			$('#datetimepicker1').data("DateTimePicker").setMinDate(dt);
			$('#datetimepicker2').data("DateTimePicker").setMinDate(dt);
			$('#datetimepicker3').data("DateTimePicker").setMinDate(dt);
		}
		
		var dt = new Date();
		var dt1 = new Date();
		dt1.setDate(dt.getDate() + 21);
		$('#datetimepickeremail').data("DateTimePicker").setMinDate(dt);
		$('#datetimepickeremail').data("DateTimePicker").setMaxDate(dt1);
		
		$("#btn").click(function(event){
			if($("#optionsRadios1").is(':checked') == false && $("#optionsRadios2").is(':checked') == false && $("#optionsRadios3").is(':checked') == false &&  $("#optionsRadios4").is(':checked') == false  && $("#optionsRadios5").is(':checked') == false  && $("#optionsRadios6").is(':checked') == false ){			
				var value1 = $("#datepicker1").val();
				var value2 = $("#datepicker2").val();
				var value3 = $("#datepicker3").val();
				if((value1 == "" || value2 == "" || value3 == "" ) && $('#datepicker1').is(':visible') && $('#datepicker2').is(':visible') && $('#datepicker3').is(':visible')){
					alert("Please accept a date or  enter a time slot");
					event.preventDefault();
				}else if ((value1 == value2 || value1 == value3 || value2 == value3) && $('#datepicker1').is(':visible') && $('#datepicker2').is(':visible') && $('#datepicker3').is(':visible')) {
					alert("Please enter a different time slot");
					event.preventDefault();
				}else if ($('#datetimepickeremail').is(':visible') && $("#datepickeremail").val() =="") {
					alert("Please enter  date");
					event.preventDefault();
				}else if ($("#sessionplan").val() == "") {
					alert("Please enter a session plan");
					event.preventDefault();
				}else if (!$('#datepicker1').is(':visible') && !$('#datepicker2').is(':visible') && !$('#datepicker3').is(':visible') && $("#optionsRadios1").is(':checked') == false && $("#optionsRadios2").is(':checked') == false && $("#optionsRadios3").is(':checked') == false &&  $("#optionsRadios4").is(':checked') == false  && $("#optionsRadios5").is(':checked') == false  && $("#optionsRadios6").is(':checked') == false && $("#requestModes").val() != "email") {
					alert("Please choose a date and time slot");
					event.preventDefault();
				}
			}else{
				if($("#sessionplan").val() == ""){
					alert("Please enter a session plan");
					event.preventDefault();
				}
			}
			if($('#datetimepickeremail').is(':visible') == false){
				$("#datepickeremail").val("");
			}
			
		});
	});
	</script>
	<script type="text/javascript">
var _urq = _urq || [];
_urq.push(['initSite', '8571f59c-9c67-4ac9-a169-0eb6aa49f203']);
(function() {
var ur = document.createElement('script'); ur.type = 'text/javascript'; ur.async = true;
ur.src = ('https:' == document.location.protocol ? 'https://cdn.userreport.com/userreport.js' : 'http://cdn.userreport.com/userreport.js');
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ur, s);
})();
</script> 
</body>

</html>
