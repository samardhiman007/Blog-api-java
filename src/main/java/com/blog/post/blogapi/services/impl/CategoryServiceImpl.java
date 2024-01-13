package com.blog.post.blogapi.services.impl;

import com.blog.post.blogapi.entities.Category;
import com.blog.post.blogapi.exceptions.ResourceNotFoundException;
import com.blog.post.blogapi.payloads.CategoryDto;
import com.blog.post.blogapi.repositories.CategoryRepo;
import com.blog.post.blogapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
            Category category=this.modelMapper.map(categoryDto, Category.class);
            Category category1=this.categoryRepo.save(category);

        return this.modelMapper.map(category1,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.get(categoryId)
                    .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        Category category1=this.categoryRepo.save(category);


        return this.modelMapper.map(category1,CategoryDto.class);
    }

    public Optional<Category> get(Integer categoryId){
        return categoryRepo.findById(categoryId);
    }
    @Override
    public void deleteCategory(Integer categoryId) {
        Category category =this.get(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        this.categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category =this.get(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));



        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList=this.categoryRepo.findAll();
           List<CategoryDto> categoryDtoList= categoryList.stream()
                   .map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());

        return categoryDtoList;
    }
}
