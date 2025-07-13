package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Column;
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
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_benefit", schema = "smartagrodb")
public class Benefit implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private LocalDateTime dataConcedimento;

//  =========================================== JUNÇÕES 1-N
	@ManyToOne
	private Farmer beneficiado;

//  ===========================================

	@Override
	public int hashCode() {
		return java.util.Objects.hash(descricao, dataConcedimento, beneficiado);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Benefit that = (Benefit) o;

		return java.util.Objects.equals(descricao, that.descricao)
				&& java.util.Objects.equals(dataConcedimento, that.dataConcedimento)
				&& java.util.Objects.equals(beneficiado, that.beneficiado);
	}

	@Override
	public String toString() {
		return "Benefit{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				", dataConcedimento=" + dataConcedimento +
				", beneficiado=" + (beneficiado != null ? beneficiado.getId() : null) +
				'}';
	}

}
