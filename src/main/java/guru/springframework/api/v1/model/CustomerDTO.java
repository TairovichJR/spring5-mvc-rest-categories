package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
@Data
public class CustomerDTO {

	private String firstname;
	private String lastname;
	
	@JsonProperty("customer_url")
	private String customerUrl;
}
