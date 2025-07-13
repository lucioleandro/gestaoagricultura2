package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long>{

	List<Property> findByFarmerId(Long id);

}
