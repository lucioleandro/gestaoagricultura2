package br.com.smart4.gestaoagriculturaapi.api.domains;

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
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "agro_agriculture_activity", schema = "smartagrodb")
public class AgricultureActivity implements Serializable {

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
	private br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumIrrigationMethod metodoIrrigacao;
	
	@Enumerated(EnumType.STRING)
	private br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumWaterSource fonteAgua;
//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Property property;

	@ManyToOne
	private Product product;

//  ===========================================

	public AgricultureActivity() {
	}

	public AgricultureActivity(Long id, int version, LocalDate dataPlantio, String variedade, double areaPlantada,
                               Integer quantidadePlantas, double producaoAnual, br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumIrrigationMethod metodoIrrigacao,
                               br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumWaterSource fonteAgua, Property property, Product product) {
		this.id = id;
		this.version = version;
		this.dataPlantio = dataPlantio;
		this.variedade = variedade;
		this.areaPlantada = areaPlantada;
		this.quantidadePlantas = quantidadePlantas;
		this.producaoAnual = producaoAnual;
		this.metodoIrrigacao = metodoIrrigacao;
		this.fonteAgua = fonteAgua;
		this.property = property;
		this.product = product;
	}

	public AgricultureActivity(Product product, LocalDate dataPlantio, String variedade, double areaPlantada,
                               Integer quantidadePlantas, double producaoAnual, br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumIrrigationMethod metodoIrrigacao,
                               br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumWaterSource fonteAgua, Property property) {
		this.product = product;
		this.dataPlantio = dataPlantio;
		this.variedade = variedade;
		this.areaPlantada = areaPlantada;
		this.quantidadePlantas = quantidadePlantas;
		this.producaoAnual = producaoAnual;
		this.metodoIrrigacao = metodoIrrigacao;
		this.fonteAgua = fonteAgua;
		this.property = property;
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

	public br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumIrrigationMethod getMetodoIrrigacao() {
		return metodoIrrigacao;
	}

	public br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumWaterSource getFonteAgua() {
		return fonteAgua;
	}

	public Property getProperty() {
		return property;
	}

	public Product getProduct() {
		return product;
	}
	
}
