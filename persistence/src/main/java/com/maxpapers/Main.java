package com.maxpapers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.dao.PhotoDao;
import com.maxpapers.dao.PhotoDaoImplDev;
import com.maxpapers.utils.ImageConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//TODO fix class

@Slf4j
@SpringBootApplication

public class Main {
    @Autowired
    private PhotoDao photoDao;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init(){
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.DEBUG);
//        addPhoto();
    }

    public void addPhoto(){
        Path finalPath = Paths.get("/home/victor/Pictures/db_photos/space/" +
                "pexels-sam-willis-3934512.jpg");
        String title = "The solar system jewel";
        Theme theme = Theme.Space;
        String tags ="stars, planet";
        /////////////////////////////////////////////////
        log.info(finalPath.toFile().toString());

        byte[] bytes = ImageConverter.toByteArray(finalPath);

        Photo photo = Photo.ofFile(title, theme, tags, bytes);
        try{
            Files.delete(finalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((PhotoDaoImplDev) photoDao).add(photo);
    }
}
