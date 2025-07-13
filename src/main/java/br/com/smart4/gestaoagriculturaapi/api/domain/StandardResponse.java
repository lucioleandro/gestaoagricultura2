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
@Table(name = "agro_standard_response")
public class StandardResponse implements Serializable {

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
	Question question;

//  ===========================================

	public StandardResponse() {
	}

	public StandardResponse(Long id, int version, String descricao, Question question) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.question = question;
	}

	public StandardResponse(String descricao, Question question) {
		this.descricao = descricao;
		this.question = question;
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

	public Question getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "StandardResponse [descricao=" + descricao + ", question=" + question + "]";
	}
	
}
