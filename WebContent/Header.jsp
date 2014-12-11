<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<% 
Boolean isLoggedIn=false;
String username="";
	if(session.getAttribute("username") !=null)
	{
		isLoggedIn=true;
		username=(String)session.getAttribute("username");
	}
	else{
		
		   Cookie cookie = null;
		   Cookie[] cookies = null;
		    // Get an array of Cookies associated with this domain
		   cookies = request.getCookies();
		   if( cookies != null ){
		      
		      for (int i = 0; i < cookies.length; i++){
		         cookie = cookies[i];
		         //out.print("Name : " + cookie.getName( ) + ",  ");
		         //out.print("Value: " + cookie.getValue( )+" <br/>");
		         if(cookie.getName().equals("username")){
		        	 username= cookie.getValue();
		        	 isLoggedIn=true;
		        	 break;
		         }
		      }
		  }
	}
	pageContext.setAttribute("isLoggedIn", isLoggedIn);
%>
</head>
<body>
 <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header" style="margin-left">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><b style="font-size:30px">Advisor </b></br> <b style="font-size:30px;color:#000;font-family: Arial">Circuit</b></a>
        </div>
         
        <div style="height:10px"></div>

        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
		    
            <li><a href="#" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-size:20px">Home</a></li>
            <li><a href="#" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-size:20px">About</a></li>
            <li><a href="#" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-size:20px">Services</a></li>
            <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-size:20px">Advisors<span class="caret"></span> </a>
			<ul class="dropdown-menu"> <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> 
			</ul> </li>
            <li><a href="#" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-size:20px">Contact</a></li>
			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-size:20px"> More <span class="caret"></span> </a>
			<ul class="dropdown-menu"> <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> 
		    </ul> </li>
		    <c:if test="${isLoggedIn}">
		    <li>
		    <div style="width:180px;">
			<form class="" role="form" action="SearchController.do" method="post">
			<div class="input-group">
				uday's account
			 </div>
			</form>
			</div>		    
		    </li>
			</c:if>
			<li>
			<div style="width:180px;">
			<form class="" role="form" action="SearchController" method="post">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search" name="search"  >
					 <span class="input-group-btn">
					 <button class="btn btn-default" type="button" style="height:42px"><i class="glyphicon glyphicon-search"></i></button> 
					 </span>
			 </div>
			</form>
			</div>
			</li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

</body>
</html>