package com.example.product_service.controller;

import com.example.product_service.client.InventoryClient;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.ProductMapper;
import com.example.product_service.service.IProductService;
import com.example.shared_contracts.dtos.InventoryDTO;
import com.example.shared_contracts.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService service;
    private final ProductMapper mapper;
    private final InventoryClient inventoryClient;

    public ProductController(IProductService service, ProductMapper mapper,  InventoryClient inventoryClient) {
        this.service = service;
        this.mapper = mapper;
        this.inventoryClient = inventoryClient;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        var products = service.getAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var dtos = products.stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDto(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO productDTO) {
        var product = mapper.toEntity(productDTO);
        product.setId(null);
        var newProduct = service.add(product);
        var newInventory =  new InventoryDTO();
        newInventory.setProductId(newProduct.getId());
        newInventory.setQuantity(productDTO.getQuantity());
        inventoryClient.add(newProduct.getId(), newInventory);
        return ResponseEntity.ok(mapper.toDto(newProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        var product = mapper.toEntity(productDTO);
        product.setId(id);
        service.update(product);
        return ResponseEntity.ok(mapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
