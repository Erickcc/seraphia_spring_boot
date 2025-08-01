package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.*;
import com.seraphia.seraphia.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final ProductsRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addItemToCart(Long productId, Long cartId) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setCart(cart);
        item.setColor(product.getColor());
        item.setSize(product.getSize());
        item.setQuantity(1);  // Siempre 1
        item.setCategory(product.getCategory() != null ? product.getCategory().getCategoryName() : "N/A");

        return cartItemRepository.save(item);
    }


    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return cartItemRepository.findByCartId(cart.getId());
    }

    @Override
    public void deleteCartItemById(Long itemId) {
        if (!cartItemRepository.existsById(itemId)) {
            throw new RuntimeException("El item no existe");
        }
        cartItemRepository.deleteById(itemId);
    }
}
