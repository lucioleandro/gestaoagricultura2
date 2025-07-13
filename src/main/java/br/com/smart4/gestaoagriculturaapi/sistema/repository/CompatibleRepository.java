package br.com.smart4.gestaoagriculturaapi.sistema.repository;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Compatible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompatibleRepository extends JpaRepository<Compatible, Long>{

	@Query("select c from Compatible c where c.codSistema = :id")
    Compatible findByIdSistema(Integer id);

}
