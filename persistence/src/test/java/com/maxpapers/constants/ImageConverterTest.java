package com.maxpapers.constants;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageConverterTest {

    @Test(expected = IllegalArgumentException.class)
    public void imageConverterTest(){
        ImageConverter.toByteArray(Paths.get("this/path/doenst/exist"));
    }
}
