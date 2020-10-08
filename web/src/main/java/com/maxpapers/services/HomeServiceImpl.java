package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.constants.Attribute;
import com.maxpapers.utils.Ansi;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class HomeServiceImpl implements HomeService {
    private DaoService daoService;
    private final Random random = new Random();

    @Value("${home.photo.quantity}")
    private int homePhotoQuantity;

    @Autowired
    public HomeServiceImpl(DaoService daoService) {
        this.daoService = daoService;
    }

    @Async
    @Override
    public CompletableFuture<Map<String, List<Photo>>> queryByTheme(@NonNull Theme theme) {
        List<Photo> results = daoService.queryByTheme(theme);
        int firstHalf = (int) Math.ceil(results.size()/2d); // Bigger if size is odd

        return CompletableFuture.completedFuture(
                Map.of(Attribute.FIRST_COL_HOME_PHOTOS, results.subList(0, firstHalf),
                        Attribute.SECOND_COL_HOME_PHOTOS, results.subList(firstHalf, results.size())));
    }

    @Async
    @Override
    public CompletableFuture<Map<String, List<Photo>>> getHomePagePhotos() { // 16 images - 2 columns x 8 rows
        int upperBound = daoService.getEntryCount() + 1;
        Set<Integer> randomIndices = new HashSet<>();
        List<Photo> randomPhotos = new LinkedList<>();


        while (randomIndices.size() != homePhotoQuantity){
            int randomIndex = random.nextInt(upperBound);
            if (randomIndex == 0) randomIndex = 1;
            log.debug("{} GOT id {}", Ansi.GREEN, randomIndex);

            if (randomIndices.add(randomIndex)) { // Index wasn't picked already
                Photo randomPhoto = daoService.get(randomIndex);
                randomPhotos.add(randomPhoto);
                log.info("{} ADDED {} to the randomPhotos", Ansi.GREEN, randomPhoto.getTitle());
            }
        }
        int firstHalf = (int) Math.ceil(randomPhotos.size()/2d); // Bigger if size is odd

        return CompletableFuture.completedFuture(
                Map.of(Attribute.FIRST_COL_HOME_PHOTOS, randomPhotos.subList(0, firstHalf),
                        Attribute.SECOND_COL_HOME_PHOTOS, randomPhotos.subList(firstHalf, randomPhotos.size())));
    }
}
