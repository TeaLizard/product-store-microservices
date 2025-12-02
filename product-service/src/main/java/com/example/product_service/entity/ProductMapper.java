package com.example.product_service.entity;

import com.example.product_service.client.InventoryClient;
import com.example.shared_contracts.dtos.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final InventoryClient inventoryClient;
    public ProductMapper(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public ProductDTO toDto(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();

        var inventoryResponse = inventoryClient.getById(product.getId());
        if (inventoryResponse == null) {
            throw new RuntimeException("Inventory Not Found");
        }
        var inventory = inventoryResponse.getBody();
        if (inventory == null || inventory.getQuantity() == null) {
            throw new RuntimeException("Inventory Not Found");
        }

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setSku(product.getSku());
        dto.setImage(product.getImage());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setSku(dto.getSku());
        product.setImage(dto.getImage());
        return product;
    }
}