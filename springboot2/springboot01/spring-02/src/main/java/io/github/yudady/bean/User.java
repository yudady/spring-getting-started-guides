package io.github.yudady.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ToString
@EqualsAndHashCode
@ConfigurationProperties("my")
public class User {

	public String name;

	public Integer age;

}
