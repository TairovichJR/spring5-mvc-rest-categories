package guru.springframework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;

/**
 * Created by jt on 9/25/17.
 */
//@Mapper
public interface CategoryMapper {

    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //@Mapping(source="getId", target="id")
    CategoryDTO categoryToCategoryDTO(Category category);
}