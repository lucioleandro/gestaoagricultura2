package br.com.smart4.gestaoagriculturaapi.api.domains;


import br.com.smart4.gestaoagriculturaapi.api.domains.enums.QuestionTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "agro_question", schema = "smartagrodb")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Boolean ativa;
	
	@Column(nullable = false)
	private Boolean obrigatoria;

	@Enumerated(EnumType.STRING)
	private QuestionTypeEnum tipoQuestion;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

	public Question() {
	}

	public Question(Long id, int version, String descricao, Boolean ativa, QuestionTypeEnum tipoQuestion, Boolean obrigatoria) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.ativa = ativa;
		this.tipoQuestion = tipoQuestion;
		this.obrigatoria = obrigatoria;
	}

	public Question(String descricao, Boolean ativa, QuestionTypeEnum tipoQuestion, Boolean obrigatoria) {
		this.descricao = descricao;
		this.ativa = ativa;
		this.tipoQuestion = tipoQuestion;
		this.obrigatoria = obrigatoria;
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

	public Boolean getAtiva() {
		return ativa;
	}

	public QuestionTypeEnum getTipoQuestion() {
		return tipoQuestion;
	}
	
	public Boolean getObrigatoria() {
		return obrigatoria;
	}

	@Override
	public String toString() {
		return "Question [descricao=" + descricao + ", tipoQuestion=" + tipoQuestion + "]";
	}
	
}
