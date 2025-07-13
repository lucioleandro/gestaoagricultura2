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

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_city", schema = "smartagrodb")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @Basic
    private String nome;

    @Basic
    private Integer cadastroUnico;

    @Column(length = 2)
    private String uf;

    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, uf, cadastroUnico);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return java.util.Objects.equals(nome, city.nome) &&
                java.util.Objects.equals(uf, city.uf) &&
                java.util.Objects.equals(cadastroUnico, city.cadastroUnico);
    }

    @Override
    public String toString() {
        return nome + " - " + uf;
    }


}
