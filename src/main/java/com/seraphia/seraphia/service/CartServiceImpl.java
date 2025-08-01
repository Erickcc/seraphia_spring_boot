package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Cart;
import com.seraphia.seraphia.model.User;
import com.seraphia.seraphia.repository.CartRepository;
import com.seraphia.seraphia.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public Cart createCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (cartRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("El usuario ya tiene un carrito");
        }

        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario"));
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCartById(Long id, Cart updatedCart) {
        Cart existing = getCartById(id);
        existing.setModificationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        return cartRepository.save(existing);
    }

    @Override
    public Cart deleteCartById(Long id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
        return cart;
    }

    @Override
    public Cart getOrCreateCartByUserId(Long userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Cart newCart = new Cart(user);
            return cartRepository.save(newCart);
        }
    }
}
