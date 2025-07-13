package br.com.smart4.gestaoagriculturaapi.sistema.service;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Compatible;
import br.com.smart4.gestaoagriculturaapi.sistema.repository.CompatibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompatibleService {
	
	@Autowired
	private CompatibleRepository compatibleRepository;
	
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
