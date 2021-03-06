package com.maxpapers.dao;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;

import java.util.List;

public interface PhotoDao {
    List<Photo> query(String query);
    List<Photo> queryByTheme(Theme theme);
    Photo get(int id);
    int getEntryCount();
}
