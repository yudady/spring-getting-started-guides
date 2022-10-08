package io.github.yudady.bean;

import io.github.yudady.config.JsonPropertySourceFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@ToString
@EqualsAndHashCode
//@Configuration(proxyBeanMethods = true)

//@Component
@PropertySource(value = { "classpath:data.json" }, factory = JsonPropertySourceFactory.class)
@ConfigurationProperties(prefix = "my")
public class User {

	//	@Value("${my.name}")
	public String name;

//	@Value("${age}")
	public Integer age;

}
