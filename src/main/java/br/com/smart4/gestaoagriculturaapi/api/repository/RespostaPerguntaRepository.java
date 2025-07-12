package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.RespostaPergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RespostaPerguntaRepository extends JpaRepository<RespostaPergunta, Long> {

	List<RespostaPergunta> findByPergunta(Long id);

	List<RespostaPergunta> findByAgricultorId(Long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM agri_respostapergunta  \n" + 
			"WHERE agricultor_id = :id AND 0 <> (SELECT COUNT(id) FROM agri_pergunta WHERE `tipo_pergunta` = 'ME' "
			+ "AND `id` = agri_respostapergunta.`pergunta_id`);", nativeQuery = true)
	void removeRespostasMultiplaEscolhaByAgricultor(Long id);
	

}
