<!DOCTYPE html>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Advisor Registration</title>
  		 
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	 <%
    UserDetailsDTO profile = (UserDetailsDTO)request.getAttribute("user");
    	pageContext.setAttribute("profile", profile);
    %>
</head>
<body>
    <div class="container">
        <%@include file="/Header.jsp" %>
        
        <div class="row">
        <div class="mainbox col-md-8 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title" style="font-size:26px;">User Profile</div>
                </div> 
                 <div class="panel-body" >
	               <form id="signupform" class="form-horizontal" role="form" action="usermyaccount" method="post" enctype="multipart/form-data" >
	                 <div class="form-group" id="dfname">
		               <label for="fname" class="col-md-3 control-label">Name</label>
		               <div class="col-md-5">
		                   <input id="name" type="text" class="form-control" name="name" placeholder="Name" value="${profile.getFullName()}" maxlength="100">
		               </div>
		               <div class="col-md-2">
		                   <p class="required" id="required_name">Field Required</p>
		                   <p class="required" id="invalid_name">Invalid Name</p>
		               </div>
		           </div>
					<input id="email" value="${profile.getEmail()}" type="hidden" name="email">
	              <div class="form-group" id="dphone">
	                  <label for="icode" class="col-md-3 control-label">Phone Number</label>
	                  <div class="col-md-5">
	                      <input id="phone" type="text" class="form-control" name="phone" placeholder="" required="required" min="10" max="10" value="${profile.getPhone()}">
	                  </div>
	                  <div class="col-md-2">
	                      <p class="required" id="required_phone">Field Required</p>
	                      <p class="required" id="invalid_phone">Invalid Number</p>
	                  </div>
	              </div>
	              
	               <c:if test="${profile.getImage() != null}">
	               	<div class="form-group">
	                    <label for="icode" class="col-md-3 control-label">Uploaded Picture</label>
	                    <div id = "fileupload" class="col-md-9">
	                    	<img alt="" src="${user.getImage()}" width="50%">
	                    </div>
	                </div>
	               </c:if>
	               <div class="form-group">
	                   <label for="icode" class="col-md-3 control-label">Upload Picture</label>
	                   <div id = "fileupload" class="col-md-9">
	                       <input type="file" id="uploadFile" name="file" accept="image/png,image/jpg,image/gif,image/jpeg" >
	                       <p class="help-block">Max Size 2.5MB</p>
	                   </div>
	               </div>
	                <div class="form-group">
	                    <!-- Button -->                                        
	                    <div class="col-md-offset-3 col-md-9">
	                        	<button id="btn-signup" type="submit" style="float: right" class="btn btn-info">Save Changes</button>                                            		
	                    </div>
	                </div>
	             </form>
	             </div>
         	</div>
        </div>
        </div>
        
        <%@include file="/Footer.jsp" %>
    </div>
    <!-- /#container -->
</body>
<script src="assets/js/jquery-1.11.0.js"></script>
<script type='assets/js/jquery.min.js'></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
<script type="text/javascript">
$(document).ready(function() {
	
	<!--Name can't be blank-->
	var name_flag, relindus_flag,industry_flag,job_flag,mail_flag,nation_flag,state_flag,city_flag,phone_flag,name_flag,age_flag,org_flag;
	$("#btn-signup").click(function(event){
		var input_n = $("#name").val();
		var filtern = /^[a-z A-Z]+$/; 
		var is_name = filtern.test(input_n);
		debugger;
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

		var input_p = $("#phone").val();
		var filter = /^\d{10}$/; 
		var is_phone = filter.test(input_p);
		if(input_p=='')
		{
			$("#required_phone").show();
			$("#invalid_phone").hide();
			event.preventDefault();
			isPreventDefault = true;

		}
		else if (!is_phone){
			phone_flag = 1;
			$("#invalid_phone").show();
			$("#required_phone").hide();
			$("#dphone").addClass("has-error");
				event.preventDefault();
				isPreventDefault = true;

		}else{
			$("#required_phone").hide();
			$("#invalid_phone").hide();
			phone_flag = 0;
			$("#dphone").removeClass("has-error");
		}	
	});	

	
$("#btn-signup").click(function(event){
	var avatar = $("#uploadFile").val();
	var extension = avatar.split('.').pop().toUpperCase();
	var size = $('#uploadFile').size();
	//alert(size);
	var file = $('#uploadFile')[0].files[0].size;

	if (extension!="PNG" && extension!="JPG" && extension!="GIF" && extension!="JPEG"){
		avatarok = 0;
		alert("invalid extension "+extension);
		event.preventDefault();
	}else if (file > 2621440) {
		alert("Please upload a file less than 2.5 MB");
		event.preventDefault();
	} else {
		avatarok = 1;
		//success all ok
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
