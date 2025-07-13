package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.ResponseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResponseQuestionRepository extends JpaRepository<ResponseQuestion, Long> {

	List<ResponseQuestion> findByQuestionId(Long id);

	List<ResponseQuestion> findByFarmerId(Long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM agri_respostapergunta  \n" + 
			"WHERE farmer_id = :id AND 0 <> (SELECT COUNT(id) FROM agri_pergunta WHERE `tipo_pergunta` = 'ME' "
			+ "AND `id` = agri_respostapergunta.`pergunta_id`);", nativeQuery = true)
	void removeRespostasMultiplaEscolhaByFarmer(Long id);
	

}
