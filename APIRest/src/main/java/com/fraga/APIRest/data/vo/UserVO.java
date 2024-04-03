package com.fraga.APIRest.data.vo;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

public class UserVO extends RepresentationModel <UserVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long key; 
	private String userName;
	private String fullName;
	private String password;
	private Boolean active;
	
	public UserVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public String toString() {
		return "UserVO [key=" + key + ", userName=" + userName + ", fullName=" + fullName + ", password=" + password
				+ ", active=" + active + "]";
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(active, fullName, key, password, userName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		return Objects.equals(active, other.active) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(key, other.key) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName);
	}
	
	

}
