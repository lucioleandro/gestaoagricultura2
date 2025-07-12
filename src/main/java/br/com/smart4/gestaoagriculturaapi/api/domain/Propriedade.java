package br.com.smart4.gestaoagriculturaapi.api.domain;


import br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumRegularizacaoFundiaria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_propriedade", schema = "agricultor")
public class Propriedade implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;
	
	private String nome;

	private String itr;

	private String incra;

	@Column(nullable = false)
	private String latitude;

	@Column(nullable = false)
	private String longitude;

	private Double areaTotal;

	private Double areaAgricola;

	private Double reservaLegal;

	private String tipoResidencia;
	
	@Enumerated(EnumType.STRING)
	private EnumRegularizacaoFundiaria regularizacaoFundiaria;

//  =========================================== RELACIONAMENTOS

	@ManyToOne
	@JoinColumn(name = "agricultor_id")
	private Agricultor agricultor;

//  =========================================== JUNÇÕES 1-1

	@OneToOne
	@JoinColumn(unique = true, name = "endereco_id")
	private Endereco endereco;

//  ===========================================

	public Propriedade() {
	}

	public Propriedade(Long id, int version, String nome, String itr, String incra, String latitude, String longitude,
			Double areaTotal, Double areaAgricola, Double reservaLegal, String tipoResidencia,
			EnumRegularizacaoFundiaria regularizacaoFundiaria, Agricultor agricultor, Endereco endereco) {
		this.id = id;
		this.version = version;
		this.nome = nome;
		this.itr = itr;
		this.incra = incra;
		this.latitude = latitude;
		this.longitude = longitude;
		this.areaTotal = areaTotal;
		this.areaAgricola = areaAgricola;
		this.reservaLegal = reservaLegal;
		this.tipoResidencia = tipoResidencia;
		this.regularizacaoFundiaria = regularizacaoFundiaria;
		this.agricultor = agricultor;
		this.endereco = endereco;
	}

	public Propriedade(String nome, String itr, String incra, String latitude, String longitude, Double areaTotal,
			Double areaAgricola, Double reservaLegal, String tipoResidencia,
			EnumRegularizacaoFundiaria regularizacaoFundiaria, Agricultor agricultor, Endereco endereco) {
		this.nome = nome;
		this.itr = itr;
		this.incra = incra;
		this.latitude = latitude;
		this.longitude = longitude;
		this.areaTotal = areaTotal;
		this.areaAgricola = areaAgricola;
		this.reservaLegal = reservaLegal;
		this.tipoResidencia = tipoResidencia;
		this.regularizacaoFundiaria = regularizacaoFundiaria;
		this.agricultor = agricultor;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getNome() {
		return nome;
	}

	public String getItr() {
		return itr;
	}

	public String getIncra() {
		return incra;
	}

	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Double getAreaTotal() {
		return areaTotal;
	}

	public Double getAreaAgricola() {
		return areaAgricola;
	}

	public Double getReservaLegal() {
		return reservaLegal;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public EnumRegularizacaoFundiaria getRegularizacaoFundiaria() {
		return regularizacaoFundiaria;
	}

	public Agricultor getAgricultor() {
		return agricultor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return "Propriedade [nome=" + nome + ", agricultor=" + agricultor + ", endereco=" + endereco + "]";
	}
	
}
