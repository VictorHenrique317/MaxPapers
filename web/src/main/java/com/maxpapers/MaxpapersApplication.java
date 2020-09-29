package com.maxpapers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MaxpapersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaxpapersApplication.class, args);
	}

	@PostConstruct
	private void init(){
		Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.setLevel(Level.INFO);
	}

}
