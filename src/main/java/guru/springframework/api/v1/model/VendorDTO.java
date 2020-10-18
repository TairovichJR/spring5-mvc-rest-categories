package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@Data
public class VendorDTO {

	private String name;
	
	@JsonProperty("vendor_url")
	private String vendorUrl;
}
