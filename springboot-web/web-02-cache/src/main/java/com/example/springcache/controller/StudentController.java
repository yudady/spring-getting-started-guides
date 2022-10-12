package com.example.springcache.controller;

import com.example.springcache.domain.Student;
import com.example.springcache.service.StudentService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	ConcurrentMapCacheManager cacheManager;

	@GetMapping("/")
	public Collection<String> getCacheNames() {
		return cacheManager.getCacheNames();
	}


	@GetMapping("/cache-manager-name")
	public String getCacheManagerName() {
		return cacheManager.getClass().getName();
	}

	@GetMapping("/student/{id}")
	public Student findStudentById(@PathVariable String id) {
		System.out.println("Searching by ID  : " + id);
		return studentService.getStudentByID(id);
	}
}
