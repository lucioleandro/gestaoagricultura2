package br.com.smart4.gestaoagriculturaapi.sistema.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "system_compatible", schema = "smartagrodb")
public class Compatible implements Serializable {

	private static final long serialVersionUID = 1L;

	//  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    
    private Integer codSistema;
    
    private String senhaDeLiberacao;
    
    private String versaoLiberada;

//  ===========================================
    
    public Compatible() { }

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

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getSenhaDeLiberacao() {
		return senhaDeLiberacao;
	}

	public void setSenhaDeLiberacao(String senhaDeLiberacao) {
		this.senhaDeLiberacao = senhaDeLiberacao;
	}

	public String getVersaoLiberada() {
		return versaoLiberada;
	}

	public void setVersaoLiberada(String versaoLiberada) {
		this.versaoLiberada = versaoLiberada;
	}

	@Override
	public String toString() {
		return "Compativeis [senhaDeLiberacao=" + senhaDeLiberacao + ", versaoLiberada=" + versaoLiberada + "]";
	}
    
    
}
