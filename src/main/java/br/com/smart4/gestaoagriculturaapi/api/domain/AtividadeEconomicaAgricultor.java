package br.com.smart4.gestaoagriculturaapi.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "agri_atividade_economica_agricultor", schema = "agricultura")
public class AtividadeEconomicaAgricultor implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	private boolean principal;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataInicial;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataFinal;

//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Agricultor agricultor;

	@ManyToOne
	private Propriedade propriedade;

	@ManyToOne
	private AtividadeEconomica atividadeEconomica;

//  ===========================================

	public AtividadeEconomicaAgricultor() {
	}

	public AtividadeEconomicaAgricultor(Long id, int version, boolean principal, LocalDate dataInicial,
			LocalDate dataFinal, Agricultor agricultor, Propriedade propriedade,
			AtividadeEconomica atividadeEconomica) {
		this.id = id;
		this.version = version;
		this.principal = principal;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.agricultor = agricultor;
		this.propriedade = propriedade;
		this.atividadeEconomica = atividadeEconomica;
	}

	public AtividadeEconomicaAgricultor(boolean principal, LocalDate dataInicial, LocalDate dataFinal,
			Agricultor agricultor, Propriedade propriedade, AtividadeEconomica atividadeEconomica) {
		this.principal = principal;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.agricultor = agricultor;
		this.propriedade = propriedade;
		this.atividadeEconomica = atividadeEconomica;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public Agricultor getAgricultor() {
		return agricultor;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public AtividadeEconomica getAtividadeEconomica() {
		return atividadeEconomica;
	}

}
