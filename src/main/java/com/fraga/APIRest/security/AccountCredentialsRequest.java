package com.fraga.APIRest.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O nome de usuário é obrigatório")
	@Size(min = 3, max = 50, message = "O nome de usuário deve ter entre 3 e 50 caracteres")
	private String username;

	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
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
