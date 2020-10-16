package guru.springframework.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
@Data
@AllArgsConstructor
public class CustomerListDTO {

	List<CustomerDTO> customers;
}
