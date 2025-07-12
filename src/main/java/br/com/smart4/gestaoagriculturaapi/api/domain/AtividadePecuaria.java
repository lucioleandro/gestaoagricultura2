package br.com.smart4.gestaoagriculturaapi.api.domain;

import br.com.smart4.gestaoagriculturaapi.api.domain.enums.EnumEspecie;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "agri_atividade_pecuaria", schema = "agricultura")
public class AtividadePecuaria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Version
	    private int version;
	    
	    @Enumerated(EnumType.STRING)
	    private EnumEspecie especie;
	    
	    private Integer quantidade;
	    
	    private String raca;

	//  =========================================== RELACIONAMENTOS
	    
	    @ManyToOne
	    private Propriedade propriedade;
	    
	//  ===========================================

	    public AtividadePecuaria() { }
	    
	    public AtividadePecuaria(Long id, int version, EnumEspecie especie, Integer quantidade, String raca,
	    		Propriedade propriedade) {
	    	this.id = id;
	    	this.version = version;
	    	this.especie = especie;
	    	this.quantidade = quantidade;
	    	this.raca = raca;
	    	this.propriedade = propriedade;
	    }

		public AtividadePecuaria(EnumEspecie especie, Integer quantidade, String raca, 
				Propriedade propriedade) {
			this.especie = especie;
			this.quantidade = quantidade;
			this.raca = raca;
			this.propriedade = propriedade;
		}

		public Long getId() {
			return id;
		}

		public int getVersion() {
			return version;
		}

		public EnumEspecie getEspecie() {
			return especie;
		}

		public Integer getQuantidade() {
			return quantidade;
		}

		public String getRaca() {
			return raca;
		}

		public Propriedade getPropriedade() {
			return propriedade;
		}
	    
		
		@Override
		public String toString() {
			return "AtividadePecuaria [especie=" + especie + ", quantidade=" + quantidade
					+ "]";
		}
}
