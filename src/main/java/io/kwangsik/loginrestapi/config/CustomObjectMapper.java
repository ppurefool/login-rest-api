package io.kwangsik.loginrestapi.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class CustomObjectMapper {
    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

        objectMapper.setVisibility(objectMapper.getSerializationConfig().getDefaultVisibilityChecker().
                withFieldVisibility(JsonAutoDetect.Visibility.ANY).
                withGetterVisibility(JsonAutoDetect.Visibility.NONE).
                withSetterVisibility(JsonAutoDetect.Visibility.NONE).
                withCreatorVisibility(JsonAutoDetect.Visibility.NONE).
                withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
        return objectMapper;
    }
}