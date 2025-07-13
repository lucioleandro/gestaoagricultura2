package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "agro_personal_information", schema = "smartagrodb")
public class PersonalInformation implements Serializable {

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
	private MaritalStatus maritalStatus;

	@Column(name = "conjugue")
	private String nomeConjugue;

//  =========================================== JUNÇÕES 1-1

	@OneToOne
	@JoinColumn(unique = true, name = "farmer_id")
	private Farmer farmer;

//  ===========================================

	public PersonalInformation() {
	}

	public PersonalInformation(Long id, int version, String apelido, String mae, String pai, MaritalStatus maritalStatus,
			String nomeConjugue, Farmer farmer) {
		this.id = id;
		this.version = version;
		this.apelido = apelido;
		this.mae = mae;
		this.pai = pai;
		this.maritalStatus = maritalStatus;
		this.nomeConjugue = nomeConjugue;
		this.farmer = farmer;
	}

	public PersonalInformation(String apelido, String mae, String pai, MaritalStatus maritalStatus, String nomeConjugue,
			Farmer farmer) {
		this.apelido = apelido;
		this.mae = mae;
		this.pai = pai;
		this.maritalStatus = maritalStatus;
		this.nomeConjugue = nomeConjugue;
		this.farmer = farmer;
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

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public String getNomeConjugue() {
		return nomeConjugue;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	@Override
	public String toString() {
		return "PersonalInformation [apelido=" + apelido + ", farmer=" + farmer + "]";
	}
	
}
