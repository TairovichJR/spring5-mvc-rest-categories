package guru.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return customerRepository.findAll()
						.stream()
						.map(customer -> {
							CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
							customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
							return customerDTO;
						})
						.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		
		Optional<Customer> customerOptional = customerRepository.findById(id);
		
		if (customerOptional.isPresent()) {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerOptional.get());
			customerDTO.setCustomerUrl("/api/v1/customers/" + id);
			return customerDTO;
		}else {
			throw new RuntimeException("Customer not found");
		}
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		
		Customer savedCustomer =  customerRepository.save(customer);
		
		CustomerDTO returnDTO =  customerMapper.customerToCustomerDTO(savedCustomer);
		
		returnDTO.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());
	
		return returnDTO;
	}

}
