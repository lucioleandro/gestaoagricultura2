package br.com.smart4.gestaoagriculturaapi.autenticacao.mappers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.UserResponse;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        if (user == null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .nome(user.getNome())
                .email(user.getEmail())
                .login(user.getLogin())
                .cpf(user.getCpf())
                .telefone(user.getTelefone())
                .telefoneAlternativo(user.getTelefoneAlternativo())
                .tipo(user.getTipo())
                .build();
    }

}

