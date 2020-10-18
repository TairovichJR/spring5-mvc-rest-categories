package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Vendor;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
