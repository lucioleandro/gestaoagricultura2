package br.com.smart4.gestaoagriculturaapi.api.domains;


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
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "agro_property", schema = "farmer")
public class Property implements Serializable {

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
	private br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumLandRegularization regularizacaoFundiaria;

//  =========================================== RELACIONAMENTOS

	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private Farmer farmer;

//  =========================================== JUNÇÕES 1-1

	@OneToOne
	@JoinColumn(unique = true, name = "address_id")
	private Address address;

//  ===========================================

	public Property() {
	}

	public Property(Long id, int version, String nome, String itr, String incra, String latitude, String longitude,
                    Double areaTotal, Double areaAgricola, Double reservaLegal, String tipoResidencia,
                    br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumLandRegularization regularizacaoFundiaria, Farmer farmer, Address address) {
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
		this.farmer = farmer;
		this.address = address;
	}

	public Property(String nome, String itr, String incra, String latitude, String longitude, Double areaTotal,
                    Double areaAgricola, Double reservaLegal, String tipoResidencia,
                    br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumLandRegularization regularizacaoFundiaria, Farmer farmer, Address address) {
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
		this.farmer = farmer;
		this.address = address;
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

	public br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumLandRegularization getRegularizacaoFundiaria() {
		return regularizacaoFundiaria;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Property [nome=" + nome + ", farmer=" + farmer + ", address=" + address + "]";
	}
	
}
