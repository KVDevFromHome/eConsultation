package com.econsultation.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name = "TBL_USER_CREDS", schema="learn")
public class UserCreds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userCredId;
	@Size(min=8, message="User Name is Mandatory, enter minimum 8 characters!")
	private String userName;
	private String pass;
	private Date startDate;
	private Date endDate;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;
	

	public Long getUserCredId() {
		return userCredId;
	}

	public void setUserCredId(Long userCredId) {
		this.userCredId = userCredId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserCreds [userCredId=" + userCredId + ", pass=" + pass + ", startDate=" + startDate + ", endDate="
				+ endDate + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, pass, startDate, user, userCredId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCreds other = (UserCreds) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(pass, other.pass)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(user, other.user)
				&& Objects.equals(userCredId, other.userCredId);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	

	
}
