package com.javacafe.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class EmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}
	
	@PostConstruct
	public void checkEnv() {
	    String value = System.getenv("EVENTHUB_CONN_STRING");
	    System.out.println("✅ ENV loaded: " + (value != null ? "Yes" : "No"));
	}


}
