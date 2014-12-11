<!DOCTYPE html>
<%@page import="org.AC.dto.SearchDTO"%>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Advisor Landing</title>
	<style type="text/css"> 
		.col-lg-4{width:41% !important;}
	
	
		#ex1Slider .slider-selection {
					background: #BABABA;}
		.bdesign{font-size:20px !important;}
		.deskhid{display:none;}
		.hode{display:none;}
		.filter_lab{color:#5f5f5f;font-family:"Helvetica Neue",Arial,sans-serif !important;padding-left:0px;font-size:11px;
			word-wrap:break-word;word-break: break-all;display:block;}
		@media only screen and (max-width:700px){
		.font_size{font-size:22px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		#wrapper{ margin-left:0 !important;}
		.col-lg-4{width:100% !important;}
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		.hode{display:block !important}
		.marleft{margin-left:-220px;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		#wrapper{ margin-left:0 !important;}
		.col-lg-4{width:100% !important;}
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		.hode{display:block !important}
		}
		 </style>  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/slider.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	<!-- Custom CSS -->
    <link href="assets/css/simple-sidebar.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    	<% List<AdvisorProfileDTO> advisors= (List<AdvisorProfileDTO>)request.getAttribute("advisorProfile");
		List<SearchDTO> advisorskill= (List<SearchDTO>)request.getAttribute("advisorService");
		pageContext.setAttribute("advisors", advisors);
		pageContext.setAttribute("advisorskill", advisorskill);
		%>
  </head>

  <body>
	<%@include file="/Header.jsp" %>

	<hr>
	<hr>
	<hr>

	  <div id="wrapper">
		
		
		        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand" style="display:none">
                    <button type="button" class="btn btn-default btn-m">
  <span class="glyphicon glyphicon-filter"></span> Filter
</button>
                </li>
                <li>
                    <b>Industries<b>
                </li>
                <li>
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">All
					 </label>
				</div>
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Higher Education
					 </label>
				</div>	 
				<div style="width:100%">
					 <label class="filter_lab">
					  <input type="checkbox" style="width:25px;"><span style="word-wrap:break-word;word-break: break-all;">Management Consulting</span>
					 </label>
				</div>	 
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Information Technology
					 </label>
				</div>	 
				<div style="width:100%">
					 <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Financial Services
					 </label>
				</div>
				<div style="width:100%">
					 <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Computer Software
					 </label>
				</div>
				
                </li>
				
				<li>
                    <b>Services<b>
                </li>
                <li>
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">All
					 </label>
				</div>
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Career Conversation
					 </label>
				</div>	 
				<div style="width:100%">
					 <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Resume Critique 
					 </label>
				</div>	 
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">Mock Interview
					 </label>
				</div>	 
                </li>
				
				<li>
                    <b>Experience<b>
                </li>
                <li>
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">All
					 </label>
				</div>
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">>5 Year
					 </label>
				</div>	 
				<div style="width:100%">
					 <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">>10 Year
					 </label>
				</div>	 
				<div style="width:100%">
                    <label class="filter_lab">
					  <input type="checkbox" style="width:25px;">>15 Year
					 </label>
				</div>	 
                </li>
				
				<li>
                    <b>Price<b>
                </li>
                <li>
				<div style="width:100%">
				<input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="14"/>
				<input id="ex2" type="text" class="span2" value="" data-slider-min="10" data-slider-max="1000" data-slider-step="5" data-slider-value="[250,450]"/>
				</div>
				</li>
				
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->


        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row centered">
				 <div style="height:25px"></div>
				  <div class="hode"><a href="#menu-toggle" class="btn btn-default marleft" id="menu-toggle">Show/Hide Filters</a></div>
				  <div style="height:10px"></div>
                   <c:choose>
					          <c:when test="${advisors.size() > 0}">
					           		<c:forEach items="${advisors}" var="advisor">
    									<c:url value="/AdvisorProfileServlet" var="myURL">
				   							<c:param name="aId" value="${advisor.getAdvisorId()}"/>
										</c:url>
                                        <div class="col-lg-4">
					           			<a href="${myURL}">
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr><td><img src="${advisor.getImage()}" style="width:140px;height:141px" alt="">
												</td><td  style="background-color:#B0ACAC;width=60%;"><h3 style="color:#571F74;"><b><c:out value="${advisor.getName()}"/></b></h3><h4 style="color:#571F74;"><c:out value="${advisor.getOccupation()}" /></h4></td></tr></table>
										</a>
										<p style="text-align:left;">SERVICES:
										<c:forEach items="${advisorskill}" var="advisors">
											<c:if test="${advisor.getAdvisorId() == advisors.getAdvisorId()}">
												<c:out value="${advisors.getServices()}"/>
											</c:if>
										</c:forEach>
										</p>
                                        </div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:out value="NO MATCH FOUND"></c:out>
								</c:otherwise>
						</c:choose>
                </div>

            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

	

    <!-- Bootstrap core JavaScript
    ================================================== -->
	  <!-- jQuery Version 1.11.0 -->
    <script src="assets/js/jquery-1.11.0.js"></script>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap-slider.js"></script>
	  <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
	
	$('#ex1').slider({
	formatter: function(value) {
		return 'Current value: ' + value;
	}
	});
	$("#ex2").slider({});

    </script>

  </body>
</html>
