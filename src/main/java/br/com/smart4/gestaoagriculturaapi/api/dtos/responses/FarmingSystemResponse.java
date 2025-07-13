package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.FarmSystemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingSystemResponse {
    private Long id;
    private String descricao;
    private FarmSystemEnum ramoAtividade;
}

