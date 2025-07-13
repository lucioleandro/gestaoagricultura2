package br.com.smart4.gestaoagriculturaapi.autenticacao.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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
@Table(name = "aut_user_picture", schema = "smartagrodb")
public class UserPicture implements Serializable {

    private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @Lob
    @Setter
    private String fotoPerfil;

//  =========================================== RELACIONAMENTOS

    @OneToOne
    @JoinColumn(unique = true)
    @Setter
    private User usuario;

    public UserPicture(String fotoPerfil, User user) {
        this.fotoPerfil = fotoPerfil;
        this.usuario = user;
    }

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(usuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPicture other = (UserPicture) obj;
        return java.util.Objects.equals(usuario, other.usuario);
    }

    @Override
    public String toString() {
        return "UserPicture{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getLogin() : "null") +
                '}';
    }

}
