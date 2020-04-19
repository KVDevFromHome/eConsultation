<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	<div class="container">

		<form:form action="/signin" method="Post">
			<c:if test="${param.error != null}">
				<p style='color: red'>Invalid username and password.</p>
			</c:if>
			<c:if test="${param.logout != null}">
				<p style='color: blue'>You have been logged out.</p>
			</c:if>
			
			<c:if test="${userInfo != null}">
				<p style='color: blue'>user ${userInfo.firstName} ${userInfo.lastName} added successfully!.</p>
			</c:if>
			
			<fieldset>
				<label>Enter your User Name</label> 
				
					<input type="text" id="txtUsername" name="txtUsername"
					class="form-control" required="required" value="${userInfo.userName}">
			
			</fieldset>
			<br />
			<fieldset>
				<label>Enter your Password</label> <input type="password" id="txtPassword" name="txtPassword" class="form-control" required="required">
			</fieldset>
			<br />
			<div class="form-actions" style="margin-top: 12px;">
				<input type="submit" class="btn btn-success"/>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>
		<div>Not yet Registered? </div>
		</br></br>
		<div>Register as <a href="/add-user?roleId=2" >Doctor</a></div></br>
		<div>Register as <a href="/add-user?roleId=1" >Patient</a></div>

	</div>
	<%@ include file="common/footer.jspf"%>
