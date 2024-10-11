package com.samruddhi.fit_buy.controller;

import com.samruddhi.fit_buy.model.Category;
import com.samruddhi.fit_buy.response.ApiResponse;
import com.samruddhi.fit_buy.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor //for constructor injection
@RestController
@RequestMapping("${api.prefix}/image")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/categories/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found!", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", INTERNAL_SERVER_ERROR));
        }
    }

    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name){
        Category theCategory = categoryService.addCategory(name);
    }
}
