package com.joseph.spring.product.Controller;

import com.joseph.spring.product.Model.Product;
import com.joseph.spring.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductControllerGraphQL {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @QueryMapping
    public Product getById(@Argument Long id) {
        return productService.getById(id);
    }

    @MutationMapping
    public Product create(@Argument Product product) {
        return productService.create(product);
    }

    @MutationMapping
    public Product update(@Argument Long id, @Argument Product product) {
        productService.update(id, product);
        return product;
    }

    @MutationMapping
    public void delete(@Argument Long id) {
        productService.delete(id);
    }
}