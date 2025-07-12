package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.DocumentoPropriedade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentosPropriedadeRepository extends JpaRepository<DocumentoPropriedade, Long>{

	List<DocumentoPropriedade> findByPropriedadeId(Long id);

}
