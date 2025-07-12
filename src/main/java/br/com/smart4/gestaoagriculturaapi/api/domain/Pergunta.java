package br.com.smart4.gestaoagriculturaapi.api.domain;


import br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumTipoPergunta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_pergunta", schema = "agricultura")
public class Pergunta implements Serializable {

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
	private EnumTipoPergunta tipoPergunta;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

	public Pergunta() {
	}

	public Pergunta(Long id, int version, String descricao, Boolean ativa, EnumTipoPergunta tipoPergunta, Boolean obrigatoria) {
		this.id = id;
		this.version = version;
		this.descricao = descricao;
		this.ativa = ativa;
		this.tipoPergunta = tipoPergunta;
		this.obrigatoria = obrigatoria;
	}

	public Pergunta(String descricao, Boolean ativa, EnumTipoPergunta tipoPergunta, Boolean obrigatoria) {
		this.descricao = descricao;
		this.ativa = ativa;
		this.tipoPergunta = tipoPergunta;
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

	public EnumTipoPergunta getTipoPergunta() {
		return tipoPergunta;
	}
	
	public Boolean getObrigatoria() {
		return obrigatoria;
	}

	@Override
	public String toString() {
		return "Pergunta [descricao=" + descricao + ", tipoPergunta=" + tipoPergunta + "]";
	}
	
}
