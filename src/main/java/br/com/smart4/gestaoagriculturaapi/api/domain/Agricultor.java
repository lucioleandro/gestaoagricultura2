package br.com.smart4.gestaoagriculturaapi.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "agri_agricultor", schema = "agricultura",
		uniqueConstraints = { @UniqueConstraint(name = "cpf_unique", columnNames = {"cpf"})})
public class Agricultor implements Serializable {

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

	public Agricultor() {
	}

	public Agricultor(Long id, int version, String nome, String cpf, String rg, String orgaoExpeditor, String apelido,
			LocalDate dataNascimento) {
		this.id = id;
		this.version = version;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpeditor = orgaoExpeditor;
		this.apelido = apelido;
		this.dataNascimento = dataNascimento;
	}


	public Agricultor(String nome, String cpf, String rg, String orgaoExpeditor, String apelido,
			LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpeditor = orgaoExpeditor;
		this.apelido = apelido;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getOrgaoExpeditor() {	
		return orgaoExpeditor;
	}

	public String getApelido() {
		return apelido;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	@Override
	public String toString() {
		return "Agricultor [nome=" + nome + ", apelido=" + apelido
				+ "]";
	}

}
