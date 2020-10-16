package guru.springframework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
//@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO customerToCustomerDTO(Customer customer);
	
	Customer customerDTOToCustomer(CustomerDTO customerDTO);
	
}
