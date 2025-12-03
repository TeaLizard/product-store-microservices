package com.example.cart_service.entity;

import com.example.cart_service.client.ProductClient;
import com.example.shared_contracts.dtos.CartDTO;
import com.example.shared_contracts.dtos.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final ProductClient productClient;

    public CartMapper(ProductClient productClient) {
        this.productClient = productClient;
    }

    public CartDTO toDto(Cart cart) {
        if (cart == null) return null;

        List<ProductDTO> products = cart.getProductIds().stream()
                .map(productId -> {
                    var response = productClient.getById(productId);
                    return response != null ? response.getBody() : null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList()); // Get Products from the list of ids

        return new CartDTO(cart.getId(), products);
    }

    public Cart toEntity(CartDTO dto) {
        if (dto == null) return null;

        Cart cart = new Cart();
        cart.setId(dto.getId());

        // Extract product IDs from ProductDTOs
        List<Integer> productIds = dto.getProducts().stream()
                .map(ProductDTO::getId)
                .collect(Collectors.toList());

        cart.setProductIds(productIds);
        return cart;
    }
}

