package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.TitleDeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleDeedRepository extends JpaRepository<TitleDeed, Long>{

	List<TitleDeed> findByPropertyId(Long id);

}
