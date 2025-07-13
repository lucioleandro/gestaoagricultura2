package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.UserTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aut_user", schema = "smartagrodb",
uniqueConstraints = { 
		@UniqueConstraint(name = "login_unique", columnNames = {"login"}),
		@UniqueConstraint(name = "cpf_unique", columnNames = {"cpf"})
})
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Setter
	@Column(nullable = false)
	private String nome;

	@Setter
	@Column(nullable = false)
	private String email;

	@Setter
	@Column(nullable = false)
	private String login;

	@Setter
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String cpf;
	
	private String telefone;
	
	private String telefoneAlternativo;
	
	@Enumerated(EnumType.STRING)
	private UserTypeEnum tipo;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", version=" + version + ", nome=" + nome + ", email=" + email + ", login=" + login
				+ ", senha=" + senha + ", tipo=" + tipo + "]";
	}
	
}
