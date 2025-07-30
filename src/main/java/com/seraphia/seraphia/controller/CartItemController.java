package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.model.CartItem;
import com.seraphia.seraphia.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//http://localhost:8080/api/categories
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/cart-items")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    // POST: Agregar un nuevo item al carrito
    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody Map<String, Object> requestData) {
        try {
            Long productId = Long.valueOf(requestData.get("productId").toString());
            Long colorId = Long.valueOf(requestData.get("colorId").toString());
            Long sizeId = Long.valueOf(requestData.get("sizeId").toString());
            Long cartId = Long.valueOf(requestData.get("cartId").toString());
            Integer quantity = Integer.valueOf(requestData.get("quantity").toString());
            String category = requestData.get("category").toString();

            CartItem cartItem = cartItemService.addItemToCart(productId, colorId, sizeId, cartId, quantity, category);
            return ResponseEntity.ok(cartItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Datos inválidos o error: " + e.getMessage());
        }
    }

    // GET: Obtener todos los items del carrito de un usuario (usado por el frontend al cargar)
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getItemsByUserId(@PathVariable Long userId) {
        try {
            List<CartItem> items = cartItemService.getCartItemsByUserId(userId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener los items del carrito: " + e.getMessage());
        }
    }

    // DELETE: Eliminar un item del carrito (usado por el botón eliminar)
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long itemId) {
        cartItemService.deleteCartItemById(itemId);
        return ResponseEntity.noContent().build();
    }
}
