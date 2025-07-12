package br.com.smart4.gestaoagriculturaapi.autenticacao.domain;


import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

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
