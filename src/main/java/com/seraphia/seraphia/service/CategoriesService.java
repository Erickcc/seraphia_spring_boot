package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> getAllCategories();

    Categories getCategoryById(Long id);

    Categories addCategory(Categories category);

    Categories deleteCategoryById(Long id);

    Categories updateCategoryById(Long id, Categories categoryUpdated);

}
