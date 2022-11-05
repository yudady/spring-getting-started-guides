package me.marioscalas.helloworld;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }


}

@RequiredArgsConstructor
@Getter
class SaluteResponse {
    private final String salute;
}

@RestController
@RequestMapping("/")
class HelloWorldController {

    @GetMapping
    public me.marioscalas.helloworld.SaluteResponse getSalute() {
        return new me.marioscalas.helloworld.SaluteResponse("Hello Kubernetes World!!!");
    }
}
