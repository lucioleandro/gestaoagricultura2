package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.StreetType;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "agro_address", schema = "smartagrodb")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Version
	private int version;
	
	@Basic
	private String logradouro;
	
	@Basic
	private String numero;
	
	@Basic
	private String cep;
	@Basic
	private String complemento;
	
	@Enumerated(EnumType.STRING)
	private StreetType tipoLogradouro;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "neighborhood_id")
	private Neighborhood neighborhood;
	
	public Address() { }

	public Address(Long id, int version, String logradouro, String numero, String cep, String complemento,
			StreetType tipoLogradouro, City city, Neighborhood neighborhood) {
		this.id = id;
		this.version = version;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.tipoLogradouro = tipoLogradouro;
		this.city = city;
		this.neighborhood = neighborhood;
	}
	


	public Address(String logradouro, String numero, String cep, String complemento, StreetType tipoLogradouro,
			City city, Neighborhood neighborhood) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.tipoLogradouro = tipoLogradouro;
		this.city = city;
		this.neighborhood = neighborhood;
	}


	public String toString() {
		String resultado = "";

		if (tipoLogradouro != null)
			resultado = resultado + tipoLogradouro.toString() + " ";
		if (logradouro != null)
			resultado = resultado + logradouro;
		if (numero != null)
			resultado = resultado + ", N " + numero + ". ";
		if (complemento != null)
			resultado = resultado + complemento;
		if (neighborhood != null)
			resultado = resultado + ", " + neighborhood.getNome();

		resultado = resultado + ".";
		return resultado;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public StreetType getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(StreetType tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

}
