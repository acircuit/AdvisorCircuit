<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="selectServices" href="#">Select Menu</a>
<div id="j_sidebar">
<div class="sidebar-nav">
    <ul class="nav" id="side-menu">
        <li><a href="AdminAdvisors">Advisor</a>
            <ul class="nav nav-second-level">
                <li><a href="Email">Create Profile</a></li>
                <li><a href="EditProfile.jsp">Edit Profile</a></li>
            </ul>
        </li>
        <li><a href="AdminUsers">User</a></li>
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
                <li><a href="AdminMyUpcomingSessions">Upcoming</a></li>
                <li><a href="AdminPreviousSessions">Previous</a></li>
                <li><a href="AdminCancelledSessions">Cancelled</a></li>
            </ul>
            <!-- /.nav-second-level -->
        </li>
        <li><a href="AdminReview">Review & Recommendation</a></li>
        <li><a href="AdminMessages"><span class="msgadmin"></span> Messages & File Uploads</a></li>
        <li><a href="AdminContactUs">Contact Us</a></li>
        <li><a href="AdminHereToHelp">We Are Here To Help</a></li>
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