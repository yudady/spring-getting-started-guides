package env02.springfactories.bean;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@ToString
@EqualsAndHashCode
@Configuration(proxyBeanMethods = true)
public class User {

	@Value("${my.name}")
	public String name;

	@Value("${my.age}")
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
