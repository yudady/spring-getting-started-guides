package com.atguigu.boot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = "classpath:data.json")
@ConfigurationProperties(prefix = "my")
public class JsonProperties {

    @Value("name")
    public String name;
}