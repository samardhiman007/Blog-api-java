package com.blog.post.blogapi.controllers;

import com.blog.post.blogapi.payloads.ApiResponse;
import com.blog.post.blogapi.payloads.CategoryDto;
import com.blog.post.blogapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1,HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto categoryDto1=this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    @DeleteExchange("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtoList=this.categoryService.getAllCategory();

        return new ResponseEntity<List<CategoryDto>>(categoryDtoList,HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        return new ResponseEntity<CategoryDto>(this.categoryService.getCategoryById(catId),HttpStatus.OK);
    }


}
