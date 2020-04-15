<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="user">
		<form:hidden path="userId" />
		<fieldset class="form-group">
			<form:label path="firstName">First Name</form:label>
			<form:input path="firstName" type="text" class="form-control"
				required="required" />
			<form:errors path="firstName" cssClass="text-warning" />
		</fieldset>
		<fieldset class="form-group">
			<form:label path="lastName">Last Name</form:label>
			<form:input path="lastName" type="text" class="form-control" />
		</fieldset>
		<fieldset class="form-group">
			<form:label path="primaryEmail">Primary Email ID</form:label>
			<form:input path="primaryEmail" type="text" class="form-control"
				required="required" />
			<form:errors path="primaryEmail" cssClass="text-warning" />
		</fieldset>
		<fieldset class="form-group">
			<form:label path="primaryMobile">Primary Mobile Number</form:label>
			<form:input path="primaryMobile" type="text" class="form-control"/>
		</fieldset>
		
		<%-- <fieldset class="form-group">
			<form:label path="roleId">Role ID</form:label> 
			
			<form:input path="roleId" type="text" class="form-control" />
		</fieldset> --%>
		
		<%-- <form:select path="roleId" items="${userRoles}" /> --%>
		
		<fieldset class="form-group">
			<form:label path="userRole.roleId">Role ID</form:label>
			<form:select path="userRole.roleId" required="required" id="userRole.roleId">
				<form:option value="0" label="Select" />
				<form:options items="${userRoles}" />
			</form:select>
			<form:errors path="userRole.roleId" cssClass="text-warning" />
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="userName">User Name</form:label>
			<form:input path="userName" type="text" class="form-control"
				required="required" />
			<form:errors path="userName" cssClass="text-warning" />
		</fieldset> 
		
		<fieldset class="form-group">
			<form:label path="password">Set Password</form:label>
			<form:input path="password" type="password" class="form-control"
				required="required" />
			<form:errors path="password" cssClass="text-warning" />
		</fieldset> 
		
		<fieldset class="form-group">
			<form:label path="startDate">Registration Date</form:label>
			<form:input path="startDate" type="text" class="form-control"
				required="required" />
			<form:errors path="startDate" cssClass="text-warning" />
		</fieldset>
		
	<%-- 	<fieldset class="form-group">
			<form:label path="endDate">End Date</form:label>
			<form:input path="endDate" type="text" class="form-control" />
		</fieldset> --%>

		<button type="submit" class="btn btn-success">Submit</button>
		<button type="reset" class="btn btn-success">Reset</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>