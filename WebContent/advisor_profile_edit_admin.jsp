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
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		@media only screen and (max-width:700px){
		.font_size{font-size:22px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
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

	<div style="height:40px"></div>
	
	    <div class="container">    
        <div id="signupbox1" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Sign Up</div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" action="RegistrationController" method="post">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                    
                                <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="name" placeholder="Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Gender</label>
                                    <div class="col-md-9">
                                        <select class="dropreg" name="gender">
										  <option value="male">male</option>
										  <option value="female">female</option>
										</select>
                                    </div>
                                </div>
								 <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Age</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="age" placeholder="">
                                    </div>
                                </div>
								
								 <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Phone Number</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="phone" placeholder="">
                                    </div>
                                </div>
                               	<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Email Address</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="email" placeholder="">
                                   	</div>
                               </div>
                                    
                                    
                         			<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Current City</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="city" placeholder="">
                                    </div>
                                	</div>
                                	
                             	 <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Nationality</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="nationality" placeholder="">
                                    </div>
                                </div>
                                
                            	<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Industry</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="industry" placeholder="">
                                    </div>
                                </div>
								<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Occupation</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="occupation" placeholder="">
                                    </div>
                                </div>
                                	<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Introduction</label>
                                    <div class="col-md-9">
                                        <textarea name="introduction" class="form-control" rows="3"></textarea> 
                                    </div>
                                </div>
								<h4>Current Occupation</h4>
								<hr>
								 <div class="form-group">
									<label for="icode" class="col-md-3 control-label">Name of Organisation</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="organisation" placeholder="">
                                    </div>
                                </div>
								
								<div class="form-group">
									<label for="icode" class="col-md-3 control-label">Job Title</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="job" placeholder="">
                                    </div>
                                </div>
								<hr>	
								
								<div class="form-group">
									<label for="icode" class="col-md-3 control-label">Experience in Relevant Industry</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="experience" placeholder="">
                                    </div>
                                </div>
                                
                                <h4>Education</h4>
                                <hr>
                                <div class="form-group">
									<label for="icode" class="col-md-3 control-label">Undergraduate</label>
                                    <div class="col-md-9">
                                        <textarea name="ug" class="form-control" rows="3"></textarea>  
                                    </div>
                                </div>
                                 <div class="form-group">
									<label for="icode" class="col-md-3 control-label">Post Graduate</label>
                                    <div class="col-md-9">
                                        <textarea name="pg" class="form-control" rows="3"></textarea>  
                               	</div>
                                </div>
                                 <div class="form-group">
									<label for="icode" class="col-md-3 control-label">Any other</label>
                                    <div class="col-md-9">
                                        <textarea name="others" class="form-control" rows="3"></textarea>  
                               		</div>
                                </div>	
                                
							<h4>Professional Background</h4>
								<hr>
								<div id ="ProfessionalBackground">
									<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Introduction</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="introduction_professionalbackground" rows="3"></textarea>  
	                               		</div>
                                	</div>
									<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Company</label>
	                                    <div class="col-md-9">
	                                        <input type="text"  class="form-control" name="company[]" placeholder="">  
	                               		</div>
                                	</div>
                                	<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Designation</label>
	                                    <div class="col-md-9">
	                                        <input type="text" class="form-control" name="designation[]" placeholder="">  
	                               		</div>
                                	</div>
                                	 <div class="form-group">
										<label for="icode" class="col-md-3 control-label">Description</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="description[]" rows="3"></textarea>  
	                               		</div>
                                	</div>
                                	<a href="#ProfessionalBackground" id="add-profession" class="add-profession">Add More Professional Background</a>
                               	</div>
                                	<hr>
                                	<div class="form-group" id="achievement">
										<label for="icode" class="col-md-3 control-label">Achievements and Awards</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="achievement" rows="3"></textarea>  
	                               		</div>
                                	</div>
                             	    <hr>
                            	    <div class="form-group" id="achievement">
										<label for="icode" class="col-md-3 control-label">Know Your Advisor Better</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="knowyouradvisor" rows="3"></textarea>  
	                               		</div>
                                	</div>
                                	<hr>
                                	<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Key Skills</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="keyskills" rows="3"></textarea>  
	                               		</div>
                                	</div>
                                	<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Hobbies and interests</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="hobbies" rows="3"></textarea>  
	                               		</div>
                                	</div>
                                	<div class="form-group">
										<label for="icode" class="col-md-3 control-label">A fun fact about yourself</label>
	                                    <div class="col-md-9">
	                                        <textarea class="form-control" name="funfact" rows="3"></textarea>  
	                               		</div>
                                	</div>
                               	<h4>Services</h4>
                               		<hr>
                               		<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Services</label>
	                                    <div class="col-md-9 ">
						                     <div class="panel panel-default">
						                        <div class="panel-heading">
						                                
							                                        Career Talk<input type="checkbox" id="careerTalkCheckBox" name="services" value="careertalk"/>
							                                        Mock Interview<input type="checkbox" id="mockInterviewCheckBox" name="services" value="mockinterview"/>
							                                        CV Critique<input type="checkbox" id="cvCritiqueCheckBox" name="services" value="cvcritique"/>
							                                        Personal Workshops<input type="checkbox" id="personalWorkshopCheckBox" name="services" value="personalworkshops"/>  
						                                 
						                        </div>
						                        <div id="collapseOne" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            	<h4>Career Talk</h4>
				                           				<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    <div class="col-md-9">
						                                        <textarea class="form-control1" name="careertalkdescription" rows="3"></textarea>  
						                               		</div>
					                                	</div>
					                                	<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Mode Of Communication</label>
						                                    <div class="col-md-9 ">
						                                       	Phone<input type="checkbox" id="careertalkmodephone" name="careertalkmode" value="phone"/>
						                                        Email<input type="checkbox" id="careertalkmodeemail" name="careertalkmode" value="email"  />
						                                        WebChat<input type="checkbox" id="careertalkmodewebchat" name="careertalkmode" value="webchat"  />
						                               		</div>
					                                	</div>
					                                	<div id="collapsecareertalkpricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="careertalkpricephone" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                	<div id="collapsecareertalkpricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="careertalkpricewebchat" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                	<div id="collapsecareertalkpriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="careertalkpriceemail" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
                                					</div>
						                        </div>
						                        <div id="collapseTwo" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            	<h4>Mock Interview</h4>
						                            	<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    <div class="col-md-9">
						                                        <textarea class="form-control1" name="mockinterviewdescription" rows="3"></textarea>  
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
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="mockinterviewpricephone" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                	
					                                	<div id="collapsemockinterviewpriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="mockinterviewpriceemail" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                 	<div id="collapsemockinterviewpricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="mockinterviewpricewebchat" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
                                					</div>
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
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    <div class="col-md-9">
						                                        <textarea class="form-control1" name="cvcritiquedescription" rows="3"></textarea>  
						                               		</div>
					                                	</div>
					                                	<hr>
					                                	<div id="collapsecvcritiquepricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="cvcritiquepricephone" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                	
					                                	<div id="collapsecvcritiquepriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="cvcritiquepriceemail" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                 	<div id="collapsecvcritiquepricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="cvcritiquepricewebchat" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                	
					                                	
                                					</div>
						                        </div>
						                        <div id="collapseFour" class="panel-collapse collapse">
						                            <div class="panel-body">
						                            <h4>Personal Workshops</h4>
						                            
						                          		<div class="form-group">
															<label for="icode" class="col-md-3 control-label">Description</label>
						                                    <div class="col-md-9">
						                                        <textarea class="form-control1" name="personalworkshopdescription" rows="3"></textarea>  
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

					                                	
					                                	<div id="collapsepersonalworkshoppricephone" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Phone</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="personalworkshoppricephone" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                	
					                                	<div id="collapsepersonalworkshoppriceemail" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Email</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="personalworkshoppriceemail" placeholder="">  
			                               							</div>
			                               							<hr>
		                                						</div>
	                                						</div>
	                                					</div>
					                                	<hr>
					                                	
					                                 	<div id="collapsepersonalworkshoppricewebchat" class="panel-collapse collapse">
						                           			 <div class="panel-body">
																<div class="form-group">
																	<h4>Web Chat</h4>
																	<hr>
																	<label for="icode" class="col-md-3 control-label">Price</label>
		                                    						<div class="col-md-9">
			                                        					<input type="text" class="form-control1" name="personalworkshoppricewebchat" placeholder="">  
			                               							</div>
			                               							<hr>
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
                                	<hr>
                                
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info">Sign Up</button>
                                    </div>
                                </div>
     
                            </form>
                           <div class="panel-group" id="accordion">

                            </div>
                         </div>
                    </div> 
         </div> 
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
  </body>
</html>
