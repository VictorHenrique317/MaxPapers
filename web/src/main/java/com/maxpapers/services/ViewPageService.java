package com.maxpapers.services;

import com.maxpapers.common.Photo;

import java.util.List;

public interface ViewPageService {
    List<Photo> getRelatedPhotosFor(Photo photo);
}
