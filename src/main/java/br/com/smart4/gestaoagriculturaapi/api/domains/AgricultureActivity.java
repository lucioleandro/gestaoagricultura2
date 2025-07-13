package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.IrrigationMethodEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.enums.WaterSourceEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_agriculture_activity", schema = "smartagrodb")
public class AgricultureActivity implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataPlantio;

	private String variedade;

	private double areaPlantada;

	private Integer quantidadePlantas;

	private double producaoAnual;

	@Enumerated(EnumType.STRING)
	private IrrigationMethodEnum metodoIrrigacao;
	
	@Enumerated(EnumType.STRING)
	private WaterSourceEnum fonteAgua;
//  =========================================== RELACIONAMENTOS

	@Getter
    @ManyToOne
	private Property property;

	@ManyToOne
	private Product product;

//  ===========================================


}
