package k8s06.frontend.controller;


import java.util.Map;
import k8s06.frontend.model.ConsumerKey;
import k8s06.frontend.model.ConsumerUser;
import k8s06.frontend.service.ConsumerRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
