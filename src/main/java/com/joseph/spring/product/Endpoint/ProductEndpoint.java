package com.joseph.spring.product.Endpoint;

import com.joseph.spring.product.Repository.ProductRepository;
import io.spring.guides.gs_producing_web_service.GetProductRequest;
import io.spring.guides.gs_producing_web_service.GetProductResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "https://spring.io/guides/gs-producing-web-service";

    private final ProductRepository productRepository;

    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(ProductRepository.findProduct(request.getProduct()));

        return response;
    }
}