package br.com.smart4.gestaoagriculturaapi.sistema.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "system_parameters", schema = "smartagrodb")
public class Parameters implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String logradouro;

    private String tipoLogradouro;

    private String acod;

    private String neighborhood;

    @Lob
    private byte[] brasao;

    private String cep;

    private String cidade;

    private String cnpj;

    private String codfebraban;

    private Integer codigoPM;

    private String fax;

    private String nome;

    private String numero;

    private String ordenadorPrincipal;

    private String telefone;

    private String uf;

    private String inscestadual;

    private String inscmunicipal;

//  =========================================== RELACIONAMENTOS

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cnpj, nome, cidade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Parameters other = (Parameters) obj;
        return java.util.Objects.equals(cnpj, other.cnpj) &&
                java.util.Objects.equals(nome, other.nome) &&
                java.util.Objects.equals(cidade, other.cidade);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

}
