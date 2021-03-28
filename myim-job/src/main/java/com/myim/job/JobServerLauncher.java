package com.myim.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.myim")
@MapperScan(basePackages = "com.myim.server.dao")
public class JobServerLauncher {
	public static void main(String[] args) {
		SpringApplication.run(JobServerLauncher.class, args);
	}
}