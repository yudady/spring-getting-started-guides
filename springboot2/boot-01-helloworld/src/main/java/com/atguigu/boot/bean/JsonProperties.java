package com.atguigu.boot.bean;

import com.atguigu.boot.config.JsonPropertySourceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = "classpath:data.json")
//@ConfigurationProperties(prefix = "my")

@PropertySource(value = {"classpath:data.json"}, factory = JsonPropertySourceFactory.class)
public class JsonProperties {

    @Value("my.name")
    public String name;


}