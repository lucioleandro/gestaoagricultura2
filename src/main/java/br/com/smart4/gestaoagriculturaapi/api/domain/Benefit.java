package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "agro_benefit", schema = "smartagrodb")
public class Benefit implements Serializable {

	private static final long serialVersionUID = 1L;
	
//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Version
	private int version;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private LocalDateTime dataConcedimento;

//  =========================================== JUNÇÕES 1-N
	@ManyToOne
	private Farmer beneficiado;

//  ===========================================
	
	public Benefit() {}

	public Benefit(Long id, int version, String descricao, LocalDateTime dataConcedimento, Farmer beneficiado) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.dataConcedimento = dataConcedimento;
		this.beneficiado = beneficiado;
	}

	
	
	public Benefit(String descricao, LocalDateTime dataConcedimento, Farmer beneficiado) {
		this.descricao = descricao;
		this.dataConcedimento = dataConcedimento;
		this.beneficiado = beneficiado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataConcedimento() {
		return dataConcedimento;
	}

	public Farmer getBeneficiado() {
		return beneficiado;
	}

	@Override
	public String toString() {
		return "Benefit [descricao=" + descricao + "]";
	}
	
}
