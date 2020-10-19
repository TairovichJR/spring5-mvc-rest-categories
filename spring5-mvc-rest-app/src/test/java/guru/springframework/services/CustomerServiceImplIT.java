package guru.springframework.services;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.mapper.VendorMapper;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.bootstrap.Bootstrap;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.model.CustomerDTO;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by tairovich_jr on Oct 16, 2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	VendorRepository vendorRepository;
	
	CustomerService customerService;
	VendorService vendorService;

	@Before
	public void setUp() throws Exception {
		System.out.println("Loading Bootstrap Data");
		System.out.println("Customer Data: "+customerRepository.findAll().size());
		System.out.println("Vendor Data " + vendorRepository.findAll().size());
		
		// setup data for testing
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
		bootstrap.run(); // load data

		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
		vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void patchCustomerUpdateFirstName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getIdEntityId("customer");

		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		// save original first name
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(updatedName);

		customerService.patchCustomer(id, customerDTO);

		Customer updatedCustomer = customerRepository.findById(id).get();

		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getFirstName());
		assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
		assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void patchCustomerUpdateLastName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getIdEntityId("customer");

		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);

		// save original first/last name
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastname(updatedName);

		customerService.patchCustomer(id, customerDTO);

		Customer updatedCustomer = customerRepository.findById(id).get();

		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getLastName());
		assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
		assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void patchVendorName() {
		String updatedName = "Updated Name";
		Long id = getIdEntityId("vendor");
		Vendor originalVendor = vendorRepository.getOne(id);
		
		//save original vendor name
		String originalName = originalVendor.getName();
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(updatedName);
		
		vendorService.patchVendor(id, vendorDTO);
		
		Vendor updatedVendor = vendorRepository.findById(id).get();
		
		assertNotNull(updatedVendor);
		assertEquals(updatedName, updatedVendor.getName());
		assertThat(originalName, not(equalTo(updatedVendor.getName())));
	}
	
	
	private Long getIdEntityId(String entityType) {
		entityType = entityType.toLowerCase();
		switch (entityType) {
		case "customer":
			List<Customer> customers = customerRepository.findAll();
			System.out.println("Customers Found: " + customers.size());
			return customers.get(0).getId();
		case "vendor":
			List<Vendor> vendors = vendorRepository.findAll();
			System.out.println("Vendor Found: " + vendors.size());
			return vendors.get(0).getId();
		default:
			return null;
		}
	}
	
}
