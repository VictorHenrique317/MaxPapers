package com.maxpapers.dao;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.dao.common.Statements;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Repository
@Profile("!dev") //prod
// This class is used to fetch wallpapers in the database
public class PhotoDaoImpl implements PhotoDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhotoDaoImpl(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init(){
        log.info("======== Initializing PhotoDaoImpl");
    }

    @Override
    public List<Photo> query(String query) {
        return Statements.query(jdbcTemplate, query);
    }

    @Override
    public List<Photo> queryByTheme(Theme theme) {
        return Statements.queryByTheme(jdbcTemplate, theme);
    }

    @Override
    public Photo get(int id) {
        return Statements.get(jdbcTemplate, id);
    }

    @Override
    public int getEntryCount() {
        return Statements.getEntryCount(jdbcTemplate);
    }
}
