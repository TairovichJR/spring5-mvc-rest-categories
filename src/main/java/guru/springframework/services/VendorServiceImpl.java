package guru.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.controllers.v1.VendorController;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@Service
public class VendorServiceImpl implements VendorService{

	private final VendorMapper vendorMapper;
	private final VendorRepository vendorRepository;

	public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
		this.vendorMapper = vendorMapper;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public List<VendorDTO> getAllVendors() {
		return vendorRepository.findAll().stream()
					.map(vendor ->{
						VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
						vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
						return vendorDTO;
					})
					.collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendorById(Long id) {

		return vendorRepository.findById(id)
					.map(vendor -> vendorMapper.vendorToVendorDTO(vendor))
					.map(vendorDTO -> {
						//set api url for vendor
						vendorDTO.setVendorUrl(getVendorUrl(id));
						return vendorDTO;
					})
					.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
	
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
		Vendor savedVendor = vendorRepository.save(vendor);
		VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
		returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));
		
		return returnDto;
	}

	@Override
	public VendorDTO updateVendorByDTO(Long id, VendorDTO vendorDTO) {
		
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
		vendor.setId(id);
		
		return saveAndReturnVendorDto(vendor);
	}
	
	public VendorDTO saveAndReturnVendorDto(Vendor vendor) {
		
		Vendor savedVendor = vendorRepository.save(vendor);
		VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
		returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));
		
		return returnDto;
	}
	
	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		
		return vendorRepository.findById(id).map( vendor -> {
			
			if (vendor.getName() != null) {
				vendor.setName(vendorDTO.getName());
			}
			
			VendorDTO returnDto = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
			returnDto.setVendorUrl(getVendorUrl(id));
			
			return returnDto;
		}).orElseThrow(ResourceNotFoundException::new);
		
	}

	@Override
	public void deleteVendorById(Long id) {
		Optional<Vendor> vendorOptional = vendorRepository.findById(id);
		if (vendorOptional.isPresent()) {
			vendorRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException();
		}
	}

	private String getVendorUrl(Long id) {
		return VendorController.BASE_URL + "/" + id;
	}
	
}
