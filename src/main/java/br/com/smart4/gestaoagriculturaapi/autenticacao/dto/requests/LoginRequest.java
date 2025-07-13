package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests;


import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank
	private String userName;

	@NotBlank
	private String password;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
