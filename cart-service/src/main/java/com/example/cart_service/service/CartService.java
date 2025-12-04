package com.example.cart_service.service;

import com.example.cart_service.client.OrderClient;
import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartMapper;
import com.example.cart_service.entity.CartToOrderMapper;
import com.example.cart_service.exeption.NotFoundException;
import com.example.cart_service.repository.CartRepository;
import com.example.shared_contracts.dtos.OrderDTO;
import com.example.shared_contracts.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    private final int CART_ID = 1;
    private final CartRepository repository;
    private final OrderClient orderClient;
    private final CartMapper cartMapper;
    private final CartToOrderMapper cartToOrderMapper;
    public CartService(
            CartRepository repository,
            OrderClient orderClient,
            CartMapper cartMapper,
            CartToOrderMapper cartToOrderMapper
    ) {
        this.repository = repository;
        this.orderClient = orderClient;
        this.cartMapper = cartMapper;
        this.cartToOrderMapper = cartToOrderMapper;
    }

    @Override
    public Cart get() {
        return repository.findById(CART_ID).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setId(CART_ID);
            cart.setProductIds(new ArrayList<>());
            return repository.save(cart);
        });
    }

    @Override
    public Cart add(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public void update(Cart cart) {
        var old = repository.findById(CART_ID).orElseThrow(
                () -> new NotFoundException("Cart", CART_ID)
        );
        old.setProductIds(cart.getProductIds());
        repository.save(old);
    }

    @Override
    public void delete(Cart cart) {
        repository.delete(cart);
    }

    @Override
    public void addToCart(Integer productId) {
        var old = repository.findById(CART_ID).orElseThrow(
                () -> new NotFoundException("Cart", CART_ID)
        );
        var productsIds = old.getProductIds();
        productsIds.add(productId);
        old.setProductIds(productsIds);
        repository.save(old);
    }

    @Override
    public void removeFromCart(Integer productId) {
        var old = repository.findById(CART_ID).orElseThrow(
                () -> new NotFoundException("Cart", CART_ID)
        );
        var productsIds = old.getProductIds();
        productsIds.remove(productId);
        old.setProductIds(productsIds);
        repository.save(old);
    }

    @Override
    public void emptyCart() {
        var old = repository.findById(CART_ID).orElseThrow(
                () -> new NotFoundException("Cart", CART_ID)
        );
        old.setProductIds(new ArrayList<>());
        repository.save(old);
    }

    @Override
    public OrderDTO checkout(String location) {
        var cart = cartMapper.toDto(get());
        var order = cartToOrderMapper.toOrderDTO(cart, location);
        var response = orderClient.createOrder(order);
        emptyCart();
        return response.getBody();
    }

    private void initCart() {
        if (repository.count() == 0) {
            var cart = new Cart();
            cart.setId(CART_ID);
            cart.setProductIds(new ArrayList<Integer>());
            repository.save(cart);
        }
    }
}
