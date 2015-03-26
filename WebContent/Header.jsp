	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	 <% 
			Boolean isLoggedIn=false;
			String username="";
			int  userd =0;
			int advisord = 0;
				if(session.getAttribute("userId") !=null ){
					isLoggedIn=true;
					username=(String)session.getAttribute("username");
					userd = (Integer)session.getAttribute("userId");
				}else if( session.getAttribute("advisorId") !=null){
					isLoggedIn=true;
					username = "Admin";
					advisord = (Integer)session.getAttribute("advisorId");
				}else if( session.getAttribute("admin") != null &&  (Boolean)session.getAttribute("admin")){
					isLoggedIn=true;
					username = "Admin";
				}
				pageContext.setAttribute("isLoggedIn", isLoggedIn);
		%>    <!-- Fixed navbar -->
  <head>
      <title><%= request.getParameter("title") %> </title>
      
  </head>
<fmt:bundle basename="Resources.Dependency" prefix="path.">
  
<div class="user-link">
        <div>
    	<c:choose>
    		<c:when test="<%=isLoggedIn %>">
    			<c:if test="<%=advisord != 0%>">
					<a href="myaccount">My Account</a>
				</c:if>
				<c:if test="<%=userd != 0%>">
					<a href="usermyaccount">My Account</a>
				</c:if>
				<c:if test="<%=advisord == 0 && userd == 0%>">
					<a href="AdminRequests?pending=true">My Account</a>
				</c:if>
				<a href="Logout">Logout</a>
    		</c:when>
    		<c:otherwise>
    			<a href="login">Login/Sign Up</a>
    		</c:otherwise>
    	</c:choose>
        </div>
        </div>
        
    	<div class="navbar navbar-default">    
		<div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
           
           	<a class="navbar-brand" href=<fmt:message key="home"/>><img src="assets/img/logo-black.png" width="150"></a>
         </div>
		<div id="navbar" class="navbar-collapse collapse" aria-expanded="true">
        	<ul class="nav navbar-nav">
            	<li class="orange dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Advisors &#9662</a>
				<ul class="dropdown-menu">
					<c:url value="Advisors" var="CareerTalk">
   						<c:param name="service" value="CareerTalk"/>
					</c:url>
					<li><a href="${CareerTalk}">Career Talk</a></li>
					<c:url value="Advisors" var="MockInterview">
   						<c:param name="service" value="MockInterview"/>
					</c:url>
					<li><a href="${MockInterview}">Mock Interview</a></li>
					<c:url value="Advisors" var="CVCritique">
   						<c:param name="service" value="CVCritique"/>
					</c:url>
					<li><a href="${CVCritique}">Resume Critique</a></li>
					<c:url value="Advisors" var="PersonalWorkshop">
   						<c:param name="service" value="PersonalWorkshop"/>
					</c:url>
					<li><a href="${PersonalWorkshop}">Personal Workshop</a></li>
					<c:url value="Advisors" var="All">
   						<c:param name="service" value="All"/>
					</c:url>
					<li><a href="${All}">All Advisors</a></li>
				</ul>
				</li>
            	<li class="green"><a href="becomeanadvisor">Become an Advisor</a></li>
                <li class="blue"><a href="whyusac">Why use Advisor Circuit</a></li>
				<li class="red"><a href="howitworks">How it Works</a></li>
			</ul>
        	<div class="navbar-form navbar-right">
            <form class="" role="form" action="Search" method="post" id="searchform">
                <div class="input-group">
                <input type="text" class="form-control" name="search" id="searchval" placeholder="Enter your Industry" required="required" required autofocus>
                     <span class="input-group-btn">
                     <button class="btn btn-default" type="submit" onsubmit="CheckSearch(event)"><i class="glyphicon glyphicon-search"></i></button> 
                     </span>
                 </div>
            </form>
			</div>
        </div>
		<!--/.nav-collapse -->

    </div>
	   	</fmt:bundle>
	
    
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script>
	$(document).ready(function(){
		$('.dropdown').hover(function(){ 
			  $('.dropdown-toggle', this).trigger('click'); 
		});
		function CheckSearch(event){
			if($("#searchval").val()== ""){
				event.preventDefault();
			}
		}
	});	
</script>
