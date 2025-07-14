package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_product_image", schema = "smartagrodb")
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @Lob
    private byte[] arquivo;

    private String extensao;

//  =========================================== RELACIONAMENTOS

    @ManyToOne
    private Product product;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(extensao, product);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProductImage other = (ProductImage) obj;
        return java.util.Objects.equals(extensao, other.extensao) &&
                java.util.Objects.equals(product, other.product);
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", extensao='" + extensao + '\'' +
                ", product=" + (product != null ? product.getId() : null) +
                '}';
    }


}
