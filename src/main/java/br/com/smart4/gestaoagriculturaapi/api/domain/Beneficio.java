package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "agri_beneficio", schema = "agricultura")
public class Beneficio implements Serializable {

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

	private Agricultor beneficiado;

//  ===========================================
	
	public Beneficio() {}

	public Beneficio(Long id, int version, String descricao, LocalDateTime dataConcedimento, Agricultor beneficiado) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.dataConcedimento = dataConcedimento;
		this.beneficiado = beneficiado;
	}

	
	
	public Beneficio(String descricao, LocalDateTime dataConcedimento, Agricultor beneficiado) {
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

	public Agricultor getBeneficiado() {
		return beneficiado;
	}

	@Override
	public String toString() {
		return "Beneficio [descricao=" + descricao + "]";
	}
	
}
