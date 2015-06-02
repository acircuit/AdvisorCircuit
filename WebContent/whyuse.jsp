<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="School, College or On the Job. Everyone needs career advice. Learn how our Advisors can help you.">
    <meta name="author" content="">
    <meta name="keywords" content="career counsellor, how to choose a career, career guidance, how to prepare a resume, telephonic mock interview, interview preparation, how to prepare for an interview, career guidance after 12th, career change.">
    <fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>Why Use Advisor Circuit | Advisor Circuit</title>
	 
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
    
    <div class="why-us content">
        <div class="row primary">
            <div class="col-lg-10 col-lg-offset-1">
                <h1 class="text-center">Why use Advisor Circuit?</h1>
                <p class="text-center">Wondering how advisor circuit can help you? Our advisors have something for everybody; whether you are in school, college or working.</p>
                <h3 class="text-center" style="margin:15px 0 30px 0;">Click on your category to find out more.</h3>
			</div>
            
            <div class="row">
            <div class="col-lg-12">
                <div role="tabpanel">

                  <!-- Nav tabs -->
                  <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="school"><a href="#school" aria-controls="school" role="tab" data-toggle="tab">I am in <br>school</a></li>
                    <li role="presentation" class="college"><a href="#college" aria-controls="college" role="tab" data-toggle="tab">I am in <br>college</a></li>
                    <li role="presentation" class="working"><a href="#working" aria-controls="working" role="tab" data-toggle="tab">I am <br>working</a></li>
                  </ul>

                  <!-- Tab panes -->
                  <div class="tab-content">
                    <div role="tabpanel" class="tab-pane" id="school">
                    	<ul>
                        	<li>
                            	<h4>Career Guidance</h4>
                            	<p>Learn all the options available to you, what you need to do to pursue them further, discover what you might be good at and get answered any other questions you may have about your career</p>
                            </li>
                            <li>
                            	<h4>Choosing the right stream <h4>
                            	<p>Get help to understand which course, education plan you need to follow to get to the career you want and how to prepare for it</p>
                            </li>
                            <li>
                            	<h4>Understanding what really happens in "your dream job" </h4>
                            	<p>Get all your facts right before deciding which career you may want to pursue; learn from our advisors who are accomplished in their fields what a career actually entails and is like</p>
                            </li>
                            <li>
                            	<h4>Getting through the right college </h4>
                            	<p>Confused and overwhelmed with college applications? Start of your career with understanding what your best options are, where and how to apply and what fits you best</p>
                            </li>
                        </ul>
                        <div class="text-center">
                        	<a href="Advisors?service=All" class="btn btn-black">Find advisors now</a>
                        </div>
                    </div>
                    <!-- end school -->
                    
                    <div role="tabpanel" class="tab-pane" id="college">
                    	<ul>
                        	<li>
                            	<h4>Industry specific Career Guidance</h4>
                                <p>Understand what all choices are available to you, what actually happens in that job, the do's and don'ts, what it takes to get there and succeed etc. Talk to somebody with experience about all your doubts and fears.</p>
                            </li>
                            <li>
                            	<h4>Preparing for those placement interviews</h4>
                                <p>Learn how to crack that interview and get your dream job. Experience a "close to actual" interview customised to your requirement; learn what to do and what not to do and gain some confidence!</p>
                            </li>
                            <li>
                            	<h4>Making sure you have the best resume possible</h4>
                                <p>Resumes are critical and are usually your first step in the recruitment cycle. Create the best resume you can with the help of our experts and land that shortlist</p>
                            </li>
                            <li>
                            	<h4>Getting all your facts right before taking a job</h4>
                                <p>Overwhelmed with the several job profiles companies offer? Get to know what that job will really be like, the kind of work involved, the organisation work culture and make an informed decision</p>
                            </li>
                            <li>
                            	<h4>How to make the best of your time in college</h4>
                                <p>Give your career the right start! Understand what activities you should join, what work experience to gather etc, to know what potential recruiters value </p>
                            </li>
                            <li>
                            	<h4>Higher education</h4>
                                <p>Get advice on higher education; whether it's the right decision for you, what course, from where, how to apply etc.</p>
                            </li>
                            <li>
                            	<h4>Networking</h4>
                                <p>Get an opportunity to connect with people and build your network</p>
                            </li>
                        </ul>
                        <div class="text-center">
                        	<a href="Advisors?service=All" class="btn btn-black">Find advisors now</a>
                        </div>
                    </div>
                    <!-- end college -->
                    
                    <div role="tabpanel" class="tab-pane" id="working">
                    	<ul>
                        	<li>
                            	<h4>How to deal with on the job issues</h4>
                                <p>Understand how to better face and handle the issues you face at work; be it a difficult boss, working in a team, handling positions of authority, work specific help required etc</p>
                            </li>
                            <li>
                            	<h4>Becoming the best at what you do</h4>
                                <p>Learn from those more experienced on how they got to where they are and things you can do to excel at your work</p>
                            </li>
                            <li>
                            	<h4>Career growth</h4>
                                <p>Find out what you can do to grow in your field, climb up that corporate ladder, what your next steps should be</p>
                            </li>
                            <li>
                            	<h4>Changing jobs</h4>
                                <p>Get advice on whether it's time to change your job, evaluating other opportunities, preparing yourself for the shift, how to tackle your new job</p>
                            </li>
                            <li>
                            	<h4>Switching industries</h4>
                                <p>Stuck in the wrong job? Feel you will do better in some other field? Understand exactly what kind of work is waiting for you and how to make that transition successfully</p>
                            </li>
                            <li>
                            	<h4>Job interview preparation</h4>
                                <p>Book a mock interview and experience a "close to actual" interview situation to get an idea of what to expect and comprehensive feedback on your performance</p>
                            </li>
                            <li>
                            	<h4>Industry specific profile and resume building</h4>
                                <p>Work with experts to create resumes and other accompaniments (portfolios etc) tailored to your industry</p>
                            </li>
                            <li>
                            	<h4>Networking</h4>
                                <p>Get an opportunity to connect with people in your field and build stronger ties in your industry</p>
                            </li>
                        </ul>
                        <div class="text-center">
                        	<a href="Advisors?service=All" class="btn btn-black">Find advisors now</a>
                        </div>
                    </div>
                    <!-- end working -->
                  </div>

				</div>
            </div>
            </div>
        </div><!-- /row -->
	
        <div class="row text-center primary">
            <div class="col-lg-8 col-lg-offset-2" style="padding-top:12px;">
              <p>Still confused whether we can solve your career doubts? <br><a href="#" data-toggle="modal" data-target="#heretohelp">Send us your query </a> and we will tell you <br>
                whether our advisors can help you or not!</p>
            </div>
            
        </div><!-- /row -->
        <div class="modal fade" id="heretohelp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
							<h4 class="modal-title" id="myModalLabel" style="text-align: center;">Get Help Finding Your Advisor</h4>
						</div>
						<div class="modal-body">
							<h4 style="text-align: center;">Need help? Please send in the below details and we will contact you in no time!</h4>
							<hr>
							<form id="help" class="form-horizontal" role="form" method="post">
								
                                <div class="form-group">
								<label for="email" class="col-md-4 control-label">Email:</label>
                                 <div class="col-md-4">
									  <input id ="email" type="text" name="email" class="form-control" maxlength="150">
								 </div>
                                </div>
                                <div class="form-group">
								<label for="phone" class="col-md-4 control-label">Phone:</label>
                                 <div class="col-md-4">
									  <input id ="phone" type="text" name="phone" class="form-control" maxlength="40">
								 </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-5 col-md-9">
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
	</div>

	<%@include file="/Footer.jsp" %>
			
	</div>
	<!--main container-->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
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
		var input_e = $("#email").val();
		var re = /^[a-zA-Z0-9.!$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		var is_email=re.test(input_e);
		
		var input_p = $("#phone").val();
		var filter = /^\d{10}$/; 
		var is_phone = filter.test(input_p);
		

		if(input_e=='')
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
		}else if (input_p =='') {
			alert("Phone No Required");
			event.preventDefault();
			ajax = false;
		}else if (!is_phone) {
			alert("Please Enter a Valid Phone Number");
			event.preventDefault();
			ajax = false;
		}
		if(ajax){
	  		var email=$("#email").val();
	  		var phone=$("#phone").val();
	  		$.ajax({
	     	    url : 'WeAreHereToHelp', // Your Servlet mapping or JSP(not suggested)
	     	    data : {"email" :email,"phone":phone},
	     	    type : 'POST',
	     	    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
	     	    success : function(response) {
	     	    	  alert(response);
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