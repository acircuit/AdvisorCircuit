<!DOCTYPE html>
<%@page import="org.AC.Util.GetTimeLeftForReply"%>
<%@page import="org.AC.dto.ProfessionalBackgroundDTO"%>
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

    <title>Advisor Registration</title>
  		 
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	
			<%
		        List<ProfessionalBackgroundDTO> professionalBackground = (List<ProfessionalBackgroundDTO>)request.getAttribute("list");
				String exp = (String)request.getAttribute("experience");
				Boolean edit = (Boolean)request.getAttribute("edit");
				pageContext.setAttribute("professionalBackground", professionalBackground);
				pageContext.setAttribute("experience", exp);
				String action ="";
				String general="";
				String educations ="";
				String professional= "";
				String other = "";
				String service= "";
				String image = "";
				if(edit != null &&  edit){
					action = "ProfessionalBackgroundEdit";
					general ="GeneralInfoEdit";
					educations="EducationInfoEdit";
					professional="ProfessionalBackgroundEdit";
					other="OtherInfoEdit";
					service="ServicesEdit";
					image = "ImageEdit";

				}else{
					action = "AdvisorRegistrationProfessionalBackground";
					general ="AdvisorRegistrationGeneralInfo";
					educations="AdvisorRegistrationEducationInfo";
					professional="AdvisorRegistrationProfessionalBackground";
					other="AdvisorRegistrationOtherInfo";
					service="AdvisorRegistrationServices";
					image = "AdvisorRegistrationImage";
				}
	%>
