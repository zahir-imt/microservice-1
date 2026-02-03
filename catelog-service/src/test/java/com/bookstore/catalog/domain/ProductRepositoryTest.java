package com.bookstore.catalog.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.bookstore.catalog.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:postgresql:18-alpine:///db",
})
@Sql("/test-data.sql")
//@Import(TestcontainersConfiguration.class)
class ProductRepositoryTest {

    @Autowired ProductRepository productRepository;

    @Test
    void shouldGetAllProducts(){
        List<ProductEntity> list=  productRepository.findAll();
        assertThat(list).hasSize(15);
    }

}
