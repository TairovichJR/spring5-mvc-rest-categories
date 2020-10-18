package guru.springframework.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;
import guru.springframework.services.VendorService;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

	public static final String BASE_URL = "/api/v1/shop/vendors";
	
	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors() {
		return new VendorListDTO(vendorService.getAllVendors());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorDtoById(@PathVariable Long id) {
		return vendorService.getVendorById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
		return vendorService.createNewVendor(vendorDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable Long id, VendorDTO vendorDTO) {
		return vendorService.updateVendorByDTO(id, vendorDTO);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchCustomer(@PathVariable Long id, VendorDTO vendorDTO) {
		return vendorService.patchVendor(id, vendorDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteVendor(@PathVariable Long id) {
		vendorService.deleteVendorById(id);
	}
	
}