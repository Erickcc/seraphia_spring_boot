package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem addItemToCart(Long productId, Long cartId);

    List<CartItem> getCartItemsByUserId(Long userId);

    void deleteCartItemById(Long itemId);
}
