package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Override
    public List<Photo> queryByTheme(Theme theme) {
        return daoService.queryByTheme(theme);
    }

    @Override
    public List<Photo> getHomePagePhotos() { // 16 images - 4 columns x 4 rows
        int upperBound = daoService.getEntryCount() + 1;

        List<Photo> randomPhotos = new ArrayList<>();
        for (int i = 1; i <= homePhotoQuantity ; i++){
            int randomIndex = random.nextInt(upperBound);
            randomPhotos.add(daoService.get(randomIndex));
        }
        return randomPhotos;
    }
}
