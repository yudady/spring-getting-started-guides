package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.loader.JarLauncher;

@SpringBootApplication
public class RestServiceApplication {

	//	public class JarLauncher extends ExecutableArchiveLauncher {
	//		public static void main(String[] args) throws Exception {
	//			new org.springframework.boot.loader.JarLauncher().launch(args);
	//		}
	//	}

	public static void main(String[] args) {

		JarLauncher jarLauncher = new JarLauncher();

		SpringApplication.run(RestServiceApplication.class, args);
	}

}
