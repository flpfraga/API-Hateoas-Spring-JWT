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

	public AccountCredentialsRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public AccountCredentialsRequest() {
	}

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


}
