package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
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

import java.io.Serializable;
import java.util.Comparator;

/**
 * Descrição:
 *
 * @author Lúcio Leandro
 * @version 2.0
 **/
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_economic_activity", schema = "smartagrodb")
public class EconomicActivity implements Comparator<Object>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    @Column(length = 15, unique = true)
    private String codigocnae;

    @Column(length = 500)
    private String observacao;

    @Basic
    private String descricao;

    @Basic
    private Boolean situacao;

    @Column(precision = 2, columnDefinition = "double default 0")
    private Double aliquota;

    @Column(precision = 2, columnDefinition = "double default 0")
    private Double valor;

    @Basic
    private boolean isentoiss;

    @Basic
    private Boolean atividadeDeServico;

    @Override
    public int compare(Object o1, Object o2) {

        return 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(codigocnae, descricao, situacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EconomicActivity that = (EconomicActivity) o;

        return java.util.Objects.equals(codigocnae, that.codigocnae) &&
                java.util.Objects.equals(descricao, that.descricao) &&
                java.util.Objects.equals(situacao, that.situacao);
    }

    @Override
    public String toString() {
        return codigocnae;
    }

}
