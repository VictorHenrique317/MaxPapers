package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface HomeService {
    CompletableFuture<Map<String, List<Photo>>> getHomePagePhotos();
    CompletableFuture<Map<String, List<Photo>>> queryByTheme(Theme theme);
}
