package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivityFarmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityFarmerRequest;

public final class EconomicActivityFarmerFactory {

    private EconomicActivityFarmerFactory() {}

    public static EconomicActivityFarmer fromRequest(
            EconomicActivityFarmerRequest request
    ) {
        return EconomicActivityFarmer.builder()
                .principal(request.getPrincipal())
                .dataInicial(request.getDataInicial())
                .dataFinal(request.getDataFinal())
                .farmer(Farmer.builder().id(request.getFarmerId()).build())
                .property(Property.builder().id(request.getPropertyId()).build())
                .economicActivity(EconomicActivity.builder().id(request.getEconomicActivityId()).build())
                .build();
    }
}

