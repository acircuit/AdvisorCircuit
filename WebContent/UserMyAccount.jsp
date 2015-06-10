<!DOCTYPE html>
<%@page import="org.AC.dto.UserRequestDTO"%>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.UserDetailsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">   
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>My Account</title>  		 

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
 
	<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%
    UserDetailsDTO user = (UserDetailsDTO)request.getAttribute("user");
    	pageContext.setAttribute("user", user);
    %>
</head>

<body>
    <div class="container">
   	<%@include file="/Header.jsp"%>
    
    <div id="wrapper" class="content">
    
    	<div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-9">
            	<h4></h4>
            	<h1 class="page-header">My Account</h1>
            </div>
        </div>
        
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_user.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
					
            		<div class="grey-panel-1" style="display:none;">
                    	<p class="notification-msg">You have 2 message</p>
                        <ul class="notification-list">
                        	<li>- Aditya Raina has replied to your session request.</li>
                            <li>- You have a new message from Lavi Aggarwal.</li>
                        </ul>
                    </div>
                    
                    <div class="grey-panel-1">
                        <div class="row">
                        	<div class="col-md-9">
                            <div class="account-info">
                            	<dl>
                                	<dt>Name</dt>
                                    <dd>${user.getFullName()}</dd>
                                </dl>
                                <dl>
                                	<dt>Email Id</dt>
                                    <dd>${user.getEmail()}</dd>
                                </dl>
                                <dl>
                                	<dt>Phone Number</dt>
                                    <dd>${user.getPhone()}</dd>
                                </dl>
                                 <dl>
                                	<dt>Occupation</dt>
                                    <dd>${user.getOccupation()}</dd>
                                </dl>
                                <dl>
                                	<dt>Date of Registration</dt>
                                    <dd>${user.getDor()}</dd>
                                </dl>
                            </div>
                            </div>
                            <div class="col-md-3">
                            	<img id="" src="${user.getImage()}" alt="${user.getFullName()}" border="0" width="110">
                            </div>
                        </div>
                    </div>
                    <c:if test="${user.getRefCode() != null && !user.getRefCode().equals('')}">
	                    <div class="col-md-5 grey-panel-1" style="padding-left: 0px">
	            				<h3 style="margin-top: 0px;text-align: center;">Your Referral Code</h3>
	            				<h4 style="text-align: center;">${user.getRefCode()}</h4>
	            				<div style="text-align: center;">
		            				<h4>Share with friends to win !</h4>
		            					<div  class="addthis_sharing_toolbox">
										</div>
								</div>
	            		</div>
	            		<c:if test="${user.getRefCount() >= 5 && user.getRefMessage()}">
		            		<div class="col-md-5 grey-panel-1">
		            			<p>Congrats ! You have won a great resume designed by us</p>
		            		</div>
	            		</c:if>
            		</c:if>
        		</div>
            </div><!-- /right area -->
		
     	</div><!-- /row -->
        
    </div>   
    <!-- /#wrapper --> 
    <%@include file="/Footer.jsp" %>
    
	</div>
    <!-- /#container -->

    <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>   
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
	
	<!-- Go to www.addthis.com/dashboard to customize your tools -->
	<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-557687ac702ff828" async="async"></script>
	<script src="ZeroClipboard.js"></script>
    <script src="main.js"></script>
    <!-- Page-Level Demo Scripts - Notifications - Use for reference -->
    <script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
    <script type="text/javascript">
    var code = $("#ref").val();
var addthis_share = {
   url: "https://www.advisorcircuit.com/login",
   title: "Use My Referral Code:" +code
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
<script src="https://apis.google.com/js/platform.js" async defer></script>
 
    </body>

</html>
