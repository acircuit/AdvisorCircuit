<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Account Activation</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
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
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	
  </body>
</html>
