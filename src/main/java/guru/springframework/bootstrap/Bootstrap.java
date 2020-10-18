package guru.springframework.bootstrap;
/**
 * Created by tairovich_jr on Oct 12, 2020
 */

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;


@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;
	private VendorRepository vendorRepository;
	
	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
			VendorRepository vendorRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
		loadVendor();
	}

	private void loadCategories() {
		Category fruits = new Category();
		fruits.setName("Fruits");
		
		Category dried = new Category();
		dried.setName("Dried");
		
		Category fresh = new Category();
		fresh.setName("Fresh");
		
		Category exotic = new Category();
		exotic.setName("Exotic");
		
		Category nuts = new Category();
		nuts.setName("Nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		
		System.out.println("Categories loaded = " + categoryRepository.count());
	}

	private void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setFirstName("Omon");
		customer1.setLastName("Yokubov");
		
		Customer customer2 = new Customer();
		customer2.setFirstName("Bob");
		customer2.setLastName("Gregory");
		
		Customer customer3 = new Customer();
		customer3.setFirstName("Helen");
		customer3.setLastName("Kenny");
		
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		
		System.out.println("Customers loaded = " + customerRepository.count());
	}
	
	
	private void loadVendor() {
		Vendor vendor1 = new Vendor();
		vendor1.setName("Mike and Ike");
		
		Vendor vendor2 = new Vendor();
		vendor2.setName("Sunoco Inc");
		
		Vendor vendor3 = new Vendor();
		vendor3.setName("Rasmus Mundus Inc");
		
		Vendor vendor4 = new Vendor();
		vendor4.setName("Hello World Inc");
		
		vendorRepository.saveAll(Arrays.asList(vendor1, vendor2, vendor3, vendor4));
		
		System.out.println("Vendors loaded: " + vendorRepository.count());
		
	}
	
	
}
