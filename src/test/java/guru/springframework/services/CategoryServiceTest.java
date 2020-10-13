package guru.springframework.services;
/**
 * Created by tairovich_jr on Oct 12, 2020
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;

public class CategoryServiceTest {

	
	public static final Long ID = 2L;
	public static final String NAME = "Omon";
	
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
	}
	
	@Test
	public void getAllCategories() {
		//given
		List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
		
		when(categoryRepository.findAll()).thenReturn(categories);
		
		//when
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
		
		assertEquals(3, categoryDTOs.size());
	}
	
	@Test
    public void getCategoryByName() throws Exception {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
	
}
