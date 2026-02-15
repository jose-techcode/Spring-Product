package com.joseph.spring.product.Service;

import com.joseph.spring.product.Model.Product;
import com.joseph.spring.product.Repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductService(ProductRepository productRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product create(Product product) {
        Product createdProduct = productRepository.save(product);
        kafkaTemplate.send("product", "Created product with ID: " + createdProduct.getId());
        kafkaTemplate.send("product", "Created product with name: " + createdProduct.getProduct());
        kafkaTemplate.send("product", "Created product with price: " + createdProduct.getPrice());
        kafkaTemplate.send("product", "Created product with quantity: " + createdProduct.getQuantity());
        return createdProduct;
    }

    public void update(Long id, Product product) {
        product.setId(id);
        productRepository.save(product);
        kafkaTemplate.send("product", "Updated product with ID: " + product.getId());
        kafkaTemplate.send("product", "Updated product with name: " + product.getProduct());
        kafkaTemplate.send("product", "Updated product with price: " + product.getPrice());
        kafkaTemplate.send("product", "Updated product with quantity: " + product.getQuantity());
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
        kafkaTemplate.send("product", "Deleted product with ID: " + id);
    }
}