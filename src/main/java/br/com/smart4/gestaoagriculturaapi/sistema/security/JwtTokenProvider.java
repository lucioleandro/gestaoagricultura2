package br.com.smart4.gestaoagriculturaapi.sistema.security;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.services.ProfileService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenProvider {

	private final SecretKey jwtSecret;

	private final long jwtExpirationInMs;

	public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret,
							@Value("${app.jwtExpirationInMs}") long jwtExpirationInMs, ProfileService profileService) {
		this.jwtSecret = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		this.jwtExpirationInMs = jwtExpirationInMs;
		this.profileService = profileService;
	}
	
	private final ProfileService profileService;

	public String generateToken(Authentication authentication) {
		User userPrincipal = (User) authentication.getPrincipal();

		OffsetDateTime expiryDate = OffsetDateTime.now().plus(jwtExpirationInMs, ChronoUnit.MILLIS);

		Map<String, Object> claims = new HashMap<>();
		
		List<ProfileResponse> perfis = profileService.findByIdUsuario(userPrincipal.getId());

		claims.put("id", userPrincipal.getId());
		claims.put("nome", userPrincipal.getNome());
		claims.put("login", userPrincipal.getLogin());
		claims.put("email", userPrincipal.getEmail());
		claims.put("perfis", perfis);

		String token = Jwts.builder().setSubject(new String(userPrincipal.getNome())).setClaims(claims)
				.setIssuedAt(Date.from(OffsetDateTime.now().toInstant()))
				.setExpiration(Date.from(expiryDate.toInstant())).signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();

		return token;
	}

	String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser().verifyWith(jwtSecret).build().parseClaimsJws(token).getBody();
		return String.valueOf(claims.get("login"));
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().verifyWith(jwtSecret).build().parseSignedClaims(authToken).getPayload();
			return true;
		} catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException |
				 SignatureException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return false;
	}

}
