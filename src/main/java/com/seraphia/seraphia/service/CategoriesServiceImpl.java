package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Categories;
import com.seraphia.seraphia.repository.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategoryById(Long id) {
        return categoriesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La categoria con id " + id + " no existe")
        );
    }

    @Override
    public Categories addCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    @Override
    public Categories deleteCategoryById(Long id) {
        Categories tmp = null;
        if (!categoriesRepository.existsById(id)) return tmp;
        tmp = categoriesRepository.findById(id).get();
        categoriesRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Categories updateCategoryById(Long id, Categories categoryUpdated) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new IllegalArgumentException("La categoria con el id " + id + " no existe");
        Categories originalCategory = optionalCategory.get();
        if (categoryUpdated.getCategoryName() != null)
            originalCategory.setCategoryName(categoryUpdated.getCategoryName());
        return categoriesRepository.save(originalCategory);
    }
}
