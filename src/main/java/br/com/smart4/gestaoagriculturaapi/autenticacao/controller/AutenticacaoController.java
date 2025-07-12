package br.com.smart4.gestaoagriculturaapi.autenticacao.controller;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.LoginDTO;
import br.com.smart4.gestaoagriculturaapi.sistema.security.JwtAuthenticationResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/autenticacao")
@RestController
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/sigin")
	public ResponseEntity<?> autenticaUsuario(@Valid @RequestBody LoginDTO request) {
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
