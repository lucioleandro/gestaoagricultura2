package br.com.smart4.gestaoagriculturaapi.autenticacao.domain;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.enums.EnumTipoUsuario;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "aut_usuario", schema = "agricultura",
uniqueConstraints = { 
		@UniqueConstraint(name = "login_unique", columnNames = {"login"}),
		@UniqueConstraint(name = "cpf_unique", columnNames = {"cpf"})
})
public class Usuario implements UserDetails {

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
	private EnumTipoUsuario tipo;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

	public Usuario(String nome, String email, String login, String senha, EnumTipoUsuario tipo) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Usuario() {
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
	
	public EnumTipoUsuario getTipo() {
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
