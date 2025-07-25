package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageRequest {

    @NotNull(message = "File content must not be null.")
    private byte[] arquivo;

    @NotBlank(message = "File extension must not be blank.")
    private String extensao;

    @NotNull(message = "Product ID must not be null.")
    private Long productId;

}

