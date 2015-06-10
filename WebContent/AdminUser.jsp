<!DOCTYPE html>
<%@page import="org.AC.dto.UserReferralDTO"%>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
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
    <title>Users</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    
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
			List<AdvisorProfileDTO> users = (List<AdvisorProfileDTO>)request.getAttribute("users");
			List<UserReferralDTO> referral = (List<UserReferralDTO>)request.getAttribute("referral");
			Boolean isPromoActive = (Boolean) request.getAttribute("isActive");
			pageContext.setAttribute("users", users);
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
            	<h1 class="page-header">Users</h1>
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
                    <div class="table-responsive" style="overflow-x:visible ">
                        <table style="text-align:center" class="table table-bordered" id="tblData">
                            <tr><th style="text-align:center">ID</th><th style="text-align:center">NAME</th><th style="text-align:center">EMAIL</th><th style="text-align:center">ISACTIVE</th><th style="text-align:center">PHONE</th><th style="text-align:center">DATE OF REGISTRATION</th><th style="text-align:center">ACTION</th></tr>
                                <c:forEach items="${users}" var="user">
                                    <tr><td><p>${user.getUserId()}</p></td><td><p>${user.getFullName()}</p></td><td><p>${user.getEmail()}</p></td>
                                    <td>
                                    <c:choose>
                                        <c:when test="${user.getIsActive()}">
                                            <p>ACTIVATED</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>DEACTIVATED</p>
                                        </c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td><p>${user.getPhone()}</p></td>
                                    <td><p>${user.getDateOfRegistration()}</p></td>														
                                    <td>
                                    <ul style="list-style-type: none; padding-top:5px; padding-left:0px" class="action-btn">
                                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" >Select Action</a>
                                             <ul class="dropdown-menu" role="menu">
                                                <c:url value="/AdminViewUserProfile" var="viewprofile">
                                                    <c:param name="email" value="${user.getEmail()}"/>
                                                </c:url>
                                                <li><a href="${viewprofile}" target="_blank" href="#">View Profile</a></li>
                                                <li><a id="${user.getUserId()}" onclick="deactivate(this)" href="#">Deactivate Account</a></li>
                                                <li><a id="${user.getUserId()}" onclick="activate(this)" href="#">Activate Account</a></li>
                                                <c:if test="<%=isPromoActive %>">
                                                	<li><a id="${user.getUserId()}" onclick="OpenReferral(this)" href="#">Update Referral Count</a></li>
                                                </c:if>
                                            </ul>
                                        </li>
                                    </ul>
                                    </td>
                                    <c:forEach items="${referral}" var="ref">
										<c:if test="${ref.getUserId() == user.getUserId() }">
                                  		<div class="modal fade" id="refs${user.getUserId()}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
														<h4 class="modal-title" id="myModalLabel">Update Referral Count</h4>
													</div>
											        	<div class="modal-body">
											        		<form action="">
											        			
												        				<div class="form-group">
																			<label  class="col-md-3 control-label">Referral Code : </label>
																			<div class="col-md-9">
																				<input type="text" value="${ref.getRefCode()}"> 
																				</div>
																			</div>
																		<div class="form-group" style="margin-top: 10%">
																			<label  class="col-md-3 control-label">Referral Count : </label>
																			<div class="col-md-9">
																				<input type="text" id="count${user.getUserId()}" value="${ref.getRefCount()}">
																			</div>
																		</div>
																		<div class="form-group" style="margin-top:5% ">
													          			   <button type="button" style="margin-left:25%"	 class="btn btn-primary"  onclick="updaterefs(${user.getUserId()})">Update</button>        																				
													          			</div>
													          			<div class="form-group" style="margin-top: 10%">
																			<label  class="col-md-3 control-label">Referral Message : </label>
																			<div class="col-md-9">
																				<input type="text" id="message${user.getUserId()}" value="${ref.getRefMessage()}">
																			</div>
																		</div>
																		<div class="form-group" style="margin-top:5% ">
													          			   <button type="button" style="margin-left:25%"	 class="btn btn-primary"  onclick="updatemessage(${user.getUserId()})">Toggle</button>        																				
													          			</div>
												          			
											          		</form>
											        	</div>
												    </div>
											  </div>
										</div>
									</c:if>
							</c:forEach>
                                    <div id ="deactivate">
                                    </div>
                                    </tr>
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
                url : 'DeactivateUser', // Your Servlet mapping or JSP(not suggested)
                data : {"uId" :id},
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
	function OpenReferral(elem){
		var id = elem.id;
		$("#refs"+id).modal();
	}
	function activate(e){
		var id = e.id;
		$.ajax({
            url : 'DeactivateUser', // Your Servlet mapping or JSP(not suggested)
            data : {"uId" :id,"activate" :true},
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
	function updaterefs(id){
		$.ajax({
            url : 'AdminUsers', // Your Servlet mapping or JSP(not suggested)
            data : {"uId" :id,"count" :$("#count"+id).val()},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
                		location.reload();
               					// create an empty div in your page with some id
            },
            error : function(request, textStatus, errorThrown) {
                alert(errorThrown);
                
            }
		});
	}
	function updatemessage(id){
		$.ajax({
            url : 'AdminUsers', // Your Servlet mapping or JSP(not suggested)
            data : {"uId" :id,"val" :$("#message"+id).val()},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
                		location.reload();
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
