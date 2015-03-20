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

    <title>Career Growth Online | Counselling | Advisor Circuit</title>
	 
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
	<a href="#" data-toggle="modal" data-target="#heretohelp">
    <div class="row text-center help-panel">
    	<div class="help">We are here to help</div>
        <div class="col-lg-6 col-lg-offset-3">
            <img src="assets/img/WebMail/HomePage/Panel_1_Node.png" width="220" alt=""/>
        </div>	
    </div><!-- /row -->
	</a>
	<div class="modal fade" id="heretohelp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
							<h4 class="modal-title" id="myModalLabel" style="text-align: center;">Get Help Finding Your Advisor</h4>
						</div>
						<div class="modal-body">
							<h4 style="text-align: center;">Just send in the below details; we'll go through our panel of experts and help you pick the right advisor! </h4>
							<hr>
							<form id="help" class="form-horizontal" role="form" method="post">
								<h4 style="font-family:'custom_light' !important;color:#555">1.Your Personal Information</h4>
								<div class="form-group">
								<label for="email" class="col-md-2 control-label">Full Name:</label>
                                 <div class="col-md-9">
									  <input id ="name" type="text" name="name" class="form-control" maxlength="70">
								 </div>
                                </div>
                                <div class="form-group">
								<label for="email" class="col-md-2 control-label">Email:</label>
                                 <div class="col-md-9">
									  <input id ="email" type="text" name="email" class="form-control" maxlength="150">
								 </div>
                                </div>
                                <div class="form-group">
								<label for="phone" class="col-md-2 control-label">Phone:</label>
                                 <div class="col-md-9">
									  <input id ="phone" type="text" name="phone" class="form-control" maxlength="40">
								 </div>
                                </div>
                                <div class="form-group">
								<label for="occupation" class="col-md-2 control-label">Occupation:</label>
                                 <div class="col-md-9">
									  <input id ="occupation" type="text" name="phone" class="form-control" maxlength="200">
								 </div>
                                </div>
								<h4 style="font-family:'custom_light' !important;color:#555">2.Industry or area in which you need help:</h4>
								 <div class="form-group">
                                     <div class="col-md-12">
										  <input id ="industry" type="text" name="industry" class="form-control" maxlength="200">
									 </div>
                                </div>
                                <h4 style="font-family:'custom_light' !important;color:#555">3.Which Service do you want [Visit How It Works page for details]</h4>
                                <div class="form-group">
                                     <div class="col-md-9 radio-inline" style="margin-left: 5%">
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="service"  id="optionsRadiosInlinem1" value="CareerTalk"/>Career Talk</li>
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="service"  id="optionsRadiosInlinem2" value="Mock Interview"/>Mock Interview</li>	
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="service"  id="optionsRadiosInlinem3" value="Resume Critique"/>Resume Critique</li>
							         </div>
                                </div>
                                <h4 style="font-family:'custom_light' !important;;color:#555">4.Rank below modes of communication in order of preference </h4>
                                <div class="form-group">
                                    <label for="icode" class="col-md-2 control-label" style="text-align: left;margin-left: 5%">Phone:</label>
                                     <div class="col-md-9 radio-inline">
										  <li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="phonemode"  id="optionsRadiophone1" value="1"/>1</li>
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="phonemode"  id="optionsRadiophone2" value="2"/>2</li>	
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="phonemode"  id="optionsRadiophone3" value="3"/>3</li>
									 </div>
								 </div>
								 <div class="form-group">
									 <label for="icode" class="col-md-2 control-label" style="text-align: left;margin-left: 5%">Email:</label>
                                     <div class="col-md-9 radio-inline">
										  <li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="emailmode"  id="optionsRadioemail1" value="1"/>1</li>
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="emailmode"  id="optionsRadioemail2" value="2"/>2</li>	
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="emailmode"  id="optionsRadioemail3" value="3"/>3</li>
									 </div>
                                 </div>
                                 <div class="form-group">
									 <label for="icode" class="col-md-2 control-label" style="text-align: left;margin-left: 5%">WebChat:</label>
                                     <div class="col-md-9 radio-inline">
										  <li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="webchat"  id="optionsRadiowebchat1" value="1"/>1</li>
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="webchat"  id="optionsRadiowebchat2" value="2"/>2</li>	
										<li style ="display: inline;float:left;margin-right:30px;font-family:'custom_light' !important;color:#555"><input type="radio" name="webchat"  id="optionsRadiowebchat3" value="3"/>3</li>
									 </div>
                                 </div>
                                 <h4 style="font-family:'custom_light' !important;;color:#555">5.Enter Your Query : </h4>
                                <div class="form-group">
                                    <div class="col-md-10 ">
                                        <textarea class="form-control" style="font-family:'custom_light' !important;" id="query" name="query" rows="3" maxlength="1200"></textarea>
                                    </div>
                                    <div class="col-md-2 ">	
                                   	 <button type="button" style="float:right;margin-right:20px;" class="btn btn-default" data-container="body" data-toggle="popover" data-html="true" data-placement="right" data-content="<p>Example: Hi Mr. Rao, I'mpursuing Civil Engineering from ABC University. I am currently in my final year and want to take up this field professionally. I want to discuss:<br>1.Further education required<br>2.Different specializations in the field and Job Opportunities<br>3.Expected career growth<br>4.How should I plan my future<br>I want to especially focus on making a career plan and things I need to do to achieve it. Looking forward to our session.</p>" data-trigger="focus">
							   		<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>   
                              		</div>
                                </div>
                                <h4 style="font-family:'custom_light' !important;;color:#555">6.Any other details that will help us choose a better advisor for you: </h4>
                              	<div class="form-group">
                                    <div class="col-md-10">
                                        <textarea class="form-control" style="font-family:'custom_light' !important;" id="other" name="other" rows="3" maxlength="1200"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="submit_btn" style="font-family:'custom_light' !important;" type="button"  class="btn btn-info">Submit</button>
										<!--<button id="btn" type="submit" class="btn btn-info">Cancel</button>	-->
                                    </div>
                                </div>
                                <h4 style="font-family:'custom_light' !important;;color:#555" id="here"></h4>
							</form>
						</div>
					</div>
            	</div>
        	</div>
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
	<fmt:bundle basename="Resources/Dependency" prefix="path.">
		<div class="row advisor">
	    	<h1 class="text-center">Featured Advisors</h1>
	        <div class="col-md-4 text-center">
	                <c:url value="AdvisorProfile" var="archana">
	                    <c:param name="aId" value="18"/>
	                </c:url>
	                <a href="${archana}"><img src=<fmt:message key="advisorImage1"/> alt="Archana Gupta" width="210"></a>
	                <h3><a href="${archana}"><fmt:message key="advisorName1"/></a></h3>
	                <p><fmt:message key="advisorIntro1"/></p>
	            </div><!--/col-lg-4 -->
						
	        <div class="col-md-4 text-center">
	                <c:url value="AdvisorProfile" var="keshav">
	                    <c:param name="aId" value="21"/>
	                </c:url>
	                <a href="${keshav}"><img src=<fmt:message key="advisorImage2"/> width="210" alt="Keshav Kanoria"></a>
	                <h3><a href="${keshav}"><fmt:message key="advisorName2"/></a></h3>
	                <p><fmt:message key="advisorIntro2"/></p>
	            </div><!--/col-lg-4 -->
	                
	        <div class="col-md-4 text-center">
	                <c:url value="AdvisorProfile" var="mathew">
	                    <c:param name="aId" value="24"/>
	                </c:url>
	                <a href="${mathew}"><img src=<fmt:message key="advisorImage3"/> width="210" alt="Mathews Thomas"></a>
	                <h3><a href="${mathew}"><fmt:message key="advisorName3"/></a></h3>
	                <p><fmt:message key="advisorIntro3"/></p>
	            </div><!--/col-lg-4 -->
		</div><!-- /advisor -->
    </fmt:bundle>
	<%@include file="/Footer.jsp" %>
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<a href="https://plus.google.com/111112371994148232700" rel="publisher"></a>
<script type="text/javascript">
var _urq = _urq || [];
_urq.push(['initSite', '8571f59c-9c67-4ac9-a169-0eb6aa49f203']);
(function() {
var ur = document.createElement('script'); ur.type = 'text/javascript'; ur.async = true;
ur.src = ('https:' == document.location.protocol ? 'https://cdn.userreport.com/userreport.js' : 'http://cdn.userreport.com/userreport.js');
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ur, s);
})();

