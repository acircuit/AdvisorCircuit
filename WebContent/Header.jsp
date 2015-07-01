<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	Boolean isLoggedIn=false;
	String username="";
	int  userd =0;
	int advisord = 0;
	if( session.getAttribute("admin") != null &&  (Boolean)session.getAttribute("admin")){
		isLoggedIn=true;
		username = "Admin";
	}
	else if(session.getAttribute("userId") !=null ){
			isLoggedIn=true;
			username=(String)session.getAttribute("username");
			userd = (Integer)session.getAttribute("userId");
	}
	else if( session.getAttribute("advisorId") !=null){
			isLoggedIn=true;
			username=(String)session.getAttribute("username");
			advisord = (Integer)session.getAttribute("advisorId");
	} 
	pageContext.setAttribute("isLoggedIn", isLoggedIn);
%>    <!-- Fixed navbar -->
<head>
	<title><%= request.getParameter("title") %> </title>
</head>

<fmt:bundle basename="Resources.Dependency" prefix="path.">
	<div class="user-link">
		<div>
			<c:if test="<%=isLoggedIn %>">
				<%@include file="/Notification.jsp" %>
			</c:if>	
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
					<a href="#" type="button" data-toggle="modal" data-target="#ac-login">Log In</a>
					<a href="#" type="button" data-toggle="modal" data-target="#ac-signup">Sign Up</a>
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
				<li class="orange dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Advisors &#9662</a>
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
				<li class="orange"><a href="https://www.advisorcircuit.com/blog/" target="blank">Blog</a></li>
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

	<div class="modal fade ac-header-theme-modal" role="dialog" aria-labelledby="ac-login" id="ac-login">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="dismiss" data-dismiss="modal" aria-hidden="true">X</button>
					<div class="form">
						<div class="tab-content">
							<h1>Login</h1>
							<form action="/" method="post">
								<div class="field-wrap field-input">
									<label>
										Email<span class="req">*</span>
									</label>
									<input type="email" name="email" required autocomplete="off"/>
								</div>
								<div class="field-wrap field-input">
									<label>
										Password<span class="req">*</span>
									</label>
									<input type="password" name="password" required autocomplete="off"/>
								</div>
								<button type="submit" class="button button-block"/>Sign In</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade ac-header-theme-modal" role="dialog" aria-labelledby="ac-signup" id="ac-signup">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="dismiss" data-dismiss="modal" aria-hidden="true">X</button>
					<div class="form-container">
						<div class="row">
							<div class="col-sm-12 col-md-6">
								<div class="form">
									<ul class="tab-group">
										<li class="tab active"><a href="#user">User</a></li>
										<li class="tab"><a href="#advisor">Advisor</a></li>
									</ul>
									<div class="tab-content">
										<div id="user">   
											<h1>Sign Up for Free</h1>
											<form action="/" method="post">
												<div class="field-wrap field-input">
													<label>
														Name<span class="req">*</span>
													</label>
													<input type="text" name="name" required autocomplete="off" />
												</div>
												<div class="field-wrap field-input">
													<label>
														Email<span class="req">*</span>
													</label>
													<input type="email" name="email" required autocomplete="off"/>
												</div>
												<div class="field-wrap field-input">
													<label>
														Password<span class="req">*</span>
													</label>
													<input type="password" name="password" required autocomplete="off"/>
												</div>
												<div class="field-wrap field-checkbox">
													<input type="checkbox" name="newsletter" id="newsletter" required checked/>
													<label for="newsletter">
														Newsletter Signup
													</label>
												</div>
												<div class="field-wrap field-checkbox">
													<input type="checkbox" name="tnc" id="tnc" required checked/>
													<label for="tnc">
														Terms and Conditions
													</label>
												</div>
												<button type="submit" class="button button-block"/>Get Started</button>
											</form>
										</div>
										<div id="advisor">
											<h1>Sign Up for Free</h1>
											<form action="/" method="post">
												<div class="field-wrap field-input">
													<label>
														Name<span class="req">*</span>
													</label>
													<input type="text" required autocomplete="off" />
												</div>
												<div class="field-wrap field-input">
													<label>
														Email<span class="req">*</span>
													</label>
													<input type="email"required autocomplete="off"/>
												</div>
												<div class="field-wrap field-input">
													<label>
														Password<span class="req">*</span>
													</label>
													<input type="password"required autocomplete="off"/>
												</div>
												<div class="field-wrap field-checkbox">
													<input type="checkbox" name="newsletter" id="newsletter" required checked/>
													<label for="newsletter">
														Newsletter Signup
													</label>
												</div>
												<div class="field-wrap field-checkbox">
													<input type="checkbox" name="tnc" id="tnc" required checked/>
													<label for="tnc">
														Terms and Conditions
													</label>
												</div>
												<button type="submit" class="button button-block"/>Get Started</button>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-12 col-md-6 hidden-xs hidden-sm covers">
								<div class="cover cover-user">User</div>
								<div class="cover cover-advisor hide">Advisor</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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

		$('.ac-header-theme-modal .form').find('input, textarea').on('keyup blur focus', function (e) {
			
			var $this = $(this),
			label = $this.prev('label');

			if (e.type === 'keyup') {
				if ($this.val() === '') {
					label.removeClass('active highlight');
				} else {
					label.addClass('active highlight');
				}
			} else if (e.type === 'blur') {
				if( $this.val() === '' ) {
					label.removeClass('active highlight'); 
				} else {
					label.removeClass('highlight');   
				}   
			} else if (e.type === 'focus') {
				
				if( $this.val() === '' ) {
					label.removeClass('highlight'); 
				} 
				else if( $this.val() !== '' ) {
					label.addClass('highlight');
				}
			}

		});

		$('.ac-header-theme-modal .tab a').on('click', function (e) {
			
			e.preventDefault();
			
			$(this).parent().addClass('active');
			$(this).parent().siblings().removeClass('active');
			
			var target = $(this).attr('href'),
				plainTarget = target.replace(/#/g,'');

			$('.covers .cover').not('.cover-'+plainTarget).addClass('hide');
			$('.tab-content > div').not(target).hide();

			$(target).fadeIn(600);
			$('.covers .cover.cover-'+plainTarget).removeClass('hide').fadeIn(600);
		});
	});
</script>
