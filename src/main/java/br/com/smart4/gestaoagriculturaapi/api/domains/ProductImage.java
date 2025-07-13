package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "agro_product_image", schema = "smartagrodb")
public class ProductImage implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Lob
	private byte[] arquivo;
	
	private String extensao;

//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Product product;

//  ===========================================

	public ProductImage() {
	}

	public ProductImage(Long id, int version, byte[] arquivo, String extensao,
			Product product) {
		this.id = id;
		this.version = version;
		this.arquivo = arquivo;
		this.extensao = extensao;
		this.product = product;
	}

	public ProductImage(byte[] arquivo,  String extensao, Product product) {
		this.arquivo = arquivo;
		this.extensao = extensao;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public String getExtensao() {
		return extensao;
	}

	public Product getProduct() {
		return product;
	}

}
