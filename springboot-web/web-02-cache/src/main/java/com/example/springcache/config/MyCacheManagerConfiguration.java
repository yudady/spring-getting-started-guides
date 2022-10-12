package com.example.springcache.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyCacheManagerConfiguration {


    /**
     * CacheManager是由 Spring Boot 自動配置的，
     * <p>
     * 您可以在完全初始化之前通過公開實現該CacheManagerCustomizer接口的 bean 進一步調整其配置。
     * <p>
     * 以下示例設置一個標誌，表示null不應將值傳遞給底層映射：
     *
     * @return https://docs.spring.io/spring-boot/docs/current/reference/html/io.html#io.caching.provider
     */
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return (cacheManager) -> cacheManager.setAllowNullValues(false);
    }

}