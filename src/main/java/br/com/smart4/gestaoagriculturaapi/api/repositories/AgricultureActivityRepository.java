package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgricultureActivityRepository extends JpaRepository<AgricultureActivity, Long> {

	List<AgricultureActivity> findByPropertyId(Long id);

}
