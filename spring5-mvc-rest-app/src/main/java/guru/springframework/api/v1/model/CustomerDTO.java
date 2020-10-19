package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
@Data
public class CustomerDTO {

	@ApiModelProperty(value = "This is the first name", required = true)
	private String firstname;
	
	@ApiModelProperty(value = "This is the last name", required = true)
	private String lastname;

	@JsonProperty("customer_url")
	private String customerUrl;
}
