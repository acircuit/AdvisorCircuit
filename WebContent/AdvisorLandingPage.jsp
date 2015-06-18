<!DOCTYPE html>
<%@page import="org.AC.dto.ProfessionalBackgroundDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="assets/css/slider.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/main.css" rel="stylesheet">

<!-- Fonts from Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,900'
    rel='stylesheet' type='text/css'>
<link href="assets/css/font-awesome.min.css" rel="stylesheet"
    type="text/css">
<%
    List<AdvisorProfileDTO> advisors= (List<AdvisorProfileDTO>)request.getAttribute("advisorProfile");
        List<AdvisorServiceDTO> advisorskill= (List<AdvisorServiceDTO>)request.getAttribute("advisorService");
        List<ProfessionalBackgroundDTO> professions= (List<ProfessionalBackgroundDTO>)request.getAttribute("profession");
        List<String> industries= (List<String>)request.getAttribute("industries");
        String ids =(String)request.getAttribute("ids");        
        pageContext.setAttribute("advisors", advisors);
        pageContext.setAttribute("advisorservice", advisorskill);
        pageContext.setAttribute("professions", professions);
        pageContext.setAttribute("industries", industries);
        String service = request.getParameter("service");
        String searchWord = request.getParameter("search");
        pageContext.setAttribute("service", service);
%>
</head>

