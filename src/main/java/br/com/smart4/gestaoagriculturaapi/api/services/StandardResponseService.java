package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.StandardResponse;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.StandardResponseRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.StandardResponseDTO;
import br.com.smart4.gestaoagriculturaapi.api.factories.StandardResponseFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.StandardResponseMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.StandardResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StandardResponseService {

    private final StandardResponseRepository respostaPadraoRepository;

    public StandardResponseService(StandardResponseRepository respostaPadraoRepository) {
        this.respostaPadraoRepository = respostaPadraoRepository;
    }

    @Transactional
    public StandardResponseDTO create(StandardResponseRequest respostaPadrao) {
        StandardResponse entity = respostaPadraoRepository.save(
                StandardResponseFactory.fromRequest(respostaPadrao)
        );
        return StandardResponseMapper.toResponse(entity);
    }

    @Transactional
    public StandardResponseDTO update(StandardResponseRequest respostaPadrao) {
        StandardResponse entity = respostaPadraoRepository.save(
                StandardResponseFactory.fromRequest(respostaPadrao)
        );
        return StandardResponseMapper.toResponse(entity);
    }

    public Optional<StandardResponseDTO> findById(Long id) {
        return respostaPadraoRepository.findById(id)
                .map(StandardResponseMapper::toResponse);
    }

    public List<StandardResponseDTO> findAll() {
        return StandardResponseMapper.toListResponse(
                respostaPadraoRepository.findAll()
        );
    }

    public List<StandardResponseDTO> findByQuestionId(Long id) {
        return StandardResponseMapper.toListResponse(
                respostaPadraoRepository.findByQuestionId(id)
        );
    }

    @Transactional
    public void remove(StandardResponse respostaPadrao) {
        respostaPadraoRepository.delete(respostaPadrao);
    }
}
