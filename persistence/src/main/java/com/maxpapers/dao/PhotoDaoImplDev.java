package com.maxpapers.dao;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import com.maxpapers.dao.common.Statements;
import com.maxpapers.utils.Ansi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiColors;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Repository
@Profile("dev")
// This class is used to fetch wallpapers and dump new images into the database (Development only)
public class PhotoDaoImplDev implements PhotoDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhotoDaoImplDev(BasicDataSource basicDataSource) {
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    @PostConstruct
    public void init(){
        log.info("======== Initializing PhotoDaoImplDev");
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

    // Used to insert manually new wallpapers into the database
    public int add(Photo photo){
        int rows = Statements.add(jdbcTemplate, photo);
        log.debug("{} Added {} entry to the database", Ansi.GREEN,rows);
        return rows;
    }
}
