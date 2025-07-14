package br.com.smart4.gestaoagriculturaapi.api.domains;

import br.com.smart4.gestaoagriculturaapi.api.domains.enums.MaritalStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_personal_information", schema = "smartagrodb")
public class PersonalInformation implements Serializable {

    private static final long serialVersionUID = 1L;

//  =========================================== CAMPOS PARTICULARES DA CLASSE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    private int version;

    @Column(nullable = false)
    private String apelido;

    @Column(nullable = false)
    private String mae;

    @Column(nullable = false)
    private String pai;

    @Column(nullable = false)
    private MaritalStatusEnum maritalStatus;

    @Column(name = "conjugue")
    private String nomeConjugue;

//  =========================================== JUNÇÕES 1-1

    @OneToOne
    @JoinColumn(unique = true, name = "farmer_id")
    private Farmer farmer;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(apelido, mae, pai, maritalStatus, nomeConjugue, farmer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PersonalInformation that = (PersonalInformation) obj;
        return java.util.Objects.equals(apelido, that.apelido) &&
                java.util.Objects.equals(mae, that.mae) &&
                java.util.Objects.equals(pai, that.pai) &&
                maritalStatus == that.maritalStatus &&
                java.util.Objects.equals(nomeConjugue, that.nomeConjugue) &&
                java.util.Objects.equals(farmer, that.farmer);
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "apelido='" + apelido + '\'' +
                ", mae='" + mae + '\'' +
                ", pai='" + pai + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", nomeConjugue='" + nomeConjugue + '\'' +
                ", farmer=" + (farmer != null ? farmer.getNome() : null) +
                '}';
    }

}
