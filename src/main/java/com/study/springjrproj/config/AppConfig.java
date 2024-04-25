package com.study.springjrproj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

@Configuration
//@PropertySource("classpath:application.yaml")
//@ComponentScan(basePackages = "org.example",
//        resourcePattern = "**/*.class",
//        useDefaultFilters = false,
//        includeFilters = {
//                @Filter(type = FilterType.ANNOTATION,
//                        value = Component.class),
//                @Filter(type = FilterType.ASSIGNABLE_TYPE,
//                        value = CompanyRepository.class),
//                @Filter(type = FilterType.REGEX,
//                        pattern = "org\\..Repository"),
//        }
//)
public class AppConfig {
}

