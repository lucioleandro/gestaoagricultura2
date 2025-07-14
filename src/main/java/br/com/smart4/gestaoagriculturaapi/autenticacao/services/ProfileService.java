package br.com.smart4.gestaoagriculturaapi.autenticacao.services;

import br.com.smart4.gestaoagriculturaapi.autenticacao.domains.Profile;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.requests.ProfileRequest;
import br.com.smart4.gestaoagriculturaapi.autenticacao.dto.responses.ProfileResponse;
import br.com.smart4.gestaoagriculturaapi.autenticacao.factories.ProfileFactory;
import br.com.smart4.gestaoagriculturaapi.autenticacao.mappers.ProfileMapper;
import br.com.smart4.gestaoagriculturaapi.autenticacao.repositories.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public ProfileResponse create(ProfileRequest request) {
        Profile entity = profileRepository.saveAndFlush(ProfileFactory.fromRequest(request));
        return ProfileMapper.toResponse(entity);
    }

    @Transactional
    public ProfileResponse update(Long id, ProfileRequest request) {
        Profile entity = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));

        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setTipo(request.getTipo());
        entity.setSistema(request.getSistema());

        return ProfileMapper.toResponse(profileRepository.save(entity));
    }

    public List<ProfileResponse> findAll() {
        return ProfileMapper.toListResponse(profileRepository.findAll());
    }

    public ProfileResponse findById(Long id) {
        return profileRepository.findById(id)
                .map(ProfileMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
    }

    public List<ProfileResponse> findByIdUsuario(Long id) {
        return ProfileMapper.toListResponse(profileRepository.findByUserId(id));
    }

    @Transactional
    public void remove(Long id) {
        Profile entity = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
        profileRepository.delete(entity);
    }
}
