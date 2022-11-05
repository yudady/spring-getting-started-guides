package k8s05.frontend.service;

import java.util.Map;
import k8s05.frontend.model.ConsumerKey;
import k8s05.frontend.model.ConsumerUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerRestTemplateService {

    @Value("${backend.url}")
    String backend;

    public Map<Integer, ConsumerUser> getUser() {

        RestTemplate restTemplate = new RestTemplate(); // ! check  inject
        String url = backend + "/getUser";
        System.out.println("url = " + url);
        System.out.println("url = " + url);
        System.out.println("url = " + url);
        System.out.println("url = " + url);
        System.out.println("url = " + url);
        ResponseEntity<Map<Integer, ConsumerUser>> response = restTemplate.exchange(url,
            HttpMethod.GET, null, new ParameterizedTypeReference<Map<Integer, ConsumerUser>>() {
            });
        Map<Integer, ConsumerUser> users = response.getBody();

        return users;

    }

    public String deleteuser(Integer UserId) {

        RestTemplate restTemplate = new RestTemplate();
        ConsumerKey key = new ConsumerKey();
        key.setKey(UserId);
        String url = backend + "/deleteUser";
        String commonResult = restTemplate.postForObject(url, key, String.class);

        return commonResult;
    }

}
