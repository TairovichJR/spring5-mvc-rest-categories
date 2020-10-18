package guru.springframework.api.v1.mapper;

import org.springframework.stereotype.Component;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@Component
public class VendorMapperImpl implements VendorMapper{

	@Override
	public VendorDTO vendorToVendorDTO(Vendor vendor) {
		if (vendor == null) {
			return null;
		}
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(vendor.getName());
		return vendorDTO;
	}

	@Override
	public Vendor vendorDTOToVendor(VendorDTO vendorDTO) {
		if (vendorDTO == null) {
			return null;
		}
		
		Vendor vendor = new Vendor();
		vendor.setName(vendorDTO.getName());
		return vendor;
	}

}
