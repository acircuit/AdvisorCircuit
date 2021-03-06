<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>

    <title>Email Verification</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
    <!-- Fonts from Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    
  
  </head>

  <body>

	<div class="container">	
	<%@include file="/Header.jsp" %>
        <div class="row">
        <div class="col-lg-12 text-center ex-page">
            <img src="assets/img/logo-new.png" alt="Advisor Circuit" width="450">
            <h1>Thank you for starting the registration process.</h1>
            <p>We've sent you a verification link on your Email. Please verify your account to continue.</p>
        </div><!-- /col-lg-12 -->
    </div><!-- /row -->
	<%@include file="/Footer.jsp" %>
    <!--footer ends--->
	</div>
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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