<!DOCTYPE html>
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
    <title>Advisor Registration</title>  		 
	
    <!-- Bootstrap Core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="assets/plugins/timeline.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="assets/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	<%
	Boolean isInvalidUsername= (Boolean)request.getAttribute("isInvalidUsername");
	Boolean isInvalid= (Boolean)request.getAttribute("isInvalid");
	%>
</head>
<body>
		 
        <div class="container">
        <%@include file="/Header.jsp" %>

          <div class="text-center">
               <h2>We would love to have you as an Advisor!</h2>
               <h4 style="margin-top:25px">
               Just follow the below steps to register:
               </h4>
               <p style="line-height:26px;">
               1. Decide which services you want to offer (refer to <a href="FAQs">FAQs</a> and <a href="howitworks">How it Works</a> page).</br>
               2. Fill out the below form with as much accuracy and detail as possible. Your profile will be created based on the same.</br>
               3. We will review your form and get back to you within 72 hours.</br>
               </p> 
               <h4 style="margin-top:25px; color:#555">
               Enter your email address and create your password to fill the registration form :
               </h4>
               
               <div class="row">
               <div class="mainbox col-md-4 col-md-offset-4">
               		<form id="forgot_password_form" class="form-horizontal" role="form" action="AdvisorRegistrationEmail" method="post">
                           <div class="form-group" id="demail">
                                <div class="col-md-12">
                                    <input type="email" id ="email" name="email" class="form-control" placeholder="Email Id" style="width:100%;">
                                    <p class="required text-left" id="required_email">Field Required</p>
                                    <p class="required text-left" id="invalid_mail">Invalid Email</p>
                                </div>
                           </div>
                           <div class="form-group" id="dsignup-password">
                                <div class="col-md-12">
                                    <input id="signup-password" type="password" class="form-control" name="passwd" placeholder="Password" style="width:100%;">
                                    <p class="required text-left" id="required_pass">Field Required</p>
                                </div>
                           </div>
                           <div class="form-group" id="dconfirm-password">
                                <div class="col-md-12">
                                    <input id="confirm-password" type="password" class="form-control" name="confirmpasswd" placeholder="Confirm Password" style="width:100%;">
                                    <p class="required text-left" id="required_confirm_pass">Field Required</p>
                                    <p class="required text-left" id="invalid_pass">Password does not match</p>
                                </div>
                           </div>	
                           <c:if test="<%=isInvalid != null && isInvalid %>">
                           <h5>Whoa! Looks like your email id already exists with us. </h5>
                            </c:if>	
                                                       				
                           <div class="form-group">
                               <!-- Button -->                                        
                               <div class="col-md-12">
                                   <button id="btn-signup" type="Submit" class="btn btn-info">Submit</button>
                                    <!--<button id="btn" type="submit" class="btn btn-info">Cancel</button>	-->
                               </div>
                           </div>
                       </form>
				</div>
               </div>
               
               <h4>(You can save your form in the middle and continue later!)</h4>
               <h4><a  data-toggle="modal" data-target="#datepay">Forgot Password ?</a></h4>
                                         
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
                                         <div class="col-md-7">
                                              <input id = "emailId" type="text" name="email" class="form-control" placeholder="Enter your email">
                                         </div>
                                    </div>
                                    <c:if test="<%= isInvalidUsername != null && isInvalidUsername %>">
                                    <c:out value="Invalid Username"></c:out>
                                    </c:if>
                                    <div class="form-group">
                                        <!-- Button -->                                        
                                        <div class="col-md-offset-3 col-md-9">
                                            <button id="btn" onclick="GetForgotPasswordAction()" type="button" class="btn btn-info">Submit</button>
                                            <!--<button id="btn" type="submit"  class="btn btn-info">Cancel</button>	-->
                                        </div>
                                    </div>
                                    <div id="forgot">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                     
             </div>

        <%@include file="/Footer.jsp" %>
        </div>
</body>
<script src="assets/js/jquery-1.11.0.js"></script>
<script type='assets/js/jquery.min.js'></script>
<script src="assets/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	var mail_flag;
$("#btn-signup").click(function(event){
		var input_e = $("#email").val();
		var re = /^[a-zA-Z0-9.!$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		var is_email=re.test(input_e);
		if(input_e=='')
		{
			$("#required_email").show();
			$("#invalid_mail").hide();
			event.preventDefault();
		}
		else if (!is_email){
			mail_flag=1;
			$("#invalid_mail").show();
			$("#required_email").hide();
			$("#demail").addClass("has-error");
				event.preventDefault();
		}else{
			mail_flag=0;
			$("#required_email").hide();
			$("#demail").removeClass("has-error");
		}
		var input_pwd = $("#signup-password").val();
		if (input_pwd==''){
			$("#dsignup-password").addClass("has-error");
			$("#required_pass").show();
			event.preventDefault(); 
		}
		else
			{	
				$("#required_pass").hide();
				$("#dsignup-password").removeClass("has-error");
			}
		
		var input_pwd = $("#confirm-password").val();
		var input_pwd1 = $("#signup-password").val();
		if (input_pwd==''){
			$("#required_confirm_pass").show();
			$("#dconfirm-password").addClass("has-error");
			event.preventDefault(); 
		}else if (input_pwd != input_pwd1) {
			$("#invalid_pass").show();
			$("#required_confirm_pass").hide();
			event.preventDefault(); 
		}else
			{
				$("#dconfirm-password").removeClass("has-error");
				$("#invalid_pass").hide();
				$("#required_confirm_pass").hide();
			}
			});
});
</script>
<script type="text/javascript">
 function div_show() {
	 document.getElementById('forgot_password').style.display = "block";
 }
 function div_hide() {
	 document.getElementById('forgot_password').style.display = "none";
}
 function GetForgotPasswordAction(){
	 var input = document.getElementById('emailId').value;
		$.ajax({
		    url : 'AdvisorForgotPassword', // Your Servlet mapping or JSP(not suggested)
		    data : {"email" : input,"register" :"true"},
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
 </script>
</html>