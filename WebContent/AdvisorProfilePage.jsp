<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
<%@page import="org.AC.dto.ProfessionalBackgroundDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
<%@page import="org.AC.dto.AdvisorModeDTO"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/favicon.png">

    <title>Advisor Profile</title>
	<style type="text/css"> 
		.carousel-control.right{background-image:none !important;}
		.carousel-control.left{background-image:none !important;}
		.bdesign{font-size:20px !important;}
		.imgresize{width:100%}
		@media only screen and (max-width:700px){
		.font_size{font-size:22px !important;}
		.imgresize{width:100%}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:16px !important;}
		.hode{display:none;}
		.imgresize{width:100%;height:180px !important}
		.resize_height{height:180px}
		}
		
		#datetimepicker1,#datetimepicker1 input,#datetimepicker2,#datetimepicker2 input,#datetimepicker3,#datetimepicker3 input,#datetimepicker4,#datetimepicker4 input,#datetimepicker5,#datetimepicker5 input,#datetimepicker6,#datetimepicker6 input{display: inline-block;
		margin-bottom: 0;
		vertical-align: middle;}
		
		#datetimepicker1 .add-on,#datetimepicker6 .add-on,#datetimepicker2 .add-on,#datetimepicker3 .add-on,#datetimepicker4 .add-on,#datetimepicker5 .add-on{display: inline-block;
		width: auto;
		height: 28px;
		min-width: 16px;
		padding: 0;
		font-size: 14px;
		font-weight: normal;
		line-height: 20px;
		text-align: center;
		text-shadow: 0 1px 0 #fff;
		background-color: #eee;
		border: 1px solid #ccc;
		vertical-align: top;
		margin-left: -1px;
		border-radius: 0 4px 4px 0;}
		
		#datetimepicker1 .add-on i,#datetimepicker2 .add-on i,#datetimepicker3 .add-on i,#datetimepicker4 .add-on i,#datetimepicker5 .add-on i,#datetimepicker6 .add-on i{
		margin-top:3px;
		line-height: 14px;
		vertical-align: text-top;
		background-image: url("assets/img/glyphicons-halflings.png");
		background-position: -192px -120px;
		background-repeat: no-repeat;}
		
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:16px !important;}
		.imgresize{width:50%;height:180px !important}
		}
		 </style>  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	 <link href="assets/css/full-slider.css" rel="stylesheet">
	 <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	 <script src="assets/js/jquery-1.11.0.js"></script>
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
   	<%
   		List<AdvisorProfileDTO> advisors= (List<AdvisorProfileDTO>)request.getAttribute("advisordetails");
   	  	List<ProfessionalBackgroundDTO> profession= (List<ProfessionalBackgroundDTO>)request.getAttribute("advisorprofession");
   	 	List<AdvisorServiceDTO> services= (List<AdvisorServiceDTO>)request.getAttribute("advisorservices");
   	 	List<AdvisorModeDTO> modes= (List<AdvisorModeDTO>)request.getAttribute("advisormodes");
		String advisorId = (String)request.getParameter("aId");
		request.setAttribute("advisorId", advisorId);	
   		    int  uId =  0;
   		    if(session.getAttribute("userId") != null){
   	 	    uId = (Integer)session.getAttribute("userId");
   		    }
   	 		Boolean redirect = false;
   	 		if ( uId == 0){
   	 			redirect = true;
   	 		}
   			pageContext.setAttribute("advisors", advisors);
   			pageContext.setAttribute("profession", profession);
   			pageContext.setAttribute("services", services);
   			pageContext.setAttribute("modes", modes);
   			
   	%>
  
  </head>

  <body>

    <%@include file="/Header.jsp" %>
	<div style="height:100px"></div>
	<div class="container">
					
				<div style="margin-left:8%">	
				
				<div style="width:100%">
					<table width="100%">
					<tr><td style=" width:65%;height:300px;" class="resize_height"><!-- Full Page Image Background Carousel Header -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('assets/img/profile_car2.png');"></div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Two');"></div>

            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('assets/img/profile_car.png');"></div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>

					</td>
					
				<c:forEach items="${advisors}" var="advisor">
					<td valign="top" style="padding:1% 4% 1% 4%">
					<h3 style="color:rgba(123, 119, 125, 1);font-size:34px;letter-spacing:1px" class="font_size">
					<b><c:out value="${advisor.getName()}"/></b></h3><h4 style="font-size:26px;color:#848181" class="font_size"><c:out value="${ advisor.getOccupation()}"/></h4>
					</br>
						<p  class="font_size"><a id="book_a_session" onclick="div_show()" class="btn btn-primary btn-lg" role="button">Book Session</a></p>
					</td>
					</tr>
					</table>
					
					<div style="height:40px"></div>
					
					<p style="width:85%"><c:out value="${advisor.getIntroduction()}"/></p>
				</div>
					
					
					<div style="height:40px"></div>
				
				<!--advisor experience table-->
				<table  style="width:90%;background-color:rgba(243, 243, 243, 0.9);border-top:1px solid #ccc">
					<tr>
						<td rowspan="5" valign="top" class="hode">
						<h2 style="font-size:22px;padding:1% 5% 1% 5%;"><b>Kishi is available for the following services : </b></h2>
						
						<c:forEach items="${services}" var="service">
							<div class="centered">
							<img class="hode" src="assets/img/carrier.png" alt="">
							<h4><c:out value="${service.getService()}"></c:out></h4>
							<p><c:out value="${service.getDescription()}"></c:out> </p>
							<a href=# class="btn btn-default btn-sm">Read More</a>
							<hr style="width:80%;margin-left:10%">
							</div>
						</c:forEach>
						</td>
						
						<td valign="top" style="padding:1% 5% 1% 5%;border-bottom:1px solid #ccc;" class="font_size">
						<h2 style="font-size:20px" class="font_size"><b>EDUCATION:<b></h2>
						<h5 style="font-size:16px;" class="font_size">
									<c:out value="${advisor.getUg()}"/><br>
									<c:out value="${advisor.getPg()}"/><br>
									<c:if test="${!empty advisor.getOthersStringArray()}">
										<c:forEach items="${advisor.getOthersStringArray()}" var="others">
												<c:out value="${others}"/>
										</c:forEach>
									</c:if>
								
						</h5>
						
						</td>
					</tr>
					<tr>
						
							<td valign="top" style="padding:1% 5% 1% 5%;border-bottom:1px solid #ccc">
							<h2 style="font-size:20px" class="font_size"><b>PROFESSIONAL BACKGROUND:<b></h2>
							<c:forEach items="${profession}" var="prof">
								<h5 style="font-size:16px;" class="font_size">
								<b><c:out value="${prof.getCompany()}"></c:out>,<c:out value="${prof.getDesignation()}"></c:out></b>
								<br>
								<c:forEach items="${prof.getDescriptionArray()}" var="description">
									<ul>
										<li style="margin-top: 10px"><c:out value="${description}"></c:out>
									</ul>
								</c:forEach>
								</h5>
							</c:forEach>
							</td>
					
					</tr>
					
					<tr>
						<td valign="top" style="padding:1% 5% 1% 5%;border-bottom:1px solid #ccc">
						<h2 style="font-size:20px" class="font_size"><b>SKILLS:<b></h2>
						<h5 style="font-size:16px;" class="font_size">
						<c:forEach items="${advisor.getKeyskills()}" var="skill">
							<ul>
									<li><c:out value="${skill}"/>
							</ul>
						</c:forEach>
						</h5>
						</td>
					</tr>
					
					
						<tr>
							<td valign="top" style="padding:1% 5% 1% 5%;border-bottom:1px solid #ccc">
							<h2 style="font-size:20px" class="font_size"><b>AWARDS AND ACHIEVEMENTS :<b></h2>
					
							<h5 style="font-size:16px;" class="font_size"><br>
							<ul>
							<c:forEach items="${advisor.getAchievementsStringArray()}" var="achievement">
									<li><c:out value="${achievement}"/>
							</c:forEach>
							</ul>
							</h5>
							
							</td>
						</tr>
					
					<tr>
						<td valign="top" style="padding:1% 5% 1% 5%;">
						<h2 style="font-size:20px" class="font_size"><b>KNOW YOUR ADVISOR BETTER :<b></h2>
						<h5 style="font-size:16px;" class="font_size"><c:out value="${advisor.getKnowYourAdvisor()}"/>
						</h5>
						<div style="height:60px"></div>
						<a class="btn btn-primary btn-lg" role="button"><b style="font-size:26px" class="font_size">Book Session</b></a>
						</td>
					</tr>
				</table>
				<!--advisor experience table ends-->
			</c:forEach>
		<div style="height:40px"></div>
		<hr style="width:95%">
			<!--review-->
			<div style="padding:0.01% 5% 5% 5%">
			<h2 style="font-size:26px"><b>REVIEWS</b></h2>
								<input type="hidden" name="redirect" id="redirect" value="<%=redirect%>">
								<input type="hidden" name="aId" id="aId" value="<%=advisorId%>">
			
			<!-- Comment -->
                <div class="media">
                    <a class="pull-left" href="#">
                        <img class="media-object" src="assets/img/aditya.png" alt="" style="width:64px;height:64px">
                    </a>
                    <div class="media-body">
                        <h2 class="media-heading"> 	
								<b style="font-size:18px">Amie Williams</b>
							<small style="font-size:14px">August 25, 2014 at 9:30 PM</small>
                        </h2>
                      <font style="font-size:15px">
					  Definitely the most professional people I've worked with. I got everything I expected and more. Highly recommended. 
					  </font>
					</div>
                </div>
				
				<hr style="width:70%;margin-left:10%">
				
                <!--2nd Comment -->
                <div class="media">
                    <a class="pull-left" href="#">
                        <img class="media-object" src="assets/img/uday.png" alt="" style="width:64px;height:64px">
                    </a>
                    <div class="media-body">
                        <h2 class="media-heading"> 	
								<b style="font-size:18px">Uday Khatry</b>
							<small style="font-size:14px">August 25, 2014 at 9:30 PM</small>
                        </h2>
                      <font style="font-size:15px">
					  Definitely the most professional people I've worked with. I got everything I expected and more. Highly recommended. 
					  </font> 
                        <!-- Nested Comment 
                        <div class="media">
                            <a class="pull-left" href="#">
                                <img class="media-object" src="http://placehold.it/64x64" alt="">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">Nested Start Bootstrap
                                    <small>August 25, 2014 at 9:30 PM</small>
                                </h4>
                                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                            </div>
                        </div>
                        End Nested Comment -->
                    </div>
                </div>

			</div>
			<!--review ends-->

			<hr style="width:95%">
			
			<div class="row mt centered">
			<div class="col-lg-6 col-lg-offset-3">
				<h2><b>Similar Advisors</b></h2>
			</div>
		</div><!-- /row -->
		
		<div class="row mt centered">
			<div style="margin-left:-4%">
			<div class="col-lg-4">
				<img src="assets/img/aditya.png" style="width:200px;height:150px">
				<h2 style="font-size:22px">Aditya</h2>
				<h4 style="font-size:18px"><b>Chef</b></h4>
				<p>Talk to experts about any kind of career related queries you have for cooking</p>
			</div><!--/col-lg-4 -->

			<div class="col-lg-4">
				<img src="assets/img/uday.png" style="width:200px;height:150px">
				<h2 style="font-size:22px">Uday</h2>
				<h4 style="font-size:18px"><b>Consultant</b></h4>
				<p>I am available for career conversations and mock interviews.</p>

			</div><!--/col-lg-4 -->

			<div class="col-lg-4">
				<img src="assets/img/lavi.png" style="width:200px;height:150px">
				<h2 style="font-size:22px">Lavi</h2>
				<h4 style="font-size:18px"><b>Fashion Designer</b></h4>
				<p>Look good, Otherwise don't show me your face - Fashion </p>

			</div><!--/col-lg-4 -->
			</div>
		</div><!-- /row -->
			
			
	</div>
	 <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">

                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Book Session
                            <img alt="" id="close" onclick="div_hide()" src="assets/img/close.png" style="float: right; ">
                            </div>
                        </div>
  						
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" action="BookASessionServlet" method="post" enctype="multipart/form-data">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Services</label>
                                     <div class="col-md-9">
										<select class="form-control" id="services_dropdown" name="services_dropdown" >
											<option>Select a Service</option>
											<c:forEach items="${services}" var="service">
												<option>${service.getService()}</option>
				                            </c:forEach>
			                            </select>
									 </div>
                                </div>
                               	<c:forEach items="${services}" var="service">	
                               		<input type="hidden" value="${service.getAdvisorId()}" name="aId"></input>
                               	</c:forEach>	
                                 <div id="modediv" class="form-group collapse">
                                 		<label for="icode" class="col-md-3 control-label">Mode</label>
										<div class="col-md-9">
	                                            <label id="modes" class="radio-inline" >
	                                            <ul style="list-style-type: none;display: inline;padding: 0"></ul>
	                                            </label>
                                        </div>
								</div>
								 <div id="cvdiv" class="form-group collapse">
                                 		<label for="icode" class="col-md-3 control-label">Upload Your CV</label>
										<div class="col-md-9">
	                                           <input type="file" name= "cv" id="cv" accept="application/pdf"  >
                                        </div>
								</div>	
								 <div id="durationdiv" class="form-group collapse">
                                            <label for="icode" class="col-md-3 control-label">Duration</label>
                                            <div class="col-md-9">
											<label id="duration" class="radio-inline">
                                                <input type="radio" onclick="getPrice()" name="duration" id="optionsRadiosInline1" value="0.5">Half Hour
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" onclick="getPrice()" name="duration" id="optionsRadiosInline2" value="1">1 hour
                                            </label>
                                         	 <label class="radio-inline">
                                                <input type="radio" onclick="getPrice()" name="duration" id="optionsRadiosInline3" value="1.5">1.5 Hour
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" onclick="getPrice()" name="duration" id="optionsRadiosInline3" value="2">2  Hour
                                            </label>
										</div>	
                                </div>								
								<div id="pricediv" class="form-group collapse">
                                    <label for="icode" class="col-md-3 control-label">Price</label>
                                    <div class="col-md-9">
                                        <input type="text" name="price" class="form-control" id="disabledInput" placeholder="" readOnly="true">
                                    </div>
                                </div>		
								  
                               
								
								
								<div style="height:10px"></div>
								<h3>Choose Date and Time</h3>
								<hr>
							
								 <div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 1</label>
										<div id="datetimepicker1" class="input-append date">
										  <input type="datetime" name="datetimepicker1"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 2</label>
										<div id="datetimepicker2" class="input-append date">
										  <input type="datetime" name="datetimepicker2"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 3</label>
										<div id="datetimepicker3" class="input-append date">
										  <input type="datetime" name="datetimepicker3"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 4</label>
										<div id="datetimepicker4" class="input-append date">
										  <input type="datetime" name="datetimepicker4"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 5</label>
										<div id="datetimepicker5" class="input-append date">
										  <input type="datetime" name="datetimepicker5"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 6</label>
										<div id="datetimepicker6" class="input-append date">
										  <input type="datetime" name="datetimepicker6"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<hr>
								
								<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Enter Your Query :</label>
                                    <div class="col-md-9">
                                        <textarea class="form-control" name="query" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn" type="Submit" class="btn btn-info">Submit</button>
										<!--<button id="btn" type="submit" class="btn btn-info">Cancel</button>	-->
                                    </div>
                                </div>
                            </form>
                         </div>
                    </div> 
         </div> 
    </div>
    
	</div>
	<!--container ends-->
	
	
	<div class="container">
		<hr>
		<div class="row centered">
			<div class="col-lg-6 col-lg-offset-3">
				<form class="form-inline" role="form">
				  <div class="form-group">
				    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter your email address">
				  </div>
				  <button type="submit" class="btn btn-warning btn-lg">Invite Me!</button>
				</form>					
			</div>
			<div class="col-lg-3"></div>
		</div><!-- /row -->
		<hr>
		<p class="centered">Created by Hemant Katyal - 2014</p>
	</div><!-- /container -->
    	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">
      $('#datetimepicker1').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language:'en'
      });
      $('#datetimepicker2').datetimepicker({
          format: 'dd/MM/yyyy hh:mm:ss',
          language:'en'
        });
      $('#datetimepicker3').datetimepicker({
          format: 'dd/MM/yyyy hh:mm:ss',
          language:'en'
        });
      $('#datetimepicker4').datetimepicker({
          format: 'dd/MM/yyyy hh:mm:ss',
          language:'en'
        });
      $('#datetimepicker5').datetimepicker({
          format: 'dd/MM/yyyy hh:mm:ss',
          language:'en'
        });
      $('#datetimepicker6').datetimepicker({
          format: 'dd/MM/yyyy hh:mm:ss',
          language:'en'
        });

    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    function div_show() {
    	var val = document.getElementById("redirect").value;
		if( val == "true"){
    		$("#book_a_session").attr({
    		    "href" : "http://localhost:7080/Tomact_7/Login.jsp?profile=true&aId=<%=advisorId %>"
    		  });
    	}else{
			document.getElementById('signupbox').style.display = "block";
    	}
	}
    function div_hide() {
    	document.getElementById('signupbox').style.display ="none";
   	}
    $("#services_dropdown").change(function() { 
        var value = document.getElementById('services_dropdown').value;
        $("#modediv").show();
        $("#modes ul").html('');
        <c:forEach items="${modes}" var="mode">
        if("${mode.getService()}" == value){
       		$("#modes ul").append("<li style ='display: inline;float:left;margin-right:30px' class='modesstyle'><input type='radio' name='mode' onclick='getDuration()' id='optionsRadiosInlinem' value='${mode.getModeOfCommunication()}'/>${mode.getModeOfCommunication()}</li>");
        }
        </c:forEach>
        alert(value);
        if(value == "mockinterview" || value == "cvcritique"){
        	$("#cvdiv").show();
        }else{
        	$("#cvdiv").hide();
        }
        });
    function getDuration(){
    	$("#durationdiv").show();
    }
    function getPrice(){
    	var valuemode = $('input:radio[name=mode]:checked').val();
    	var valueduration = $('input:radio[name=duration]:checked').val();
    	<c:forEach items="${modes}" var="mode">
    		if("${mode.getModeOfCommunication()}" == valuemode){
    			alert(${mode.getPrice()});
    			var price = ${mode.getPrice()};
    		}
    	</c:forEach>
    	var totalPrice = price * valueduration;
    	$("#pricediv").show();
    	$("#disabledInput").val(totalPrice);
    }
    
    </script>
  </body>
</html>
