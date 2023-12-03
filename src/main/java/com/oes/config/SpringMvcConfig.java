package com.oes.config;

import com.oes.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//等同于<context:component-scan base-package="com.lts.controller"/>
@ComponentScan("com.oes.controller")
//等同于<mvc:annotation-driven/>，还不完全相同
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    /*@Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.jsp/","/jsp/stuRegister.jsp","/jsp/teaRegister.jsp","/images/**","/fonts/**");
    }*/
}

