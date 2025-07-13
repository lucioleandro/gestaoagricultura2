package br.com.smart4.gestaoagriculturaapi.sistema.services;

import br.com.smart4.gestaoagriculturaapi.sistema.domains.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.repositories.CompatibleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompatibleService {
	
	private final CompatibleRepository compatibleRepository;

	public CompatibleService(CompatibleRepository compatibleRepository) {
		this.compatibleRepository = compatibleRepository;
	}

	public Compatible create(Compatible compatible) {
		return compatibleRepository.save(compatible);
	}

	public Compatible atualiza(Compatible compatible) {
		return compatibleRepository.save(compatible);
	}

	public void remove(Compatible compatible) {
		compatibleRepository.delete(compatible);
	}

	public Optional<Compatible> findById(Long id) {
		return compatibleRepository.findById(id);
	}

	public List<Compatible> findAll() {
		return compatibleRepository.findAll();
	}

	public Compatible findByCodSistema(Integer id) {
		return compatibleRepository.findByIdSistema(id);
	}
}
