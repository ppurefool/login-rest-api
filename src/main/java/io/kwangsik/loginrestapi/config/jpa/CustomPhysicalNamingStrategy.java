package io.kwangsik.loginrestapi.config.jpa;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPhysicalNamingStrategy {
    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new CustomSpringPhysicalNamingStrategy();
    }
}