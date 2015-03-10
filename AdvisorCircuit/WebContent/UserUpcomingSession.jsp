<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Upcoming Sessions</title>  		 

    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
 
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%
		List<AdvisorProfileDTO> advisordetails = (List<AdvisorProfileDTO>)request.getAttribute("advisordetails");
		List<UserRequestDTO> userRequestDetails = (List<UserRequestDTO>)request.getAttribute("requests");
		int sessionId = (Integer)request.getAttribute("sId");
		pageContext.setAttribute("userRequestDetails", userRequestDetails);
		pageContext.setAttribute("advisordetails", advisordetails);
    %>

</head>

<body>

    <div class="container">
   	<%@include file="/Header.jsp"%>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-9">
            	<h4></h4>
            	<h1 class="page-header">Upcoming Sessions</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_user.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
			
                <c:choose>
                  <c:when test="${advisordetails.size() > 0 && userRequestDetails.size() > 0}">
                      <c:forEach items="${advisordetails}" var="advisor">
                        <c:forEach items="${userRequestDetails}" var="request">
                            <c:if test="${advisor.getAdvisorId() == request.getAdvisorId()}">			
					
                        	<!-- Blog Post Row -->
                        	<div class="grey-panel">
                        		<div class="row">
                        	
                            		<div class="col-md-9">
                                    <div class="user-detail">
                                        <input type="hidden" name="rId" value="request.getRequestId()">
                                        <h1><c:out value="SESSION WITH ${advisor.getName()}"/></h1>
                                        
                                        <c:if test="${request.getService().equals('careertalk')}">
                                            <h3 class="text-career">Career Talk</h3>
                                        </c:if>
                                        <c:if test="${request.getService().equals('mockinterview')}">
                                            <h3 class="text-Mock">Mock Interview</h3>
                                        </c:if>
                                        <c:if test="${request.getService().equals('cvcritique')}">
                                            <h3 class="text-resume">Resume Critique</h3>
                                        </c:if>
                                        <c:if test="${request.getService().equals('personalworkshops')}">
                                            <h3 class="text-personal">Service : Personal Workshop</h3>
                                        </c:if>
                                        
                                        <!--<p><c:out value="${request.getQuery()}"/></p>-->
                                        <c:url value="UserUpcomingSessionViewDetails" var="myURL">
                                        	<c:param name="rId" value="${request.getRequestId()}"/>
                                        </c:url>
                                        
                                        <a class="btn btn-career" href="${myURL}">View Details</a>
                                        <c:if test="${request.getDays() == 0 && request.getHours() == 0 && request.getMinutes() == 0 }">
                                        <a class="btn btn-primary review" data-toggle="modal" data-target="#datepay">Review & Rate Your Advisor <i class="fa fa-angle-right"></i></a>
                                        </c:if>
                                    </div>           
                            		</div>
                            
                                    <div class="col-md-3 text-center time-sesion">
                                        <p>Time Left For Session</p>
                                        <div class="time">
                                        	<div class="tm days"><span><c:out value="${request.getDays()}"/></span> <p>Days</p></div>
                                            <div class="tm hours"><span><c:out value="${request.getHours()}"/></span> <p>Hours</p></div>
                                            <div class="tm min"><span><c:out value="${request.getMinutes()}"/></span> <p>Minutes</p></div>
                                        </div>
                                    </div>
                                    
                                    <div class="modal fade" id="datepay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content" style="background-color:#E5E5E5">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
									<h4 class="modal-title" id="myModalLabel" style="color:#575758">Review & Recommend Your Advisor</h4>
								</div>
								<div class="modal-body">
										<div class="panel panel-info">
											<div class="panel-body" >
				                                <form action="" >
					                                <div class="form-group">
					                                    <label for="icode" class="col-md-3 control-label" style="font-size:19px">Review Message</label>
					                                     <div class="col-md-9">
					                                     		<textarea rows="3" id="reviewmessage" name="reviewmessage" class="form-control"></textarea>
														 </div>
					                                </div>
												 	     <div class="col-md-10" style="padding-left:0px;">
								                            <label for="icode" class="col-md-3 control-label" style="margin-top:30px; font-size:19px">Recommend</label>
								                            <a onclick="recommend()"><img alt="" src="assets/img/services/Icon_Ranking.png" width="40px" height="40px" style="margin-top:30px; margin-left:60px"></a>
								                        </div>
					                                <div class="form-group">
					                                    <!-- Button -->                                        
					                                    <div class="col-md-offset-3 col-md-9" style="margin-top: 20px">
					                                        <button id="btn" type="button" onclick="review()" class="btn btn-info" style="font-size:17px">Submit</button>
															<!--<button id="btn" type="button"  class="btn btn-info">Cancel</button>	-->
					                                    </div>
					                                </div>
					     	                        	<div style="font: bold;" class="col-md-8" id ="error">
					                        			</div>      
					                             </form>
													
					                         </div>
             							</div> 
								</div>
							</div>
						</div>
					</div>
                            
                        		</div>
                        	</div>

     						</c:if>
						</c:forEach>
					</c:forEach> 
				</c:when>
                <c:otherwise>
                    <c:out value="YOU HAVE NO UPCOMING SESSIONS"></c:out>
                </c:otherwise>
                </c:choose>
					
            
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

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>

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
		function div_show() {
			$(review).show();
		}
		function div_hide(){
			document.getElementById('review').style.display = "none";
		}
		function recommend(){
			 $.ajax({
	                url : 'Recommend', // Your Servlet mapping or JSP(not suggested)
	                data : {"sId" :<%=sessionId%>},
	                type : 'POST',
	                dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	                success : function(response) {
	                    $('#error').html(response); // create an empty div in your page with some id
	                },
	                error : function(request, textStatus, errorThrown) {
	                    alert(errorThrown);
	                }
	            }); 
		}
		function review(){

			$.ajax({
                url : 'Recommend', // Your Servlet mapping or JSP(not suggested)
                data : {"sId" :<%=sessionId%>,"review":"true","reviewmessage": $("#reviewmessage").val()},
                type : 'POST',
                dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
                success : function(response) {
                    $('#error').html(response);
                   					// create an empty div in your page with some id
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                    
                }
            }); 
			
			
			
		}
	</script>
</body>

</html>