$(document).ready(function() {
	$("#submit_btn").click(function(event){
        	var ajax = true;
        	var indus = $("#industry").val();
        	
        	var input_n = $("#name").val();
			var filtern = /^[a-z A-Z]+$/; 
			var is_name = filtern.test(input_n);
			
			var input_e = $("#email").val();
			var re = /^[a-zA-Z0-9.!$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
			var is_email=re.test(input_e);
			
			var input_p = $("#phone").val();
			var filter = /^\d{10}$/; 
			var is_phone = filter.test(input_p);
			
			
			if(input_n=='')
			{
				alert("Full Name Required");
				event.preventDefault();
				ajax = false;
			}
			else if (!is_name){
				name_flag = 1;
				alert("Invalid Name");
				event.preventDefault(); 
				ajax = false;
			}else if(input_e=='')
			{
				alert("Email Required");
				event.preventDefault();
				ajax = false;
			}
			else if (!is_email){
				mail_flag=1;
				alert("Invalid Email");
				event.preventDefault();
				ajax = false;
			}else if(input_p=='')
			{	
				 if (!is_phone){
					alert("Please Enter a Valid Phone Number");
					event.preventDefault();
					ajax = false;
				}
			}else if($("#occupation").val() == ""){
				alert("Please Enter Your Occupation");
				event.preventDefault();
				ajax = false;
			}
			else if(indus == ""){
        		alert("Please Enter the industry");
        		event.preventDefault();
        		ajax = false;
        	}else if ($("#optionsRadiosInlinem1").is(':checked') == false && $("#optionsRadiosInlinem2").is(':checked') ==false && $("#optionsRadiosInlinem3").is(':checked') == false) {
        		alert("Choose a service");
        		ajax = false;
        		event.preventDefault();
			}else if ($("#optionsRadiophone1").is(':checked') == false && $("#optionsRadiophone2").is(':checked') == false && $("#optionsRadiophone3").is(':checked') == false) {
				alert("Choose a Phone Preference");
        		event.preventDefault();
        		ajax = false;
			}else if ($("#optionsRadioemail1").is(':checked') == false && $("#optionsRadioemail2").is(':checked') == false && $("#optionsRadioemail3").is(':checked') == false) {
				alert("Choose a Email Preference");
        		event.preventDefault();
        		ajax = false;
			}else if ($("#optionsRadiowebchat1").is(':checked') == false && $("#optionsRadiowebchat2").is(':checked') == false && $("#optionsRadiowebchat3").is(':checked') == false) {
				alert("Choose a WebChat Preference");
        		event.preventDefault();
        		ajax = false;
			}else if ($("#query").val() =="") {
				alert("Please Enter Your Query");
        		event.preventDefault();
        		ajax = false;
			}else{
				var i=1;
				while(i<=3){
					if(($("#optionsRadiophone"+i).is(':checked') && $("#optionsRadioemail"+i).is(':checked'))
							|| ($("#optionsRadioemail"+i).is(':checked') && $("#optionsRadiowebchat"+i).is(':checked') )
									|| ($("#optionsRadiophone"+i).is(':checked') && $("#optionsRadiowebchat"+i).is(':checked')) ){
						alert("Please Change Your Mode preference.Two modes cannot have the same preference");
						event.preventDefault();
						ajax = false;
					}
					i++;
				}
			}
			if(ajax){
				var name=$("#name").val();
		  		var email=$("#email").val();
		  		var phone=$("#phone").val();
		  		var occupation=$("#occupation").val();
		  		var industry = $("#industry").val();
		  		var radios = document.getElementsByName('service'); 
		  	    var service  = '';
				for (var i = radios.length; i--;) {
			  	    if (radios[i].checked) {
			  	        service = radios[i].value;
			  	        break;
			  	    }
		  		}
				var radios1 = document.getElementsByName('phonemode'); 
		  	    var phonemode  = '';
				for (var i = radios1.length; i--;) {
			  	    if (radios1[i].checked) {
			  	    	phonemode = radios1[i].value;
			  	        break;
			  	    }
		  		}
				var radios2 = document.getElementsByName('emailmode'); 
		  	    var emailmode  = '';
				for (var i = radios2.length; i--;) {
			  	    if (radios2[i].checked) {
			  	    	emailmode = radios2[i].value;
			  	        break;
			  	    }
		  		}
				var radios3 = document.getElementsByName('webchat'); 
		  	    var webchat  = '';
				for (var i = radios3.length; i--;) {
			  	    if (radios3[i].checked) {
			  	    	webchat = radios3[i].value;
			  	        break;
			  	    }
		  		}
				var query = $("#query").val();
				var other = $("#other").val();
		  		$.ajax({
		     	    url : 'WeAreHereToHelp', // Your Servlet mapping or JSP(not suggested)
		     	    data : {"name" :name,"email" :email,"phone":phone,"occupation":occupation,"industry" : industry,"service" : service,"phonemode" :phonemode,"emailmode" :emailmode,"webchat" :webchat,"query" :query,"other" :other},
		     	    type : 'POST',
		     	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
		     	    success : function(response) {
		     	    	  $('#here').html(response);
		     	    },
		     	    error : function(request, textStatus, errorThrown) {
		     	    }
		     	}); 
			}
        });
	});
</script> 
  </body>
</html>