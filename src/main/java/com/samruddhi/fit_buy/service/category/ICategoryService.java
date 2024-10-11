package com.samruddhi.fit_buy.service.category;

import com.samruddhi.fit_buy.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String Name);
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
    List<Category> getAllCategories();
}
