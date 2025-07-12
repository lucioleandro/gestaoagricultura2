package br.com.smart4.gestaoagriculturaapi.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "agri_documentos_propriedade", schema = "agricultura")
public class DocumentoPropriedade implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	private String titulo;

	@Column(length = 2000)
	private String observacao;

	private LocalDateTime dataAdicao;

	@Lob
	@JsonIgnore
	private byte[] bytes;

	private String extensao;

	@Enumerated(EnumType.STRING)
	private EnumDocumentosPropriedade documento;
//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Propriedade propriedade;

//  ===========================================

	public DocumentoPropriedade() {
	}

	public DocumentoPropriedade(Long id, int version, String titulo, String observacao, LocalDateTime dataAdicao,
			byte[] bytes, String extensao, EnumDocumentosPropriedade documento, Propriedade propriedade) {
		this.id = id;
		this.version = version;
		this.titulo = titulo;
		this.observacao = observacao;
		this.dataAdicao = dataAdicao;
		this.bytes = bytes;
		this.extensao = extensao;
		this.documento = documento;
		this.propriedade = propriedade;
	}

	public DocumentoPropriedade(String titulo, String observacao, LocalDateTime dataAdicao, byte[] bytes,
			String extensao, EnumDocumentosPropriedade ducumento, Propriedade propriedade) {
		this.titulo = titulo;
		this.observacao = observacao;
		this.dataAdicao = dataAdicao;
		this.bytes = bytes;
		this.extensao = extensao;
		this.documento = ducumento;
		this.propriedade = propriedade;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getObservacao() {
		return observacao;
	}

	public LocalDateTime getDataAdicao() {
		return dataAdicao;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public String getExtensao() {
		return extensao;
	}

	public EnumDocumentosPropriedade getDocumento() {
		return documento;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

}
