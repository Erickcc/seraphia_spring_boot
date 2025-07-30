package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.ColorSizeCategoryRequest;
import com.seraphia.seraphia.dto.ImagesRequest;
import com.seraphia.seraphia.model.Products;

import java.util.List;

public interface ProductsService {
    List<Products> getAllProducts();

    Products getProductById(Long id);

    Products addProduct(Products product);

    Products deleteProductById(Long id);

    Products updateProductById(Long id, Products productUpdated);

//    Products addProductColor(Long id, String newColor);
//
//    Products addProductSize(Long id, String newSize);
//
//    Products addProductCategory(Long id, String newCategory);

    Products addColorSizeCategoryToProduct(Long id, ColorSizeCategoryRequest colorSizeCategoryRequest);

    Products addImagesToProduct(Long id, ImagesRequest newImages);
}
