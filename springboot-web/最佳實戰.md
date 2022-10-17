# springboot 最佳實踐
● 引入場景依賴
○ https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter
● 查看自動配置了哪些（選做）
○ 自己份析，引入場景對應的自動配置一般都生效了
○ 配置文件中debug=true開啟自動配置報告。Negative（不生效）\Positive（生效）
● 是否需要修改
○ 參照文檔修改配置項
■ https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties
■ 自己份析。xxxxProperties綁定了配置文件的哪些。
○ 自定義加入或者替換組件
■ @Bean、@Component。。。
○ 自定義器  XXXXXCustomizer；  web-02-default-cache
○ ......




https://www.tutorialworks.com/spring-boot-prometheus-micrometer/


<dependency>
  <groupId>io.micrometer</groupId>
  <artifactId>micrometer-registry-prometheus</artifactId>
  <scope>runtime</scope>
</dependency>