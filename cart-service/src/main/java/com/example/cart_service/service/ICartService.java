package com.example.cart_service.service;

import com.example.cart_service.entity.Cart;
import com.example.shared_contracts.dtos.OrderDTO;
import com.example.shared_contracts.dtos.ProductDTO;

public interface ICartService {
    Cart get();
    Cart add(Cart cart);
    void update(Cart cart);
    void delete(Cart cart);

    void addToCart(Integer productId);
    void removeFromCart(Integer productId);
    void emptyCart();
    OrderDTO checkout(String location);
}
