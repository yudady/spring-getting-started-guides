package com.atguigu.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

public class JsonPropertySourceFactory implements PropertySourceFactory {

    public JsonPropertySourceFactory() {
        System.out.println("JsonPropertySourceFactory ============================ ");
    }

    @Override
    public org.springframework.core.env.PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map source = objectMapper.readValue(resource.getInputStream(), Map.class);
        return new MapPropertySource("jsonPropertySourceFactory", source);
    }

}