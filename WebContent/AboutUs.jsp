<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
  <head>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="The What's and Who's of Advisor Circuit. Read on to know about our team.">
    <meta name="author" content="">
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>About Us | Advisor Circuit</title>
	 
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  
  </head>

  <body>

  <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div class="about-page content">
        <div class="row text-center how-started">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>How we started</h1>
                <p>So it all began with a thought, "Who do I call". The work for Advisor Circuit was started back in early 2014 when we decided that we'd had enough of unapproachable relatives 
                and 'Thank you, Sir's. We realized that our way of reaching out to people was still archaic in this world of global 
                interconnectivity. It was then that we decided to create an online environment where the aim would be to provide quick 
                and effective solutions to people's problems by connecting them to the right people in a hassle free manner. We wanted 
                to make sure that people anywhere, no matter how remote, would have accessibility to experts and professionals in all fields.
                </p>
                <img src="assets/img/AboutUs/Panel_1_Icon.png" width="250" alt=": It all began with the thought,'Who do I call'">
            </div>
        </div><!-- /row -->
	
        <div class="row text-center we-are">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>Who are we?</h1>
                <p>We are a diverse group consisting of graduates from Delhi College of Engineering and Shri Ram College of Commerce with each one working their hardest to achieve our vision. Advisor Circuit is not just about 
                its core team but each advisor who joins us and every one of those people who reaches out to them.</p>
                <img src="assets/img/AboutUs/Panel_2_Icon.png" width="250" alt="A diverse group of graduates from DCE & SRCC">
            </div>
        </div><!-- /row -->
	
        <div class="row text-center advisor-circuit">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>What does Advisor Circuit do?</h1>
                <p>Here, we try to ensure that everyone gets an opportunity to reach out to advisors and make better decisions for their careers. We hope to achieve this by having experienced professionals from every 
                field available for sessions. Advisors on our platform offer 4 services</p>
            </div>
            
            <div class="inner-block">
            <c:url value="Advisors" var="CarrerTalk">
                    <c:param name="service" value="CarrerTalk"/>
                </c:url>
                <c:url value="Advisors" var="CVCritique">
                    <c:param name="service" value="CVCritique"/>
                </c:url>
                <c:url value="Advisors" var="MockInterview">
                    <c:param name="service" value="MockInterview"/>
                </c:url>
                <c:url value="Advisors" var="PersonalWorkshop">
                    <c:param name="service" value="PersonalWorkshop"/>
                </c:url>
                
                <div class="col-md-3 text-center">
                    <a href="${CarrerTalk}">
                    <img src="assets/img/WebMail/HomePage/Panel_2_Icon_1.png" width="55" alt="Career Talk">
                    <h4>Career Talk</h4>
                    </a>
                </div><!--/col-lg-4 -->
                
                <div class="col-md-3 text-center">
                    <a href="${MockInterview}">					
                    <img src="assets/img/WebMail/HomePage/Panel_2_Icon_2.png" width="55" alt="Mock Interview">
                    <h4>Mock Interview</h4>
                    </a>
                </div><!--/col-lg-4 -->
                
                <div class="col-md-3 text-center">
                    <a href="${CVCritique}">					
                    <img src="assets/img/WebMail/HomePage/Panel_2_Icon_3.png" width="55" alt="Resume Critique">
                    <h4>Resume Critique</h4>
                    </a>
                </div><!--/col-lg-4 -->
                
                <div class="col-md-3 text-center">
                    <a href="${PersonalWorkshop}">					
                    <img src="assets/img/WebMail/HomePage/Panel_2_Icon_4.png" width="55" alt="Personal Workshops">
                    <h4>Personal Workshop</h4>
                    </a>
                </div><!--/col-lg-4 -->
            </div>
            
            <div class="col-lg-8 col-lg-offset-2">
                <p>These services are aimed at providing industry specific counseling, mentoring & discussions via different modes of communication such as Email, Phone or Web Chat.</p>
            </div>
            
        </div><!-- /row -->
	
        <div class="row text-center vision">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>So is there a vision to this?</h1>
                <p>We dream of lesser confused people. But seriously, we truly believe that what we're trying 
                to do here can help people better understand their own choices. We hope that this portal can help everyone grow faster in their 
                careers and connect with dream mentors for guidance and inspiration. We want to save the unnecessary time and resources wasted 
                currently in reaching out to people in our small circles and overcome geographical barriers.</br> 
                Advisor Circuit is young but from the very outset we believe that it has the ability to fundamentally change how career decisions 
                are taken in developing countries like India; we look forward to a time when deep rooted knowledge of an industry would be 
                accessible to newcomers to help push their careers on the right track.</p>
                <img  src="assets/img/AboutUs/Panel_3_Icon.png" width="250" alt="Introducing personalisation in career counselling">
            </div>
            
        </div><!-- /row -->
	</div>

	<%@include file="/Footer.jsp" %>
			
	</div>
	<!--main container-->

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