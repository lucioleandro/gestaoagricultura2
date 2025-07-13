package br.com.smart4.gestaoagriculturaapi.sistema.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompatibleResponse {
    private Long id;
    private Integer codSistema;
    private String versaoLiberada;
}

