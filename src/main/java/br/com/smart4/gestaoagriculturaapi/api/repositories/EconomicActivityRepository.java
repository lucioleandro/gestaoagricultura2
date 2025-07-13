package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EconomicActivityRepository extends JpaRepository<EconomicActivity, Long> {

}
