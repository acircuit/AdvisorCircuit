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
	  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	<%		String edit = (String)request.getParameter("edit");
			String action ="";
			String general="";
			String education ="";
			String professional= "";
			String other = "";
			String service= "";
			Boolean isShow = true;
			if(edit != null &&  edit.equals("true")){
				action = "ImageEdit";
				general ="GeneralInfoEdit";
				education="EducationInfoEdit";
				professional="ProfessionalBackgroundEdit";
				other="OtherInfoEdit";
				service="ServicesEdit";
				isShow = false;
			}else{
				action = "AdvisorRegistrationImages";
				general ="AdvisorRegistrationGeneralInfo";
				education="AdvisorRegistrationEducationInfo";
				professional="AdvisorRegistrationProfessionalBackground";
				other="AdvisorRegistrationOtherInfo";
				service="AdvisorRegistrationServices";
			} 
			String path = request.getParameter("path");
	%>
</head>
<body>
	
        <div class="container">	
            <%@include file="/Header.jsp" %>
            
            <div class="row">

                <div id="signupbox1" class="mainbox col-md-8  col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title" style="font-size:26px">Advisor Registration</div>
                </div>  
             <div class="panel-body" >	                         
                <form id="signupform" class="form-horizontal" role="form" onsubmit="validation(this)" enctype="multipart/form-data" action="<%=action %>" method="post">
            
                    
                            <!--part5--->
                            <div id="part5">
                        <ul class="tab-list">
                            <li>General Information</li>
                            <li><a href="<%=education %>?tab=true">Education Information</a></li>
                            <li><a href="<%=professional %>?tab=true">Professional Background</a></li>
                            <li><a href="<%=other %>?tab=true">Other Information</a></li>
                            <li><a href="<%=service %>?tab=true">Service Information</a></li>
                            <li class="active">Upload Image</li>
                        </ul>
                        <h4>Picture</h4>
                        <hr>
                        <c:if test="<%=!isShow %>">
	                       	<div class="form-group">
	                            <label for="icode" class="col-md-3 control-label">Uploaded Picture</label>
	                            <div id = "fileupload" class="col-md-9">
	                            	<img alt="" src="<%=path%>">
	                            </div>
	                        </div>
                        </c:if>
                        <div class="form-group">
                            <label for="icode" class="col-md-3 control-label">Upload Picture</label>
                            <div id = "fileupload" class="col-md-9">
                                <input type="file" id="uploadFile" name="file" accept="image/png,image/jpg,image/gif,image/jpeg" required="required">
                                <p class="help-block">Max Size 2.5MB</p>
                            </div>
                        </div>
                        <c:if test="<%=isShow %>">
	                        <h4>Terms & Conditions</h4>
	                        <hr>
	                        <div class="col-md-3"></div>
	                        <div class="col-md-9">
	                			<input type="checkbox" id="agree" name="agree"><span style="padding:1%; font-size:16px;">I agree with the <a href="terms" target="blank">Terms & Conditions.</a> & <a href="nda" target="blank">Non-Disclosure Agreement.</a></span>        	
	                        </div>
						</c:if>
                         <div class="form-group">
                            <!-- Button -->                                        
                            <div class="col-md-offset-3 col-md-9">
                                <c:choose>
                                     <c:when test="${edit != null && edit }">
                                        <button id="btn-signup" type="submit" style="float: right" class="btn btn-info">Save Changes</button>                                            		
                                     </c:when>
                                     <c:otherwise>
                                		<button id="btn-signup" type="submit" style="float: right" class="btn btn-info">Save and Continue</button>                                     
                                     </c:otherwise>
                                 </c:choose>
                            </div>
                        </div>
                     </div><!--part2-->	
             </form>
           </div>
         </div>
         </div>
         
         	</div>

            <%@include file="/Footer.jsp" %>
        </div>

</body>
<script type="text/javascript">
$(document).ready(function() {
	
$("#btn-signup").click(function(event){
	var avatar = $("#uploadFile").val();
	var extension = avatar.split('.').pop().toUpperCase();
	var size = $('#uploadFile').size();
	//alert(size);
	var file = $('#uploadFile')[0].files[0].size;

	if(avatar.length < 1) {
		alert(extension);
		avatarok = 0;
		event.preventDefault();
		alert("Please upload a file");
	}
	else if (extension!="PNG" && extension!="JPG" && extension!="GIF" && extension!="JPEG"){
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
	if(!($("#agree").is(':checked'))){
		event.preventDefault();
		alert("Hey!! You forgot to accept the Terms & Condition");
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