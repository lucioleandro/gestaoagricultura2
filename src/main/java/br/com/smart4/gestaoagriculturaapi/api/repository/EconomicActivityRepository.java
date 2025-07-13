package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.EconomicActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EconomicActivityRepository extends JpaRepository<EconomicActivity, Long> {

}
