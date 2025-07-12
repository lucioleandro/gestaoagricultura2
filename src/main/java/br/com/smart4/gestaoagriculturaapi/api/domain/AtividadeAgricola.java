package br.com.smart4.gestaoagriculturaapi.api.domain;

import br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumFonteAgua;
import br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumMetodoIrrigacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "agri_atividade_agricola", schema = "agricultura")
public class AtividadeAgricola implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataPlantio;

	private String variedade;

	private double areaPlantada;

	private Integer quantidadePlantas;

	private double producaoAnual;

	@Enumerated(EnumType.STRING)
	private EnumMetodoIrrigacao metodoIrrigacao;
	
	@Enumerated(EnumType.STRING)
	private EnumFonteAgua fonteAgua;
//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Propriedade propriedade;

	@ManyToOne
	private Produto produto;

//  ===========================================

	public AtividadeAgricola() {
	}

	public AtividadeAgricola(Long id, int version, LocalDate dataPlantio, String variedade, double areaPlantada,
			Integer quantidadePlantas, double producaoAnual, EnumMetodoIrrigacao metodoIrrigacao,
			EnumFonteAgua fonteAgua, Propriedade propriedade, Produto produto) {
		this.id = id;
		this.version = version;
		this.dataPlantio = dataPlantio;
		this.variedade = variedade;
		this.areaPlantada = areaPlantada;
		this.quantidadePlantas = quantidadePlantas;
		this.producaoAnual = producaoAnual;
		this.metodoIrrigacao = metodoIrrigacao;
		this.fonteAgua = fonteAgua;
		this.propriedade = propriedade;
		this.produto = produto;
	}

	public AtividadeAgricola(Produto produto, LocalDate dataPlantio, String variedade, double areaPlantada,
			Integer quantidadePlantas, double producaoAnual, EnumMetodoIrrigacao metodoIrrigacao,
			EnumFonteAgua fonteAgua, Propriedade propriedade) {
		this.produto = produto;
		this.dataPlantio = dataPlantio;
		this.variedade = variedade;
		this.areaPlantada = areaPlantada;
		this.quantidadePlantas = quantidadePlantas;
		this.producaoAnual = producaoAnual;
		this.metodoIrrigacao = metodoIrrigacao;
		this.fonteAgua = fonteAgua;
		this.propriedade = propriedade;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public LocalDate getDataPlantio() {
		return dataPlantio;
	}

	public String getVariedade() {
		return variedade;
	}

	public double getAreaPlantada() {
		return areaPlantada;
	}

	public Integer getQuantidadePlantas() {
		return quantidadePlantas;
	}

	public double getProducaoAnual() {
		return producaoAnual;
	}

	public EnumMetodoIrrigacao getMetodoIrrigacao() {
		return metodoIrrigacao;
	}

	public EnumFonteAgua getFonteAgua() {
		return fonteAgua;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public Produto getProduto() {
		return produto;
	}
	
}
