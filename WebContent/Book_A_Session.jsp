<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
	<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
   

    <title>Book Session</title>
	<style type="text/css"> 
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		input[type="text"]{height:40px !important}
		.add-on{height:40px !important;cursor:pointer !important}
		.control-label{width:130px !important}
		@media only screen and (max-width:700px){
		.font_size{font-size:22px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:22px !important;}
		}
		 </style>  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
    
  
  </head>

  <body>
	<%@include file="/Header.jsp" %>

	<div style="height:40px"></div>
	
	    <div class="container">    
        
        <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Book Session</div>
                            
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <div class="form-group">
                                            <label for="icode" class="col-md-3 control-label">Services</label>
                                            <div class="col-md-9">
											<select class="form-control">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                            </select>
											</div>
                                </div>
                                  
                                <div class="form-group">
                                            <label for="icode" class="col-md-3 control-label">Mode</label>
										<div class="col-md-9">	
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInlinem" id="optionsRadiosInlinem1" value="optionm1" checked>1
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInlinem" id="optionsRadiosInlinem2" value="optionm2">2
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInlinem" id="optionsRadiosInlinem3" value="optionm3">3
                                            </label>
                                        </div>
								</div>		
								
								<div class="form-group">
                                    <label for="icode disabledSelect" class="col-md-3 control-label">Price</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" id="disabledInput" placeholder="" disabled="">
                                    </div>
                                </div>		
								  
                                <div class="form-group">
                                            <label for="icode" class="col-md-3 control-label">Duration</label>
                                            <div class="col-md-9">
											<label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="option1" checked>Half Hour
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="option2">1 hour
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline3" value="option3">1.5 Hour
                                            </label>
										</div>	
                                </div>
								
								
								<div style="height:10px"></div>
								<h3>Choose Date and Time</h3>
								<hr>
							
								 <div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 1</label>
										<div id="datetimepicker1" class="input-append date">
										  <input type="text"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 2</label>
										<div id="datetimepicker2" class="input-append date">
										  <input type="text"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 3</label>
										<div id="datetimepicker3" class="input-append date">
										  <input type="text"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 4</label>
										<div id="datetimepicker4" class="input-append date">
										  <input type="text"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 5</label>
										<div id="datetimepicker5" class="input-append date">
										  <input type="text"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<div class="form-group">
										<label for="icode" class="col-md-3 control-label">Slot 6</label>
										<div id="datetimepicker6" class="input-append date">
										  <input type="text"></input>
										  <span class="add-on">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" style="margin-top:7px"></i>
										  </span>
										</div>
								</div>
								<hr>
								
								<div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Enter Your Query :</label>
                                    <div class="col-md-9">
                                        <textarea class="form-control" rows="3"></textarea>
                                    </div>
                                </div>
								
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn" type="button" class="btn btn-info">Submit</button>
										<!--<button id="btn" type="button" class="btn btn-info">Cancel</button>	-->
                                    </div>
                                </div>
                            </form>
                         </div>
                    </div> 
         </div> 
    </div>
    
	
	<div class="container">
		<hr>
		<p class="centered">Created by Hemant Katyal - 2014</p>
	</div><!-- /container -->
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery-1.11.0.js"></script>
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
