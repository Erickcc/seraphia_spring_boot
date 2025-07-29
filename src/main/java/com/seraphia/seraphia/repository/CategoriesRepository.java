package com.seraphia.seraphia.repository;

import com.seraphia.seraphia.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findByCategoryNameIs(String category);
}
