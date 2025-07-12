package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long>{

	List<Propriedade> findByAgricultorId(Long id);

}
