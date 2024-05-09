package com.study.springjrproj;

import com.study.springjrproj.anotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@IT
public abstract class IntegrationTestBase {
    private static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.1")
            .withUsername("root")
            .withPassword("12345678")
            .withDatabaseName("jdbc:mysql://localhost:3306/todo");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
