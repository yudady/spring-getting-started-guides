package com.knf.dev.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knf.dev.demo.helper.WordHelper;

@RestController
public class WordController {

	@Autowired
	WordHelper wordHelper;

	@GetMapping(value = {"/", "/api/word"}, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	public ResponseEntity<InputStreamResource> word() throws IOException, InvalidFormatException {

		ByteArrayInputStream bis = wordHelper.generateWord();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=mydoc.docx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
	}
}
