<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>List of existing users</caption>
			<thead>
				<tr>
					<!-- <th>User Name</th> -->
					<th>First Name</th>
					<th>Last Name</th>
					<th>Role</th>
					<th>Primary Email</th>
					<th>Phone Number</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<%-- <td>${user.userName}</td> --%>
						<%-- <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td> --%>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.userRole.roleName}</td>
						<td>${user.primaryEmail}</td>
						<td>${user.primaryMobile}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-user?userId=${user.userId}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-user?userId=${user.userId}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
		<div> Actions available for user ${loggedUser} are : </div>
		
			<a class="button" href="/add-user?roleId=1">Add a new Patient</a>
			<br/>
			<a class="button" href="/add-user?roleId=2">Add a new Doctor</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>