<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
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
			AdvisorProfileDTO profile = (AdvisorProfileDTO)request.getAttribute("profile");
			Boolean edit = (Boolean)request.getAttribute("edit");
			pageContext.setAttribute("profile", profile);
			String action = "";
			String general="";
			String education ="";
			String professional= "";
			String other = "";
			String service= "";
			String image = "";
			if(edit != null && edit){
				action = "GeneralInfoEdit";
				general ="GeneralInfoEdit";
				education="EducationInfoEdit";
				professional="ProfessionalBackgroundEdit";
				other="OtherInfoEdit";
				service="ServicesEdit";
				image = "ImageEdit";
			}else{
				action = "AdvisorRegistrationGeneralInfo";
				general ="AdvisorRegistrationGeneralInfo";
				education="AdvisorRegistrationEducationInfo";
				professional="AdvisorRegistrationProfessionalBackground";
				other="AdvisorRegistrationOtherInfo";
				service="AdvisorRegistrationServices";
				image = "AdvisorRegistrationImage";
			}
	%>
</head>
<body>
    <div class="container">
        <%@include file="/Header.jsp" %>
        
        <div class="row">
        <div class="mainbox col-md-8 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title" style="font-size:26px;">Advisor Registration</div>
                </div> 
                <c:choose>
                    <c:when test="${profile.getName() != null}">
                             <div class="panel-body" >	                         
                                <form id="signupform" class="form-horizontal" role="form" action="<%=action %>" method="post">
                            
                                     <div id="part1">
                                        
                                        <ul class="tab-list">
				                        	<li class="active">General Information</li>
				                            <li><a href="<%=education %>?tab=true">Education Information</a></li>
				                            <li><a href="<%=professional %>?tab=true">Professional Background</a></li>
				                            <li><a href="<%=other %>?tab=true">Other Information</a></li>
				                            <li><a href="<%=service %>?tab=true">Service Information</a></li>
				                            <li><a href="<%=image%>">Upload Image</a></li>
				                        </ul>
	
                                        <div style="height:20px"></div>
                                        <input type="hidden" id="edit" name="edit" value="true">
                                        <div class="form-group" id="dfname">
                                            <label for="fname" class="col-md-3 control-label">Name</label>
                                            <div class="col-md-5">
                                                <input id="fname" type="text" class="form-control" name="name" placeholder="Name" value="${profile.getName()}" maxlength="100">
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="required_name">Field Required</p>
                                                <p class="required" id="invalid_name">Invalid Name</p>
                                            </div>
                                        </div>
                                        <div class="form-group" id="dgender">
                                            <label for="icode" class="col-md-3 control-label">Gender</label>
                                            <div class="col-md-5">
                                                <c:if test="${profile.getGender().equals('male')}">
                                                    <select id="gender" class="dropreg" name="gender">
                                                      <option value="male">male</option>
                                                      <option value="female">female</option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${profile.getGender().equals('female')}">
                                                    <select id="gender" class="dropreg" name="gender">
                                                      <option value="female">female</option>
                                                      <option value="male">male</option>
                                                    </select>
                                                </c:if>
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="invalid_gender">Invalid Age</p>
                                                <p class="required" id="required_gender">Field Required</p>
                                            </div>
                                        </div>
                                         <div class="form-group" id="dage">
                                            <label for="icode" class="col-md-3 control-label">Age</label>
                                            <div class="col-md-5">
                                                <input id="age" type="text" class="form-control" name="age" placeholder="" value="${profile.getAge()}" maxlength="15">
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="invalid_age">Invalid Age</p>
                                                <p class="required" id="required_age">Field Required</p>
                                            </div>
                                        </div>
                                        
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
                                            
                                            <div class="form-group" id="dcity">
                                            <label for="icode" class="col-md-3 control-label">City</label>
                                            <div class="col-md-5">
                                                <input id="city" type="text" class="form-control" name="city" placeholder="" required="required" value="${profile.getCity()}" maxlength="40">
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="required_city">Field Required</p>
                                                <p class="required" id="invalid_city">Invalid City</p>
                                            </div>
                                            </div>
                                                <div class="form-group" id="dstate">
                                            <label for="icode" class="col-md-3 control-label">State</label>
                                            <div class="col-md-5">
                                                <input id="state" type="text" class="form-control" name="state" placeholder="" required="required" value="${profile.getState()}" maxlength="40">
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="required_state">Field Required</p>
                                                <p class="required" id="invalid_state">Invalid State</p>										
                                            </div>
                                            </div>
                                            
                                        
                                        
                                        <div class="form-group" id="dindustry">
                                            <label for="icode" class="col-md-3 control-label">Industry</label>
                                            <div class="col-md-5">
                                                <input id="industry" type="text" class="form-control" name="industry" placeholder="" value="${profile.getIndustry()}" maxlength="200">
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="required_industry">Field Required</p>
                                                <p class="required" id="invalid_industry">Invalid Industry</p>																														
                                            </div>
                                        </div>
                                        <div class="form-group" id="dintro">
                                            <label for="icode" class="col-md-3 control-label">Introduction</label>
                                            <div class="col-md-5">
                                                <textarea id="intro" name="intro"  class="form-control" rows="" cols="" placeholder="" maxlength="3000" >${profile.getIntroduction()}</textarea>
                                            </div>
                                            <div class="col-md-2">
                                                <p class="required" id="required_intro">Field Required</p>
                                            </div>
                                        </div>
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
                                    </div>
                                </form>
                            </div>
                    </c:when>
                    <c:otherwise>
                                <div class="panel-body" >	                         
                <form id="signupform" class="form-horizontal" role="form" action="<%=action %>" method="post">
            
                     <div id="part1">
                     
                     	<ul class="tab-list">
                        	<li class="active">General Information</li>
                            <li><a href="<%=education %>?tab=true">Education Information</a></li>
                            <li><a href="<%=professional %>?tab=true">Professional Background</a></li>
                            <li><a href="<%=other %>?tab=true">Other Information</a></li>
                            <li><a href="<%=service %>?tab=true">Service Information</a></li>
                            <li><a href="<%=image%>">Upload Image</a></li>
                        </ul>
                        	
                        <div style="height:20px"></div>
                        <div class="form-group" id="dfname">
                            <label for="fname" class="col-md-3 control-label">Name</label>
                            <div class="col-md-5">
                                <input id="fname" type="text" class="form-control" name="name" placeholder="Name" maxlength="100">
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="required_name">Field Required</p>
                                <p class="required" id="invalid_name">Invalid Name</p>
                            </div>
                        </div>
                        <div class="form-group" id="dgender">
                            <label for="icode" class="col-md-3 control-label">Gender</label>
                            <div class="col-md-5">
                                <select id="gender" class="dropreg" name="gender">
                                 <option value="select">Select A Gender</option>
                                  <option value="male">male</option>
                                  <option value="female">female</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="invalid_gender">Invalid Age</p>
                                <p class="required" id="required_gender">Field Required</p>
                            </div>
                        </div>
                         <div class="form-group" id="dage">
                            <label for="icode" class="col-md-3 control-label">Age</label>
                            <div class="col-md-5">
                                <input id="age" type="text" class="form-control" name="age" placeholder="" maxlength="15">
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="invalid_age">Invalid Age</p>
                                <p class="required" id="required_age">Field Required</p>
                            </div>
                        </div>
                        
                         <div class="form-group" id="dphone">
                            <label for="icode" class="col-md-3 control-label">Mobile Number</label>
                            <div class="col-md-5">
                                <input id="phone" type="text" class="form-control" name="phone" placeholder="" required="required" min="10" max="10">
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="required_phone">Field Required</p>
                                <p class="required" id="invalid_phone">Invalid Number</p>
                            </div>
                        </div>
                            
                            <div class="form-group" id="dcity">
                            <label for="icode" class="col-md-3 control-label">City</label>
                            <div class="col-md-5">
                                <input id="city" type="text" class="form-control" name="city" placeholder="" required="required" maxlength="40">
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="required_city">Field Required</p>
                                <p class="required" id="invalid_city">Invalid City</p>
                            </div>
                            </div>
                                <div class="form-group" id="dstate">
                            <label for="icode" class="col-md-3 control-label">State</label>
                            <div class="col-md-5">
                                <input id="state" type="text" class="form-control" name="state" placeholder="" required="required" maxlength="40">
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="required_state">Field Required</p>
                                <p class="required" id="invalid_state">Invalid State</p>										
                            </div>
                            </div>
                            
                         
                        <div class="form-group" id="dindustry">
                            <label for="icode" class="col-md-3 control-label">Industry</label>
                            <div class="col-md-5">
                                <input id="industry" type="text" class="form-control" name="industry" placeholder="" maxlength="200">
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="required_industry">Field Required</p>
                                <p class="required" id="invalid_industry">Invalid Industry</p>																														
                            </div>
                        </div>
                        <div class="form-group" id="dintro">
                            <label for="icode" class="col-md-3 control-label">Introduction</label>
                            <div class="col-md-5">
                                <textarea id="intro" name="intro"  class="form-control" rows="" cols="" placeholder="" maxlength="3000"></textarea>
                            </div>
                            <div class="col-md-2">
                                <p class="required" id="required_intro">Field Required</p>
                            </div>
                            <div class="col-md-2">	
                                    <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Example: I had my first stint with advertising back in 2004 fresh out of college and have been working with clients to solve their problems and meet their advertising needs ever since. It is just as exciting for me today as my first day of work was. I love the constant out of the box thinking and the interaction with diverse people which is a part of the job. I have worked with a few advertising agencies but mainly been a part of JWT, Delhi. My years spent have given me diverse knowledge about this industry and taught me a lot about different marketing strategies and channels." data-trigger="focus">
                                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>   
                            </div>
                        </div>
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
                    </div>
            
              </form>
           </div>
                    </c:otherwise>
            </c:choose> 
             
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
<script>
$(document).ready(function() {
	<!--Name can't be blank-->
	var name_flag, relindus_flag,industry_flag,job_flag,mail_flag,nation_flag,state_flag,city_flag,phone_flag,name_flag,age_flag,org_flag;
	$("#btn-signup").click(function(event){
		var input_n = $("#fname").val();
		var filtern = /^[a-z .A-Z]+$/; 
		var is_name = filtern.test(input_n);
		if(input_n=='')
		{
			$("#required_name").show();
			$("#invalid_name").hide();
			$("#dfname").addClass("has-error");
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
	});
	$("#btn-signup").click(function(event){
		var gender = $("#gender").val();
		if(gender == "select"){
			$("#required_gender").show();
			$("#dgender").addClass("has-error");
			event.preventDefault();
		}else{
			$("#required_gender").hide();
			$("#dgender").removeClass("has-error");
		}
	});	
	<!--age can't be blank-->
	$("#btn-signup").click(function(event){
		var input_a = $("#age").val();
		var filter = /^[0-9-+]+$/;
		var is_age = filter.test(input_a);
		if(input_a=='')
		{
			$("#required_age").show();
			$("#invalid_age").hide();
			$("#dage").addClass("has-error");
			event.preventDefault();
		}
		else if (!is_age){
			 age_flag = 1;
			$("#invalid_age").show();
			$("#required_age").hide();
			$("#dage").addClass("has-error");
				event.preventDefault();
		}else{
			age_flag = 0;
			$("#required_age").hide();
			$("#invalid_age").hide();
			$("#dage").removeClass("has-error");
		}	
	});
	<!--phone can't be blank-->
	$("#btn-signup").click(function(event){
		var input_p = $("#phone").val();
		var filter = /^\d{10}$/; 
		var is_phone = filter.test(input_p);
		if(input_p=='')
		{
			$("#required_phone").show();
			$("#invalid_phone").hide();
			$("#dphone").addClass("has-error");
			event.preventDefault();
		}
		else if (!is_phone){
			phone_flag = 1;
			$("#invalid_phone").show();
			$("#required_phone").hide();
			$("#dphone").addClass("has-error");
				event.preventDefault();
		}else{
			$("#required_phone").hide();
			$("#invalid_phone").hide();
			phone_flag = 0;
			$("#dphone").removeClass("has-error");
		}	
		
	});
	<!--city can't be blank-->
	$("#btn-signup").click(function(event){
		var input_city = $("#city").val();
		var filtern = /^[a-z A-Z ]+$/; 
		var is_city = filtern.test(input_city)
		if(input_city=='')
		{
			$("#required_city").show();
			$("#invalid_city").hide();
			$("#dcity").addClass("has-error");
			event.preventDefault();
		}
		else{
			$("#required_city").hide();
			$("#invalid_city").hide();
			city_flag=0;
			$("#dcity").removeClass("has-error");
		}
	});
	
	<!--city can't be blank-->
	$("#btn-signup").click(function(event){
		var input_city = $("#state").val();

		if(input_city=='')
		{
			$("#required_state").show();
			$("#dstate").addClass("has-error");
			event.preventDefault();
		}	
		else{
			$("#required_state").hide();
			state_flag=0;
			$("#dstate").removeClass("has-error");
		}
	});
	

	<!--industry can't be blank-->
	$("#btn-signup").click(function(event){
		var input_city = $("#industry").val();
		if(input_city=='')
		{
			$("#required_industry").show();
			$("#dindustry").addClass("has-error");
			event.preventDefault();
		}else{
			$("#required_industry").hide();
			industry_flag=0;
			$("#dindustry").removeClass("has-error");
		}		
	});
	<!--organisation can't be blank-->
	$("#btn-signup").click(function(event){
		var input_city = $("#intro").val();
		if (input_city==''){
			org_flag= 1;
			$("#required_intro").show();
			$("#dintro").addClass("has-error");
			event.preventDefault(); 
		}	
		else{
		$("#required_intro").hide();
		$("#dintro").removeClass("has-error");
		org_flag=0;
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