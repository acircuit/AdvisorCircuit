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
	    <% String isLoginFailed= (String)request.getAttribute("loginverificationfailed");
	    Boolean isInvalidUsername= (Boolean)request.getAttribute("isInvalidUsername");
		pageContext.setAttribute("isLoginFailed", isLoginFailed);
		

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
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                     <div class="panel-heading">
                        <div class="panel-title" style="font-size:26px">Sign In</div>
                    </div> 
     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" onsubmit="GetFormAction()"  method="post">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="email" class="form-control" name="email" value="<%=remember %>" placeholder="email">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                                    </div>
                                      <div style="margin-bottom: 25px" class="input-group">
                                   		<span style="float: left"><b>Type</b></span>
	                                     <div class="col-md-9">
											  <select id = "type" name="type" >
											  	<option value="Advisor">Advisor</option>
											  	<option value="User">User</option>
											  </select>
										 </div>
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
                                </div>
                                <div class="input-group">
                                      <div >
                                        <label>
                                          <a style="float: left" onclick="div_show()">Forgot Password ?</a> 
                                        </label>
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
                                        <a href="#">
                                            Sign Up Here
                                        </a>
                                        </div>
                                    </div>
                                </div>    
                            </form>
                      </div>       
							<div id="forgot_password" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                   				 <div class="panel panel-info">
  						
			                        <div class="panel-body" >
			                            <form id="forgot_password_form" class="form-horizontal" role="form" action="ForgotPasswordController" method="post">
			                                
			                                 <div class="panel-heading">
					                            <div class="panel-title" style="font-size:26px">Forgot Password?
					                           		 <img alt="" id="close" onclick="div_hide()" src="assets/img/close.png" style="float: right; ">
				                            	</div>
                        					</div>
                        					<hr>
			                                <div class="form-group">
			                                    <label for="icode" class="col-md-3 control-label">Username</label>
			                                     <div class="col-md-9">
													  <input type="text" name="username" class="form-control" placeholder="Enter your UserName">
												 </div>
			                                </div>
			                                	
			                                <c:if test="${isInvalidUsername}">
			                                <c:out value="Invalid Username"></c:out>
			                                </c:if>
			                                				
			                                <div class="form-group">
			                                    <!-- Button -->                                        
			                                    <div class="col-md-offset-3 col-md-9">
			                                        <button id="btn" type="Submit" class="btn btn-info">Submit</button>
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
 <script type="text/javascript">
 function div_show() {
	 document.getElementById('forgot_password').style.display = "block";
 }
 function div_hide() {
	 document.getElementById('forgot_password').style.display = "none";
}
 function GetFormAction(){
	var type =document.getElementById('type').value;
	alert(type)
	if(type == "Advisor"){
		alert("123");
		document.getElementById("loginform").action = "http://localhost:7080/Tomact_7/AdvisorLoginController";
	}else{
		document.getElementById("loginform").action = "http://localhost:7080/Tomact_7/UserLoginController";
	}
 }
 </script>
</body>
</html>