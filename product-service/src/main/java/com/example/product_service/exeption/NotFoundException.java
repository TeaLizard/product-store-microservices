package com.example.product_service.exeption;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String itemType, int itemId) {
        super(itemType + " with id " + itemId + " not found");
    }
}
