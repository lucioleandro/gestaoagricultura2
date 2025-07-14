package br.com.smart4.gestaoagriculturaapi.autenticacao.repositories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.UserPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPictureRepository extends JpaRepository<UserPicture, Long>{

	Optional<UserPicture> findByUsuarioLogin(String login);
	Optional<UserPicture> findByUsuarioId(Long id);

}
