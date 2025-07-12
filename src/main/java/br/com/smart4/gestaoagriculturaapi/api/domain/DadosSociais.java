package br.com.smart4.gestaoagriculturaapi.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_dados_sociais", schema = "agricultura")
public class DadosSociais implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;

	@Column(nullable = false)
	private String apelido;

	@Column(nullable = false)
	private String mae;

	@Column(nullable = false)
	private String pai;

	@Column(nullable = false)
	private EstadoCivil estadoCivil;

	@Column(name = "conjugue")
	private String nomeConjugue;

//  =========================================== JUNÇÕES 1-1

	@OneToOne
	@JoinColumn(unique = true, name = "agricultor_id")
	private Agricultor agricultor;

//  ===========================================

	public DadosSociais() {
	}

	public DadosSociais(Long id, int version, String apelido, String mae, String pai, EstadoCivil estadoCivil,
			String nomeConjugue, Agricultor agricultor) {
		this.id = id;
		this.version = version;
		this.apelido = apelido;
		this.mae = mae;
		this.pai = pai;
		this.estadoCivil = estadoCivil;
		this.nomeConjugue = nomeConjugue;
		this.agricultor = agricultor;
	}

	public DadosSociais(String apelido, String mae, String pai, EstadoCivil estadoCivil, String nomeConjugue,
			Agricultor agricultor) {
		this.apelido = apelido;
		this.mae = mae;
		this.pai = pai;
		this.estadoCivil = estadoCivil;
		this.nomeConjugue = nomeConjugue;
		this.agricultor = agricultor;
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

	public String getApelido() {
		return apelido;
	}

	public String getMae() {
		return mae;
	}

	public String getPai() {
		return pai;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public String getNomeConjugue() {
		return nomeConjugue;
	}

	public Agricultor getAgricultor() {
		return agricultor;
	}

	@Override
	public String toString() {
		return "DadosSociais [apelido=" + apelido + ", agricultor=" + agricultor + "]";
	}
	
}
