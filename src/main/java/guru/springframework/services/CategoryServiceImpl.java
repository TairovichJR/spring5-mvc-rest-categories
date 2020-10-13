package guru.springframework.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;

/**
 * Created by tairovich_jr on Oct 12, 2020
 */
@Service
public class CategoryServiceImpl implements CategoryService{

	private final CategoryMapper categoryMapper;
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		
		return categoryRepository.findAll()
								.stream()
								.map(category -> categoryMapper.categoryToCategoryDTO(category))
								.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}



}