</head>
<body>
        <div class="container">
        <%@include file="/Header.jsp" %>
		
        <div class="row">
        
         	<div id="signupbox1" class="mainbox col-md-8 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                
                <div class="panel-heading">
                    <div class="panel-title" style="font-size:26px">Advisor Registration</div>
                </div> 
             	
                <div class="panel-body">	                         
                <form id="signupform" class="form-horizontal" role="form" action="<%=action %>" method="post">
            
                <div id="part4">	
                    <ul class="tab-list">
                        <li><a href="<%=general %>?tab=true">General Information</a></li>
                        <li><a href="<%=educations %>?tab=true">Education Information</a></li>
                        <li class="active"><a href="<%=professional %>?tab=true">Professional Background</a></li>
                        <li><a href="<%=other %>?tab=true">Other Information</a></li>
                        <li><a href="<%=service %>?tab=true">Service Information</a></li>
                        <li><a href="<%=image%>">Upload Image</a></li>
                    </ul>
                        <c:set value="false" var="isAdd"></c:set>
                        <c:choose>
                            <c:when test="${professionalBackground.size() > 0 }">
                            <input type="hidden" id="edit" name="edit" value="true">
                                <h4>Professional Background</h4>
                                <hr>
                                <div id ="ProfessionalBackground0">
                                <div class="form-group" id="dexperience">
                                    <label for="icode" class="col-md-3 control-label">Experience In  Relevant Industry (Please Enter digits only. Eg: 10)</label>
                                    <div class="col-md-5">
                                        <input id="experience" type="text"  class="form-control" name="experience" placeholder="" value="${experience}" maxlength="20">
                                        <p class="required" id="required_experience">Field Required</p>																			                                        
                                        <p class="required" id="invalid_experience">Invalid Experience</p>	 
                                    </div>	
                                </div>	
                                    <c:set value="0" var="counter"></c:set>											
                                    <c:forEach var="prof" items="${professionalBackground}">
                                        <c:if test="${prof.getCompany() != null}">
                                        	<div id ="professional${counter }">
                                                <c:if test="${counter >0 }">
                                                    <i class="glyphicon glyphicon-remove" id="${counter}" onclick="div_hide(this)" style="float: right"></i>
                                                </c:if>
                                                <div class="form-group" id="dcompany${counter}">
                                                <label for="icode" class="col-md-3 control-label">Organisation</label>
                                                <div class="col-md-5">
                                                    <input id="company${counter}" type="text"  class="form-control" name="company[]" placeholder="" value="${prof.getCompany()}" maxlength='150'>
                                                    <p class="required" id="required_company${counter}">Field Required</p>
                                                </div>
                                                </div>
                                                <div class="form-group" id="dduration${counter}">
                                                    <label for="icode"  class="col-md-3 control-label">Duration</label>
                                                    <div class="col-md-5">
                                                        <input id="duration${counter}" type="text" class="form-control" name="duration[]" placeholder="" value="${prof.getDuration()}" maxlength='150'>
                                                        <p class="required" id="required_duration${counter}">Field Required</p>																			                                        	                                        
                                                    </div>
                                                    <div class="col-md-2">	
                                                        <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Example: 2009- current" data-trigger="focus">
                                                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>   
                                                    </div>
                                                </div>
                                                <div class="form-group" id="ddesignation${counter}">
                                                    <label for="icode" class="col-md-3 control-label">Designation</label>
                                                    <div class="col-md-5">
                                                        <input id="designation${counter}" type="text" class="form-control" name="designation[]" placeholder="" value="${prof.getDesignation()}" maxlength='150'>
                                                        <p class="required" id="required_designation${counter}">Field Required</p>																			                                        	                                        
                                                    </div>
                                                    <div class="col-md-2">	
                                                        <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Example: Vice President" data-trigger="focus">
                                                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>   
                                                    </div>
                                                </div>
                                                <c:set value="0" var="desccounter"></c:set>	
                                                 <c:forEach var="desc" items="${prof.getDescriptionArray()}">
                                                    <c:if test="${!desc.isEmpty() && !desc.equals('') && desc.length()> 0}">
                                                         <div class="form-group" id="ddescription${counter}${desccounter}">
                                                            <label for="icode" class="col-md-3 control-label">Description(Explain your work in bullet points)</label>
                                                            <div class="col-md-5 col-xs-8">
                                                                <input id="description${counter}${desccounter}" class="form-control" name="description${counter}[]" value="${desc}" maxlength="350">
                                                                <p class="required" id="required_description${counter}">Field Required</p>																			                                        	                                        
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<c:choose>
                                                            		<c:when test="${desccounter > 0 }">
                                                            			<a id="hide${counter}${desccounter}" onclick="closedesc(this)"><img src="assets/img/close.png"></a>
                                                            		</c:when>
                                                            		<c:otherwise>
                                                            			 <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-trigger="focus" data-content="Example: Manage and develop newspaper, magazine and internet advertising for existing and new accounts while building and cultivating client relations by continually updating my knowledge of the ever-changing media and market.Assist with the clients advertising strategy, ad message and creative development.Consistently grew business and established strong relationships with clients.">
                                                               			 <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>  
                                                            		</c:otherwise>
                                                            	</c:choose>
                                                               
                                                            </div>
                                                        </div>
                                                        <c:set value="${desccounter +1 }" var="desccounter"></c:set>					                                	
                                                    </c:if>
                                        </c:forEach>					                                	
                                         <c:if test="${desccounter ==0 }">
                                         		<div class="form-group" id="ddescription${counter}${desccounter}">
                                                            <label for="icode" class="col-md-3 control-label">Description(Explain your work in bullet points)</label>
                                                            <div class="col-md-5 col-xs-8">
                                                                <input id="description${counter}${desccounter}" class="form-control" name="description${counter}[]" value="${desc}" maxlength="350">
                                                                <p class="required" id="required_description${counter}">Field Required</p>																			                                        	                                        
                                                            </div>
                                                            <div class="col-md-2">
                                                            <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-trigger="focus" data-content="Example: Manage and develop newspaper, magazine and internet advertising for existing and new accounts while building and cultivating client relations by continually updating my knowledge of the ever-changing media and market.Assist with the clients advertising strategy, ad message and creative development.Consistently grew business and established strong relationships with clients.">
                                                               			 <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                            </div>
                                                </div>
                                                <c:set value="${desccounter +1 }" var="desccounter"></c:set>					                                	
                                         </c:if>           
                                        <div id="adddescription${counter}">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="icode" class="col-md-3 control-label"></label>
                                            <div class="col-md-5">
                                                <a href="" id="descript${counter}" class="add-description" onclick="AddDescription(event,this)" style="float: right;margin-right: 5%" >+ Add Point</a> 
                                            </div>
                                        </div>
                                        <hr>
                                        
                                        <c:set value="${counter +1 }" var="counter"></c:set>
                                        </div>					                                	
                                        </c:if>	
                                    </c:forEach>
                                    <c:set value="true" var="isAdd"></c:set>
                                    
                                </div>
                            </c:when>
                            <c:otherwise>
                            
                        <h4>Professional Background</h4>
                        <hr>
                        
                        <div id="ProfessionalBackground0">
                            <div class="form-group" id="dexperience">
                            <label for="icode" class="col-md-3 control-label">Experience In  Relevant Industry (Please Enter digits only. Eg: 10)</label>
                            <div class="col-md-5">
                                <input id="experience" type="text"  class="form-control" name="experience" placeholder="" maxlength=20>
                                <p class="required" id="required_experience">Field Required</p>																			                                        
                                <p class="required" id="invalid_experience">Invalid Experience</p>	 
                            </div>	
                            </div>
                                								
                            <div class="form-group" id="dcompany0">
                            <label for="icode" class="col-md-3 control-label">Organisation</label>
                            <div class="col-md-5">
                                <input id="company0" type="text"  class="form-control" name="company[]" placeholder="" maxlength='150'>
                                <p class="required" id="required_company0">Field Required</p>																			                                        
                            </div>	
                            </div>
                                
                            <div class="form-group" id="dduration0">
                                <label for="icode"  class="col-md-3 control-label">Duration</label>
                                <div class="col-md-5">
                                    <input id="duration0" type="text" class="form-control" name="duration[]" placeholder="" maxlength='150'>
                                    <p class="required" id="required_duration0">Field Required</p>																			                                        	                                        
                                </div>
                                <div class="col-md-2">	
                                    <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Example: 2009- current" data-trigger="focus">
                                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>   
                                </div>
                            </div>
                            
                            <div class="form-group" id="ddesignation0">
                                <label for="icode" class="col-md-3 control-label">Designation</label>
                                <div class="col-md-5">
                                    <input id="designation0" type="text" class="form-control" name="designation[]" placeholder="" maxlength='150'>
                                    <p class="required" id="required_designation0">Field Required</p>																			                                        	                                        
                                </div>
                                <div class="col-md-2">	
                                    <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Example: Vice President" data-trigger="focus">
                                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>   
                                </div>
                            </div>
                            
                            <div class="form-group" id="ddescription00">
                                <label for="icode" class="col-md-3 control-label">Description(Explain your work in bullet points)</label>
                                <div class="col-md-5">
                                    <input id="description0" class="form-control" name="description0[]" maxlength="350">
                                    <p class="required" id="required_description0">Field Required</p>																			                                        	                                        
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-trigger="focus" data-content="Example: Manage and develop newspaper, magazine and internet advertising for existing and new accounts while building and cultivating client relations by continually updating my knowledge of the ever-changing media and market.Assist with the clients advertising strategy, ad message and creative development.Consistently grew business and established strong relationships with clients.">
                                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>  
                                </div>
                            </div>
                            
                            <div id="adddescription0">
                            </div>
                            
                            <div class="form-group">
                                <label for="icode" class="col-md-3 control-label"></label>
                                <div class="col-md-5">
                                    <a href="" id="descript0" class="add-description" onclick="AddDescription(event,this)" style="float: right;margin-right: 5%">+ Add Point</a> 
                                </div>
                            </div>
                        </div><!--part4-->
                        
                        <div class="col-md-12">
                            <a href="" id="add-profession" class="add-profession">Add More Professional Background</a>
                           </div>
                        <hr>
                        
                        </c:otherwise>
                        </c:choose>
                        <c:if test="${isAdd == true}">
                        
                        <div class="col-md-12">
                                    <a href="" id="${counter}" class="addprof" onclick="AddProf(event,this)">Add More Professional Background</a>
                               </div>
                        <hr>
                        
                        </c:if>
                        
                        <div class="form-group">
                                <!-- Button -->                                        
                                <div class="col-md-offset-3 col-md-9">
                                    <c:choose>
                                     <c:when test="${edit != null && edit }">
                                        <button id="btn-signup" type="submit" style="float: right" class="btn btn-info">Save Changes</button>                                            		
                                     </c:when>
                                     <c:otherwise>
                                		<button id="btn-signup" type="submit" style="float: right" class="btn btn-info">Save and Continue</button>                                     
                                     </c:otherwise>
                                 </c:choose>
                                </div>
                            </div>	 
                 </div>              
                 </form>
           		</div>
                
         	</div>
            </div>
            
        </div><!-- end row-->
        
        <%@include file="/Footer.jsp" %>
        </div>
                 
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    	    <script src="assets/js/jquery-1.11.0.js"></script>

    <script src="assets/js/bootstrap.min.js"></script>
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
   <script>
		$(document).ready(function(){
			
			$("#btn-signup").click(function(event){
				var input_a = $("#experience").val();
				var filter = /^[0-9-+ ]+$/;
				var is_exp = filter.test(input_a);
				if($("#experience").val() ==""){
					$("#required_experience").show();
					$("#invalid_experience").hide();
					event.preventDefault();
				}else if(!is_exp){
					$("#invalid_experience").show();
					$("#required_experience").hide();
					event.preventDefault();
				}else{
					$("#required_experience").hide();
					$("#invalid_experience").hide();
				}
				var k=0;
				  while(k <= 10){
					  if(!$("#company"+k).is(':visible')){
						  $('#company'+k).val("");
					  }
					  k++;
				  }
				var j=0;
				while(j<=10){
					if ($("#company"+j).val() =='' && $("#company"+j).is(':visible')){
						$("#required_company"+j).show();
						event.preventDefault(); 
					}	
					else{
						$("#required_company"+j).hide();
					}
					j++;
				}
				var i=0;
				while(i <= 10) {
					  i++;
					if ($('.com'+i).val()=='' && $('.com'+i).is(':visible')){
						$('.coms'+i).show();
						event.preventDefault(); 
					}	
					else{
						$('.coms'+i).hide();
						if(!$('.com'+i).is(':visible')){
							$('.com'+i).val("");
						}
					}
				}
				
			});
			$("#btn-signup").click(function(event){
				var k=0;
				  while(k <= 10){
					  if(!$("#duration"+k).is(':visible')){
						  $('#duration'+k).val("");
					  }
					  k++;
				  }
				var j=0;
				while(j<=10){
					if ($("#duration"+j).val()=='' && $("#duration"+j).is(':visible')){
						$("#required_duration"+j).show();
						event.preventDefault(); 
					}	
					else{
						$("#required_duration"+j).hide();
					}
					j++;
				}
				var i=0;
				while(i <= 10) {
					  i++;
					if ($('.dur'+i).val()=='' && $('.dur'+i).is(':visible')){
						$('.durs'+i).show();
						event.preventDefault(); 
					}	
					else{
						$('.durs'+i).hide();
						if(!$('.dur'+i).is(':visible')){
							$('.dur'+i).val("");
						}
					}
				}
				
			});
			$("#btn-signup").click(function(event){
				var k=0;
				  while(k <= 10){
					  if(!$("#designation"+k).is(':visible')){
						  $('#designation'+k).val("");
					  }
					  k++;
				  }
				var j=0;
				while(j<=10){
					if ($("#designation"+j).val() =='' && $("#designation"+j).is(':visible')){
						$("#required_designation"+j).show();
						event.preventDefault(); 
					}	
					else{
						$("#required_designation"+j).hide();
					}
					j++;
				}
				var i=0;
				while(i <= 10) {
					  i++;
					if ($('.desig'+i).val()=='' && $('.desig'+i).is(':visible')){
						$('.desigs'+i).show();
						event.preventDefault(); 
					}	
					else{
						$('.desigs'+i).hide();
					}
				}

			});
			$("#btn-signup").click(function(event){
			/*	var input_city = $("#description").val();
				var j=0;
				while(j<=10){
					if ($("#description"+j).val() =='' && $("#description"+j).is(':visible')){
						$("#required_description"+j).show();
						event.preventDefault(); 
					}	
					else{
						$("#required_description"+j).hide();
					}
					j++;
				}
				var z=0;
				while(z <= 10) {
					  z++;
					var input_city1 = $('.desc'+z).val();
					if (input_city1=='' && $('.desc'+z).is(':visible')){
						$('.descs'+z).show();
						event.preventDefault(); 
					}	
					else{
						$('.descs'+z).hide();
						if(!$('.desc'+i).is(':visible')){
							$('.desc'+i).val("");
						}
					}
				}*/
				var i=0;
				while(i <= 10) {
						var k=0;
					  while(k <= 10){
						/*var input_city1 = $('#description'+i+k).val();
						if (input_city1=='' && $('#description'+i+k).is(':visible')){
							$('.de'+i+k).show();
							event.preventDefault(); 
						}	*/
						/*else{*/
							$('.de'+i+k).hide();
							if(!$('#description'+i+k).is(':visible')){
								$('#description'+i+k).val("");
							}
							/*}*/	
						k++;
					}
					  i++;
				}
			});
			
			var max_fields = 9;
			var i =0;
			  $("a.add-profession").click(function(e){
				 // $("#ProfessionalBackground1").toggle();
				  e.preventDefault();
				  if(i <= max_fields) {
					  i++;
					  var newprofessionalbackground = "<div id ='professional"+i+"'><i class='glyphicon glyphicon-remove' id='"+i+"' onclick='div_hide(this)' style='float: right'></i><div class='form-group'><label for='icode' class='col-md-3 control-label'>Organisation</label><div class='col-md-5'><input type='text' id='company"+i+"' class='form-control com"+i+"' name='company[]' placeholder='' maxlength='150'><p class='required coms"+i+"' id='required_company'>Field Required</p></div></div><div class='form-group'><label for='icode' class='col-md-3 control-label'>Duration</label><div class='col-md-5'><input type='text' id='duration"+i+"' class='form-control dur"+i+"' name='duration[]' placeholder='' maxlength='150'><p class='required durs"+i+"' id='required_duration'>Field Required</p></div></div><div class='form-group'><label for='icode' class='col-md-3 control-label'>Designation</label><div class='col-md-5'><input type='text' id='designation"+i+"' class='form-control desig"+i+"' name='designation[]' placeholder='' maxlength='150'><p class='required desigs"+i+"' id='required_designation'>Field Required</p></div></div><div class='form-group'><label for='icode' class='col-md-3 control-label'>Description</label><div class='col-md-5'><input class='form-control desc"+i+"' id ='description"+i+"' name='description"+i+"[]' maxlength='350'><p class='required descs"+i+"' id='required_description'>Field Required</p></div></div><div  id='adddescription"+i+"'></div ><div class='form-group'><label for='icode' class='col-md-3 control-label'></label><div class='col-md-5'><a href='' id='descript"+i+"' class='add-description' onclick='AddDescription(event,this)' style='float: right;margin-right: 5%'>+ Add Point</a></div></div></div>";
					  $("#ProfessionalBackground0").append(newprofessionalbackground);
				  }
			  });
		});
		function AddProf(event,elem){
			event.preventDefault();
			var ids = elem.id;
			if(ids <=9){
				 var newprofessionalbackground = "<div id ='professional"+ids+"'><i class='glyphicon glyphicon-remove' id='"+ids+"' onclick='div_hide(this)' style='float: right'></i><div class='form-group'><label for='icode' class='col-md-3 control-label'>Organisation</label><div class='col-md-5'><input type='text' id='company"+ids+"' class='form-control com"+ids+"' name='company[]' placeholder='' maxlength='150'><p class='required coms"+ids+"' id='required_company'>Field Required</p></div></div><div class='form-group'><label for='icode' class='col-md-3 control-label'>Duration</label><div class='col-md-5'><input type='text' id='duration"+ids+"' class='form-control dur"+ids+"' name='duration[]' placeholder='' maxlength='150'><p class='required durs"+ids+"' id='required_duration'>Field Required</p></div></div><div class='form-group'><label for='icode' class='col-md-3 control-label'>Designation</label><div class='col-md-5'><input type='text' id='designation"+ids+"' class='form-control desig"+ids+"' name='designation[]' placeholder='' maxlength='150'><p class='required desigs"+ids+"' id='required_designation'>Field Required</p></div></div><div class='form-group'><label for='icode' class='col-md-3 control-label'>Description</label><div class='col-md-5'><input class='form-control desc"+ids+"' id ='description"+ids+"' name='description"+ids+"[]' maxlength='350'><p class='required descs"+ids+"' id='required_description'>Field Required</p></div></div><div  id='adddescription"+ids+"'></div ><div class='form-group'><label for='icode' class='col-md-3 control-label'></label><div class='col-md-5'><a href='' id='descript"+ids+"' class='add-description' onclick='AddDescription(event,this)' style='float: right;margin-right: 5%'>+ Add Point</a></div></div></div>";
				  $("#ProfessionalBackground0").append(newprofessionalbackground);
				  ids++;
				  elem.id = ids;
			}
			
		}
		 function div_hide(elem) {
			 var id = elem.id;
			 document.getElementById("professional"+id).style.display = "none";
		}
		 var a=0;
		 var b=0;
		 var c=0;
		 var d=0;
		 var e=0;
		 var f=0;
		 var g=0;
		 var h=0;
		 var k=0;
		 var y=0;
		 var z=0;
		function AddDescription(event,elem){
			var id = elem.id;
			var l;
			var no = id.split("descript");
			var max_description = 10;
			event.preventDefault();
			if(no[1] == 0){
				l=a;
			}else if (no[1] == 1) {
				l=b
			}else if (no[1] == 2) {
				l=c
			}else if (no[1] == 3) {
				l=d
			}else if (no[1] == 4) {
				l=e
			}else if (no[1] == 5) {
				l=f
			}else if (no[1] == 6) {
				l=g
			}else if (no[1] == 7) {
				l=h
			}else if (no[1] == 8) {
				l=k
			}else if (no[1] == 9) {
				l=y
			}else if (no[1] == 10) {
				l=z
			}
			if(l <= max_description) {
				  $("#adddescription"+no[1]).append("<div id='close"+no[1]+l+"'  class='form-group'><label for='icode' class='col-md-3 control-label'>Description</label><div class='col-md-9'><div class='row'><div class='col-md-6 col-xs-10' style='padding-right:10px;'><input id='description"+no[1]+l+"' class='form-control' name='description"+no[1]+"[]' maxlength='350'><p class='required de"+no[1]+l+"' id='required_description'>Field Required</p></div><div class='col-md-6 col-xs-2' style='padding-left:0; padding-top:4px;'><a id='hide"+no[1]+l+"' onclick=closediv(this)><i class='glyphicon glyphicon-remove'></i></div></div></div></div>");	  
				  if(no[1] == 0){
						a++;
					}else if (no[1] == 1) {
						b++;
					}else if (no[1] == 2) {
						c++;
					}else if (no[1] == 3) {
						d++;
					}else if (no[1] == 4) {
						e++;
					}else if (no[1] == 5) {
						f++;
					}else if (no[1] == 6) {
						g++;
					}else if (no[1] == 7) {
						h++;
					}else if (no[1] == 8) {
						k++;
					}else if (no[1] == 9) {
						y++;
					}else if (no[1] == 10) {
						z++;
					}
			}
		}
		function closediv(elem){
			var id =elem.id;
			var element = id.split("hide");
			$('#close'+element[1]).hide();
		}
		function closedesc(elem){
			var id =elem.id;
			var element = id.split("hide");
			$('#ddescription'+element[1]).hide();
		}
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