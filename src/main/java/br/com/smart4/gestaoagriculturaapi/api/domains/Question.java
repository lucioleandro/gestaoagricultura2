package br.com.smart4.gestaoagriculturaapi.api.domains;


import br.com.smart4.gestaoagriculturaapi.api.domains.enums.QuestionTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "agro_question", schema = "smartagrodb")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    private int version;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Boolean ativa;

    @Column(nullable = false)
    private Boolean obrigatoria;

    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum tipoQuestion;

//  =========================================== JUNÇÕES 1-N

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(descricao, tipoQuestion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question that = (Question) o;
        return java.util.Objects.equals(descricao, that.descricao) &&
                tipoQuestion == that.tipoQuestion;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", ativa=" + ativa +
                ", obrigatoria=" + obrigatoria +
                ", tipoQuestion=" + tipoQuestion +
                '}';
    }

}
