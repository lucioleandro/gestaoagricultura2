package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;


@Entity
@Table(name = "agro_product", schema = "smartagrodb")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	private String descricao;

	private String unidadeMedida;

	private String siglaUnidadeMedida;

//  =========================================== RELACIONAMENTOS

//  ===========================================

	public Product() {
	}

	public Product(Long id, int version, String descricao) {
		this.id = id;
		this.version = version;
	}

	public Product(String descricao, String unidadeMedida, String siglaUnidadeMedida) {
		this.unidadeMedida = unidadeMedida;
		this.siglaUnidadeMedida = siglaUnidadeMedida;
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

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public String getSiglaUnidadeMedida() {
		return siglaUnidadeMedida;
	}

	@Override
	public String toString() {
		return "Product [descricao=" + descricao + "]";
	}
	
}
