package br.com.smart4.gestaoagriculturaapi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agro_economic_activity_farmer", schema = "smartagrodb")
public class EconomicActivityFarmer implements Serializable {

    private static final long serialVersionUID = 1L;

    //  =========================================== CAMPOS PARTICULARES DA CLASSE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private boolean principal;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;

//  =========================================== RELACIONAMENTOS

    @ManyToOne
    private Farmer farmer;

    @ManyToOne
    private Property property;

    @ManyToOne
    private EconomicActivity economicActivity;

//  ===========================================

    @Override
    public int hashCode() {
        return java.util.Objects.hash(dataInicial, dataFinal, principal, farmer, property, economicActivity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EconomicActivityFarmer that = (EconomicActivityFarmer) o;

        return principal == that.principal &&
                java.util.Objects.equals(dataInicial, that.dataInicial) &&
                java.util.Objects.equals(dataFinal, that.dataFinal) &&
                java.util.Objects.equals(farmer, that.farmer) &&
                java.util.Objects.equals(property, that.property) &&
                java.util.Objects.equals(economicActivity, that.economicActivity);
    }

    @Override
    public String toString() {
        return "EconomicActivityFarmer{" +
                "principal=" + principal +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", farmer=" + (farmer != null ? farmer.getId() : null) +
                ", property=" + (property != null ? property.getId() : null) +
                ", economicActivity=" + (economicActivity != null ? economicActivity.getId() : null) +
                '}';
    }


}
