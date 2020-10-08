package com.maxpapers.services;

import com.maxpapers.common.Photo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface SearchService {
    CompletableFuture<Map<String, List<Photo>>> search(String query);
    CompletableFuture<Photo> get(int id);
    CompletableFuture<List<Photo>> getRelatedPhotosTo(int id, int  amount);
}
