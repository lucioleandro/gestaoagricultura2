package br.com.smart4.gestaoagriculturaapi.autenticacao.repository;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domain.UserPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPictureRepository extends JpaRepository<UserPicture, Long>{

	Optional<UserPicture> findByUsuarioLogin(String login);

}
