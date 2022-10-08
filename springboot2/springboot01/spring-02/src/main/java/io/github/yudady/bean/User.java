package io.github.yudady.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ToString
@EqualsAndHashCode
//@Configuration(proxyBeanMethods = true)
//@ConfigurationProperties(prefix = "my")
@ConfigurationProperties("my")

public class User {

//	@Value("${my.name}")
	public String name;

//	@Value("${my.age}")
	public Integer age;

}
