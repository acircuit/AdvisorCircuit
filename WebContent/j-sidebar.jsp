<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="selectServices" href="#">Select Menu</a>
<div id="j_sidebar">
<div class="sidebar-nav">
    <ul class="nav" id="side-menu">
	     	<li><a href="myaccount"><span class="myaccount"></span> My Account</a></li>
		<li class="request"><a href="#"><span class="request"></span> Request</a>
        	<ul class="nav nav-second-level">
            	<li>
                    <c:url value="AdvisorRequests" var="newURL">
                        <c:param name="new" value="true"/>
                    </c:url>
                    <a href="${newURL}">New</a>
                </li>
                <li>
                    <c:url value="AdvisorRequests" var="answered">
                        <c:param name="answered" value="true"/>
                    </c:url>
                    <a href="${answered}">Answered</a>
                </li>
            </ul>
        </li>
        <li><a href="AdvisorMessageAdmin"><span class="msgadmin"></span> Message Admin</a></li>
        <li class="session"><a href="#"><span class="session"></span> Sessions</a>
            <ul class="nav nav-second-level">
                <li><a href="AdvisorUpcomingSessions">Current</a></li>
                <li><a href="AdvisorPreviousSessions">Previous</a></li>
                <li><a href="AdvisorCancelledSessions">Cancelled</a></li>
            </ul>
            <!-- /.nav-second-level -->
        </li>
        <li><a href="GeneralInfoEdit"><span class="editprofile"></span> EditProfile</a></li>
        <li><a href="advisorpayment"><span class="request"></span> Payment</a></li>
    </ul>
</div>
</div>

<script>
$(document).ready(function() {
	$(".selectServices").click(function(e) {
		e.preventDefault();
		$("#j_sidebar").slideToggle();
	});
});
</script>