package com.example.cart_service.entity;

import com.example.cart_service.client.OrderClient;
import com.example.shared_contracts.dtos.CartDTO;
import com.example.shared_contracts.dtos.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class CartToOrderMapper {
    private final OrderClient orderClient;
    public CartToOrderMapper(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    public OrderDTO toOrderDTO(CartDTO cartDTO, String location) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(null);
        orderDTO.setProducts(cartDTO.getProducts());
        orderDTO.setLocation(location);
        orderDTO.setStatus("Pending");
        return orderDTO;
    }
}
