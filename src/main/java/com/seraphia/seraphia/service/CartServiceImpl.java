package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Cart;
import com.seraphia.seraphia.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    @Override
    public Cart createCart(Long userId) {
        Cart cart = new Cart(userId); // Usa el constructor que inicializa las fechas
        return cartRepository.save(cart);
    }
    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No se encontró un carrito para el usuario con ID " + userId));
    }
    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCartById(Long id, Cart updatedCart) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));

        existingCart.setUserId(updatedCart.getUserId());
        return cartRepository.save(existingCart);
    }

    @Override
    public Cart deleteCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));
        cartRepository.delete(cart);
        return cart;
    }
}
