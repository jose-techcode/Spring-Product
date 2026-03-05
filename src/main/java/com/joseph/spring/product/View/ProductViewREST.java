package com.joseph.spring.product.View;

import com.joseph.spring.product.Model.Product;
import com.joseph.spring.product.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product/view")
public class ProductViewREST {

    private final ProductService productService;

    public ProductViewREST(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        Product product;
        if (id != null) {
            Product foundProduct = productService.getById(id);
            if (foundProduct == null) {
                return "redirect:/product/view";
            }
            product = foundProduct;
            model.addAttribute("isEdit", true);
        } else {
            product = new Product();
            model.addAttribute("isEdit", false);
        }

        model.addAttribute("product", product);
        return "form";
    }

    @PostMapping
    public String save(@RequestParam String product, @RequestParam BigDecimal price, @RequestParam Integer quantity, @RequestParam(required = false) Long id) {
        Product prod = new Product();
        prod.setId(id);
        prod.setProduct(product);
        prod.setPrice(price);
        prod.setQuantity(quantity);

        if (id > 0) {
            productService.update(id, prod);
        } else {
            productService.create(prod);
        }

        return "redirect:/product/view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product/view";
    }
}
