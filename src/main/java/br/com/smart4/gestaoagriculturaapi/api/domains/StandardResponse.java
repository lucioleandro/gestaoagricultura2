package br.com.smart4.gestaoagriculturaapi.api.domains;

import jakarta.persistence.Column;
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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_standard_response")
public class StandardResponse implements Serializable {

    private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    private int version;

    @Column(nullable = false)
    private String descricao;

//  =========================================== JUNÇÕES 1-1

    @ManyToOne
    Question question;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(descricao, question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardResponse that = (StandardResponse) o;
        return java.util.Objects.equals(descricao, that.descricao) &&
                java.util.Objects.equals(question, that.question);
    }

    @Override
    public String toString() {
        return "StandardResponse{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", question=" + (question != null ? question.getDescricao() : "null") +
                '}';
    }

}
