package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.FarmerRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.FarmerResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.FarmerFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.FarmerMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Transactional
    public FarmerResponse create(FarmerRequest request) {
        Farmer entity = farmerRepository.save(FarmerFactory.fromRequest(request));
        return FarmerMapper.toResponse(entity);
    }

    @Transactional
    public FarmerResponse update(Long id, FarmerRequest request) {
        Farmer existing = farmerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + id));

        existing.setNome(request.getNome());
        existing.setCpf(request.getCpf());
        existing.setRg(request.getRg());
        existing.setOrgaoExpeditor(request.getOrgaoExpeditor());
        existing.setApelido(request.getApelido());
        existing.setDataNascimento(request.getDataNascimento());

        Farmer updated = farmerRepository.save(existing);
        return FarmerMapper.toResponse(updated);
    }

    public FarmerResponse findById(Long id) {
        return farmerRepository.findById(id)
                .map(FarmerMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + id));
    }

    public List<FarmerResponse> findAll() {
        return FarmerMapper.toListResponse(farmerRepository.findAll());
    }

    public FarmerResponse findByCpf(String cpf) {
        return farmerRepository.findByCpf(cpf)
                .map(FarmerMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found with CPF: " + cpf));
    }

    @Transactional
    public void remove(Long id) {
        Farmer existing = farmerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + id));
        farmerRepository.delete(existing);
    }
}
