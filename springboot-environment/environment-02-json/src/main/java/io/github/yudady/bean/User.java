package io.github.yudady.bean;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ToString
@EqualsAndHashCode
@ConfigurationProperties("my")
public class User {

	public String name;

	public Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
