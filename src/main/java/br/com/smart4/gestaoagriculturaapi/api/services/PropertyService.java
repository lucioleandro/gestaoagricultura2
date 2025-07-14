package br.com.smart4.gestaoagriculturaapi.api.services;

import br.com.smart4.gestaoagriculturaapi.api.domains.City;
import br.com.smart4.gestaoagriculturaapi.api.domains.Farmer;
import br.com.smart4.gestaoagriculturaapi.api.domains.Neighborhood;
import br.com.smart4.gestaoagriculturaapi.api.domains.Property;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.AddressRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.requests.PropertyRequest;
import br.com.smart4.gestaoagriculturaapi.api.dtos.responses.PropertyResponse;
import br.com.smart4.gestaoagriculturaapi.api.exceptions.BusinessException;
import br.com.smart4.gestaoagriculturaapi.api.factories.PropertyFactory;
import br.com.smart4.gestaoagriculturaapi.api.mappers.PropertyMapper;
import br.com.smart4.gestaoagriculturaapi.api.repositories.AddressRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.CityRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.FarmerRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.NeighborhoodRepository;
import br.com.smart4.gestaoagriculturaapi.api.repositories.PropertyRepository;
import br.com.smart4.gestaoagriculturaapi.api.utils.Coordinates;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;
	private final FarmerRepository farmerRepository;
	private final AddressRepository addressRepository;
	private final NeighborhoodRepository neighborhoodRepository;
	private final CityRepository cityRepository;

	private final AddressService addressService;

	private final RestTemplate restTemplate;

	private final String apiKey;

	public PropertyService(@Value("${app.apiKey}") String apiKey,
                           PropertyRepository propertyRepository,
                           FarmerRepository farmerRepository,
                           AddressRepository addressRepository,
                           NeighborhoodRepository neighborhoodRepository,
                           CityRepository cityRepository, AddressService addressService,
                           RestTemplateBuilder restTemplateBuilder) {
	this.apiKey = apiKey;
	this.propertyRepository = propertyRepository;
		this.farmerRepository = farmerRepository;
		this.addressRepository = addressRepository;
        this.neighborhoodRepository = neighborhoodRepository;
        this.cityRepository = cityRepository;
        this.addressService = addressService;
        this.restTemplate = restTemplateBuilder.build();
    }

	@Transactional
	public PropertyResponse create(PropertyRequest request) {
		// 1) Lookup coords
		Coordinates coords = lookupCoordinates(request.getAddress());
		request.setLatitude(coords.getLatitude());
		request.setLongitude(coords.getLongitude());

		// 2) Create the Address first
		addressService.create(request.getAddress());

		// 3) Create the Property
		Property entity = propertyRepository.save(PropertyFactory.fromRequest(request));
		return PropertyMapper.toResponse(entity);
	}

	@Transactional
	public PropertyResponse update(Long id, PropertyRequest request) {
		Coordinates coords = lookupCoordinates(request.getAddress());
		request.setLatitude(coords.getLatitude());
		request.setLongitude(coords.getLongitude());

		// 2) Push address update
		addressService.update(id, request.getAddress());

		// 3) Load existing property + associations
		Property existing = propertyRepository.findById(id)
				.orElseThrow(() ->
						new EntityNotFoundException("Property not found with id: " + id)
				);

		Farmer farmer = farmerRepository.findById(request.getFarmerId())
				.orElseThrow(() ->
						new EntityNotFoundException("Farmer not found with id: " + request.getFarmerId())
				);

		// 4) Apply all updates
		existing.setNome(request.getNome());
		existing.setItr(request.getItr());
		existing.setIncra(request.getIncra());
		existing.setLatitude(request.getLatitude());
		existing.setLongitude(request.getLongitude());
		existing.setAreaTotal(request.getAreaTotal());
		existing.setAreaAgricola(request.getAreaAgricola());
		existing.setReservaLegal(request.getReservaLegal());
		existing.setTipoResidencia(request.getTipoResidencia());
		existing.setRegularizacaoFundiaria(request.getRegularizacaoFundiaria());
		existing.setFarmer(farmer);
		// addressService.update has already synced the Address entity

		// 5) Save & map
		Property updated = propertyRepository.save(existing);
		return PropertyMapper.toResponse(updated);
	}

	public PropertyResponse findById(Long id) {
		return propertyRepository.findById(id)
				.map(PropertyMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
	}

	public List<PropertyResponse> findAll() {
		return PropertyMapper.toListResponse(propertyRepository.findAll());
	}

	public List<PropertyResponse> findByFarmer(Long farmerId) {
		return PropertyMapper.toListResponse(propertyRepository.findByFarmerId(farmerId));
	}

	@Transactional
	public void remove(Long id) {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
		propertyRepository.delete(property);
	}

	private Coordinates lookupCoordinates(AddressRequest addrReq) {
		// fetch full Neighborhood and City
		Neighborhood nb = neighborhoodRepository.findById(addrReq.getNeighborhoodId())
				.orElseThrow(() ->
						new EntityNotFoundException("Neighborhood not found with id: " + addrReq.getNeighborhoodId())
				);
		City city = cityRepository.findById(addrReq.getCityId())
				.orElseThrow(() ->
						new EntityNotFoundException("City not found with id: " + addrReq.getCityId())
				);

		// build the address string
		String address = String.format("%s, %s, %s, %s/%s",
				addrReq.getLogradouro(),
				addrReq.getNumero(),
				nb.getNome(),
				city.getNome(),
				city.getUf()
		);

		String url = UriComponentsBuilder
				.fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
				.queryParam("address", address)
				.queryParam("key", apiKey)
				.encode()
				.toUriString();

		@SuppressWarnings("unchecked")
		Map<String, Object> result = restTemplate.getForObject(url, Map.class);

		List<?> results = (List<?>) result.get("results");
		if (results.isEmpty()) {
			throw new BusinessException("Não foi possível obter coordenadas para o endereço informado");
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> geometry = (Map<String, Object>) ((Map<?,?>) results.get(0)).get("geometry");
		@SuppressWarnings("unchecked")
		Map<String, Object> location = (Map<String, Object>) geometry.get("location");

		String lat = (location.get("lat")).toString();
		String lng = ( location.get("lng")).toString();
		return new Coordinates(lat, lng);
	}

}
