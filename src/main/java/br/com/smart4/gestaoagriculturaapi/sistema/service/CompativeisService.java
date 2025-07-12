package br.com.smart4.gestaoagriculturaapi.sistema.service;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Compativeis;
import br.com.smart4.gestaoagriculturaapi.sistema.repository.CompativeisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompativeisService {
	
	@Autowired
	private CompativeisRepository compativeisRepository;
	
	public Compativeis create(Compativeis compativeis) {
		return compativeisRepository.save(compativeis);
	}

	public Compativeis atualiza(Compativeis compativeis) {
		return compativeisRepository.save(compativeis);
	}

	public void remove(Compativeis compativeis) {
		compativeisRepository.delete(compativeis);
	}

	public Optional<Compativeis> findById(Long id) {
		return compativeisRepository.findById(id);
	}

	public List<Compativeis> findAll() {
		return compativeisRepository.findAll();
	}

	public Compativeis findByCodSistema(Integer id) {
		return compativeisRepository.findByIdSistema(id);
	}
}
