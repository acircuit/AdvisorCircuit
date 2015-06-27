<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.ProfessionalBackgroundDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
<%@page import="org.AC.dto.AdvisorModeDTO"%>
<%@page import="org.AC.dto.AdvisorEducationDTO"%>
<%@page import="org.AC.dto.RecommendationDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">

<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
  	<title>ProfilePage</title>
  
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!-- Profile CSS given by Raunak -->
    <!-- Book My Session CSS -->
    <link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">	    
    <link href="assets/css/full-slider.css" rel="stylesheet">
    
    <%
    List<AdvisorProfileDTO> advisors= (List<AdvisorProfileDTO>)request.getAttribute("advisordetails");
    List<ProfessionalBackgroundDTO> profession= (List<ProfessionalBackgroundDTO>)request.getAttribute("advisorprofession");
    List<AdvisorServiceDTO> services= (List<AdvisorServiceDTO>)request.getAttribute("advisorservices");
    List<AdvisorModeDTO> modes= (List<AdvisorModeDTO>)request.getAttribute("advisormodes");
    List<AdvisorEducationDTO> advisoreducation= (List<AdvisorEducationDTO>)request.getAttribute("advisoreducation");
    List<AdvisorProfileDTO> advisorskills= (List<AdvisorProfileDTO>)request.getAttribute("advisorskills");
    List<AdvisorProfileDTO> awards= (List<AdvisorProfileDTO>)request.getAttribute("awards");
	List<RecommendationDTO> reviews= (List<RecommendationDTO>)request.getAttribute("reviews");
	List<UserDetailsDTO> reviewUserDetails= (List<UserDetailsDTO>)request.getAttribute("reviewUserDetails");
   	Boolean userIsFree = (Boolean) request.getAttribute("userIsFree");
   	Boolean getUserPhoneNumber = (Boolean) request.getAttribute("getUserPhoneNumber"); 	
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
		pageContext.setAttribute("userIsFree", userIsFree);
		pageContext.setAttribute("getUserPhoneNumber", getUserPhoneNumber);
		pageContext.setAttribute("profession", profession);
		pageContext.setAttribute("services", services);
		pageContext.setAttribute("modes", modes);
		pageContext.setAttribute("modes", modes);
		pageContext.setAttribute("reviews", reviews);
		pageContext.setAttribute("reviewUserDetails", reviewUserDetails);
		pageContext.setAttribute("advisoreducation", advisoreducation);
		pageContext.setAttribute("advisorskills", advisorskills);
	%>
  
</head>

