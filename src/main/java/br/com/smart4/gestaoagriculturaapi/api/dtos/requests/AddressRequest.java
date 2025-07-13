package br.com.smart4.gestaoagriculturaapi.api.dtos.requests;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.StreetTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressRequest {

    @NotBlank(message = "Street name must not be blank.")
    @Size(max = 255, message = "Street name must be at most 255 characters long.")
    private String logradouro;

    @NotBlank(message = "House/building number must not be blank.")
    @Size(max = 50, message = "Number must be at most 50 characters long.")
    private String numero;

    @NotBlank(message = "ZIP code is required.")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Invalid ZIP code format. Expected: 12345-678 or 12345678.")
    private String cep;

    @Size(max = 255, message = "Complement must be at most 255 characters long.")
    private String complemento;

    @NotNull(message = "Street type is required.")
    private StreetTypeEnum tipoLogradouro;

    @NotNull(message = "City ID is required.")
    private Long cityId;

    @NotNull(message = "Neighborhood ID is required.")
    private Long neighborhoodId;


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public StreetTypeEnum getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(StreetTypeEnum tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(Long neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }
}

