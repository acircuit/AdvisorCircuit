<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Log in to get the most out of Advisor Circuit.Don't have an account? Sign up today.">
    <meta name="author" content="">
    <fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>Log in & Start Networking | Advisor Circuit</title>
	 		 
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	<link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	    <% String isLoginFailed= (String)request.getAttribute("loginverificationfailed");
	    Boolean isInvalidUsername= (Boolean)request.getAttribute("isInvalidUsername");
	    Boolean isLoginFailedFromProfile =false;
	    if( request.getAttribute("loginverificationfailedfromprofile") != null){
	    	isLoginFailedFromProfile= (Boolean)request.getAttribute("loginverificationfailedfromprofile");
	    }

		pageContext.setAttribute("isLoginFailed", isLoginFailed);
		
		String advisorId = request.getParameter("aId");
		String fromProfile = request.getParameter("profile");
		   Cookie cookie = null;
		   Cookie[] cookies = null;
		   String remember = "";
		   // Get an array of Cookies associated with this domain
		   cookies = request.getCookies();
		   if( cookies != null ){
		      
		      for (int i = 0; i < cookies.length; i++){
		         cookie = cookies[i];
		         if(cookie.getName().equals("RememberMe")){
		        	 remember= cookie.getValue();
		        	 break;
		         }
		      }
		  }

		%>
  
  </head>