<body>
  <div class="container">
	<%@include file="/Header.jsp" %>
    
    <c:forEach items="${advisors}" var="advisor">
      <div class="row profile-intro">
        <div class="col-md-6 profile_image" style="padding-left:0;">
        	<img src="${advisor.getImage()}" class="img-responsive"/>
        </div>
        <div class="col-md-6 intro text-center">
            <h1>${advisor.getName()}
            	<span class="rating">
					<img src="assets/img/services/Icon_Ranking.png" style="width:40px; height:40px;" align="absmiddle">
					<c:out value="${advisor.getRatings()}"></c:out>
				</span>
            </h1>
            <h3>${advisor.getOccupation()}</h3>
            <p id="selfintroduction"></p>
			<div class="modal fade" id="intro" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
								<h4 class="modal-title" id="myModalLabel">Introduction</h4>
							</div>
							<div class="modal-body">
									<p style = "font-size: 18px!important" id="intr"></p>
							</div>
						</div>
					</div>
			</div>
            <div class="book-session text-center">
                <a href="#" id="book_a_session1" onclick="div_show(this)" class="modes">
                    <img src="assets/img/ProfileImages/Panel_Services_Book.png" height="65"></br>
                    Book a Session
                </a>
            </div>
            <c:if test="${advisor.getCharity() != null && !advisor.getCharity().equals('')}">
	            <div>
	            	 <p style="color:#c84c4e">${advisor.getName()} donates all earnings from Advisor Circuit to ${advisor.getCharity()}. </p>
	            </div>
            </c:if>
        </div>   
      </div>
      <!--end profile intro-->
        
      <div class="profile-details content">
      	<h1>Education</h1>
        <ul>
        <c:forEach var="education" items="${advisoreducation}">
        	<li class="details">${education.getEducation()}</li>
        </c:forEach>
        </ul>
        
        <h1>Professional Background</h1>
        <c:forEach items="${profession}" var="prof">
        <h4>${prof.getDesignation()},${prof.getCompany()},${prof.getDuration()}</h4>
        <ul>
	    <c:forEach items="${prof.getDescriptionArray()}" var="description">
	    	<c:if test="${description.length() >0}">
				<li class="details">${description}</li>
			</c:if>
		</c:forEach>
        </ul>
		</c:forEach>
        
        <h1>I can help you with</h1>
        <ul>
			<c:forEach items="${advisorskills}" var="skill">
				<li class="details">${skill.getKeyskill()}</li>
			</c:forEach> 
        </ul>
		
        <c:if test="${awards != null && awards.size() >0}">      
		<h1>Awards & Achievements</h1>
        <ul>
			<c:forEach items="${awards}" var="achievement">
				<li class="details">${achievement.getAchievements()}</li>
			</c:forEach>
        </ul>
		</c:if>
        
		<c:if test="${advisor.getKnowYourAdvisor() != null && !advisor.getKnowYourAdvisor().equals('')}">    
		<h1>Know Your Advisor Better</h1>
	    <p>${advisor.getKnowYourAdvisor()}</p>
        </c:if>
        
     </div>
      <!--end profile detail-->
     
	  <input type="hidden" name="redirect" id="redirect" value="<%=redirect%>">
      <input type="hidden" name="aId" id="aId" value="<%=advisorId%>">
            
	  <div class="profile-services text-center">
      	<h1>${advisor.getName()} is available for the following services</h1>
  	  </div>
      
      <div class="row other-service">
	  <c:forEach items="${services}" var="service">
    	<c:if test="${service.getAdvisorId() == advisor.getAdvisorId() }">
	    	<c:if test="${service.getService().equals('careertalk')}">
            
				<div id="career-talk" class="col-md-6 service">
                <div class="hpanel text-center">
                <c:if test="${service.getIsFree() != 0 || service.getDiscount() ==100}">
                	<div class="free-session-cb"></div>
                </c:if>
			    	<h2>Career Talk </h2>
                    <p class="careertalkdescription">${service.getDescription()}</p>
			        <div class="icon-group">
			        	<ul class="list-unstyled">
				        	<c:forEach items="${modes}" var="mode">
				          	<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('careertalk') }">
				          		<c:choose>
				          			<c:when test="${service.getDiscount() != 0}">
				          				<c:if test="${mode.getModeOfCommunication().equals('email')}">
						            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
								        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
			         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
				          			</c:when>
				          			<c:otherwise>
				          				<c:if test="${mode.getModeOfCommunication().equals('email')}">
						            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email<br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
								        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone<br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
			         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat<br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
				          			</c:otherwise>
				          		</c:choose>
					        </c:if>
				            </c:forEach>
		       			</ul>
					</div>
			        <p style="color: #c84c4e">Prices shown for Phone/Webchat are for 30 mins.</p>
                    <div class="book-session text-center">
			      		<a href="#" id="book_a_session2" onclick="div_show(this)"><img src="assets/img/ProfileImages/Panel_Services_Book.png" height="50"><br>Book a Session</a>
			      	</div>
                </div>
			    </div>
                
			    <div class="modal fade" id="careertalkdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
								<h4 class="modal-title" id="myModalLabel">Career Talk Description</h4>
							</div>
							<div class="modal-body">
								<p style = "font-size: 18px!important" id="ctdesc"> </p>
							</div>
						</div>
                     </div>
				</div>
                
		    </c:if>
                
	        <c:if test="${service.getService().equals('mockinterview')}">
				<div id="mock-interview" class="col-md-6 service">
                <div class="hpanel text-center">
                <c:if test="${service.getIsFree() != 0 || service.getDiscount() ==100}">
                	<div class="free-session-cb"></div>
                </c:if>
			    	<h2>Mock Interview</h2>
                    <p class="mockinterviewdescription">${service.getDescription()}</p>
                    
			        <div class="icon-group">
			          	<ul class="list-unstyled">
				           	<c:forEach items="${modes}" var="mode">
				          	<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('mockinterview') }">
					        	<c:choose>
				          			<c:when test="${service.getDiscount() != 0}">
				          				<c:if test="${mode.getModeOfCommunication().equals('email')}">
						            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
								        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
			         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
				          			</c:when>
				          			<c:otherwise>
				          				<c:if test="${mode.getModeOfCommunication().equals('email')}">
						            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email</br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
								        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone</br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
			         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat</br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
				          			</c:otherwise>
				          		</c:choose>
					        </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
			        <p style="color: #c84c4e">Prices shown for Phone/Webchat are for 30 mins.</p>			        
                    <div class="book-session text-center">
			      		<a href="#" id="book_a_session3" onclick="div_show(this)"><img src="assets/img/ProfileImages/Panel_Services_Book.png" height="50"><br>Book a Session</a>
			      	</div>
                    
                 </div>
			     </div>
                 
			    <div class="modal fade" id="mockinterviewdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
									<h4 class="modal-title" id="myModalLabel">Mock Interview Description</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important" id="midesc"></p>
								</div>
							</div>
                     	</div>
                   	</div>
                    
			</c:if>
                
   	        <c:if test="${service.getService().equals('cvcritique')}">
				<div id="cv" class="col-md-6 service">
                <div class="hpanel text-center">
                <c:if test="${service.getIsFree() != 0 || service.getDiscount() ==100}">
                	<div class="free-session-cb"></div>
                </c:if>
			    	<h2>Resume Critique </h2>
                    <p class="cvcritiquedescription">${service.getDescription()}</p>
                    
			        <div class="icon-group">
			        	<ul class="list-unstyled">
				           	<c:forEach items="${modes}" var="mode">
				          	<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('cvcritique') }">
					        	<c:choose>
				          			<c:when test="${service.getDiscount() != 0}">
				          				<c:if test="${mode.getModeOfCommunication().equals('email')}">
						            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
								        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
			         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat</br><span class="originalPrice">Rs.${mode.getPrice()}</span>Rs.${mode.getDiscounted_price()}</span></li>
								        </c:if>
				          			</c:when>
				          			<c:otherwise>
				          				<c:if test="${mode.getModeOfCommunication().equals('email')}">
						            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email</br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
								        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
								        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone</br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
			         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
									        <li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat</br>Rs.${mode.getPrice()}</span></li>
								        </c:if>
				          			</c:otherwise>
				          		</c:choose>
					        </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
      			        <p style="color: #c84c4e">Prices shown for Phone/Webchat are for 30 mins</p>                     
			          <div class="book-session text-center">
			      		<a href="#" id="book_a_session4" onclick="div_show(this)"><img src="assets/img/ProfileImages/Panel_Services_Book.png" height="50"><br>Book a Session</a>
			      	  </div>
                      
                    </div>  
			        </div>
                    
			    <div class="modal fade" id="cvdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
									<h4 class="modal-title" id="myModalLabel">Resume Critique Description</h4>
								</div>
								<div class="modal-body">
										<p style = "font-size: 18px!important" id="rcdesc"></p>
								</div>
							</div>
                     	</div>
                   	</div>
                    
			</c:if>
		        
	        <c:if test="${service.getService().equals('personalworkshops')}">
				<div id="workshop" class="col-md-6 service">
                <div class="hpanel text-center">
                <c:if test="${service.getIsFree() != 0 || service.getDiscount() ==100}">
                	<div class="free-session-cb"></div>
                </c:if>
			    	<h2>Personal Workshop </h2>
			        <p class="personalworkshopsdescription">${service.getDescription()}</p>
                    
                    <div class="icon-group">	
			          	<ul class="list-unstyled">
				           	<c:forEach items="${modes}" var="mode">
				          	<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('personalworkshops') }">
					        	<c:if test="${mode.getModeOfCommunication().equals('email')}">
				            		<li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" height="50"><span>Email<br/>Rs.${mode.getPrice()}</span></li>
						        </c:if>
						        <c:if test="${mode.getModeOfCommunication().equals('phone')}">
						        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" height="50"><span>Phone/30 mins<br/>Rs.${mode.getPrice()}</span></li>
						        </c:if>
	         					<c:if test="${mode.getModeOfCommunication().equals('webchat')}">
						        	<li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" height="50"><span>Webchat/30 mins<br/>Rs.${mode.getPrice()}</span></li>
						        </c:if>
					        </c:if>
				            </c:forEach>
		       			</ul>
			          </div>
                      
			          <div class="book-session text-center">
			      		<a href="#" id="book_a_session5" onclick="div_show(this)"><img src="assets/img/ProfileImages/Panel_Services_Book.png" height="50"><br>Book a Session</a>
			      	  </div>
                      
			        </div>
                    </div>
                    
			    	<div class="modal fade" id="pdesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
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
      
     <c:if test="${reviews.size() >0}">
      <div class="profile-reviews">
        <h1>Reviews</h1>
        <c:set var="doneLoop" value="0"/>
      	<c:forEach items="${reviews}" var="review">
      		<c:forEach var="user" items="${reviewUserDetails}">
      			<c:if test="${review.getUserId() == user.getUserId() && doneLoop <2}">
		      		<div class="review"><img style="width:90px;" src="${user.getImage()}">
			          <div class="review-content">
			           		<p style="font-size: 20px !important">${review.getReviewMessage()} <span style="display:block;">- ${user.getFullName()}</span></p>
			          </div>
			        </div>
			        <c:set var="doneLoop" value="${doneLoop +1}"/>
		        </c:if>
	        </c:forEach>
      	</c:forEach>
      	 <c:if test="${reviews.size() >2}">
      	 	<h3><a  data-toggle='modal' data-target='#rev'>View All</a></h3>
      	 </c:if>
      </div>
      </c:if>
      <div class="modal fade" id="rev" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
							<h4 class="modal-title" id="myModalLabel">Reviews</h4>
						</div>
						<div class="modal-body">
							<div>
								<c:forEach items="${reviews}" var="review">
						      		<c:forEach var="user" items="${reviewUserDetails}">
						      			<c:if test="${review.getUserId() == user.getUserId()}">
								      		<div class="review"><img style="width:90px;" src="${user.getImage()}" class="img-responsive">
									          <div class="review-content">
									           	<p >${review.getReviewMessage()}</p><span style="color: #333;font-family:'custom_light';">- ${user.getFullName()}</span>
									          </div>
									        </div>
								        </c:if>
							        </c:forEach>
					      		</c:forEach>
				      		</div>
						</div>
					</div>
            	</div>
        	</div>
      	
    <div class="row similar-advisors" style="display: none">
        <h1>Similar Advisors</h1>
        <div class="col-lg-4 text-center">
            <c:url value="AdvisorProfile" var="michael">
                <c:param name="aId" value="47"/>
            </c:url>
            <a href="${michael}"><img src="assets/UserImages/michaeldiias@gmail.com/Mr_MichaelDias.jpg" width="210"></a>
            <h3>Michael Dias, Lawyer</h3>
            <p>Mr. Dias is a stalwart of corporate law. Having an experience of almost 40 years in the field of law he is ..</p>
        </div><!--/col-lg-4 -->
                
        <div class="col-lg-4 text-center">
            <c:url value="AdvisorProfile" var="keshav">
                <c:param name="aId" value="50"/>
            </c:url>
            <a href="${keshav}"><img src="assets/UserImages/Keshav@gmail.com/KK1.jpg" width="210"></a>
            <h3>Keshav Kanoria, Management Consultant</h3>
            <p>Having worked with three different organizations in the fields of consulting, marketing and advisory services, Keshav ..</p>
        </div><!--/col-lg-4 -->
        
        <div class="col-lg-4 text-center">
            <c:url value="AdvisorProfile" var="mathew">
                <c:param name="aId" value="53"/>
            </c:url>
            <a href="${mathew}"><img src="assets/UserImages/mathewk@gmail.com/pic2.jpg" width="210"></a>
            <h3>Mathew Thomas, Teacher</h3>
            <p>Mathews Thomas has been a teacher for over 20 years. He has not only taught at numerous institutes but is also a ..</p>
        </div><!--/col-lg-4 -->
	</div>
		
	<div class="modal fade" id="bookasessionmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
									<h3 class="modal-title" id="myModalLabel" style="font-family:'custom_light' !important;text-align: center;" >Book A Session</h3>
								</div>
								<div class="modal-body">
									<form id="signupform" class="form-horizontal" role="form" action="BookASession" method="post" enctype="multipart/form-data">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                <input type="hidden" id="isfree" name="isFree">
                                <input type="hidden" id="discount" name="discount">
                                <input type="hidden" id="userisfree" name="userisfree">
                                <div class="form-group">
                                    <label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Services</label>
                                     <div class="col-md-6">
										<select class="form-control" id="services_dropdown" style="font-family:'custom_light' !important;" name="services_dropdown">
											<option>Select a Service</option>
											<c:forEach items="${services}" var="service">
												<c:if test="${service.getService().equals('careertalk')}">
												<option value="careertalk">Career Talk</option>
												</c:if>
												<c:if test="${service.getService().equals('mockinterview')}">
												<option value="mockinterview">Mock Interview</option>
												</c:if>
												<c:if test="${service.getService().equals('cvcritique')}">
												<option value="cvcritique">Resume Critique</option>
												</c:if>
												<c:if test="${service.getService().equals('personalworkshops')}">
												<option value="personalworkshops">Personal Workshop</option>
												</c:if>
				                            </c:forEach>
			                            </select>
									 </div>
                                </div>
                               	<c:forEach items="${services}" var="service">	
                               		<input type="hidden" value="${service.getAdvisorId()}" name="aId"></input>
                               	</c:forEach>	
                                 <div id="modediv" class="form-group collapse">
                                 		<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Mode</label>
										<div class="col-md-9">
	                                            <span id="modes" class="radio-inline" >
	                                            <ul style="list-style-type: none;display: inline;padding: 0;font-family:'custom_light' !important;"></ul>
	                                            </span>
                                        </div>
								</div>
								 <div id="cvdiv" class="form-group collapse">
                                 		<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Upload Your CV</label>
										<div class="col-md-9">
	                                           <input type="file" name= "cv" id="cvinput" accept="application/pdf"   >
                                        </div>
								</div>	
								 <div id="durationdiv" class="form-group collapse">
                                            <label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Duration</label>
                                            <div class="col-md-9">
											<label id="duration" font-family:'custom_light' !important; class="radio-inline">
                                                <input type="radio" style="font-family:'custom_light' !important;" onchange="CheckUserIsFree()" name="duration" id="optionsRadiosInline1" value="0.5">Half Hour
                                            </label>
                                            <label class="radio-inline" font-family:'custom_light' !important;>
                                                <input type="radio" style="font-family:'custom_light' !important;" onchange="CheckUserIsFree()" name="duration" id="optionsRadiosInline2" value="0.75">45 mins
                                            </label>
                                         	 <label class="radio-inline" font-family:'custom_light' !important;>
                                                <input type="radio" style="font-family:'custom_light' !important;" onchange="CheckUserIsFree()" name="duration" id="optionsRadiosInline3" value="1">1 Hour
                                            </label>
                                            <label class="radio-inline" font-family:'custom_light' !important;>
                                                <input type="radio" style="font-family:'custom_light' !important;" onchange="CheckUserIsFree()" name="duration" id="optionsRadiosInline4" value="1.5">1.5  Hour
                                            </label>
										</div>	
                                </div>
                                <div id="userFree" class="form-group collapse">
                                            <label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Do you want to use your Free 15 minutes credit ?</label>
                                            <div class="col-md-9">
											<label id="duration" font-family:'custom_light' !important; class="radio-inline">
                                                <input type="radio" style="font-family:'custom_light' !important;" onchange="getPrice()" name="free" id="optionsRadiosInlinefree1" value="yes">Yes
                                            </label>
                                            <label class="radio-inline" font-family:'custom_light' !important;>
                                                <input type="radio" style="font-family:'custom_light' !important;" onchange="getPrice()" name="free" id="optionsRadiosInlinefree2" value="no">No
                                            </label>
                                         	</div>	
                                </div>
                                
                                <div id="regpricediv" class="form-group collapse">
                                    <label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Price(Rs)</label>
                                    <div class="col-md-4">
                                        <input type="text" id="registrationPrice" name="registrationPrice" style="font-family:'custom_light' !important;" class="form-control" readonly="readonly">
                                    </div>
                                    <p id="serviceDiscount" style="color: #c84c4e"></p>
                                </div>								
								<div id="pricediv" class="form-group collapse">
                                    <label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Discounted Price(Rs)</label>
                                    <div class="col-md-4">
                                        <input type="text" name="price" style="font-family:'custom_light' !important;" class="form-control" id="disabledInput" placeholder="" readOnly="true">
					     				<h4 id="freesession" style="font-family:'custom_light' !important;color: #c84c4e;text-align: left;display: none;">Great news ! The first 30 mins is free for you. </h4> 
                                   		<h4 id="freesession1" style="font-family:'custom_light' !important;color: #c84c4e;text-align: left;display: none;">Great news ! This session is free for you. </h4> 
                                    </div>
                                </div>		
								  <c:if test="<%=getUserPhoneNumber %>">
								        <div id="dphone" class="form-group">
                                 		<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Phone Number</label>
										<div class="col-md-4">
	                                           <input id="phone" type="text"  class="form-control" name="phone" placeholder="" maxlength="20">
                                        </div>
								       </div>
								  </c:if>
                               
								
								
								<div style="height:10px"></div>
								<div id="notemail" style="display: none;">
								<h4 style="font-family:'custom_light' !important;text-align: center;">Choose Date and Time</h4>
								<hr>

									<div class="form-group">
										<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Slot 1</label>
										<div id="datetimepicker1" class="input-append date" >
										  <input type="datetime" style="font-family:'custom_light' !important;" id="datepicker1" name="datetimepicker1" readonly="readonly"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
									</div>

									<div class="form-group">
										<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Slot 2</label>
										<div id="datetimepicker2" class="input-append date" >
										  <input type="datetime" style="font-family:'custom_light' !important;" id="datepicker2" name="datetimepicker2" readonly="readonly"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
									</div>

									<div class="form-group">
										<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Slot 3</label>
										<div id="datetimepicker3" class="input-append date" >
										  <input type="datetime" style="font-family:'custom_light' !important;" id="datepicker3" name="datetimepicker3" readonly="readonly"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
									</div>

									<div class="form-group">
										<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Slot 4</label>
										<div id="datetimepicker4" class="input-append date">
										  <input type="datetime" style="font-family:'custom_light' !important;" id="datepicker4" name="datetimepicker4" readonly="readonly"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>


								</div>
								<div id="emailslot" style="display: none">
									<h4 style="font-family:'custom_light' !important;text-align: center;">Chose the date by which you want the reply</h4>
									<hr>
									<div class="form-group">
											<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label">Slot 1</label>
											<div id="datetimepickeremail" class="input-append date" >
											  <input type="datetime" style="font-family:'custom_light' !important;" id="datepickeremail" name="datetimepickeremail" data-provide="datepicker"  class="datepicker" readonly="readonly" ></input>
											  <span class="add-on">
												<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
											  </span>
											</div>
									</div>
								</div>
								<h4 style="font-family:'custom_light' !important;text-align: center;">Enter Your Query</h4>
									<hr>
								<div class="form-group">
									
									<label for="icode" style="font-family:'custom_light' !important;" class="col-md-3 control-label"></label>
                                    <div class="col-md-6">
                                        <textarea class="form-control" style="font-family:'custom_light' !important;" id="query" name="query" rows="3" maxlength="6000" placeholder="Write your query in detail"></textarea>
                                        <p class="required" id="complete-query">Please write your complete email.</p>
                                    </div>
                                   
                                </div>
                                      
                                <div id="datacontent"></div>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="submit_btn" style="font-family:'custom_light' !important;" type="submit" class="btn btn-info">Submit</button>
										<!--<button id="btn" type="submit" class="btn btn-info">Cancel</button>	-->
                                    </div>
                                </div>
                            </form>
								</div>
							</div>
                     	</div>
                   	</div>
                   		
  
	<%@include file="/Footer.jsp" %>  
	</div>
	<!--main container-->
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/moment.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap3-datetimepicker.js"></script>
	<script>
    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
	<script>
      $(function () { $('.popover-show').popover('show');});
      $(function () { $('.popover-hide').popover('hide');});
      $(function () { $('.popover-destroy').popover('destroy');});
      $(function () { $('.popover-toggle').popover('toggle');});
     $(function () { $(".popover-options a").popover({html : true });});
   </script>
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
   $('#datetimepickeremail').datetimepicker({
		pick12HourFormat: false,
		useCurrent:false,
		pickTime: false
    });
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    });
	 <c:forEach items="${advisors}" var="advisor">
   	 var intro ="${advisor.getIntroduction()}";
	 var intro1 = ShowDoubleQuotes(intro);
    var length = intro.length;
    if(length > 500){
    	intro1 = intro.substring(0,500);
    	$('#selfintroduction').html(intro1+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#intro'>View More</a>")
    	$('#intr').html(intro);
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
    		var service1 =  ShowDoubleQuotes(service);
    		if(length > 188){
    			service1 = service.substring(0,150);
    			$('.careertalkdescription').html(service1+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#careertalkdesc'>View More</a>");
    			$("#ctdesc").html(service);
    		}else{
    			$('.careertalkdescription').html(service);
    		}
    	}else if(${service.getService() == "mockinterview"}){
    		counter++;
    		var service = "${service.getDescription()}";
    		var length = service.length;
    		var service1 =  ShowDoubleQuotes(service);
    		if(length > 188){
    			service1 = service.substring(0,150);
    			$('.mockinterviewdescription').html(service1+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#mockinterviewdesc'>View More</a>");
    			$("#midesc").html(service);
    		}else{
    			$('.mockinterviewdescription').html(service);
    		}
    	}else if(${service.getService() == "cvcritique"}){
    		counter++;
    		var service ="${service.getDescription()}";
    		var length = service.length;
    		var service1 =  ShowDoubleQuotes(service);
    		if(length > 188){
    			service1 = service.substring(0,150);
    			$('.cvcritiquedescription').html(service1+" .. <a style='float:none !important;text-decoration:underline !important' data-toggle='modal' data-target='#resumecritiquedesc'>View More</a>");
    			$("#rcdesc").html(service);
    		}else{
    			$('.cvcritiquedescription').html(service);
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

    function div_show(elem) {
    	var id = elem.id;
    	var val = document.getElementById("redirect").value;
		if( val == "true"){
			
			if(id == "book_a_session1"){
    		$("#book_a_session1").attr({
    		    "href" : "login?profile=true&aId=<%=advisorId %>"
    		  });
			}
			if(id == "book_a_session2"){
	    		$("#book_a_session2").attr({
	    		    "href" : "login?profile=true&aId=<%=advisorId %>"
	    		  });
				}
			if(id == "book_a_session3"){
	    		$("#book_a_session3").attr({
	    		    "href" : "login?profile=true&aId=<%=advisorId %>"
	    		  });
				}
			if(id == "book_a_session4"){
	    		$("#book_a_session4").attr({
	    		    "href" : "login?profile=true&aId=<%=advisorId %>"
	    		  });
				}
			if(id == "book_a_session5"){
	    		$("#book_a_session5").attr({
	    		    "href" : "login?profile=true&aId=<%=advisorId %>"
	    		  });
				}
    	}else{
        	$("#bookasessionmodal").modal({show:true});
    	}
	}
    function div_hide() {
    	document.getElementById('signupbox').style.display ="none";
   	}
    $("#services_dropdown").change(function() { 
        var value = document.getElementById('services_dropdown').value;
        if(document.getElementById('services_dropdown').value == "careertalk") {
    	 	$("#cvdiv").hide();
    	}
        $("#optionsRadiosInline1").prop('checked', false);
        $("#optionsRadiosInline2").prop('checked', false);
        $("#optionsRadiosInline3").prop('checked', false);
        $("#durationdiv").hide();
        $("#complete-query").hide();
        $("#pricediv").hide();
        $("#regpricediv").hide();
        $("#userFree").hide();
        $("#modediv").show();
        $("#modes ul").html('');     
        var i =1;
        <c:forEach items="${modes}" var="mode">
        if("${mode.getService()}" == value){
        	if("${mode.getModeOfCommunication()}" == "email"){
	       		$("#modes ul").append("<li style ='display: inline;float:left;margin-right:30px' class='modesstyle'><input type='radio' name='mode' onchange='getPrice()' id='optionsRadiosInlinem"+i+"' value='${mode.getModeOfCommunication()}'/>Email</li>");
	       		i++;
        	}else if ("${mode.getModeOfCommunication()}" == "phone") {
        		$("#modes ul").append("<li style ='display: inline;float:left;margin-right:30px' class='modesstyle'><input type='radio' name='mode' onchange='getDuration()' id='optionsRadiosInlinem"+i+"' value='${mode.getModeOfCommunication()}'/>Phone</li>");
           		i++;	
			}else if ("${mode.getModeOfCommunication()}" == "webchat") {
        		$("#modes ul").append("<li style ='display: inline;float:left;margin-right:30px' class='modesstyle'><input type='radio' name='mode' onchange='getDuration()' id='optionsRadiosInlinem"+i+"' value='${mode.getModeOfCommunication()}'/>Webchat</li>");
           		i++;	
			}
        }
        </c:forEach>
        if(value == "mockinterview" || value == "cvcritique"){
        	$("#cvdiv").show();
        }else{
        	$("#cvdiv").hide();
        }
        });
    function getDuration(){
    	if(document.getElementById('services_dropdown').value == "careertalk") {
    	 	$("#cvdiv").hide();
    	}
         $("#optionsRadiosInline1").prop('checked', false);
         $("#optionsRadiosInline2").prop('checked', false);
         $("#optionsRadiosInline3").prop('checked', false);
         $("#durationdiv").hide();
         $("#pricediv").hide();
         $("#regpricediv").hide();
         $("#complete-query").hide();
         $("#userFree").hide();
    	$("#durationdiv").show();
		document.getElementById('notemail').style.display = "block";
		document.getElementById('emailslot').style.display = "none";
    }
    function CheckUserIsFree(){
    	$("#pricediv").hide();
        $("#regpricediv").hide();
    	var advisorFree = 0;
    	<c:forEach items="${services}" var="service">
    	if(document.getElementById('services_dropdown').value == "${service.getService()}"){
    		advisorFree = "${service.getIsFree()}";
    	}
    	</c:forEach>
    	if(<%= userIsFree%> && $('input:radio[name=mode]:checked').val() != "email"  && advisorFree == 0){
    		$("#optionsRadiosInlinefree1").prop('checked', false);
    		$("#optionsRadiosInlinefree2").prop('checked', false);
    		$("#userFree").show();
    	}else{
    		$("#userFree").hide();
    		getPrice();
    	}
    }
    function getPrice(){
    	var valuemode = $('input:radio[name=mode]:checked').val();
    	
    	var valueduration;
    	if(valuemode != "email"){
	    	valueduration = $('input:radio[name=duration]:checked').val();
    	}else{
    		$("#userFree").hide();
			document.getElementById('durationdiv').style.display = "none";
			document.getElementById('notemail').style.display = "none";
			document.getElementById('emailslot').style.display = "block";
    	}
    	var isUserOptFree = $('input:radio[name=free]:checked').val();
	var price;
	var free= false;
	var pr;
	var isFreeSessions = 0;
    	<c:forEach items="${modes}" var="mode">
    		if("${mode.getModeOfCommunication()}" == valuemode && "${mode.getService()}" ==  document.getElementById('services_dropdown').value){
    			<c:forEach items="${services}" var="service">
    				if("${mode.getService()}" == "${service.getService()}"){
    					isFreeSessions = "${service.getIsFree()}";
    					disc = "${service.getDiscount()}";
    					pr = "${mode.getPrice()}";
    					if(("${service.getIsFree()}" > 0 && $('input:radio[name=duration]:checked').val()== "0.5") || ("${service.getIsFree()}" > 0 && valuemode=="email") ){
    						price= "0";
    						free = true;
    					}else if("${service.getIsFree()}" > 0 && $('input:radio[name=duration]:checked').val()== "0.75"){
    						var price1 = "${mode.getPrice()}";
    						price = Math.round(price1/2);
    						free = true;
    					}else if("${service.getIsFree()}" > 0 && $('input:radio[name=duration]:checked').val()== "1"){
    						price = "${mode.getPrice()}";
    						free = true;
    					}else if("${service.getIsFree()}" > 0 && $('input:radio[name=duration]:checked').val()== "1.5"){
    						price = "${mode.getPrice()}" * 2;
    						free = true;
    					}else if("${service.getIsFree()}" == 0 && valuemode=="email" && "${service.getDiscount()}"==0){
							price = "${mode.getPrice()}";			    						
    					}
    					else if("${mode.getDiscounted_price()}" == 0 && "${service.getDiscount()}" == 0){
    						if(isUserOptFree == "yes" && <%= userIsFree%> && valuemode != "email"){
    							price =	Math.round("${mode.getPrice()}" * valueduration * 2 );
    							price = price - "${mode.getUser_isfree_price()}";
    						}else{
        	    				price = "${mode.getPrice()}"  * 2;
    						}
    	    			}else{
    	    				var discount= "${service.getDiscount()}";
    	    				if(isUserOptFree == "yes" && <%= userIsFree%> && valuemode != "email"){
    	    					price =	Math.round("${mode.getPrice()}" * valueduration * 2 );
    							price = Math.round(price - ((discount * price)/100));
    							price = price - "${mode.getUser_isfree_price()}";
    						}else{
        	    				price = "${mode.getDiscounted_price()}";
    						}
    	    			}
    					if(valuemode != "email"){
    						$("#registrationPrice").val(Math.round("${mode.getPrice()}" * valueduration * 2 ));
    					}else{
    						$("#registrationPrice").val("${mode.getPrice()}");
    					}
        				$("#discount").val("${service.getDiscount()}");
    				}
    			</c:forEach>
    			
    		}
    	</c:forEach>
    	if(disc > 0 && pr != 0){
    		$("#serviceDiscount").html(disc +"% Discount");
    		
    	}
    	if(free){
    		$("#isfree").val(true);
    	}
    	if(isUserOptFree == "yes" && <%= userIsFree%> && valuemode != "email" && isFreeSessions == 0){
    		$("#userisfree").val(true);
    	}else{
    		$("#userisfree").val(false);
    	}
    	if(valuemode != "email" &&  !free && isUserOptFree != "yes"){
    		price = Math.round(price * valueduration * 2);
    	}
    	$("#regpricediv").show();
    	if(isFreeSessions > 0 || disc > 0 || isUserOptFree== "yes"){
        	$("#pricediv").show();
    	}else{
    		$("#pricediv").hide();
    	}

	if(valuemode == "email" && (isFreeSessions > 0 || disc > 0 || isUserOptFree== "yes")){
    		$("#disabledInput").val(price);
    		$("#complete-query").show();
	}else if(valuemode != "email" && free){
		$("#disabledInput").val(price);
	}else{
		$("#disabledInput").val(price);
	}
	if(free){
		if(valuemode == "email"){
			$("#freesession1").show();
			$("#freesession").hide();
		}else{
			$("#freesession").show();
			$("#freesession1").hide();
		}
	}else{
		$("#freesession").hide();
		$("#freesession1").hide();
	}
    }
	function ShowDoubleQuotes(str){
		 var i = str.length,
         aRet = [];

     while (i--) {
       var iC = str[i].charCodeAt();
       if (iC < 65 || iC > 127 || (iC>90 && iC<97)) {
         aRet[i] = '&#'+iC+';';
       } else {
         aRet[i] = str[i];
       }
      }
     return aRet.join('');    
    }
    
    
    </script>
  <script type="text/javascript">
	$(document).ready(function() {
		var dt = new Date();
		dt.setDate(dt.getDate() + 3);
		var dt1 = new Date();
		dt1.setDate(dt.getDate() + 21);
		$('#datetimepicker1').data("DateTimePicker").setMinDate(dt);
		$('#datetimepicker2').data("DateTimePicker").setMinDate(dt);
		$('#datetimepicker3').data("DateTimePicker").setMinDate(dt);
		$('#datetimepicker4').data("DateTimePicker").setMinDate(dt);
		$('#datetimepickeremail').data("DateTimePicker").setMinDate(dt);
		$('#datetimepickeremail').data("DateTimePicker").setMaxDate(dt1);
		$("#submit_btn").click(function(event){
			var drop = $("#services_dropdown").val();
			var noError = true;
			var input_p = $("#phone").val();
			var filter = /^\d{10}$/; 
			var is_phone = filter.test(input_p);
			<c:forEach items="${services}" var="service">
			if("${service.getService()}" == drop && $('input:radio[name=duration]:checked').val()== "0.5" || "${service.getService()}" == drop && $('input:radio[name=mode]:checked').val() == "email"){
				if("${service.getIsFree()}" > 0){
					$("#isfree").val("true");
				}else{
					$("#isfree").val("false");
				}
			}
			</c:forEach>
			if(drop=="Select a Service")
			{
				alert("Please choose a service to move ahead !");
				event.preventDefault();
				noError =true;
			}else if($("#optionsRadiosInlinem1").is(':checked') == true || $("#optionsRadiosInlinem2").is(':checked') == true || $("#optionsRadiosInlinem3").is(':checked') == true)
				{	
				var query = $("#query").val();
				var value1 = $("#datepicker1").val();
				var value2 = $("#datepicker2").val();
				var value3 = $("#datepicker3").val();
				var value4 = $("#datepicker4").val();
				if(($("#optionsRadiosInline1").is(':checked') == false) && ($("#optionsRadiosInline2").is(':checked')== false) && ($("#optionsRadiosInline3").is(':checked') == false) && ($("#optionsRadiosInline4").is(':checked') == false) && $('input:radio[name=mode]:checked').val() != "email")
							{
									alert("Please Choose a duration to move ahead !");
									event.preventDefault();
							}else if ($('#datepicker1').is(':visible') && $('#datepicker2').is(':visible') && $('#datepicker3').is(':visible') && $('#datepicker4').is(':visible') && (value1 == "" || value2=="" || value3=="" || value4=="" )) {
								alert("Please Enter the slot timing to move ahead !");
								event.preventDefault();
							}else if($('#datepickeremail').is(':visible') && $('#datepickeremail').val() == ""){
								alert("Please Enter the slot timing to move ahead !");
								event.preventDefault();
							}else if($("#cvinput").is(':visible') && !document.getElementById('cvinput').files[0]){
								alert("Please Upload your CV to move ahead !");
								event.preventDefault();
							}else if($("#query").val() == ""){
								alert("Please enter the Query you want to send to the Advisor.");
								event.preventDefault();
							}else if ($('#dphone').is(':visible') && input_p=='') {
								alert("Please enter your phone number.");
								event.preventDefault();
							
							}else if ($('#dphone').is(':visible') && !is_phone) {
								alert("Phone number entered is not valid.");
								event.preventDefault();
							}
							else {
								if($('#datepicker1').is(':visible') && $('#datepicker2').is(':visible') && $('#datepicker3').is(':visible') && $('#datepicker4').is(':visible')){
									if(value1 == value2 || value1 == value3 || value1 == value4	){
									alert("Oops, you've chosen two same date & time slots.");
									event.preventDefault();
									}else if (value2 == value1 || value2 == value3 || value2 == value4) {
										alert("Oops, you've chosen two same date & time slots.");
										event.preventDefault();
									} else if (value3 == value1 || value3 == value2 || value3 == value4) {
										alert("Oops, you've chosen two same date & time slots.");
										event.preventDefault();
									}else if (value4 == value1 || value4 == value2 || value4 == value3) {
										alert("Oops, you've chosen two same date & time slots.");
										event.preventDefault();
									}
								}
							}	
				}	
			else{
					alert("Choose a mode of communication ! Email, Phone or Webchat ?");
					event.preventDefault();
				}
		});
	});
	$('#pop').on('click', function (e) {
            $('[data-toggle="popover"]').popover('hide');
        
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
