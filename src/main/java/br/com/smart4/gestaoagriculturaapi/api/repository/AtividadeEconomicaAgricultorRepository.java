package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeEconomicaAgricultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeEconomicaAgricultorRepository extends JpaRepository<AtividadeEconomicaAgricultor, Long>{

	List<AtividadeEconomicaAgricultor> findByAgricultorId(Long id);

	List<AtividadeEconomicaAgricultor> findByPropriedadeId(Long id);

}
