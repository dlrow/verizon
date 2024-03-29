package com.verizon.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.*")
@EnableSwagger2
@EntityScan(basePackages="com.verizon.domain")
@EnableJpaRepositories(basePackages = "com.verizon.dao")
public class AppMain {

	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}

}
