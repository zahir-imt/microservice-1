/*
package com.bookstore.catalog.config;


@Configuration
class OpenAPI3Configuration {

    @Value("${swagger.api-gateway-url}")
    String apiGatewayUrl;

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalog Service APIs")
                        .description("BookStore Catalog Service APIs")
                        .version("v1.0.0")
                        .contact(new Contact().name("SivaLabs").email("sivalabs@sivalabs.in")))
                .servers(List.of(new Server().url(apiGatewayUrl)));
    }
}
*/
