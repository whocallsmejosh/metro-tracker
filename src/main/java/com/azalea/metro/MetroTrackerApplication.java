package com.azalea.metro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MetroTrackerApplication {

	@RequestMapping("/")
	public String start() {
		return "Hello, I have started";
	}

	public static void main(String[] args) {
		SpringApplication.run(MetroTrackerApplication.class, args);
	}

}
