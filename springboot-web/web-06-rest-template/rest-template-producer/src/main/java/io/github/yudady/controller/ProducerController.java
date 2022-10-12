package io.github.yudady.controller;

import io.github.yudady.model.ProducerKey;
import io.github.yudady.model.ProducerUser;
import io.github.yudady.service.ProducerUserService;
import java.util.Map;

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
public class ProducerController {

	@Autowired
	ProducerUserService service;

	@GetMapping(value = "/getUser")
	public ResponseEntity<Map<Integer, ProducerUser>> listAllUsers() {
		log.info("restemplate-producer getUser");
		return new ResponseEntity<>(service.getUser(), HttpStatus.OK);
	}

	@PostMapping(value = "/deleteUser")
	public ResponseEntity<Map<Integer, ProducerUser>> deleteUser(@RequestBody ProducerKey key) {
		log.info("restemplate-producer deleteUser");
		return new ResponseEntity<>(service.deleteuser(key.getKey()), HttpStatus.OK);
	}

}
