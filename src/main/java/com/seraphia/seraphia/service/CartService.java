package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getCartById(Long id);
    Cart getCartByUserId(Long userId);
    Cart addCart(Cart cart);
    Cart updateCartById(Long id, Cart updatedCart);
    Cart deleteCartById(Long id);
    Cart createCart(Long userId);
}
