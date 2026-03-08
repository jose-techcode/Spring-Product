package com.joseph.spring.product.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import io.spring.guides.gs_producing_web_service.Product;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ProductRepository {

    private static final Map<String, Product> products = new HashMap<>();

    static {
        Product product1 = new Product();
        product1.setName("Coffee");
        product1.setPrice(BigDecimal.valueOf(2.99));
        product1.setQuantity(100);

        products.put(product1.getName(), product1);
    }
        public static Product findProduct(String name){
            Assert.notNull(name, "The country's name must not be null");
            return products.get(name);
        }
}