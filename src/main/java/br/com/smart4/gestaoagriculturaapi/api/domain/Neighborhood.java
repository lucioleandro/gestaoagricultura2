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
@Table(name = "agro_neighborhood", schema = "smartagrodb")
public class Neighborhood implements Serializable {
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
	private City city;
	
	public Neighborhood() { }

	public Neighborhood(Long id, int version, String nome, String zona, City city) {
		this.id = id;
		this.version = version;
		this.nome = nome;
		this.zona = zona;
		this.city = city;
	}
	

	public Neighborhood(String nome, String zona, City city) {
		this.nome = nome;
		this.zona = zona;
		this.city = city;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	@Override
	public String toString() {
		return "Neighborhood [nome=" + nome + ", city=" + city + "]";
	}
	
}
