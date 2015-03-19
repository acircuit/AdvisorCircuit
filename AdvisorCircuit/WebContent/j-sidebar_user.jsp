<a class="selectServices" href="#">Select Menu</a>
<div id="j_sidebar">
<div class="sidebar-nav">
	<ul class="nav" id="side-menu">
    	<li><a href="usermyaccount"><span class="myaccount"></span> My Account</a></li>						
        <li class="request"><a href="UserRequests"><span class="request"></span> Request</a></li>						
    	<li class="session"><a href="#"><span class="session"></span> Sessions</a>
        	<ul class="nav nav-second-level">
            	<li><a href="UserUpcomingSessions">Current</a></li>
            	<li><a href="UserPreviousSessions">Previous</a></li>
            	<li><a href="UserCancelledSessions">Cancelled</a></li>
        	</ul>
        	<!-- /.nav-second-level -->
    	</li>
	</ul>
</div>
</div>
<!-- /.sidebar-collapse -->

<script>
$(document).ready(function() {
	$(".selectServices").click(function(e) {
		e.preventDefault();
		$("#j_sidebar").slideToggle();
	});
});
</script>