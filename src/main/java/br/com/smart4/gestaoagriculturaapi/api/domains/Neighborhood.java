package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
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
@Table(name = "agro_neighborhood", schema = "smartagrodb")
public class Neighborhood implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Basic
	private String nome;

	@Basic
	private String zona;
	
	@ManyToOne
	private City city;

	@Override
	public int hashCode() {
		return java.util.Objects.hash(nome, zona, city);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Neighborhood that = (Neighborhood) obj;
		return java.util.Objects.equals(nome, that.nome) &&
				java.util.Objects.equals(zona, that.zona) &&
				java.util.Objects.equals(city, that.city);
	}

	@Override
	public String toString() {
		return "Neighborhood{" +
				"nome='" + nome + '\'' +
				", zona='" + zona + '\'' +
				", city=" + (city != null ? city.getNome() : null) +
				'}';
	}



}
