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
		<script src="assets/js/jquery-1.11.0.js"></script>
	    <%@include file="/Header.jsp" %>
	    		<div id="signupbox1" style=" margin-top:50px" class="mainbox col-md-8 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Advisor Registration</div>
                        </div>  
                     <div class="panel-body" >	                         
	    	   			<form id="signupform" class="form-horizontal" role="form" action="AdvisorRegistrationServicesController" method="post">
	    			
							
									<!--part5--->
									<div id="part5">
									
									<table style="width:100%;text-align:center;background-color:#f8f8f8;" cellpadding="10">
										<tr>
										<td><h5 class="font_size">General Information</h5></td>
										<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
										<td><h5 class="font_size">Education Information</h5></td>
										<td><img class="img-responsive" src="assets/img/right.png" alt=""></td>
										<td><h5 class="font_size">Other Information</h5></td>
										<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
										<td><h5 class="font_size">Professional Background</h5></td>
										<td><img class="img-responsive" src="assets/img/right.png" alt=""></span></td>
										<td style="background-color:#E1E1E1"><h5 class="font_size">Service Information</h5></td>
										</tr>
									</table>
									<h4>Services</h4>
                               		<hr>
                               		<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Services</label>
	                                    <div class="col-md-9 ">
														<div class="checkbox">
															<label>
																<input type="checkbox" id="careerTalkCheckBox" name="services" value="careertalk">Career Talk
															</label>
														</div>
														<div id="collapseOne" class="panel-collapse collapse">
						                            	<h4>Career Talk</h4>
														<hr>
				                           				<div class="form-group" id="dcareerTalk_description">
															<div class="col-md-3">
															<label for="icode" class="col-md-3 control-label">Description</label>
															</div>
						                                    <div class="col-md-5">
						                                        <textarea id="careerTalk_description" class="form-control" name="careertalkdescription" rows="3"></textarea> 
						                       
						                               		</div>
															<div class="col-md-2">	
																		<button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right"  data-content="Ask me anything you want about the profession I love and have worked in for over 10 years">
										   								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               							</div>
					                                	</div>
					                                	<div class="form-group" id="dcareerTalk_mode">
															<div class="col-md-3">
															<label for="icode" class="col-md-3 control-label">Mode Communication</label>
						                                    </div>
															<div class="col-md-8">
						                                       	<input type="checkbox" id="careertalkmodephone" name="careertalkmode" value="phone"/> Phone
						                                        <input type="checkbox" id="careertalkmodeemail" name="careertalkmode" value="email"  /> Email
						                                        <input type="checkbox" id="careertalkmodewebchat" name="careertalkmode" value="webchat"  /> WebChat
						                               		</div>
					                                	</div>
														<hr>
					                                	<div id="collapsecareertalkpricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group" id="dcareerTalk_phone">
																	<h4>Phone</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input id="careerTalk_phone" type="text" class="form-control" name="careertalkpricephone" placeholder="">  
			                               							</div>
		                                						</div>
	                                						</div>
														<hr>	
	                                					</div>
					                                	
					                                	
					                                	<div id="collapsecareertalkpricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group" id="dcareerTalk_chat">
																	<h4>Web Chat</h4>
														
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input id="careerTalk_chat" type="text" class="form-control" name="careertalkpricewebchat" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                	<div id="collapsecareertalkpriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group" id="dcareerTalk_mail">
																	<h4>Email</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input id="careerTalk_mail" type="text" class="form-control" name="careertalkpriceemail" placeholder="">  
			                               							</div>
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
						                        </div>
														<div class="checkbox">
															<label>
																<input type="checkbox" id="mockInterviewCheckBox" name="services" value="mockinterview">Mock Interview
															</label>
														</div>
														<div id="collapseTwo" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            	<h4>Mock Interview</h4>
														<hr>
						                            	<div class="form-group">
															<div class="col-md-3">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                </div>   
														   <div class="col-md-5">
						                                        <textarea class="form-control" name="mockinterviewdescription" rows="3"></textarea> 
						                               		</div>
															<div class="col-md-2">	
																		<button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right"  data-content="I have conducted interviews and recruited over 50 individuals for various positions and feel confident that I can give you a close to reality interview experience">
										   								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               							</div> 
					                                	</div>
					                                	<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Mode Of Communication</label>
						                                    <div class="col-md-9 ">
						                                       	Phone<input type="checkbox" id="mockinterviewmodephone" name="mockinterviewmode" value="phone"/>
						                                        Email<input type="checkbox" id="mockinterviewmodeemail" name="mockinterviewmode" value="email"  />
						                                        WebChat<input type="checkbox" id="mockinterviewmodewebchat" name="mockinterviewmode" value="webchat"  />
						                               		</div>
					                                	</div>
														<hr>
														<div id="collapsemockinterviewpricephone" class="panel-collapse collapse">
																
															<div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="mockinterviewpricephone" placeholder="">  
			                               							</div>
			                        
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                	
					                                	<div id="collapsemockinterviewpriceemail" class="panel-collapse collapse">
														   
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="mockinterviewpriceemail" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                 	<div id="collapsemockinterviewpricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="mockinterviewpricewebchat" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
																
	                                						</div>
															<hr>
	                                					</div>
                                					</div>
													
						                        </div>
														<div class="checkbox">
															<label>
																<input type="checkbox" id="cvCritiqueCheckBox" name="services" value="cvcritique">CV Critique
															</label>
														</div>
														<div id="collapseThree" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            	<h4>CV Critique</h4>
					                                	<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Mode Of Communication</label>
						                                    <div class="col-md-9 ">
						                                       	Phone<input type="checkbox" id="cvcritiquemodephone" name="cvcritiquemode" value="phone"/>
						                                        Email<input type="checkbox" id="cvcritiquemodeemail" name="cvcritiquemode" value="email"  />
						                                        WebChat<input type="checkbox" id="cvcritiquemodewebchat" name="cvcritiquemode" value="webchat"  />
						                               		</div>
					                                	</div>
														
					                                	<div class="form-group">
															<div class="col-md-3">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    </div>
															<div class="col-md-5">
						                                        <textarea class="form-control" name="cvcritiquedescription" rows="3"></textarea>  
						                                        
						                               		</div>
															 <div class="col-md-2">	
																		<button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right"  data-content="I have scanned and shortlisted various resume over the span of my career and know what recruiters look for in potential candidates.">
										   								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               								</div>
					                                	</div>
					                                	<hr>
														
					                                	<div id="collapsecvcritiquepricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="cvcritiquepricephone" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                	
					                                	<div id="collapsecvcritiquepriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="cvcritiquepriceemail" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                 	<div id="collapsecvcritiquepricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="cvcritiquepricewebchat" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
                                					</div>
						                        </div>
														<div class="checkbox">
															<label>
																<input type="checkbox" id="personalWorkshopCheckBox" name="services" value="personalworkshops">Personal Workshops
															</label>
														</div>
														<div id="collapseFour" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            <h4>Personal Workshops</h4>
														<hr>
						                          		<div class="form-group">
															<div class="col-md-2">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    </div>
															<div class="col-md-5">
						                                        <textarea class="form-control" name="personalworkshopdescription" rows="3"></textarea>
						                                      
						                               		</div>
															<div class="col-md-2">	
																		<button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right"  data-content="We can cover some main social media marketing tools and how these new age platforms can make or break a brand these days.">
										   								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               								</div>
					                                	</div>
						                            
					                                	<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Mode Of Communication</label>
						                                    <div class="col-md-9 ">
						                                       	Phone<input type="checkbox" id="personalworkshopmodephone" name="personalworkshopmode" value="phone"/>
						                                        Email<input type="checkbox" id="personalworkshopmodeemail" name="personalworkshopmode" value="email"  />
						                                        WebChat<input type="checkbox" id="personalworkshopmodewebchat" name="personalworkshopmode" value="webchat"  />
						                               		</div>
					                                	</div>
														<hr>	
					                                	
					                                	<div id="collapsepersonalworkshoppricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="personalworkshoppricephone" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
	                                					</div>
					                                	
					                                	
					                                	
					                                	<div id="collapsepersonalworkshoppriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="personalworkshoppriceemail" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
	                                					</div>
					                                	
					                                	
					                                 	<div id="collapsepersonalworkshoppricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="personalworkshoppricewebchat" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<div id="collapseFour" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            <h4>Personal Workshops</h4>
														<hr>
						                          		<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    <div class="col-md-9">
						                                        <textarea class="form-control" name="personalworkshopdescription" rows="3"></textarea>
						                                        <div class="col-md-2">	
																		<button type="button" style="float: right;" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-placement="right"  data-content="We can cover some main social media marketing tools and how these new age platforms can make or break a brand these days.">
										   								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               								</div>   
						                               		</div>
					                                	</div>
						                            
					                                	<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Mode Of Communication</label>
						                                    <div class="col-md-9 ">
						                                       	Phone<input type="checkbox" id="personalworkshopmodephone" name="personalworkshopmode" value="phone"/>
						                                        Email<input type="checkbox" id="personalworkshopmodeemail" name="personalworkshopmode" value="email"  />
						                                        WebChat<input type="checkbox" id="personalworkshopmodewebchat" name="personalworkshopmode" value="webchat"  />
						                               		</div>
					                                	</div>
														<hr>	
					                                	
					                                	<div id="collapsepersonalworkshoppricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="personalworkshoppricephone" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                	
					                                	<div id="collapsepersonalworkshoppriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="personalworkshoppriceemail" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	
					                                	
					                                 	<div id="collapsepersonalworkshoppricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control" name="personalworkshoppricewebchat" placeholder="">  
			                               							</div>
			                               							
		                                						</div>
	                                						</div>
															<hr>
	                                					</div>
					                                	<hr>
                                					</div>
                                					
						                        </div>
                                			</div>
						                 </div>
														
										</div>
								</div>
						                       
									<hr>
	                                <div class="form-group">
	                                    <!-- Button -->                                        
	                                    <div class="col-md-offset-3 col-md-9">
	                                        <button id="btn-signup" type="submit" class="btn btn-info">Submit</button>
	                                    </div>
	                                </div>
							</div><!--part3-->	
							</div>
							</div>
                     </form>
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
   <script>
	$(document).ready(function(){
	  $("#btn-signup").click(function(event){
		 var careerTalk_checked= $("#careerTalkCheckBox").is(':checked'); 
		 var input_careerTalk_description = $("#careerTalk_description").val();
		 var careerTalk_checked_phone= $("#careertalkmodephone").is(':checked'); 
		 var input_careerTalk_phone = $("#careerTalk_phone").val();
		 var careerTalk_checked_mail= $("#careertalkmodeemail").is(':checked'); 
		 var input_careerTalk_mail = $("#careerTalk_mail").val();
		 var careerTalk_checked_chat= $("#careertalkmodewebchat").is(':checked'); 
		 var input_careerTalk_chat = $("#careerTalk_chat").val();
		
		 if(careerTalk_checked == true)
		 {
			 if (input_careerTalk_description==''){
					$("#dcareerTalk_description").addClass("has-error");
					event.preventDefault(); 
				}	
				
				else{
						$("#dcareerTalk_description").removeClass("has-error");
					if(careerTalk_checked_phone||careerTalk_checked_chat||careerTalk_checked_mail==true)	
						{
							$("#dcareerTalk_mode").removeClass("has-error");
							if(careerTalk_checked_phone==true)
								{
									if (input_careerTalk_phone==''){
										$("#dcareerTalk_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else{$("#dcareerTalk_phone").removeClass("has-error");}
								}
							else if(careerTalk_checked_mail==true)
								{
									if (input_careerTalk_mail==''){
										$("#dcareerTalk_mail").addClass("has-error");
										event.preventDefault(); 
									}
									else{$("#dcareerTalk_mail").removeClass("has-error");}
								}
							else if(careerTalk_checked_chat==true)
								{
									if (input_careerTalk_chat==''){
										$("#dcareerTalk_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else{$("#dcareerTalk_chat").removeClass("has-error");}
								}	
							}
				
						else{ 
							  $("#dcareerTalk_mode").addClass("has-error");	
							  event.preventDefault();
							}
				}
				
		}
				 
			 });
			//document ready
		});

   </script>
</body>
</html>