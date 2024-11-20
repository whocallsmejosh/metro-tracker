package com.azalea.metro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
@RestController
public class MetroTrackerApplication {

	Logger logger = LogManager.getLogger(MetroTrackerApplication.class);


	@Value("${version.file.location}")
	private String filePath;

	@RequestMapping("/")
	public String start() {
		StringBuilder version = new StringBuilder();

		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextLine()) {
				version.append(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			logger.error("An error occurred getting the version file: ", e);
			return "Error determining the version";
		}
        logger.info("version {} has been started successfully", version);
        logger.debug("debug version {} has been started successfully", version);
		return "Hello, I have started version " + version;
	}

	public static void main(String[] args) {
		SpringApplication.run(MetroTrackerApplication.class, args);
	}

}
