package com.assistant.app.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assistant.app")
public class AssistantBoot {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AssistantBoot.class, args);	//run the spring boot app.
/*		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}*/
	}
}
