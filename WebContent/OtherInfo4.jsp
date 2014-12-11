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
	
	    		<div id="signupbox1" style=" margin-top:50px" class="mainbox col-md-8 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Advisor Registration</div>
                        </div>  
                     <div class="panel-body" >	                         
	    	   			<form id="signupform" class="form-horizontal" role="form" action="AdvisorRegistrationOtherInfoControler" method="post">
	    			
								<!--part3-->
								<div id="part3">
								<table style="width:100%;text-align:center;background-color:#f8f8f8;" cellpadding="10">
								<tr>
								<td><h5 class="font_size">General Information</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
								<td><h5 class="font_size">Education Information</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></td>
								<td style="background-color:#E1E1E1"><h5 class="font_size">Other Information</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
								<td><h5 class="font_size">Professional Background</h5></td>
								<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
								<td><h5 class="font_size">Service Information</h5></td>
								</tr>
								</table>
                                <hr>
                                	<div class="form-group" id="daward">
										<label for="icode" class="col-md-3 control-label">Achievements and Awards</label>
	                                    <div class="col-md-4">
	                                        <textarea id="award" class="form-control" name="achievement" rows="3"></textarea>  
										</div>
										<div class="col-md-2">	
	                                         <button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right"  data-content="Award for <b>Excellent Salesmanship</b> in 2010. <br>Part of teams which got nominated and won several prestigious awards in advertising">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               		</div>
                                	</div>
                                	 <div class="form-group" id="dskill">
										<label for="icode" class="col-md-3 control-label">Key Skills</label>
	                                    <div class="col-md-4">
	                                        <textarea id="skill" class="form-control" name="keyskills" rows="3"></textarea>  
										</div>
										<div class="col-md-2">
	                                        <button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right" data-content="Specialise in market research and understanding consumer behaviour.<br>Extensive knowledge about the field of advertising.<br>How to prepare for a career in digital marketing.<br> How to work with multi-cultural teams. ">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>  
	                               		</div>
                                	</div>
                                	<div class="form-group" id="dhobby">
										<label for="icode" class="col-md-3 control-label">Hobbies and interests</label>
	                                    <div class="col-md-4">
	                                        <textarea id="hobby" class="form-control" name="hobbies" rows="3"></textarea>  
										</div>
										<div class="col-md-2">
	                                        <button type="button" style="float: right;" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="I am an avid reader and love fiction. I also am a big fan of the theatre and try catching plays whenever I can.">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               		</div>
                                	</div>
                                	<div class="form-group" id="dfact">
										<label for="icode" class="col-md-3 control-label">A fun fact about yourself</label>
	                                    <div class="col-md-4">
	                                        <textarea id="fact" class="form-control" name="funfact" rows="3"></textarea>  
										</div>
										<div class="col-md-2">
	                                        <button type="button" style="float: right;" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="I love animals. I have had various pets since I was a child ranging from a dog, a cat, a hamster, a parrot and a squirrel!">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>  
	                               		</div>
                                	</div>
									<hr>
									 <div class="form-group">
                                    <!-- Button -->                                        
	                                    <div class="col-md-offset-3 col-md-9">
	                                        <button id="btn-signup" type="submit" class="btn btn-info">Submit And Continue</button>
	                                    </div>
                                    </div>
							</div><!--part3-->	
                     </form>
					 <div class="col-md-12">
                             	<img alt="logo" style="width: 300px;margin-left:75%;margin-bottom:-10%" src="assets/img/logo_black.png">
                      </div>
                   </div>
                 </div>
               </div>
      <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>            
    <script>
    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
	<script>
      $(function () { $('.popover-show').popover('show');});
      $(function () { $('.popover-hide').popover('hide');});
      $(function () { $('.popover-destroy').popover('destroy');});
      $(function () { $('.popover-toggle').popover('toggle');});
     $(function () { $(".popover-options a").popover({html : true });});
   </script>
   <script type='assets/js/javascript' src='jquery.min.js'></script>

		<script>
		$(document).ready(function() {
		<!--under can't be blank-->
			$("#btn-signup").click(function(event){
				var input_city = $("#award").val();
				if (input_city==''){
					$("#daward").addClass("has-error");
					event.preventDefault(); 
				}	
				else{
					$("#daward").removeClass("has-error");
				}
			});
			<!--job can't be blank-->
			$("#btn-signup").click(function(event){
				var input_city = $("#skill").val();
				if (input_city==''){
					$("#dskill").addClass("has-error");
					event.preventDefault(); 
				}	
				else{
					$("#dskill").removeClass("has-error");
				}
			});
			$("#btn-signup").click(function(event){
				var input_city = $("#hobby").val();
				if (input_city==''){
					$("#dhobby").addClass("has-error");
					event.preventDefault(); 
				}	
				else{
					$("#dhobby").removeClass("has-error");
				}
			});
			$("#btn-signup").click(function(event){
				var input_city = $("#fact").val();
				if (input_city==''){
					$("#dfact").addClass("has-error");
					event.preventDefault(); 
				}	
				else{
					$("#dfact").removeClass("has-error");
				}
			});
			
		});		
		</script>

</body>
</html>