<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="We would love to hear from you! Call us +91 9999828839 or mail us at contactus@advisorcircuit.com">
    <meta name="author" content="Advisor Circuit">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Contact us | Advisor Circuit</title>
	  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  
  </head>

  <body>

  <div class="container">
	<%@include file="/Header.jsp" %>
		
        <!--main container(middle)-->
		<div class="contact content">
			
			<h1 class="text-center">We would love to hear from you!</h1>
			
            <div class="row">
            	<div class="col-md-6">
                	<div class="row contact-detail">
                    	<div class="col-md-5 col-md-offset-2 text-center">
                        	<img src="assets/img/Icon_Call.png" width="55" alt="Call us">
                    		<h4>Call Us</h4>
                    		<h5>+91 9999372087</h5>
                        </div>
                        <div class="col-md-4 text-center">
                        	<img src="assets/img/Icon_Mail.png" width="55" alt="Mail Us">
                    		<h4>Mail Us</h4>
                    		<h5>contactus@advisorcircuit.com</h5>
                        </div>
                    </div>
                </div>
                <!-- /col -->
                
                <div class="col-md-6 text-center social-link">
                	<a href="https://www.facebook.com/advisorcircuit" target="blank"><img src="assets/img/Icon_FB.png" width="55" alt="Facebook"></a>
                    <a href="https://twitter.com/advisorcircuit" target="blank"><img src="assets/img/Icon_Twitter.png" width="55" alt="Twitter"></a>
                    <a href="https://www.linkedin.com/company/advisor-circuit?trk=ppro_cprof" target="blank"><img src="assets/img/Icon_LinkedIn.png" width="55" alt="LinkedIn"></a>
                    <a href="https://plus.google.com/u/0/b/111112371994148232700/111112371994148232700/about/p/pub" target="blank"><img src="assets/img/Icon_Google.png" width="55" alt="Google+"></a>                    <h4>Advisor Circuit @ social media</h4>
                </div>
                <!-- /col -->
            </div>
            <!-- /row -->
					
			<form role="form" method="post" action="ContactUs">
            	<h3 class="text-center">Drop in a message and we will get back to you in no time!</h3>
                
				<div class="row">
					<div class="col-lg-6">
                        <div class="form-group col-lg-12" id="dfname">
                            <label>Name</label>
                            <input id="fname" type="text" name="name" class="form-control" style="width:100%">
                        </div>
                        <p class="required" id="required_name">Field Required</p>
                        <p class="required" id="invalid_name">Invalid Name</p>
                        
                        <div class="form-group col-lg-12">
                            <label>Email Address</label>
                            <input id="email" type="email" name="email" class="form-control" style="width:100%" required="required">
                        </div>
                        <p class="required" id="required_email">Field Required</p>
                        <p class="required" id="invalid_mail">Invalid Email</p>
								
                        <div class="form-group col-lg-12" id="dphone">
                            <label>Phone Number</label>
                            <input id="phone" type="tel" name="phone" class="form-control" style="width:100%">
                        </div>
                        <p class="required" id="required_phone">Field Required</p>
                        <p class="required" id="invalid_phone">Invalid Number</p>
								
					</div>
                    <!-- /col -->
                           
                    <div class="col-lg-6" id="dpost">
                    	<div class="form-group col-lg-12">
                        	<label>Message</label>
                        	<textarea id="post" class="form-control" rows="7" style="width:100%;" name="message" maxlength="1200"></textarea>
                        	<p class="required" id="required_message">Please enter a message</p>
                        </div>
                        
                        <div class="form-group col-lg-12">
                        	<input type="hidden" name="save" value="contact">
                            <button type="submit" id ="submit_btn" class="btn"></button>	
                        </div>
                    </div>
                    <!-- /col -->
				</div>
            </form>
            <!-- /form -->
				
		</div>
        <!-- /content -->
			
	<%@include file="/Footer.jsp" %>
			
	</div>
	<!--main container-->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-1.11.0.js"></script>
	<script type='assets/js/jquery.min.js'></script>
	<script>
	$(document).ready(function() {
	
		$("#submit_btn").click(function(event){
				var input_n = $("#fname").val();
				var filtern = /^[a-z A-Z ]+$/; 
				var is_name = filtern.test(input_n);
				if(input_n=='')
				{
					$("#required_name").show();
					$("#invalid_name").hide();
					event.preventDefault();
				}
				else if (!is_name){
					name_flag = 1;
					$("#required_name").hide();
					$("#invalid_name").show();
					$("#dfname").addClass("has-error");
					event.preventDefault(); 
				}	
				else{
					name_flag = 0;
					$("#invalid_name").hide();
					$("#required_name").hide();
					$("#dfname").removeClass("has-error");
				}
			});
			
			
		<!--email-->
		$("#submit_btn").click(function(event){
				var input_e = $("#email").val();
				var re = /^[a-zA-Z0-9.com!$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
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
					$("#invalid_mail").hide();
					$("#demail").removeClass("has-error");
				}		
				
		});
			
		<!--message-->
		$("#submit_btn").click(function(event){
			var input = $("#post").val();
			if (input==''){
				$("#dpost").addClass("has-error");
				$("#required_message").show();
				event.preventDefault(); 
			}else{
				$("#required_message").hide();
				$("#dpost").removeClass("has-error");
			}	
		});
		
		$("#submit_btn").click(function(event){
			var input_p = $("#phone").val();
			var filter = /^\d{10}$/; 
			var is_phone = filter.test(input_p);
			if(!input_p=='')
			{	
				 if (!is_phone){
					$("#invalid_phone").show();
					$("#dphone").addClass("has-error");
					event.preventDefault();
				}else{
					$("#invalid_phone").hide();
					$("#dphone").removeClass("has-error");
				}
			}
		});
	});
	</script>
  </body>
</html>