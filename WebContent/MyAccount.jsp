<!DOCTYPE html>
<%@page import="org.AC.dto.NotificationDTO"%>
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
    	AdvisorProfileDTO advisor = (AdvisorProfileDTO)request.getAttribute("advisor");
   		 List<NotificationDTO> notify= (List<NotificationDTO>)request.getAttribute("notify");
    	pageContext.setAttribute("advisor", advisor);
    	pageContext.setAttribute("notify", notify);
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
				<%@ include file="j-sidebar.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
					<c:if test="${notify.size() > 0 }">
            		<div class="grey-panel-1" style="display:none;>
                    	<p class="notification-msg" ">You have 2 message</p>
                        <ul class="notification-list">
                        	<c:forEach var="note" items="${notify}">
                        		<li><a href="${note.getHref()}&nId=${note.getnId()}">${note.getComment()}</a></li>
                        	</c:forEach>
                        </ul>
                    </div>
                    </c:if>
                    <div class="grey-panel-1">
                        <div class="row">
                        	<div class="col-md-9">
                            <div class="account-info">
                            	<dl> 
                                	<dt>Name</dt>
                                    <dd>${advisor.getName()}</dd>
                                </dl>
                                <dl>
                                	<dt>Email Id</dt>
                                    <dd>${advisor.getEmail()}</dd>
                                </dl>
                                <dl>
                                	<dt>Age</dt>
                                    <dd>${advisor.getAge()}</dd>
                                </dl>
                                <dl>
                                	<dt>Phone Number</dt>
                                    <dd>${advisor.getPhone()}</dd>
                                </dl>
                                 <dl>
                                	<dt>City</dt>
                                    <dd>${advisor.getCity()}</dd>
                                </dl>
                                 <dl>
                                	<dt>State</dt>
                                    <dd>${advisor.getState()}</dd>
                                </dl>
                                 <dl>
                                	<dt>Industry</dt>
                                    <dd>${advisor.getIndustry()}
                                    </dd>
                                </dl>
                                
                            </div>
                            
                            </div>
                            <div class="col-md-3">
                            	<img id="" src="${advisor.getImage()}" alt="${advisor.getName()}" border="0" width="110">
                            </div>
   
                            <div class="col-md-3">
                            	   <div style="margin-top: 100%"> <a href="AdvisorProfile?aId=${advisor.getAdvisorId()}&advisor=true"><img width="35" id="" src="assets/img/Icon_Advisor.png" alt="32321" border="0" align="absmiddle" style="margin-right:10px;"> View Profile	</a></div>
                            </div>
                                                                               
                            
                        </div>
                    </div>
            
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
