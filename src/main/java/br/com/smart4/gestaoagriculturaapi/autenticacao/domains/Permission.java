package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aut_permission", schema = "smartagrodb")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String componente;

    private boolean ativo;

    private boolean atalho;

    private boolean somenteLeitura;

//  =========================================== RELACIONAMENTOS

    @Setter
    @ManyToOne
    private Profile perfil;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(componente, perfil);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;
        return java.util.Objects.equals(componente, that.componente) &&
                java.util.Objects.equals(perfil, that.perfil);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", componente='" + componente + '\'' +
                ", ativo=" + ativo +
                ", atalho=" + atalho +
                ", somenteLeitura=" + somenteLeitura +
                ", perfil=" + (perfil != null ? perfil.getDescricao() : "null") +
                '}';
    }

}
