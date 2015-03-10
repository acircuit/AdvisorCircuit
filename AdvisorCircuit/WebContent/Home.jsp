<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Use Advisor Circuit network to connect with industry experts. Reach out for career advice, resume critiques, mock interviews and workshops. Land a job. Get shortlisted. Grow in your career. Understand how to choose a career">
    <meta name="author" content="Advisor Circuit">
    <meta name="keywords" content="prepare interview, career guidance, telephonic interview, career options, how to prepare for an interview, career growth, how to choose a career, career guidance after 12th, how to make a good resume, online career counselling.">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Career Counselling Online | Advisor Circuit</title>
	 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  
  </head>

  <body>
  <div class="container">
  	<%@include file="/Header.jsp" %>
	
	<div class="row main-wrapper">
        <div class="col-md-12 text-center">
            <h1>Have questions about your career?</br>Take charge today!</h1>
            <h4>Connect with professionals who are actually in the field.</h4>
        </div>
		
        <div class="inner-block">
            <div class="col-md-4 text-center">
            	<img src="assets/img/WebMail/HomePage/Panel_1_Icon_1.png" width="60" alt="Search for your advisor">
            	<p>Search and pick your advisor</p>
            </div><!--/col-md-3 -->
            
            <div class="col-md-4 text-center">
            	<img src="assets/img/WebMail/HomePage/Panel_1_Icon_2.png" width="60" alt="Book as per your comfort">
            	<p>Book a session as per your convenience</p>
            </div><!--/col-md-3 -->
            
            <div class="col-md-4 text-center">
                <img src="assets/img/WebMail/HomePage/Panel_1_Icon_3.png" width="60" alt="Grow in your career">
                <p>Connect via Phone, Email or WebChat</p>
            </div><!--/col-md-3 -->
        </div>
				 
        <div class="col-lg-12 text-center">
            <form class="find-advisor" role="form" action="Search" formnovalidate  method="post">
                <div class="input-group">
                <input type="text" class="form-control" name="search" placeholder="Find Advisors in your Field" required="required">
                <span class="input-group-btn">
                	<button class="btn btn-default" type="Submit"><i class="glyphicon glyphicon-search" style="color:#FFFFFF"></i></button> 
                </span>
                </div>
            </form>
		</div>	
	</div><!-- /main wrapper -->

    <div class="row text-center">
        <div class="col-lg-6 col-lg-offset-3">
            <img src="assets/img/WebMail/HomePage/Panel_1_Node.png" width="220" alt=""/>
        </div>	
    </div><!-- /row -->
	
	<div class="row services">
        <div class="col-lg-12 text-center">
            <h1>Services</h1>
        </div>
				
		<div class="inner-block">
            <div class="col-md-3 text-center">
                <c:url value="Advisors" var="CarrerTalk">
                    <c:param name="service" value="CarrerTalk"/>
                </c:url>
            	<img src="assets/img/WebMail/HomePage/Panel_2_Icon_1.png" width="60" alt="Career Talk">
            	<h4><a href="${CarrerTalk}">Career Talk</a></h4>
            	<p>Catch up with an industry expert for a quick session on your doubts</p>
            </div><!--/col-md-3 -->
                    
            <div class="col-md-3 text-center">
                <c:url value="Advisors" var="MockInterview">
                    <c:param name="service" value="MockInterview"/>
                </c:url>
            	<img src="assets/img/WebMail/HomePage/Panel_2_Icon_2.png" width="60" alt="Mock Interview">
            	<h4><a href="${MockInterview}">Mock Interview</a></h4>
            	<p>Experience a mock interview before actually going for the real one</p>
            </div><!--/col-md-3 -->
                    
            <div class="col-md-3 text-center">
                <c:url value="Advisors" var="CVCritique">
                    <c:param name="service" value="CVCritique"/>
                </c:url>
            	<img src="assets/img/WebMail/HomePage/Panel_2_Icon_3.png" width="60" alt="Resume Critique">
            	<h4><a href="${CVCritique}">Resume Critique</a></h4>
            	<p>Get your CV critiqued by actual recruiters to make that shortlist</p>
            </div><!--/col-lg-3 -->
                    
            <div class="col-md-3 text-center">
                <c:url value="Advisors" var="PersonalWorkshop">
                    <c:param name="service" value="PersonalWorkshop"/>
                </c:url>
                <img src="assets/img/WebMail/HomePage/Panel_2_Icon_4.png" width="60" alt="Personal Workshop">
                <h4><a href="${PersonalWorkshop}">Personal Workshop</a></h4>
                <p>Get a one on one workshop with experts to gain knowledge</p>
            </div><!--/col-md-3 -->
		</div>
	</div><!-- /row -->

    <div class="row text-center">
        <div class="col-lg-6 col-lg-offset-3">
            <img src="assets/img/WebMail/HomePage/Panel_3_Node.png" width="240" alt=""/>
        </div>
    </div><!-- /row -->
	
	<div class="row business">
        <div class="col-lg-12 text-center">
            <h1>Why use Advisor Circuit</h1>
			<h4>Wondering how we can help your career ?</br>Click on your category to find out more</h4>
        </div>
        
        <div class="inner-block">
        	<div class="col-md-4 text-center">
            	<a href="whyusac"><img src="assets/img/WebMail/HomePage/Icon_School_Colour.png" width="80" alt="I'm in School"></a>
            	<h4><a href="whyusac">I'm in School</a></h4>
        	</div><!--/col-lg-4 -->
        
        	<div class="col-md-4 text-center">
                <a href="whyusac"><img src="assets/img/WebMail/HomePage/Icon_College_Colour.png" width="80" alt="I'm in College"></a>
                <h4><a href="whyusac">I'm in College</a></h4>
        	</div><!--/col-lg-4 -->
        
        	<div class="col-md-4 text-center">
            	<a href="whyusac"><img src="assets/img/WebMail/HomePage/Icon_Working_Colour.png" width="80" alt="I'm working"></a>
            	<h4><a href="whyusac">I'm Working</a></h4>
        	</div><!--/col-lg-4 -->
        </div>
	</div><!-- /business -->
	
    <div class="row text-center">
        <div class="col-lg-6 col-lg-offset-3">
            <img src="assets/img/WebMail/HomePage/Panel_4_Node.png" width="240" alt=""/>
        </div>
    </div><!-- /row -->
	
	<div class="row beadvisor">
        <div class="col-lg-12 text-center">
            <h1>Be an Advisor!</h1>
            <h4>Make a huge impact by providing guidance, offering your knowledge</br>
                and get paid at the same time.</h4>
            <img src="assets/img/WebMail/HomePage/Panel_4_Icon_1.png" width="60" alt="Advisor" class="advisor-img">	
            <h3>Offer advice and guidance | Earn money while doing something of value <br>
            Take sessions when you're free |  Work from the comfort of your own home</h3>
            <a href="Email" class="btn btn-comm">Join Us!</a>				
        </div>
	</div><!-- /beadvisor -->
	
    <div class="row text-center">
        <div class="col-lg-6 col-lg-offset-3">
            <img src="assets/img/WebMail/HomePage/Panel_5_Node.png" width="258" alt=""/>
        </div>
    </div><!-- /row -->

	<div class="row advisor">
    	<h1 class="text-center">Featured Advisors</h1>
        <div class="col-md-4 text-center">
                <c:url value="AdvisorProfile" var="archana">
                    <c:param name="aId" value="18"/>
                </c:url>
                <a href="${archana}"><img src="AdvisorImages/archassoc@gmail.com/archana gupta.JPG" alt="Archana Gupta" width="210"></a>
                <h3><a href="${archana}">Archana Gupta, Architect</a></h3>
                <p>With over 17 years of experience in this profession, I have been involved in both the professional practice and ..</p>
            </div><!--/col-lg-4 -->
					
        <div class="col-md-4 text-center">
                <c:url value="AdvisorProfile" var="keshav">
                    <c:param name="aId" value="21"/>
                </c:url>
                <a href="${keshav}"><img src="AdvisorImages/keshav.kanoria@gmail.com/KeshavKanoria.jpg" width="210" alt="Keshav Kanoria"></a>
                <h3><a href="${keshav}">Keshav Kanoria, Management Consultant</a></h3>
                <p>I've worked in the fields of strategy/management consulting, research and financial analysis and business development ..</p>
            </div><!--/col-lg-4 -->
                
        <div class="col-md-4 text-center">
                <c:url value="AdvisorProfile" var="mathew">
                    <c:param name="aId" value="24"/>
                </c:url>
                <a href="${mathew}"><img src="AdvisorImages/mathewdonbosco@yahoo.com/MathewThomas.jpg" width="210" alt="Mathews Thomas"></a>
                <h3><a href="${mathew}">Mathew Thomas, Teacher</a></h3>
                <p>I realized when i was young that teaching was my calling and I've being pursuing it for the past 20 years ..</p>
            </div><!--/col-lg-4 -->
	</div><!-- /advisor -->
    
	<%@include file="/Footer.jsp" %>
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<a href="https://plus.google.com/111112371994148232700" rel="publisher"></a>
  </body>
</html>