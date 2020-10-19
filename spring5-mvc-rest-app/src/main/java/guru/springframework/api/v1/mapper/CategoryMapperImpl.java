package guru.springframework.api.v1.mapper;

import org.springframework.stereotype.Component;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;

/**
 * Created by tairovich_jr on Oct 13, 2020
 */
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }
}
