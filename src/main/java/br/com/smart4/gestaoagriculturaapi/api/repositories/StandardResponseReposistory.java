package br.com.smart4.gestaoagriculturaapi.api.repositories;

import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardResponseReposistory extends JpaRepository<StandardResponse, Long> {
}
