package br.com.smart4.gestaoagriculturaapi.autenticacao.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "aut_usuarioperfil", schema = "agricultura")
public class UsuarioPerfil implements Serializable {

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
	private Perfil perfil;

	@ManyToOne
	private Usuario usuario;

//  ===========================================

	public UsuarioPerfil() { }

	public UsuarioPerfil(Long id, int version, boolean ativo, boolean administrador, Perfil perfil, Usuario usuario) {
		this.id = id;
		this.version = version;
		this.ativo = ativo;
		this.administrador = administrador;
		this.perfil = perfil;
		this.usuario = usuario;
	}

	public UsuarioPerfil(boolean ativo, boolean administrador, Perfil perfil, Usuario usuario) {
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuarioPerfil [ativo=" + ativo + ", administrador=" + administrador + ", perfil=" + perfil
				+ ", usuario=" + usuario + "]";
	}
	
}