<body>
    <div class="container">
        <c:choose>
            <c:when test="${service.equals('CareerTalk')}">
                <jsp:include flush="true" page="Header.jsp">
                    <jsp:param name="title"
                        value="Career talk - Get career counselling online | Advisor Circuit" />
                </jsp:include>
            </c:when>
            <c:when test="${service.equals('MockInterview')}">
                <jsp:include flush="true" page="Header.jsp">
                    <jsp:param name="title"
                        value="Mock Interview - Learn how to prepare for an interview | Advisor Circuit" />
                </jsp:include>
            </c:when>
            <c:when test="${service.equals('CVCritique')}">
                <jsp:include flush="true" page="Header.jsp">
                    <jsp:param name="title"
                        value="Critique - How to make a good resume | Advisor Circuit" />
                </jsp:include>
            </c:when>
            <c:when test="${service.equals('PersonalWorkshop')}">
                <jsp:include flush="true" page="Header.jsp">
                    <jsp:param name="title" value="Personal Workshop | Advisor Circuit" />
                </jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include flush="true" page="Header.jsp">
                    <jsp:param name="title"
                        value="Career Growth Online | Counselling |Advisor Circuit" />
                </jsp:include>
            </c:otherwise>
        </c:choose>

        <div id="head"></div>

        <div class="content landingpage">
            <c:set var="services" value="<%=service%>"></c:set>
            <c:if test="${services.equals('CareerTalk')}">
                <div class="row carrer-talk">
                    <div class="col-md-8 col-md-offset-2 text-center">
                        <h2>Career Talk</h2>
                        <p>A career talk with an industry expert can help you better
                            understand your own choices for your career. It's an opportunity
                            to connect with an advisor to clarify your doubts that you may be
                            having about choosing an industry, understanding more about a
                            particular field, discussing issues you are facing on the job and
                            a lot more.</p>
                        <h1>Check out the below advisor profiles to choose who you'd
                            want to take a session with!</h1>
                        <a href="#" data-toggle="modal" data-target="#heretohelp"><h4>Confused
                                about which advisor to choose? We are here to help.</h4>
                            <a>
                                <div class="hode">
                                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Show/Hide
                                        Filters</a>
                                </div>
                    </div>
                </div>
                <!-- /row -->
            </c:if>

            <c:if test="${services.equals('CVCritique')}">
                <div class="row resume">
                    <div class="col-md-8 col-lg-offset-2 text-center">
                        <h2>Resume Critique</h2>
                        <p>Resume Critique involves an industry expert analyzing your
                            resume and giving feedback to give you an opportunity to
                            understand how a recruiter perceives the same. Get in touch with
                            actual recruiters, headhunters and corporate insiders to know
                            more about your strengths, weaknesses and areas of improvement.
                            Work with them to help build a strong front for your
                            applications.</p>
                        <h1>Check out the below advisor profiles to choose who you'd
                            want to take a session with!</h1>
                        <a href="#" data-toggle="modal" data-target="#heretohelp"><h4>Confused
                                about which advisor to choose? We are here to help.</h4>
                            <a>
                                <div class="hode">
                                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Show/Hide
                                        Filters</a>
                                </div>
                    </div>
                </div>
                <!-- /row -->
            </c:if>

            <c:if test="${services.equals('MockInterview')}">
                <div class="row mock">
                    <div class="col-md-8 col-lg-offset-2 text-center">
                        <h2>Mock Interview</h2>
                        <p>Mock interview lets you experience a practice interview
                            which is conducted by an expert from your field. It's a great way
                            to get an idea of how an interviewer forms his questions, his
                            assessment of your answers and personality. Work with your
                            advisor to get a sneak peek into your interviewer's mind and
                            learn how to tackle tricky questions and answer queries in an
                            impressive way.</p>
                        <h1>Check out the below advisor profiles to choose who you'd
                            want to take a session with!</h1>
                        <a href="#" data-toggle="modal" data-target="#heretohelp"><h4>Confused
                                about which advisor to choose? We are here to help.</h4>
                            <a>
                                <div class="hode">
                                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Show/Hide
                                        Filters</a>
                                </div>
                    </div>
                </div>
                <!-- /row -->
            </c:if>

            <c:if test="${services.equals('All')}">
                <div class="row our-advisor">
                    <div class="col-md-8 col-lg-offset-2 text-center">
                        <h2>Our Advisors</h2>
                        <p>We have diverse professionals from different industries who
                            can help you grow in your career and excel in your field. Ask
                            them any and every career related query you may have; they are
                            availble for sessions through phone, email and web chat. So go
                            ahead, book a session and take advantage of our advisors' years
                            of experience!</p>
                        <h1>Check out the below advisor profiles to choose who you'd
                            want to take a session with!</h1>
                        <a href="#" data-toggle="modal" data-target="#heretohelp"><h4>Confused
                                about which advisor to choose? We are here to help.</h4>
                            <a>
                                <div class="hode">
                                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Show/Hide
                                        Filters</a>
                                </div>
                    </div>
                </div>
                <!-- /row -->
            </c:if>
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
            <c:choose>
                <c:when test="${(services).equals('PersonalWorkshop')}">
                    <div class="row workscope">
                        <div class="col-md-8 col-lg-offset-2 text-center">
                            <h2>Personal Workshop</h2>
                            <p>A personal workshop is a way for you to understand and
                                learn key skills that experts have acquired over the years and
                                in turn use them to further your career. Let the expert take you
                                through a topic and teach you certain skills that they think are
                                important for career development. You're even assessed in the
                                end to determine how much you've learnt!</p>
                            <h1>This service will be coming up shortly!</h1>
                            <div class="hode">
                                <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Show/Hide
                                    Filters</a>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </c:when>
                <c:otherwise>

                    <div class="row">
                        <div class="col-md-6 filter-list">
                            <ul></ul>
                        </div>
                        <div class="col-md-6 pull-right text-right">

                            <ul class="pagination">
                                <li id="PageList"></li>
                            </ul>
                        </div>
                    </div>
                    <!--end pagination-->
                    

                    <div class="row users-list">

                        <div class="col-md-2 ac-advisor-sidebar">
                            <!-- Sidebar -->
                            <a class="selectServices" href="#">Select Filter</a>
                            <div id="sidebar-wrapper">
                                <h4>Filter By Services</h4>
                                <h4>
                                    Industries
                                    <div class="pull-right ac-nav-tree" for="industries">
                                        <i class="glyphicon glyphicon-minus-sign"></i>
                                    </div>
                                </h4>
                                <div class="tree-container" data-tree="industries">
                                    <ul class="sidebar-nav list-unstyled industry hidden-xs hidden-sm">
                                        <c:forEach var="industry" items="${industries}">
                                            <li>
                                                <label class="filter_lab">
                                                    <input id="Industry${industry}" onchange="CreateFilterParameter(this)" type="checkbox" style="width: 25px;">${industry}
                                                </label>
                                            </li>
                                        </c:forEach>
