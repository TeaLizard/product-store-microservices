package com.example.order_service.entity;

import com.example.order_service.client.ProductClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ProductClient productClient;

    public OrderMapper(ProductClient productClient) {
        this.productClient = productClient;
    }

    public OrderDTO toDTO(Order order) {
        List<ProductDTO> products = order.getProductIds().stream()
                .map(id -> {
                    ResponseEntity<ProductDTO> response = productClient.getById(id);
                    return response.getBody(); // unwrap ResponseEntity
                })
                .collect(Collectors.toList()); // This block maps the ids to ProductDTOs

        return new OrderDTO(
                order.getId(),
                products,
                order.getStatus(),
                order.getLocation()
        );
    }

    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());

        // Extract product IDs from ProductDTOs
        List<Integer> productIds = dto.getProducts().stream()
                .map(ProductDTO::getId)
                .collect(Collectors.toList()); // Maps ProductDTOs to id Integers

        order.setProductIds(productIds);
        order.setStatus(dto.getStatus());
        order.setLocation(dto.getLocation());

        return order;
    }
}


