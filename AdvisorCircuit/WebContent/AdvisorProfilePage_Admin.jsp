<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.ProfessionalBackgroundDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
<%@page import="org.AC.dto.AdvisorModeDTO"%>
<%@page import="org.AC.dto.AdvisorEducationDTO"%>

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
  <title>ProfilePage</title>
  <style type="text/css"> 
  		
  				
  		#datetimepicker1,#datetimepicker1 input,#datetimepicker2,#datetimepicker2 input,#datetimepicker3,#datetimepicker3 input,#datetimepicker4,#datetimepicker4 input,#datetimepicker5,#datetimepicker5 input,#datetimepicker6,#datetimepicker6 input{display: inline-block;
		margin-bottom: 0;
		vertical-align: middle;}
		
		#datetimepicker1 .add-on,#datetimepicker6 .add-on,#datetimepicker2 .add-on,#datetimepicker3 .add-on,#datetimepicker4 .add-on,#datetimepicker5 .add-on{display: inline-block;
		width: auto;
		height: 28px;
		min-width: 16px;
		padding: 0;
		font-size: 14px;
		font-weight: normal;
		line-height: 20px;
		text-align: center;
		text-shadow: 0 1px 0 #fff;
		background-color: #eee;
		border: 1px solid #ccc;
		vertical-align: top;
		margin-left: -1px;
		border-radius: 0 4px 4px 0;}
		
		#datetimepicker1 .add-on i,#datetimepicker2 .add-on i,#datetimepicker3 .add-on i,#datetimepicker4 .add-on i,#datetimepicker5 .add-on i,#datetimepicker6 .add-on i{
		margin-top:3px;
		line-height: 14px;
		vertical-align: text-top;
		background-image: url("assets/img/glyphicons-halflings.png");
		background-position: -192px -120px;
		background-repeat: no-repeat;}
  		
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
			padding-right:0px !important;
			padding-left:0px !important;
			}	
		
		h5{
			padding-right:0px !important;
			padding-left:0px !important;
		}	
			
		p{font-family:'custom_light' !important;}
		
		a{font-family:'custom_light' !important;}
		
		h2{margin-bottom: 10px;}
		
		li{font-family:'custom_light' !important;}
		
		video {  
        width:100%;  
        height:650px;  
        position: relative;  
			}
		
		.modes{color:#575758; margin-left: 5px !important;}	
		.foot-size{font-size:14px !important;}
		.foot-color{color:#717171;}
		.details
		{
			padding-top: 5px;
			padding-bottom: 0px;
			margin-bottom: 5px;
			margin-left:40px;
			font-size:17px;
		}
		@media only screen and (max-width:700px){
		.font_size{font-size:22px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		
		</style>
	  	<!-- Bootstrap core CSS -->
	    <link href="assets/css/bootstrap.css" rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="assets/css/main.css" rel="stylesheet">
	
	    <!-- Fonts from Google Fonts -->
		<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	  	
	  	<!-- Profile CSS given by Raunak -->
	  	<link rel="stylesheet" type="text/css" href="assets/css/profile.css">
		
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	    
	    <!-- Book My Session CSS -->
	    <link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">	    
	    
	    <link href="assets/css/full-slider.css" rel="stylesheet">
	    
    	<%
   		List<AdvisorProfileDTO> advisors= (List<AdvisorProfileDTO>)request.getAttribute("advisordetails");
   	  	List<ProfessionalBackgroundDTO> profession= (List<ProfessionalBackgroundDTO>)request.getAttribute("advisorprofession");
   	 	List<AdvisorServiceDTO> services= (List<AdvisorServiceDTO>)request.getAttribute("advisorservices");
   	 	List<AdvisorModeDTO> modes= (List<AdvisorModeDTO>)request.getAttribute("advisormodes");
   	 	List<String> reviews= (List<String>)request.getAttribute("reviews");
   	 	List<AdvisorEducationDTO> advisoreducation= (List<AdvisorEducationDTO>)request.getAttribute("advisoreducation");
   	 	List<AdvisorProfileDTO> advisorskills= (List<AdvisorProfileDTO>)request.getAttribute("advisorskills");
   	 	List<AdvisorProfileDTO> awards= (List<AdvisorProfileDTO>)request.getAttribute("awards");

   	 	
   	 	
		String advisorId = (String)request.getParameter("aId");
		request.setAttribute("advisorId", advisorId);	
   		    int  uId =  0;
   		    if(session.getAttribute("userId") != null){
   	 	    uId = (Integer)session.getAttribute("userId");
   		    }
   	 		Boolean redirect = false;
   	 		if ( uId == 0){
   	 			redirect = true;
   	 		}
   			pageContext.setAttribute("advisors", advisors);
   			pageContext.setAttribute("profession", profession);
   			pageContext.setAttribute("services", services);
   			pageContext.setAttribute("modes", modes);
   			pageContext.setAttribute("modes", modes);
   			pageContext.setAttribute("reviews", reviews);
   			pageContext.setAttribute("advisoreducation", advisoreducation);
   			pageContext.setAttribute("advisorskills", advisorskills);
   		%>
  
</head>

<body>
 <div>	
  <div style="margin-left:5.9%;margin-right:5.9%">
	<%@include file="/Header.jsp" %>
	</div>
  <div class="container" style="width: 90% !important">
    <div class="content">
    <c:forEach items="${advisors}" var="advisor">
      <div class="profile-intro">
        <div class="profile_image">
        <img src="${advisor.getImage()}" width="50%" height="428px"/>
        </div>
        <div class="details1">
          <h2>${advisor.getName()}</h2>
          <h3 style="color: #333;font-size: 24px;margin-top: 10px !important;margin-bottom: 20px !important;">${advisor.getOccupation()}</h3>
          <p style="font-size:17px; color: #333;margin-top:-1%;text-align:center;height:190px; margin-bottom:10px" id="selfintroduction"></p>
           <div class="modal fade" id="intro" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">Introduction</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important">${advisor.getIntroduction()}</p>
								</div>
							</div>
                     	</div>
             	</div>
        
        <div class="icon" style="padding-left:42%;margin-top:-1%">
        <p style="margin-top: 10px; margin-left:12px">
        <a href="#" id="book_a_session1" onclick="div_show(this)"  style="color:#333;font-size:19px; text-decoration:underline !important" class="modes">
        <img  src="assets/img/ProfileImages/Panel_Services_Book.png" style="width:65px;height:65px; margin-bottom:10px"></br>
        Book a Session
        </a>
        </p>
      	</div>
	        </div>
     </div>
      <div class="profile-details">
        <h2>Education</h2>
        <c:forEach var="education" items="${advisoreducation}">
        	<li class="details">${education.getEducation()}</li>
        </c:forEach>
        <h2>Professional Background</h2>
        <c:forEach items="${profession}" var="prof">
        	<h4 style="text-align: left;important;font-size: 18px !important;margin-top: 10px;margin-bottom: 10px;margin-left:20px; color: #333;">${prof.getDesignation()},${prof.getCompany()},${prof.getDuration()}</h4>
	        <c:forEach items="${prof.getDescriptionArray()}" var="description">
	        	<c:if test="${description.length() >0}">
					<li class="details">${description}</li>
				</c:if>
			</c:forEach>
		</c:forEach>
        <h2 style="margin-bottom: 10px;">Skills</h2>
		<c:forEach items="${advisorskills}" var="skill">
		<li class="details">${skill.getKeyskill()}</li>
		</c:forEach> 
		<c:if test="${awards != null && awards.size() >0}">      
			<h2 style="margin-bottom: 10px;">Awards & Achievements</h2>
			<c:forEach items="${awards}" var="achievement">
				<li class="details">${achievement.getAchievements()}</li>
			</c:forEach>
		</c:if>     
		<c:if test="${advisor.getKnowYourAdvisor() != null && !advisor.getKnowYourAdvisor().equals('')}">    
			<h2 style="margin-bottom: 10px;">Know Your Advisor Better</h2>
	        <p style="padding-top: 0px;padding-bottom: 0px;font-size:17px">${advisor.getKnowYourAdvisor()}</p>
        </c:if>
      	</div>
		<input type="hidden" name="redirect" id="redirect" value="<%=redirect%>">
		<input type="hidden" name="aId" id="aId" value="<%=advisorId%>">
      <div class="profile-services">
        <div id="service-banner">
          <h2>${advisor.getName()} is available for the following services</h2>
        </div>
        
        <c:forEach items="${services}" var="service">
        	<c:if test="${service.getAdvisorId() == advisor.getAdvisorId() }">
	        	<c:if test="${service.getService().equals('careertalk')}">
			        <div id="career-talk" class="col-md-5 service" style="margin-bottom:20px;width:48%;height:385px">
			          <h2 style="margin-bottom: 5px;">Career Talk </h2>
			          <div class="icon-group" style="margin-top: 15px; margin-left:20px;height:48%">
			           	<h4 class="careertalkdescription" style="font-size:17px; color:#575758">${service.getDescription()}</h4>
			          	<ul style="margin-left: 21%;" class="modecareertalk">
				           	<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('careertalk') }">
					            	  <c:if test="${mode.getModeOfCommunication().equals('email')}">
				            	  		  <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" style="margin-bottom:13px"></br>Email</br>Rs.${mode.getPrice()}
							              </li>
						              </c:if>
						              <c:if test="${mode.getModeOfCommunication().equals('phone')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" style="margin-bottom:13px"></br>Phone</br>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
	         					      <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px !important"> <img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" style="margin-bottom:13px"></br>Webchat</br>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
					            </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
			           <span class="icon" style="margin-left:42%; margin-top:9px">
			        
			      <p><a href="#" id="book_a_session2" onclick="div_show(this)" style="text-decoration:underline"><img src="assets/img/ProfileImages/Panel_Services_Book.png"><br>Book a Session</a></p>
			      </span>
			        </div>
			        <div class="modal fade" id="careertalkdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">Career Talk Description</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>
		        </c:if>
	           	<c:if test="${service.getService().equals('mockinterview')}">
			        <div id="mock-interview" class="col-md-5 service" style="margin-bottom:20px;width:48%;height:385px">
			          <h2 style="margin-bottom: 5px;">Mock Interview </h2>
			          <div class="icon-group" style="margin-top: 15px; margin-left:20px;height:48%">
			           	<h4 class="mockinterviewdescription" style="font-size:17px; color:#575758">${service.getDescription()}</h4>
			          	<ul style="margin-left: 21%;" class="modemockinterview">
				           	<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('mockinterview') }">
					            	  <c:if test="${mode.getModeOfCommunication().equals('email')}">
				            	  		  <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" style="margin-bottom:13px"></br>Email<br/>Rs.${mode.getPrice()}
							              </li>
						              </c:if>
						              <c:if test="${mode.getModeOfCommunication().equals('phone')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" style="margin-bottom:13px"></br>Phone<br/>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
	         					      <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"> <img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" style="margin-bottom:13px"></br>Webchat<br/>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
					            </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
			           <span class="icon" style="margin-left:42%; margin-top:9px">
			      	<p><a href="#" id="book_a_session3" onclick="div_show(this)" style="text-decoration:underline"><img src="assets/img/ProfileImages/Panel_Services_Book.png"><br>Book a Session</a></p>
			      </span>
			        </div>
			        <div class="modal fade" id="mockinterviewdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">Mock Interview Description</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>
		        </c:if>
   	           	<c:if test="${service.getService().equals('cvcritique')}">
			        <div id="cv" class="col-md-5 service" style="margin-bottom:20px;width:48%;height:385px;">
			          <h2 style="margin-bottom: 5px;">Resume Critique </h2>
			          <div class="icon-group" style="margin-top: 15px; margin-left:20px;;height:48%">
			           	<h4 class="cvcritiquedescription" style="font-size:17px; color:#575758">${service.getDescription()}</h4>
			          	<ul style="margin-left: 21%;" class="modecvcritique">
				           	<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('cvcritique') }">
					            	  <c:if test="${mode.getModeOfCommunication().equals('email')}">
				            	  		  <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" style="margin-bottom:13px"></br>Email<br/>Rs.${mode.getPrice()}
							              </li>
						              </c:if>
						              <c:if test="${mode.getModeOfCommunication().equals('phone')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" style="margin-bottom:13px"></br>Phone<br/>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
	         					      <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"> <img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" style="margin-bottom:13px"></br>Webchat<br/>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
					            </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
			           <span class="icon" style="margin-left:42%; margin-top:9px">
			      	<p><a href="#" id="book_a_session4" onclick="div_show(this)" style="text-decoration:underline"><img src="assets/img/ProfileImages/Panel_Services_Book.png"><br>Book a Session</a></p>			        
			      </span>
			        </div>
			        <div class="modal fade" id="cvdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">Resume Critique Description</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>
		        </c:if>
		        
	             <c:if test="${service.getService().equals('personalworkshops')}">
			        <div id="workshop" class="col-md-5 service" style="margin-bottom:20px;width:48%;height:385px">
			          <h2 style="margin-bottom: 5px;">Personal Workshop </h2>
			          <div class="icon-group" style="margin-top: 15px; margin-left:20px;;height:48%">
			           	<h4 class="personalworkshopsdescription" style="font-size:17px; color:#575758;">${service.getDescription()}</h4>
			          	<ul style="margin-left: 21%;" class="modepersonalworkshops">
				           	<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('personalworkshops') }">
					            	  <c:if test="${mode.getModeOfCommunication().equals('email')}">
				            	  		  <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" style="margin-bottom:13px"></br>Email<br/>Rs.${mode.getPrice()}
							              </li>
						              </c:if>
						              <c:if test="${mode.getModeOfCommunication().equals('phone')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" style="margin-bottom:13px"></br>Phone<br/>Rs.${mode.getPrice()}
						              </li>
						              </c:if>
	         					      <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
						              <li class="icon-1" style="margin-bottom: 10px; font-size:18px"> <img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" style="margin-bottom:13px"></br>Webchat<br/>Rs.${mode.getPrice()}
						              </li>
						        </c:if>
					            </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
			           <span class="icon" style="margin-left:42%; margin-top:9px">
			      <p><a href="#" id="book_a_session5" onclick="div_show(this)" style="text-decoration:underline"><img src="assets/img/ProfileImages/Panel_Services_Book.png"><br>Book a Session</a></p>			        
			      </span>
			        </div>
			        <div class="modal fade" id="pdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">Personal Workshop Description</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>
		        </c:if>
	        </c:if>
        </c:forEach>
      </div>
      </c:forEach>
      <c:if test="${reviews.size()>0 }">
	      <div class="profile-reviews">
	        <h2>Reviews</h2>
	        <div class="review"><img src="assets/img/ProfileImages/Panel_8_Advisor_1.png">
	          <div class="review-content">
	            <h3 style="color: #333">Advisor Circuit directed me in a direction no one could!</h3>
	            <p>Definitely most professional people i have worked woth. Highly recommended.</p><span style="color: #333;font-family:'custom_light';">- Uday Khatry</span>
	          </div>
	        </div>
	
	        <div class="review"><img src="assets/img/ProfileImages/Panel_8_Advisor_2.png">
	          <div class="review-content">
	            <h3 style="color: #333">Advisor Circuit directed me in a direction no one could!</h3>
	            <p>Definitely most professional people i have worked woth. Highly recommended.</p><span style="color: #333;font-family:'custom_light';">- Uday Khatry</span>
	          </div>
	        </div>
	      </div>
      </c:if>
      	<div style="background-color:#e5e5e5;min-height:400px" class="similar-advisors">	
      	
			<div class="row centered">
				<div class="col-md-12 centered">
					<h1 style="color:#555; font-size: 46px">Similar Advisors</h1>
					<div style="height:20px"></div>
				</div>
				<div class="col-lg-12">
					<div class="col-lg-4">
						<c:url value="AdvisorProfile" var="michael">
   							<c:param name="aId" value="23"/>
						</c:url>
						<a href="${michael}"><img src="assets/UserImages/michaeldiias@gmail.com/Mr_MichaelDias.jpg" style="width:70%;height:180px" alt="123"></a>
						<h3 style="color:#555; font-size: 21px !important; margin-top:20px">Michael Dias, Lawyer</h3>
						<h4 style="color:#878787; font-size: 17px !important">Mr. Dias is a stalwart of corporate law. Having an experience of almost 40 years in the field of law he is ..</h4>
					</div><!--/col-lg-4 -->
					<div class="col-lg-4">
						<c:url value="AdvisorProfile" var="keshav">
   							<c:param name="aId" value="21"/>
						</c:url>
						<a href="${keshav}"><img src="assets/UserImages/Keshav@gmail.com/KK1.jpg" style="width:70%;height:180px" ></a>
						<h3 style="color:#555; font-size: 21px !important; margin-top:20px">Keshav Kanoria, Management Consultant</h3>
						<h4 style="color:#878787; font-size: 17px !important">Having worked with three different organizations in the fields of consulting, marketing and advisory services, Keshav ..</h4>
					</div><!--/col-lg-4 -->
					<div class="col-lg-4">
						<c:url value="AdvisorProfile" var="mathew">
   							<c:param name="aId" value="24"/>
						</c:url>
						<a href="${mathew}"><img src="assets/UserImages/mathewk@gmail.com/pic2.jpg" style="width:70%;height:180px"></a>
						<h3 style="color:#555; font-size: 21px !important; margin-top:20px">Mathew Thomas, Teacher</h3>
						<h4 style="color:#878787; font-size: 17px !important">Mathews Thomas has been a teacher for over 20 years. He has not only taught at numerous institutes but is also a ..</h4>
					</div><!--/col-lg-4 -->
			</div>
			</div><!-- /row -->
		</div><!-- /container -->
    </div>
    
    <!-- end .container -->
  </div>
    <div style="margin-left:5.9%;margin-right:5.9%">
	<%@include file="/Footer.jsp" %>
	</div>
 </div>
   <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/moment.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap3-datetimepicker.js"></script>
    <script type="text/javascript">
    $('#datetimepicker1').datetimepicker({
    	pick24HourFormat: true,
    	useCurrent:false
    });
    $('#datetimepicker2').datetimepicker({
    	pick12HourFormat: false,
    	useCurrent:false,
    });
    $('#datetimepicker3').datetimepicker({
    	pick12HourFormat: false,
    	useCurrent:false
    });
    $('#datetimepicker4').datetimepicker({
    	pick12HourFormat: false,
    	useCurrent:false
    });
    $('#datetimepicker5').datetimepicker({
    	pick12HourFormat: false,
    	useCurrent:false
    });
    $('#datetimepicker6').datetimepicker({
    	pick12HourFormat: false,
    	useCurrent:false
    });
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    });
    <c:forEach items="${advisors}" var="advisor">
    var intro = "${advisor.getIntroduction()}"
    var length = intro.length;
    if(length > 500){
    	intro = intro.substring(0,500);
    	$('#selfintroduction').html(intro+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#intro'>View More</a>")
    }else{
    	$('#selfintroduction').html(intro);
    }
    </c:forEach>
    var counter = 0;
    var service ="";
    var modecareertalkcounter =0;
    var modemockinterviewcounter =0;
    var modecvcritiquecounter =0;
    var modepersonalworkshopscounter =0;
    <c:forEach items="${services}" var="service">
    	if(${service.getService() == "careertalk"}){
    		counter++;
    		var service = "${service.getDescription()}";
    		var length = service.length;
    		if(length > 188){
    			service = service.substring(0,150);
    			$('.careertalkdescription').html(service+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#careertalkdesc'>View More</a>");
    		}
    	}else if(${service.getService() == "mockinterview"}){
    		counter++;
    		debugger;
    		var service = "${service.getDescription()}";
    		var length = service.length;
    		if(length > 188){
    			service = service.substring(0,150);
    			$('.mockinterviewdescription').html(service+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#mockinterviewdesc'>View More</a>");
    		}
    	}else if(${service.getService() == "cvcritique"}){
    		counter++;
    		var service = "${service.getDescription()}";
    		var length = service.length;
    		if(length > 188){
    			service = service.substring(0,150);
    			$('.cvcritiquedescription').html(service+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#cvdesc'>View More</a>");
    		}
    	}else if(${service.getService() == "personalworkshops"}){
    		counter++;
    		var service = "${service.getDescription()}";
    		var length = service.length;
    		if(length > 188){
    			service = service.substring(0,150);
    			$('.personalworkshopsdescription').html(service+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#pdesc'>View More</a>");
    		}
    	}
    	<c:forEach items="${modes}" var="mode">
			if(${mode.getAdvisorId() == service.getAdvisorId()}){
				if(${mode.getService()== "careertalk" && service.getService()== "careertalk" }){
					if(${mode.getModeOfCommunication() == "email"}){
						modecareertalkcounter++;
					}else if(${mode.getModeOfCommunication() == "phone"}){
						modecareertalkcounter++;
					}else if(${mode.getModeOfCommunication() == "webchat"}){
						modecareertalkcounter++;
					}
				}else if (${mode.getService()== "mockinterview" && service.getService()== "mockinterview"}) {
					if(${mode.getModeOfCommunication() == "email"}){
						modemockinterviewcounter++;
					}else if(${mode.getModeOfCommunication() == "phone"}){
						modemockinterviewcounter++;
					}else if(${mode.getModeOfCommunication() == "webchat"}){
						modemockinterviewcounter++;
					}
				}else if (${mode.getService()== "cvcritique" && service.getService()== "cvcritique"}) {
						if(${mode.getModeOfCommunication() == "email"}){
							modecvcritiquecounter++;
						}else if(${mode.getModeOfCommunication() == "phone"}){
							modecvcritiquecounter++;
						}else if(${mode.getModeOfCommunication() == "webchat"}){
							modecvcritiquecounter++;
						}
					}else if (${mode.getService()== "personalworkshops" && service.getService()== "personalworkshops"}) {
							if(${mode.getModeOfCommunication() == "email"}){
								modepersonalworkshopscounter++;
							}else if(${mode.getModeOfCommunication() == "phone"}){
								modepersonalworkshopscounter++;
							}else if(${mode.getModeOfCommunication() == "webchat"}){
								modepersonalworkshopscounter++;
							}
						}    
			}
		</c:forEach>
    	if(counter == 3 || counter == 2){
    	service = "${service.getService()}";
    	}
    </c:forEach>
    if(modecareertalkcounter == 1){
		$('.modecareertalk').css('margin-left','38%');
	}else if(modecareertalkcounter == 2){
		$('.modecareertalk').css('margin-left','30%');
	}
    if(modemockinterviewcounter == 1){
		$('.modemockinterview').css('margin-left','38%');
	}else if(modemockinterviewcounter == 2){
		$('.modemockinterview').css('margin-left','30%');
	}
    if(modecvcritiquecounter == 1){
		$('.modecvcritique').css('margin-left','38%');
	}else if(modecvcritiquecounter == 2){
		$('.modecvcritique').css('margin-left','30%');
	}
    if(modepersonalworkshopscounter == 1){
		$('.modepersonalworkshops').css('margin-left','38%');
	}else if(modepersonalworkshopscounter == 2){
		$('.modepersonalworkshops').css('margin-left','30%');
	}
    
    if(counter == 1){
    	$('.service').addClass('col-lg-offset-3');
    }else if(counter == 2){
    	if(service == "cvcritique"){
    		$('#cv').css('float','right');
    	}
    } else if(counter == 3){
    	if(service == "careertalk" ){
    		$('#career-talk').addClass('col-lg-offset-3');
    	}else if(service == "mockinterview"){
    		$('#mock-interview').addClass('col-lg-offset-3');
    	}else if(service == "cvcritique"){
    		$('#cv').addClass('col-lg-offset-3');
    	}else if(service == "personalworkshops"){
    		$('#workshop').addClass('col-lg-offset-3');
    	}
    }
    </script>