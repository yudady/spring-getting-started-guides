package com.atguigu.boot.config;

import org.springframework.boot.env.YamlPropertySourceLoader;

public class JsonPropertySourceLoader extends YamlPropertySourceLoader {
    public JsonPropertySourceLoader() {
        System.out.println("\"JsonPropertySourceLoader\" = " + "JsonPropertySourceLoader");
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"json"};
    }
}