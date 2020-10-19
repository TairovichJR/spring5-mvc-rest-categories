package guru.springframework.services;
/**
 * Created by tairovich_jr on Oct 17, 2020
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

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.controllers.v1.VendorController;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;

public class VendorServiceTest {

	public static final String NAME = "OAK DALE Inc";
	public static final Long ID = 1L;
	
	VendorService vendorService;
	
	@Mock
	VendorRepository vendorRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
	}

	@Test
	public void getAllVendorsTest() {
		List<Vendor> vendors = Arrays.asList(new Vendor(),
				new Vendor(), new Vendor());
		
		when(vendorRepository.findAll()).thenReturn(vendors);
		
		List<VendorDTO> vendorDTOs = vendorService.getAllVendors();
		
		assertNotNull(vendorDTOs);
		assertEquals(3, vendorDTOs.size());
	}
	
	@Test
	public void getVendorByIdTest() {
		Vendor vendor = new Vendor();
		vendor.setId(ID);
		vendor.setName(NAME);
		
		when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));
		
		VendorDTO vendorDTO = vendorService.getVendorById(ID);
		
		assertEquals(NAME, vendorDTO.getName());
		assertEquals(VendorController.BASE_URL +"/1", vendorDTO.getVendorUrl());
	}
	
	@Test
	public void createNewVendorTest() {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Mike and Ike");
		
		Vendor savedVendor = new Vendor();
		savedVendor.setName(vendorDTO.getName());
		savedVendor.setId(1L);
		
		when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
		
		VendorDTO saveDto = vendorService.createNewVendor(vendorDTO);
		
		assertEquals(vendorDTO.getName(), saveDto.getName());
		assertEquals(VendorController.BASE_URL +"/1", saveDto.getVendorUrl());
	}
	
	@Test
	public void updateVendorByDto() {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Mike and Ike");
		
		Vendor savedVendor = new Vendor();
		savedVendor.setId(1L);
		savedVendor.setName(vendorDTO.getName());
		
		when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
		
		VendorDTO savedDto = vendorService.updateVendorByDTO(1L, vendorDTO);
		
		assertEquals(vendorDTO.getName(), savedDto.getName());
		assertEquals(VendorController.BASE_URL +"/1", savedDto.getVendorUrl());
		
	}
	
	@Test
	public void deleteVendorById() {
		
		vendorService.deleteVendorById(1L);
		verify(vendorRepository, times(1)).deleteById(anyLong());
	}
	
}
