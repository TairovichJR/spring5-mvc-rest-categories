package guru.springframework.api.v1.mapper;
/**
 * Created by tairovich_jr on Oct 17, 2020
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.controllers.v1.VendorController;
import guru.springframework.domain.Vendor;

public class VendorMapperTest {

	public final static String NAME = "Oak Dale Inc";
	public final static Long ID = 1L;
	
	VendorMapper vendorMapper = VendorMapper.INSTANCE;
	
	@Test
	public void vendorToVendorDtoTest() {
		
		Vendor vendor = new Vendor();
		vendor.setName(NAME);
		
		VendorDTO vendorDto = vendorMapper.vendorToVendorDTO(vendor);
		vendorDto.setVendorUrl(VendorController.BASE_URL);
		
		assertEquals(NAME, vendorDto.getName());
		assertEquals(VendorController.BASE_URL, vendorDto.getVendorUrl());
	}
	
}
