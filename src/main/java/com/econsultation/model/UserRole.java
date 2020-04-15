package com.econsultation.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_USER_ROLE", schema="learn")
public class UserRole {

	@Id
	private Long roleId;
	private String roleName;
	private Date startDate;
	private Date endDate;
	private String access;
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY, targetEntity = User.class)
//	@JoinColumn(name = "userId", referencedColumnName = "userId")
//	private List<User> users = new ArrayList<User>();

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", roleName=" + roleName + ", startDate=" + startDate 
				//+ ", endDate="	+ endDate //+ ", users=" + users 
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, roleName, startDate); //,users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return  Objects.equals(roleId, other.roleId)
				&& Objects.equals(roleName, other.roleName) && Objects.equals(startDate, other.startDate);
				//&& Objects.equals(users, other.users);
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}




	
}
