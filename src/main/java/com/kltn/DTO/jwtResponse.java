package com.kltn.DTO;

import com.kltn.Model.Role;

import java.util.Collection;

public class jwtResponse {
	private String token;
	private String type = "Bearer";

	public jwtResponse (String accessToken) {
	        this.token = accessToken;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}
}
