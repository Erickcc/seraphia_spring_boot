package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.dto.AddCartItemRequestDTO;
import com.seraphia.seraphia.model.CartItem;
import com.seraphia.seraphia.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/cart-items")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody AddCartItemRequestDTO request) {
        try {
            CartItem cartItem = cartItemService.addItemToCart(
                    request.getProductId(),
                    request.getColorId(),
                    request.getSizeId(),
                    request.getCartId(),
                    request.getQuantity(),
                    request.getCategory()
            );
            return ResponseEntity.ok(cartItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Datos inválidos o error: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getItemsByUserId(@PathVariable Long userId) {
        try {
            List<CartItem> items = cartItemService.getCartItemsByUserId(userId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener los items del carrito: " + e.getMessage());
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long itemId) {
        cartItemService.deleteCartItemById(itemId);
        return ResponseEntity.noContent().build();
    }
}
