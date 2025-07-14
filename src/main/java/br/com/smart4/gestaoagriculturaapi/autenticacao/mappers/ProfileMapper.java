package br.com.smart4.gestaoagriculturaapi.autenticacao.mappers;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileWithPermissionsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileMapper {

    private ProfileMapper() {
    }

    public static ProfileResponse toResponse(Profile profile) {
        if (profile == null) return null;

        return ProfileResponse.builder()
                .id(profile.getId())
                .nome(profile.getNome())
                .descricao(profile.getDescricao())
                .tipo(profile.getTipo())
                .sistema(profile.getSistema())
                .build();
    }

    public static ProfileWithPermissionsResponse toWithPermissionsResponse(Profile profile) {
        if (profile == null) return null;

        return ProfileWithPermissionsResponse.builder()
                .id(profile.getId())
                .nome(profile.getNome())
                .descricao(profile.getDescricao())
                .tipo(profile.getTipo())
                .sistema(profile.getSistema())
                .permissoes(
                        profile.getPermissoes() != null ?
                                profile.getPermissoes().stream()
                                        .map(PermissionMapper::toResponse)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    public static List<ProfileResponse> toListResponse(List<Profile> profiles) {
        return profiles.stream()
                .map(ProfileMapper::toResponse)
                .toList();
    }

    public static List<ProfileWithPermissionsResponse> toListWithPermissionsResponse(List<Profile> profiles) {
        return profiles.stream()
                .map(ProfileMapper::toWithPermissionsResponse)
                .toList();
    }


}

