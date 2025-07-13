package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

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

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aut_user_profile", schema = "smartagrodb")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private boolean ativo;

    private boolean administrador;

//  =========================================== RELACIONAMENTOS

    @ManyToOne
    private Profile perfil;

    @ManyToOne
    private User usuario;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(usuario, perfil);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserProfile other = (UserProfile) obj;
        return java.util.Objects.equals(usuario, other.usuario) &&
                java.util.Objects.equals(perfil, other.perfil);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getLogin() : "null") +
                ", perfil=" + (perfil != null ? perfil.getNome() : "null") +
                ", administrador=" + administrador +
                ", ativo=" + ativo +
                '}';
    }


}
