package com.maxpapers.services;

import com.maxpapers.common.Photo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchService {
    CompletableFuture<List<Photo>> search(String query);
    CompletableFuture<Photo> get(int id);
}