<body>	
	<div class="container">
		
	<%@include file="/Header.jsp" %>
    
    <div class="row">

        <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                     <div class="panel-heading" style="background-color:#66CCCC">
                        <div class="panel-title" style="font-size:28px; color:#575758">Sign In</div>
                    </div> 
     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" onsubmit="GetFormAction()"  method="post">
                                    
                            <div style="margin-bottom: 25px" class="input-group" id="dlogin-username">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="email" class="form-control" name="email" value="" placeholder="Email" maxlength="100">                                        
                                    </div>
                                    <c:if test="<%=advisorId != null && fromProfile!= null%>">
                                    	<input type="hidden" name="aId" value="<%=advisorId%>">
                                    	<input type="hidden" name="profile" value="<%=fromProfile%>">  
                                    </c:if>
                                    
		                            <div style="margin-bottom: 25px" class="input-group" id="dlogin-password">
		                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="Password" style="font-family:"custom-light" !important;" maxlength="125">
                                    </div>
                                    <c:if test="<%=isLoginFailedFromProfile %>">
                               			<h4 style="color: #c84c4e"><c:out value="Please Login as a user"></c:out></h4>
                               		</c:if>
	                            	<c:if test="${isLoginFailed == true}">
	                              		<h4 style="color: #c84c4e"><c:out value="Your login details are invalid. Did you select the right account?"></c:out></h4>
	                              	</c:if>
                                      <div style="margin-bottom: 25px; width:400px" class="input-group">
		                                   		<span style="float: left; font-size:17px"><b>Account Type</b></span>
			                                     <div class="col-md-9">
													  <select id = "type" name="type" >
													  	<option value="User">User</option>
													  	<option value="Advisor">Advisor</option>
													  </select>
												 </div>
									 </div>
	                              <div>
                              </div>      

                                
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label  style="font-size:17px">
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                </div>
                                <div class="input-group">
                                      <div >
                                        <label style="font-size:17px">
                                        	<a style="float: left" data-toggle="modal" data-target="#datepay">Forgot Password ?</a>
                                        </label>
                                      </div>
                                </div>


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                    <button id="btn-login" type="submit" class="btn btn-info" style="background-color:#66CCCC !important; font-size:17px">Login</button>
                                    </div>
                                </div>

                   
					
                                <div class="form-group">
                                    <div class="col-md-12 control"  style="font-size:17px">
                                        <div style="border-top: 1px solid #66CCCC; padding-top:15px; font-size:100%" >
                                            Don't have an account! 
                                        	<a href="#" data-toggle="modal" data-target="#usersignup">
                                            Sign Up as User
                                            </a>
                                            </br>
                                            Click <a href="Email">Here</a> to sign up as an Advisor
                                        </div>
                                    </div>
                                </div>    
                            </form>
                         
						<div class="modal fade" id="usersignup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-md">
								<div class="modal-content">
									<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
									<h4 class="modal-title" id="myModalLabel" style="text-align: center;">SIGN UP FOR FREE</h4>
									</div>
								<div class="modal-body">
									<form id="signupform" class="form-horizontal" role="form" enctype="multipart/form-data"  action="UserRegistration" method="post" >
										<div id="signupalert" style="display:none" class="alert alert-danger">
											<p>Error:</p>
											<span></span>
										</div>
											
										<div class="form-group" id="demail">
											<label for="email" class="col-md-3 control-label">Email</label>
											<div class="col-md-6">
												<input id="email" type="email" class="form-control" name="email" placeholder="Email Address" maxlength="150">
											</div>
											<div class="col-md-6">
												<p class="required" id="required_email" style="float: right;">Field Required</p>
												<p class="required" id="invalid_mail">Invalid Email</p>
												<p class="required" id="mailnotavailable">Whoa! Looks like your email id already exists with us.</p>
											</div>
										</div>
										 <div class="form-group" id="dsignup-password">
											<label for="password" class="col-md-3 control-label">Password</label>
											<div class="col-md-6">
												<input id="signup-password" type="password" class="form-control" name="passwd" placeholder="Password" maxlength="40">
											</div>
											<div class="col-md-6">
												<p class="required" id="required_pass" style="float: right;">Field Required</p>
											</div>
										</div>

											
										<div class="form-group">
											<label for="firstname" class="col-md-3 control-label" id="dfname">Full Name</label>
											<div class="col-md-6">
												<input id="fname" type="text" class="form-control" name="fullname" placeholder="Full Name" maxlength="100">
											</div>
											<div class="col-md-6">
												<p class="required" id="required_name" style="float: right;">Field Required</p>
												<p class="required" id="invalid_name">Invalid Name</p>
											</div>
										</div>
										<div class="form-group col-md-12 collapse" id="referralPromotion"> 
											<div class="accordion" id="accordion2">
											  <div class="accordion-group">
											    <div class="accordion-heading">
											    <label for="icode" class="col-md-3 control-label"></label>
											    <div class="col-md-6">
											      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="font-size:larger">
											        	Have a Referral Code ?
											      </a>
											       <div id="collapseOne" class="accordion-body collapse">
											      <div class="accordion-inner">
											        <input id="refcode" name="refcode" placeholder="Enter your referral code" class="form-control">
											    	<p class="required" id="required_promo">Field Required</p>
											    	<p class="required" id="invalid_promo">Invalid Referral Code</p>
											    	<p  id="valid_promo" style="display: none">Your Referral code has been applied. </p>
											         <button id="btn-promo" style="margin-top: 3%" type="button" class="btn btn-info" onclick="CheckReferralValidity()">Apply</button>
											      </div>
											    </div>
											     </div>
											    </div>
											   
											  </div>
										    </div>
									   </div>
									     <input type="hidden" id="refCodeUserId" name="refCodeUserId">
										    <input type="hidden" id="isPromoActive" name="isPromoActive" value="true">
										    <input type="hidden" style="display:none;" name="isCodeApplied" id="isCodeApplied" value="false">
										<div class="form-group">
											<!-- Button -->                                        
											<div class="col-md-offset-3 col-md-9">
												<h5>By registering you confirm that you accept the <a href="terms" target="blank">Terms & Conditions</a> and <a href="privacy" target="blank">Privacy Policy</a></h5>
								   				<input type="checkbox" id="agree" name="newsletter" checked="checked"><span style="padding: 1%">I wish to subscribe to recent news and updates. </span>  
												
								
												<div style="height:5px"></div>  
											</div>
										</div>    	
									  	<div class="form-group">
											<!-- Button -->                                        
											<div class="col-md-offset-3 col-md-9">
												<button id="btn-signup" type="submit" class="btn btn-info">Sign Up</button>
												<div style="height:5px"></div>  
											</div>
										</div>
									</form>
								</div>
							</div>
                     	</div>
                   	</div>
                     <div class="modal fade" id="datepay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
									<h4 class="modal-title" id="myModalLabel">Forgot Password?</h4>
								</div>
								<div class="modal-body">
									<form id="forgot_password_form" class="form-horizontal" role="form"  method="post">
		                                <div class="form-group">
		                                    <label for="icode" class="col-md-3 control-label">Email</label>
		                                     <div class="col-md-9">
												  <input id = "emailId" type="text" name="email" class="form-control" placeholder="Enter your email">
											 </div>
		                                </div>
		                                  <div class="form-group">
		                                    <label for="icode" class="col-md-3 control-label">Account Type</label>
		                                     <div class="col-md-9">
												  <select id = "forgotpasswordtype" name="type" >
												  	<option value="Advisor">Advisor</option>
												  	<option value="User">User</option>
												  </select>
											 </div>
										 </div>
		                                <c:if test="${isInvalidUsername}">
		                                <c:out value="Invalid Username"></c:out>
		                                </c:if>
		                                <div id="forgot">
		                                </div>
		                                <div class="form-group">
		                                    <!-- Button -->                                        
		                                    <div class="col-md-offset-3 col-md-9">
		                                        <button id="btn" onclick="GetForgotPasswordAction()" type="button" class="btn btn-info">Submit</button>
												<!--<button id="btn" type="submit"  class="btn btn-info">Cancel</button>	-->
		                                    </div>
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

	<%@include file="/Footer.jsp" %>
	</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="assets/js/acInviteNotification.js"></script>
