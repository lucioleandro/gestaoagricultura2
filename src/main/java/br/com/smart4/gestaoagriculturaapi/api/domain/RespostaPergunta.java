package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_respostapergunta", schema = "agricultura")
public class RespostaPergunta implements Serializable {

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
	@JoinColumn(name = "pergunta_id")
	private Pergunta pergunta;
	
	@ManyToOne
	@JoinColumn(name = "agricultor_id")
	private Agricultor agricultor;

//  ===========================================

	public RespostaPergunta() {
	}

	public RespostaPergunta(String descricao, Pergunta pergunta) {
		this.descricao = descricao;
		this.pergunta = pergunta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public Agricultor getAgricultor() {
		return agricultor;
	}

	@Override
	public String toString() {
		return "RespostaPergunta [descricao=" + descricao + ", pergunta=" + pergunta + ", agricultor=" + agricultor
				+ "]";
	}
	
}
