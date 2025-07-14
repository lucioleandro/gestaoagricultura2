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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_address", schema = "smartagrodb")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		return java.util.Objects.hash(logradouro, numero, cep, complemento, tipoLogradouro, city, neighborhood);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Address other = (Address) obj;
		return java.util.Objects.equals(logradouro, other.logradouro)
				&& java.util.Objects.equals(numero, other.numero)
				&& java.util.Objects.equals(cep, other.cep)
				&& java.util.Objects.equals(complemento, other.complemento)
				&& tipoLogradouro == other.tipoLogradouro
				&& java.util.Objects.equals(city, other.city)
				&& java.util.Objects.equals(neighborhood, other.neighborhood);
	}

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
