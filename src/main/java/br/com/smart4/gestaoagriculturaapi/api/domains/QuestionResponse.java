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
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_question_response", schema = "smartagrodb")
public class QuestionResponse implements Serializable {

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

	@Override
	public String toString() {
		return "ResponseQuestion [descricao=" + descricao + ", question=" + question + ", farmer=" + farmer
				+ "]";
	}
	
}
