<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Advisor PDF</title>
	<style type="text/css"> 
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
</head>
<body>
		`<div id="PDF" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
              <div class="panel panel-info">
  						
                   <div class="panel-body" >
                       <form id="forgot_password_form" class="form-horizontal" role="form" action="PDFController" method="post">
                           
                            <div class="panel-heading">
                         			<div class="panel-title" style="font-size:26px">PDF
                        		 		<img alt="" id="close" onclick="div_hide()" src="assets/img/close.png" style="float: right; ">
                        			</div>
                				</div>
                					<hr>
                           <div class="form-group">
                               <label for="icode" class="col-md-3 control-label">Email</label>
                                <div class="col-md-9">
					 				<input type="email" name="email" class="form-control" placeholder="Enter the email ID">
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