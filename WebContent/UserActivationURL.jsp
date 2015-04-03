<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Account Activation</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
      <%	String active = request.getParameter("active");
    %>
  
  </head>

  <body>

  <div class="container">
  <%@include file="/Header.jsp" %>
	<c:choose>
		<c:when test="<%=active != null %>">
		<div class="row">
			<div class="col-lg-12 text-center ex-page">
            <img src="assets/img/logo-new.png" alt="Advisor Circuit" width="450">
            <h1>This is an invalid activation URL.</h1>
			</div><!-- /col-lg-12 -->
		</div><!-- /row -->
		</c:when>
		<c:otherwise>
		<div class="row">
			<div class="col-lg-12 text-center ex-page">
            <img src="assets/img/logo-new.png" alt="Advisor Circuit" width="450">
            <h1>Your account has been activated!</h1>
			<p>Click <a href="login">here</a> to login.</p>
			</div><!-- /col-lg-12 -->
		</div><!-- /row -->
		</c:otherwise>
	</c:choose>
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
