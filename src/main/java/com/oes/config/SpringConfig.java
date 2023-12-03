package com.oes.config;

import org.springframework.context.annotation.*;

@Configuration
@Import({MyBatisConfig.class, JdbcConfig.class})
	/*
	等同于<context:component-scan base-package="com.lts.service">
	 */
@ComponentScan(value = "com.oes.service")
	/*
	将MyBatisConfig类和JdbcConfig类交给Spring管理
	 */
public class SpringConfig {
}

