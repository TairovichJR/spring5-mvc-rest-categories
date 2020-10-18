package guru.springframework.api.v1.mapper;

import org.mapstruct.factory.Mappers;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
public interface VendorMapper {

	public static final VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
	
	VendorDTO vendorToVendorDTO(Vendor vendor);
	
	Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
