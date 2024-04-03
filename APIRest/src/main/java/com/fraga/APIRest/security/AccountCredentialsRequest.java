package com.fraga.APIRest.security;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Nome do usuário não pode ser vazio")
	private String username;
	@NotBlank(message = "Password do usuário não pode ser vazio")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountCredentialsRequest other = (AccountCredentialsRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

}
