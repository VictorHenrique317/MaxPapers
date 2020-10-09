package com.maxpapers.services;

import com.maxpapers.common.Photo;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PhotoViewService {
    CompletableFuture<List<Photo>> getRelatedPhotosTo(int id, int amount);
    File getPhotoFileWithId(int id);
}