<script>
	$(document).ready(function() {
		//Get whether promotion is active or not
		$.ajax({
            url : 'AdminPromotions', // Your Servlet mapping or JSP(not suggested)
            data : {"Referral" :true},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
            	if(response == "true"){
		         $("#referralPromotion").show();
            	}else{
   		         $("#isPromoActive").val("false");
            	}
               					// create an empty div in your page with some id
            },
            error : function(request, textStatus, errorThrown) {
                alert(errorThrown);
                
            }
        }); 	
		
	$("#btn-promo").click(function(event){
		var input_promo = $("#refcode").val();
		if(input_promo ==""){
			event.preventDefault(); 
			$("#required_promo").show();
		}else{
			$("#required_promo").hide();
		}
	});
$("#btn-login").click(function(event){
			
			var input_user = $("#login-username").val();
			if (input_user==''){
				$("#dlogin-username").addClass("has-error");
				event.preventDefault(); 
			}
			else
				{
					$("#dlogin-username").removeClass("has-error");
				}
			var input_pwd = $("#login-password").val();
			if (input_pwd==''){
				$("#dlogin-password").addClass("has-error");
				event.preventDefault(); 
			}	
			else
				{
					$("#dlogin-password").removeClass("has-error");
				}
		});
		$("#btn-signup").click(function(event){
			debugger;
			 event.preventDefault();
			 var val = $("#email").val();
				 $.ajax({
			 	    url : 'UserRegistration', // Your Servlet mapping or JSP(not suggested)
			 	    data : {"emailFromAjax" : val},
			 	    type : 'POST',
			 	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
			 	   cache: false
			 	}).done(function(result) {
			        if (result == "true") {
			        	validations(event);
			        	$("#mailnotavailable").hide();}
			        else {
			        	alert("Whoa! Looks like your email id already exists with us.");
			        	validate(event);
			        }
			    }).fail(function() {
			        alert('ERROR');
			    });
		 });
		function validate(event){
			debugger
var isPreventDefault = false;
			
			
			var input_n = $("#fname").val();
			var filtern = /^[a-z A-Z]+$/; 
			var is_name = filtern.test(input_n);
			if(input_n=='')
			{
				$("#required_name").show();
				$("#invalid_name").hide();
				event.preventDefault();
				isPreventDefault = true;

			}
			else if (!is_name){
				name_flag = 1;
				$("#required_name").hide();
				$("#invalid_name").show();
				$("#dfname").addClass("has-error");
				event.preventDefault(); 
				isPreventDefault = true;

			}	
			else{
				name_flag = 0;
				$("#invalid_name").hide();
				$("#required_name").hide();
				$("#dfname").removeClass("has-error");
			}
				
			
			var input_e = $("#email").val();
			var re = /^[a-zA-Z0-9.!$%&*+/=?^_`'{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
			var is_email=re.test(input_e);
			if(input_e=='')
			{
				$("#required_email").show();
				$("#invalid_mail").hide();
				event.preventDefault();
				isPreventDefault = true;

			}
			else if (!is_email){
				mail_flag=1;
				$("#invalid_mail").show();
				$("#required_email").hide();
				$("#demail").addClass("has-error");
					event.preventDefault();
					isPreventDefault = true;

			}else{
				mail_flag=0;
				$("#required_email").hide();
				$("#invalid_mail").hide();
				$("#demail").removeClass("has-error");
			}		
				var input_pwd1 = $("#signup-password").val();
				if (input_pwd==''){
					$("#required_confirm_pass").show();
					$("#dconfirm-password").addClass("has-error");
					event.preventDefault();
					isPreventDefault = true;

				}else
					{
						$("#dconfirm-password").removeClass("has-error");
						$("#invalid_pass").hide();
						$("#required_confirm_pass").hide();
					}


				var input_pwd = $("#signup-password").val();
				if (input_pwd==''){
					$("#dsignup-password").addClass("has-error");
					$("#required_pass").show();
					event.preventDefault(); 
					isPreventDefault = true;

				}
				else
					{	
						$("#required_pass").hide();
						$("#dsignup-password").removeClass("has-error");
					}
				
		}

	function validations(event){
		debugger;
		var isPreventDefault = false;
			
			var input_n = $("#fname").val();
			var filtern = /^[a-z A-Z]+$/; 
			var is_name = filtern.test(input_n);
			if(input_n=='')
			{
				$("#required_name").show();
				$("#invalid_name").hide();
				event.preventDefault();
				isPreventDefault = true;

			}
			else if (!is_name){
				name_flag = 1;
				$("#required_name").hide();
				$("#invalid_name").show();
				$("#dfname").addClass("has-error");
				event.preventDefault(); 
				isPreventDefault = true;

			}	
			else{
				name_flag = 0;
				$("#invalid_name").hide();
				$("#required_name").hide();
				$("#dfname").removeClass("has-error");
			}
			
			
			var input_e = $("#email").val();
			var re = /^[a-zA-Z0-9.!$%&*+/=?^_`'{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
			var is_email=re.test(input_e);
			if(input_e=='')
			{
				$("#required_email").show();
				$("#invalid_mail").hide();
				event.preventDefault();
				isPreventDefault = true;

			}
			else if (!is_email){
				mail_flag=1;
				$("#invalid_mail").show();
				$("#required_email").hide();
				$("#demail").addClass("has-error");
					event.preventDefault();
					isPreventDefault = true;

			}else{
				mail_flag=0;
				$("#required_email").hide();
				$("#invalid_mail").hide();
				$("#demail").removeClass("has-error");
			}		



				var input_pwd = $("#signup-password").val();
				if (input_pwd==''){
					$("#dsignup-password").addClass("has-error");
					$("#required_pass").show();
					event.preventDefault(); 
					isPreventDefault = true;

				}
				else
					{	
						$("#required_pass").hide();
						$("#dsignup-password").removeClass("has-error");
					}
		if(isPreventDefault){
			event.preventDefault(); 
		}else{
			$("#signupform").submit();
		}
	}	
	});
		
		
		
		
