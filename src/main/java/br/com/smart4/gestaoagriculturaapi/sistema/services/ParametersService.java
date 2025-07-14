package br.com.smart4.gestaoagriculturaapi.sistema.services;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Parameters;
import br.com.smart4.gestaoagriculturaapi.sistema.dto.responses.ParametersResponse;
import br.com.smart4.gestaoagriculturaapi.sistema.mappers.ParametersMapper;
import br.com.smart4.gestaoagriculturaapi.sistema.repositories.ParametersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParametersService {

	private final ParametersRepository parametroRepository;

	public ParametersService(ParametersRepository parametroRepository) {
		this.parametroRepository = parametroRepository;
	}

	@Transactional
	public ParametersResponse create(Parameters parametro) {
		Parameters saved = parametroRepository.save(parametro);
		return ParametersMapper.toResponse(saved);
	}

	@Transactional
	public ParametersResponse update(Parameters parametro) {
		Parameters updated = parametroRepository.save(parametro);
		return ParametersMapper.toResponse(updated);
	}

	public Optional<ParametersResponse> findById(Long id) {
		return parametroRepository.findById(id)
				.map(ParametersMapper::toResponse);
	}

	public List<ParametersResponse> findAll() {
		return ParametersMapper.toListResponse(parametroRepository.findAll());
	}

	@Transactional
	public void remove(Long parametroId) {
		Parameters parameter = parametroRepository.findById(parametroId)
				.orElseThrow(() -> new EntityNotFoundException("Parameter not found with id: " + parametroId));
		parametroRepository.delete(parameter);
	}
}
