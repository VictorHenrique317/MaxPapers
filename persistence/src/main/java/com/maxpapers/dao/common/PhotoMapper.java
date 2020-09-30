package com.maxpapers.dao.common;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoMapper implements RowMapper<Photo> {

    @Override
    public Photo mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String theme = resultSet.getString("theme");
        String tags = resultSet.getString("tags");
        byte[] bytes = resultSet.getBytes("bytes");
        return Photo.ofEntry(id, title, Theme.valueOf(theme), tags, bytes);
    }
}
