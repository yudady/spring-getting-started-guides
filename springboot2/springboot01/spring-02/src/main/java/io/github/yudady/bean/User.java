package io.github.yudady.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ToString
@EqualsAndHashCode
@Component
@ConfigurationProperties(prefix = "my")
public class User {

	@Value("${my.name}")
	public String name;

	@Value("${my.age}")
	public Integer age;

}
