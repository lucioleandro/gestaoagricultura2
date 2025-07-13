package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
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
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "agro_title_deed", schema = "smartagrodb")
public class TitleDeed implements Serializable {

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
	private TitleDeedEnum documento;
//  =========================================== RELACIONAMENTOS

	@ManyToOne
	private Property property;

//  ===========================================

	public TitleDeed() {
	}

	public TitleDeed(Long id, int version, String titulo, String observacao, LocalDateTime dataAdicao,
                     byte[] bytes, String extensao, TitleDeedEnum documento, Property property) {
		this.id = id;
		this.version = version;
		this.titulo = titulo;
		this.observacao = observacao;
		this.dataAdicao = dataAdicao;
		this.bytes = bytes;
		this.extensao = extensao;
		this.documento = documento;
		this.property = property;
	}

	public TitleDeed(String titulo, String observacao, LocalDateTime dataAdicao, byte[] bytes,
                     String extensao, TitleDeedEnum ducumento, Property property) {
		this.titulo = titulo;
		this.observacao = observacao;
		this.dataAdicao = dataAdicao;
		this.bytes = bytes;
		this.extensao = extensao;
		this.documento = ducumento;
		this.property = property;
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

	public TitleDeedEnum getDocumento() {
		return documento;
	}

	public Property getProperty() {
		return property;
	}

}