<!--                                         <c:forEach var="industry" items="${industries}" begin="6">
                                            <li class="hidden">
                                                <label class="filter_lab">
                                                    <input id="Industry${industry}" onchange="CreateFilterParameter(this)" type="checkbox" style="width: 25px;">${industry}
                                                </label>
                                            </li>
                                        </c:forEach> -->
                                    </ul>
                                    <div class="hidden-xs hidden-sm show-more-industries"type="button" data-toggle="modal" data-target="#exampleModal">Show More</div>
                                    <ul class="sidebar-nav list-unstyled industry ac-popover-industry hidden-md hidden-lg">
                                        <c:forEach var="industry" items="${industries}">
                                            <li>
                                                <label class="filter_lab">
                                                    <input id="Industry${industry}" onchange="CreateFilterParameter(this)" type="checkbox" style="width: 25px;">${industry}
                                                </label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" id="exampleModal">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                                    <h4 class="modal-title" id="gridSystemModalLabel">Filter By Services</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <ul class="sidebar-nav list-unstyled">
                                                        <c:forEach var="industry" items="${industries}">
                                                            <li>
                                                                <label class="filter_lab">
                                                                    <input id="ModalIndustry${industry}" onchange="CreateFilterParameter(this,true)" type="checkbox" style="width: 25px;">${industry}
                                                                </label>
                                                            </li>
                                                        </c:forEach>
                                                    </ul> 
                                                </div>
                                            </div>
                                        </div>
                                    </div> 
                                </div>

                                <h4>
                                    Services
                                    <div class="pull-right ac-nav-tree" for="services">
                                        <i class="glyphicon glyphicon-minus-sign"></i>
                                    </div>
                                </h4>
                                <div class="tree-container" data-tree="services">
                                    <ul class="sidebar-nav list-unstyled">
                                        <li><label class="filter_lab"> <input
                                                id="Servicecareertalk" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">Career Talk
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Servicecvcritique" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">Resume Critique
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Servicemockinterview"
                                                onchange="CreateFilterParameter(this)" type="checkbox"
                                                style="width: 25px;">Mock Interview
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Servicepersonalworkshops"
                                                onchange="CreateFilterParameter(this)" type="checkbox"
                                                style="width: 25px;">Personal Workshop
                                        </label></li>
                                    </ul>
                                </div>

                                <h4>
                                    Experience
                                    <div class="pull-right ac-nav-tree" for="experience">
                                        <i class="glyphicon glyphicon-minus-sign"></i>
                                    </div>
                                </h4>
                                <div class="tree-container" data-tree="experience">
                                    <ul class="sidebar-nav list-unstyled">
                                        <li><label class="filter_lab"> <input
                                                id="Experience5" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">0-5 Years
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Experience10" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">5-10 Years
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Experience15" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">>10 Years
                                        </label></li>
                                    </ul>
                                </div>

                                <h4>
                                    Modes
                                    <div class="pull-right ac-nav-tree" for="modes">
                                        <i class="glyphicon glyphicon-minus-sign"></i>
                                    </div>
                                </h4>
                                <div class="tree-container" data-tree="modes">
                                    <ul class="sidebar-nav list-unstyled">
                                        <li><label class="filter_lab"> <input
                                                id="Modephone" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">Phone
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Modeemail" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">Email
                                        </label></li>
                                        <li><label class="filter_lab"> <input
                                                id="Modewebchat" onchange="CreateFilterParameter(this)"
                                                type="checkbox" style="width: 25px;">Webchat
                                        </label></li>
                                    </ul>
                                </div>
                            </div>
                            <!-- /#sidebar-wrapper -->
                        </div>
                        <!-- /#sidebar -->

                        <div class="col-md-10">
                            <div class="row">
                                <ul id="gallary">
                                    <c:choose>
                                        <c:when test="${advisors.size() > 0 && advisorservice.size() > 0}">
                                            <c:set var="counter" value="0"></c:set>
                                            <c:forEach var="advisor" items="${advisors}">
                                                <c:set var="counter" value="${counter +1 }"></c:set>
                                                <c:if test="${counter == 1 }">
                                                    <li>
                                                </c:if>
                                                <div class="col-md-6" id="advisor">
                                                    <div class="pattern clearfix">
                                                        <div class="row">
                                                            <c:url value="AdvisorProfile" var="id">
                                                                <c:param name="aId" value="${advisor.getAdvisorId()}" />
                                                            </c:url>
                                                            <c:set var="str"
                                                                value="${str.concat(advisor.getAdvisorId())}"></c:set>

                                                            <div class="col-md-6 col-xs-6 user-pic">
                                                                <a href="${id}"><img src="${advisor.getImage()}"
                                                                    alt="" height="165"></a>
                                                            </div>

                                                            <div class="col-md-6 col-xs-6 user-detail">
                                                                <div class="prof">
                                                                    <c:url value="AdvisorProfile" var="id">
                                                                        <c:param name="aId" value="${advisor.getAdvisorId()}" />
                                                                    </c:url>
                                                                    <h4 class="name">
                                                                        <a href="${id}"><c:out
                                                                                value="${advisor.getName()}"></c:out></a>
                                                                    </h4>
                                                                    <h4 class="industry">
                                                                        <a href="${id}"><c:out
                                                                                value="${advisor.getIndustry()}"></c:out></a>
                                                                    </h4>
                                                                    <c:set var="doneLoop" value="false" />
                                                                    <c:forEach var="profession" items="${professions}">
                                                                        <c:if
                                                                            test="${advisor.getAdvisorId() == profession.getAdvisorId() && not doneLoop }">
                                                                            <p>
                                                                                <a href="${id}"><c:out
                                                                                        value="${profession.getDesignation()}"></c:out></a><br>
                                                                                <a href="${id}"><c:out
                                                                                        value="${profession.getCompany()}"></c:out></a>
                                                                            </p>
                                                                            <c:set var="doneLoop" value="true" />
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-12 desc">
                                                                <p>
                                                                    <a href="${id}"><c:out
                                                                            value="${advisor.getIntroduction()}..."></c:out></a>
                                                                </p>
                                                            </div>

                                                            <a href="${id}">
                                                                <div class="col-md-12 brief">
                                                                    <div class="col-md-9 col-xs-8">
                                                                        <c:forEach var="service" items="${advisorservice}">
                                                                            <c:if
                                                                                test="${service.getAdvisorId() == advisor.getAdvisorId()}">

                                                                                <c:if
                                                                                    test="${service.getService().equals('careertalk') }">
                                                                                    <div class="col-md-3 col-xs-3 text-center"
                                                                                        id="image">
                                                                                        <img
                                                                                            src="assets/img/WebMail/HomePage/Panel_2_Icon_1.png"
                                                                                            width="45" alt="">
                                                                                        <p>Career Talk</p>
                                                                                    </div>
                                                                                    <!--/col-lg-3-->
                                                                                </c:if>

                                                                                <c:if
                                                                                    test="${service.getService().equals('mockinterview') }">
                                                                                    <div class="col-md-3 col-xs-3 text-center"
                                                                                        id="image">
                                                                                        <img
                                                                                            src="assets/img/WebMail/HomePage/Panel_2_Icon_2.png"
                                                                                            width="45" alt="">
                                                                                        <p>Mock Interview</p>
                                                                                    </div>
                                                                                    <!--/col-lg-3-->
                                                                                </c:if>

                                                                                <c:if
                                                                                    test="${service.getService().equals('cvcritique') }">
                                                                                    <div class="col-md-3 col-xs-3 text-center"
                                                                                        id="image">
                                                                                        <img
                                                                                            src="assets/img/WebMail/HomePage/Panel_2_Icon_3.png"
                                                                                            width="45" alt="">
                                                                                        <p>Resume Critique</p>
                                                                                    </div>
                                                                                    <!--/col-lg-3-->
                                                                                </c:if>

                                                                                <c:if
                                                                                    test="${service.getService().equals('personalworkshops') }">
                                                                                    <div class="col-md-3 col-xs-3 text-center"
                                                                                        id="image">
                                                                                        <img
                                                                                            src="assets/img/WebMail/HomePage/Panel_2_Icon_4.png"
                                                                                            width="45" alt="">
                                                                                        <p>Personal Workshop</p>
                                                                                    </div>
                                                                                    <!--/col-lg-3-->
                                                                                </c:if>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>

                                                                    <div class="review text-center pull-right" id="image">
                                                                        <a id="${advisor.getAdvisorId()}" href="#" onclick="GetAdvisorReview(this)" data-toggle="modal" data-target="#datepay">
                                                                            <img src="assets/img/services/Icon_Reviews.png" style="width: 45px;" alt=""/>
                                                                            <p>Reviews</p>
                                                                        </a>
                                                                    </div>
                                                                    <!--/col-lg-4 -->

                                                                </div>
                                                            </a>

                                                        </div>
                                                    </div>
                                                </div>
                                                <c:if test="${counter == 6}">
                                                    </li>
                                                    <c:set var="counter" value="0"></c:set>
                                                </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <h4 style="font-style: solid">
                                                <p>Sorry there are no advisors that match your search. Please try a different keyword or <a href = "Advisors?service=all">click here</a> to view all our advisors.</p>
                                            </h4>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                                </li>

                                <div class="modal fade" id="datepay" tabindex="-1" role="dialog"
                                    aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">x</button>
                                                <h4 class="modal-title" id="myModalLabel">Advisor's Reviews</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="panel panel-info">
                                                    <div class="panel-body">
                                                        <div id="reviews" class="myreview"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /#modelwindow -->

                            </div>
                            <!-- /#userlist -->

                        </div>
                        <!--end userlist-->

                    </div>

                    <div class="row">
                        <div class="col-md-12 pull-right text-right">
                            <ul class="pagination">
                                <li id="PageList2"></li>
                            </ul>
                        </div>
                    </div>
                    <!--end pagination-->

        </div>
        <!-- /end content -->

        </c:otherwise>
        </c:choose>

        <%@include file="/Footer.jsp"%>
    </div>
    <!--main container-->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
   <script src="assets/js/bootstrap-slider.js"></script>
   <script src="assets/js/DoublePagination.js"></script>
   <!-- Menu Toggle Script -->
   <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    
    $('#ex1').slider({
    formatter: function(value) {
        return 'Current value: ' + value;
    }
    });
    $("#ex2").slider({});

    </script>
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
    
    if(${services == "CareerTalk"}){
        document.getElementById("Servicecareertalk").checked = true;
    }else if(${services == "PersonalWorkshop"}){
        document.getElementById("Servicepersonalworkshops").checked = true;
    }else if(${services == "MockInterview"}){
        document.getElementById("Servicemockinterview").checked = true;
    }else if(${services == "CVCritique"}){
        document.getElementById("Servicecvcritique").checked = true;
    }
    
    function GetAdvisorReview(e){
        var val = e.id;
        $.ajax({
            url : 'GetReviews', // Your Servlet mapping or JSP(not suggested)
            data : {"aId" : val},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
                $('#reviews').html(response); // create an empty div in your page with some id
            },
            error : function(request, textStatus, errorThrown) {
                alert(errorThrown);
            }
        }); 
    }
    var filter = ":";
    //var ids= "";
    var id = "${ids}";
    function CreateFilterParameter(e,isModal){
        var val = e.id;
        if(isModal){
            val=e.id.replace('Modal','');
        }
        var checkbox = document.getElementById(val);
        checkbox.checked = e.checked;
        var flag = true;
        if(checkbox.checked){
            filter = filter.concat(val +':');
            $('.filter-list ul').append('<li class="filter"><span>'+e.labels[0].innerText+'</span><span class="glyphicon glyphicon-remove" for="'+val+'"></span></li>');
        }else{
            filter = filter.replace(":"+val+":", ":");
            $('.filter-list ul').find('li span.glyphicon[for="'+ val +'"]').parent('li').remove();
        }
        if(filter == ":"){
            location.href = "Advisors?service=All";
            flag=false;
        }
        if(!isModal){
            var modalFilter = document.getElementById('Modal'+e.id);
            if(modalFilter) modalFilter.checked = e.checked;
        }
        /*var id = "";
        if(ids == ""){
         id = "${ids}";
        }else{
         id = ids;  
        }*/
        if(flag){
            $.ajax({
                url : 'Filter', // Your Servlet mapping or JSP(not suggested)
                data : {"filter" : filter,"ids" : id},
                type : 'POST',
                dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
                success : function(response) {
                    var responses = response.split(":::");
                    //ids = responses[1];
                    $('#PageList').html("");
                    $('#PageList2').html("");
                    $('#gallary').html(responses[0]);// create an empty div in your page with some id
                    $("#gallary").Pagination();
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            }); 
        }
    }
  

    $(document).ready(function() {
        $("#gallary").Pagination();
        $(".selectServices").click(function(e) {
            e.preventDefault();
            $("#sidebar-wrapper").slideToggle();
        });
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
    
        $('.ac-advisor-sidebar').on('click', 'h4 .ac-nav-tree', function(e){
            var nav = $(this),
                navId = nav.attr('for'),
                navCon = $('.ac-advisor-sidebar').find('div[data-tree="'+navId+'"]');

            if(nav.hasClass('collapsed')){
                nav.removeClass('collapsed');
                nav.find('i').removeClass('glyphicon-plus-sign').addClass('glyphicon-minus-sign');
                navCon.slideDown();
            } else{
                nav.addClass('collapsed');
                nav.find('i').removeClass('glyphicon-minus-sign').addClass('glyphicon-plus-sign');
                navCon.slideUp();
            }
        });
        $('.filter-list ul').on('click','li.filter .glyphicon-remove', function(e){
            e.stopPropagation();
            CreateFilterParameter({
                id : $(this).attr('for'),
                checked : false
            },false);
        });
    });
  
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
