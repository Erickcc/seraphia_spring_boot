package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.dto.ColorSizeCategoryRequest;
import com.seraphia.seraphia.dto.ImagesRequest;
import com.seraphia.seraphia.model.Products;
import com.seraphia.seraphia.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/products")
@AllArgsConstructor
public class ProductsController {
    private ProductsService productsService;

    @GetMapping
    public List<Products> getAllProducts() {
        return this.productsService.getAllProducts();
    }

    @GetMapping(path = "all/products-with-stock")
    public List<Products> getAllProductsWithStock() {
        return this.productsService.getAllProductsWithStock();
    }

    @GetMapping(path = "{productId}")
    public Products getProductById(@PathVariable("productId") Long id) {
        return this.productsService.getProductById(id);
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return this.productsService.addProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public Products deleteProductById(@PathVariable("productId") Long id) {
        return this.productsService.deleteProductById(id);
    }

    @PutMapping(path = "{productId}/change-product")
    public Products updateProductById(@PathVariable("productId") Long id, @RequestBody Products productUpdated) {
        return productsService.updateProductById(id, productUpdated);
    }

//    @PostMapping(path = "{productId}/add-color")
//    public Products addProductColor(@PathVariable("productId") Long id, @RequestBody String newColor) {
//        return productsService.addProductColor(id, newColor);
//    }
//
//    @PostMapping(path = "{productId}/add-size")
//    public Products addProductSize(@PathVariable("productId") Long id, @RequestBody String newSize) {
//        return productsService.addProductSize(id, newSize);
//    }
//
//    @PostMapping(path = "{productId}/add-category")
//    public Products addProductCategory(@PathVariable("productId") Long id, @RequestBody String newCategory) {
//        return productsService.addProductCategory(id, newCategory);
//    }

    @PostMapping(path = "{productId}/add-color-size-category")
    public Products addColorSizeCategoryToProduct(@PathVariable("productId") Long id, @RequestBody ColorSizeCategoryRequest colorSizeCategoryRequest) {
        return productsService.addColorSizeCategoryToProduct(id, colorSizeCategoryRequest);
    }

    @PostMapping(path = "{productId}/add-images")
    public Products addImagesToProduct(@PathVariable("productId") Long id, @RequestBody ImagesRequest newImages) {
        return productsService.addImagesToProduct(id, newImages);
    }
}
