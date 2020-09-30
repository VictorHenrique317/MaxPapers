package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;

import java.util.List;

public interface HomeService {
    List<Photo> getHomePagePhotos();
    List<Photo> queryByTheme(Theme theme);
}
