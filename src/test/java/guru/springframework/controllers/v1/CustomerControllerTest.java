package guru.springframework.controllers.v1;

/**
 * Created by tairovich_jr on Oct 15, 2020
 */

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.services.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

	public static final String FIRST_NAME = "Omon";
	public static final String LAST_NAME = "Yokubov";
	public static final Long ID = 1L;

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void getAllCustomers() throws Exception {

		// given
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstname("Michale");
		customer1.setLastname("Weston");
		customer1.setCustomerUrl("/api/v1/customer/1");

		CustomerDTO customer2 = new CustomerDTO();
		customer2.setFirstname("Sam");
		customer2.setLastname("Axe");
		customer2.setCustomerUrl("/api/v1/customer/2");

		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void getCustomerById() throws Exception {

		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstname("Michale");
		customer1.setLastname("Weston");
		customer1.setCustomerUrl("/api/v1/customer/1");

		when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

		  mockMvc.perform(get("/api/v1/customers/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.firstname", equalTo("Michale")));

	}
	
	@Test
	public void createNewCustomer() throws Exception {
	     //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Flintstone");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);
		
		 //when/then
        mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
		
		
	}
}