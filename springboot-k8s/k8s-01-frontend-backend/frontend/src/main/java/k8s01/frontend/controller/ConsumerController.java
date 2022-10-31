package k8s01.frontend.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import k8s01.frontend.model.ConsumerKey;
import k8s01.frontend.model.ConsumerUser;
import k8s01.frontend.service.ConsumerRestTemplateService;

@RestController
public class ConsumerController {

    @Autowired
    ConsumerRestTemplateService service;

    @GetMapping(value = "/")
    public ResponseEntity<Map<Integer, ConsumerUser>> listAllUsers() {
        return new ResponseEntity<>(service.getUser(), HttpStatus.OK);
    }


    @PostMapping(value = "/")
    public ResponseEntity<String> deleteUser(@RequestBody ConsumerKey key) {

        return new ResponseEntity<>(service.deleteuser(key.getKey()), HttpStatus.OK);
    }
}
