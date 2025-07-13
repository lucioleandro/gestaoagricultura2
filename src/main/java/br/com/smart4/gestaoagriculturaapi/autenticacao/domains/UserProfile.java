package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

	@Override
	public String toString() {
		return "UsuarioPerfil [ativo=" + ativo + ", administrador=" + administrador + ", perfil=" + perfil
				+ ", usuario=" + usuario + "]";
	}
	
}
