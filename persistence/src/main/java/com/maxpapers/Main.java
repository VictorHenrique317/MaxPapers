package com.maxpapers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.maxpapers.dao.PhotoDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class Main {
    private PhotoDao photoDao;

    @Autowired
    public Main(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init(){
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.DEBUG);
        log.info("========== NUMBER OF ROWS {}", photoDao.getEntryCount());

    }
}
