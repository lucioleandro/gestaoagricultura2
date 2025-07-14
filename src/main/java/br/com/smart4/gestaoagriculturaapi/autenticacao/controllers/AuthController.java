package br.com.smart4.gestaoagriculturaapi.autenticacao.controllers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.LoginRequest;
import br.com.smart4.gestaoagriculturaapi.sistema.security.JwtAuthenticationResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints related to user authentication and JWT generation")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;

	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping
	@Operation(
			summary = "Authenticate user and return JWT token",
			description = "Authenticates a user using username and password, and returns a JWT token in the response header and body"
	)
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest request) {
		// Autentica usuário com base nas credenciais
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Gera o token JWT
		String jwt = tokenProvider.generateToken(authentication);

		// Configura cabeçalhos para permitir que o frontend acesse o token
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-access-token", jwt);
		headers.add("Access-Control-Expose-Headers", "x-access-token");

		return ResponseEntity.ok().headers(headers).body(new JwtAuthenticationResponse(jwt));
	}
}
