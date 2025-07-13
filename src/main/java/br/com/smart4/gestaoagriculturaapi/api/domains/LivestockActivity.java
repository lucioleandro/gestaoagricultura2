package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "agro_livestock_activity", schema = "smartagrodb")
public class LivestockActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Version
	    private int version;
	    
	    @Enumerated(EnumType.STRING)
	    private br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumSpecies especie;
	    
	    private Integer quantidade;
	    
	    private String raca;

	//  =========================================== RELACIONAMENTOS
	    
	    @ManyToOne
	    private Property property;
	    
	//  ===========================================

	    public LivestockActivity() { }
	    
	    public LivestockActivity(Long id, int version, br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumSpecies especie, Integer quantidade, String raca,
                                 Property property) {
	    	this.id = id;
	    	this.version = version;
	    	this.especie = especie;
	    	this.quantidade = quantidade;
	    	this.raca = raca;
	    	this.property = property;
	    }

		public LivestockActivity(br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumSpecies especie, Integer quantidade, String raca,
                                 Property property) {
			this.especie = especie;
			this.quantidade = quantidade;
			this.raca = raca;
			this.property = property;
		}

		public Long getId() {
			return id;
		}

		public int getVersion() {
			return version;
		}

		public br.com.smart4.gestaoagriculturaapi.api.domains.enums.EnumSpecies getEspecie() {
			return especie;
		}

		public Integer getQuantidade() {
			return quantidade;
		}

		public String getRaca() {
			return raca;
		}

		public Property getProperty() {
			return property;
		}
	    
		
		@Override
		public String toString() {
			return "LivestockActivity [especie=" + especie + ", quantidade=" + quantidade
					+ "]";
		}
}
