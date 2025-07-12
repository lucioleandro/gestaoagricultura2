package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_bairro", schema = "agricultura")
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Basic
	private String nome;

	@Basic
	private String zona;
	
	@ManyToOne
	private Municipio municipio;
	
	public Bairro() { }

	public Bairro(Long id, int version, String nome, String zona, Municipio municipio) {
		this.id = id;
		this.version = version;
		this.nome = nome;
		this.zona = zona;
		this.municipio = municipio;
	}
	

	public Bairro(String nome, String zona, Municipio municipio) {
		this.nome = nome;
		this.zona = zona;
		this.municipio = municipio;
	}



	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	
	@Override
	public String toString() {
		return "Bairro [nome=" + nome + ", municipio=" + municipio + "]";
	}
	
}
