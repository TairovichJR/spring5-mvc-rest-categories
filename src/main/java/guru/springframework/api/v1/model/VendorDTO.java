package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@Data
public class VendorDTO {

	@ApiModelProperty(value = "This is the vendor name", required = true)
	private String name;
	
	@JsonProperty("vendor_url")
	private String vendorUrl;
}
