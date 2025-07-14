package br.com.smart4.gestaoagriculturaapi.sistema.repositories;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompatibleRepository extends JpaRepository<Compatible, Long>{

	@Query("select c from Compatible c where c.codSistema = :id")
    Optional<Compatible> findByIdSistema(Integer id);

}
