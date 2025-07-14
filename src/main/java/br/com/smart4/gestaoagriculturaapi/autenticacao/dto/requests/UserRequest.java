package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String cpf;
    private String telefone;
    private String telefoneAlternativo;
    private UserTypeEnum tipo;
    private String password;

}

