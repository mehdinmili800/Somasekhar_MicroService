package com.example.Somasekhar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SomasekharApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomasekharApplication.class, args);
	}

}
