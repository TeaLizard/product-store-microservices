package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.exeption.NotFoundException;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Product", id)
        );
    }

    @Override
    public Product add(Product product) {
        repository.save(product);
        return product;
    }

    @Override
    public void update(Product product) {
        var old = repository.findById(product.getId()).orElseThrow(
                () -> new NotFoundException("Product", product.getId())
        );
        old.setName(product.getName());
        old.setDescription(product.getDescription());
        old.setPrice(product.getPrice());
        old.setSku(product.getSku());
        old.setImage(product.getImage());
        repository.save(old);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
