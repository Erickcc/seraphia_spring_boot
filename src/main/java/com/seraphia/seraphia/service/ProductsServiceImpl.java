package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.ColorSizeCategoryRequest;
import com.seraphia.seraphia.dto.ImagesRequest;
import com.seraphia.seraphia.model.*;
import com.seraphia.seraphia.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final ColorsRepository colorsRepository;
    private final SizesRepository sizesRepository;
    private final CategoriesRepository categoriesRepository;
    private final ImagesRepository imagesRepository;

    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(Long id) {
        return productsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con el id " + id + " no existe")
        );
    }

    @Override
    public Products addProduct(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public Products deleteProductById(Long id) {
        Products tmp = null;
        if (!productsRepository.existsById(id)) return tmp;
        tmp = productsRepository.findById(id).get();
        productsRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Products updateProductById(Long id, Products productUpdated) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isEmpty())
            throw new IllegalArgumentException("El producto con el id " + id + " no existe");
        Products originalProduct = optionalProduct.get();
        if (productUpdated.getName() != null) originalProduct.setName(productUpdated.getName());
        if (productUpdated.getDescription() != null) originalProduct.setDescription(productUpdated.getDescription());
        if (productUpdated.getPrice() != null) originalProduct.setPrice(productUpdated.getPrice());
        if (productUpdated.getStock() != null) originalProduct.setStock(productUpdated.getStock());
        if (productUpdated.getIsAvailable() != null) originalProduct.setIsAvailable(productUpdated.getIsAvailable());
        // creation date is skipped because it is only defined when the product is created, not when it is modified
        return productsRepository.save(originalProduct);
    }

//    @Override
//    public Products addProductColor(Long id, String newColor) {
//        Products product = productsRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("El producto con el id " + id + " no existe")
//        );
//        Optional<Colors> optionalColor = colorsRepository.findByColorNameContaining("Rojo");
//        if (optionalColor.isEmpty()) throw new IllegalArgumentException("El color " + newColor + " no existe");
//        Colors originalColor = optionalColor.get();
//
//        product.setColor(originalColor);
//        originalColor.getProductsList().add(product);
//        return productsRepository.save(product);
//    }
//
//    @Override
//    public Products addProductSize(Long id, String newSize) {
//        Optional<Products> optionalProduct = productsRepository.findById(id);
//        if (optionalProduct.isEmpty()) throw new IllegalArgumentException("El producto con el id " + id + " no existe");
//        Optional<Sizes> optionalSize = sizesRepository.findBySizeNameContaining("Chica");
//        if (optionalSize.isEmpty())
//            throw new IllegalArgumentException("El tamaño con el nombre " + newSize + " no existe");
//
//        Products originalProduct = optionalProduct.get();
//        Sizes originalSize = optionalSize.get();
//        originalProduct.setSize(originalSize);
//        originalSize.getProductsList().add(originalProduct);
//        return productsRepository.save(originalProduct);
//    }
//
//    @Override
//    public Products addProductCategory(Long id, String newCategory) {
//        Optional<Products> optionalProduct = productsRepository.findById(id);
//        if (optionalProduct.isEmpty()) throw new IllegalArgumentException("El producto con el id " + id + " no existe");
//        Optional<Categories> optionalCategory = categoriesRepository.findByCategoryNameIs("Blusa");
//        if (optionalCategory.isEmpty())
//            throw new IllegalArgumentException("El tamaño con el nombre " + newCategory + " no existe");
//
//        Products originalProduct = optionalProduct.get();
//        Categories originalCategory = optionalCategory.get();
//        originalProduct.setCategory(originalCategory);
//        originalCategory.getProductsList().add(originalProduct);
//        return productsRepository.save(originalProduct);
//    }

    @Override
    public Products addColorSizeCategoryToProduct(Long id, ColorSizeCategoryRequest colorSizeCategoryRequest) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new IllegalArgumentException("El producto con el id " + id + " no existe");

        Optional<Colors> optionalColor = colorsRepository.findByColorNameIs(colorSizeCategoryRequest.getColorName());
        if (optionalColor.isEmpty())
            throw new IllegalArgumentException("El color " + colorSizeCategoryRequest.getColorName() + " no existe");

        Optional<Sizes> optionalSize = sizesRepository.findBySizeNameIs(colorSizeCategoryRequest.getSizeName());
        if (optionalSize.isEmpty())
            throw new IllegalArgumentException("El tamaño con el nombre " + colorSizeCategoryRequest.getSizeName() + " no existe");

        Optional<Categories> optionalCategory = categoriesRepository.findByCategoryNameIs(colorSizeCategoryRequest.getCategoryName());
        if (optionalCategory.isEmpty())
            throw new IllegalArgumentException("El tamaño con el nombre " + colorSizeCategoryRequest.getCategoryName() + " no existe");

        Products originalProduct = optionalProduct.get();
        Colors originalColor = optionalColor.get();
        Sizes originalSize = optionalSize.get();
        Categories originalCategory = optionalCategory.get();

        originalProduct.setColor(originalColor);
        originalProduct.setSize(originalSize);
        originalProduct.setCategory(originalCategory);

        originalColor.getProductsList().add(originalProduct);
        originalSize.getProductsList().add(originalProduct);
        originalCategory.getProductsList().add(originalProduct);

        return productsRepository.save(originalProduct);
    }

    @Override
    public Products addImagesToProduct(Long id, ImagesRequest newImages) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new IllegalArgumentException("El producto con el id " + id + " no existe");
        Products originalProduct = optionalProduct.get();
        List<Images> originalImages = newImages.getImages();

        if (!originalImages.isEmpty()) imagesRepository.deleteByProductId(id);

        for (Images image : originalImages) {
            if (image.getImageUrl() != null && image.getImageOrder() != null) {
                Images imageTemp = new Images();
                imageTemp.setProduct(originalProduct);
                imageTemp.setImageUrl(image.getImageUrl());
                imageTemp.setImageOrder(image.getImageOrder());

                imagesRepository.save(imageTemp);

                originalProduct.getImagesList().add(imageTemp);
            }
//            if (image.getImageUrl() != null) originalImages.add(image.getImageUrl())
//            if (image.getImageOrder() != null) originalImages.add(image.getImageOrder());
        }
//        imagesRepository.saveAll(originalProduct.getImagesList());

//
//        Products originalProduct = optionalProduct.get();
//
//        imagesRepository.save(image);
//        originalProduct.getImagesList().add(image);
        return productsRepository.save(originalProduct);


//        originalProduct.setImagesList();
//        Optional<Images> optionalImages = imagesRepository.findByColorNameIs(colorSizeCategoryRequest.getColorName());
//        if (optionalColor.isEmpty())
//            throw new IllegalArgumentException("El color " + colorSizeCategoryRequest.getColorName() + " no existe");
    }
}