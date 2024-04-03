package com.fraga.APIRest.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class TokenVO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String username;
	private boolean aunthenticated;
	private Date created;
	private Date expiration;
	private String  accessToken;
	private String  refreshToken;
	
	public TokenVO() {
		
	}

	public TokenVO(String username, boolean aunthenticated, Date created, Date expiration, String accessToken,
			String refreshToken) {
		super();
		this.username = username;
		this.aunthenticated = aunthenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAunthenticated() {
		return aunthenticated;
	}

	public void setAunthenticated(boolean aunthenticated) {
		this.aunthenticated = aunthenticated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
