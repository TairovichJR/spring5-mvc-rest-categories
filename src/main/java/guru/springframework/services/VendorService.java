package guru.springframework.services;
/**
 * Created by tairovich_jr on Oct 17, 2020
 */

import java.util.List;

import guru.springframework.api.v1.model.VendorDTO;

public interface VendorService {

	List<VendorDTO> getAllVendors();
	
	VendorDTO getVendorById(Long id);
	
	VendorDTO createNewVendor(VendorDTO vendorDTO);
	
	VendorDTO updateVendorByDTO(Long id, VendorDTO vendorDTO);
	
	VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
	
	void deleteVendorById(Long id);
	
}
