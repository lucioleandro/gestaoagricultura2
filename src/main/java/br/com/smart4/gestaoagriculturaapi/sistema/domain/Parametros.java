package br.com.smart4.gestaoagriculturaapi.sistema.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "sistema_parametros", schema = "agricultura")
public class Parametros implements Serializable {
	
	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    
    private String logradouro;
    
    private String tipoLogradouro;
    
    private String acod;
    
    private String bairro;
    
    @Lob
    private byte[] brasao;
    
    private String cep;
    
    private String cidade;
    
    private String cnpj;
    
    private String codfebraban;
    
    private Integer codigoPM;
    
    private String fax;
    
    private String nome;
    
    private String numero;
    
    private String ordenadorPrincipal;
    
    private String telefone;
    
    private String uf;
    
    private String inscestadual;
    
    private String inscmunicipal;

//  =========================================== RELACIONAMENTOS

//  ===========================================
    
    public Parametros() { }

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public String getAcod() {
		return acod;
	}

	public String getBairro() {
		return bairro;
	}

	public byte[] getBrasao() {
		return brasao;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getCodfebraban() {
		return codfebraban;
	}

	public Integer getCodigoPM() {
		return codigoPM;
	}

	public String getFax() {
		return fax;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getOrdenadorPrincipal() {
		return ordenadorPrincipal;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getUf() {
		return uf;
	}

	public String getInscestadual() {
		return inscestadual;
	}

	public String getInscmunicipal() {
		return inscmunicipal;
	}
    
}
