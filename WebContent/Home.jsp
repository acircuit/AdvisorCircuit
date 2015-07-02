<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Use Advisor Circuit network to connect with industry experts. Reach out for career advice, resume critiques, mock interviews and workshops. Land a job. Get shortlisted. Grow in your career. Understand how to choose a career">
    <meta name="author" content="Advisor Circuit">
    <meta name="keywords" content="prepare interview, career guidance, telephonic interview, career options, how to prepare for an interview, career growth, how to choose a career, career guidance after 12th, how to make a good resume, online career counselling.">

    <title>Career Growth Online | Counselling | Advisor Circuit</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <fmt:bundle basename="Resources.Dependency" prefix="path.">
         <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>
    </fmt:bundle>
  </head>

  <body>
  <div class="container">

    


    <div class="alert ac-alert hidden-lg" role="alert">
        <!-- <a class="hidden-xs hidden-sm">Invite others and we will create your resume</a> -->
        <a href="#" type="button" data-toggle="modal" data-target="#ac-promotion-banner">Click here and get your resume created for FREE</a>
        <button class="glyphicon glyphicon-remove pull-right ac-dismiss"></button>
    </div>
    <div class="modal fade ac-promotion-banner" role="dialog" aria-labelledby="ac-promotion-banner" id="ac-promotion-banner">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
            	<div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                 </div>
                <div class="modal-body">
                    <div class="row">
                        <img  class="col-sm-12" src="assets/img/PromotionBannerMobile.jpg" alt="" height="100%" width="100%">
                    </div>
                    <div class="">
                        <a href="login"><button class="btn btn-ac center-block" style="margin-bottom:20px;margin-top:20px;width:60%;background-color:#c84c4e;font-size:30px;color: #fff;">START NOW</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/Header.jsp" %>

    <div id="ac-home-carousel" class="carousel slide hidden-xs hidden-sm hidden-md" data-ride="carousel">
      <!--   <ol class="carousel-indicators" style="bottom:0px;">
            <li data-target="#ac-home-carousel" data-slide-to="0" class="active"></li>
            <li data-target="#ac-home-carousel" data-slide-to="1"></li>
        </ol> -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <div class="row main-wrapper">
                    <div class="col-md-12 text-center">
                        <h1>Have questions about your career?<br/>Take charge today!</h1>
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
                </div>
            </div>
            <div class="item ">
                <img src="assets/img/PromotionBanner1.jpg" alt="Search for your advisor">
                <div class="carousel-caption"></div>
            </div>
        </div>
        <a class="left carousel-control" href="#ac-home-carousel" role="button" data-slide="prev" style="opacity:0.1">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true" style="margin-left:-75px"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#ac-home-carousel" role="button" data-slide="next" style="opacity:0.1">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="margin-right:-75px"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="row main-wrapper hidden-lg">
        <div class="col-md-12 text-center">
            <h1>Have questions about your career?<br/>Take charge today!</h1>
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
    </div>

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
                            <h4 style="text-align: center;">Send in the below details and we will contact you in no time!</h4>
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
                                    <div class="col-md-offset-5 col-md-12">
                                        <button id="submit_btn" style="font-family:'custom_light' !important;margin-left: 5%" type="button"  class="btn btn-info">Submit</button>
                                        <!--<button id="btn" type="submit" class="btn btn-info">Cancel</button> -->
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
    <script src="assets/js/acInviteNotification.js"></script>
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
