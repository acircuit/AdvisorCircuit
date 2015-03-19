<!DOCTYPE html>
<%@page import="org.AC.dto.SessionDTO"%>
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
			List<SessionDTO> sessionList = (List<SessionDTO>)request.getAttribute("sessionList");
			pageContext.setAttribute("sessionList", sessionList);
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
            	<h1 class="page-header">Review And Recommendation</h1>
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
                    	<table style="text-align:center" class="table table-bordered" id="tblData">
												<tr><th style="text-align:center">SESSION_ID</th><th style="text-align:center">REQUEST_ID</th><th style="text-align:center">STATUS</th><th style="text-align:center">REVIEW MESSAGE</th><th style="text-align:center">RECOMMENDATION</th><th style="text-align:center">REVIEW MESSAGE STATUS</th></tr>
														<c:forEach items="${sessionList}" var="session">
																<c:url value="SessionViewDetails" var="myURL">
										   						<c:param name="rId" value="${session.getRequestId()}"/>
														</c:url>
														<tr><td><a href="${myURL}" target="_blank">${session.getSessionId()}</a></td><td><p>${session.getRequestId()}</p></td><td><p>${session.getStatus()}</p></td>
														<td>
														<a data-toggle="modal" data-target="#reviewmessage${session.getSessionId()}">Review Message</a>														
														</td>
														<td>
														<c:choose>
															<c:when test="${session.getIsRating()}">
																<p>RECOMMENDED</p>
															</c:when>
															<c:otherwise>
																<p>NOT RECOMMENDED</p> 																
															</c:otherwise>														
														</c:choose>
														</td>
														<td>
															<c:choose>
																<c:when test="${session.getReviewMessageStatus().equals('APPROVED') }">
																	<p>APPROVED</p>
																</c:when>
																<c:when test="${session.getReviewMessageStatus().equals('REJECTED') }">
																	<p>REJECTED</p>
																</c:when>
																<c:otherwise>
									                               <div class="col-md-offset-3 col-md-9">
									                               		<a href="" id="${session.getSessionId()}" onclick="approve(this)" class="btn btn-info">APPROVE</a>
									                               </div>
									                           		<span><b>OR</b></span>
									                               <!-- Button -->                                        
									                               <div class="col-md-offset-3 col-md-9">
									                               		 <a href="" id="${session.getSessionId()}" onclick="reject(this)" class="btn btn-info">REJECT</a>
									                               </div>
																</c:otherwise>
															</c:choose>
															<div id = "reply">
															</div>
														</td>
														</tr>
													<div class="modal fade" id="reviewmessage${session.getSessionId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
														<div class="modal-dialog modal-lg">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
																	<h4 class="modal-title" id="myModalLabel">Review Message</h4>
																</div>
																<div class="modal-body">
																<p>${session.getReviewMessage()}</p>
																</div>
															</div><!-- /.modal-content -->
														</div><!-- /.modal-dialog -->
													</div><!-- /.modal -->
													</c:forEach>
												</table>
					</div>
 
        		</div>
        		
                <div style="margin-bottom:80px">
                    <ul class="pagination" style="float:right;">
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
   <script type="text/javascript">
	function approve(e){
		var id = e.id;
			$.ajax({
                url : 'AdminAdvisorReview', // Your Servlet mapping or JSP(not suggested)
                data : {"sId" :id,"approveorreject" :"APPROVE"},
                type : 'POST',
                dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
                success : function(response) {
                	location.reload();
                    $('#reply').html(response);
                   					// create an empty div in your page with some id
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                    
                }
            }); 
		}
	
	function reject(e){
		var id = e.id;
		$.ajax({
            url : 'AdminAdvisorReview', // Your Servlet mapping or JSP(not suggested)
            data : {"sId" :id,"approveorreject" :"REJECT"},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
            	location.reload();
                $('#reply').html(response);
               					// create an empty div in your page with some id
            },
            error : function(request, textStatus, errorThrown) {
                alert(errorThrown);
                
            }
        }); 	
	}
	</script>

	<script src="assets/js/gridSearch.js"></script>

</body>

</html>
