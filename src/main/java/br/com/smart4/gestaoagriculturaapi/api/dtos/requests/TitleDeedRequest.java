package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TitleDeedRequest {

    @NotBlank(message = "The title must not be blank.")
    private String titulo;

    private String observacao;

    @NotNull(message = "The document file must not be null.")
    private MultipartFile file;

    @NotNull(message = "The document type must be specified.")
    private TitleDeedEnum documento;

    @NotNull(message = "The property ID must not be null.")
    private Long propertyId;

    public TitleDeedRequest(String titulo, String observacao, LocalDateTime dataAdicao, byte[] byteArquivo, String extensao, TitleDeedEnum documento, Property property) {
    }
}

