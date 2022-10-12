package io.github.yudady.service;

import io.github.yudady.model.ConsumerKey;
import io.github.yudady.model.ConsumerUser;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerRestTemplateService {

	public Map<Integer, ConsumerUser> getUser() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map<Integer, ConsumerUser>> response = restTemplate.exchange("http://localhost:9094/getUser",
			HttpMethod.GET, null, new ParameterizedTypeReference<Map<Integer, ConsumerUser>>() {
			});
		Map<Integer, ConsumerUser> users = response.getBody();

		return users;

	}

	public String deleteuser(Integer UserId) {

		RestTemplate restTemplate = new RestTemplate();
		ConsumerKey key = new ConsumerKey();
		key.setKey(UserId);
		String url = "http://localhost:9094/deleteUser";
		String commonResult = restTemplate.postForObject(url, key, String.class);

		return commonResult;
	}

}
