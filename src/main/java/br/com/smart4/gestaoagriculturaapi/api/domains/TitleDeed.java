package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.TitleDeedEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_title_deed", schema = "smartagrodb")
public class TitleDeed implements Serializable {

    private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String titulo;

    @Column(length = 2000)
    private String observacao;

    private LocalDateTime dataAdicao;

    @Lob
    private byte[] bytes;

    private String extensao;

    @Enumerated(EnumType.STRING)
    private TitleDeedEnum documento;
//  =========================================== RELACIONAMENTOS

    @ManyToOne
    private Property property;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(titulo, dataAdicao, documento, property);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleDeed that = (TitleDeed) o;
        return java.util.Objects.equals(titulo, that.titulo) &&
                java.util.Objects.equals(dataAdicao, that.dataAdicao) &&
                java.util.Objects.equals(documento, that.documento) &&
                java.util.Objects.equals(property, that.property);
    }

    @Override
    public String toString() {
        return "TitleDeed{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", documento=" + documento +
                ", dataAdicao=" + dataAdicao +
                ", propriedade=" + (property != null ? property.getNome() : "null") +
                '}';
    }

}
