package br.com.smart4.gestaoagriculturaapi.api.domain;

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

import java.io.Serializable;

@Entity
@Table(name = "agri_endereco", schema = "agricultura")
public class Endereco implements Serializable {
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
	private TipoLogradouro tipoLogradouro;
	
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;
	
	@ManyToOne
	@JoinColumn(name = "bairro_id")
	private Bairro bairro;
	
	public Endereco() { }

	public Endereco(Long id, int version, String logradouro, String numero, String cep, String complemento,
			TipoLogradouro tipoLogradouro, Municipio municipio, Bairro bairro) {
		this.id = id;
		this.version = version;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.tipoLogradouro = tipoLogradouro;
		this.municipio = municipio;
		this.bairro = bairro;
	}
	


	public Endereco(String logradouro, String numero, String cep, String complemento, TipoLogradouro tipoLogradouro,
			Municipio municipio, Bairro bairro) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.tipoLogradouro = tipoLogradouro;
		this.municipio = municipio;
		this.bairro = bairro;
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
		if (bairro != null)
			resultado = resultado + ", " + bairro.getNome();

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

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

}
