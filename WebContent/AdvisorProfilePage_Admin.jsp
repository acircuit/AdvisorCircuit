<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.ProfessionalBackgroundDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
<%@page import="org.AC.dto.AdvisorModeDTO"%>
<%@page import="org.AC.dto.AdvisorEducationDTO"%>

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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    
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
  <div class="container">
	<%@include file="/Header.jsp" %>

    <c:forEach items="${advisors}" var="advisor">
    	<div class="row profile-intro">
            <div class="col-md-6 profile_image" style="padding-left:0;">
            	<img src="${advisor.getImage()}" class="img-responsive"/>
            </div>
            <div class="col-md-6 intro text-center">
            	<h1>${advisor.getName()}</h1>
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
										<p style = "font-size: 18px!important">${advisor.getIntroduction()}</p>
								</div>
							</div>
                     	</div>
             	</div>
            
                <div class="book-session text-center">
                    <a href="#" id="book_a_session1" onclick="div_show(this)" class="modes">
                        <img src="assets/img/ProfileImages/Panel_Services_Book.png" height="65"></br>Book a Session
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
            
        	<h1>Skills</h1>
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
			          	<h2>Career Talk </h2>
                        <p class="careertalkdescription">${service.getDescription()}</p>
                        
			          	<div class="icon-group">
			          		<ul class="list-unstyled">
				           		<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('careertalk') }">
                                  <c:if test="${mode.getModeOfCommunication().equals('email')}">
                                      <li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" width="50"><span>Email</br>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('phone')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" width="50"><span>Phone</br>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" width="50"><span>Webchat</br>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
					            </c:if>
				            	</c:forEach>
		       				</ul>
			          	</div>
                      
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
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>
		        </c:if>
                
	           	<c:if test="${service.getService().equals('mockinterview')}">
			        <div id="mock-interview" class="col-md-6 service">
                    <div class="hpanel text-center">
                    	<h2>Mock Interview</h2>
                    	<p class="mockinterviewdescription">${service.getDescription()}</p>
                        
			          	<div class="icon-group">
			           		<ul class="list-unstyled">
				           		<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('mockinterview') }">
                                  <c:if test="${mode.getModeOfCommunication().equals('email')}">
                                      <li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" height="50"><span>Email<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('phone')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" height="50"><span>Phone<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" height="50"><span>Webchat<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
					            </c:if>
				            	</c:forEach>
		       				</ul>
			          	</div>
                        
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
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>    
		        </c:if>
                
   	           	<c:if test="${service.getService().equals('cvcritique')}">
			        <div id="cv" class="col-md-6 service">
                    <div class="hpanel text-center">
                    	<h2>Resume Critique </h2>
                    	<p class="cvcritiquedescription">${service.getDescription()}</p>
			          
			          	<div class="icon-group">
			          		<ul class="list-unstyled">
				           		<c:forEach items="${modes}" var="mode">
				          		<c:if test="${service.getAdvisorId() == mode.getAdvisorId() && mode.getService().equals('cvcritique') }">
                                  <c:if test="${mode.getModeOfCommunication().equals('email')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_Mail.png" height="50"><span>Email<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('phone')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" height="50"><span>Phone<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" height="50"><span>Webchat<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
					            </c:if>
				            	</c:forEach>
		       				</ul>
			          	</div>
                        
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
										<p style = "font-size: 18px!important"> ${service.getDescription()}</p>
								</div>
							</div>
                     	</div>
                   	</div>
		        </c:if>
		        
	             <c:if test="${service.getService().equals('personalworkshops')}">
			        <div id="workshop" class="col-md-6 service">
                    <div class="hpanel text-center">
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
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_Phone.png" height="50"><span>Phone<br/>Rs.${mode.getPrice()}</span></li>
                                  </c:if>
                                  <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
                                    <li><img src="assets/img/ProfileImages/Panel_4_Icon_WebChat.png" height="50"><span>Webchat<br/>Rs.${mode.getPrice()}</span></li>
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
								      		<div class="review"><img src="${user.getImage()}" class="img-responsive">
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
      
       
    <%@include file="/Footer.jsp" %>
    </div>
    <!-- end .container -->
 
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
