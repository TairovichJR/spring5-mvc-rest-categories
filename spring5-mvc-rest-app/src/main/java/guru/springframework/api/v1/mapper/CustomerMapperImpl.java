package guru.springframework.api.v1.mapper;

import org.springframework.stereotype.Component;
import guru.springframework.domain.Customer;
import guru.springframework.model.CustomerDTO;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
@Component
public class CustomerMapperImpl implements CustomerMapper{

	@Override
	public CustomerDTO customerToCustomerDTO(Customer customer) {
		if (customer == null) {
			return null;
		}
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(customer.getFirstName());
		customerDTO.setLastname(customer.getLastName());
	
		return customerDTO;
	}

	@Override
	public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
		if (customerDTO == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setFirstName(customerDTO.getFirstname());
		customer.setLastName(customerDTO.getLastname());
		return customer;
	}
	
	
	

}