</script>
 <script type="text/javascript">
 function CheckReferralValidity(){
		var promo_code = $("#refcode").val();
		$.ajax({
         url : 'AdminPromotions', // Your Servlet mapping or JSP(not suggested)
         data : {"promo_code" :promo_code},
         type : 'POST',
         dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
         success : function(response) {
         	if(response != 0){
		         $("#valid_promo").show();
		         $("#required_promo").hide();
    		  	 $("#invalid_promo").hide();
    		  	 $("#btn-promo").hide();
    		  	 $("#refCodeUserId").val(response);
    				$("#refcode").prop("readonly", true);
    				$("#isCodeApplied").val(true);
         	}else{
         		  $("#invalid_promo").show();
			         $("#required_promo").hide();
			         $("#valid_promo").hide();
	    				$("#refcode").prop("readonly", false);
	    				$("#isCodeApplied").val(false);
         	}
            					// create an empty div in your page with some id
         },
         error : function(request, textStatus, errorThrown) {
             alert(errorThrown);
             
         }
     });
		
	}
 function div_show() {
	 document.getElementById('forgot_password').style.display = "block";
 }
 function div_hide() {
	 document.getElementById('forgot_password').style.display = "none";
}
 function GetFormAction(){
	var type =document.getElementById('type').value;
	if(type == "Advisor"){
		if(<%=fromProfile%>) {
		document.getElementById("loginform").action = "AdvisorLogin?aId=<%=advisorId%>&loginFromAdvisorProfile=true";
		}else{
			document.getElementById("loginform").action = "AdvisorLogin";
		}
	}else{
		if(<%=fromProfile%>) {
			document.getElementById("loginform").action = "UserLogin?aId=<%=advisorId%>&loginFromAdvisorProfile=true";
		}	else{
			document.getElementById("loginform").action = "UserLogin";

		}
	}
 }
 function GetForgotPasswordAction(){
	var type =document.getElementById('forgotpasswordtype').value;
	if(type == "Advisor"){
		var input = document.getElementById('emailId').value;
		$.ajax({
		    url : 'AdvisorForgotPassword', // Your Servlet mapping or JSP(not suggested)
		    data : {"email" : input},
		    type : 'POST',
		    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
		    success : function(response) {
		        $('#forgot').html(response); // create an empty div in your page with some id
		    },
		    error : function(request, textStatus, errorThrown) {
		        alert(errorThrown);
		    }
		}); 
	}else{
		var input = document.getElementById('emailId').value;
		$.ajax({
		    url : 'UserForgotPassword', // Your Servlet mapping or JSP(not suggested)
		    data : {"email" : input},
		    type : 'POST',
		    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
		    success : function(response) {
		        $('#forgot').html(response); // create an empty div in your page with some id
		    },
		    error : function(request, textStatus, errorThrown) {
		        alert(errorThrown);
		    }
		}); 
	}	 
 }
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