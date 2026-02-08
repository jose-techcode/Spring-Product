package com.joseph.spring.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joseph.spring.product.Model.Product;
import com.joseph.spring.product.Repository.ProductRepository;
import com.joseph.spring.product.Service.ProductService;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class ProductApplicationTests {

    @BeforeAll
    public static void Database_Environment_Variable() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @MockitoBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testGetAll() throws Exception {
        Product product1 = new Product();
        product1.setProduct("Coffee");
        product1.setPrice(BigDecimal.valueOf(2.99));
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setProduct("Tea");
        product2.setPrice(BigDecimal.valueOf(1.99));
        product2.setQuantity(5);

        List<Product> products = Arrays.asList(product1, product2);
        Mockito.when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = productService.getAll();
        Assertions.assertEquals(2, foundProducts.size());

        mockMvc.perform(get("/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("product-GetAll"));
    }

    @Test
    public void testGetById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setProduct("Coffee");
        product.setPrice(BigDecimal.valueOf(2.99));
        product.setQuantity(10);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getById(1L);
        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals("Coffee", foundProduct.getProduct());

        mockMvc.perform(get("/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("product-GetById"));
    }

    @Test
    public void testCreate() throws Exception {
        Product product = new Product();
        product.setProduct("Coffee");
        product.setPrice(BigDecimal.valueOf(2.99));
        product.setQuantity(10);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        Product savedProduct = productService.create(product);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals("Coffee", savedProduct.getProduct());
        Assertions.assertEquals(BigDecimal.valueOf(2.99), savedProduct.getPrice());
        Assertions.assertEquals(10, savedProduct.getQuantity());

        String json = new ObjectMapper().writeValueAsString(product);

        mockMvc.perform(post("/product").contentType((MediaType.APPLICATION_JSON)).content(json))
                .andExpect(status().isCreated())
                .andDo(document("product-Create"));
    }

    @Test
    public void testUpdate() throws Exception {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setProduct("Coffee");
        existingProduct.setPrice(BigDecimal.valueOf(2.99));
        existingProduct.setQuantity(10);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        Product updatedProduct = new Product();
        updatedProduct.setProduct("Tea");
        updatedProduct.setPrice(BigDecimal.valueOf(1.99));
        updatedProduct.setQuantity(5);

        productService.update(1L, updatedProduct);

        Mockito.verify(productRepository, Mockito.times(1)).save(updatedProduct);

        String json = new ObjectMapper().writeValueAsString(updatedProduct);

        mockMvc.perform(put("/product/{id}", 1L).contentType((MediaType.APPLICATION_JSON)).content(json))
                .andExpect(status().isOk())
                .andDo(document("product-Update"));
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(new Product()));

        productService.delete(id);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);

        mockMvc.perform(delete("/product/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(document("product-Delete"));
    }
}