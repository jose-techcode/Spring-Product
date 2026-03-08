package com.joseph.spring.product.WebService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration(proxyBeanMethods = false)
public class ProductWebServiceConfig {

    @Bean
    public DefaultWsdl11Definition products(SimpleXsdSchema productsWs) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductsPort");
        wsdl11Definition.setLocationUri("/services");
        wsdl11Definition.setTargetNamespace("https://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(productsWs);
        return wsdl11Definition;
    }

}