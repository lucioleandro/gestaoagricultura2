package br.com.smart4.gestaoagriculturaapi.api.factories;

import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.domains.TitleDeed;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.TitleDeedRequest;

import java.io.IOException;
import java.time.LocalDateTime;

public final class TitleDeedFactory {

    private TitleDeedFactory() {}

    public static TitleDeed fromRequest(TitleDeedRequest request) throws IOException {
        return TitleDeed.builder()
                .titulo(request.getTitulo())
                .observacao(request.getObservacao())
                .dataAdicao(LocalDateTime.now())
                .bytes(request.getFile().getBytes())
                .extensao(getFileExtension(request.getFile().getOriginalFilename()))
                .documento(request.getDocumento())
                .property(Property.builder().id(request.getPropertyId()).build())
                .build();
    }

    private static String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf('.') + 1);
        }
        return "unknown";
    }
}

