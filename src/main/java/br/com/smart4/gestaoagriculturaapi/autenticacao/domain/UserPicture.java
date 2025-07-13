package br.com.smart4.gestaoagriculturaapi.autenticacao.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "aut_user_picture", schema = "smartagrodb")
public class UserPicture implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Lob
	private String fotoPerfil;

//  =========================================== RELACIONAMENTOS

	@OneToOne
	@JoinColumn(unique = true)
	private User usuario;

//  ===========================================

	public UserPicture() {
	}

	public UserPicture(Long id, int version, String fotoPerfil, User usuario) {
		this.id = id;
		this.version = version;
		this.fotoPerfil = fotoPerfil;
		this.usuario = usuario;
	}

	public UserPicture(String fotoPerfil, User usuario) {
		this.fotoPerfil = fotoPerfil;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public User getUsuario() {
		return usuario;
	}
	
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

}
