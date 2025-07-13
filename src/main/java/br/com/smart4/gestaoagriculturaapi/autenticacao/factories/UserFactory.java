package br.com.smart4.gestaoagriculturaapi.autenticacao.factories;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.User;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.UserRequest;

public final class UserFactory {

    private UserFactory() {}

    public static User fromRequest(UserRequest request) {
        return User.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .login(request.getLogin())
                .senha(request.getSenha())
                .cpf(request.getCpf())
                .telefone(request.getTelefone())
                .telefoneAlternativo(request.getTelefoneAlternativo())
                .tipo(request.getTipo())
                .build();
    }
}

