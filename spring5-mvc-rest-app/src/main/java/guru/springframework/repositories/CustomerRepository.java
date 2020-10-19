package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Customer;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{


}
