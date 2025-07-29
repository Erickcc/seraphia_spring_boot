package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Colors;
import com.seraphia.seraphia.model.Products;
import com.seraphia.seraphia.repository.ColorsRepository;
import com.seraphia.seraphia.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final ColorsRepository colorsRepository;

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

    @Override
    public Products addProductColor(Long id, String newColor) {
        Products product = productsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con el id " + id + " no existe")
        );
        Optional<Colors> optionalColor = colorsRepository.findByColorNameContaining("Rojo");
        if (optionalColor.isEmpty()) throw new IllegalArgumentException("El color " + newColor + " no existe");
        Colors originalColor = optionalColor.get();

        product.setColor(originalColor);
        originalColor.getProductsList().add(product);
        return productsRepository.save(product);
    }
}