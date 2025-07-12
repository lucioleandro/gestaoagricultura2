package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;


@Entity
@Table(name = "agri_imagem_produto", schema = "agricultura")
public class ImagemProduto implements Serializable {

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
	private Produto produto;

//  ===========================================

	public ImagemProduto() {
	}

	public ImagemProduto(Long id, int version, byte[] arquivo, String extensao,
			Produto produto) {
		this.id = id;
		this.version = version;
		this.arquivo = arquivo;
		this.extensao = extensao;
		this.produto = produto;
	}

	public ImagemProduto(byte[] arquivo,  String extensao, Produto produto) {
		this.arquivo = arquivo;
		this.extensao = extensao;
		this.produto = produto;
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

	public Produto getProduto() {
		return produto;
	}

}
