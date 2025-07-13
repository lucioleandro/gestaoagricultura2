package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.LivestockActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.LivestockActivityRequest;

public final class LivestockActivityFactory {

    private LivestockActivityFactory() {}

    public static LivestockActivity fromRequest(LivestockActivityRequest request) {
        return LivestockActivity.builder()
                .especie(request.getEspecie())
                .quantidade(request.getQuantidade())
                .raca(request.getRaca())
                .property(Property.builder().id(request.getPropertyId()).build())
                .build();
    }
}

