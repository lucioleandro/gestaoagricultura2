package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.SpeciesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "agro_livestock_activity", schema = "smartagrodb")
public class LivestockActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Version
	    private int version;
	    
	    @Enumerated(EnumType.STRING)
	    private SpeciesEnum especie;
	    
	    private Integer quantidade;
	    
	    private String raca;

	//  =========================================== RELACIONAMENTOS
	    
	    @ManyToOne
	    private Property property;
	    
	//  ===========================================

		@Override
		public String toString() {
			return "LivestockActivity [especie=" + especie + ", quantidade=" + quantidade
					+ "]";
		}
}
