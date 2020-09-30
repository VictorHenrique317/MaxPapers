package com.maxpapers;

import com.maxpapers.common.Photo;
import com.maxpapers.services.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PersistenceTest {
    @Autowired
    private PhotoService photoService;

    public PersistenceTest() {
    }

    @Test
    public void queryTest() {
        List<Photo> results = photoService.query("landscape");
        assertThat(results).extracting("id")
                .contains(6, 13, 26);
    }
}
