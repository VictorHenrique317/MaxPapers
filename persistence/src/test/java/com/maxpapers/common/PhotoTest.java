package com.maxpapers.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PhotoTest {
    public PhotoTest() {
    }

    @Test(expected = NullPointerException.class)
    public void photoEntryTest(){
        Photo photo = Photo.ofEntry(1, null, Theme.Space, "dummy", new byte[]{1}, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void photoEntryIdTest(){
        Photo photo = Photo.ofEntry(0, "dummy", Theme.Space, "dummy", new byte[]{1}, "test");
    }

    @Test(expected = NullPointerException.class)
    public void photoFileTest(){
        Photo photo = Photo.ofFile("dummy", null, "dummy", new byte[]{1},
                "test");
    }
}
