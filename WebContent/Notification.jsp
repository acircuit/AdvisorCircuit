 <style type="text/css">
.notificationContainer 
{
background-color: #fff;
border: 1px solid rgba(100, 100, 100, .4);
-webkit-box-shadow: 0 3px 8px rgba(0, 0, 0, .25);
overflow: visible;
color : #ffffff;
position: absolute;
top: 30px;
margin-left: -80%;
z-index: -1;
width: 300px;
height: 500px;
overflow-y: scroll;
display: none; // Enable this after jquery implementation 
}
// Popup Arrow
.notificationContainer:before {
content: '';
display: block;
position: absolute;
width: 0;
height: 0;
border: 10px solid black;
border-color: transparent transparent white;
margin-top: -20px;
margin-left: 188px;
}
.notificationTitle
{
text-align: center;
font-weight: bold;
padding: 8px;
font-size: 12px;
background-color: #ffffff;
border-bottom: 1px solid #dddddd;
color: #555;
}
.notificationsBody
{
padding: 0px !important;
min-height:500px;
width: 100%;

}

.notificationsBody ul{padding: 0;list-style:none;width: 100%;}
.notificationsBody li{padding: 10px;text-align: left;"}
.notificationsBody li .date{  
  width: 100%;
  color: black;
  padding-top: 17px;
  font-size: 10px;
 }

.notificationsBody li a{
	margin :0px 0px 0px 0px;
}

.notificationFooter
{
background-color: #e9eaed;
text-align: center;
font-weight: bold;
padding: 8px;
font-size: 12px;
border-top: 1px solid #dddddd;
}
.notification_count 
{
padding: 3px 7px 3px 7px;
background: #cc0000;
color: #ffffff;
font-weight: bold;
margin-left: 25px;
border-radius: 9px;
-moz-border-radius: 9px; 
-webkit-border-radius: 9px;
position: absolute;
margin-top: -11px;
font-size: 11px;
}
</style>
<%
String source="";
if( session.getAttribute("admin") != null &&  (Boolean)session.getAttribute("admin")){
	source = "admin";
}else if(session.getAttribute("userId") !=null ){
	source="user";
	}else if( session.getAttribute("advisorId") !=null){
		source="advisor";
	} 
%>
	<span class="notification_count" id="notification_count" style="display: none"></span>
			
			<a href="" class="notificationLink"><img alt="" src="assets/img/notification-icon.jpg"></a>
			<div class="notificationContainer" id="notificationContainer">
			
			<div class="notificationTitle" id="notificationTitle">Notifications</div>
			<div class="notificationsBody" id="notificationsBody">
			</div>
			<div class="notificationFooter" id="notificationFooter"></div>
			</div>
<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" >
var id ="";
	$(document).ready(function() 	
	{
		$(".notificationLink").click(function()
		{	
			var type="";
			if(<%=source.equals("admin")%>){
				type = "admin";
			}else if (<%=source.equals("advisor")%>) {
				type = "advisor";
			}else{
				type = "user";
			}
			if($("#notificationsBody").is(":visible") == false){
				$.ajax({
				    url : 'NotificationViewedController', // Your Servlet mapping or JSP(not suggested)
				    data : {"id" : id,"type" : type},
				    type : 'POST',
				    dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
				    success : function(response) {
				    },
				    error : function(request, textStatus, errorThrown) {
				        alert(errorThrown);
				    }
				}); 
			}	
		$(".notificationContainer").fadeToggle(300);
		$(".notification_count").fadeOut("slow");
		return false;
		
		});
		
		//Document Click hiding the popup 
		$(document).click(function()
		{
		$(".notificationContainer").hide();
		});
		
		if(<%=source.equals("admin")%>){
			var eventSource = new EventSource("AdminNotificationSSE");
			eventSource.addEventListener('notify', function(event) {
			        document.getElementById('notificationsBody').innerHTML = event.data;
			    }, false);
			eventSource.addEventListener('count', function(event) {
				
				if(event.data >0){
					 document.getElementById('notification_count').style.display = 'block';
			        document.getElementById('notification_count').innerHTML = event.data;
				}
		    }, false);
			eventSource.addEventListener('id', function(event) {
				id= event.data;
		    }, false);
		}
		else if(<%=source.equals("user")%>){
			var eventSource = new EventSource("UserNotificationSSE");
			eventSource.addEventListener('notify', function(event) {
			        document.getElementById('notificationsBody').innerHTML = event.data;
			    }, false);
			eventSource.addEventListener('count', function(event) {
				if(event.data >0){
					 var div = document.getElementById('notification_count');
					    div.style.display = 'block';
			        document.getElementById('notification_count').innerHTML = event.data;
				}
		    }, false);
			eventSource.addEventListener('id', function(event) {
				id= event.data;
		    }, false);
		}else if (<%=source.equals("advisor")%>) {
			var eventSource = new EventSource("AdvisorNotificationSSE");
			eventSource.addEventListener('notify', function(event) {
			        document.getElementById('notificationsBody').innerHTML = event.data;
			    }, false);
			eventSource.addEventListener('count', function(event) {
				if(event.data >0){
					 document.getElementById('notification_count').style.display = 'block';
			        document.getElementById('notification_count').innerHTML = event.data;
				}
		    }, false);
			eventSource.addEventListener('id', function(event) {
				id= event.data;
		    }, false);
		}
		
	
	});
</script>
