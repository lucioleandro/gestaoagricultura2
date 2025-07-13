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
@Table(name = "agro_economic_activity_farmer", schema = "smartagrodb")
public class EconomicActivityFarmer implements Serializable {

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
	private Farmer farmer;

	@ManyToOne
	private Property property;

	@ManyToOne
	private EconomicActivity economicActivity;

//  ===========================================

	public EconomicActivityFarmer() {
	}

	public EconomicActivityFarmer(Long id, int version, boolean principal, LocalDate dataInicial,
			LocalDate dataFinal, Farmer farmer, Property property,
			EconomicActivity economicActivity) {
		this.id = id;
		this.version = version;
		this.principal = principal;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.farmer = farmer;
		this.property = property;
		this.economicActivity = economicActivity;
	}

	public EconomicActivityFarmer(boolean principal, LocalDate dataInicial, LocalDate dataFinal,
			Farmer farmer, Property property, EconomicActivity economicActivity) {
		this.principal = principal;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.farmer = farmer;
		this.property = property;
		this.economicActivity = economicActivity;
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

	public Farmer getFarmer() {
		return farmer;
	}

	public Property getProperty() {
		return property;
	}

	public EconomicActivity getEconomicActivity() {
		return economicActivity;
	}

}
