package br.com.smart4.gestaoagriculturaapi.sistema.security;

public class JwtAuthenticationResponse {

	private final String accessToken;

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return "Bearer";
	}

}
