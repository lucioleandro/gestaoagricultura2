package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select m from Usuario m where m.login = :username")
	Usuario findByName(String username);

	Optional<Usuario>findByLogin(String login);
}
