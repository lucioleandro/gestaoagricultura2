package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.AtividadePecuaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadePecuariaRepository extends JpaRepository<AtividadePecuaria, Long>{

	List<AtividadePecuaria> findByPropriedadeId(Long id);

}
