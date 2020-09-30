package com.maxpapers.dao.common;

import com.maxpapers.common.Photo;
import com.maxpapers.common.Theme;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@PropertySource("classpath:application.properties")
public class StatementsTest {
    private JdbcTemplate jdbcTemplate;
    @Value("${db.username}") private String username;
    @Value("${db.password}") private String password;
    @Value("${db.driverClassName}") private String driverClassName;
    @Value("${db.url}") private String url;

    public StatementsTest() {
    }

    @PostConstruct
    public void init(){
        log.info("================= INITIALIZING TEST CLASS ===========================");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Test
    public void queryTest() {
        List<Photo> results = Statements.query(jdbcTemplate, "landscape");
        assertThat(results).extracting("id")
                .contains(6, 13, 26);

        results = Statements.query(jdbcTemplate, "sky");
        assertThat(results).extracting("title")
                .contains("Heaven Mountains", "Forest moutains", "Exploring the sky", "The great cupola",
                        "Orion brace");
    }
    @Test(expected = NullPointerException.class)
    public void queryNullTest(){
        Statements.query(jdbcTemplate, "thisTagDoesn'tExist");
    }

    @Test
    public void queryByThemeTest(){
        List<Photo> results = Statements.queryByTheme(jdbcTemplate, Theme.People);
        assertThat(results).extracting("title")
                .contains("Hands tight", "Taking the shot", "Hair on the wind");
    }

    @Test(expected = NullPointerException.class)
    public void queryByThemeNullTest(){
        Statements.queryByTheme(jdbcTemplate,null);
    }

    @Test
    public void getTest(){
        Photo test = Statements.get(jdbcTemplate, 1);
        assertThat(test).extracting("title")
                .isEqualTo("Aurora Mountain");

        test = Statements.get(jdbcTemplate, 15);
        assertThat(test).extracting("title")
                .isEqualTo("Connecting with the nature");
    }

    @Test(expected = NullPointerException.class)
    public void getNullTest(){
        Statements.get(jdbcTemplate, 0);
    }

    @Test
    public void getEntryCountTest() {
        assertThat(Statements.getEntryCount(jdbcTemplate)).isNotNull();
    }
}
