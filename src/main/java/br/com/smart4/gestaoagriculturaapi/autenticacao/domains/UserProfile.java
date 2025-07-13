package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "aut_user_profile", schema = "smartagrodb")
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	private boolean ativo;

	private boolean administrador;

//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Profile perfil;

	@ManyToOne
	private User usuario;

//  ===========================================

	public UserProfile() { }

	public UserProfile(Long id, int version, boolean ativo, boolean administrador, Profile perfil, User usuario) {
		this.id = id;
		this.version = version;
		this.ativo = ativo;
		this.administrador = administrador;
		this.perfil = perfil;
		this.usuario = usuario;
	}

	public UserProfile(boolean ativo, boolean administrador, Profile perfil, User usuario) {
		this.ativo = ativo;
		this.administrador = administrador;
		this.perfil = perfil;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public Profile getPerfil() {
		return perfil;
	}

	public void setPerfil(Profile perfil) {
		this.perfil = perfil;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuarioPerfil [ativo=" + ativo + ", administrador=" + administrador + ", perfil=" + perfil
				+ ", usuario=" + usuario + "]";
	}
	
}
