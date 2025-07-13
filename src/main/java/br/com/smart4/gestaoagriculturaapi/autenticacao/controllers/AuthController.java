package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.LoginRequest;
import br.com.smart4.gestaoagriculturaapi.sistema.security.JwtAuthenticationResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenProvider tokenProvider;

	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping
	public ResponseEntity<?> autenticaUsuario(@Valid @RequestBody LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		HttpHeaders headers = new HttpHeaders();

		headers.add("x-access-token", jwt);

		List<String> allowedHeaders = new ArrayList<>();

		allowedHeaders.add("x-access-token");

		headers.setAccessControlAllowHeaders(allowedHeaders);

		return (ResponseEntity<?>) ResponseEntity.ok().headers(headers).body(new JwtAuthenticationResponse(jwt));
	}

}
