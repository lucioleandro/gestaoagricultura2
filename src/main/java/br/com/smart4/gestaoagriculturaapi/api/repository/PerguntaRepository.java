package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{

	@Query("select p from Pergunta p where p.ativa = true")
	List<Pergunta> findPerguntasAtivas();

}
