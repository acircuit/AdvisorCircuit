<!DOCTYPE html>
<%@page import="org.AC.dto.PromotionsDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<fmt:bundle basename="Resources.Dependency" prefix="path.">
  		 <link rel="shortcut icon" href=<fmt:message key="shortcuticon"/>>	
  	</fmt:bundle>
    <title>Users</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    
    <!-- MetisMenu CSS -->
    <link href="assets/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	
		<!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="assets/css/sb-admin-2.css" rel="stylesheet">
	<!--<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">-->
	<!-- Custom styles for this template -->
    
    <!-- Fonts from Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,900' rel='stylesheet' type='text/css'>
	
    <!-- Custom Fonts -->
    <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style type="text/css">
    	table {
		  table-layout: fixed;
		  width: 120px; /* Important */
		  
		}
		td,th {
		  width: 30px;
		  word-wrap: break-word;
		}
    </style>	
	<%
			List<PromotionsDTO> promotions = (List<PromotionsDTO>)request.getAttribute("promotions");
			pageContext.setAttribute("promotions", promotions);
	%>
</head>

<body>

    <div class="container">
	<%@include file="/Header.jsp" %>
    
    <div id="wrapper" class="content">
    	
        <div class="row">
        	<div class="col-md-3">&nbsp;</div>
            <div class="col-md-6">
            	<h4></h4>
            	<h1 class="page-header">Promotions</h1>
            </div>
             <div class="col-md-3">
				<input type="text" id="search" style="float: right;" placeholder="SEARCH" onkeyup="searchTable(this.value)"/>
			</div>
        </div>
          
        <div class="row">
        	
            <div class="col-md-3">
				<%@ include file="j-sidebar_admin.jsp" %>
            </div><!-- /sidebar -->
            
            <div class="col-md-9">
            	<div id="page-wrapper">
                    <div class="table-responsive" style="overflow-x:visible ">
                        <table style="text-align:center" class="table table-bordered" id="tblData">
                            <tr><th style="text-align:center">ID</th><th style="text-align:center">NAME</th><th style="text-align:center">ISACTIVE</th><th style="text-align:center">ACTION</th></tr>
                                <c:forEach items="${promotions}" var="promotion">
                                    <tr><td><p>${promotion.getId()}</p></td><td><p>${promotion.getPromo()}</p></td>
                                    <td>
                                    <c:choose>
                                        <c:when test="${promotion.getIsActive()}">
                                            <p>ACTIVATED</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>DEACTIVATED</p>
                                        </c:otherwise>
                                    </c:choose>
                                    </td>													
                                    <td>
                                    <ul style="list-style-type: none; padding-top:5px; padding-left:0px" class="action-btn">
                                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" >Select Action</a>
                                             <ul class="dropdown-menu" role="menu">
                                                <li><a id="${promotion.getId()}" onclick="deactivate(this)" href="#">Deactivate Promotion</a></li>
                                                <li><a id="${promotion.getId()}" onclick="activate(this)" href="#">Activate Promotion</a></li>	
                                            </ul>
                                        </li>
                                    </ul>
                                    </td>
                                    <div id ="deactivate${promotion.getId()}">
                                    </div>
                                    </tr>
                                </c:forEach>
                            </table>
                    </div>   
        		</div>
                
                <div style="margin-bottom: 80px">
                    <ul class="pagination" style="float: right;">
                        <li id="PageList"></li>
                    </ul>
                </div>
                
            </div><!-- /right area -->
            
		</div><!-- /row -->

 	</div>
    <!-- /#wrapper -->
    
    <%@include file="/Footer.jsp" %>
	</div>
	<!-- /#container -->

    <!-- jQuery Version 1.11.0 -->
	<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="assets/js/bootstrap-slider.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/js/sb-admin-2.js"></script>
    <script src="assets/js/Pagination.js"></script>
    <!-- Page-Level Demo Scripts - Notifications - Use for reference -->
    <script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
   <script type="text/javascript">
	function deactivate(e){
		var id = e.id;
			$.ajax({
                url : 'AdminPromotions', // Your Servlet mapping or JSP(not suggested)
                data : {"Id" :id,"activate" :false},
                type : 'POST',
                dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
                success : function(response) {
                    $('#deactivate'+id).html(response);
                    location.reload();
                   					// create an empty div in your page with some id
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                    
                }
            }); 		
		}
	
	function activate(e){
		var id = e.id;
		$.ajax({
            url : 'AdminPromotions', // Your Servlet mapping or JSP(not suggested)
            data : {"Id" :id,"activate" :true},
            type : 'POST',
            dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
            success : function(response) {
                $('#deactivate'+id).html(response);
                location.reload();
               					// create an empty div in your page with some id
            },
            error : function(request, textStatus, errorThrown) {
                alert(errorThrown);
                
            }
        }); 		
	}
	</script>
	<script src="assets/js/gridSearch.js"></script>

</body>

</html>
