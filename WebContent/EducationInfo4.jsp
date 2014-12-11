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
	<style type="text/css"> 
		.img-responsive{width:28px !important} 
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		.font_size{font-size:16px !important;}
		@media only screen and (max-width:700px){
		.font_size{font-size:12px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:12px !important;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:12px !important;}
		}
		 </style>  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
</head>
<body>
	
	    		<div id="signupbox1" style=" margin-top:50px" class="mainbox col-md-8  col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Advisor Registration</div>
                        </div>  
                     <div class="panel-body" >	                         
	    	   			<form id="signupform" class="form-horizontal" role="form" action="AdvisorRegistrationEducationInfoController" method="post">
	    			
							<div id="part2">
								<table style="width:100%;text-align:center;background-color:#f8f8f8;" cellpadding="10">
								<tr>
								<td><h5 class="font_size">General Information</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
								<td style="background-color:#E1E1E1"><h5 class="font_size">Education Information</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></td>
								<td><h5 class="font_size">Other Information</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
								<td><h5 class="font_size">Professional Background</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
								<td><h5 class="font_size">Service Information</h5></td>
								</tr>
								</table>
								<h4>Education</h4>
                                <hr>
                                <div class="form-group" id="dunder">
									<label for="icode" class="col-md-3 control-label">Undergraduate</label>
                                    <div class="col-md-9">
                                         <textarea id="under" name="ug" style="margin-top: 20px" class="form-control" rows="3"></textarea>  
                                    </div>
                                </div>
                                 <div class="form-group" id="dpost">
									<label for="icode" class="col-md-3 control-label">Post Graduate</label>
                                    <div class="col-md-9">
                                        <textarea id="post" name="pg" style="margin-top: 20px" class="form-control" rows="3"></textarea>  
                               	</div>
                                </div>
                                 <div class="form-group" id="dother" >
									<label for="icode" class="col-md-3 control-label">Any other</label>
                                    <div class="col-md-9">
                                        <textarea id="other" name="others" style="margin-top: 20px" class="form-control" rows="3"></textarea>  
                               		</div>
                                </div>	
								<hr>
								 <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info">Submit and Continue</button>
                                    </div>
                                </div>
							 </div><!--part2-->	
                     </form>
					 <div class="col-md-12">
                             	<img alt="logo" style="width: 300px;margin-left:75%;margin-bottom:-10%" src="assets/img/logo_black.png">
                      </div>
                   </div>
                 </div>
                </div>
</body>
<script src="assets/js/jquery-1.11.0.js"></script>
<script type='assets/js/javascript' src='jquery.min.js'></script>

		<script>
		$(document).ready(function() {
		<!--under can't be blank-->
			$("#btn-signup").click(function(event){
				var input_city = $("#under").val();
				if (input_city==''){
					$("#dunder").addClass("has-error");
					event.preventDefault(); 
				}	
			});
			<!--job can't be blank-->
			$("#btn-signup").click(function(event){
				var input_city = $("#post").val();
				if (input_city==''){
					$("#dpost").addClass("has-error");
					event.preventDefault(); 
				}	
			});
			$("#btn-signup").click(function(event){
				var input_city = $("#other").val();
				if (input_city==''){
					$("#dother").addClass("has-error");
					event.preventDefault(); 
				}	
			});
			
		});		
		</script>
</html>