<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorEducationDTO"%>
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
	  		 
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
		<%
			List<AdvisorEducationDTO> education = (List<AdvisorEducationDTO>)request.getAttribute("education");
			Boolean edit = (Boolean)request.getAttribute("edit");
			pageContext.setAttribute("education", education);
			String action ="";
			String general="";
			String educations ="";
			String professional= "";
			String other = "";
			String service= "";
			if(edit != null && edit){
				action = "EducationInfoEdit";
				general ="GeneralInfoEdit";
				educations="EducationInfoEdit";
				professional="ProfessionalBackgroundEdit";
				other="OtherInfoEdit";
				service="ServicesEdit";
			}else{
				action = "AdvisorRegistrationEducationInfo";
				general ="AdvisorRegistrationGeneralInfo";
				educations="AdvisorRegistrationEducationInfo";
				professional="AdvisorRegistrationProfessionalBackground";
				other="AdvisorRegistrationOtherInfo";
				service="AdvisorRegistrationServices";
			}
	%>
</head>
<body>
				
    <div class="container">
    <%@include file="/Header.jsp" %>
	
    <div class="row">
    	<div id="signupbox1" class="mainbox col-md-8  col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title" style="font-size:26px">Advisor Registration</div>
            </div> 
         <div class="panel-body" >	                         
            <form id="signupform" class="form-horizontal" role="form" action="<%=action %>" method="post">
        
                <div id="part2">
                    
                    <ul class="tab-list">
                        <li><a href="<%=general %>?tab=true">General Information</a></li>
                        <li class="active"><a href="<%=educations %>?tab=true">Education Information</a></li>
                        <li><a href="<%=professional %>?tab=true">Professional Background</a></li>
                        <li><a href="<%=other %>?tab=true">Other Information</a></li>
                        <li><a href="<%=service %>?tab=true">Service Information</a></li>
                        <li>Upload Image</li>
                    </ul>
                        
                    <h4>Education</h4>
                    <hr>
                    <c:choose>
                        <c:when test="${education.size()>0}">
                        <input type="hidden" id="edit" name="edit" value="true">
                            <div id="education">
                                <c:forEach var="edu" items="${education}">
                                <c:if test="${edu.getType().equals('UG')}">
                                    <div class="form-group" id="dunder">
                                        <label for="icode" class="col-md-3 control-label">Undergraduate</label>
                                            <div class="col-md-5">
                                                 <input id="under" name="ug" class="form-control" maxlength='350' value="${edu.getEducation()}"></input>
                                            </div>
                                        <div class="col-md-2">
                                            <p class="required" id="required_name">Field Required</p>																		
                                        </div>
                                    </div>
                                </c:if>	
                                <c:if test="${edu.getType().equals('PG')}">		                                                                    			
                                     <div class="form-group" id="dpost">
                                        <label for="icode" class="col-md-3 control-label">Post Graduate</label>
                                            <div class="col-md-5">
                                               <input id="post" name="pg" class="form-control" maxlength='350' value="${edu.getEducation()}"></input>  
                                            </div>
                                    </div>
                                </c:if>
                                <c:if test="${edu.getType().equals('OTHERS')}">
                                     <div class="form-group" id="dother" >
                                        <label for="icode" class="col-md-3 control-label">Any other</label>
                                            <div class="col-md-5">
                                            <input id="other" name="others[]" class="form-control" maxlength='350' value="${edu.getEducation()}"></input>
                                            </div>
                                    </div>
                               </c:if>
                              </c:forEach>
                          </div>
                          <div class="row">
                          	<div class="col-md-3"></div>
                            <div class="col-md-9 form-group add-edu">
                            <a href="" id="add-education" class="">Add More Education</a>
                          </div>
                          </div>
                        </c:when>
                        <c:otherwise>
                            <div id="education">
                                <div class="form-group" id="dunder">
                                    <label for="icode" class="col-md-3 control-label">Undergraduate</label>
                                    <div class="col-md-5">
                                         <input id="under" name="ug" class="form-control" maxlength='350'></input>
                                    </div>
                                    <div class="col-md-2">
                                        <p class="required" id="required_name">Field Required</p>																		
                                    </div>
                                </div>
                                 <div class="form-group" id="dpost">
                                    <label for="icode" class="col-md-3 control-label">Post Graduate</label>
                                    <div class="col-md-5">
                                       <input id="post" name="pg" class="form-control" maxlength='350'></input>  
                                    </div>
                                </div>
                                 <div class="form-group" id="dother" >
                                    <label for="icode" class="col-md-3 control-label">Any other</label>
                                    <div class="col-md-5">
                                    <input id="other" name="others[]" class="form-control" maxlength='350'></input>
                                    </div>
                                </div>
                              </div>
                              <div class="row">
                          		<div class="col-md-3"></div>
                                <div class="col-md-9 add-edu">
                                	<a href="" id="add-education" class="">Add More Education</a>
                              	</div>
                          		</div>	
                        </c:otherwise>
                    </c:choose> 
                    <div class="form-group">
                        <!-- Button -->                                        
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn-signup" type="submit" style="float: right" class="btn btn-info">Submit and Continue</button>
                        </div>
                    </div>
                 </div><!--part2-->	
         </form>
       </div>
     </div>
    </div>
    </div>
    
    <%@include file="/Footer.jsp" %>
	</div>
    
</body>
<script src="assets/js/jquery-1.11.0.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

		<script>
		$(document).ready(function() {
		<!--under can't be blank-->
			$("#btn-signup").click(function(event){
				var input_city = $("#under").val();
				if (input_city==''){
					$("#required_name").show();
					event.preventDefault(); 
				}else{
					$("#required_name").hide();
				}	
			});
			$("#btn-signup").click(function(event){
				for(i=1;i<=10;i++){
					if(!$("#other"+i).is(':visible')){
						$("#other"+i).val("");
					}
				}
			});
			
		});	
		var i =1;
		$(document).ready(function(){
			var max_fields = 10;
			  $("#add-education").click(function(e){
				  if( 1<=max_fields){
				  e.preventDefault();
					  var neweducation = "<div class='form-group' id='dother"+i+"'><label for='icode' class='col-md-3 control-label'>Any other</label><div class='col-md-9'><div class='row'><div class='col-md-6 col-xs-10'><input id='other"+i+"' name='others[]' class='form-control' maxlength='350'></input></div><div class='col-md-2 col-xs-2' style='padding-left:0; padding-top:4px;'><a id='"+i+"' onclick=closediv(this)><img src='assets/img/close.png'></a></div></div></div></div>";
					  $("#education").append(neweducation);
					  i++;
				  }
				 });
		});
		
		function closediv(elem){
			var id = elem.id;
			$("#dother"+id).hide();
		}
		</script>
</html>