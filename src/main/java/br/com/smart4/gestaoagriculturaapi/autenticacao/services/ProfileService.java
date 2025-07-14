package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.ProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.ProfileFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.ProfileMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public ProfileResponse create(ProfileRequest perfil) {
        Profile entity = profileRepository.saveAndFlush(ProfileFactory.fromRequest(perfil));
        return ProfileMapper.toResponse(entity);
    }

    @Transactional
    public ProfileResponse update(ProfileRequest perfil) {
        Profile entity = profileRepository.save(ProfileFactory.fromRequest(perfil));
        return ProfileMapper.toResponse(entity);
    }

    public List<ProfileResponse> findAll() {
        return ProfileMapper.toListResponse(profileRepository.findAll());
    }

    public Optional<ProfileResponse> findById(Long id) {
        return profileRepository.findById(id)
                .map(ProfileMapper::toResponse);
    }

    public List<ProfileResponse> findByIdUsuario(Long id) {
        return ProfileMapper.toListResponse(profileRepository.findByUserId(id));
    }

    @Transactional
    public void remove(Profile perfil) {
        profileRepository.delete(perfil);
    }
}
