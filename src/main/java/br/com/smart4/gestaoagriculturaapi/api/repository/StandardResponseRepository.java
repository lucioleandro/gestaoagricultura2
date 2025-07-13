package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.StandardResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardResponseRepository extends JpaRepository<StandardResponse, Long> {

	List<StandardResponse> findByQuestionId(Long id);

}
