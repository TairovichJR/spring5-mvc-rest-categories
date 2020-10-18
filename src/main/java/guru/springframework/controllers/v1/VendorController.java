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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@Api(tags = {"vendor"})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

	public static final String BASE_URL = "/api/v1/shop/vendors";
	
	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@ApiOperation(value = "This will get a list of vendors", notes = "These are some notes about the vendor API")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors() {
		return new VendorListDTO(vendorService.getAllVendors());
	}
	
	@ApiOperation(value = "This will get one vendor")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorDtoById(@PathVariable Long id) {
		return vendorService.getVendorById(id);
	}
	
	@ApiOperation(value = "This will create one vendor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
		return vendorService.createNewVendor(vendorDTO);
	}
	
	@ApiOperation(value = "This will update an existing vendor")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.updateVendorByDTO(id, vendorDTO);
	}
	
	@ApiOperation(value = "This will patch an existing vendor")
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchCustomer(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.patchVendor(id, vendorDTO);
	}
	
	@ApiOperation(value = "This will delete an existing vendor")
	@DeleteMapping("/{id}")
	public void deleteVendor(@PathVariable Long id) {
		vendorService.deleteVendorById(id);
	}
	
}
