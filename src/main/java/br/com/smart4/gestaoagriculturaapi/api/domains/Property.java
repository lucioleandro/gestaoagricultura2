package br.com.smart4.gestaoagriculturaapi.api.domains;


import br.com.smart4.gestaoagriculturaapi.api.domains.enums.LandRegularizationEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "agro_property", schema = "farmer")
public class Property implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Version
	private int version;
	
	private String nome;

	private String itr;

	private String incra;

	@Column(nullable = false)
	private String latitude;

	@Column(nullable = false)
	private String longitude;

	private Double areaTotal;

	private Double areaAgricola;

	private Double reservaLegal;

	private String tipoResidencia;
	
	@Enumerated(EnumType.STRING)
	private LandRegularizationEnum regularizacaoFundiaria;

//  =========================================== RELACIONAMENTOS

	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private Farmer farmer;

//  =========================================== JUNÇÕES 1-1

	@OneToOne
	@JoinColumn(unique = true, name = "address_id")
	private Address address;

//  ===========================================

	@Override
	public String toString() {
		return "Property [nome=" + nome + ", farmer=" + farmer + ", address=" + address + "]";
	}
	
}
