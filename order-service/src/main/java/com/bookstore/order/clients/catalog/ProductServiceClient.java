package com.bookstore.order.clients.catalog;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductServiceClient {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;

    ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallback")
    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);

        var product =
                restClient.get().uri("/api/products/{code}", code).retrieve().body(Product.class);
        return Optional.ofNullable(product);
    }
    /*
    @Retry log - retried 3 times
    20:17:03 [INFO ] c.b.o.c.catalog.ProductServiceClient - Fetching product for code: P100
    20:17:08 [INFO ] c.b.o.c.catalog.ProductServiceClient - Fetching product for code: P100
    20:17:14 [INFO ] c.b.o.c.catalog.ProductServiceClient - Fetching product for code: P100
     */

    /* For re-try we cant use try-catch block
    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);
        try {
            var product = restClient
                    .get()
                    .uri("/api/products/{code}", code)
                    .retrieve()
                    .body(Product.class);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            log.error("Error fetching product for code: {}", code, e);
            return Optional.empty();
        }
    }
    */

    Optional<Product> getProductByCodeFallback(String code, Throwable t) {
        log.info("catalog-service get product by code fallback: code:{}, Error: {} ", code, t.getMessage());
        return Optional.empty();
    }
}
