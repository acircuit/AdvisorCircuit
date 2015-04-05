<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Set Password</title>
	  		 
	<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	<link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <%	
   		    String advisorId = "";
    		String userId = "";
    		String controller = "";
    		String uId = (String)request.getParameter("uId");
    		String aId = (String)request.getParameter("aId");
    		String id = "";
    		if(uId == null){
    			controller = "AdvisorNewPassword";
    			id = aId;
    		}else{
    			controller = "UserNewPassword";
    			id = uId;
    		}
    	%>
</head>
<body>
	
		<div class="container">
			<%@include file="/Header.jsp" %>
            
            <div class="row">
				<div id="loginbox" class="mainbox col-md-8 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title" style="font-size:26px">Change Password</div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" action="<%=controller %>" method="post">
                              <input type="hidden" name="id" value="<%=id%>"> 
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="NewPassword" placeholder="New Password">
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="c-password" type="password" class="form-control" name=" ConfirmPassword" placeholder="Confirm Password">
                                    </div>
                                    
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
                                    <div class="col-sm-12 controls">
                                    <button id="btn-signup" type="submit" class="btn btn-info">Submit</button>
                                    </div>
                                </div>
                                    </form>
                              <div>
                                                
                    </div> 
                </div>
   			 </div>

		</div>
        	</div>
        
			<%@include file="/Footer.jsp" %>
		</div>
</body>
<script>
$(document).ready(function() {
	$("#btn-signup").click(function(event){
		if($("#login-password").val()==""){
			alert("Please Enter Password");
			event.preventDefault();
		}else if ($("#c-password").val()=="") {
			alert("Please Confirm Password");
			event.preventDefault();
		}else if ($("#login-password").val() != $("#c-password").val()) {
			alert("Passwords does not match");
			event.preventDefault();
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