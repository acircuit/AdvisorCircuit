<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
				Boolean isLogged=false;
				if(session.getAttribute("userId") !=null ){
					isLogged=true;
				}else if( session.getAttribute("advisorId") !=null){
					isLogged=true;
				}else if( session.getAttribute("admin") != null &&  (Boolean)session.getAttribute("admin")){
					isLogged=true;
				}
				pageContext.setAttribute("isLoggedIn", isLogged);
		%>    <!-- Fixed navbar -->
	<script src="assets/js/google-analytics.js"></script>
	
	<div class="footer">	
		<div class="container">
			<div class="row">
            	
                <div class="col-md-2 links">
					<img src="assets/img/AdvisorCircuit_Logo_BW-01.png" class="img-responsive" align="advisor circuit" width="150" height="100" alt="Advisor Circuit Logo">
				</div><!--end col-1-->
                
                <div class="col-md-2 links">
                <fmt:bundle basename="Resources" prefix="path.">
				   <h4><a href=<fmt:message key="home"/>Home</a></h4>
			    </fmt:bundle>
					<h4>Services</h4>
					<div class="subnav">
					<c:url value="Advisors" var="CareerTalk">
   						<c:param name="service" value="CareerTalk"/>
					</c:url>
					<a href="${CareerTalk}">Career Talk</a>
					<c:url value="Advisors" var="MockInterview">
   						<c:param name="service" value="MockInterview"/>
					</c:url>
					<a href="${MockInterview}">Mock Interview</a>
					<c:url value="Advisors" var="CVCritique">
   						<c:param name="service" value="CVCritique"/>
					</c:url>
					<a href="${CVCritique}">Resume Critique</a>
					<c:url value="Advisors" var="PersonalWorkshop">
   						<c:param name="service" value="PersonalWorkshop"/>
					</c:url>
					<a href="${PersonalWorkshop}">Personal Workshop</a>
					</div>
				</div>
                <!--end col-2-->
                
                <div class="col-md-2 links">
                    <h4><a href="aboutus">About Us</a></h4>
                    <c:url value="Advisors" var="All">
                            <c:param name="service" value="All"/>
                    </c:url>
                    <h4><a href="${All}">Advisors</a></h4>
                    <h4><a href="becomeanadvisor">Become an Advisor</a></h4>
                    <h4><a href="contactus">Contact Us</a></h4>
				</div>
                <!--end col-3-->
                
                <div class="col-md-2 links">
                    <h4><a href="FAQs">FAQs</a></h4>
                    <h4><a href="howitworks">How it Works</a></h4>
                    <h4><a href="terms">Terms and Conditions</a></h4>
                    <h4><a href="privacy">Privacy Policy</a></h4>
				</div>
                <!--end col-4-->
                
                <div class="col-md-2 social-link">
                	<ul class="list-unstyled">
                    	<li><a href="https://www.linkedin.com/company/advisor-circuit?trk=ppro_cprof" target="blank"><img src="assets/img/Icon_LinkedIn_BW.png" alt="" width="35" align="absmiddle"> Linkedin</a></li>
                        <li><a href="https://www.facebook.com/advisorcircuit" target="blank"><img src="assets/img/Icon_FB_BW.png"  alt="" width="35" align="absmiddle"> Facebook</a></li>
                        <li><a href="https://twitter.com/advisorcircuit" target="blank"><img src="assets/img/Icon_Twitter_BW.png"  alt="" width="35" align="absmiddle"> Twitter</a></li>
                        <li><a href="https://plus.google.com/u/0/b/111112371994148232700/111112371994148232700/about/p/pub" target="blank"><img src="assets/img/Icon_Google_BW.png" alt="" width="35" align="absmiddle"> Google</a></li>
                    </ul>
                </div>
                <!--end col-5-->
                <c:choose>
                	<c:when test="<%= isLogged != null && isLogged %>">
                		<div class="col-md-2 links">
							<h4><a href="login">Logout</a></h4>
						</div>
                	</c:when>
                	<c:otherwise>
                		 <div class="col-md-2 links">
							<h4><a href="login">Log In</a></h4>
						</div>
                	</c:otherwise>
                </c:choose>
               
                <!--end col-6-->
                
            </div>
            
            <div class="row">
        	<div class="col-md-10 col-lg-offset-2">
            	<h4><b>Reach us at</b> <span><i class="glyphicon glyphicon-envelope" style="color:#575758; margin-left:20px"></i> contactus@advisorcircuit.com</span>
				<span><i class="glyphicon glyphicon glyphicon-earphone" style="color:#575758; margin-left:20px"></i> +919999828839</span></h4>
            </div>
        </div>
		</div>
	</div><!-- /footer -->
	
    <div style="height:10px;"></div>