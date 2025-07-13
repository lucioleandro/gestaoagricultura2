package br.com.smart4.gestaoagriculturaapi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_farmer", schema = "smartagrodb",
		uniqueConstraints = { @UniqueConstraint(name = "cpf_unique", columnNames = {"cpf"})})
public class Farmer implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String rg;
	
	@Column(name = "orgao_expeditor")
	private String orgaoExpeditor;
	
	private String apelido;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

	@Override
	public String toString() {
		return "Farmer [nome=" + nome + ", apelido=" + apelido
				+ "]";
	}

}
