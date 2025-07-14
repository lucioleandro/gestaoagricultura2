package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.EconomicActivity;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.EconomicActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.EconomicActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.EconomicActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.EconomicActivityMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.EconomicActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EconomicActivityService {

	private final EconomicActivityRepository economicActivityRepository;

	public EconomicActivityService(EconomicActivityRepository economicActivityRepository) {
		this.economicActivityRepository = economicActivityRepository;
	}

	@Transactional
	public EconomicActivityResponse create(EconomicActivityRequest request) {
		EconomicActivity entity = economicActivityRepository.save(EconomicActivityFactory.fromRequest(request));
		return EconomicActivityMapper.toResponse(entity);
	}

	@Transactional
	public EconomicActivityResponse update(Long id, EconomicActivityRequest request) {
		EconomicActivity existing = economicActivityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity not found with id: " + id));

		existing.setCodigocnae(request.getCodigocnae());
		existing.setDescricao(request.getDescricao());
		existing.setObservacao(request.getObservacao());
		existing.setSituacao(request.getSituacao());
		existing.setAliquota(request.getAliquota());
		existing.setValor(request.getValor());
		existing.setIsentoiss(request.isIsentoiss());
		existing.setAtividadeDeServico(request.getAtividadeDeServico());

		EconomicActivity updated = economicActivityRepository.save(existing);
		return EconomicActivityMapper.toResponse(updated);
	}

	public EconomicActivityResponse findById(Long id) {
		return economicActivityRepository.findById(id)
				.map(EconomicActivityMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity not found with id: " + id));
	}

	public List<EconomicActivityResponse> findAll() {
		return EconomicActivityMapper.toListResponse(economicActivityRepository.findAll());
	}

	@Transactional
	public void remove(Long id) {
		EconomicActivity existing = economicActivityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Economic activity not found with id: " + id));
		economicActivityRepository.delete(existing);
	}
}
