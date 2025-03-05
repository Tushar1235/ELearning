package com.elearn.journey.start_learning.Service.Impl;

import com.elearn.journey.start_learning.DTO.CategoryDto;
import com.elearn.journey.start_learning.DTO.CourseDto;
import com.elearn.journey.start_learning.Entities.Category;
import com.elearn.journey.start_learning.Entities.Course;
import com.elearn.journey.start_learning.Exception.ResourceNotFoundException;
import com.elearn.journey.start_learning.Repositories.CategoryRepository;
import com.elearn.journey.start_learning.Repositories.CourseRepository;
import com.elearn.journey.start_learning.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not foound"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not foound"));
        category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(String title) {
        Category category = categoryRepository.findByTitle(title)
                .orElseThrow(()-> new ResourceNotFoundException("Category not foound"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto searchByTitle(String title) {
        Category category = categoryRepository.findByTitle(title)
                .orElseThrow(()-> new ResourceNotFoundException("Category not foound"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto addCourseToCategory(int categoryId, int courseId) {
        // Fetch Category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found with ID: " + categoryId));

        // Fetch Course (Fixing the incorrect ID usage)
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found with ID: " + courseId));

        // Associate the course with category
        course.setCategory(category); // Fix: Ensure Course is correctly linked to Category
        category.getCourses().add(course); // Add to category's list

        // Save both course and category
        courseRepository.save(course);  // Save course first to avoid transient issues
        categoryRepository.save(category);

        return modelMapper.map(category, CategoryDto.class);

    }
}
