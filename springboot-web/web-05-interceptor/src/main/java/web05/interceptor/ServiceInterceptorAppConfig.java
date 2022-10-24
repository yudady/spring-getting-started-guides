package web05.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class ServiceInterceptorAppConfig implements WebMvcConfigurer {
    @Autowired
    HTTPInterceptor serviceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Custom interceptor, add intercept path and exclude intercept path
        registry.addInterceptor(serviceInterceptor).addPathPatterns("/**");
    }
}