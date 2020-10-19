package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import guru.springframework.domain.Category;

/**
 * Created by tairovich_jr on Oct 12, 2020
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByName(String name);
	
}
