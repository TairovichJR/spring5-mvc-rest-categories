package guru.springframework.services;
/**
 * Created by tairovich_jr on Oct 13, 2020
 */

import java.util.List;
import guru.springframework.api.v1.model.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> getAllCustomers();
	CustomerDTO getCustomerById(Long id);
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}