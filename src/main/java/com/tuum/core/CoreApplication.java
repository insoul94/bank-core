package com.tuum.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//TODO WHY THIS NEEDS TO BE HERE IF SHOULD BE AUTOSCANNED?? issue with Spring Boot 2.7.4 (2.3.4 worked)
//@ComponentScan({"com.tuum.core.controller", "com.tuum.core.data.service"})
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
