package com.bookstore.catalog.web.controllers;

import com.bookstore.catalog.domain.PagesResult;
import com.bookstore.catalog.domain.Product;
import com.bookstore.catalog.domain.ProductNotFoundException;
import com.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagesResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        // sleep(); tun it on for testing of resiliency (circuit breaker/ retry mechanism)
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }

    void sleep() {
        try {
            Thread.sleep(6000); // >5 sec will give IO Read timeout error
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
