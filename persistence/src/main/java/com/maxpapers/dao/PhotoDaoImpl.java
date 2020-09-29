package com.maxpapers.dao;

import com.maxpapers.common.Photo;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoImpl implements PhotoDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhotoDaoImpl(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Photo> query(String query) {
        String sql = "SELECT * FROM wallpapers WHERE tags LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{query}, new PhotoMapper());
    }

    @Override
    public List<Photo> queryByTheme(String theme) {
        String sql = "SELECT * FROM wallpapers WHERE theme LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{theme}, new PhotoMapper());

    }

    @Override
    public Photo get(int id) {
        String sql = "SELECT * FROM wallpapers WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PhotoMapper());
    }
}
