package com.joseph.spring.product.Service;

import com.joseph.spring.product.Model.Product;
import com.joseph.spring.product.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void update(Long id, Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}