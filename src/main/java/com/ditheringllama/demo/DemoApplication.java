package com.ditheringllama.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@ComponentScan(
	    basePackages = {
	      "com.ditheringllama.demo"
	    })
public class DemoApplication {

	public static void main(String[] args) {
		//DEBUG: Show Spring Version
		//System.out.println("version: " + SpringVersion.getVersion());
		SpringApplication.run(DemoApplication.class, args);
	}

}
