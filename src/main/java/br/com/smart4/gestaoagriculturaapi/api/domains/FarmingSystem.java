package br.com.smart4.gestaoagriculturaapi.api.domains;


import br.com.smart4.gestaoagriculturaapi.api.domains.enums.FarmSystemEnum;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "agro_farming_system", schema = "smartagrodb")
public class FarmingSystem implements Serializable {

	private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	@Basic
	private String descricao;

	@Enumerated(EnumType.STRING)
    FarmSystemEnum ramoAtividade;

//  =========================================== RELACIONAMENTOS

//  ===========================================

	@Override
	public String toString() {
		return "FarmingSystem [descricao=" + descricao + ", ramoAtividade=" + ramoAtividade + "]";
	}
	
	
}
