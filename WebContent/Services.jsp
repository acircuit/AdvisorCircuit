<!DOCTYPE html>
<%@page import="org.AC.dto.AdvisorModeDTO"%>
<%@page import="org.AC.dto.AdvisorServiceDTO"%>
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
		List<AdvisorServiceDTO> advisorServices = (List<AdvisorServiceDTO>)request.getAttribute("advisorservices");
		List<AdvisorModeDTO> advisorModes = (List<AdvisorModeDTO>)request.getAttribute("advisormodes");
		pageContext.setAttribute("advisorServices", advisorServices);
		Boolean edit = (Boolean)request.getAttribute("edit");
		pageContext.setAttribute("advisorModes", advisorModes);
		String action ="";
		String general="";
		String education ="";
		String professional= "";
		String other = "";
		String service= "";
		String image = "";
		if(edit != null &&  edit){
			action = "ServicesEdit";
			general ="GeneralInfoEdit";
			education="EducationInfoEdit";
			professional="ProfessionalBackgroundEdit";
			other="OtherInfoEdit";
			service="ServicesEdit";
			image = "ImageEdit";
		}else{
			action = "AdvisorRegistrationServices";
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
<script src="assets/js/jquery-1.11.0.js"></script>
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
        
                
                        <!--part5--->
                        <div id="part5">
                        <ul class="tab-list">
                            <li><a href="<%=general %>?tab=true">General Information</li>
                            <li><a href="<%=education %>?tab=true">Education Information</a></li>
                            <li><a href="<%=professional %>?tab=true">Professional Background</a></li>
                            <li><a href="<%=other %>?tab=true">Other Information</a></li>
                            <li class="active"><a href="<%=service %>?tab=true">Service Information</a></li>
                            <li><a href="<%=image%>">Upload Image</a></li>
                        </ul>
                        
                        <h4>Services</h4>
                        <hr>
                            <c:set value="true" var="ct" ></c:set>
                            <c:set value="true" var="mi" ></c:set>
                            <c:set value="true" var="cv" ></c:set>
                        <c:choose>
                        
                            <c:when test="${advisorServices.size()> 0 &&  advisorModes.size()>0}">
                            <input type="hidden" id="edit" name="edit" value="true">
                                <div class="form-group">
                                <div class="col-md-10">
                                <c:forEach var="service" items="${advisorServices}">
                                    <c:if test="${service.getService().equals('careertalk')}">
                                                    <c:set value="false" var="ct" ></c:set>									
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" id="careerTalkCheckBox" name="services" value="careertalk" checked="checked">Career Talk
                                                        </label>
                                                    </div>
                                                <div id="collapseOne" class="panel-collapse collapse">
                                                
                                                <hr>
                                                <div class="form-group" id="dcareerTalk_description">
                                                    <div class="col-md-3">
                                                    <label for="icode" class="control-label">Description</label>
                                                    </div>
                                                    <div class="col-md-7">
                                                        <textarea id="careerTalk_description"  name="careertalkdescription" style="width:100%;" maxlength="400">${service.getDescription()}</textarea> 
                                       
                                                    </div>
                                                    <div class="col-md-2">	
                                                    	<button type="button" class="btn btn-default" data-html=" " data-trigger="focus" data-container="body" data-toggle="popover" data-placement="right"  data-content="Example: Ask me anything you want about the profession I love and have worked in for over 10 years">
                                                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    		<div class="col-md-3">
                                                   				<label for="icode" class="control-label " style="padding-top:0;">Free Session
                                                   				</label>
                                                   			</div>
                                                   			<div class="col-md-7">
                                                <c:choose>
                                                   	<c:when test="${service.getIsFree()== 0}">
                                                    	<select id="gender" class="dropreg" name="isfreecareertalk">
                                                    		      <option value="0">0</option>
			                                                      <option value="1">1</option>
			                                                      <option value="2">2</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 1}">
                                                    	<select id="gender" class="dropreg" name="isfreecareertalk">
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 2}">
                                                    	<select id="gender" class="dropreg" name="isfreecareertalk">
			                                                      <option value="2">2</option>
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 3}">
                                                    	<select id="gender" class="dropreg" name="isfreecareertalk">
                                                    			  <option value="3">3</option>	 
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:otherwise>
                                                   			<select id="gender" class="dropreg" name="isfreecareertalk">
			                                                      <option value="4">4</option>
                                                    			  <option value="3">3</option>	 
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                              </select>
                                                   	
                                                   	</c:otherwise>
                                                </c:choose>
                                                     <p>Please select if you want to give users a free introductory session. Session here means 30 mins. </p>
         
                                                   </div> 
                                                  </div>
                                                   
                                              			<c:set value="0" var="ctphone"></c:set>
                                                        <c:set value="0" var="ctemail"></c:set>
                                                        <c:set value="0" var="ctwebchat"></c:set>
                                                        <div class="form-group" id="dcareerTalk_mode">
                                                            <div class="col-md-3">
                                                            <label for="icode" class="control-label" style="text-align: left">Mode of Communication</label>
                                                            </div>
                                                            
                                                             <c:forEach var="mode" items="${advisorModes }">
                                                    			<c:if test="${mode.getAdvisorId() == service.getAdvisorId() && mode.getService() == service.getService() }">
		                                                            <c:if test="${mode.getModeOfCommunication().equals('phone')}">
		                                                                <div class="col-md-8">
		                                                                    <input type="checkbox" id="careertalkmodephone"  name="careertalkmode" value="phone" checked="checked"/> Phone
		                                                                </div>
		                                                                <c:set value="1" var="ctphone"></c:set>
		                                                            </c:if>
		                                                            <c:if test="${mode.getModeOfCommunication().equals('email')}">									                                       	
		                                                                <div class="col-md-8">
		                                                                	<input type="checkbox" id="careertalkmodeemail"  name="careertalkmode" value="email" checked="checked"/> Email
		                                                                </div>
		                                                                <c:set value="1" var="ctemail"></c:set>
		                                                            </c:if>
		                                                            <c:if test="${mode.getModeOfCommunication().equals('webchat')}">									                                        
		                                                                <div class="col-md-8">
		                                                                	<input type="checkbox" id="careertalkmodewebchat"  name="careertalkmode" value="webchat" checked="checked"/> WebChat
		                                                                </div>
		                                                                <c:set value="1" var="ctwebchat"></c:set>
		                                                            </c:if>
                                                            	</c:if>
                                                            </c:forEach>
                                                            <c:if test="${ctphone.equals('0')}">
                                                            	<div class="col-md-8">
                                                                    <input type="checkbox" id="careertalkmodephone"  name="careertalkmode" value="phone"/> Phone
                                                                </div>
                                                            </c:if>
                                                             <c:if test="${ctemail.equals('0')}">
                                                            	<div class="col-md-8">
                                                                	<input type="checkbox" id="careertalkmodeemail"  name="careertalkmode" value="email"/> Email
                                                                </div>
                                                            </c:if>
                                                             <c:if test="${ctwebchat.equals('0')}">
                                                            	<div class="col-md-8">
                                                                	<input type="checkbox" id="careertalkmodewebchat"  name="careertalkmode" value="webchat"/> WebChat
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                        
                                                          <c:forEach var="mode" items="${advisorModes }">
                                                    <c:if test="${mode.getAdvisorId() == service.getAdvisorId() && mode.getService() == service.getService() }">
                                                        <c:if test="${mode.getModeOfCommunication().equals('phone') }">
	                                                        <div id="collapsecareertalkpricephone" class="panel-collapse collapse">
	                                                             <div class="panel-body">
	                                                                <div class="form-group" id="dcareerTalk_phone">                                                                    
	                                                                    <label for="icode" class="col-md-3 control-label">Phone Price(Rs/Hour)</label>
	                                                                    <div class="col-md-7">
	                                                                        <input id="careerTalk_phone" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricephone" placeholder="" value="${mode.getPrice()}" maxlength="10">  
	                                                                    </div>
	                                                                    <div class="col-md-2">
	                                                                        <p class="required" id="required_careerTalk_phone">Field Required</p>
	                                                                        <p class="required" id="invalid_careerTalk_phone">Invalid Price</p>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                        </div>	
                                                        </c:if>
                                                        <c:if test="${mode.getModeOfCommunication().equals('email') }">	
	                                                        				                                									                                	
	                                                        <div id="collapsecareertalkpriceemail" class="panel-collapse collapse">
	                                                             <div class="panel-body">
	                                                                <div class="form-group" id="dcareerTalk_mail">
	                                                                    <label for="icode" class="col-md-3 control-label">Email Price</label>
	                                                                    <div class="col-md-7">
	                                                                        <input id="careerTalk_mail" type="text" class="form-control" name="careertalkpriceemail" placeholder="" value="${mode.getPrice()}" maxlength="10">  
	                                                                    </div>
	                                                                    <div class="col-md-3">
	                                                                        <p class="required" id="required_careerTalk_mail">Field Required</p>
	                                                                        <p class="required" id="invalid_careerTalk_mail">Invalid Price</p>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                        </div>
	                                                       
                                                        </c:if>
                                                        <c:if test="${mode.getModeOfCommunication().equals('webchat') }">
                                                        				                                	
	                                                        <div id="collapsecareertalkpricewebchat" class="panel-collapse collapse">
	                                                             <div class="panel-body">
	                                                                <div class="form-group" id="dcareerTalk_chat">
	                                                                    <label for="icode" class="col-md-3 control-label">Web Chat Price(Rs/Hour)</label>
	                                                                    <div class="col-md-7">
	                                                                        <input id="careerTalk_chat" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricewebchat" placeholder="" value="${mode.getPrice()}" maxlength="10">  
	                                                                    </div>
	                                                                    <div class="col-md-2">
	                                                                        <p class="required" id="required_careerTalk_chat">Field Required</p>
	                                                                        <p class="required" id="invalid_careerTalk_chat">Invalid Price</p>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                        </div>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${ ctphone.equals('0')}">
                                                      <div id="collapsecareertalkpricephone" class="panel-collapse collapse">
                                                           <div class="panel-body">
                                                              <div class="form-group" id="dcareerTalk_phone">                                                                    
                                                                  <label for="icode" class="col-md-3 control-label">Phone Price(Rs/Hour)</label>
                                                                  <div class="col-md-7">
                                                                      <input id="careerTalk_phone" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricephone" placeholder="" maxlength="10">  
                                                                  </div>
                                                                  <div class="col-md-2">
                                                                      <p class="required" id="required_careerTalk_phone">Field Required</p>
                                                                      <p class="required" id="invalid_careerTalk_phone">Invalid Price</p>
                                                                  </div>
                                                              </div>
                                                          </div>
                                                      </div>	                                                
                                                </c:if>
                                                <c:if test="${ ctemail.equals('0')}">
                                                      <div id="collapsecareertalkpriceemail" class="panel-collapse collapse">
                                                            <div class="panel-body">
                                                               <div class="form-group" id="dcareerTalk_mail">
                                                                   <label for="icode" class="col-md-3 control-label">Email Price</label>
                                                                   <div class="col-md-7">
                                                                       <input id="careerTalk_mail" type="text" class="form-control" name="careertalkpriceemail" placeholder="" maxlength="10" >  
                                                                   </div>
                                                                   <div class="col-md-3">
                                                                       <p class="required" id="required_careerTalk_mail">Field Required</p>
                                                                       <p class="required" id="invalid_careerTalk_mail">Invalid Price</p>
                                                                   </div>
                                                               </div>
                                                           </div>
	                                                   </div>                                        
                                                </c:if>
                                                <c:if test="${ ctwebchat.equals('0')}">
                                                      <div id="collapsecareertalkpricewebchat" class="panel-collapse collapse">
                                                          <div class="panel-body">
                                                             <div class="form-group" id="dcareerTalk_chat">
                                                                 <label for="icode" class="col-md-3 control-label">Web Chat Price(Rs/Hour)</label>
                                                                 <div class="col-md-7">
                                                                     <input id="careerTalk_chat" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricewebchat" placeholder="" maxlength="10">  
                                                                 </div>
                                                                 <div class="col-md-2">
                                                                     <p class="required" id="required_careerTalk_chat">Field Required</p>
                                                                     <p class="required" id="invalid_careerTalk_chat">Invalid Price</p>
                                                                 </div>
                                                             </div>
                                                         </div>
	                                                 </div>                                               
                                                </c:if>
                                                <hr>
                                             </div>
                                             </c:if>
                                             
                                             <c:if test="${service.getService().equals('mockinterview')}">
                                                 <c:set value="false" var="mi" ></c:set>									
                                                	<div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" id="mockInterviewCheckBox" name="services" value="mockinterview" checked="checked">Mock Interview
                                                        </label>
                                                    </div>
                                                    
                                                 <div id="collapseTwo" class="panel-collapse collapse">
                                                    
                                                    <hr>
                                                    <div class="form-group" id="dmockInterview_description">
                                                        <div class="col-md-3">
                                                        <label for="icode" class="control-label">Description</label>
                                                    </div>   
                                                       <div class="col-md-5">
                                                            <textarea id="mockInterview_description" name="mockinterviewdescription" style="width:100%;" maxlength="400">${service.getDescription() }</textarea> 
                                                        </div>
                                                        <div class="col-md-2">	
                                                                    <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right"  data-content="Example: I have conducted interviews and recruited over 50 individuals for various positions and feel confident that I can give you a close to reality interview experience">
                                                                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                        </div> 
                                                    </div>
                                                    <div class="form-group">
                                                    		<div class="col-md-3">
                                                   				<label for="icode" class="control-label " style="padding-top:0;">Free Session
                                                   				</label>
                                                   			</div>
                                                   			<div class="col-md-7">
                                                <c:choose>
                                                   	<c:when test="${service.getIsFree()== 0}">
                                                    	<select id="gender" class="dropreg" name="isfreemockinterview">
                                                    		      <option value="0">0</option>
			                                                      <option value="1">1</option>
			                                                      <option value="2">2</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 1}">
                                                    	<select id="gender" class="dropreg" name="isfreemockinterview">
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 2}">
                                                    	<select id="gender" class="dropreg" name="isfreemockinterview">
			                                                      <option value="2">2</option>
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 3}">
                                                    	<select id="gender" class="dropreg" name="isfreemockinterview">
                                                    			  <option value="3">3</option>	 
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:otherwise>
                                                   			<select id="gender" class="dropreg" name="isfreemockinterview">
			                                                      <option value="4">4</option>
                                                    			  <option value="3">3</option>	 
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                              </select>
                                                   	
                                                   	</c:otherwise>
                                                </c:choose>
                                                <p>Please select if you want to give users a free introductory session. Session here means 30 mins. </p>

                                                   </div> 
                                                  </div>
                                                    <c:set value="0" var="miphone"></c:set>
                                                     <c:set value="0" var="miwebchat"></c:set>
                                                    <div class="form-group" id="dmockInterview_mode">
	                                                        <div class="col-md-3">
	                                                        <label for="icode" class="control-label" style="text-align: left">Mode Of Communication</label>
	                                                        </div>
	                                                         <c:forEach var="mode" items="${advisorModes }">
	                                                       			 <c:if test="${mode.getAdvisorId() == service.getAdvisorId() && mode.getService() == service.getService() }">
				                                                            <c:if test="${mode.getModeOfCommunication().equals('phone')}">
				                                                            	<div class="col-md-8 ">								                                    
				                                                                	<input type="checkbox" id="mockinterviewmodephone" name="mockinterviewmode" value="phone" checked="checked"/> Phone
				                                                            	</div>
				                                                            	<c:set value="1" var="miphone"></c:set>
				                                                            </c:if>
				                                                            <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
				                                                            	<div class="col-md-8 ">									                                        
				                                                                	<input type="checkbox" id="mockinterviewmodewebchat" name="mockinterviewmode" value="webchat" checked="checked" /> WebChat
				                                                            	</div>
				                                                            	<c:set value="1" var="miwebchat"></c:set>
				                                                            </c:if>
				                                             	    </c:if>
				                                             </c:forEach>
				                                             <c:if test="${miphone.equals('0')}">
				                                             	<div class="col-md-8 ">								                                    
				                                                        <input type="checkbox" id="mockinterviewmodephone" name="mockinterviewmode" value="phone"/> Phone
				                                                </div>
				                                             </c:if>
				                                             <c:if test="${miwebchat.equals('0')}">
				                                             	<div class="col-md-8 ">								                                    
				                                                        <input type="checkbox" id="mockinterviewmodewebchat" name="mockinterviewmode" value="webchat"/> WebChat
				                                                </div>
				                                             </c:if>
				                                       </div>
			                                                    
                                                    </div>
                                                    <c:forEach var="mode" items="${advisorModes }">
                                                        <c:if test="${mode.getAdvisorId() == service.getAdvisorId() && mode.getService() == service.getService() }">
                                                    <c:if test="${mode.getModeOfCommunication().equals('phone') }">								                                									                                																	
                                                        <div id="collapsemockinterviewpricephone" class="panel-collapse collapse">
                                                                
                                                            <div class="panel-body">
                                                                <div class="form-group" id="dmockInterview_phone">                                                                    
                                                                    <label for="icode" class="col-md-3 control-label">Phone Price(Rs/Hour)</label>
                                                                    <div class="col-md-6">
                                                                        <input id="mockInterview_phone" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricephone" placeholder="" value="${mode.getPrice() }" maxlength="10">  
                                                                    </div>
                                                                    <div class="col-md-3">
                                                                        <p class="required" id="required_mockInterview_phone">Field Required</p>
                                                                        <p class="required" id="invalid_mockInterview_phone">Invalid Price</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${mode.getModeOfCommunication().equals('webchat') }">								                                									                                																				                                								                                	
                                                        <div id="collapsemockinterviewpricewebchat" class="panel-collapse collapse">
                                                             <div class="panel-body">
                                                                <div class="form-group" id="dmockInterview_chat">                                                                    
                                                                    <label for="icode" class="col-md-3 control-label">Web Chat Price(Rs/Hour)</label>
                                                                    <div class="col-md-7">
                                                                        <input id="mockInterview_chat" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricewebchat" placeholder="" value="${mode.getPrice()}" maxlength="10">  
                                                                    </div>
                                                                    <div class="col-md-3">
                                                                        <p class="required" id="required_mockInterview_chat">Field Required</p>
                                                                        <p class="required" id="invalid_mockInterview_chat">Invalid Price</p>
                                                                    </div>
                                                                </div>
                                                                
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${miphone.equals('0') }">
                                           		<div id="collapsemockinterviewpricephone" class="panel-collapse collapse">
                                                           <div class="panel-body">
                                                               <div class="form-group" id="dmockInterview_phone">                                                                    
                                                                   <label for="icode" class="col-md-3 control-label">Phone Price(Rs/Hour)</label>
                                                                   <div class="col-md-6">
                                                                       <input id="mockInterview_phone" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricephone" placeholder="" maxlength="10">  
                                                                   </div>
                                                                   <div class="col-md-3">
                                                                       <p class="required" id="required_mockInterview_phone">Field Required</p>
                                                                       <p class="required" id="invalid_mockInterview_phone">Invalid Price</p>
                                                                   </div>
                                                               </div>
                                                           </div>
                                                      </div>
                                           </c:if>
                                           <c:if test="${miwebchat.equals('0')}">
                                           		<div id="collapsemockinterviewpricewebchat" class="panel-collapse collapse">
                                                     <div class="panel-body">
                                                        <div class="form-group" id="dmockInterview_chat">                                                                    
                                                            <label for="icode" class="col-md-3 control-label">Web Chat Price(Rs/Hour)</label>
                                                            <div class="col-md-7">
                                                                <input id="mockInterview_chat" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricewebchat" placeholder="" maxlength="10">  
                                                            </div>
                                                            <div class="col-md-3">
                                                                <p class="required" id="required_mockInterview_chat">Field Required</p>
                                                                <p class="required" id="invalid_mockInterview_chat">Invalid Price</p>
                                                            </div>
                                                        </div>
                                                        
                                                    </div>
                                               </div>
                                           </c:if>
                                           <hr>
                                            </c:if>
                                           <c:if test="${service.getService().equals('cvcritique')}">
                                           <c:set value="false" var="cv"></c:set>
                                            
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="cvCritiqueCheckBox" name="services" value="cvcritique" checked="checked">Resume Critique
                                                </label>
                                            </div>
                                            <hr>
                                            
                                            <div id="collapseThree" class="panel-collapse collapse">
                                            
                                            <div class="form-group" id="dcvCritique_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                                </div>
                                                <div class="col-md-5">
                                                    <textarea id="cvCritique_description" name="cvcritiquedescription" style="width:100%;" maxlength="400">${service.getDescription()}</textarea>  
                                                    
                                                </div>
                                                 <div class="col-md-2">	
                                                            <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right"  data-content="Example: I have scanned and shortlisted various resume over the span of my career and know what recruiters look for in potential candidates.">
                                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                    </div>
                                                  
                                            </div>
                                            <div class="form-group">
                                                    		<div class="col-md-3">
                                                   				<label for="icode" class="control-label " style="padding-top:0;">Free Session
                                                   				</label>
                                                   			</div>
                                                   			<div class="col-md-7">
                                                <c:choose>
                                                   	<c:when test="${service.getIsFree()== 0}">
                                                    	<select id="gender" class="dropreg" name="isfreeresumecritique">
                                                    		      <option value="0">0</option>
			                                                      <option value="1">1</option>
			                                                      <option value="2">2</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 1}">
                                                    	<select id="gender" class="dropreg" name="isfreeresumecritique">
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 2}">
                                                    	<select id="gender" class="dropreg" name="isfreeresumecritique">
			                                                      <option value="2">2</option>
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:when test="${service.getIsFree()== 3}">
                                                    	<select id="gender" class="dropreg" name="isfreeresumecritique">
                                                    			  <option value="3">3</option>	 
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                                      <option value="4">4</option>
			                                              </select>
                                            				
                                                   	</c:when>
                                                   	<c:otherwise>
                                                   			<select id="gender" class="dropreg" name="isfreeresumecritique">
			                                                      <option value="4">4</option>
                                                    			  <option value="3">3</option>	 
			                                                      <option value="1">1</option>
                                                    		      <option value="0">0</option>
			                                                      <option value="2">2</option>
			                                              </select>
                                                   	
                                                   	</c:otherwise>
                                                </c:choose>
                                                	<p>Please select if you want to give users a free introductory session. Session here means 30 mins. </p>

                                                   </div> 
                                                  </div>
                                           
                                                	<c:set value="0" var="rcphone"></c:set>
                                                    <c:set value="0" var="rcwebchat"></c:set>
		                                            <div class="form-group" id="dcvCritique_mode">
		                                            	<div class="col-md-3">
		                                                <label for="icode" class="control-label" style="text-align: left">Mode Of Communication</label>
		                                                </div>
		                                                <div class="col-md-9">
		                                                	<c:forEach var="mode" items="${advisorModes }">
		                                                		<c:if test="${mode.getAdvisorId() == service.getAdvisorId() && mode.getService() == service.getService() }">
				                                                    <c:if test="${mode.getModeOfCommunication().equals('phone')}">	
				                                                    	<div class="col-md-9">									                                    						                                    	
				                                                        	<input type="checkbox" id="cvcritiquemodephone" name="cvcritiquemode" value="phone" checked="checked"/> Phone
				                                                    	</div>
				                                                    	<c:set value="1" var="rcphone"></c:set>
				                                                    </c:if>
				                                                    <c:if test="${mode.getModeOfCommunication().equals('webchat')}">
				                                                    	<div class="col-md-9">									                                    						                                        
				                                                        	<input type="checkbox" id="cvcritiquemodewebchat" name="cvcritiquemode" value="webchat" checked="checked" /> WebChat
				                                                    	</div>
				                                                    	<c:set value="1" var="rcwebchat"></c:set>
				                                                    </c:if>
				                                                 </c:if>
			                                                 </c:forEach>
			                                                 <c:if test="${rcphone.equals('0')}">
			                                                 		<div class="col-md-9">									                                    						                                    	
				                                                        	<input type="checkbox" id="cvcritiquemodephone" name="cvcritiquemode" value="phone"/> Phone
				                                                    	</div>
			                                                 </c:if>
			                                                 <c:if test="${rcwebchat.equals('0')}">
			                                                 		<div class="col-md-9">									                                    						                                    	
				                                                        	<input type="checkbox" id="cvcritiquemodewebchat" name="cvcritiquemode" value="webchat"/> WebChat
				                                                    	</div>
			                                                 </c:if>
		                                                </div>
		                                            </div>
		                                     <c:forEach var="mode" items="${advisorModes }">
                                                <c:if test="${mode.getAdvisorId() == service.getAdvisorId() && mode.getService() == service.getService() }">
		                                            <c:if test="${mode.getModeOfCommunication().equals('phone') }">								                                									                                																															
		                                                <div id="collapsecvcritiquepricephone" class="panel-collapse collapse">
		                                                     <div class="panel-body">
		                                                        <div class="form-group" id="dcvCritique_phone">		                                                            
		                                                            <label for="icode" class="col-md-3 control-label">Phone Price(Rs/Hour)</label>
		                                                            <div class="col-md-6">
		                                                                <input id="cvCritique_phone" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricephone" placeholder="" value="${mode.getPrice()}" maxlength="10">  
		                                                            </div>
		                                                            <div class="col-md-3">
		                                                                <p class="required" id="required_cvCritique_phone">Field Required</p>
		                                                                <p class="required" id="invalid_cvCritique_phone">Invalid Price</p>
		                                                            </div>
		                                                        </div>
		                                                    </div>
		                                                </div>
		                                            </c:if>
		                                            <c:if test="${mode.getModeOfCommunication().equals('webchat') }">								                                									                                																																                                					
		                                                <div id="collapsecvcritiquepricewebchat" class="panel-collapse collapse">
		                                                     <div class="panel-body">
		                                                        <div class="form-group" id="dcvCritique_chat">		                                                            
		                                                            <label for="icode" class="col-md-3 control-label">Web Chat Price(Rs/Hour)</label>
		                                                            <div class="col-md-7">
		                                                                <input id="cvCritique_chat" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricewebchat" placeholder="" value="${mode.getPrice()}" maxlength="10">  
		                                                            </div>
		                                                            <div class="col-md-2">
		                                                                <p class="required" id="required_cvCritique_chat">Field Required</p>
		                                                                <p class="required" id="invalid_cvCritique_chat">Invalid Price</p>
		                                                            </div>
		                                                        </div>
		                                                    </div>
		                                                </div>
		                                            </c:if>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${rcphone.equals('0') }">
                                        	<div id="collapsecvcritiquepricephone" class="panel-collapse collapse">
                                                   <div class="panel-body">
                                                      <div class="form-group" id="dcvCritique_phone">		                                                            
                                                          <label for="icode" class="col-md-3 control-label">Phone Price(Rs/Hour)</label>
                                                          <div class="col-md-6">
                                                              <input id="cvCritique_phone" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricephone" placeholder="" value="${mode.getPrice()}" maxlength="10">  
                                                          </div>
                                                          <div class="col-md-3">
                                                              <p class="required" id="required_cvCritique_phone">Field Required</p>
                                                              <p class="required" id="invalid_cvCritique_phone">Invalid Price</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                              </div>
                                        </c:if>
                                        <c:if test="${rcwebchat.equals('0') }">
                                        	<div id="collapsecvcritiquepricewebchat" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                       <div class="form-group" id="dcvCritique_chat">		                                                            
                                                           <label for="icode" class="col-md-3 control-label">Web Chat Price(Rs/Hour)</label>
                                                           <div class="col-md-7">
                                                               <input id="cvCritique_chat" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricewebchat" placeholder="" value="${mode.getPrice()}" maxlength="10">  
                                                           </div>
                                                           <div class="col-md-2">
                                                               <p class="required" id="required_cvCritique_chat">Field Required</p>
                                                               <p class="required" id="invalid_cvCritique_chat">Invalid Price</p>
                                                           </div>
                                                       </div>
                                                   </div>
                                               </div>
                                        </c:if>
                                        <hr>
                                    </div>
                                 </c:if>
                                       
                                </c:forEach>
                                <c:if test="${ct == 'true' }">
                                        <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="careerTalkCheckBox" name="services" value="careertalk">Career Talk
                                                </label>
                                            </div>
                                        <div id="collapseOne" class="panel-collapse collapse">
                                            
                                            <hr>
                                            <div class="form-group" id="dcareerTalk_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                                </div>
                                                <div class="col-md-5">
                                                    <textarea id="careerTalk_description"  name="careertalkdescription" style="width:100%;" maxlength="400"></textarea> 
                                   
                                                </div>
                                                <div class="col-md-2">	
                                                            <button type="button" class="btn btn-default" data-html=" " data-trigger="focus" data-container="body" data-toggle="popover" data-placement="right"  data-content="Example: Ask me anything you want about the profession I love and have worked in for over 10 years">
                                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-3">
                                                 	<label for="icode" class="control-label " style="padding-top:0;"></label>
                                                 </div>
                                                 <div class="col-md-9">
                                            		<input type="checkbox" id="careertalkfreecheckbox" name="careertalkfreecheckbox" value="true"/> Please check if you want to give a free 30 mins introductory session.  
                                            	</div>                                                  	                                                    		
                                            </div>
                                            <div class="form-group" id="dcareerTalk_mode">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label" style="text-align: left">Mode of Communication</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="careertalkmodephone"  name="careertalkmode" value="phone"/> Phone
                                                    <input type="checkbox" id="careertalkmodeemail"  name="careertalkmode" value="email"  /> Email
                                                    <input type="checkbox" id="careertalkmodewebchat"  name="careertalkmode" value="webchat"  /> WebChat
                                                </div>
                                                
                                            </div>
                                            <hr>
                                            <div id="collapsecareertalkpricephone" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcareerTalk_phone">
                                                        <h4>Phone</h4>
                                                        
                                                        <label for="icode" class="col-md-3 control-label">Price(Rs/Hour)</label>
                                                        <div class="col-md-6">
                                                            <input id="careerTalk_phone" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricephone" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-3">
                                                            <p class="required" id="required_careerTalk_phone">Field Required</p>
                                                            <p class="required" id="invalid_careerTalk_phone">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            <hr>	
                                            </div>
                                            <div id="collapsecareertalkpricewebchat" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcareerTalk_chat">
                                                        <h4>Web Chat</h4>
                                            
                                                        <label for="icode" class="col-md-3 control-label">Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="careerTalk_chat" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricewebchat" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_careerTalk_chat">Field Required</p>
                                                            <p class="required" id="invalid_careerTalk_chat">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>
                                            
                                            
                                            <div id="collapsecareertalkpriceemail" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcareerTalk_mail">
                                                        <h4>Email</h4>
                                                        
                                                        <label for="icode" class="col-md-3 control-label">Price</label>
                                                        <div class="col-md-7">
                                                            <input id="careerTalk_mail" type="text" class="form-control" name="careertalkpriceemail" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_careerTalk_mail">Field Required</p>
                                                            <p class="required" id="invalid_careerTalk_mail">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>
                                            </div>
                                
                                
                                
                                </c:if>
                                <c:if test="${mi =='true' }">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="mockInterviewCheckBox" name="services" value="mockinterview">Mock Interview
                                                </label>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse">
                                            
                                            <hr>
                                            <div class="form-group" id="dmockInterview_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                            </div>   
                                               <div class="col-md-7">
                                                    <textarea id="mockInterview_description" name="mockinterviewdescription" style="width:100%;" maxlength="400"></textarea> 
                                                </div>
                                                <div class="col-md-2">	
                                                            <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right"  data-content="Example: I have conducted interviews and recruited over 50 individuals for various positions and feel confident that I can give you a close to reality interview experience">
                                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                </div> 
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-3">
                                                   	<label for="icode" class="control-label " style="padding-top:0;"></label>
                                                </div>
                                                <div class="col-md-9">
                                            		<input type="checkbox" id="mockinterviewfreecheckbox" name="mockinterviewfreecheckbox" value="true"/> Please check if you want to give a free 30 mins introductory session.
                                            	</div>                                                    	                                                    		
                                            </div>
                                            <div class="form-group" id="dmockInterview_mode">
                                            	<div class="col-md-3">
                                                <label for="icode" class="control-label" style="text-align: left">Mode Of Communication</label>
                                                </div>
                                                <div class="col-md-9">
                                                    <input type="checkbox" id="mockinterviewmodephone" name="mockinterviewmode" value="phone"/> Phone
                                                    <input type="checkbox" id="mockinterviewmodewebchat" name="mockinterviewmode" value="webchat" /> WebChat
                                                </div>
                                            </div>
                                            <hr>
                                            <div id="collapsemockinterviewpricephone" class="panel-collapse collapse">
                                                    
                                                <div class="panel-body">
                                                    <div class="form-group" id="dmockInterview_phone">
                                                        <h4>Phone</h4>
                                                        
                                                        <label for="icode" class="col-md-3 control-label">Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="mockInterview_phone" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricephone" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_mockInterview_phone">Field Required</p>
                                                            <p class="required" id="invalid_mockInterview_phone">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>			                                	
                                            <div id="collapsemockinterviewpricewebchat" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dmockInterview_chat">
                                                        <h4>Web Chat</h4>
                                                        
                                                        <label for="icode" class="col-md-3 control-label">Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="mockInterview_chat" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricewebchat" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_mockInterview_chat">Field Required</p>
                                                            <p class="required" id="invalid_mockInterview_chat">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
                                        
                                
                                </c:if>
                                    <c:if test="${cv =='true' }">
                                        <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="cvCritiqueCheckBox" name="services" value="cvcritique" >Resume Critique
                                                </label>
                                            </div>
                                        <div id="collapseThree" class="panel-collapse collapse">
                                            
                                            <hr>
                                            <div class="form-group" id="dcvCritique_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                                </div>
                                                <div class="col-md-7">
                                                    <textarea id="cvCritique_description" name="cvcritiquedescription" style="width:100%;" maxlength="400"></textarea>  
                                                    
                                                </div>
                                                 <div class="col-md-2">	
                                                            <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right"  data-content="Example: I have scanned and shortlisted various resume over the span of my career and know what recruiters look for in potential candidates.">
                                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                    </div>
                                            </div>
                                             <div class="form-group">
                                             	<div class="col-md-3">
                                                   	<label for="icode" class="control-label " style="padding-top:0;"></label>
                                                </div>
                                                <div class="col-md-9">
                                            		<input type="checkbox" id="resumecritiquefreecheckbox" name="resumecritiquefreecheckbox" value="true"/> Please check if you want to give a free 30 mins introductory session.  
                                            	</div>                                                  	                                                    		
                                            </div>
                                            <div class="form-group" id="dcvCritique_mode">
                                            	<div class="col-md-3">
                                                <label for="icode" class="control-label" style="text-align: left">Mode Of Communication</label>
                                                </div>
                                                <div class="col-md-9" id="cvCritique_mode">
                                                    <input type="checkbox" id="cvcritiquemodephone" name="cvcritiquemode" value="phone"/> Phone
                                                    <input type="checkbox" id="cvcritiquemodewebchat" name="cvcritiquemode" value="webchat" /> WebChat
                                                </div>
                                            </div>
                                            <hr>
                                            
                                            <div id="collapsecvcritiquepricephone" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcvCritique_phone">
                                                        <h4>Phone</h4>
                                                        
                                                        <label for="icode" class="col-md-3 control-label">Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="cvCritique_phone" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricephone" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_cvCritique_phone">Field Required</p>
                                                            <p class="required" id="invalid_cvCritique_phone">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>
                                            <div id="collapsecvcritiquepricewebchat" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcvCritique_chat">
                                                        <h4>Web Chat</h4>
                                                        
                                                        <label for="icode" class="col-md-3 control-label">Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="cvCritique_chat" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricewebchat" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_cvCritique_chat">Field Required</p>
                                                            <p class="required" id="invalid_cvCritique_chat">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    
                                    
                                    </c:if>
                                </div>
                        </div>
                        </c:when>
                        <c:otherwise>
                                <div class="form-group">
                            <div class="col-md-10">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="careerTalkCheckBox" name="services" value="careertalk">Career Talk
                                                </label>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse">
                                            
                                            <hr>
                                            <div class="form-group" id="dcareerTalk_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                                </div>
                                                <div class="col-md-7">
                                                    <textarea id="careerTalk_description"  name="careertalkdescription" style="width:100%;" maxlength="400"></textarea> 
                                   
                                                </div>
                                                <div class="col-md-2">	
                                                    <button type="button" class="btn btn-default" data-html=" " data-trigger="focus" data-container="body" data-toggle="popover" data-placement="right"  data-content="Example: Ask me anything you want about the profession I love and have worked in for over 10 years">
                                                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-3">
                                                   	<label for="icode" class="control-label " style="padding-top:0;"></label>
                                                </div>
                                                <div class="col-md-9">
                                            		      <select id="gender" class="dropreg" name="isfreecareertalk">
                                                    		      <option value="0">0</option>
			                                                      <option value="1">1</option>
                                                    			  <option value="2">2</option>	 
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
			                                              <p>Please select if you want to give users a free introductory session. Session here means 30 mins. </p>
                                                 
                                            	</div>
                                            </div>
                                            <div class="form-group" id="collapsecareertalkprice">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label" style="text-align: left">Mode of Communication</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="careertalkmodephone"  name="careertalkmode" value="phone"/> Phone
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="careertalkmodeemail"  name="careertalkmode" value="email"  /> Email
                                                 </div>
                                                 <div class="col-md-8">
                                                    <input type="checkbox" id="careertalkmodewebchat"  name="careertalkmode" value="webchat"  /> WebChat
                                                 </div>
                                            </div>
                                           <div id="collapsecareertalkpricephone" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcareerTalk_phone">                                                        
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Phone Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="careerTalk_phone" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricephone" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-3">
                                                            <p class="required" id="required_careerTalk_phone">Field Required</p>
                                                            <p class="required" id="invalid_careerTalk_phone">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="collapsecareertalkpriceemail" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcareerTalk_mail">                                                        
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Email Price</label>
                                                        <div class="col-md-7">
                                                            <input id="careerTalk_mail" type="text" class="form-control" name="careertalkpriceemail" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_careerTalk_mail">Field Required</p>
                                                            <p class="required" id="invalid_careerTalk_mail">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div id="collapsecareertalkpricewebchat" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcareerTalk_chat">                                            
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Web Chat Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="careerTalk_chat" type="text" onchange="sameprice(this)" class="form-control" name="careertalkpricewebchat" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_careerTalk_chat">Field Required</p>
                                                            <p class="required" id="invalid_careerTalk_chat">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                           <hr>
                                     </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="mockInterviewCheckBox" name="services" value="mockinterview">Mock Interview
                                                </label>
                                            </div>
                                        <div id="collapseTwo" class="panel-collapse collapse">
                                            
                                            <hr>
                                            <div class="form-group" id="dmockInterview_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                            </div>   
                                               <div class="col-md-7">
                                                    <textarea id="mockInterview_description" name="mockinterviewdescription" style="width:100%;" maxlength="400"></textarea> 
                                                </div>
                                                <div class="col-md-2">	
                                                            <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right"  data-content="Example: I have conducted interviews and recruited over 50 individuals for various positions and feel confident that I can give you a close to reality interview experience">
                                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                </div> 
                                            </div>
                                             <div class="form-group">
                                            	 <div class="col-md-3">
                                                   		<label for="icode" class="control-label " style="padding-top:0;"></label>
                                                  </div>
                                            	  <div class="col-md-9">
 															<select id="gender" class="dropreg" name="isfreemockinterview">
                                                    		      <option value="0">0</option>
			                                                      <option value="1">1</option>
                                                    			  <option value="2">2</option>	 
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
			                                              <p>Please select if you want to give users a free introductory session. Session here means 30 mins. </p>
                                                                            		  </div>                                         
                                            </div>
                                            <div class="form-group" id="dmockInterview_mode">
                                            	<div class="col-md-3">
                                                <label for="icode" class="control-label" style="text-align: left">Mode Of Communication</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="mockinterviewmodephone" name="mockinterviewmode" value="phone"/> Phone
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="mockinterviewmodewebchat" name="mockinterviewmode" value="webchat" /> WebChat
                                                </div>
                                            </div>
                                           <div id="collapsemockinterviewpricephone" class="panel-collapse collapse">
                                                    
                                                <div class="panel-body">
                                                    <div class="form-group" id="dmockInterview_phone">
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Phone Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="mockInterview_phone" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricephone" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_mockInterview_phone">Field Required</p>
                                                            <p class="required" id="invalid_mockInterview_phone">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>			                                	
                                            <div id="collapsemockinterviewpricewebchat" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dmockInterview_chat">
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Web Chat Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="mockInterview_chat" type="text" onchange="sameprice(this)" class="form-control" name="mockinterviewpricewebchat" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_mockInterview_chat">Field Required</p>
                                                            <p class="required" id="invalid_mockInterview_chat">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                                         <hr>
                                    </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="cvCritiqueCheckBox" name="services" value="cvcritique">Resume Critique
                                                </label>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse">
                                            
                                            <hr>
                                            <div class="form-group" id="dcvCritique_description">
                                                <div class="col-md-3">
                                                <label for="icode" class="control-label">Description</label>
                                                </div>
                                                <div class="col-md-7">
                                                    <textarea id="cvCritique_description" name="cvcritiquedescription" style="width:100%;" maxlength="400"></textarea>  
                                                    
                                                </div>
                                                 <div class="col-md-2">	
                                                            <button type="button" class="btn btn-default" data-html=" " data-container="body" data-trigger="focus" data-toggle="popover" data-placement="right"  data-content="Example: I have scanned and shortlisted various resume over the span of my career and know what recruiters look for in potential candidates.">
                                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> </button>
                                                    </div>
                                            </div>
                                             <div class="form-group">
                                            	<div class="col-md-3">
	                                            	<label for="icode" class="col-md-3 control-label"></label>
	                                            </div>
	                                            <div class="col-md-9">
	                                            	 <select id="gender" class="dropreg" name="isfreeresumecritique">
                                                    		      <option value="0">0</option>
			                                                      <option value="1">1</option>
                                                    			  <option value="2">2</option>	 
			                                                      <option value="3">3</option>
			                                                      <option value="4">4</option>
			                                              </select>
			                                              <p>Please select if you want to give users a free introductory session. Session here means 30 mins.</p>
                                           
                                            	</div>
                                            </div>          
                                            <div class="form-group" id="dcvCritique_mode">
                                            	<div class="col-md-3">
                                                <label for="icode" class="control-label" style="text-align: left">Mode Of Communication</label>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="cvcritiquemodephone" name="cvcritiquemode" value="phone"/> Phone
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="checkbox" id="cvcritiquemodewebchat" name="cvcritiquemode" value="webchat" /> WebChat
                                                </div>
                                            </div>
                                                                                                                       
                                            <div id="collapsecvcritiquepricephone" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcvCritique_phone">
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Phone Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="cvCritique_phone" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricephone" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_cvCritique_phone">Field Required</p>
                                                            <p class="required" id="invalid_cvCritique_phone">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="collapsecvcritiquepricewebchat" class="panel-collapse collapse">
                                                 <div class="panel-body">
                                                    <div class="form-group" id="dcvCritique_chat">                                                        
                                                        <label for="icode" class="col-md-3 control-label" style="text-align: left">Web Chat Price(Rs/Hour)</label>
                                                        <div class="col-md-7">
                                                            <input id="cvCritique_chat" onchange="sameprice(this)" type="text" class="form-control" name="cvcritiquepricewebchat" placeholder="" maxlength="10">  
                                                        </div>
                                                        <div class="col-md-2">
                                                            <p class="required" id="required_cvCritique_chat">Field Required</p>
                                                            <p class="required" id="invalid_cvCritique_chat">Invalid Price</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                           <hr>
                                        </div>
                                            
                                    </div>
                             </div>
                            </c:otherwise>
                        </c:choose>
                        
                        
                            
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
                                 <h4 style="font-family:'custom_light' !important;color: #c84c4e;text-align: left;">Note :<br>1. The price shown on your profile will be your price+ our service fee.<br>2. The prices for phone or webchat are the same.</h4> 
                                 		
                            </div>
                    </form>									                                
                    </div>	
                </div><!--part3-->
                </div>
                
		</div>
       
        <%@include file="/Footer.jsp" %>
    </div>
                 
                 
       <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>            
    
    <script type="text/javascript">
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
	  if(($("#careerTalkCheckBox").is(':checked'))||($("#mockInterviewCheckBox").is(':checked'))||($("#cvCritiqueCheckBox").is(':checked')) == true)
		{
		 var careerTalk_checked= $("#careerTalkCheckBox").is(':checked'); 
		 var input_careerTalk_description = $("#careerTalk_description").val();
		 var careerTalk_checked_phone= $("#careertalkmodephone").is(':checked'); 
		 var input_careerTalk_phone = $("#careerTalk_phone").val();
		 var careerTalk_checked_mail= $("#careertalkmodeemail").is(':checked'); 
		 var input_careerTalk_mail = $("#careerTalk_mail").val();
		 var careerTalk_checked_chat= $("#careertalkmodewebchat").is(':checked'); 
		 var input_careerTalk_chat = $("#careerTalk_chat").val();
		
		 if(careerTalk_checked == true)
		 {
			 if (input_careerTalk_description==''){
					$("#dcareerTalk_description").addClass("has-error");
					alert("Please Enter Career Talk Description");
					event.preventDefault(); 
				}	
				
				else{
						$("#dcareerTalk_description").removeClass("has-error");
					if(careerTalk_checked_phone||careerTalk_checked_chat||careerTalk_checked_mail==true)	
						{
							
							$("#dcareerTalk_mode").removeClass("has-error");
							if(careerTalk_checked_phone==true)
								{
					
									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_careerTalk_phone);
									if (input_careerTalk_phone==''){
										$("#required_careerTalk_phone").show();
										$("#invalid_careerTalk_phone").hide();
										$("#dcareerTalk_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_careerTalk_phone").hide();
										$("#invalid_careerTalk_phone").show();
										$("#dcareerTalk_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#dcareerTalk_phone").removeClass("has-error");
									$("#required_careerTalk_phone").hide();
									$("#invalid_careerTalk_phone").hide();}
								}
							 if(careerTalk_checked_mail==true)
								{
									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_careerTalk_mail);
									if (input_careerTalk_mail==''){
										$("#required_careerTalk_mail").show();
										$("#invalid_careerTalk_mail").hide();
										$("#dcareerTalk_mail").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_careerTalk_mail").hide();
										$("#invalid_careerTalk_mail").show();
										$("#dcareerTalk_mail").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#required_careerTalk_mail").hide();
									$("#invalid_careerTalk_mail").hide();
									$("#dcareerTalk_mail").removeClass("has-error");}
								}
							 if(careerTalk_checked_chat==true)
								{

									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_careerTalk_chat	);
									if (input_careerTalk_chat==''){
										$("#required_careerTalk_chat").show();
										$("#invalid_careerTalk_chat").hide();
										$("#dcareerTalk_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_careerTalk_chat").hide();
										$("#invalid_careerTalk_chat").show();
										$("#dcareerTalk_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#required_careerTalk_chat").hide();
									$("#invalid_careerTalk_chat").hide();
									$("#dcareerTalk_chat").removeClass("has-error");}
								}	
							}
				
						else{ 
							  $("#dcareerTalk_mode").addClass("has-error");
							  alert("Please enter Career Talk Mode");
							  event.preventDefault();
							}
				}
				
		}
			
			 //mock
			
		 var mockInterview_checked= $("#mockInterviewCheckBox").is(':checked'); 
		 var input_mockInterview_description = $("#mockInterview_description").val();
		 var mockInterview_checked_phone= $("#mockinterviewmodephone").is(':checked'); 
		 var input_mockInterview_phone = $("#mockInterview_phone").val();
		 var mockInterview_checked_mail= $("#mockinterviewmodeemail").is(':checked'); 
		 var input_mockInterview_mail = $("#mockInterview_mail").val();
		 var mockInterview_checked_chat= $("#mockinterviewmodewebchat").is(':checked'); 
		 var input_mockInterview_chat = $("#mockInterview_chat").val();
		
		 if(mockInterview_checked == true)
		 {
			 if (input_mockInterview_description==''){
					$("#dmockInterview_description").addClass("has-error");
					alert("Please Enter Mock Interview Description");
					event.preventDefault(); 
				}	
				
				else{
						$("#dmockInterview_description").removeClass("has-error");
					if(mockInterview_checked_phone||mockInterview_checked_chat==true)	
						{
							$("#dmockInterview_mode").removeClass("has-error");
							if(mockInterview_checked_phone==true)
								{
					
									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_mockInterview_phone);
									if (input_mockInterview_phone==''){
										$("#required_mockInterview_phone").show();
										$("#invalid_mockInterview_phone").hide();
										$("#dmockInterview_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_mockInterview_phone").hide();
										$("#invalid_mockInterview_phone").show();
										$("#dmockInterview_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#dmockInterview_phone").removeClass("has-error");
									$("#required_mockInterview_phone").hide();
									$("#invalid_mockInterview_phone").hide();}
								}
							 if(mockInterview_checked_chat==true)
								{
									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_mockInterview_chat);
									if (input_mockInterview_chat==''){
										$("#required_mockInterview_chat").show();
										$("#invalid_mockInterview_chat").hide();
										$("#dmockInterview_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_mockInterview_chat").hide();
										$("#invalid_mockInterview_chat").show();
										$("#dmockInterview_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#required_mockInterview_chat").hide();
									$("#invalid_mockInterview_chat").hide();
									$("#dmockInterview_chat").removeClass("has-error");}
								}	
							}
				
						else{ 
							  $("#dmockInterview_mode").addClass("has-error");
							  alert("Please enter Mock Interview Mode");
							  event.preventDefault();
							}
				}
				
		}
				 
			 
			 //cv critique
			 	
		 var cvCritique_checked= $("#cvCritiqueCheckBox").is(':checked'); 
		 var input_cvCritique_description = $("#cvCritique_description").val();
		 var cvCritique_checked_phone= $("#cvcritiquemodephone").is(':checked'); 
		 var input_cvCritique_phone = $("#cvCritique_phone").val();
		 var cvCritique_checked_mail= $("#cvcritiquemodeemail").is(':checked'); 
		 var input_cvCritique_mail = $("#cvCritique_mail").val();
		 var cvCritique_checked_chat= $("#cvcritiquemodewebchat").is(':checked'); 
		 var input_cvCritique_chat = $("#cvCritique_chat").val();
		
		 if(cvCritique_checked == true)
		 {
			 if (input_cvCritique_description==''){
					$("#dcvCritique_description").addClass("has-error");
					alert("Please Enter Resume Critique Description");
					event.preventDefault(); 
				}	
				
				else{
						$("#dcvCritique_description").removeClass("has-error");
					if(cvCritique_checked_phone||cvCritique_checked_chat==true)	
						{
							$("#dcvCritique_mode").removeClass("has-error");
							if(cvCritique_checked_phone==true)
								{
					
									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_cvCritique_phone);
									if (input_cvCritique_phone==''){
										$("#required_cvCritique_phone").show();
										$("#invalid_cvCritique_phone").hide();
										$("#dcvCritique_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_cvCritique_phone").hide();
										$("#invalid_cvCritique_phone").show();
										$("#dcvCritique_phone").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#dcvCritique_phone").removeClass("has-error");
									$("#required_cvCritique_phone").hide();
									$("#invalid_cvCritique_phone").hide();}
								}
							 if(cvCritique_checked_chat==true)
								{
									var filter = /^[0-9-+]+$/;
									var is = filter.test(input_cvCritique_chat);
									if (input_cvCritique_chat==''){
										$("#required_cvCritique_chat").show();
										$("#invalid_cvCritique_chat").hide();
										$("#dcvCritique_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else if (!is){
										$("#required_cvCritique_chat").hide();
										$("#invalid_cvCritique_chat").show();
										$("#dcvCritique_chat").addClass("has-error");
										event.preventDefault(); 
									}
									else{
									$("#required_cvCritique_chat").hide();
									$("#invalid_cvCritique_chat").hide();
									$("#dcvCritique_chat").removeClass("has-error");}
								}		
							}
				
						else{ 
							  $("#dcvCritique_mode").addClass("has-error");	
							  alert("Please enter Resume Critique Mode");
							  event.preventDefault();
							}
				}
				
		}
				 
				 
		}
			else{alert("Please choose a service");
				  event.preventDefault();		
				  }
			});
			//document ready
		});
	
	function sameprice(e){
		var id = e.id;
		if(id=="careerTalk_phone"){
			$('#careerTalk_chat').val($('#careerTalk_phone').val());	
		}else if (id=="careerTalk_chat") {
			$('#careerTalk_phone').val($('#careerTalk_chat').val());	
		}else if (id=="mockInterview_phone") {
			$('#mockInterview_chat').val($('#mockInterview_phone').val());	
		}else if (id=="mockInterview_chat") {
			$('#mockInterview_phone').val($('#mockInterview_chat').val());	
		}else if (id=="cvCritique_phone") {
			$('#cvCritique_chat').val($('#cvCritique_phone').val());	
		}else if (id=="cvCritique_chat") {
			$('#cvCritique_phone').val($('#cvCritique_chat').val());	
		}
		
		
		
	}
	$(document).ready(function(){
		 var checked= $("#careerTalkCheckBox").is(':checked'); 
		 if(checked){
			 $("#collapseOne").show(); 
		 }
		  $("#careerTalkCheckBox").click(function(){
			 if($("#careerTalkCheckBox").is(':checked')){
				 $("#collapseOne").show();
			 }else{
				 $("#collapseOne").hide();
			 }
		 });
	});
	$(document).ready(function(){
		  var checked= $("#mockInterviewCheckBox").is(':checked'); 
		  if(checked){
				 $("#collapseTwo").show();		  
		  }
		  $("#mockInterviewCheckBox").click(function(){
				 if($("#mockInterviewCheckBox").is(':checked') == true){
					 $("#collapseTwo").show();
				 }else{
					 $("#collapseTwo").hide();
				 }
		  });
	});
	$(document).ready(function(){
		var checked= $("#cvCritiqueCheckBox").is(':checked'); 
		if(checked) {
			$("#collapseThree").show();
		}
		  $("#cvCritiqueCheckBox").click(function(){
			  
				 if($("#cvCritiqueCheckBox").is(':checked') == true){
					 $("#collapseThree").show();
				 }else{
					 $("#collapseThree").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#personalWorkshopCheckBox").is(':checked');
		  if(checked){
				$("#collapseFour").show(); 
		  }
		  $("#personalWorkshopCheckBox").click(function(){
			  var checked= $("#personalWorkshopCheckBox").is(':checked'); 
				 if($("#personalWorkshopCheckBox").is(':checked') == true){
					 $("#collapseFour").show();
				 }else{
					 $("#collapseFour").hide();
				 }
		  });
	});

	$(document).ready(function(){
		  var checked= $("#careertalkmodephone").is(':checked');
		  if(checked){
				$("#collapsecareertalkpricephone").show();
		  }
		  $("#careertalkmodephone").click(function(){
				 if($("#careertalkmodephone").is(':checked') == true){
					 $("#collapsecareertalkpricephone").show();
				 }else{
					 $("#collapsecareertalkpricephone").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#careertalkmodeemail").is(':checked'); 
		  if(checked){
				$("#collapsecareertalkpriceemail").show();  
		  }
		  $("#careertalkmodeemail").click(function(){
				 if($("#careertalkmodeemail").is(':checked') == true){
					 $("#collapsecareertalkpriceemail").show();
				 }else{
					 $("#collapsecareertalkpriceemail").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#careertalkmodewebchat").is(':checked'); 
		  if(checked){
			$("#collapsecareertalkpricewebchat").show();  
		  }
		  $("#careertalkmodewebchat").click(function(){
				 if($("#careertalkmodewebchat").is(':checked') == true){
					 $("#collapsecareertalkpricewebchat").show();
				 }else{
					 $("#collapsecareertalkpricewebchat").hide();
				 }
		  });
	});

	$(document).ready(function(){
		  var checked= $("#mockinterviewmodephone").is(':checked'); 
		  if(checked){
			  $("#collapsemockinterviewpricephone").show();	  
		  }
		  $("#mockinterviewmodephone").click(function(){
				 if($("#mockinterviewmodephone").is(':checked') == true){
					 $("#collapsemockinterviewpricephone").show();
				 }else{
					 $("#collapsemockinterviewpricephone").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#mockinterviewmodeemail").is(':checked');
		  if(checked){
				$("#collapsemockinterviewpriceemail").show();  
		  }
		  $("#mockinterviewmodeemail").click(function(){
				 if($("#mockinterviewmodeemail").is(':checked') == true){
					 $("#collapsemockinterviewpriceemail").show();
				 }else{
					 $("#collapsemockinterviewpriceemail").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#mockinterviewmodewebchat").is(':checked');
		  if(checked){
				$("#collapsemockinterviewpricewebchat").show();
		  }
		  $("#mockinterviewmodewebchat").click(function(){
				 if($("#mockinterviewmodewebchat").is(':checked') == true){
					 $("#collapsemockinterviewpricewebchat").show();
				 }else{
					 $("#collapsemockinterviewpricewebchat").hide();
				 }
		  });
	});

	$(document).ready(function(){
		  var checked= $("#cvcritiquemodephone").is(':checked'); 
		  if(checked){
				 $("#collapsecvcritiquepricephone").show();  
		  }
		  $("#cvcritiquemodephone").click(function(){
				 if($("#cvcritiquemodephone").is(':checked') == true){
					 $("#collapsecvcritiquepricephone").show();
				 }else{
					 $("#collapsecvcritiquepricephone").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#cvcritiquemodeemail").is(':checked'); 
		  if(checked){
				$("#collapsecvcritiquepriceemail").show();  
		  }
		  $("#cvcritiquemodeemail").click(function(){
				 if($("#cvcritiquemodeemail").is(':checked') == true){
					 $("#collapsecvcritiquepriceemail").show();
				 }else{
					 $("#collapsecvcritiquepriceemail").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#cvcritiquemodewebchat").is(':checked'); 
		  if(checked){
				 $("#collapsecvcritiquepricewebchat").show(); 
		  }
		  $("#cvcritiquemodewebchat").click(function(){
				 if($("#cvcritiquemodewebchat").is(':checked') == true){
					 $("#collapsecvcritiquepricewebchat").show();
				 }else{
					 $("#collapsecvcritiquepricewebchat").hide();
				 }
		  });
	});

	$(document).ready(function(){
		  var checked= $("#personalworkshopmodephone").is(':checked'); 
		  if(checked){
				 $("#collapsepersonalworkshoppricephone").show();  
		  }
		  $("#personalworkshopmodephone").click(function(){
				 if($("#personalworkshopmodephone").is(':checked') == true){
					 $("#collapsepersonalworkshoppricephone").show();
				 }else{
					 $("#collapsepersonalworkshoppricephone").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#personalworkshopmodeemail").is(':checked'); 
		  if(checked){
				$("#collapsepersonalworkshoppriceemail").show();
		  }
		  $("#personalworkshopmodeemail").click(function(){
				 if($("#personalworkshopmodeemail").is(':checked') == true){
					 $("#collapsepersonalworkshoppriceemail").show();
				 }else{
					 $("#collapsepersonalworkshoppriceemail").hide();
				 }
		  });
	});
	$(document).ready(function(){
		  var checked= $("#personalworkshopmodewebchat").is(':checked'); 
		  if(checked){
			$("#collapsepersonalworkshoppricewebchat").show();
		  }
		  $("#personalworkshopmodewebchat").click(function(){
				 if($("#personalworkshopmodewebchat").is(':checked') == true){
					 $("#collapsepersonalworkshoppricewebchat").show();
				 }else{
					 $("#collapsepersonalworkshoppricewebchat").hide();
				 }
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