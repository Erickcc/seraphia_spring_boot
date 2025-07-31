package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.model.Categories;
import com.seraphia.seraphia.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/api/categories
@CrossOrigin
@RestController
@RequestMapping(path = "api/categories")
@AllArgsConstructor
public class CategoriesController {
    private CategoriesService categoriesService;

    @GetMapping
    public List<Categories> getAllCategories() {
        return this.categoriesService.getAllCategories();
    }

    @GetMapping(path = "{categoryId}")
    public Categories getCategoryById(@PathVariable("categoryId") Long id) {
        return this.categoriesService.getCategoryById(id);
    }

    @PostMapping
    public Categories addCategory(@RequestBody Categories category) {
        return this.categoriesService.addCategory(category);
    }

    @DeleteMapping(path = "{categoryId}")
    public Categories deleteCategoryById(@PathVariable("categoryId") Long id) {
        return this.categoriesService.deleteCategoryById(id);
    }

    @PutMapping(path = "{categoryId}/change-category")
    public Categories updateCategoryById(@PathVariable("categoryId") Long id, @RequestBody Categories categoryUpdated) {
        return categoriesService.updateCategoryById(id, categoryUpdated);
    }
}
