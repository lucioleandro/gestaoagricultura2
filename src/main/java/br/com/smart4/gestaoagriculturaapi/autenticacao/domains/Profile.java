package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.SistemasMBEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.ProfileTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "aut_profile", schema="smartagrodb")
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	
//  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    
    private String nome;
    
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private ProfileTypeEnum tipo;
    
    @Enumerated(EnumType.STRING)
    private SistemasMBEnum sistema;
    

//  =========================================== RELACIONAMENTOS

    @OneToMany(targetEntity = Permission.class, mappedBy = "perfil",
    		cascade = {CascadeType.REMOVE})
    private List<Permission> permissoes;
    
//  ===========================================
    
    public Profile() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ProfileTypeEnum getTipo() {
		return tipo;
	}

	public void setTipo(ProfileTypeEnum tipo) {
		this.tipo = tipo;
	}

	public SistemasMBEnum getSistema() {
		return sistema;
	}

	public void setSistema(SistemasMBEnum sistema) {
		this.sistema = sistema;
	}

	public List<Permission> getPermissoes() {
		return permissoes;
	}

	public void serPermissoes(List<Permission> permission) {
		this.permissoes = permission;
	}

	@Override
	public String toString() {
		return "Perfil [nome=" + nome + ", descricao=" + descricao + "]";
	}

}
