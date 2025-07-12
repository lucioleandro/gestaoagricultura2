package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_respostapadrao")
public class RespostaPadrao implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;

	@Column(nullable = false)
	private String descricao;

//  =========================================== JUNÇÕES 1-1

	@ManyToOne
	Pergunta pergunta;

//  ===========================================

	public RespostaPadrao() {
	}

	public RespostaPadrao(Long id, int version, String descricao, Pergunta pergunta) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.pergunta = pergunta;
	}

	public RespostaPadrao(String descricao, Pergunta pergunta) {
		this.descricao = descricao;
		this.pergunta = pergunta;
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

	public Pergunta getPergunta() {
		return pergunta;
	}

	@Override
	public String toString() {
		return "RespostaPadrao [descricao=" + descricao + ", pergunta=" + pergunta + "]";
	}
	
}
