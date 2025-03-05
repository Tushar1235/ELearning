package com.elearn.journey.start_learning.Service;

import com.elearn.journey.start_learning.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {
    public List<CategoryDto> getAllCategory();
    public void createCategory(CategoryDto categoryDto);
    public CategoryDto getCategory(int categoryId);
    public CategoryDto updateCategory(CategoryDto categoryDto);
    public void deleteCategory(String title);
    public CategoryDto searchByTitle(String title);
    public CategoryDto
    addCourseToCategory(int categoryId, int courseId);

}
