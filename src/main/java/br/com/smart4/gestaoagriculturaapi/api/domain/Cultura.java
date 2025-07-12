package br.com.smart4.gestaoagriculturaapi.api.domain;


import br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumRamoAtividade;
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
@Table(name = "agri_cultura", schema = "agricultura")
public class Cultura implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Basic
	@Id
	private String descricao;

	@Enumerated(EnumType.STRING)
	EnumRamoAtividade ramoAtividade;

//  =========================================== RELACIONAMENTOS

//  ===========================================

	public Cultura() {
	}

	public Cultura(Long id, int version, String descricao, EnumRamoAtividade ramoAtividade) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.ramoAtividade = ramoAtividade;
	}

	public Cultura(String descricao, EnumRamoAtividade ramoAtividade) {
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

	public EnumRamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	@Override
	public String toString() {
		return "Cultura [descricao=" + descricao + ", ramoAtividade=" + ramoAtividade + "]";
	}
	
	
}
