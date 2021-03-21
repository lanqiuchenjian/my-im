package com.myim.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.myim")
public class WebServerLauncher {
	public static void main(String[] args) {
		SpringApplication.run(WebServerLauncher.class, args);
	}
}