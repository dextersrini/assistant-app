package com.assistant.app.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.assistant.app.model.Assistant;

@SpringBootApplication
@ComponentScan(basePackages = "com.assistant")
public class AssistantBoot {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AssistantBoot.class, args); // run the spring boot
		System.out.println(applicationContext.getApplicationName() + " is started and ready");
		for (String beanname : applicationContext.getBeanDefinitionNames()) 
			System.out.println(beanname);
		System.out.println(((Assistant) applicationContext.getBean("assistant")).getAppname());
		System.out.println(((Assistant) applicationContext.getBean("assistant")).getAppage());
		System.out.println(((Assistant) applicationContext.getBean("assistant")).getAppscope());
	}
}
