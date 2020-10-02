package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface HomeService {
    CompletableFuture<List<Photo>> getHomePagePhotos();
    CompletableFuture<List<Photo>> queryByTheme(Theme theme);
}
