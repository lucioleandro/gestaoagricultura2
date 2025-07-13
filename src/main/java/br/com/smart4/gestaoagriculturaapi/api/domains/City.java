package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "agro_city", schema = "smartagrodb")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Basic
	private String nome;

	@Basic
	private Integer cadastroUnico;

	@Column(length = 2)
	private String uf;
	
	
	public City() {}

	public City(Long id, int version, String nome, Integer cadastroUnico, String uf) {
		this.id = id;
		this.version = version;
		this.nome = nome;
		this.cadastroUnico = cadastroUnico;
		this.uf = uf;
	}
	
	

	public City(String nome, Integer cadastroUnico, String uf) {
		this.nome = nome;
		this.cadastroUnico = cadastroUnico;
		this.uf = uf;
	}


	public int compare(Object o1, Object o2) {
		return 0;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCadastroUnico() {
		return cadastroUnico;
	}

	public void setCadastroUnico(Integer cadastroUnico) {
		this.cadastroUnico = cadastroUnico;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public String toString() {
		return nome + " - " + uf.toString();
	}
	
}
