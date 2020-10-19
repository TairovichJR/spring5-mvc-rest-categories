package guru.springframework.api.v1.mapper;
/**
 * Created by tairovich_jr on Oct 15, 2020
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.domain.Customer;
import guru.springframework.model.CustomerDTO;

public class CustomerMapperTest {

	
	public static final String FIRST_NAME = "Omon";
	public static final String LAST_NAME = "Yokubov";
	public static final Long ID = 1L;
	
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;
	
	@Test
	public void customerToCustomerDTO() {
		
		Customer customer = new Customer();
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		assertEquals(FIRST_NAME, customerDTO.getFirstname());
		assertEquals(LAST_NAME, customerDTO.getLastname());
		
		
	}
	
	
}
