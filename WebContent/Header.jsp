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
	Boolean isPopUpViewed = (Boolean)session.getAttribute("isPopUpViewed");
	Boolean showPopUp;
	if(isPopUpViewed != null){
		showPopUp = !isPopUpViewed;
	}else{
		showPopUp = true;
		session.setAttribute("isPopUpViewed", true);
	}
	pageContext.setAttribute("isLoggedIn", isLoggedIn);
%>    <!-- Fixed navbar -->
<head>
	<title><%= request.getParameter("title") %> </title>
</head>
<div class="modal fade" role="dialog" aria-labelledby="ac-promotion-onload" id="ac-promotion-onload">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <button type="button" class="dismiss" data-dismiss="modal" aria-hidden="true">X</button>
                    <div class="ribbon">
                        <div class="ribbon-strip strip-1"></div>
                        <div class="ribbon-strip strip-2"></div>
                        <div class="ribbon-strip strip-3"></div>
                        <div class="ribbon-strip strip-4"></div>
                    </div>
                    <h3 style="text-align:center;">Sign up for FREE within seconds</h3>
                    <p style="text-align:center;"><span>Be the first to receive exclusive offers and promotions</span></p>
                    <form action="">
                        <input id="pop-up-name" type="text" placeholder="Enter your Name" required/>
                        <input id="pop-up-email" type="email" placeholder="Enter your Email" required/>
                        <input id="pop-up-password" type="password" placeholder="Enter your Password" required/>
                        <button type="button" onclick="PopUpSignUp()">SIGN ME UP!</button>
                        <span>By Signing up I accept <a href="">Terms of Service</a></span>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
					<a href="#" type="button" data-toggle="modal" data-target="#ac-login" data-keyboard="false" data-backdrop="static">Log In</a>
					<a href="#" type="button" data-toggle="modal" data-target="#ac-signup" data-keyboard="false" data-backdrop="static">Sign Up</a>
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
							<h1 style="margin-bottom:30px;">Login</h1>
							<form action="/" method="post">
								<div class="field-wrap field-input">
									<label>
										Email<span class="req">*</span>
									</label>
									<input type="email" name="email" required autocomplete="off"/>
									<span></span>
								</div>
								<div class="field-wrap field-input">
									<label>
										Password<span class="req">*</span>
									</label>
									<input type="password" name="password" required autocomplete="off"/>
								</div>
								<button type="submit" class="button button-block"/>Sign In</button>
								<div class="field-wrap field-toggle-modal">
									<label>
										<a href="#" ac-target="#ac-signup">
											Don't have an account? Signup for free.
										</a>
									</label>
								</div>
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
							<div class="col-sm-12 col-md-6 covers">
								<div class="cover cover-user">
									<div class="cover-row">
										<div class="icon icon-1"></div>
										<div class="desc">
											<p class="">Manage Your Orders <br/> Easily Track Orders, Create Returns</p>
										</div>
									</div>
									<div class="cover-row">
										<div class="icon icon-2"></div>
										<div class="desc">
											<p class="">Make Informed Decisions <br/> Get Relevant Alerts And Recommendations</p>
										</div>
									</div>
									<div class="cover-row">
										<div class="icon icon-3"></div>
										<div class="desc">
											<p class="">Engage Socially <br/> With Wishlists, Reviews, Ratings</p>
										</div>
									</div>
								</div>
								<div class="cover cover-advisor hide">
									<div class="cover-row">
										<div class="icon icon-1"></div>
										<div class="desc">
											<p class="">Manage Your Orders <br/> Easily Track Orders, Create Returns</p>
										</div>
									</div>
									<div class="cover-row">
										<div class="icon icon-2"></div>
										<div class="desc">
											<p class="">Make Informed Decisions <br/> Get Relevant Alerts And Recommendations</p>
										</div>
									</div>
									<div class="cover-row">
										<div class="icon icon-3"></div>
										<div class="desc">
											<p class="">Engage Socially <br/> With Wishlists, Reviews, Ratings</p>
										</div>
									</div>
								</div>
							</div>
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
												<div class="field-wrap field-input password">
													<label>
														Password<span class="req">*</span>
													</label>
													<input type="password" name="password" required autocomplete="off"/>
												</div>
												<div class="field-wrap field-input confirmpassword hide">
													<label>
														Confirm Password<span class="req">*</span>
													</label>
													<input type="confirmpassword" name="confirmpassword" required autocomplete="off"/>
												</div>
												<button type="submit" class="button button-block"/>Get Started</button>
												<div class="field-wrap field-checkbox">
													<input type="checkbox" name="newsletter" id="newsletter" required checked/>
													<label for="newsletter">
														Newsletter Signup
													</label>
												</div>
												<div class="field-wrap field-checkbox">
													<label>
														<a href="">
															Terms and Conditions
														</a>
													</label>
												</div>
												<div class="field-wrap field-toggle-modal">
													<label>
														<a href="#" ac-target="#ac-login">
															Already have an account? Log In.
														</a>
													</label>
												</div>
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
												<div class="field-wrap field-input password">
													<label>
														Password<span class="req">*</span>
													</label>
													<input type="password"required autocomplete="off"/>
												</div>
												<div class="field-wrap field-input confirmpassword hide">
													<label>
														Confirm Password<span class="req">*</span>
													</label>
													<input type="confirmpassword" name="confirmpassword" required autocomplete="off"/>
												</div>
												<button type="submit" class="button button-block"/>Get Started</button>
												<div class="field-wrap field-checkbox">
													<input type="checkbox" name="newsletter" id="newsletter" required checked/>
													<label for="newsletter">
														Newsletter Signup
													</label>
												</div>
												<div class="field-wrap field-checkbox">
													<label>
														<a href="">
															Terms and Conditions
														</a>
													</label>
												</div>
												<div class="field-wrap field-toggle-modal">
													<label>
														<a href="#" ac-target="#ac-login">
															Already have an account? Log In.
														</a>
													</label>
												</div>
											</form>
										</div>
									</div>
								</div>
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

		$('.ac-header-theme-modal .password').on('keyup', 'input', function(){
			var $input = $(this);
			$input.val().length>0?$input.parent('.password').next('.confirmpassword').removeClass('hide'):$input.parent('.password').next('.confirmpassword').addClass('hide');
		});

		$('.field-wrap.field-toggle-modal').on('click','a', function(e){
			e.stopPropagation();
			e.preventDefault();
			var $link = $(this);
			$link.parents('.modal').modal('hide');
			setTimeout(function(){
				$($link.attr('ac-target')).modal('show');
			},0);
		});
	});
	$(window).on('load',function(){
           if(<%=showPopUp && !isLoggedIn%>){
	          $('#ac-promotion-onload').modal('show');
           }
	});
	
	function PopUpSignUp(){
			 var ajax = false;
             var name =$("#pop-up-name").val();
             var email=$("#pop-up-email").val();
             var password =$("#pop-up-password").val();
			 $.ajax({
		 	    url : 'UserRegistration', // Your Servlet mapping or JSP(not suggested)
		 	    data : {"emailFromAjax" : email},
		 	    type : 'POST',
		 	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
		 	   cache: false
		 	}).done(function(result) {
		        if (result == "true") {
		        	Registration();
		        	}else {
		        		alert("Whoa! Looks like your email id already exists with us.");
		        }
		    }).fail(function() {
		        alert('ERROR');
		    });
	          
	}
	
	function Registration(){
		 var name =$("#pop-up-name").val();
         var email=$("#pop-up-email").val();
         var password =$("#pop-up-password").val();
		   $.ajax({
               url : 'UserRegistration', // Your Servlet mapping or JSP(not suggested)
               data : {"email" :email,"passwd":password,"fullname":name},
               type : 'POST',
               dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
               success : function(response) {
              	       $('#ac-promotion-onload').modal('hide');
              	       $('#ac-login').modal('show');
               },
               error : function(request, textStatus, errorThrown) {
               }
           }); 
	}
</script>

