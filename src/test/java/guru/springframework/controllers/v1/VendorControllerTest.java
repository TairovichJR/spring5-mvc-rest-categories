package guru.springframework.controllers.v1;
/**
 * Created by tairovich_jr on Oct 17, 2020
 */

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.*;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.services.ResourceNotFoundException;
import guru.springframework.services.VendorService;

public class VendorControllerTest extends AbstractRestControllerTest {

	
	public static final String NAME = "MIKE and IKE";
	public static final Long ID = 1L;
	
	@Mock
	VendorService vendorService;
	
	@InjectMocks
	VendorController vendorController;
	
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}
	
	@Test
	public void getAllVendorsTest() throws Exception {
		VendorDTO vendorDTO1 = new VendorDTO();
		vendorDTO1.setName("Hello World Inc");
		vendorDTO1.setVendorUrl(VendorController.BASE_URL +"/1");
		
		VendorDTO vendorDTO2 = new VendorDTO();
		vendorDTO2.setName("Sunoco Stations");
		vendorDTO2.setVendorUrl(VendorController.BASE_URL +"/2");
		
		VendorDTO vendorDTO3 = new VendorDTO();
		vendorDTO3.setName("Sweet Teeth Inc");
		vendorDTO3.setVendorUrl(VendorController.BASE_URL +"/3");
		
		when(vendorService.getAllVendors()).thenReturn(
				Arrays.asList(vendorDTO1, vendorDTO2, vendorDTO3));
		
		mockMvc.perform(get(VendorController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vendors", hasSize(3)));
	}
	
	@Test
	public void getVendorByIdTest() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Hello World Inc");
		vendorDTO.setVendorUrl(VendorController.BASE_URL +"/1");
		
		when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);
		
		mockMvc.perform(get(VendorController.BASE_URL +"/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Hello World Inc")))
				.andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL +"/1")));	
	}
	
	@Test
	public void ceateNewVendorTest() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Abc Inc");
		
		VendorDTO returnDto = new VendorDTO();
		returnDto.setName(vendorDTO.getName());
		returnDto.setVendorUrl(VendorController.BASE_URL+"/1");
		
		when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnDto);
		
		mockMvc.perform(post(VendorController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo("Abc Inc")))
				.andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));
	}
	
	@Test
	public void updateVendorTest() throws Exception {
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Abc Inc");
		
		VendorDTO returnDto = new VendorDTO();
		returnDto.setName(vendorDTO.getName());
		returnDto.setVendorUrl(VendorController.BASE_URL+"/1");
		
		when(vendorService.updateVendorByDTO(anyLong(), any(VendorDTO.class)))
						.thenReturn(returnDto);
		
		mockMvc.perform(put(VendorController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Abc Inc")))
				.andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));
	}
	
	@Test
	public void pathVendorTest() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Abc Inc");
		
		VendorDTO returnDto = new VendorDTO();
		returnDto.setName(vendorDTO.getName());
		returnDto.setVendorUrl(VendorController.BASE_URL+"/1");
		
		when(vendorService.patchVendor(anyLong(), any(VendorDTO.class)))
						.thenReturn(returnDto);
		
		mockMvc.perform(patch(VendorController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Abc Inc")))
				.andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));
	}
	
	@Test
	public void deleteVendorTest() throws Exception {
		mockMvc.perform(delete(VendorController.BASE_URL +"/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		verify(vendorService, times(1)).deleteVendorById(anyLong());
	}
	
	@Test
	public void findVendorByIdNotFoundExceptionTest() throws Exception {
		when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get(VendorController.BASE_URL+"/123")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
