<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="selectServices" href="#">Menu</a>
<div id="j_sidebar">
<div class="sidebar-nav">
    <ul class="nav" id="side-menu">
        <li class="request"><a href="AdminAdvisors"><span class="request"></span> Advisor</a>
            <ul class="nav nav-second-level">
                <li><a href="Email">Create Profile</a></li>
                <li><a href="EditProfile.jsp">Edit Profile</a></li>
            </ul>
        </li>
        <li class="request"><a href="AdminUsers"><span class="request"></span> User</a></li>
        <li class="request"><a href=""><span class="request"></span> Request</a>
            <ul class="nav nav-second-level">
                <li>
                    <c:url value="AdminRequests" var="pending">
                        <c:param name="pending" value="true"/>
                    </c:url>
                    <a href="${pending}">Pending</a>
                </li>
                <li>
                    <c:url value="AdminRequests" var="approved">
                        <c:param name="approved" value="true"/>
                    </c:url>
                    <a href="${approved}">Approved</a>
                </li>
            </ul>
        </li>
        <li class="session"><a href="#"><span class="session"></span> Sessions</a>
            <ul class="nav nav-second-level">
                <li><a href="AdminMyUpcomingSessions">Current</a></li>
                <li><a href="AdminPreviousSessions">Previous</a></li>
                <li><a href="AdminCancelledSessions">Cancelled</a></li>
            </ul>
            <!-- /.nav-second-level -->
        </li>
        <li class="request"><a href="AdminReview"><span class="request"></span> Reviews</a></li>
        <li class="request"><a href="AdminMessages"><span class="msgadmin"></span> Messages</a></li>
        <li class="request"><a href="AdminContactUs"><span class="request"></span> Contact Us</a></li>
        <li class="request"><a href="AdminHereToHelp"><span class="request"></span> We Are Here To Help</a></li>
        <li class="request"><a href="AdminPayment"><span class="request"></span> Payment</a></li>
        
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
