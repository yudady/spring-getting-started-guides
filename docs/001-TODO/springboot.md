---
title: Spring Boot Reference Documentation
tags: []
aliases: [Spring Boot Reference Documentation, springboot]
created_date: 2022-10-05 11:25
updated_date: 2022-10-06 15:14
---

# Spring Boot Reference Documentation

- Link: [Spring REST Docs](https://spring.io/projects/spring-restdocs#samples)

## reading list

- [x] [Documentation Overview](https://docs.spring.io/spring-boot/docs/current/reference/html/documentation.html#documentation) : 總覽
	- spring-cli 可以直接 run groovy
- [x] [Getting Started](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started) : Introducing Spring Boot, System Requirements, Servlet Containers, Installing Spring Boot, and Developing Your First Spring Boot Application
- [x] [Using Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using) : Build Systems, Structuring Your Code, Configuration, Spring Beans and Dependency Injection, DevTools, and more. : [[開發一個springboot流程]]
- [x] [Core Features](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features) :Profiles, Logging, Security, Caching, Spring Integration, Testing, and more.
	- ApplicationContextEvent 不能用 `@Bean` 創建
	- META-INF/spring.factories : **org.springframework.context.ApplicationListener**=com.example.project.MyListener
	- ApplicationRunner or CommandLineRunner
	- `@DataElasticsearchTest` , `@DataJpaTest` , `@DataJdbcTest`
- [ ] [Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web) :Servlet Web, Reactive Web, GraphQL, Embedded Container Support, Graceful Shutdown, and more.
	- mvc
		- Spring MVC 自動配置
			- `ContentNegotiatingViewResolver` and `BeanNameViewResolver`
			- static resources, including support for WebJars
			- `Converter`, `GenericConverter`, and `Formatter` beans
			- `HttpMessageConverters`
			- `MessageCodesResolver`
			- `ConfigurableWebBindingInitializer`
		- ErrorViewResolver :  @ExceptionHandler , @ControllerAdvice
	- shutdown
	- web security
	- web sesion
- [ ] [Data](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data) :SQL and NOSQL data access.
- [ ] [IO](https://docs.spring.io/spring-boot/docs/current/reference/html/io.html#io) Caching, Quartz Scheduler, REST clients, Sending email, Spring Web Services, and more.
- [ ] [Messaging](https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging) :JMS, AMQP, Apache Kafka, RSocket, WebSocket, and Spring Integration.
- [x] [Container Images](https://docs.spring.io/spring-boot/docs/current/reference/html/container-images.html#container-images) : build docker image , fat jar
- [ ] [Production-ready Features](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator) : 
	- 監控 Monitoring, 
	- Metrics, 
	- Auditing, and more.
- [ ] [Deploying Spring Boot Applications](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment) :Deploying to the Cloud, and Installing as a Unix application.
	- k8s : Kubernetes Container Lifecycle
	- Heroku

## [“How-to” Guides](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto)

- [ ] [1. Spring Boot Application](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.application)
- [ ] [2. Properties and Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.properties-and-configuration)
- [ ] [3. Embedded Web Servers](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.webserver)
- [ ] [4. Spring MVC](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.spring-mvc)
- [ ] [5. Jersey](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.jersey)
- [ ] [6. HTTP Clients](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.http-clients)
- [ ] [7. Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.logging)
- [ ] [8. Data Access](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-access)
- [ ] [9. Database Initialization](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization)
- [ ] [10. Messaging](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.messaging)
- [ ] [11. Batch Applications](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.batch)
- [ ] [12. Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.actuator)
- [ ] [13. Security](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.security)
- [ ] [14. Hot Swapping](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.hotswapping)
- [ ] [15. Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.testing)
- [ ] [16. Build](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.build)
- [ ] [17. Traditional Deployment](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.traditional-deployment)
