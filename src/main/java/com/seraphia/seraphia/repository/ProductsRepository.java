package com.seraphia.seraphia.repository;

import com.seraphia.seraphia.model.Colors;
import com.seraphia.seraphia.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findByStockGreaterThanEqual(Integer stock);
}
