package com.maxpapers.dao.common;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.utils.Ansi;
import com.maxpapers.utils.ByteArrayEncoder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
// This class encapsulates query functionality, allowing PhotoDaoImpl and PhotoDaoImplDev to use the same queries
public final class Statements {
    private static final String QUERY_BY_TAGS = "SELECT * FROM wallpapers WHERE tags LIKE ?";
    private static final String QUERY_BY_THEME = "SELECT * FROM wallpapers WHERE theme = ?";
    private static final String QUERY_BY_ID = "SELECT * FROM wallpapers WHERE id = ?";
    public static final String INSERT_ENTRY = "INSERT INTO wallpapers" +
            "(title, theme, tags, bytes, encodedString) VALUES (?, ?, ?, ?, ?)";
    public static final String QUERY_ENTRY_COUNT = "SELECT COUNT(id) FROM wallpapers";

    public static List<Photo> query(@NonNull JdbcTemplate jdbcTemplate, @NonNull String query) {
        query = "%" + query + "%";
        log.info("{} Executing query with String {}", Ansi.BLUE, query);
        List<Photo> results = jdbcTemplate.query(Statements.QUERY_BY_TAGS, new Object[]{query}, new PhotoMapper());
        log.info("{} Found {} results", Ansi.BLUE, results.size());
        return results;
    }

    public static List<Photo> queryByTheme(@NonNull JdbcTemplate jdbcTemplate,@NonNull Theme theme) {
        List<Photo> results = jdbcTemplate.query
                (Statements.QUERY_BY_THEME, new Object[]{theme.toString()}, new PhotoMapper());
        if (results.isEmpty()) throw new NullPointerException("Empty query list");
        return results;
    }

    public static Photo get(@NonNull JdbcTemplate jdbcTemplate, int id) {
        Photo result;
        try {
            result = jdbcTemplate.queryForObject(Statements.QUERY_BY_ID, new Object[]{id}, new PhotoMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NullPointerException("No entry found");
        }
        return result;
    }

    public static int add(@NonNull JdbcTemplate jdbcTemplate,@NonNull Photo photo) {
        return jdbcTemplate.update(INSERT_ENTRY,
                photo.getTitle(),
                photo.getTheme(),
                photo.getTagString(),
                photo.getBytes(),
                photo.getEncodedString());
    }

    @NonNull
    public static int getEntryCount(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject(QUERY_ENTRY_COUNT, Integer.class);
    }
}
