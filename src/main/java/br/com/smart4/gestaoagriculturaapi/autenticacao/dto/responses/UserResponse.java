package br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String cpf;
    private String telefone;
    private String telefoneAlternativo;
    private UserTypeEnum tipo;
}

