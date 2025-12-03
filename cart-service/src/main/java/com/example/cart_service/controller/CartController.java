package com.example.cart_service.controller;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartMapper;
import com.example.cart_service.service.CartService;
import com.example.shared_contracts.dtos.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService service;
    private final CartMapper mapper;
    public CartController(CartService service, CartMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<CartDTO> get() {
        return ResponseEntity.ok(mapper.toDto(service.get()));
    }

    @PutMapping("/add-to-cart/{productId}")
    public ResponseEntity<CartDTO> addToCart(@PathVariable Integer productId) {
        service.addToCart(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remove-from-cart/{productId}")
    public ResponseEntity<CartDTO> removeFromCart(@PathVariable Integer productId) {
        service.removeFromCart(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/empty-cart")
    public ResponseEntity<CartDTO> emptyCart() {
        service.emptyCart();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout/{location}")
    public ResponseEntity<CartDTO> checkout(@PathVariable String location) {
        service.checkout(location);
        return ResponseEntity.noContent().build();
    }
}
