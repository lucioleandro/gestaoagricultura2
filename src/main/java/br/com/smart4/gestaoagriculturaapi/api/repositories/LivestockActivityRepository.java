package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivestockActivityRepository extends JpaRepository<LivestockActivity, Long>{

	List<LivestockActivity> findByPropertyId(Long id);

}
