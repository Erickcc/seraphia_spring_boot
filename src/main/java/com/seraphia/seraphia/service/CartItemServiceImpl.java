package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.*;
import com.seraphia.seraphia.repository.*;
import com.seraphia.seraphia.service.CartItemService;
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
        // Buscar producto, color, talla y carrito o lanzar excepción si no existen
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Colors color = colorRepository.findById(colorId)
                .orElseThrow(() -> new RuntimeException("Color no encontrado"));

        Sizes size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("Talla no encontrada"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Crear el nuevo CartItem y asignar valores
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setColor(color);
        cartItem.setSize(size);
        cartItem.setCart(cart);
        cartItem.setQuantity(quantity);
        cartItem.setCategory(category);
        cartItem.setUserId(cart.getUserId());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario con ID: " + userId));

        return cartItemRepository.findByCartId(cart.getId());
    }

    @Override
    public void deleteCartItemById(Long itemId) {
        if (!cartItemRepository.existsById(itemId)) {
            throw new RuntimeException("El item con ID " + itemId + " no existe");
        }

        cartItemRepository.deleteById(itemId);
    }
}
