package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitleDeedRequest {

    @NotBlank(message = "The title must not be blank.")
    private String titulo;

    private String observacao;

    @NotNull(message = "The document file must not be null.")
    private MultipartFile file;

    @NotNull(message = "The document type must be specified.")
    private TitleDeedEnum documento;

    @NotBlank(message = "The extension must not be blank.")
    private String extensao;

    @NotNull(message = "The property ID must not be null.")
    private Long propertyId;

}

