package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;


import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.SistemasMBEnum;
import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.enums.ProfileTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aut_profile", schema = "smartagrodb")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private ProfileTypeEnum tipo;

    @Enumerated(EnumType.STRING)
    private SistemasMBEnum sistema;


//  =========================================== RELACIONAMENTOS

    @OneToMany(targetEntity = Permission.class, mappedBy = "perfil",
            cascade = {CascadeType.REMOVE})
    private List<Permission> permissoes;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, sistema);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile that = (Profile) o;
        return java.util.Objects.equals(nome, that.nome) &&
                sistema == that.sistema;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipo=" + tipo +
                ", sistema=" + sistema +
                '}';
    }


}
