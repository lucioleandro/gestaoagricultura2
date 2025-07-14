package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;


import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TitleDeedResponse {
    private Long id;
    private String titulo;
    private String observacao;
    private LocalDateTime dataAdicao;
    private String extensao;
    private TitleDeedEnum documento;
    private Long propertyId;
    private String propertyNome;
    @JsonIgnore
    private byte[] bytes;
}

