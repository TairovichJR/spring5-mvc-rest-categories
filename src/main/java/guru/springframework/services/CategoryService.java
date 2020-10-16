package guru.springframework.services;
/**
 * Created by tairovich_jr on Oct 12, 2020
 */

import java.util.List;
import guru.springframework.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryByName(String name);
	
}
