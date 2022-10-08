package io.github.yudady.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
