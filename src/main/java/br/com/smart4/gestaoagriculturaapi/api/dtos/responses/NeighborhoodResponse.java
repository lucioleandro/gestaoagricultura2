package br.com.smart4.gestaoagriculturaapi.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodResponse {
    private Long id;
    private String nome;
    private String zona;
    private Long cityId;
    private String cityNome;
}

