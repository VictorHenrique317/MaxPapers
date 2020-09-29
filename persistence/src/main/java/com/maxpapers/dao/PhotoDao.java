package com.maxpapers.dao;

import com.maxpapers.common.Photo;

import java.util.List;

public interface PhotoDao {
    List<Photo> query(String query);
    List<Photo> queryByTheme(String theme);
    Photo get(int id);
}
