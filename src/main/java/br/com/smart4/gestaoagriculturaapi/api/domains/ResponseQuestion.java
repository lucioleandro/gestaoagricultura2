package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "agro_response_question", schema = "smartagrodb")
public class ResponseQuestion implements Serializable {

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
	@JoinColumn(name = "question_id")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private Farmer farmer;

//  ===========================================

	public ResponseQuestion() {
	}

	public ResponseQuestion(String descricao, Question question) {
		this.descricao = descricao;
		this.question = question;
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

	public Question getQuestion() {
		return question;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}

	@Override
	public String toString() {
		return "ResponseQuestion [descricao=" + descricao + ", question=" + question + ", farmer=" + farmer
				+ "]";
	}
	
}
