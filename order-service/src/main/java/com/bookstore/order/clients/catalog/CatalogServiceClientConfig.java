package com.bookstore.order.clients.catalog;

import com.bookstore.order.ApplicationProperties;
import java.time.Duration;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class CatalogServiceClientConfig {

    @Bean
    RestClient restClient(ApplicationProperties properties) {
        // Resilience Timeout pattern
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactoryBuilder.simple()
                .withCustomizer(customizer -> {
                    customizer.setConnectTimeout(Duration.ofSeconds(5));
                    customizer.setReadTimeout(Duration.ofSeconds(5));
                })
                .build();

        return RestClient.builder()
                .baseUrl(properties.catalogServiceUrl())
                .requestFactory(requestFactory)
                .build();
    }
}
