package com.fraga.APIRest.security;

import com.fraga.APIRest.data.model.Permission;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class AccountCredentialsResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String username;
	private Token token;
	private LocalDateTime dataHoraAutenticacao;
	private List<Permission> permissoes;

	public AccountCredentialsResponse(String username, Token token, LocalDateTime dataHoraAutenticacao, List<Permission> permissoes) {
		this.username = username;
		this.token = token;
		this.dataHoraAutenticacao = dataHoraAutenticacao;
		this.permissoes = permissoes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public LocalDateTime getDataHoraAutenticacao() {
		return dataHoraAutenticacao;
	}

	public void setDataHoraAutenticacao(LocalDateTime dataHoraAutenticacao) {
		this.dataHoraAutenticacao = dataHoraAutenticacao;
	}

	public List<Permission> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permission> permissoes) {
		this.permissoes = permissoes;
	}
}
