package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface USerRepository extends JpaRepository<User, Long> {

	@Query("select m from User m where m.login = :username")
    User findByName(String username);

	Optional<User>findByLogin(String login);
}
