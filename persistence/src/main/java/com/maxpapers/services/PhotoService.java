package com.maxpapers.services;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;

import java.util.List;

public interface PhotoService {
    List<Photo> query(String query);
    List<Photo> queryByTheme(Theme theme);
    Photo get(int id);
}
