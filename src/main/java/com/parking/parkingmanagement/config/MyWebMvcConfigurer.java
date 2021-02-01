package com.parking.parkingmanagement.config;//package com.course.server.config;

import com.parking.parkingmanagement.interceptors.SysInterceptor;
import com.parking.parkingmanagement.resolvers.CurrentUserArgumentResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }
    //后台拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         /*addPathPatterns 用于添加拦截规则
         excludePathPatterns 用户排除拦截*/
        registry.addInterceptor(applicationContext.getBean(SysInterceptor.class)).addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/test","/user/updateToken");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(applicationContext.getBean(CurrentUserArgumentResolver.class));
    }
}
