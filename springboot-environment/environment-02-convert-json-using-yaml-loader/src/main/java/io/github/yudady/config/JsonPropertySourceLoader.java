package io.github.yudady.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.env.YamlPropertySourceLoader;

@Slf4j
public class JsonPropertySourceLoader extends YamlPropertySourceLoader {
	public JsonPropertySourceLoader() {
		log.warn("JsonPropertySourceLoader = {}", JsonPropertySourceLoader.class);
	}

	@Override
	public String[] getFileExtensions() {
		return new String[] { "json" };
	}
}