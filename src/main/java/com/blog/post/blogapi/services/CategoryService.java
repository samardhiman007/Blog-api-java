package com.blog.post.blogapi.services;

import com.blog.post.blogapi.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

     void deleteCategory(Integer categoryId);

     CategoryDto getCategoryById(Integer categoryId);

     List<CategoryDto> getAllCategory();
}
