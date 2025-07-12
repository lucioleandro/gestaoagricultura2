package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadeAgricola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeAgricolaRepository extends JpaRepository<AtividadeAgricola, Long> {

	List<AtividadeAgricola> findByPropriedadeId(Long id);

}
