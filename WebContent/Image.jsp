<!DOCTYPE html>
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

    <title>Advisor Registration</title>
	<style type="text/css"> 
		.img-responsive{width:28px !important} 
		.bdesign{font-size:20px !important;}
		.dropreg{height:30px;width:40%;}
		.font_size{font-size:16px !important;}
		@media only screen and (max-width:700px){
		.font_size{font-size:12px !important;}
		}
		@media only screen and (min-width: 320px) and (max-width: 470px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:12px !important;}
		}
		
		@media only screen and (min-width: 470px) and (max-width: 700px) {
		.bdesign{font-size:14px !important;}
		.font_size{font-size:12px !important;}
		}
		 </style>  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
</head>
<body>
	
	    <%@include file="/Header.jsp" %>
	    		<div id="signupbox1" style=" margin-top:50px" class="mainbox col-md-8 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size:26px">Advisor Registration</div>
                        </div>  
                     <div class="panel-body" >	                         
	    	   			<form id="signupform" class="form-horizontal" role="form" enctype="multipart/form-data" action="AdvisorRegistrationImageController" method="post">
	    			
							<div id="part2">
								<h4>Picture</h4>
                                <hr>
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Upload Picture</label>
                                    <div id = "fileupload" class="col-md-9">
										<input type="file" id="uploadFile" onchange="checkFile()" name="file" accept="image/png,image/jpg,image/gif,image/jpeg" required="required">
										<p class="help-block">Max Size 3MB</p>
                                    </div>
                                </div>
								<hr>
								 <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info">Submit</button>
                                    </div>
                                </div>
							 </div><!--part2-->	
                     </form>
                   </div>
                 </div>
                 </div>

</body>
<script type="text/javascript">
function checkFile() {
    var fileElement = document.getElementById("uploadFile");
    var fileExtension = "";
    if (fileElement.value.lastIndexOf(".") > 0) {
        fileExtension = fileElement.value.substring(fileElement.value.lastIndexOf(".") + 1, fileElement.value.length);
    }
    if (fileExtension == "gif" || fileExtension == "png" || fileExtension == "jpg" || fileExtension == "jpeg") {
        return true;
    }
    else {
        alert("You must select a GIF,PNG,JPG,JPEG file for upload");
        document.getElementById("fileupload").innerHTML = "<input type='file' id='uploadFile' onchange='checkFile()' name='file' accept='image/png,image/jpg,image/gif,image/jpeg' required='required'><p class='help-block'>Max Size 3MB</p>";
        return false;
    }
}
</script>
</html>