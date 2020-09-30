package com.maxpapers.dao.common;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

// This class encapsulates query functionality, allowing PhotoDaoImpl and PhotoDaoImplDev to use the same queries
public final class Statements {
    private static final String QUERY_BY_TAGS = "SELECT * FROM wallpapers WHERE tags LIKE ?";
    private static final String QUERY_BY_THEME = "SELECT * FROM wallpapers WHERE theme = ?";
    private static final String QUERY_BY_ID = "SELECT * FROM wallpapers WHERE id = ?";
    public static final String INSERT_ENTRY = "INSERT INTO wallpapers(title, theme, tags, bytes) VALUES (?, ?, ?, ?)";

    public static List<Photo> query (JdbcTemplate jdbcTemplate, String query){
        query = "%"+query+"%";
       return jdbcTemplate.query(Statements.QUERY_BY_TAGS, new Object[]{query}, new PhotoMapper());
    }

    public static List<Photo> queryByTheme(JdbcTemplate jdbcTemplate, Theme theme) {
        return jdbcTemplate.query(Statements.QUERY_BY_THEME, new Object[]{theme.toString()}, new PhotoMapper());
    }

    public static Photo get(JdbcTemplate jdbcTemplate, int id) {
        return jdbcTemplate.queryForObject(Statements.QUERY_BY_ID, new Object[]{id}, new PhotoMapper());
    }

    public static int add(JdbcTemplate jdbcTemplate, Photo photo){
        return jdbcTemplate.update(INSERT_ENTRY,
                photo.getTitle(),
                photo.getTheme(),
                photo.getTagString(),
                photo.getBytes());
    }
}
