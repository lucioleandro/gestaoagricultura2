package br.com.smart4.gestaoagriculturaapi.sistema.services;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.CompatibleResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.mappers.CompatibleMapper;
import br.com.smart4.gestaoagriculturaapi.sistema.repositories.CompatibleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompatibleService {

    private final CompatibleRepository compatibleRepository;

    public CompatibleService(CompatibleRepository compatibleRepository) {
        this.compatibleRepository = compatibleRepository;
    }

    @Transactional
    public CompatibleResponse create(Compatible compatible) {
        Compatible saved = compatibleRepository.save(compatible);
        return CompatibleMapper.toResponse(saved);
    }

    @Transactional
    public CompatibleResponse update(Compatible compatible) {
        Compatible updated = compatibleRepository.save(compatible);
        return CompatibleMapper.toResponse(updated);
    }

    @Transactional
    public void remove(Compatible compatible) {
        compatibleRepository.delete(compatible);
    }

    public Optional<CompatibleResponse> findById(Long id) {
        return compatibleRepository.findById(id)
                .map(CompatibleMapper::toResponse);
    }

    public List<CompatibleResponse> findAll() {
        return CompatibleMapper.toListResponse(compatibleRepository.findAll());
    }

    public Optional<CompatibleResponse> findByCodSistema(Integer id) {
        return compatibleRepository.findByIdSistema(id)
                .map(CompatibleMapper::toResponse);
    }
}
