package br.com.smart4.gestaoagriculturaapi.sistema.repository;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Compativeis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompativeisRepository extends JpaRepository<Compativeis, Long>{

	@Query("select c from Compativeis c where c.codSistema = :id")
	Compativeis findByIdSistema(Integer id);

}
