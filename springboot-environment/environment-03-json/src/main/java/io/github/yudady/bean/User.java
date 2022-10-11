package io.github.yudady.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@EqualsAndHashCode
@Configuration(proxyBeanMethods = true)
public class User {

	@Value("${my.name}")
	public String name;

	@Value("${my.age}")
	public Integer age;

}
