<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	Welcome ${userName}! You have logged in at ${time}.
	<sec:authorize access="hasRole('Admin')">
		<br/>
		<a href="/list-todos">Click here</a> to manage your todo's.
	</sec:authorize>
	
	<sec:authorize access="hasRole('Patient')">
		<br/>
		<a href="/add-user?roleId=1">Click here</a> to create new Patients.
	</sec:authorize>
	<sec:authorize access="hasRole('Doctor')">
		<br/>
		<a href="/add-user?roleId=2">Click here</a> to create new Doctor.
	</sec:authorize>
	
</div>
<%@ include file="common/footer.jspf"%>