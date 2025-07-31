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

    // Agregar item al carrito: solo productId y cartId
    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody AddCartItemRequestDTO request) {
        try {
            CartItem item = cartItemService.addItemToCart(
                    request.getProductId(),
                    request.getCartId()
            );
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al agregar al carrito: " + e.getMessage());
        }
    }

    // Obtener items del carrito de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getItemsByUserId(@PathVariable Long userId) {
        try {
            List<CartItem> items = cartItemService.getCartItemsByUserId(userId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al obtener items: " + e.getMessage());
        }
    }

    // Eliminar un item del carrito
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long itemId) {
        try {
            cartItemService.deleteCartItemById(itemId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al eliminar item: " + e.getMessage());
        }
    }
}
