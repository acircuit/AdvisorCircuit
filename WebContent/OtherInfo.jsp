<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorProfileDTO"%>
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
			List<AdvisorProfileDTO> achievements = (List<AdvisorProfileDTO>)request.getAttribute("achievements");
			List<AdvisorProfileDTO> keySkill = (List<AdvisorProfileDTO>)request.getAttribute("keySkill");
			String knowYourAdvisor = (String)request.getAttribute("know");
			Boolean edit = (Boolean)request.getAttribute("edit");
			pageContext.setAttribute("achievements", achievements);
			pageContext.setAttribute("keySkill", keySkill);
			pageContext.setAttribute("knowYourAdvisor", knowYourAdvisor);
			String action ="";
			String general="";
			String education ="";
			String professional= "";
			String other = "";
			String service= "";
			String method = "";
			String image = "";
			if(edit != null &&  edit){
				action = "OtherInfoEdit";
				general ="GeneralInfoEdit";
				education="EducationInfoEdit";
				professional="ProfessionalBackgroundEdit";
				other="OtherInfoEdit";
				service="ServicesEdit";
				image = "ImageEdit";
			}else{
				action = "AdvisorRegistrationOtherInfo";
				general ="AdvisorRegistrationGeneralInfo";
				education="AdvisorRegistrationEducationInfo";
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
                     <div class="panel-body" >	                         
	    	   			<form id="signupform" class="form-horizontal" role="form" action="<%=action %>" method="post">
	    			
								<!--part3-->
								<div id="part3">
                                <ul class="tab-list">
                                    <li><a href="<%=general %>?tab=true">General Information</a></li>
                                    <li><a href="<%=education %>?tab=true">Education Information</a></li>
                                    <li><a href="<%=professional %>?tab=true">Professional Background</a></li>
                                    <li class="active"><a href="<%=other %>?tab=true">Other Information</a></li>
                                    <li><a href="<%=service %>?tab=true">Service Information</a></li>
                                    <li><a href="<%=image%>">Upload Image</a></li>
                                </ul>
								Awards And Acheivement
                                <hr>
                                	<c:choose>
                                		<c:when test="${keySkill.size() >0 }">
                                		
                                		<c:choose>
                                			<c:when test="${achievements.size()>0}">
                                			<c:set var="counter" value="0"></c:set>
                                				<c:forEach var="achievement" items="${achievements}">
	                                				<div class="form-group" id="daward${counter }">
														<label for="icode" class="col-md-3 control-label">&#8226; </label>
					                                    <div class="col-md-5">
					                                        <input id="award${counter}" class="form-control" name="achievement[]" value="${achievement.getAchievements() }" maxlength='350' >
															<p class="required" id="required_award${counter}">Field Required</p>																			                                        
														</div>
														<div class="col-md-2">	
															<c:choose>
	                                                     		<c:when test="${counter > 0 }">
	                                                     			<a id="hide${counter}" onclick="closeaward(this)"><i class="glyphicon glyphicon-remove"></i></a>
	                                                     		</c:when>
	                                                     		<c:otherwise>
	                                                     			  <button type="button" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-trigger="focus" data-placement="right"  data-content="Example: Award for <b>Excellent Salesmanship</b> in 2010. <br>Part of teams which got nominated and won several prestigious awards in advertising">
														   		    	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                                                     		</c:otherwise>
                                                   			 </c:choose>
					                                         
				                               			</div>
			                                		</div>
			                                		<c:set var="counter" value="${counter+1}"></c:set>
		                                		</c:forEach>
		                                		<div  id="awards0">
			                                	</div >
			                                	<a href="" onclick="AddAchievements(event)">Add More Achievements</a> 
			                                	<hr>
                                			</c:when>
                                			<c:otherwise>
                                					<div class="form-group" id="daward">
														<label for="icode" class="col-md-3 control-label">&#8226; </label>
					                                    <div class="col-md-5">
					                                        <input id="award0" class="form-control" name="achievement[]" maxlength='350'>
															<p class="required" id="required_award">Field Required</p>																			                                        
														</div>
														<div class="col-md-2">	
					                                         <button type="button" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-trigger="focus" data-placement="right"  data-content="Example: Award for <b>Excellent Salesmanship</b> in 2010. <br>Part of teams which got nominated and won several prestigious awards in advertising">
														   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
				                               			</div>
			                                		</div>
			                                		<div  id="awards0">
				                                	</div >
				                                	<a href="" onclick="AddAchievements(event)">Add More Achievements</a> 
                                			</c:otherwise>
                                		</c:choose>
                                		I can help you with
                                		<c:set  var="skillcounter"  value="0"></c:set>
                                		<c:forEach var="skill" items="${keySkill}">
                                			<div class="form-group" id="dskill${skillcounter}">
												<label for="icode" class="col-md-3 control-label">&#8226; </label>
			                                    <div class="col-md-5">
			                                        <input id="skill${skillcounter}" class="form-control" name="keyskills[]" value="${skill.getKeyskill()}" maxlength='350'>  
													<p class="required" id="required_skill">Field Required</p>																			                                        
												</div>
												<div class="col-md-2">
													<c:choose>
                                                     		<c:when test="${skillcounter > 0 }">
                                                     			<a id="hide${skillcounter}" onclick="closeskill(this)"><i class="glyphicon glyphicon-remove"></i></a>
                                                     		</c:when>
                                                     		<c:otherwise>
                                                     			  <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right" data-content="Example: Specialise in market research and understanding consumer behaviour.<br>Extensive knowledge about the field of advertising.<br>How to prepare for a career in digital marketing.<br> How to work with multi-cultural teams. ">
											   				  	  <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>  
                                                     		</c:otherwise>
                                                    </c:choose>
			                                    </div>
		                                	</div>
		                                	<c:set var="skillcounter" value="${skillcounter+1}"></c:set>
                                		</c:forEach>
                                		<div  id="skills0">
	                                	</div >
	                                	<a href="" onclick="AddSkills(event)">Add More Skills</a> 
	                                	<hr>
	                                	<div class="form-group" id="dhobby">
											<label for="icode" class="col-md-3 control-label">Know Your Advisor Better(HOBBIES & INTERESTS)</label>
		                                    <div class="col-md-5">
		                                        <c:choose>
													<c:when test="<%=knowYourAdvisor != null %>">
					                                        <textarea id="hobby" class="form-control" name="hobbies" rows="3" maxlength="1200"><%=knowYourAdvisor %></textarea>
													</c:when>
	                               					<c:otherwise>
															<textarea id="hobby" class="form-control" name="hobbies" rows="3" maxlength="1200"></textarea>
													</c:otherwise>
												</c:choose>
												<p class="required" id="required_hobby">Field Required</p>																		
											</div>
											<div class="col-md-2">
		                                        <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="Example: I am an avid reader and love fiction. I also am a big fan of the theatre and try catching plays whenever I can.">
											   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
		                               		</div>
                                	</div>
                                	<input type="hidden" id="edit" name="edit" value="true">
                                		</c:when>
                                		<c:otherwise>
                             		<div class="form-group" id="daward">
										<label for="icode" class="col-md-3 control-label">&#8226; </label>
	                                    <div class="col-md-5">
	                                        <input id="award0" class="form-control" name="achievement[]" maxlength='350'>
											<p class="required" id="required_award">Field Required</p>																			                                        
										</div>
										<div class="col-md-2">	
	                                         <button type="button" class="btn btn-default" data-html=" " data-container="body" data-toggle="popover" data-trigger="focus" data-placement="right"  data-content="Example: Award for <b>Excellent Salesmanship</b> in 2010. <br>Part of teams which got nominated and won several prestigious awards in advertising">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               		</div>
                                	</div>
                                	<div  id="awards0">
                                	</div >
                                	<a href="" onclick="AddAchievements(event)" class="add-edu">Add More Achievements</a> 
                                	<hr>
                                	I can help you with:
                                	<c:set  var="skillcounter"  value="1"></c:set>
                                	 <div class="form-group" id="dskill">
										<label for="icode" class="col-md-3 control-label">&#8226; </label>
	                                    <div class="col-md-5">
	                                        <input id="skill0" class="form-control" name="keyskills[]" maxlength='350'>  
											<p class="required" id="required_skill">Field Required</p>																			                                        
										</div>
										<div class="col-md-2">
	                                        <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right" data-content="Example: Specialise in market research and understanding consumer behaviour.<br>Extensive knowledge about the field of advertising.<br>How to prepare for a career in digital marketing.<br> How to work with multi-cultural teams. ">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>  
	                               		</div>
                                	</div>
                                	<div  id="skills0">
                                	</div >
                                	<a href="" onclick="AddSkills(event)" class="add-edu">Add More Skills</a> 
                                	<hr>
                                	<div class="form-group" id="dhobby">
										<label for="icode" class="col-md-3 control-label">Know Your Advisor Better(HOBBIES & INTERESTS)</label>
	                                    <div class="col-md-5">
	                                        <textarea id="hobby" class="form-control" name="hobbies" rows="3" maxlength="1200"></textarea>
	                                        <p class="required" id="required_hobby">Field Required</p>																		
										</div>
										<div class="col-md-2">
	                                        <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="Example: I am an avid reader and love fiction. I also am a big fan of the theatre and try catching plays whenever I can.">
										   	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
	                               		</div>
                                	</div>

                                	</c:otherwise>
                               	</c:choose>
                                	
									<hr>
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
							</div><!--part3-->	
                     </form>
                   </div>
                 </div>
               </div>
				</div>
                
				<%@include file="/Footer.jsp" %>
			</div>
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

		<script>
		$(document).ready(function() {
			<!--job can't be blank-->
			$("#btn-signup").click(function(event){
				var i =0;
				while(i<=10){
					if(!$('#skill'+i).is(':visible')){
						$('#skill'+i).val("");	
					}
					i++;
				}
				var input_city = $("#skill0").val();
				if (input_city==''){
					$("#required_skill").show();
					event.preventDefault(); 
				}	
				else{
					$("#required_skill").hide();
				}
				
				var i =0;
				while(i<=10){
					i++;
					var input = $('#skill'+i).val();
					if(input =='' && $('#skill'+i).is(':visible')){
						$(".key"+i).show();	
						event.preventDefault(); 
					}else{
						$(".key"+i).hide();	
						if(!$('.skill'+i).is(':visible')){
							$('.skill'+i).val("");
						}
					}
				}
			});

			
			$("#btn-signup").click(function(event){
				var i=0;
				while(i<=10){
					i++;
					if(!$('#award'+i).is(':visible')){
						$('#award'+i).val("");
					}
					if(!$('#awards'+i).is(':visible')){
						$('#awards'+i).val("");
					}
				}
			});
			
		});		
		</script>
		<script type="text/javascript">
		var l = 0;
		function AddAchievements(event){
			var max_award = 10;
			var i=0;
			event.preventDefault();
			if(i <= max_award) {
				  i++;
				  l++;
				  $("#awards0").append("<div class='form-group' id='dawards"+l+"'><label for='icode' class='col-md-3 control-label'>&#8226; </label><div class='col-md-4'><input id='awards"+i+"'  class='form-control award"+i+"' name='achievement[]' maxlength='350'><p class='required achieve"+i+"' id='required_award'>Field Required</p></div><div class='col-md-2'><a id='hide"+l+"' style='float:right' onclick=closediv(this)><i class='glyphicon glyphicon-remove'></i></a></div></div>");	  	
			}
		}
		var k="${skillcounter}";
		function AddSkills(event){
			
			var max_skill = 10;
			event.preventDefault();
			if(k <= max_skill) {
				$("#skills0").append("<div class='form-group' id='dskill"+k+"'><label for='icode' class='col-md-3 control-label'>&#8226; </label><div class='col-md-4'><input id='skill"+k+"' class='form-control skill"+k+"' name='keyskills[]' maxlength='350' ><p class='required key"+k+"' id='required_skill'>Field Required</p></div><div class='col-md-2'><a id='hide"+k+"' style='float:right' onclick=closeskill(this)><i class='glyphicon glyphicon-remove'></i></div></div>");	
				k++;
				i++;
			}
		}
		
		function closediv(elem){
			var id =elem.id;
			var element = id.split("hide");
			$('#dawards'+element[1]).hide();
		}
		function closeaward(elem){
			var id =elem.id;
			var element = id.split("hide");
			$('#daward'+element[1]).hide();
		}
		function closeskill(elem){
			var id =elem.id;
			var element = id.split("hide");
			$('#dskill'+element[1]).hide();
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