package br.com.smart4.gestaoagriculturaapi.sistema.domains;

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
@Table(name = "system_compatible", schema = "smartagrodb")
public class Compatible implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private Integer codSistema;

    private String senhaDeLiberacao;

    private String versaoLiberada;

//  ===========================================

    public void setSenhaDeLiberacao(String senhaDeLiberacao) {
        this.senhaDeLiberacao = senhaDeLiberacao;
    }

    public String getVersaoLiberada() {
        return versaoLiberada;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(codSistema, versaoLiberada);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Compatible other = (Compatible) obj;
        return java.util.Objects.equals(codSistema, other.codSistema) &&
                java.util.Objects.equals(versaoLiberada, other.versaoLiberada);
    }

    @Override
    public String toString() {
        return "Compatible{" +
                "id=" + id +
                ", codSistema=" + codSistema +
                ", versaoLiberada='" + versaoLiberada + '\'' +
                '}';
    }

}
