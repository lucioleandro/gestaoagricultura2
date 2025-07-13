package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.StreetTypeEnum;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_address", schema = "smartagrodb")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Setter
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Version
	private int version;
	
	@Basic
	private String logradouro;
	
	@Basic
	private String numero;
	
	@Basic
	private String cep;
	@Basic
	private String complemento;
	
	@Enumerated(EnumType.STRING)
	private StreetTypeEnum tipoLogradouro;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "neighborhood_id")
	private Neighborhood neighborhood;
	
	public String toString() {
		String resultado = "";

		if (tipoLogradouro != null)
			resultado = resultado + tipoLogradouro.toString() + " ";
		if (logradouro != null)
			resultado = resultado + logradouro;
		if (numero != null)
			resultado = resultado + ", N " + numero + ". ";
		if (complemento != null)
			resultado = resultado + complemento;
		if (neighborhood != null)
			resultado = resultado + ", " + neighborhood.getNome();

		resultado = resultado + ".";
		return resultado;
	}

}
