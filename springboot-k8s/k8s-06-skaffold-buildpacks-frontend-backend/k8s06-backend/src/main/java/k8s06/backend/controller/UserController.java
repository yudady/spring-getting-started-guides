package k8s06.backend.controller;


import java.util.Map;
import k8s06.backend.model.UserServer;
import k8s06.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(value = "/")
    public String index() {
        return "k8s01-backend";
    }

    @GetMapping(value = "/getUser")
    public ResponseEntity<Map<Integer, UserServer>> listAllUsers() {
        log.info("restemplate-producer getUser");
        return new ResponseEntity<>(service.getUser(), HttpStatus.OK);
    }

    @PostMapping(value = "/deleteUser")
    public ResponseEntity<Map<Integer, UserServer>> deleteUser(@RequestBody UserKey key) {
        log.info("restemplate-producer deleteUser");
        return new ResponseEntity<>(service.deleteuser(key.getKey()), HttpStatus.OK);
    }

    static class UserKey {
        private Integer key;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

    }

}
