package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agro_product", schema = "smartagrodb")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String descricao;

    private String unidadeMedida;

    private String siglaUnidadeMedida;

//  =========================================== RELACIONAMENTOS

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(descricao, unidadeMedida, siglaUnidadeMedida);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product other = (Product) obj;
        return java.util.Objects.equals(descricao, other.descricao) &&
                java.util.Objects.equals(unidadeMedida, other.unidadeMedida) &&
                java.util.Objects.equals(siglaUnidadeMedida, other.siglaUnidadeMedida);
    }

    @Override
    public String toString() {
        return "Product{" +
                "descricao='" + descricao + '\'' +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                ", siglaUnidadeMedida='" + siglaUnidadeMedida + '\'' +
                '}';
    }


}
