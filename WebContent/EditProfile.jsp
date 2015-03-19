<!DOCTYPE html>
<%@page import="javax.swing.text.StyledEditorKit.BoldAction"%>
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
		<style type="text/css"> 
		.required{display:none;color:red;}				
		.form-control{width:100% !important;}
		body{background-color:#fff !important}
		@font-face {
			font-family:"custom_light";
			src: url("assets/fonts/NGCDMC__.TTF") /* EOT file for IE */
		}
		
		@font-face {
			font-family:"custom_bold";
			src: url("assets/fonts/NGCDBC__.TTF") /* TTF file for CSS3 browsers */
		}

		h1,h2,h3,h5,label{
			font-family:'custom_bold' !important;
			}
			
		h4{ font-family:'custom_light' !important;
			font-size:19px !important;
			}	
		div{font-family:'custom_light' !important;} 
		 p{font-family:'custom_light' !important;}
		.fa-lg{font-size:1.4em !important;}
		.social{padding-bottom:9px !important;}
		.foot-size{font-size:14px !important;}
		.foot-color{color:#717171;}
		.gray{color:#575758;}
		.red{color:#B80000;}
		@media only screen and (max-width:700px){
		
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		}
		 </style>  		 
	
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
	<%  Boolean invalidEmail = (Boolean)request.getAttribute("invalidEmail");
	%>
</head>
<body>
		 
		    <div style="margin-left:5.9%;margin-right:5.9%">
				<%@include file="/Header.jsp" %>
			</div>
			<div class="container">	
			 <div id="PDF" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
	              <div class="panel panel-info">
	  						
	                   <div class="panel-body" >
	                   <div>
	                       <form id="forgot_password_form" class="form-horizontal" role="form" action="AdvisorEditProfileEmail" method="post">
	                           
	                            <div class="panel-heading">
	                         			<div class="panel-title" style="font-size:26px">Advisor Registration
	                        			</div>
	                				</div>
	                					<hr>
	                           <div class="form-group" id="demail">
	                           		<label for="icode" class="col-md-3 control-label">Email Id To Register With</label>
	                                <div class="col-md-7">
						 				<input type="email" id ="email" name="email" class="form-control" placeholder="Enter your email ID">
					 				</div>
					 				<div class="col-md-2">
										<p class="required" id="required_email">Field Required</p>
										<p class="required" id="invalid_mail">Invalid Email</p>
									</div>
	                           </div>			                           				
	                           <div class="form-group">
	                               <!-- Button -->                                        
	                               <div class="col-md-offset-3 col-md-9">
	                                   <button id="btn-signup" type="Submit" class="btn btn-info">Submit</button>
										<!--<button id="btn" type="submit" class="btn btn-info">Cancel</button>	-->
	                               </div>
	                           </div>
	                       </form>
	                       </div>
	                    </div>
	                 </div> 
	         	</div>
	         </div>
	        <div style="margin-left:5.9%;margin-right:5.9%">	
	    		<%@include file="/Footer.jsp" %>
	    	</div>
</body>
<script src="assets/js/jquery-1.11.0.js"></script>
<script type='assets/js/jquery.min.js'></script>
<script src="assets/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	if(<%=invalidEmail%>){
		$("#invalid_mail").show();
	}else{
		$("#invalid_mail").hide();
	}
	
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
	});
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
</html>