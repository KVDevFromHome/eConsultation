package com.econsultation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "TBL_USER", schema="learn")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 765495067273496403L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Size(min=3, message="First Name is Mandatory, enter minimum 3 characters!")
	private String firstName;
	private String lastName;
	
	@Size(min=5, message="Email is Mandatory, enter minimum 3 characters!")
	@Column(unique = true)
	private String primaryEmail;
	
	private String primaryMobile;
	
	
	//private long roleId;
	@OneToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private UserRole userRole;
	
	@Size(min=6, message="User Name is Mandatory, enter minimum 8 characters!")
	@Column(unique = true)
	private String userName;
	

	
	@Column(name="user_password")
	@Size(min=4, message="Password should be atleast 8 characters long!")
	private String password;
	
	private Date startDate;
	private Date endDate;

	protected User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getprimaryEmail() {
		return primaryEmail;
	}

	public void setprimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getPrimaryMobile() {
		return primaryMobile;
	}

	public void setPrimaryMobile(String primaryMobile) {
		this.primaryMobile = primaryMobile;
	}

//	public long getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(long roleId) {
//		this.roleId = roleId;
//	}

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

	public User(Long userId, String userName, String userPass,String firstName,
			String lastName, String primaryEmail, String primaryMobile, UserRole userRole, Date startDate) {//, Date endDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = userPass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryEmail = primaryEmail;
		this.primaryMobile = primaryMobile;
		this.userRole = userRole;
		this.startDate = startDate;
		//this.endDate = endDate;
	}
	
	public User( String userName, String userPass,String firstName,
			String lastName, String primaryEmail, String primaryMobile, UserRole userRole, Date startDate) {//, Date endDate) {
		super();
		this.userName = userName;
		this.password = userPass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryEmail = primaryEmail;
		this.primaryMobile = primaryMobile;
		this.userRole = userRole;
		this.startDate = startDate;
		//this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", primaryEmail=" + primaryEmail + ", primaryMobile=" + primaryMobile //+ ", role=" + roleId
				+ ", startDate=" + startDate + "]"; //", endDate=" + endDate + "]";
	}
//", userName=" + userName + 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((primaryEmail == null) ? 0 : primaryEmail.hashCode());
		result = prime * result + ((primaryMobile == null) ? 0 : primaryMobile.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (primaryEmail == null) {
			if (other.primaryEmail != null)
				return false;
		} else if (!primaryEmail.equals(other.primaryEmail))
			return false;
		if (primaryMobile == null) {
			if (other.primaryMobile != null)
				return false;
		} else if (!primaryMobile.equals(other.primaryMobile))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
		//this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}

	
	
