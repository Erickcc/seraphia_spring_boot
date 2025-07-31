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
    private final ColorsRepository colorRepository;
    private final SizesRepository sizeRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addItemToCart(Long productId, Long colorId, Long sizeId, Long cartId, Integer quantity, String category) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Colors color = colorRepository.findById(colorId)
                .orElseThrow(() -> new RuntimeException("Color no encontrado"));
        Sizes size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("Talla no encontrada"));
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setColor(color);
        item.setSize(size);
        item.setCart(cart);
        item.setQuantity(quantity);
        item.setCategory(category);

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
