package io01.config;


import io01.bean.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig {

	@Bean
	public User user01() {
		User user = new User();
		user.name = "userName";
		user.age = 18;
		return user;
	}

}
