package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.model.Colors;
import com.seraphia.seraphia.model.Products;
import com.seraphia.seraphia.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
@AllArgsConstructor
public class ProductsController {
    private ProductsService productsService;

    @GetMapping
    public List<Products> getAllProducts() {
        return this.productsService.getAllProducts();
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

    @PostMapping(path = "{productId}/add-color")
    public Products addProductColor(@PathVariable("productId") Long id, @RequestBody String newColor) {
        return productsService.addProductColor(id, newColor);
    }
}
