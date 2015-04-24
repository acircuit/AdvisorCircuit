<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
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
    <title>Advisors</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	
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
    	
	<%
			List<AdvisorProfileDTO> advisors = (List<AdvisorProfileDTO>)request.getAttribute("advisors");
			List<AdvisorServiceDTO> advisorServices = (List<AdvisorServiceDTO>)request.getAttribute("advisorServices");
			pageContext.setAttribute("advisors", advisors);
			pageContext.setAttribute("advisorServices", advisorServices);
	%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        		<div class="col-md-3">&nbsp;</div>
            <div class="col-md-4">
            	<h4></h4>
            	<h1 class="page-header">Advisors</h1>
            </div>
            <div class="col-md-2">
				<a id="discount" class="btn btn-primary" onclick="openDiscountToAllModal(this)">Discount to ALL</a>
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
												<tr>
													<th style="text-align:center">ACTION</th>
													<th style="text-align:center">ID</th>
													<th style="text-align:center">NAME</th>
													<th style="text-align:center">EMAIL</th>
													<th style="text-align:center">ISACTIVE</th>
													<th style="text-align:center">PHONE</th>
													<th style="text-align:center">PRIORITY LEVEL</th>
												</tr>	
													<c:forEach items="${advisors}" var="advisor">
														<tr>
														<td style="padding-top:12px">
														<ul style="list-style-type: none; padding-left:0px" class="action-btn">
															<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Select Action</a>
																 <ul class="dropdown-menu" role="menu">
																	<c:url value="/AdvisorProfile" var="viewprofile">
										   								<c:param name="aId" value="${advisor.getAdvisorId()}"/>
											   								<c:param name="admin" value="true"/>
																	</c:url>
																	<li><a href="${viewprofile}" target="_blank">View Profile</a></li>
																	<c:url value="/RegistrationPDF" var="PDF">
												   						<c:param name="aId" value="${advisor.getAdvisorId()}"/>
																	</c:url>
																	<li><a href="${PDF}" target="_blank" >View Profile PDF</a></li>
																	<c:url value="/AdminAdvisorMessage" var="AdminAdvisorMessage">
												   						<c:param name="email" value="${advisor.getEmail()}"/>
																	</c:url>
																	<li><a href="${AdminAdvisorMessage}" target="_blank">Message</a></li>
																	<li><a id="${advisor.getAdvisorId()}" onclick="deactivate(this)">Deactivate Account</a></li>
																	<li><a id="${advisor.getAdvisorId()}" onclick="activate(this)">Activate Account</a></li>	
																	<li><a id="${advisor.getAdvisorId()}" onclick="openChangePriorityModal(${advisor.getAdvisorId()},${advisor.getPageRank()})">Change Priority</a></li>
																	<li><a id="${advisor.getAdvisorId()}" onclick="changeVisibility(this)">Change Visibility</a></li>
																	<li><a id="${advisor.getAdvisorId()}" onclick="openFreeSessionModal(this)">Update Free Sessions</a></li>
																	<li><a id="${advisor.getAdvisorId()}" onclick="openDiscountModal(this)">Update Discount</a></li>
																</ul>
															</li>
														</ul>
														</td>
														<td><p>${advisor.getAdvisorId()}</p></td>
														<td style="color:
														<c:choose>
															<c:when test="${advisor.getIsVisible()}">black</c:when>
															<c:otherwise>red</c:otherwise>
														</c:choose>"><p>${advisor.getName()}</p></td>
														<td><p>${advisor.getEmail()}</p></td>
														<td>
														<c:choose>
															<c:when test="${advisor.getIsActive()}">
																<p>ACTIVATED</p>
															</c:when>
															<c:otherwise>
																<p>DEACTIVATED</p>
															</c:otherwise>
														</c:choose>
														</td>
														<td><p>${advisor.getPhone()}</p></td>
														<td>${advisor.getPageRank()}</td>
													<div class="modal fade" id="freeSession${advisor.getAdvisorId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-lg">
														<div class="modal-content">
															<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
															<h4 class="modal-title" id="myModalLabel">Update Free Session</h4>
															</div>
												        	<div class="modal-body">
												        		<form action="">
												        		<c:forEach items="${advisorServices}" var="service">
												        			<c:if test="${service.getAdvisorId() ==  advisor.getAdvisorId()}">
													        			<c:if test="${service.getService().equals('careertalk') }">
													        				<h4 class="control-label">Career Talk</h4>
													        				<div class="form-group">
																				<label  class="col-md-3 control-label">No of Free Sessions : </label>
																				<div class="col-md-9">
																					<input type="text" name="updateFreeSessions" id="updateFreeSessionCT${advisor.getAdvisorId()}" value="${service.getIsFree()}"/>
																				</div>
																			</div>
																			<br>
																			<br>
													        			</c:if>
													        			<c:if test="${service.getService().equals('mockinterview') }">
													        				<h4 class="" style="text-align: left">Mock Interview</h4>
													        				<div class="form-group">
																				<label  class="col-md-3 control-label">No of Free Sessions : </label>
																				<div class="col-md-9">
																					<input type="text" name="updateFreeSessions" id="updateFreeSessionMI${advisor.getAdvisorId() }" value="${service.getIsFree()}"/>
																				</div>
																			</div>
																			<br>
																			<br>
													        			</c:if>
													        			<c:if test="${service.getService().equals('cvcritique') }">
													        				<h4>Resume Critique</h4>
													        				<div class="form-group">
																				<label  class="col-md-3 control-label">No of Free Sessions : </label>
																				<div class="col-md-9">
																					<input type="text" name="updateFreeSessions" id="updateFreeSessionRC${advisor.getAdvisorId()}" value="${service.getIsFree()}"/>
																				</div>
																			</div>
																			<br>
																			<br>
													        			</c:if>
												        			</c:if>
												        		</c:forEach>
												          		
												          		<br/>
												          		<br/>
												          		<button id="${advisor.getAdvisorId() }" type="button" class="btn btn-primary" onclick="UpdateFreeSessions(this)">Change</button>        		
												          		<br/>
												          		</form>
												        	</div>
													    </div>
													  </div>
													</div>
													<div class="modal fade" id="discount${advisor.getAdvisorId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-lg">
														<div class="modal-content">
															<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
															<h4 class="modal-title" id="myModalLabel">Update Free Session</h4>
															</div>
													        	<div class="modal-body">
													        		<form action="">
													        		<c:forEach items="${advisorServices}" var="service">
													        			<c:if test="${service.getAdvisorId() ==  advisor.getAdvisorId()}">
														        			<c:if test="${service.getService().equals('careertalk') }">
														        				<h4 class="control-label">Career Talk</h4>
														        				<div class="form-group">
																					<label  class="col-md-3 control-label">Discount(%) : </label>
																					<div class="col-md-9">
																						<input type="text" name="updateFreeSessions" id="updateDiscountCT${advisor.getAdvisorId()}" value="${service.getDiscount()}"/>
																					</div>
																				</div>
																				<br>
																				<br>
														        			</c:if>
														        			<c:if test="${service.getService().equals('mockinterview') }">
														        				<h4 class="" style="text-align: left">Mock Interview</h4>
														        				<div class="form-group">
																					<label  class="col-md-3 control-label">Discount(%) : </label>
																					<div class="col-md-9">
																						<input type="text" name="updateFreeSessions" id="updateDiscountMI${advisor.getAdvisorId()}" value="${service.getDiscount()}"/>
																					</div>
																				</div>
																				<br>
																				<br>
														        			</c:if>
														        			<c:if test="${service.getService().equals('cvcritique') }">
														        				<h4>Resume Critique</h4>
														        				<div class="form-group">
																					<label  class="col-md-3 control-label">Discount(%) : </label>
																					<div class="col-md-9">
																						<input type="text" name="updateFreeSessions" id="updateDiscountRC${advisor.getAdvisorId()}" value="${service.getDiscount()}"/>
																					</div>
																				</div>
																				<br>
																				<br>
														        			</c:if>
													        			</c:if>
													        		</c:forEach>
													          		<br/>
													          		<br/>
													          		<button type="button" class="btn btn-primary"  onclick="updateDiscount(${advisor.getAdvisorId()})">Change</button>        		
													          		<br/>
													          		</form>
													        	</div>
														    </div>
														  </div>
														</div>
													<div id ="deactivate"></div>
														</tr>
													</c:forEach>
														
														
												</table>
					</div>
						
						
											
                    <div style="height:30px"></div>
                
                    <div class="col-md-6">
                        <table class="table">
                    
                        </table>
                    </div>
				 </div> 
        		
                <div style="margin-bottom: 80px">
                    <ul  class="pagination" style="float: right;">
                        <li id="PageList"></li>
                    </ul>
				</div>
                
            </div><!-- /right area -->
            
		</div><!-- /row -->
        
 	</div>
    <!-- /#wrapper -->
    
    <%@include file="/Footer.jsp" %>
	
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="myModal">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	    	<div class="modal-header">
          		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          			<h4 class="modal-title" id="mySmallModalLabel">Change Priority Level</h4>
       		</div>
        	<div class="modal-body">
          		Priority Level : <input type="text" name="updatedPriorityLevel" id="updatedPriorityLevel"/>
          		<input type="hidden" name="advisorId" id="advisorId"/>
          		<br/>
          		<br/>
          		<button type="button" class="btn btn-primary" onclick="changePriority()">Change</button>        		
          		<br/>
        	</div>
	    </div>
	  </div>
	</div>
	
	
	
	<div class="modal fade" id="discountToAll" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
			<h4 class="modal-title" id="myModalLabel">Update Discount</h4>
			</div>
        	<div class="modal-body">
        		<h4 class="control-label">Career Talk</h4>
     			<div class="form-group">
					<label  class="col-md-3 control-label">Discount(%) : </label>
					<div class="col-md-9">
						<input type="text" name="updateDiscountToAll" id="updateDiscountToAllCT"/>
					</div>
				</div>
				<br>
				<br>
     			<h4 class="" style="text-align: left">Mock Interview</h4>
     				<div class="form-group">
					<label  class="col-md-3 control-label">Discount(%) : </label>
					<div class="col-md-9">
						<input type="text" name="updateDiscountToAll" id="updateDiscountToAllMI"/>
					</div>
				</div>
				<br>
				<br>
      				<h4>Resume Critique</h4>
      				<div class="form-group">
					<label  class="col-md-3 control-label">Discount(%) : </label>
					<div class="col-md-9">
						<input type="text" name="updateDiscountToAll" id="updateDiscountToAllRC"/>
					</div>
				</div>
				<br>
				<br>
          		<button type="button" class="btn btn-primary" onclick="updateDiscountToAll()">Change</button>        		
          		<br/>
        	</div>
	    </div>
	  </div>
	</div>
	<!-- /#container -->
	</div>
    <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="assets/js/bootstrap-slider.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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
	function deactivate(e){
		var id = e.id;
			$.ajax({
                url : 'DeactivateAdvisor', // Your Servlet mapping or JSP(not suggested)
                data : {"aId" :id},
                type : 'POST',
                dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
                success : function(response) {
                    $('#deactivate').html(response);
                   					// create an empty div in your page with some id
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                    
                }
            }); 		
		}
	
	function activate(e){
		var id = e.id;
		$.ajax({
            url : 'DeactivateAdvisor', // Your Servlet mapping or JSP(not suggested)
            data : {"aId" :id,"activate" :true},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
                $('#deactivate').html(response);
               					// create an empty div in your page with some id
            },
            error : function(request, textStatus, errorThrown) {
                alert(errorThrown);
                
            }
        }); 		
	}
	
	function openChangePriorityModal(advisorId, pageRank){
		$('#myModal').modal();   
		$('#updatedPriorityLevel').val(pageRank); 
		$('#advisorId').val(advisorId);
	}
	
	function openFreeSessionModal(elem){
		var id = elem.id;
		$('#freeSession'+id).modal();
		$('#advisorId').val(id); 		
	}
	
	function openDiscountModal(elem){
		var id = elem.id;
		$('#discount'+id).modal();
		$('#advisorId').val(id); 
	}
	function openDiscountToAllModal(){
		$('#discountToAll').modal();
	}
	function UpdateFreeSessions(elem){
		var advisorId = elem.id;
		$.ajax({
            url : 'AdminAdvisors',
            data : {"operation": "updateFreeSessions", "advisorId" : advisorId, "noOfFreeSessionsCT" : $('#updateFreeSessionCT'+advisorId).val(),"noOfFreeSessionMI" : $('#updateFreeSessionMI'+advisorId).val(),"noOfFreeSessionsRC" : $('#updateFreeSessionRC'+advisorId).val()},
            type : 'POST',
            success : function(response) {
            	$('#freeSession').modal('hide'); 
            	location.reload();
            },
            error : function(request, textStatus, errorThrown) {
            	$('#freeSession').modal('hide'); 
            }
        }); 
	}
	
	function updateDiscount(id){
		$.ajax({
            url : 'AdminAdvisors',
            data : {"operation": "updateDiscount", "advisorId" : id, "updateDiscountCT" : $("#updateDiscountCT"+id).val(), "updateDiscountMI" : $("#updateDiscountMI"+id).val(),"updateDiscountRC" : $("#updateDiscountRC"+id).val()},
            type : 'POST',
            success : function(response) {
            	$('#discount').modal('hide'); 
            	location.reload();
            },
            error : function(request, textStatus, errorThrown) {
            	$('#discount').modal('hide'); 
            }
        }); 
	}
	
	function changePriority(){
		var advisorId = $('#advisorId').val();
		var updatedPriorityLevel = $('#updatedPriorityLevel').val(); 

		$.ajax({
            url : 'AdminAdvisors',
            data : {"operation": "updatePriorityLevel", "advisorId" : advisorId, "updatedPriorityLevel" : updatedPriorityLevel},
            type : 'POST',
            success : function(response) {
            	$('#myModal').modal('hide'); 
            	location.reload();
            },
            error : function(request, textStatus, errorThrown) {
            	$('#myModal').modal('hide'); 
            }
        }); 	
	}
	
	
	function changeVisibility(obj){
		$.ajax({
            url : 'AdminAdvisors',
            data : {"operation": "toggleAdvisorVisibilty", "advisorId" : obj.id},
            type : 'POST',
            success : function(response) {
            	$('#discountToAll').modal('hide'); 
            	location.reload();
            },
            error : function(request, textStatus, errorThrown) {
            	alert("Error in changing visibility.")
            }
        }); 	
		
	}
	function updateDiscountToAll(){
		$.ajax({
            url : 'AdminAdvisors',
            data : {"operation": "discountToAll","discountCT" : $('#updateDiscountToAllCT').val(),"discountMI" : $('#updateDiscountToAllMI').val(),"discountRC" : $('#updateDiscountToAllRC').val()},
            type : 'POST',
            success : function(response) {
            	location.reload();
            },
            error : function(request, textStatus, errorThrown) {
            	alert("Error in updating discount.")
            }
        }); 	
	}
	
	</script>
<script src="assets/js/gridSearch.js"></script>
</body>

</html>
