package com.study.springjrproj.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

@Configuration

public class AppConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Pageable getPageable() {
        return Pageable.ofSize(4);
    }
}

