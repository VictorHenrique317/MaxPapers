package com.maxpapers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class MaxpapersApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MaxpapersApplication.class, args);
	}

	@PostConstruct
	private void init(){
		Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.setLevel(Level.DEBUG);

	}

}
