package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.StandardResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardResponseReposistory extends JpaRepository<StandardResponse, Long> {
}
