<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
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
		tr{border:1px solid #000000;}
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
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/bootstrap.css" rel="stylesheet">
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
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		input[type="text"]{height:40px !important}
		.add-on{height:40px !important;cursor:pointer !important}
		.control-label{width:130px !important}
	</style>	
	<%
			List<AdvisorProfileDTO> users = (List<AdvisorProfileDTO>)request.getAttribute("users");
			pageContext.setAttribute("users", users);
	%>
</head>

<body>

    <div style="margin-left:5.9%;margin-right:5.9%">
	<%@include file="/Header.jsp" %>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class=" navbar-static-top" role="navigation" style="margin-bottom: 0">

			<div id="j_sidebar">
			<%@ include file="j-sidebar_admin.jsp" %>
			</div>
        </nav>

        <div id="page-wrapper" style="background-color:#E5E5E5;">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header" style="color:#575758;">Users</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			
			<div class="row">    
				<div class="col-lg-12">
							<div class="panel panel-info">
								<div class="panel-body" >
						
										<div class="col-md-12">
												<table style="text-align:center" class="table">
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
														<ul style=" list-style-type: none; padding-top:5px; padding-left:0px">
															<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" >Select Action</a>
																 <ul class="dropdown-menu" role="menu">
																	<c:url value="/AdminViewUserProfile" var="viewprofile">
										   								<c:param name="email" value="${user.getEmail()}"/>
																	</c:url>
																	<li><a href="${viewprofile}" target="_blank">View Profile</a></li>
																	<li><a id="${user.getUserId()}" onclick="deactivate(this)">Deactivate Account</a></li>
																	<li><a id="${user.getUserId()}" onclick="activate(this)">Activate Account</a></li>	
																</ul>
															</li>
														</ul>
														</td>
														<div id ="deactivate">
														</div>
														</tr>
													</c:forEach>
												</table>
											
											<div style="height:40px"></div>
											
												<div class="col-md-6">
													<table class="table">
												
												</table>
												</div>
										</div>
								 </div>
							</div> 
				 </div> 

			
        </div>    
        </div>
        <!-- /#page-wrapper -->
 	</div>
	<div style="margin-bottom: 80px">
	 	<ul  class="pagination" style="float: right;">
			<li id="PageList"></li>
		</ul>
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
	</script>

</body>

</html>
