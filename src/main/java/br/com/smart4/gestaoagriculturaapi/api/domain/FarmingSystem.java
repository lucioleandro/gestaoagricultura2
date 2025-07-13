package br.com.smart4.gestaoagriculturaapi.api.domain;


import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agro_farming_system", schema = "smartagrodb")
public class FarmingSystem implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Basic
	@Id
	private String descricao;

	@Enumerated(EnumType.STRING)
	br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumFarmSystem ramoAtividade;

//  =========================================== RELACIONAMENTOS

//  ===========================================

	public FarmingSystem() {
	}

	public FarmingSystem(Long id, int version, String descricao, br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumFarmSystem ramoAtividade) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.ramoAtividade = ramoAtividade;
	}

	public FarmingSystem(String descricao, br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumFarmSystem ramoAtividade) {
		this.descricao = descricao;
		this.ramoAtividade = ramoAtividade;
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

	public br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumFarmSystem getRamoAtividade() {
		return ramoAtividade;
	}

	@Override
	public String toString() {
		return "FarmingSystem [descricao=" + descricao + ", ramoAtividade=" + ramoAtividade + "]";
	}
	
	
}
