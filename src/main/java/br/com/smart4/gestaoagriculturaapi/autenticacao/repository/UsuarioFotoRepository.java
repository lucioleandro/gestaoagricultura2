package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UsuarioFoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioFotoRepository extends JpaRepository<UsuarioFoto, Long>{

	Optional<UsuarioFoto> findByUsuarioLogin(String login);

}
