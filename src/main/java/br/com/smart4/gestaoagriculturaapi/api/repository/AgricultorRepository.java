package br.com.smart4.gestaoagriculturaapi.api.repository;

import br.com.smart4.gestaoagriculturaapi.api.domain.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {

	Optional<Agricultor> findByCpf(String cpf);

}
