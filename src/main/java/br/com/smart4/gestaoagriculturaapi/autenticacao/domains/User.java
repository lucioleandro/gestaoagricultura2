package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.EnumUserType;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
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

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String cpf;
	
	private String telefone;
	
	private String telefoneAlternativo;
	
	@Enumerated(EnumType.STRING)
	private EnumUserType tipo;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

	public User(String nome, String email, String login, String senha, EnumUserType tipo) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
	}

	public User() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefoneAlternativo() {
		return telefoneAlternativo;
	}
	
	public void setTelefoneAlternativo(String telefoneAlternativo) {
		this.telefoneAlternativo = telefoneAlternativo;
	}
	
	public EnumUserType getTipo() {
		return tipo;
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
