package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.AgricultureActivity;
import br.com.smart4.gestaoagriculturaapi.api.domains.Product;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AgricultureActivityRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.AgricultureActivityResponse;
import br.com.smart4.gestaoagriculturaapi.api.factories.AgricultureActivityFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.AgricultureActivityMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AgricultureActivityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.ProductRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgricultureActivityService {

    private final AgricultureActivityRepository atividadeAgricolaRepository;
    private final PropertyRepository propertyRepository;
    private final ProductRepository productRepository;

    public AgricultureActivityService(
            AgricultureActivityRepository atividadeAgricolaRepository,
            PropertyRepository propertyRepository,
            ProductRepository productRepository
    ) {
        this.atividadeAgricolaRepository = atividadeAgricolaRepository;
        this.propertyRepository = propertyRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public AgricultureActivityResponse create(AgricultureActivityRequest request) {
        propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + request.getPropertyId()));

        productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.getProductId()));

        AgricultureActivity entity = AgricultureActivityFactory.fromRequest(request);
        AgricultureActivity saved = atividadeAgricolaRepository.save(entity);
        return AgricultureActivityMapper.toResponse(saved);
    }

    @Transactional
    public AgricultureActivityResponse update(Long id, AgricultureActivityRequest request) {
        AgricultureActivity existing = atividadeAgricolaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agriculture activity not found with id: " + id));

        Property property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + request.getPropertyId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.getProductId()));

        existing.setDataPlantio(request.getDataPlantio());
        existing.setVariedade(request.getVariedade());
        existing.setAreaPlantada(request.getAreaPlantada());
        existing.setQuantidadePlantas(request.getQuantidadePlantas());
        existing.setProducaoAnual(request.getProducaoAnual());
        existing.setMetodoIrrigacao(request.getMetodoIrrigacao());
        existing.setFonteAgua(request.getFonteAgua());
        existing.setProperty(property);
        existing.setProduct(product);

        AgricultureActivity updated = atividadeAgricolaRepository.save(existing);
        return AgricultureActivityMapper.toResponse(updated);
    }

    public AgricultureActivityResponse findById(Long id) {
        AgricultureActivity entity = atividadeAgricolaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agriculture activity not found with id: " + id));
        return AgricultureActivityMapper.toResponse(entity);
    }

    public List<AgricultureActivityResponse> findAll() {
        return AgricultureActivityMapper.toListResponse(
                atividadeAgricolaRepository.findAll()
        );
    }

    public List<AgricultureActivityResponse> findByProperty(Long propertyId) {
        return AgricultureActivityMapper.toListResponse(
                atividadeAgricolaRepository.findByPropertyId(propertyId)
        );
    }

    @Transactional
    public void remove(Long id) {
        AgricultureActivity entity = atividadeAgricolaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agriculture activity not found with id: " + id));
        atividadeAgricolaRepository.delete(entity);
    }
}
