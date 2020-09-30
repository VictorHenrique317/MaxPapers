package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class HomeServiceImpl implements HomeService {
    private PhotoService photoService;
    private final Random random = new Random();

    @Autowired
    public HomeServiceImpl(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    public List<Photo> queryByTheme(Theme theme) {
        return photoService.queryByTheme(theme);
    }

    @Override
    public List<Photo> getHomePagePhotos() {
        // 16 images - 4 columns x 4 rows
        int upperBound = photoService.getEntryCount() + 1;
        return null;

    }
}
