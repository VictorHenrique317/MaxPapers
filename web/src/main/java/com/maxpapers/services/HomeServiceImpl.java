package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class HomeServiceImpl implements HomeService {
    private DaoService daoService;
    private final Random random = new Random();

    @Value("${home.photo.quantity}") private int homePhotoQuantity;

    @Autowired
    public HomeServiceImpl(DaoService daoService) {
        this.daoService = daoService;
    }

    @Async
    @Override
    public CompletableFuture<List<Photo>> queryByTheme(Theme theme) {
        return CompletableFuture.completedFuture(daoService.queryByTheme(theme));
    }

    @Async
    @Override
    public CompletableFuture<List<Photo>> getHomePagePhotos() { // 16 images - 4 columns x 4 rows
        int upperBound = daoService.getEntryCount() + 1;

        List<Photo> randomPhotos = new ArrayList<>();
        for (int i = 1; i <= homePhotoQuantity ; i++){
            int randomIndex = random.nextInt(upperBound);
            if (randomIndex == 0) randomIndex = 1;
            log.debug("{} GOT id {}", Ansi.GREEN, randomIndex);

            Photo randomPhoto = daoService.get(randomIndex);
            randomPhotos.add(randomPhoto);
            log.info("{} ADDED {} to the randomPhotos", Ansi.GREEN,randomPhoto.getTitle());
        }
        return CompletableFuture.completedFuture(randomPhotos);
    }
}
