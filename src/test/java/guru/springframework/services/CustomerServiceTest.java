package guru.springframework.services;
/**
 * Created by tairovich_jr on Oct 15, 2020
 */

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
public class CustomerServiceTest {

	public static final Long ID = 1L;
	public static final String FIRST_NAME = "Omon";
	public static final String LAST_NAME = "Yokubov";
	
	CustomerService customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@BeforeEach
	public void each() {
		MockitoAnnotations.initMocks(this);
		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}
	
	
	@Test
	public void getAllCustomers() {
		
		List<Customer> customers = Arrays.asList(
				new Customer(), new Customer(), new Customer());
				
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
		
		assertNotNull(customerDTOs);
		assertEquals(3, customerDTOs.size());
		
	}
	
	
	@Test
	public void getCustomerById() {
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		
		CustomerDTO customerDTO = customerService.getCustomerById(ID);
	
		assertNotNull(customerDTO);
		assertEquals(FIRST_NAME, customerDTO.getFirstname());
		assertEquals(LAST_NAME, customerDTO.getLastname());
	}
	
	@Test
	public void createNewCustomer() {
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(FIRST_NAME);
		customerDTO.setLastname(LAST_NAME);
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstname());
		savedCustomer.setLastName(customerDTO.getLastname());
		savedCustomer.setId(ID);
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		//when
		CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);
		
		assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
		assertEquals("/api/v1/customers/1",	 savedDto.getCustomerUrl());
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
