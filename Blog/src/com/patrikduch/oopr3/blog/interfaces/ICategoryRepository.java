package com.patrikduch.oopr3.blog.interfaces;

import com.patrikduch.oopr3.blog.model.Category;

import java.util.List;

public interface ICategoryRepository {

    List<Category> getCategories();

    void addCategory(Category category);
    void deleteCategory(int id);
    Category getCategoryByName(String inputCategoryName);
    Category getCategoryById(int id);

}
