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

    <title>User Registration</title>
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
    
  <%	    Boolean isInvalidUsername= (Boolean)request.getAttribute("isInvalidUsername");
  			String isLoginFailed= (String)request.getAttribute("loginverificationfailed");

   	 		request.setAttribute("loginFromAdvisorProfile", true);
			pageContext.setAttribute("isInvalidUsername", isInvalidUsername);
			pageContext.setAttribute("isLoginFailed", isLoginFailed);
  %>
  </head>

  <body>
	<div style="height:40px"></div>
	
	    <div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title" style="font-size:26px">Sign In</div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" action="UserLoginController" method="post">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="email" class="form-control" name="email" value="" placeholder="email">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                                    </div>
                                <div>
                              	<c:if test="${isLoginFailed == true}">
                              		<c:out value="Login Invalid"></c:out>
                              	</c:if>
                              </div> 

                                
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                     <c:if test="${isInvalidUsername}">
	                                	<c:out value="Invalid Username"></c:out>
	                                </c:if>
                                      <div class="input-group">
	                                      <div >
	                                        <label>
	                                          <a style="float: left" onclick="div_show()">Forgot Password ?</a> 
	                                        </label>
	                                      </div>
                                	 </div>
                                    </div>


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                    <button id="btn-signup" type="submit" class="btn btn-info">Login</button>
                                      <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a>

                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid #ccc; padding-top:15px; font-size:100%" >
                                            Don't have an account! 
                                       	 <a href="#" onClick="$('#signupbox').hide(); $('#signupbox').show()">
                                            Sign Up Here
                                        </a>
                                        </div>
                                    </div>
                                </div>    
                            </form>     

        <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Sign Up</div>
                            <div style="float:right; font-size:100%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" enctype="multipart/form-data"  action="UserRegistration" method="post" >
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Email</label>
                                    <div class="col-md-9">
                                        <input type="email" class="form-control" name="email" placeholder="Email Address">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Password</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="passwd" placeholder="Password">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">Full Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="fullname" placeholder="Full Name">
                                    </div>
                                </div>
								<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Phone Number</label>
                                    <div class="col-md-9">
                                        <input type="text"  class="form-control" name="phone" placeholder="">
                                    </div>
                                </div>	
								 <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Age</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="age" placeholder="">
                                    </div>
                                </div>

								 <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Nationality</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="nationality" placeholder="">
                                    </div>
                                </div>								
								<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Occupation</label>
                                    <div class="col-md-9">
                                        <textarea class="form-control" name="occupation" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Upload Picture</label>
                                    <div class="col-md-9">
										<input type="file" id="exampleInputFile" name="file" accept="image/png,image/jpg,image/gif,image/jpeg">
										<p class="help-block">Max Size 3MB</p>
                                    </div>
                                </div>    
                                
                              
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info">Sign Up</button>
                                        <div style="height:5px"></div>
										<span style="margin-left:8px;">or Sign Up with</span>  
                                    </div>
                                </div>
                                
                                <div style="padding-top:3px"  class="form-group">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-fbsignup" type="button" class="btn btn-primary">Facebook</button>
										<button id="btn-fbsignup" type="button" class="btn btn-primary">Twitter</button>
                                    </div>                                           
                                        
                                </div>
                                
                                
                                
                            </form>
                         </div>
                     </div>
                  </div>
                         <div id="forgot_password" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                   				 <div class="panel panel-info">
  						
			                        <div class="panel-body" >
			                            <form id="forgot_password_form" class="form-horizontal" role="form" action="UserForgotPasswordController" method="post">
			                                
			                                 <div class="panel-heading">
					                            <div class="panel-title" style="font-size:26px">Forgot Password?
					                           		 <img alt="" id="close" onclick="div_hide()" src="assets/img/close.png" style="float: right; ">
				                            	</div>
                        					</div>
                        					<hr>
			                                <div class="form-group">
			                                    <label for="icode" class="col-md-3 control-label">Email Id</label>
			                                     <div class="col-md-9">
													  <input type="email" name="email" class="form-control" placeholder="Enter your Email Id">
												 </div>
			                                </div>			

			                                				
			                                <div class="form-group">
			                                    <!-- Button -->                                        
			                                    <div class="col-md-offset-3 col-md-9">
			                                        <button id="btn" type="Submit" class="btn btn-info">Submit</button>
													<!--<button id="btn" type="submit" class="btn btn-info">Cancel</button>	-->
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

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script type="text/javascript">
 function div_show() {
	 document.getElementById('forgot_password').style.display = "block";
 }
 function div_hide() {
	 document.getElementById('forgot_password').style.display = "none";
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
