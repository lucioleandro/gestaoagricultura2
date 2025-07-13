package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "aut_permission", schema = "smartagrodb")
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;
	
//  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    
    private String componente;
    
    private boolean ativo;
    
    private boolean atalho;
    
    private boolean somenteLeitura;
    
//  =========================================== RELACIONAMENTOS
    
    @ManyToOne
    private Profile perfil;
    
//  ===========================================
    
    public Permission() { }

	public Permission(Long id, int version, String componente, boolean ativo, boolean atalho, boolean somenteLeitura,
					  boolean controle1, boolean controle2, boolean controle3, boolean controle4, boolean controle5, Profile perfil) {
		this.id = id;
		this.version = version;
		this.componente = componente;
		this.ativo = ativo;
		this.atalho = atalho;
		this.somenteLeitura = somenteLeitura;
		this.perfil = perfil;
	}

	public Permission(String componente, boolean ativo, boolean atalho, boolean somenteLeitura,
					  boolean controle1, boolean controle2, boolean controle3, boolean controle4, boolean controle5,
					  Profile perfil) {
		this.componente = componente;
		this.ativo = ativo;
		this.atalho = atalho;
		this.somenteLeitura = somenteLeitura;
		this.perfil = perfil;
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

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtalho() {
		return atalho;
	}

	public void setAtalho(boolean atalho) {
		this.atalho = atalho;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	@JsonIgnore
	@JsonProperty(access = Access.WRITE_ONLY)
	public Profile getPerfil() {
		return perfil;
	}

	public void setPerfil(Profile perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Permissao [componente=" + componente + ", ativo=" + ativo + ", perfil=" + perfil + "]";
	}
    
}
